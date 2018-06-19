

package central;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class arranque implements Runnable{
    
    Connection con;
    
    public arranque() throws ClassNotFoundException, SQLException, ParseException{
        DataBase t=new DataBase();
        con=t.getConexaoMySQL();
    }
    public void run(){
    
       
        String[] begin;
        try {
            begin = conf();
            server s=new server(begin[0], con);
            s.iniciaServer();
        } catch (IOException ex) {
            Logger.getLogger(arranque.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    //CONF feita no txt
    public String[] conf() throws IOException{    
                String arquivoCSV = "conf.txt";
                BufferedReader br = null;
                String linha = "";
                String csvDivisor = ";";
                String[] begin= new String[1];

                try {
                    br = new BufferedReader(new FileReader(arquivoCSV));

                    while ((linha = br.readLine()) != null) {
                        begin = linha.split(csvDivisor); 
                    }
                    //g.atualiza("[COM:" + begin[0] +" , Baud-rate:" + begin[1] + " , PA:" + Integer.parseInt(begin[2]) +" microssegundos"+ " , PM:" + Integer.parseInt(begin[3]) +" milissegundos"+ " , NS:" + begin[4]+  " , Porta_sockets:" + Integer.parseInt(begin[5]) + " , IP:" + begin[6] + "]");

                } catch (FileNotFoundException e) {
                    //g.atualiza("Erro: falha na leitura do ficheiro de conf!");
                } catch (IOException e) {
                    //g.atualiza("Erro: falha na leitura do ficheiro de conf!");
                } finally {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                               //g.atualiza("Erro: falha na leitura do ficheiro de conf!");
                        }
                    }
                }
            return begin;
    }
    
}
