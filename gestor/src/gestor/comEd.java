
package gestor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class comEd {
    
    
       Thread com;
       int port;
       String ip;
       int cod;
       estatist est;
       int i=0;
       double preco;
       
       
       public comEd(String i, String p, String c, estatist ee){
           ip=i;
           port=Integer.parseInt(p);
           cod=Integer.parseInt(c);
           est=ee;
           preco=0;
       }
       
       public synchronized double getPrec(){
           //System.out.println("ENTROU VER PREC");
           return preco;
       }
       
       public void setPrec(double p){
           preco=p;
           //System.out.println("ENTROU MUDAR PREC:" +preco);
       }
       
       public void iniciaCOM(){    
            com=(new Thread(new comunicacao()));
            com.start();
       } 
       
       
       class comunicacao implements Runnable 
        {     
            
                    @Override
                    public void run (){
                        PrintWriter out;
                        Socket cs=null;
                        
                        do{
                        try {
                            cs = new Socket(ip,port);
                            out = new PrintWriter (cs.getOutputStream(), true);
                            BufferedReader in = new BufferedReader (new InputStreamReader(cs.getInputStream()));
                            
                            //para a central me conhecer
                            out.println(cod);
                            String str=in.readLine();
                            System.out.println("Central: " + str);
                            
                            if(str.equals("")){
                                i=1;
                            }
                            else{
                                str=str.replaceAll(",", ".");
                                double prec = Double.parseDouble(str);
                                setPrec(prec);
                            }
                            

                            while(i==0){
                                if(est.setnovo(false, false)==true){
                                    synchronized(est.s){
                                       out.println(est.s.getS());
                                       System.out.println("-------------> ENVIADO PARA EDIFICIO");
                                    }
                                }
                                else{
                                    Thread.sleep(1000);
                                }
                            }

                            in.close();		 
                            out.close();
                            cs.close();

                        } catch (IOException ex) {
                            /*System.out.println("Falhou 1");*/
                        } catch (InterruptedException ex) {
                            /*System.out.println("Falhou 2");*/
                        }

                        }while(i==0);
                        
                        System.exit(0);
                        
                    }
                    
        }

}


