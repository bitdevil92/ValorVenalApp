/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardatabasecreatorapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tobi
 */
public class CsvDecrypter {
    
    static String[] EXCLUDED_VALUES = {"\",Mo","\"Marc","\"cili","\"gr/k","\" Eur","\",\"\"\"","\",E81","\"BMW,","\"Valo"};    
    
    public static void csvCleaner(File fichero){
        File newFile = new File(fichero.getParent(), "tempFile");
                
        try {
            BufferedReader br = new BufferedReader(new FileReader(fichero));
            BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));
                        
            String line = br.readLine();
            while (line != null) {
                if (!new ArrayList<String>(Arrays.asList(EXCLUDED_VALUES)).contains(line.substring(0, 5))) {                       
                    bw.write(line+"\r\n");
                }
                line = br.readLine();
            }
            
            br.close();
            bw.close();
            
            String fileName = fichero.getName();
            String fileParent = fichero.getParent();
                        
            fichero.delete();            
            
            newFile.renameTo(new File(fileParent,fileName));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CsvDecrypter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CsvDecrypter.class.getName()).log(Level.SEVERE, null, ex);
        }                                
    }
      
    
    public static ArrayList<Object> csvLineDecrypter(String csvLine) {
        ArrayList<String> tokenList = new ArrayList();
        ArrayList decryptedList = new ArrayList();

        StringTokenizer stComa = new StringTokenizer(csvLine, ",");
        while (stComa.hasMoreTokens()) {
            tokenList.add(stComa.nextToken());
        }       
                                        
        decryptedList.add(tokenList.get(0).replace("\"", "")); //MARCA
        decryptedList.add(tokenList.get(1).replace("\"", "")); //MODELO
        
        decryptedList.add(getPerComercial(tokenList.get(2))[0]); //PER_COMERCIAL        
        decryptedList.add(getPerComercial(tokenList.get(2))[1]); //PER_COMERCIAL        

        decryptedList.add(tokenList.get(3).replace("\"", "")); //CILINDRADA        
        decryptedList.add(tokenList.get(4).replace("\"", "")); //N_CILINDROS
        decryptedList.add(tokenList.get(5).replace("\"", "")); //COMBUSTIBLE
        decryptedList.add(tokenList.get(6).replace("\"", "")); //POT_KW               
        decryptedList.add(tokenList.get(7).replace("\"", "")+"."+tokenList.get(8).replace("\"", "")); //POT_FISCAL        
        decryptedList.add(tokenList.get(9).replace("\"", "")); //EMISIONES
        decryptedList.add(tokenList.get(10).replace("\"", "")); //POT_CV                        
        decryptedList.add(tokenList.get(11).replace("\"", ""));//VALOR                
                        
        return decryptedList;
    }

    public static ArrayList<ArrayList> getCsvDecryptedList(File fichero) {
        ArrayList decryptedList = new ArrayList();
        csvCleaner(fichero);                
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(fichero));
                        
            String line = br.readLine();
            while (line != null) {                  
                decryptedList.add(csvLineDecrypter(line));                                
                line = br.readLine();                
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CsvDecrypter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CsvDecrypter.class.getName()).log(Level.SEVERE, null, ex);
        }

        return decryptedList;
    }
    
    private static String[] getPerComercial(String s){
        String[] perComercialArray = new String[2];
                
        if(s.length() > 4 && !s.contains("\"")){
            perComercialArray[0] = s.substring(0,4);            
            
            if(s.length() > 5){
                perComercialArray[1] = s.substring(5,9);                
            }else{
                perComercialArray[1] = null;
            }
        }else{
            perComercialArray[0] = null;
        }
        return perComercialArray;
    }
    
    
}
