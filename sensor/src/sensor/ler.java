
package sensor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ler {
    String current;
    int comecar=0;
    sensor s;
    Random gerador = new Random();
    byte cod_ard[];

    int esp;

    int comSockets;
    String ip;

    int x=0;
    int begin=0;

    DatagramSocket ss;
    InetAddress serv;

    Thread ler, escrever, esperar;

    int enviaData=0;
    int pm;
    int timestamp;

    byte acabar[];
    byte start[]=new byte[14];
    byte stop[]=new byte[2];
    byte data[];
    int ns;



    public ler(int c,  String i, byte[] cc ){
        comSockets=c;
        ip=i;
        cod_ard=cc;
    }


    public void terminar() throws IOException{
        ss.close();
        System.exit(0);
    }

     public void iniciar() throws IOException{

        ss= new DatagramSocket();

        //ip do servidor
        serv = InetAddress.getByName(ip);

        byte[] m = new byte[2];
        m[0]=cod_ard[0];
        m[1]=cod_ard[1];
        //m[0]= (byte)0;
        DatagramPacket req = new DatagramPacket(m, m.length , serv, comSockets);
        //envia datagrama contendo a mensagem m
        ss.send(req);

        /*for (byte b : m) {
            System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1) + "  ______  " + unsignedByteToInt(b));
        }*/


    }



    public void comecar() throws IOException, InterruptedException{

        s=new sensor(cod_ard[0], cod_ard[1]);


        int l=0;

        iniciar();

        do{
            esp=gerador.nextInt(300);
            DatagramPacket resp = new DatagramPacket(start, start.length);
            //ss.setSoTimeout(10000); // timeout em ms
            System.out.println("\n esperando start...");
            // recebe resposta do servidor – fica em wait ateh chegada
            ss.receive(resp);

            Thread.sleep(esp);

            byte b= (byte)start[13];
            ns= unsignedByteToInt(b);

            byte p[]= new byte[4];
            p[0]=start[5];
            p[1]=start[6];
            p[2]=start[7];
            p[3]=start[8];
            pm=ByteBuffer.wrap(p).getInt();
            //System.out.println("\n PM:"+pm);

            //System.out.println("Tamanho start: "+ start.length);
            System.out.println("\n Start recebido");



            /*for (byte bb : start) {
                System.out.println(Integer.toBinaryString(bb & 255 | 256).substring(1) + "  ______  " + unsignedByteToInt(bb));
            }*/



            escrever=(new Thread(new EscreverServidor()));
            escrever.start();



            DatagramPacket respp = new DatagramPacket(stop, stop.length);
            //ss.setSoTimeout(10000); // timeout em ms

            // recebe resposta do servidor – fica em wait ateh chegada
            ss.receive(respp);
            System.out.println("\n Stop recebido");


            /*for (byte bb : stop) {
                System.out.println(Integer.toBinaryString(bb & 255 | 256).substring(1) + "  ______  " + unsignedByteToInt(bb));
            }*/

            escrever.stop();
            //terminar();
            x=1;
            begin=0;

            if(stop[1]==0){
                terminar();
                //l=1;
            }


        }while(l==0);


    }





    public class EscreverServidor implements Runnable{
         int j=0;

         @Override
         public void run ()
         {
             do{
                           //thread que espera pm
                           esperar=(new Thread(new EsperaPM()));
                           esperar.start();


                           if(begin==0){
                                s.setComecar(0);
                                //System.out.println(" entrou: "+timestamp);
                                begin=1;
                           }

                            try {
                                //System.out.println("\n A construir data....");
                                //construir data
                                data=s.novo(start);

                                 /*for (byte b : data) {
                                    System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1) + "  ______  " + unsignedByteToInt(b));
                                 }*/


                            } catch (IOException ex) {
                                Logger.getLogger(ler.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            //envia data
                            DatagramPacket resp = new DatagramPacket(data,  data.length, serv, comSockets);
                            try {

                                ss.send(resp);
                                x=0;

                            } catch (IOException ex) {
                                Logger.getLogger(ler.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            byte pp[]= new byte[4];
                            pp[0]=data[3];
                            pp[1]=data[4];
                            pp[2]=data[5];
                            pp[3]=data[6];
                            timestamp=ByteBuffer.wrap(pp).getInt();
                            //System.out.println(" Timestamp data: "+timestamp);
                            s.setComecar(timestamp);
                            System.out.println("\n -------------> Data Enviada");
                           // System.out.println("\n timestamp anterior:"+ s.getComecar());

                            //esperar q passe o pm
                            try {
                                esperar.join();
                            } catch (InterruptedException ex) {
                                Logger.getLogger(ler.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            esperar.stop();

             }while(j==0);

         }
    }

   public class EsperaPM implements Runnable{

         @Override
         public void run ()
         {
             try {
                 Thread.sleep((long)pm);
             } catch (InterruptedException ex) {
                 Logger.getLogger(ler.class.getName()).log(Level.SEVERE, null, ex);
             }

         }

    }



    public int unsignedByteToInt(byte b) {
                return (int) b & 0xFF;
    }
}
