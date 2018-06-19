
package sensor;

public class trama_Error {
    byte TP;                        //8 bits
    byte AD[]=new byte[2];          //16 bits
    byte TS[]=new byte[4];          //32 bits
    byte ER;          //8 bits
    byte END;

    public trama_Error(){
        TP=(byte)15;
        END=(byte)255;
        
        /*AD é para eliminar. Vai ficar no conf.txt. Feito na Andreia*/
        AD[0]=(byte)5;          
    }
    
    public byte getTP(){  
        return this.TP;
    } 
    public byte getAD(int i){    //retorna o byte da posição i
        return this.AD[i];
    }
    public byte getTS(int i){    //retorna o byte da posição i
        return this.TS[i];
    }   
    public byte getER(){    //retorna o byte da posição i
        return this.ER;
    }   
    public byte getEND(){
        return this.END;
    }

    public void setER(int d){       
        this.ER=(byte)d;
    }
    public void setAD(byte a, byte b){
        this.AD[0]=a;
        this.AD[1]=b;
    }
    public void setTS(byte a, byte b, byte c, byte d){
        this.TS[0]=a;
        this.TS[1]=b;
        this.TS[2]=c;
        this.TS[3]=d;   
        /*System.out.print("setTS : ");
        System.out.print(TS[0]);
        System.out.print(" ; ");
        System.out.print(TS[1]);
        System.out.print(" ; ");
        System.out.print(TS[2]);
        System.out.print(" ; ");
        System.out.println(TS[3]);*/
    }   
    
    
    /*public void setTS(int t){
        this.TS=ByteBuffer.allocate(4).putInt(t).array();       
    }
    
    public void setPM(int d){
        this.PM=ByteBuffer.allocate(4).putInt(d).array();       
    }

    public void setPA(int d){
        this.PA=ByteBuffer.allocate(4).putInt(d).array();   
    }

    public void setNS(int d){       
        this.NS=(byte)d;
    }*/
    
    public byte[] construir(byte AD0, byte AD1){
        setAD(AD0, AD1);
        System.out.println("\n*********   Construir Error   *********");
        /*pode se alterar para outro valor. Está feito com 5 amostras*/
        
        byte trama[]= new byte[9];         //TP:0      AD:1-2      TS:3-6      ER:7
        
        trama[0]=this.getTP();
        System.out.print("Tipo : ");
        System.out.print(unsignedByteToInt(trama[0]));
        
        System.out.print(";\nAD : ");
        for(int i=1; i<3; i++){
            trama[i]=this.getAD(i-1);
            System.out.print(unsignedByteToInt(trama[i]));
            System.out.print(" ; ");
        }
        System.out.println("");
        
        System.out.print("TS : ");
        for(int i=3; i<7; i++){
            trama[i]=this.getTS(i-3);
            System.out.print(unsignedByteToInt(trama[i]));
            System.out.print(" ; ");
        }
        System.out.println("");
        
        System.out.print("ER : ");
        QualErro();
        trama[7]=this.getER();
        System.out.print(unsignedByteToInt(trama[7]));
        System.out.println(" ; ");
        
        trama[8]=this.getEND();
        System.out.print("END : ");
        System.out.println(unsignedByteToInt(trama[8]));
        
        System.out.println("\nTRAMA ERROR");
        
        for(int i=0; i<9;i++){
            System.out.println(unsignedByteToInt(trama[i]));
        }
        
        return trama;
    }
    
    public int unsignedByteToInt(byte b) {
       return (int) b & 0xFF;
    }
    
    /*terminar QualErro*/
    public int QualErro(){
        setER(1);
        return 1;
    }
}
