/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tobi
 */
public class AutoDAO {
    
    private static final String QUERY = "SELECT * FROM "+Datasource.TABLE_NAME;
    private static final String INSERT = "INSERT INTO "+Datasource.TABLE_NAME+" (marca, modelo, per_comercial, "
            + "cilindrada, n_cilindros, combustible, potencia_kw, potencia_fiscal, emisiones, "
            + "potencia_cv, valor)"
            + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";    
    private static final String QUERY_BUSQUEDA = "SELECT * FROM "+Datasource.TABLE_NAME+" WHERE marca LIKE CONCAT(IFNULL(?,marca),'%') AND modelo LIKE";
    
    
    public static void insertAutomovil(AutoDTO auto){
        Datasource data = null;
        PreparedStatement st = null;
        
        try {
            data = new Datasource();
            st = data.getStatement(INSERT);
            st.setString(1, auto.getMarca());
            st.setString(2, auto.getModelo());
            st.setString(3, auto.getPerComercial());
            st.setInt(4, auto.getCilindrada());
            st.setInt(5, auto.getNumCilindros());
            st.setString(6, auto.getCombustible());
            st.setString(7, auto.getPotenciaKW());
            st.setDouble(8, auto.getPotenciaFiscal());
            st.setString(9, auto.getEmisiones());
            st.setDouble(10, auto.getPotenciaCv());
            st.setInt(11, auto.getValor());
            data.executeUpdate(st);                                    
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println("ERROR CONEXION DATABASE");
            Logger.getLogger(AutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(st != null) st.close();
            } catch (SQLException ex) {
                Logger.getLogger(AutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            data.closeConnection();
        }                
    }
    
    public static void insertAutomovilBuffer(ArrayList<AutoDTO> bufferAutos){
        Datasource data = null;
        PreparedStatement st = null;
        
        try {
            data = new Datasource();
            for(AutoDTO auto: bufferAutos){
                st = data.getStatement(INSERT);
                st.setString(1, auto.getMarca());
                st.setString(2, auto.getModelo());
                st.setString(3, auto.getPerComercial());
                st.setInt(4, auto.getCilindrada());
                st.setInt(5, auto.getNumCilindros());
                st.setString(6, auto.getCombustible());
                st.setString(7, auto.getPotenciaKW());
                st.setDouble(8, auto.getPotenciaFiscal());
                st.setString(9, auto.getEmisiones());
                st.setDouble(10, auto.getPotenciaCv());
                st.setInt(11, auto.getValor());
                data.executeUpdate(st);                                                    
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println("ERROR CONEXION DATABASE BUFFER");
            Logger.getLogger(AutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(st != null) st.close();
            } catch (SQLException ex) {
                Logger.getLogger(AutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            data.closeConnection();
        }        
    }
   
    public static HashMap<Integer,AutoDTO> searchAuto(AutoDTO auto){
        HashMap<Integer,AutoDTO> hashCoches = new HashMap<Integer, AutoDTO>();
        ResultSet rs = null;
        PreparedStatement st = null;
        Datasource data = null;
        
        try {
            data = new Datasource();            
            
            ArrayList<String> wordList = getWordList(auto.getModelo());            
            st = data.getStatement(QUERY_BUSQUEDA+getModelParametersString(wordList));
            st.setString(1, auto.getMarca());                                           
            
            System.out.println(st);
            
            rs = data.executeSelect(st);            
            
            while(rs.next()){                
                AutoDTO objCoche = new AutoDTO();                                
                                
                objCoche.setId(rs.getInt("idCoches"));
                objCoche.setMarca(rs.getString("marca"));
                objCoche.setModelo(rs.getString("modelo"));
                objCoche.setPerComercial(rs.getString("per_comercial"));
                objCoche.setCilindrada(rs.getInt("cilindrada"));
                objCoche.setNumCilindros(rs.getInt("n_cilindros"));
                objCoche.setCombustible(rs.getString("combustible"));
                objCoche.setPotenciaKW(rs.getString("potencia_kw"));
                objCoche.setPotenciaFiscal(rs.getDouble("potencia_fiscal"));
                objCoche.setEmisiones(rs.getString("emisiones"));
                objCoche.setPotenciaCv(rs.getDouble("potencia_cv"));
                objCoche.setValor(rs.getInt("valor"));                                        
                
                hashCoches.put(objCoche.getId(), objCoche);
            }               
            System.out.println(hashCoches.size());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println("ERROR CONEXION DATABASE");
            Logger.getLogger(AutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(rs != null)rs.close();
                if(st != null)st.close();
            } catch (SQLException ex) {
                Logger.getLogger(AutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            data.closeConnection();
        }
        
        return hashCoches;   
    }
    
    public static HashMap<Integer,AutoDTO> consultarTodos (){
        HashMap<Integer,AutoDTO> hashCoches = new HashMap<Integer, AutoDTO>();
        ResultSet rs = null;
        PreparedStatement st = null;
        Datasource data = null;
        
        try {
            data = new Datasource();
            st = data.getStatement(QUERY);
            rs = data.executeSelect(st);
            
            while(rs.next()){                
                AutoDTO objCoche = new AutoDTO();                                
                
                objCoche.setId(rs.getInt("idCoches"));
                objCoche.setMarca(rs.getString("marca"));
                objCoche.setModelo(rs.getString("modelo"));
                objCoche.setPerComercial(rs.getString("per_comercial"));
                objCoche.setCilindrada(rs.getInt("cilindrada"));
                objCoche.setNumCilindros(rs.getInt("n_cilindros"));
                objCoche.setCombustible(rs.getString("combustible"));
                objCoche.setPotenciaKW(rs.getString("potencia_kw"));
                objCoche.setPotenciaFiscal(rs.getDouble("potencia_fiscal"));
                objCoche.setEmisiones(rs.getString("emisiones"));
                objCoche.setPotenciaCv(rs.getDouble("potencia_cv"));
                objCoche.setValor(rs.getInt("valor"));                                        
                
                hashCoches.put(objCoche.getId(), objCoche);
            }               
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println("ERROR CONEXION DATABASE");
            Logger.getLogger(AutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(rs != null)rs.close();
                if(st != null)st.close();
            } catch (SQLException ex) {
                Logger.getLogger(AutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            data.closeConnection();
        }
        
        return hashCoches;        
    }
    
    private static ArrayList<String> getWordList(String s){
        ArrayList<String> wordList = new ArrayList<>();
        if(s != null){
            StringTokenizer st = new StringTokenizer(s, " ");
            while(st.hasMoreTokens()){
               wordList.add(st.nextToken());
            }            
        }
        return wordList;
    }
    
    private static String getModelParametersString(ArrayList<String> list){
        String sParameters = "";
        
        if(list.size()== 0){
            sParameters = " modelo";            
        }else{            
            sParameters = " '%"+list.get(0)+"%'";  
        }
        
        for(int i=0; i< list.size()-1; i++){
            sParameters = sParameters+" AND modelo LIKE '%"+list.get(i+1)+"%'";
        }                 
        
        System.out.println(sParameters);
        return sParameters;        
    }
    
}
