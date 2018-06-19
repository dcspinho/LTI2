
package sensor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class trama_Data {
    Calendar calendar;
    Date date;
    byte TP;           //8 bits
    byte AD[]=new byte[2];           //16 bits
    byte TS[]=new byte[4];          //32 bits
    byte PM[]=new byte[4];          //32 bits
    byte PA[]=new byte[4];          //32 bits
    byte NS;          //8 bits
    byte amostras[]=new byte[getNSint()*2];    //16 bits
    byte END;
    
    
    long millis;
    
    public trama_Data(){
        TP=(byte)10;
        calendar=Calendar.getInstance();
        date=calendar.getTime();
        millis=calendar.getTimeInMillis();
        END=(byte)255;
    }
    
    public long getMillis(){
        return this.millis;
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
    public long getTSint(){    
        return ByteBuffer.wrap(this.TS).getInt();
    }   
    public byte getPM(int i){    //retorna o byte da posição i
        return this.PM[i];
    }      
    public int getPMint(){    
        return ByteBuffer.wrap(this.PM).getInt();
    } 
    public byte getPA(int i){    //retorna o byte da posição i
        return this.PA[i];
    }   
    public int getPAint(){    
        return ByteBuffer.wrap(this.PA).getInt();
    }
    public byte getNS(){
        return this.NS;
    }
    public int getNSint(){
        return unsignedByteToInt(this.NS);
    }
    public byte getEND(){
        return this.END;
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
    }
    public void setTSint(int t){
        this.TS=ByteBuffer.allocate(4).putInt(t).array();  
    }     
   

    public void setPM(byte a, byte b, byte c, byte d){
        this.PM[0]=a;
        this.PM[1]=b;
        this.PM[2]=c;
        this.PM[3]=d;  
    }  
    public void setPM(int d){
        this.PM=ByteBuffer.allocate(4).putInt(d).array();       
    }
    public void setPA(byte a, byte b, byte c, byte d){
        this.PA[0]=a;
        this.PA[1]=b;
        this.PA[2]=c;
        this.PA[3]=d;   
    }   
    public void setPA(int d){
        this.PA=ByteBuffer.allocate(4).putInt(d).array();   
    }
    public void setNS(byte a){
        this.NS=a;
    }   
    
    public byte[] construir(long millis, byte AD0, byte AD1, int comecar/*, int PA*/) throws FileNotFoundException, IOException{
        //System.out.println("\n*********   Construir Data   *********");
        /*setPA(PA);*/
        setAD(AD0, AD1);
        
        byte trama[]= new byte[17+getNSint()*2];         //TP:0      AD:1-2      TS:3-6      PM:7-10     PA:11-14        NS:15       5amostras(15+NS*2):16-25

        byte recebido[]=new byte[this.getNSint()*2];
        recebido=GerarAmostras(millis, comecar);
        
        for(int i=0; i<this.getNSint()*2; i++){
            trama[i+16]=recebido[i];  
        }
        
        trama[0]=this.getTP();
        for(int i=1; i<3; i++){
            trama[i]=this.getAD(i-1);
        }
        
        for(int i=3; i<7; i++){
            trama[i]=this.getTS(i-3);
        }
        for(int i=7; i<11; i++){
            trama[i]=this.getPM(i-7);
        }
        
        for(int i=11; i<15; i++){
            trama[i]=this.getPA(i-11);
        }
        
        trama[15]=this.getNS();
        
        trama[16+getNSint()*2]=this.getEND();
        
        /*System.out.println("\n\n***********   TRAMA DATA   **********");
        for(int i=0; i<trama.length; i++){
            System.out.println(Integer.toBinaryString(trama[i] & 255 | 256).substring(1) + "  ______  " + unsignedByteToInt(trama[i]));
            
        }*/
 
        //System.out.println("PA : "+this.getPAint());
        return trama;
    }
    
    public byte[] GerarAmostras(long antigo, int comecar) throws FileNotFoundException, IOException{
        //System.out.println("\n\n***********   GerarAmostras   **********");
        byte amostras[]=new byte[this.getNSint()*2];
        

        long valorTS=this.getTSint();
        if(comecar==0){          /*primeira trama manda o valor correto*/
            millis=System.currentTimeMillis();
            millis=millis-antigo;
            valorTS=valorTS+millis;
        }
        else{                           /*comecar tem o valor do TS da trama anterior*/
            valorTS=comecar+this.getPMint();
        }
        
        setTSint((int)valorTS);
        amostras=ler_Ficheiro();

        return amostras;
    }
    
       
    /*este método vai ler do ficheiro txt e "calcular" aleatoriamente os valores da amostra*/
    public byte[] ler_Ficheiro() throws FileNotFoundException, IOException{ 
        FileInputStream file = new FileInputStream("GerarAmostras.txt");
        int converter;
        BufferedReader br = new BufferedReader(new FileReader("GerarAmostras.txt"));
        String linha;
        int nr_linhas=1;
        int available=file.available();
        for(int i=0; i<available; i++){
            if(file.read()==13){
                nr_linhas++;
            }
        }
        
        ArrayList<Integer> amostra_lida=new ArrayList();
        //tem as amostras em bytes (2 bytes cada amostra)
        byte amostras[]= new byte[nr_linhas*2];
        byte lido[]= new byte[4];
        /*retornar é o que vai ser returnado. array com as NS*2 amostras*/
        byte retornar[]= new byte[this.getNSint()*2];
        try{
            for(int i=0; i<nr_linhas; i++){
                linha = br.readLine();
                if(linha!=null){
                    converter=Integer.parseInt(linha);
                    amostra_lida.add(converter);
                }
                else{
                    i=available;
                }
                
            }
            int j=0;
            
            //System.out.println("Amostras ficheiro");
            Iterator it = amostra_lida.iterator();
            while (it.hasNext()) {
                converter=(int)it.next();
                lido=ByteBuffer.allocate(4).putInt(converter).array();    
                amostras[j]=lido[2];
                j++;           
                amostras[j]=lido[3];
                j++;
            }

            int aleatorio;
            
            aleatorio=(int)(Math.random() * (100));
            //this.setPA(350);
            
            if(aleatorio%2!=0){ /*impar*/
                aleatorio++;
            }

            if(this.getPAint()<=200){                
                retornar=Avancar(1, amostras, aleatorio);                
            }
            else if(this.getPAint()<=250){ /*125*/
                retornar=Avancar(2, amostras, aleatorio);
            }
            else if(this.getPAint()<=300){
                retornar=Avancar(3, amostras, aleatorio);
            }
            else if(this.getPAint()<=350){
                retornar=Avancar(4, amostras, aleatorio);
            }
            else if(this.getPAint()<=400){
                retornar=Avancar(5, amostras, aleatorio);
            }
            else if(this.getPAint()<=450){
                retornar=Avancar(10, amostras, aleatorio);
            }
            else if(this.getPAint()<=500){
                retornar=Avancar(15, amostras, aleatorio);
            }
            else if(this.getPAint()<=550){
                retornar=Avancar(20, amostras, aleatorio);
            }
            else if(this.getPAint()>550){
                retornar=Avancar(30, amostras, aleatorio);
            }
         
            //NAO ELIMINAR A LINHA DE BAIXO
            //ByteToDecimal(retornar);
            
   
        }catch(FileNotFoundException e){
            System.out.println("Ficheiro não encontrado");
        }    
        return retornar;
        
    } 
    
    public int unsignedByteToInt(byte b) {
       return (int) b & 0xFF;
    }

    public byte[] Avancar(int avancar, byte[] amostras, int aleatorio){
        byte retornar[]= new byte[this.getNSint()*2];
        for(int i=aleatorio; i<(this.getNSint()*2+aleatorio);i++){
            retornar[i-aleatorio]=amostras[(i-aleatorio)*avancar+aleatorio];   
            retornar[i+1-aleatorio]=amostras[(i-aleatorio)*avancar+1+aleatorio];
            i++;
        }
        return retornar;
    }
   
    public  void ByteToDecimal(byte[] retornar){
        ArrayList<Integer> imprimir=new ArrayList();
        int k=0;
            String s1,s2;
            int p=0;
            for(int i=0; i<this.getNSint()*2;i++){  
                s1=null;
                s2=null;
                k=unsignedByteToInt(retornar[i+1]);
                s2=Integer.toBinaryString(k);
                if(s2.length()<8){
                    int conta=8-s2.length();
                    for(int y=0; y<conta; y++)
                        s2="0".concat(s2);
                }
                
                k=unsignedByteToInt(retornar[i]);
                s1=Integer.toBinaryString(k);                
                i++;
                
                s1=s1.concat(s2);
                p=Integer.parseInt(s1,2);
                imprimir.add(p);
            }
            System.out.println("IMPRIMIR");
            Iterator it = imprimir.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
    }
            
    
}