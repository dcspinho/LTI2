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
import java.text.DecimalFormat;
import java.util.TreeMap;

public class estatist {
    
    long time;
    boolean fileCria=false;
    st s;
    boolean novo;
    
    
    public estatist(){
        s=new st();
        novo=false;
    }
    
        
    public synchronized boolean setnovo(boolean editar, boolean b){
           
        if(editar==true){
            novo=b;
            return false;
        }
        else{
            if(novo==true){
                novo=false;
            return true;
            } 
            return false;
        }

    }  
    

    
    public boolean getFILECRIA(){
        return fileCria;
    }
    
    public void setFILECRIA(boolean b){
        fileCria=b;
    }

    
    public long getTime(){
        return time;
    }
    

    public void addFich(Long t, TreeMap<Integer, Double> lista){
            DecimalFormat df_ener = new DecimalFormat("###,#####0.00000");
            File arquivo = new File("ESTAT.csv");
            
            try {
                String ss ="";
                
                String str[]=new String[(lista.size()*2)+1];
                
                str[0]= String.valueOf(t);
                //System.out.println("-->"+str[0]);
                int y=0;
                double val=0;
                
                ss +=str[0]; 
                ss +=";";
                for(Integer ii:lista.keySet()){
                    str[y+1]=String.valueOf(ii);
                    ss +=str[y+1];
                    ss +=";";
                    str[y+2]=String.valueOf(df_ener.format(lista.get(ii)));
                    ss +=str[y+2];
                    str[y+2]=str[y+2].replaceAll(",", ".");
                    val=val+Double.parseDouble(str[y+2]);
                    ss +=";";
                    y++;
                }
                
                synchronized(s){
                    s.setS(str[0],val);
                    setnovo(true, true);
                }
                
                ss +="\n";
                
                //System.out.println("linha do file:");
                //System.out.println(ss);
                
                //escreve no arquivo
                FileWriter fw = new FileWriter(arquivo, true);
                BufferedWriter bw = new BufferedWriter(fw);
                
                               
                bw.append(ss);
               

                bw.close();
                fw.close();
                
            } catch (IOException ex) {
               ex.printStackTrace();
            }

    }
        
    
    
    
        

    public void gerarFich() throws IOException{
            
            //time=t;
            
            //String nome=String.valueOf(time);    
           
            File arquivo = new File("ESTAT.csv");
            
            try {
                if (!arquivo.exists()) {     
                    //cria um arquivo (vazio)
                    arquivo.createNewFile();
                }
                
           } catch (IOException ex) {
               ex.printStackTrace();
           }

        }
    
    
    
}
