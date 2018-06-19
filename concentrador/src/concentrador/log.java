/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concentrador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class log {
    
    
    
    public synchronized void atualiza(String s) throws IOException{
        
        File arquivo = new File("log.log");   //se já existir, será sobreescrito  
        FileWriter fw = new FileWriter(arquivo, true);  
        BufferedWriter bw;  
        bw = new BufferedWriter(fw);

        bw.write(s);
        bw.newLine();
        
        bw.flush();  
        bw.close();  
    
    }
    
    
}
