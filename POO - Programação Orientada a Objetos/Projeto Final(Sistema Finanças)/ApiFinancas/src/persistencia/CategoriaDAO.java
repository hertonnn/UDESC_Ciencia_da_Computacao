package persistencia;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import dados.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Persistência de Dados - Java DAO (Data Access Object) 
// Isso da flexibilidade ao código, uma vez que que esta classe pode ser adulterada 
// para uma conexão a um XML,Banco de dados(postgresql ou outro) e etc. sem que 
// outras classes que a utilizam sejam afetadas de forma severa.
// Documentação ResultSet: https://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html

public class CategoriaDAO {
    private static CategoriaDAO unicaInstancia; 
    private Connection conn;
    
    private CategoriaDAO(){
        this.conn = DatabaseConexao.getConnection();
    }
    
    // Classe Singleton para uma unica conexao ser criada
    public static synchronized CategoriaDAO getInstancia(){
        if(unicaInstancia == null){
            unicaInstancia = new CategoriaDAO();
        }
        return unicaInstancia;
    }

    // CRUD 
    public ArrayList<Categoria> listar(int id_dono){
        String sql = "SELECT * FROM categorias WHERE id_dono = ?";
        ArrayList<Categoria> categorias = new ArrayList<>();

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_dono);

            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                Categoria categoria = new Categoria(resultado.getString("nome"));
                categoria.setId(resultado.getInt("id_categoria"));
                categorias.add(categoria);
            }
            return categorias;
        }catch(SQLException e){
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    public boolean inserir(Categoria categoria, int id_dono){
        String sql = "INSERT INTO categorias(id_dono, nome) VALUES (?,?) LIMIT 1 RETURNING id_categoria;";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_dono);
            stmt.setString(2, categoria.getNome());
            
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                // Adionando o id dado pelo banco de dados ao categoria
                categoria.setId(resultado.getInt("id_categoria"));
            }   
            return true;
        }catch(SQLException e){
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    public boolean alterar(Categoria categoria){
        String sql = "UPDATE categorias SET nome = ? WHERE id_categoria = ?";
        
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, categoria.getNome());
            stmt.setInt(2, categoria.getId());
            stmt.executeUpdate();
            return true;
        } catch(SQLException e){
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }

    }
    public boolean remover(Integer id_categoria){
        String sql = "DELETE FROM categorias WHERE id_categoria = ?";
        
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_categoria);
            stmt.executeUpdate();
            return true;
        } catch(SQLException e){
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    public Categoria getCategoria(int id_categoria){

        String sql = "SELECT * FROM categorias WHERE id_categoria = ?";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_categoria);

            ResultSet resultado = stmt.executeQuery();
            Categoria categoria = null;

            while (resultado.next()) {
                categoria = new Categoria(resultado.getString("nome"));
            }
            return categoria;
        }catch(SQLException e){
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
}