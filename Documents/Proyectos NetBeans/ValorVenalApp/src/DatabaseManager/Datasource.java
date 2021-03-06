/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tobi
 */
public class Datasource {
    
    private Connection con = null;

    
    public static final String TABLE_NAME = "coches";
    public static final String DB_NAME = "ValorAutos";
    
    public Datasource() throws ClassNotFoundException, SQLException {                        
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        
        String db_url = "jdbc:derby:.\\CarDB\\"+DB_NAME+".DB;";
        con = DriverManager.getConnection(db_url);  
        
        System.out.println("CONEXION ESTABLECIDA con: "+DB_NAME);                
    }
    
    public PreparedStatement getStatement (String sql) throws SQLException{
        if(con != null){
            return con.prepareStatement(sql);
        }else{
            return null;
        }
    }
    
    public ResultSet executeSelect (PreparedStatement st) throws SQLException{
        if(con != null){
            ResultSet rs = st.executeQuery();
            System.out.println("Select ejecutado correctamente");
            return rs;
        }else{
            System.out.println("Error select");
            return null;
        }
    }
    
    public int executeUpdate (PreparedStatement st) throws SQLException{
        if(con != null){
            int regs = st.executeUpdate();
            System.out.println("Update ejecutado correctamente");
            st.close();
            return regs;
        }else{
            System.out.println("Error update");
            return 0;
        }
    }
    
    public void closeConnection(){
        try {
            if(con != null){               
                con.close();
                System.out.println("Conexion cerrada");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Datasource.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error closeConnection");
        }
    }    
    
}
