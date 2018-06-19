
package concentrador;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class comunicacao {
     
    start sta;
    comm co;
    hello h=new hello();
    
    Thread gestor, hello, espera, mandaDatas;
    log g; 
    
    String ip;
    InetAddress serv; 
    int comGestor;
    DatagramSocket s;
    int comConc;  
    
    public comunicacao(start s){
        sta=s;
    }
    
    
    public void inicio(String com, log g, int baud, int comSockets, String i, int c, int comCon) throws IOException, InterruptedException{
        ip=i;
        comGestor=c;
        comConc=comCon;
        
        s= new DatagramSocket(comConc); 
        
        try {
            
            serv = InetAddress.getByName(ip);  
                
        } catch (UnknownHostException ex) {
            Logger.getLogger(comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //comunicacao com sensores
        co= new comm(sta, g);
        
        
        //comunicacao com gestor
        
        gestor=(new Thread(new comunicacao.comecaGestor()));
        gestor.start();

        //criar threads para o gestor
        hello=(new Thread(new comunicacao.mandarHello()));
        hello.start();
        
        if(com.equals("0")==false){
                co.conetarPorta(com);                
        }
        else{
            //System.out.println("Sem sensor real");
            baud=0;
        } 
        co.iniciaEsc(comSockets, ip, baud);
        
            
    }
    
    public class mandaDatas implements Runnable 
    {        
            int i=0;
            ArrayList<byte[]> lista;
            int freq;
                
            public mandaDatas(ArrayList<byte[]> list, int f){
                lista=list;
                freq=f;
            }
            
            @Override
            public void run (){
                do{
                
                    try {
                        co.datas(lista, freq, serv, comGestor,s);
                    } catch (IOException ex) {
                        Logger.getLogger(comunicacao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(comunicacao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }while(i==0);
                
                
            }
            
    }
    
    
    public class comecaGestor implements Runnable 
    {        
            int i=0;

          
            @Override
            public void run (){
                do{
                    

                    byte[] tramaGeral= new byte[300]; 
                    //cria datagrama para receber request
                    DatagramPacket  r = new DatagramPacket(tramaGeral, tramaGeral.length);

                    try {
                        
                        s.receive(r);
                        
                        //recebi START                        
                        
                        //System.out.println("ouviiiiiiiii start do gestor!!!!!!");
                        
                        
                        byte[] numm=new byte[4];
                        numm[2]=tramaGeral[9];
                        numm[3]=tramaGeral[10];       
                        int num=java.nio.ByteBuffer.wrap(numm).getInt();  
                        
                        
                        //System.out.println("num " + num);
                        

                        byte [] recebido=new byte[11+(2*num)];
                        System.arraycopy(tramaGeral, 0, recebido, 0, recebido.length);
                        
                        /*for (byte b : recebido) {
                            System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1) + "  ______  " + unsignedByteToInt(b));    
                        }*/
                        
                        ArrayList<byte[]> listaaa=new ArrayList<byte[]>();
                        byte tempp[]=new byte[2];
                        int j=0;                            
                        
                        
                        //codigos
                        for(int i=11; i<11+(2*num);i++){
                            tempp[0]=recebido[i];
                            i++;
                            tempp[1]=recebido[i];
                            listaaa.add(tempp); 
                            tempp=new byte[2];
                        } 
                         
                        /*System.out.println("");
                        for (byte[] b : listaaa) {
                            System.out.println(Integer.toBinaryString(b[0] & 255 | 256).substring(1) + "  ______  " + unsignedByteToInt(b[0]));
                            System.out.println(Integer.toBinaryString(b[1] & 255 | 256).substring(1) + "  ______  " + unsignedByteToInt(b[1]));
                        }*/
                        
                        int freq=tramaGeral[8];

                        
                        //se ja recebi o start, vou mandar datas
                        mandaDatas=(new Thread(new comunicacao.mandaDatas(listaaa,freq)));
                        mandaDatas.start();
                        //mandaDatas.join();
                        
                        //System.out.println("a esperar stop");
                        //tenho q por na outra classe
                        tramaGeral= new byte[300]; 
                        //cria datagrama para receber request do cliente
                        r = new DatagramPacket(tramaGeral, tramaGeral.length);
                        s.receive(r);
                        
                        //System.out.println("ouviiiiiiiii stop do gestor!!!!!!");
                        
                        
                        mandaDatas.stop();                        
                        
                    } catch (IOException ex) {
                        Logger.getLogger(comunicacao.class.getName()).log(Level.SEVERE, null, ex);
                    } /*catch (InterruptedException ex) {
                        Logger.getLogger(comunicacao.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
                    
                }while(i==0);      
                
            }
    }
    
    
    public class mandarHello implements Runnable 
    {          
        boolean i=true;
            
            @Override
            public void run (){
                    
                do{
                    //iniciar temporizador
                    espera=(new Thread(new comunicacao.esperar()));
                    espera.start();
                    
                    byte hell[];
                    
                    
                        synchronized(h){    
                            hell=h.constroiHello(co.cod_sensores);
                        }
                        
                            
                        DatagramPacket resp = new DatagramPacket(hell, hell.length, serv ,comGestor);
                        
                        try {

                            //espera que o temporizador terminar para enviar o hello
                            espera.join();

                            s.send(resp);
                            espera.stop();

                        } catch (IOException ex) {
                            Logger.getLogger(comunicacao.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
                        Logger.getLogger(comunicacao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                    //System.out.println("\n Datagrama enviada");
            }while(i==true);
                
            }
    }
    
     public class esperar implements Runnable 
    {           
            @Override
            public void run (){
                    
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                        Logger.getLogger(comunicacao.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
    }

    
    public int unsignedByteToInt(byte b) {
                return (int) b & 0xFF;
    }
            
}
