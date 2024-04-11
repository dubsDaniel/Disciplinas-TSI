
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
    
    private final String database="poo1";
    private final String user="root";
    private final String password="";
    
    public Connection getConexao(){
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost/"+database+"?userSSL=false", user, password);
            
        }catch(SQLException ex){
            System.out.println("Erro ao tentar realizar conexão com o banco");
            throw new RuntimeException(ex);
            
        }
        
        
        
    }
    
    
    
    
}
