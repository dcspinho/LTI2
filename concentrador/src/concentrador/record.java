
package concentrador;

import java.nio.ByteBuffer;
import java.util.ArrayList;



public class record {
    
       
        ArrayList<String> linha=new ArrayList<String>();
        byte[] cod= new byte[2];   
        byte[] timestamp= new byte[7];
        byte[] pa= new byte[4];  
        byte ns;
        byte[] valor= new byte[2];  
        
        
        public void addLista(String s){
            linha.add(s);
        }

        public void preencheRecord() throws InterruptedException{

            int codigo=Integer.parseInt(linha.get(0));           
            byte[] codd=ByteBuffer.allocate(4).putInt(codigo).array();
            cod[0]=codd[2];
            cod[1]=codd[3];
  

            long time=Long.parseLong(linha.get(1));
            byte[] timee=ByteBuffer.allocate(8).putLong(time).array();
            //timestamp
            timestamp[0]=timee[1];
            timestamp[1]=timee[2];
            timestamp[2]=timee[3];
            timestamp[3]=timee[4];
            timestamp[4]=timee[5];
            timestamp[5]=timee[6];
            timestamp[6]=timee[7];
            
            
            int paa=Integer.parseInt(linha.get(2));           
            pa=ByteBuffer.allocate(4).putInt(paa).array();
            
            
            int nss=Integer.parseInt(linha.get(3));
            byte[] nsss=ByteBuffer.allocate(4).putInt(nss).array();
            ns=nsss[3];
            
            
            int val=Integer.parseInt(linha.get(4));           
            byte[] vall=ByteBuffer.allocate(4).putInt(val).array();
            valor[0]=vall[2];
            valor[1]=vall[3];
             
        }   
        
        
    public byte[] constroiRecord(){
        byte rec[]=new byte [16];
        
        rec[0]=cod[0]; 
        rec[1]=cod[1];
        rec[2]=timestamp[0]; 
        rec[3]=timestamp[1];
        rec[4]=timestamp[2];
        rec[5]=timestamp[3];  
        rec[6]=timestamp[4];      
        rec[7]=timestamp[5];       
        rec[8]=timestamp[6];  
        rec[9]=pa[0];
        rec[10]=pa[1];   
        rec[11]=pa[2];       
        rec[12]=pa[3];      
        rec[13]=ns;        
        rec[14]=valor[0];       
        rec[15]=valor[1];      
   
       
        
        return rec;
    } 
        
        
    public int unsignedByteToInt(byte b) {
                return (int) b & 0xFF;
    }    

        
}
