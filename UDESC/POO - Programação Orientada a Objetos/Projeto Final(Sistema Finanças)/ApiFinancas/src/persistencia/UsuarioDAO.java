package persistencia;
import java.util.logging.Level;
import java.util.logging.Logger;
import dados.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Persistência de Dados - Java DAO (Data Access Object) 
// Isso da flexibilidade ao código, uma vez que que esta classe pode ser adulterada 
// para uma conexão a um XML,Banco de dados(postgresql ou outro) e etc. sem que 
// outras classes que a utilizam sejam afetadas de forma severa.
// Documentação ResultSet: https://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html

public class UsuarioDAO {

    private static UsuarioDAO unicaInstancia; 
    private Connection conn;
    
    private UsuarioDAO(){
        this.conn = DatabaseConexao.getConnection();
    }
    
    // Classe Singleton para uma unica conexao ser criada
    public static synchronized UsuarioDAO getInstancia(){
        if(unicaInstancia == null){
            unicaInstancia = new UsuarioDAO();
        }
        return unicaInstancia;
    }
    // CRUD 
    public boolean inserir(Usuario usuario){
        String sql = "INSERT INTO usuarios(nome, senha) VALUES (?,?) LIMIT 1 RETURNING id_usuario";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                // Adionando o id dado pelo banco de dados ao usuario
                usuario.setId(resultado.getInt("id_usuario"));
            }   
            return true;
        }catch(SQLException e){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    public boolean alterar(Usuario usuario){
        String sql = "UPDATE usuarios SET nome = ?, senha = ? WHERE id_usuario = ?";
        
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            stmt.setInt(3, usuario.getId());
            stmt.executeUpdate();
            return true;
        } catch(SQLException e){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }

    }
    public boolean remover(Integer id_usuario){
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_usuario);
            stmt.executeUpdate();
            return true;
        } catch(SQLException e){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    public Usuario validar(String nome, String senha){

        String sql = "SELECT senha, id_usuario FROM usuarios WHERE nome = ?";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet resultado =  stmt.executeQuery();
            while(resultado.next()){
                if(resultado.getString("senha").equals(senha)){
                    Usuario usuario = new Usuario(nome, senha);
                    usuario.setId(resultado.getInt("id_usuario"));
                    return usuario;
                }
            }
            
            return null;
        }catch(SQLException e){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

}