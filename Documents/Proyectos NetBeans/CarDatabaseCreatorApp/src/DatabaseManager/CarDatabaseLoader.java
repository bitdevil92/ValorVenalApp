/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseManager;

import java.util.ArrayList;

/**
 *
 * @author Tobi
 */
public class CarDatabaseLoader {
    
    private static final int BUFFER_SIZE = 16;
    
    public static void loadCarInToDatabase(AutoDTO auto){        
        AutoDAO.insertAutomovil(auto);        
    }
    
    public static void loadCarsInToDatabase(ArrayList<ArrayList> carList){
        int count = 1;
        
        for(ArrayList<String> specList: carList){
            AutoDTO auto = new AutoDTO();
            
            auto.setMarca(specList.get(0));
            auto.setModelo(specList.get(1));
            
            auto.setIniPerComercial(Integer.parseInt(specList.get(2)));
            auto.setFinPerComercial(Integer.parseInt(specList.get(3)));
            
            if(!specList.get(4).equals("")){                  
                auto.setCilindrada(Integer.parseInt(specList.get(4)));
            }else{
                auto.setCilindrada(0);
            }
            
            if(!specList.get(5).equals("")){                  
                auto.setNumCilindros(Integer.parseInt(specList.get(5)));
            }else{
                auto.setNumCilindros(0);
            }            
            
            auto.setCombustible(specList.get(6));
            auto.setPotenciaKW(specList.get(7));
            auto.setPotenciaFiscal(Double.parseDouble(specList.get(8)));
                         
            auto.setEmisiones(specList.get(9));                
            
            if(!specList.get(10).equals("")){                  
                auto.setPotenciaCv(Double.parseDouble(specList.get(10))); 
            }else{
                auto.setPotenciaCv(0);
            }     
                                
            auto.setValor(Integer.parseInt(specList.get(11)));
                        
            System.out.println(count);
            loadCarInToDatabase(auto);
            
            count++;
        }                
    }
        
    public static void loadCarsInToDatabaseBuffer(ArrayList<ArrayList> carList){
        int packageCount = 1;
        ArrayList<AutoDTO> bufferAuto = new ArrayList<>();
        
        for(ArrayList<String> specList: carList){                                    
            AutoDTO auto = new AutoDTO();
            
            auto.setMarca(specList.get(0));
            auto.setModelo(specList.get(1));
            
            if(specList.get(2) != null){
                auto.setIniPerComercial(Integer.parseInt(specList.get(2)));                
            }else{
                auto.setIniPerComercial(Integer.parseInt("0"));                
            }
            
            if(specList.get(3) != null){
                auto.setFinPerComercial(Integer.parseInt(specList.get(3)));                
            }else{
                auto.setFinPerComercial(Integer.parseInt("1"));                
            }
            
            if(!specList.get(4).equals("")){                  
                auto.setCilindrada(Integer.parseInt(specList.get(4)));
            }else{
                auto.setCilindrada(0);
            }
            
            if(!specList.get(5).equals("")){                  
                auto.setNumCilindros(Integer.parseInt(specList.get(5)));
            }else{
                auto.setNumCilindros(0);
            }            
            
            auto.setCombustible(specList.get(6));
            auto.setPotenciaKW(specList.get(7));
            auto.setPotenciaFiscal(Double.parseDouble(specList.get(8)));
                         
            auto.setEmisiones(specList.get(9));                
            
            if(!specList.get(10).equals("")){                  
                auto.setPotenciaCv(Double.parseDouble(specList.get(10))); 
            }else{
                auto.setPotenciaCv(0);
            }     
                                
            auto.setValor(Integer.parseInt(specList.get(11)));
            
            bufferAuto.add(auto);
            
            if(bufferAuto.size() >= BUFFER_SIZE){
                AutoDAO.insertAutomovilBuffer(bufferAuto);
                System.out.println("PACKAGE: "+packageCount);
                packageCount++;
                bufferAuto.clear();
            }
        }                
    }
    
    
}

