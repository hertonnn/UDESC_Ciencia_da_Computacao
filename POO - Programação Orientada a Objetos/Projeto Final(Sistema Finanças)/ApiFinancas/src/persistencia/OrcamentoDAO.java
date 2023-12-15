package persistencia;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import dados.Categoria;
import dados.Usuario;
import dados.Orcamento;
import dados.Tipo_Registro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Persistência de Dados - Java DAO (Data Access Object) 
// Isso da flexibilidade ao código, uma vez que que esta classe pode ser adulterada 
// para uma conexão a um XML,Banco de dados(postgresql ou outro) e etc. sem que 
// outras classes que a utilizam sejam afetadas de forma severa.
// Documentação ResultSet: https://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html

public class OrcamentoDAO {
    private static OrcamentoDAO unicaInstancia; 
    private CategoriaDAO categoriaDAO;
    private Connection conn;
    
    private OrcamentoDAO(){
        this.conn = DatabaseConexao.getConnection();
    }
    
    // Classe Singleton para uma unica conexao ser criada
    public static synchronized OrcamentoDAO getInstancia(){
        if(unicaInstancia == null){
            unicaInstancia = new OrcamentoDAO();
        }
        return unicaInstancia;
    }

    // CRUD 
    public ArrayList<Orcamento> listar(Usuario usuario){

        categoriaDAO = CategoriaDAO.getInstancia();

        String sql = "SELECT * FROM orcamentos WHERE id_dono = ?";
        ArrayList<Orcamento> orcamentos = new ArrayList<>();

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, usuario.getId());

            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                // Definindo os argumentos
                Categoria categoria = categoriaDAO.getCategoria(resultado.getInt("id_categoria"));
                Tipo_Registro tipo = Tipo_Registro.valueOf(resultado.getString("tipo"));
                String nota = resultado.getString("nota");
                LocalDate data_criacao = resultado.getDate("data_criacao").toLocalDate();
                LocalDate data_inicio = resultado.getDate("data_inicio").toLocalDate();
                LocalDate data_fim = resultado.getDate("data_fim").toLocalDate();
                // Criando o orcamento
                Orcamento orcamento = new Orcamento(usuario, tipo, resultado.getDouble("valor"), categoria, data_criacao, data_inicio, data_fim, nota);
                //adicionando no array
                orcamento.setId(resultado.getInt("id_orcamento"));
                orcamentos.add(orcamento);
            }
            return orcamentos;
        }catch(SQLException e){
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    public boolean inserir(Orcamento orcamento){
        String sql = "INSERT INTO orcamentos(id_dono, id_categoria, valor, nota, data_criacao, data_inicio, data_fim, tipo) VALUES (?,?,?,?,?::date,?::date, ?::date, ?::tipo_registro) LIMIT 1 RETURNING id_orcamento";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, orcamento.getUsuario().getId());
            stmt.setInt(2, orcamento.getCategoria().getId());
            stmt.setDouble(3, orcamento.getLimite());
            stmt.setString(4, orcamento.getNota());
            
            String data_criacao = orcamento.getData_criacao().format(DateTimeFormatter.ofPattern("YYY-MM-dd"));
            String data_inicio = orcamento.getData_inicio().format(DateTimeFormatter.ofPattern("YYY-MM-dd"));
            String data_fim = orcamento.getData_fim().format(DateTimeFormatter.ofPattern("YYY-MM-dd"));

            stmt.setString(5, data_criacao);
            stmt.setString(6, data_inicio);
            stmt.setString(7, data_fim);
            stmt.setString(8, orcamento.getTipo().getTipo());
            
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                // Adionando o id dado pelo banco de dados ao orcamento
                orcamento.setId(resultado.getInt("id_orcamento"));
            }   
            return true;
        }catch(SQLException e){
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    public boolean alterar(Orcamento orcamento){
        String sql = "UPDATE orcamentos SET id_dono = ?, id_categoria = ?, valor = ?, nota = ?, data_criacao = ?::date, data_inicio = ?::date, data_fim = ?::date, tipo = ?::tipo_registro WHERE id_orcamento = ?";
        
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, orcamento.getUsuario().getId());
            stmt.setInt(2, orcamento.getCategoria().getId());
            stmt.setDouble(3, orcamento.getLimite());
            stmt.setString(4, orcamento.getNota());
            
            String data_criacao = orcamento.getData_criacao().format(DateTimeFormatter.ofPattern("YYY-MM-dd"));
            String data_inicio = orcamento.getData_inicio().format(DateTimeFormatter.ofPattern("YYY-MM-dd"));
            String data_fim = orcamento.getData_fim().format(DateTimeFormatter.ofPattern("YYY-MM-dd"));

            stmt.setString(5, data_criacao);
            stmt.setString(6, data_inicio);
            stmt.setString(7, data_fim);
            stmt.setString(8, orcamento.getTipo().getTipo());
            stmt.setInt(9, orcamento.getId());
            stmt.executeUpdate();
            return true;
        } catch(SQLException e){
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }

    }
    public boolean remover(Integer id_orcamento){
        String sql = "DELETE FROM orcamentos WHERE id_orcamento = ?";
        
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_orcamento);
            stmt.executeUpdate();
            return true;
        } catch(SQLException e){
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
}