
package concentrador;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

public class fich {
 
        String nomeFil;
        int st=0;
   
        
        
        public int getST(){
            return this.st;
        }
        
        public void setST(int s){
            this.st=s;
        }
        int tamanho;
        
        
        //lista de amostras
        ArrayList<amostra> lista;
        
        
        
        public fich(String nome){          
            this.nomeFil=nome;
            this.lista= new ArrayList<amostra>(); 
        }

        /*public void addFiles(String nome) throws IOException{
            
        }*/
        

        public String getNome(){
            return this.nomeFil;
        }
        
        public void setNome(String s){
            this.nomeFil=s;
        }
        
        
    
        public void add(amostra e) throws IOException
        {
             this.lista.add(e);
             
             if(lista.size()==1000){
                 String nome;
                 amostra a= lista.get(0); 
                 nome="amostras_"+a.getTIME()+".csv";
                 this.setNome(nome);
                 gerarFich();
                 
                 synchronized(comm.nome_files){
                    comm.nome_files.add(nome);
                    //System.out.println("Adicionado na lista");
                 }

                 this.lista=new ArrayList<amostra>();
                 //System.out.println("ficheiro criado!! :" + nome_files.size());      
             }
             
             
        }
            
        
        public void gerarFich() throws IOException{
           
            //System.out.println("ficheiro criado!! :" + getNome());            
            File arquivo = new File(getNome());
            try {
                if (!arquivo.exists()) {     
                    //cria um arquivo (vazio)
                    arquivo.createNewFile();
                }
                
                //caso seja um diretório, é possível listar seus arquivos e diretórios
                File[] arquivos = arquivo.listFiles();
                
                //escreve no arquivo
                FileWriter fw = new FileWriter(arquivo, true);
                BufferedWriter bw = new BufferedWriter(fw);
                
                
                //percorrer todas as amostras
                for(amostra a: lista){
                    bw.write(a.escString());
                }
               
                bw.close();
                fw.close();
                
           } catch (IOException ex) {
               ex.printStackTrace();
           }

        }
        
        
        public void fimprog() throws IOException{
            if(lista.size()==0){
            
            }
            else{
                String nome;
                amostra a= lista.get(0); 
                nome="amostras_"+a.getTIME()+".csv";
                this.setNome(nome);
                synchronized(comm.nome_files){
                    comm.nome_files.add(nome);
                }
                gerarFich();
                //System.out.println("ficheiro criado!! :" + comm.nome_files.size());    
            }
            
        }
        
        
        
        

}
