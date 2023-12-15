package persistencia;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import dados.Categoria;
import dados.Conta;
import dados.Registro;
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

public class RegistroDAO {
    private static RegistroDAO unicaInstancia; 
    private CategoriaDAO categoriaDAO;
    private Connection conn;
    
    private RegistroDAO(){
        this.conn = DatabaseConexao.getConnection();
    }
    
    // Classe Singleton para uma unica conexao ser criada
    public static synchronized RegistroDAO getInstancia(){
        if(unicaInstancia == null){
            unicaInstancia = new RegistroDAO();
        }
        return unicaInstancia;
    }

    // CRUD 
    public ArrayList<Registro> listar(Conta conta){

        categoriaDAO = CategoriaDAO.getInstancia();

        String sql = "SELECT * FROM registros WHERE id_conta = ?";
        ArrayList<Registro> registros = new ArrayList<>();

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, conta.getId());

            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                // Definindo os argumentos
                Categoria categoria = categoriaDAO.getCategoria(resultado.getInt("id_categoria"));
                Tipo_Registro tipo = Tipo_Registro.valueOf(resultado.getString("tipo"));
                String nota = resultado.getString("nota");
                LocalDate data = resultado.getDate("data_registro").toLocalDate();
                // Criando o registro
                Registro registro = new Registro(conta, resultado.getDouble("valor"), tipo, categoria, data, nota);
                registro.setId(resultado.getInt("id_registro"));
                //adicionando no array
                registros.add(registro);
            }
            return registros;
        }catch(SQLException e){
            Logger.getLogger(RegistroDAO.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    public boolean inserir(Registro registro){
        String sql = "INSERT INTO registros(id_categoria, id_conta, valor, nota, tipo, data_registro) VALUES (?,?,?,?,?::tipo_registro,?::date) LIMIT 1 RETURNING id_registro";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, registro.getCategoria().getId());
            stmt.setInt(2, registro.getConta().getId());
            stmt.setDouble(3, registro.getValor());
            stmt.setString(4, registro.getNota());
            stmt.setString(5, registro.getTipo().getTipo());


            String data = registro.getData().format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));

            stmt.setString(6, data);
            
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                // Adionando o id dado pelo banco de dados ao registro
                registro.setId(resultado.getInt("id_registro"));
            }   
            return true;
        }catch(SQLException e){
            Logger.getLogger(RegistroDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    public boolean alterar(Registro registro){
        String sql = "UPDATE registros SET id_categoria = ?, id_conta = ?, valor = ?, nota = ?, tipo = ?::tipo_registro, data_registro = ?::date WHERE id_registro = ?";
        
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, registro.getCategoria().getId());
            stmt.setInt(2, registro.getConta().getId());
            stmt.setDouble(3, registro.getValor());
            stmt.setString(4, registro.getNota());
            stmt.setString(5, registro.getTipo().getTipo());

            String data = registro.getData().format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));

            stmt.setString(6, data);

            stmt.setString(6, data);
            stmt.setInt(7, registro.getId());
            stmt.executeUpdate();
            return true;
        } catch(SQLException e){
            Logger.getLogger(RegistroDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }

    }
    public boolean remover(Integer id_registro){
        String sql = "DELETE FROM registros WHERE id_registro = ?";
        
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_registro);
            stmt.executeUpdate();
            return true;
        } catch(SQLException e){
            Logger.getLogger(RegistroDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
}