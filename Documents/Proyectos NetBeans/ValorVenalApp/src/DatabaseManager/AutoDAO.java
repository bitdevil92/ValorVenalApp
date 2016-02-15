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
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Tobi
 */
public class AutoDAO {
    
    private static final String QUERY = "SELECT * FROM "+Datasource.TABLE_NAME;
    private static final String QUERY_BUSQUEDA = "SELECT * FROM "+Datasource.TABLE_NAME+" WHERE UPPER(marca) LIKE ";            
           
    public static HashMap<Integer,AutoDTO> searchAuto(AutoDTO auto){
        HashMap<Integer,AutoDTO> hashCoches = new HashMap<Integer, AutoDTO>();
        ResultSet rs = null;
        PreparedStatement st = null;
        Datasource data = null;
        
        try {
            data = new Datasource();            
            
            ArrayList<String> wordList = getWordList(auto.getModelo());            
            st = data.getStatement(QUERY_BUSQUEDA+getMarcaParametersString(auto.getMarca())+getModelParametersString(wordList));           
                                                
            System.out.println(QUERY_BUSQUEDA+getMarcaParametersString(auto.getMarca())+getModelParametersString(wordList));                        
            
            rs = data.executeSelect(st);            
            
            while(rs.next()){                
                AutoDTO objCoche = new AutoDTO();                                
                                        
                objCoche.setId(rs.getInt("id"));
                objCoche.setMarca(rs.getString("marca"));
                objCoche.setModelo(rs.getString("modelo"));
                objCoche.setIniPerComercial(rs.getInt("ini_per_comercial"));
                objCoche.setFinPerComercial(rs.getInt("fin_per_comercial"));
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
            JOptionPane.showMessageDialog(null, "ERROR CONECTANDO DB");
            System.exit(0);
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
                                
                objCoche.setId(rs.getInt("id"));
                objCoche.setMarca(rs.getString("marca"));
                objCoche.setModelo(rs.getString("modelo"));
                objCoche.setIniPerComercial(rs.getInt("ini_per_comercial"));
                objCoche.setFinPerComercial(rs.getInt("fin_per_comercial"));
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
            JOptionPane.showMessageDialog(null, "ERROR CONECTANDO DB");
            System.exit(0);
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
    
    private static String getMarcaParametersString(String marca){
        String sMarcaParameter = marca;
        if(sMarcaParameter == null){
            sMarcaParameter = "UPPER(marca)";
        }else{
            sMarcaParameter = "'"+sMarcaParameter.toUpperCase()+"%'";
        }
        
        return sMarcaParameter+ " AND UPPER(modelo) LIKE ";
    }
    
    private static String getModelParametersString(ArrayList<String> list){
        String sParameters = "";
        
        if(list.size()== 0){
            sParameters = " UPPER(modelo)";            
        }else{            
            sParameters = " '%"+list.get(0).toUpperCase()+"%'";  
        }
        
        for(int i=0; i< list.size()-1; i++){
            sParameters = sParameters+" AND UPPER(modelo) LIKE '%"+list.get(i+1).toUpperCase()+"%'";
        }                                 
        return sParameters;        
    }
    
}
