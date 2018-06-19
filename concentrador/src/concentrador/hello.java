/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concentrador;


import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Calendar;

public class hello {
    

    byte TP; 
    byte[] TS = new byte[7];         
    byte[] NS = new byte[2];
    ArrayList<byte[]> lista_sensores=new ArrayList<byte[]>();

    
    
    public byte[] constroiHello(ArrayList<byte[]> lista_sens){
        
        lista_sensores=lista_sens;
        int nr_sensores = lista_sens.size();
        //System.out.println("nr de codigos: "+nr_sensores);
               
        byte buffer[]=new byte[10+(2*nr_sensores)];
        //tipo
        buffer[0]=(byte)0;
        
        TS=getTimestamp();
        
        //timestamp
        buffer[1]=TS[0];
        buffer[2]=TS[1];
        buffer[3]=TS[2];
        buffer[4]=TS[3];
        buffer[5]=TS[4];
        buffer[6]=TS[5];
        buffer[7]=TS[6];
        
        
        //ns
        byte temp[];
        temp=ByteBuffer.allocate(4).putInt(nr_sensores).array();
        buffer[8]=temp[2];
        buffer[9]=temp[3];
        
        
        
        int j=0;
        byte tempp[]=new byte[2];
        //codigos
        for(int i=10; i<10+(2*nr_sensores);i++){
            tempp=lista_sens.get(j);
            buffer[i]=tempp[0];
            i++;
            buffer[i]=tempp[1];
            j++;      
        }        
        
        
        //System.out.println("hello:");
       /* for (byte b : buffer) {
            System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1) + "  ______  " + unsignedByteToInt(b));    
        }*/
        
        return buffer;
        
    }
    

    public byte[] getTimestamp(){
        
        byte[] time=new byte[7];
        
        Calendar calendar = Calendar.getInstance();
        long milliseconds=calendar.getTimeInMillis();  
        //System.out.println("timestamp: "+milliseconds);
        
        byte[] time1;
        time1=ByteBuffer.allocate(8).putLong(milliseconds).array(); 
        
       
        /*for (byte b : time1) {
            System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1) + "  ______  " + unsignedByteToInt(b));    
        }*/
        
        time[0]=time1[1];
        time[1]=time1[2];
        time[2]=time1[3];
        time[3]=time1[4];
        time[4]=time1[5];
        time[5]=time1[6];
        time[6]=time1[7];
        
        /*System.out.println("Tempo que vou enviar mesmo");
        for (byte b : time) {
            System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1) + "  ______  " + unsignedByteToInt(b));    
        }*/

        return time;
    }
    
    
    public ArrayList<byte[]> acederListaSens(){
        return lista_sensores;
    }
    
    
    public int unsignedByteToInt(byte b) {
                return (int) b & 0xFF;
    }
}

