/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class fich {
 
        String nomeFil;
   
      
        //lista de amostras
        ArrayList<record> lista;

        
        
        public fich(String nome, int n){
            this.nomeFil="DATA_C"+n+"_"+nome+".csv";
            this.lista= new ArrayList<record>(); 
        
        }
        
        public ArrayList<record> retornaListaREc(){
            return lista;
        } 
        
        
        public String getNome(){
            return this.nomeFil;
        }
        
        public void setNome(String s){
            this.nomeFil=s;
        }
        
        
    
        public void add(record e) throws IOException
        {
             this.lista.add(e);   
        }
            
        
        public void gerarFich() throws IOException{
       
            File diretorio = new File("DATAS");
            String s = diretorio.getAbsolutePath();
            //System.out.println("diretoria: "+s);
            
            
            File arquivo = new File(s,getNome());
            
            try {
                if (!arquivo.exists()) {     
                    //cria um arquivo (vazio)
                    arquivo.createNewFile();
                }
                BufferedWriter ArquivoTXT = null;

                //caso seja um diretório, é possível listar seus arquivos e diretórios
                //File[] arquivos = arquivo.listFiles();
                
                //escreve no arquivo
                FileWriter fw = new FileWriter(arquivo, true);
                BufferedWriter bw = new BufferedWriter(fw);
                
                                
                //percorrer todas as amostras
                for(record a: lista){
                    bw.write(a.escString());
                }
               
                //System.out.print("\n\n\n Ficheiro criado! Nome: "+ getNome() +" \n\n");
                
                bw.close();
                fw.close();
                
           } catch (IOException ex) {
               ex.printStackTrace();
           }

        }
        
}
