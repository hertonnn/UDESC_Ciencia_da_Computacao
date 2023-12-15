package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConexao {

    static private Connection connection; 

    public static Connection getConnection() {
        if(connection == null){
            String usuario = "postgres";
            String senha = "udesc";
            String DATABASE_URL = "jdbc:postgresql://localhost:5432/financasBD"; 

            try{
            // Estabelece conexão com o banco de dados
                connection = DriverManager.getConnection(DATABASE_URL, usuario, senha);
            }
            catch(SQLException c){
            //API que permite aos usuários rastrear o erro gerado a partir de classes específicas.
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, c);
            }
        }
        return connection;
    } 
}
