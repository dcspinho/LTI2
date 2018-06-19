
package sensor;

import java.io.IOException;

public class Receber {
    long millis;
    byte AD0;
    byte AD1;
    
    public Receber(byte AD0, byte AD1){
        this.AD0=AD0;
        this.AD1=AD1;             
    }
    
    public long getMillis(){
        return this.millis;
    }
    
    public void setMillis(long m){
        this.millis=m;
    }
    
    /*recebe a trama START ou STOP e o tempo ATUAL(quando lê essa mesma trama) em millis*/
    public byte[] verificar(byte[] trama, long millis, int comecar) throws IOException{
        trama_Data t = new trama_Data();
        trama_Error e = new trama_Error();
        setMillis(System.currentTimeMillis());
        
        /*System.out.println("***********   START recebido   *********");
        for(int i=0; i<trama.length;i++){
        System.out.println(unsignedByteToInt(trama[i]));
        }*/
        
        if(trama[0]==0){        /*recebeu um START*/
            //System.out.println("START -> DATA");
            
            t.setTS(trama[1],trama[2],trama[3],trama[4]);
            t.setPM(trama[5],trama[6],trama[7],trama[8]);
            t.setPA(trama[9],trama[10],trama[11],trama[12]);
            t.setNS(trama[13]);
            
            /*int PA=t.getPAint();*/
            //System.out.println("PA : "+t.getPAint());
            
            return t.construir(millis, AD0, AD1, comecar/*, PA*/);
            
        }
        else if(trama[0]!=0 || trama[0]!=3){
            System.out.println("ERRO");
            e.setTS(trama[1],trama[2],trama[3],trama[4]);
            byte enviar[]=new byte[9];
            enviar=e.construir(AD0, AD1); 
            return enviar;
            
        }
        return trama;
        
    }
    public int unsignedByteToInt(byte b) {
       return (int) b & 0xFF;
    }
}