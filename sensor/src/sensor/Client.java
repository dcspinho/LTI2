
package sensor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;


public class Client {
    
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException, InterruptedException {
            
        int cod;
        int comSockets;
        String ip;
        String[] begin;
       
        
        //ler ficheiro config
        begin=conf();
               
        comSockets=Integer.parseInt(begin[0]);
        ip=begin[1];
        
        cod=Integer.parseInt(begin[2]);
        
        byte codd[]=intToBytes(cod);  
        
        byte cod_ard[]=new byte[2];
        cod_ard[0]=codd[2];
        cod_ard[1]=codd[3];
        
       /* for (byte b : cod_ard) {
            System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1) );    
        } */
        
        
        //***********
        

        ler l=new ler(comSockets, ip, cod_ard);
        l.comecar();
        
    }
    
    
    
    
    
    //CONF feita no txt
    public static String[] conf() throws IOException{    
            String arquivoCSV = "conf.txt";
            BufferedReader br = null;
            String linha = "";
            String csvDivisor = ";";
            String[] begin= new String[2];
            
            try {
                br = new BufferedReader(new FileReader(arquivoCSV));
                               
                while ((linha = br.readLine()) != null) {
                    begin = linha.split(csvDivisor); 
                }
                

            } catch (FileNotFoundException e) {
                
            } catch (IOException e) {
                
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                            
                    }
                }
            }
        return begin;
    }
    
    public static byte[] intToBytes( int i ) {
        ByteBuffer bb = ByteBuffer.allocate(4); 
        bb.putInt(i); 
        return bb.array();
    }
    
    
}
