
package central;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class server {
    
    int port;
    ArrayList<edificio> gestores= new ArrayList<edificio>();
    Connection con;
    
    
    public server(String s, Connection c){
        port=Integer.parseInt(s);
        con=c;
    }
    
    public synchronized ArrayList<edificio> acedeLista(){
        return gestores;
    }
    
    public synchronized void addLista(edificio c){
        gestores.add(c);
    }

    
    public void iniciaServer() throws IOException {
        

        int i=1;
        ServerSocket ss = new ServerSocket(port);
        
        do{
            Socket cs = ss.accept();
            
            edificio e= new edificio(con);
            Thread t=(new Thread(new server.falarCliente(cs, e)));
            t.start();
            System.out.println("Novo gestor!");
            
        }while(i==1);
    }
    
    
    public class falarCliente implements Runnable{
        
        Socket cs;
        PrintWriter out;
        BufferedReader in;
        int cod=0;
        edificio e;
        
        public falarCliente(Socket c, edificio ee){
            cs=c; 
            e=ee;
        }
        
        public void run ()
            {    

                try {
                    out=new PrintWriter (cs.getOutputStream(), true);
                    in = new BufferedReader (new InputStreamReader(cs.getInputStream()));
                }catch (IOException ex) {
                    Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
                }

                String current;

                try {
                    while (((current = in.readLine()) != null)&&(current.equals("")==false)){
                        
                        if(cod==0){
                            cod= Integer.parseInt(current);
                            e.setCOD(cod);
                            addLista(e);
                            DataBase t=new DataBase();
                            System.out.println("cod_edificio = "+cod);
                            double prec=t.ver_existe_edificio(con, cod);
                            System.out.println("preco_edificio = "+prec);
                                
                            
                 
                            
                            if(prec!=0){
                                out.println(prec);
                            }
                            else{
                                out.println("");
                                current="";
                            }
                                                        
                        }
                        else{
                             e.addVal(current);
                        }

                    }

                    in.close();
                    out.close();
                    cs.close();


                } catch (IOException ex) {
                    System.out.println("Gestor desconectado!");
            } catch (SQLException ex) {
                System.out.println("Gestor desconectado!");
            } catch (ClassNotFoundException ex) {
                System.out.println("Gestor desconectado!");
            }

            }
    }
    
    
 }

