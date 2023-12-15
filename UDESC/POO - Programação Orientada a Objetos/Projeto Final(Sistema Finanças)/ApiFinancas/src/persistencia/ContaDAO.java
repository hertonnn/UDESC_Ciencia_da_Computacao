package persistencia;
import java.util.logging.Level;
import java.util.logging.Logger;

import dados.Conta;
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

public class ContaDAO {
    private static ContaDAO unicaInstancia; 
    private Connection conn;
    
    private ContaDAO(){
       this.conn = DatabaseConexao.getConnection();
    }
    
    // Classe Singleton para uma unica conexao ser criada
    public static synchronized ContaDAO getInstancia(){
        if(unicaInstancia == null){
            unicaInstancia = new ContaDAO();
        }
        return unicaInstancia;
    }

    // CRUD 
    public Conta getConta(Usuario usuario){
        String sql = "SELECT * FROM contas WHERE id_dono = ?";
        Conta conta = null;
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, usuario.getId());
            
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                conta = new Conta(resultado.getString("nome"), resultado.getDouble("saldo"));
                conta.setId(resultado.getInt("id_conta"));
            }   
            return conta;
        }catch(SQLException e){
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, e);
            return conta;
        }
    }
    public boolean inserir(Conta conta, int id_dono){
        String sql = "INSERT INTO contas(id_dono, nome, saldo) VALUES (?,?,?) LIMIT 1 RETURNING id_conta";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_dono);
            stmt.setString(2, conta.getNome());
            stmt.setDouble(3, conta.getSaldo());
            
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                // Adionando o id dado pelo banco de dados ao conta
                conta.setId(resultado.getInt("id_conta"));
            }   
            return true;
        }catch(SQLException e){
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    public boolean alterar(Conta conta){
        String sql = "UPDATE contas SET nome = ?, saldo = ? WHERE id_conta = ?";
        
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, conta.getNome());
            stmt.setDouble(2, conta.getSaldo());
            stmt.setInt(3, conta.getId());
            stmt.executeUpdate();
            return true;
        } catch(SQLException e){
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }

    }
    public boolean remover(Integer id_conta){
        String sql = "DELETE FROM contas WHERE id_conta = ?";
        
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_conta);
            stmt.executeUpdate();
            return true;
        } catch(SQLException e){
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

}