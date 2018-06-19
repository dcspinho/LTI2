
package sensor;

import java.io.IOException;

public class sensor {
  
    long millis;
    byte AD0;
    byte AD1;
    int comecar;
    
    public sensor(){
    }
    
    public sensor(byte AD0, byte AD1){
        this.AD0=AD0;
        this.AD1=AD1; 
    }
    
    public long getMillis(){
        return this.millis;        
    }
    
    public int getComecar(){
        return this.comecar;
    }

    public void setComecar(int comecar){
        this.comecar=comecar;
    }
   
    public void setMillis(long m){
        this.millis=m;
    }
    
    public byte[] novo(byte trama[]) throws IOException{        
        setMillis(System.currentTimeMillis());
        
        Receber r = new Receber(AD0, AD1);
        return r.verificar(trama, this.getMillis(), this.getComecar());    
    }
    
}