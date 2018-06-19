
package concentrador;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class arranque {
    
   
    
    public static void main(String[] args) throws IOException, InterruptedException{        
            
            Date date = new Date();

            log g=new log();
            
            
            String[] begin;
        
            //variaveis
            String com;
            int baud;
            int PA;    //em mili
            int PM;    //em mili
            int NS;
            int comSockets;
            String ip;
            int comGestor;
            int comConc;
            
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String stringDate = sdf.format(date);
            
            g.atualiza("---------------------INICIO---------------------");
            g.atualiza("Arranque:");
            g.atualiza(String.valueOf(stringDate));
            
            
            //ler ficheiro config
            begin=conf(g);
            
           
            com=begin[0];
            baud=Integer.parseInt(begin[1]);
            PA=Integer.parseInt(begin[2]);
            PM=Integer.parseInt(begin[3]);
            NS=Integer.parseInt(begin[4]);            
            comSockets=Integer.parseInt(begin[5]);
            ip=begin[6];
            comGestor=Integer.parseInt(begin[7]);           
            comConc=Integer.parseInt(begin[8]);
            //por o PA,PM e NS
            //cria trama start
            start sta=new start();
            sta.setPM(PM);
            sta.setPA(PA);
            sta.setNS(NS);
                        
            comunicacao c= new comunicacao(sta);
            c.inicio(com,g, baud, comSockets, ip, comGestor, comConc);
            

            
    }
     
    
    //CONF feita no txt
    public static String[] conf(log g) throws IOException{    
            String arquivoCSV = "conf.txt";
            BufferedReader br = null;
            String linha = "";
            String csvDivisor = ";";
            String[] begin= new String[8];
            
            try {
                br = new BufferedReader(new FileReader(arquivoCSV));
                               
                while ((linha = br.readLine()) != null) {
                    begin = linha.split(csvDivisor); 
                }
                g.atualiza("[COM:" + begin[0] +" , Baud-rate:" + begin[1] + " , PA:" + Integer.parseInt(begin[2]) +" microssegundos"+ " , PM:" + Integer.parseInt(begin[3]) +" milissegundos"+ " , NS:" + begin[4]+  " , PORTA_SOCKETS_SENSOR:" + Integer.parseInt(begin[5]) + " , IP_GESTOR:" + begin[6] + " , PORTA_SOCKETS_GESTOR" + Integer.parseInt(begin[7])+ " , PORTA_SOCKETS_CON" + Integer.parseInt(begin[8])+"]");

            } catch (FileNotFoundException e) {
                g.atualiza("Erro: falha na leitura do ficheiro de conf!");
            } catch (IOException e) {
                g.atualiza("Erro: falha na leitura do ficheiro de conf!");
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                            g.atualiza("Erro: falha na leitura do ficheiro de conf!");
                    }
                }
            }
        return begin;
    }

}

