package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

public class ImagemDAO {
    private static ImagemDAO unicaInstancia;
    private Connection conn;

    private ImagemDAO(){
        // Só mudar a classe de conexão aqui
        this.conn = DatabaseConexao.getConnection();
    }
    // Classe Singleton para uma unica conexao ser criada
    public static synchronized ImagemDAO getInstancia(){
        if(unicaInstancia == null){
            unicaInstancia = new ImagemDAO();
        }
        return unicaInstancia;
    }

     // CRUD 
    public ImageIcon getImagem(Integer id_imagem){
        String sql = "SELECT * FROM imagens_perfil WHERE id_imagem = ?";
        ImageIcon img = null;
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_imagem);
            
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                byte[] imagem = resultado.getBytes("imagem");
                img = new ImageIcon(imagem);
            }   
        }catch(SQLException e){
            Logger.getLogger(ImagemDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return img;
    }
    public boolean inserir(String caminho, int id_dono){
        String sql = "INSERT INTO imagens_perfil(id_dono, imagem) VALUES (?,?) LIMIT 1 RETURNING id_imagem";
        try{

            // Acessando imagem pelo caminho recebido
            File file_imagem = new File(caminho);
            InputStream imagem = new FileInputStream(file_imagem);

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_dono);
            stmt.setBinaryStream(2, imagem);
            
            ResultSet resultado = stmt.executeQuery();
            
            // retorno id dado pelo bando de dados para a imagem, caso precise na classe 
            while(resultado.next()){
                int id_imagem = resultado.getInt("id_imagem");
            }  
            
            return true;
        }catch(SQLException | FileNotFoundException c){
            Logger.getLogger(ImagemDAO.class.getName()).log(Level.SEVERE, null, c);
            return false;
        }
    }
    public boolean alterar(String nova_imagem, int id_antiga_imagem) {
        String sql = "UPDATE imagens_perfil SET imagem = ? WHERE id_imagem = ?";
        
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Acessando imagem pelo caminho recebido
            File file_imagem = new File(nova_imagem);
            InputStream imagem = new FileInputStream(file_imagem);

            stmt.setBinaryStream(1, imagem);
            stmt.setInt(2, id_antiga_imagem);
            stmt.executeUpdate();
            return true;
        } catch(SQLException | FileNotFoundException e){
            Logger.getLogger(ImagemDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }

    }
    public boolean remover(Integer id_imagem){
        String sql = "DELETE FROM imagens_perfil WHERE id_imagem = ?";
        
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_imagem);
            stmt.executeUpdate();
            return true;
        } catch(SQLException e){
            Logger.getLogger(ImagemDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

}
