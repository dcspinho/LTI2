
package gestor;

import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;


public class sensor {
  
        byte[] cod;
        int cod_sens;
        int area;
        String designacao;                               
        ConexaoMySQL conex;
        
        ArrayList<record> lista=new ArrayList<record>();
        double consAcumu;
        double Energ_ultimo_PM;
        
        
        public sensor(byte[] codi, ConexaoMySQL cc ){
            cod=codi;
            byte[] codd=new byte[4];
            codd[0]=0;
            codd[1]=0;
            codd[2]=cod[0];
            codd[3]=cod[1];
            cod_sens= ByteBuffer.wrap(codd).getInt();
            conex=cc;
            area=conex.area(cod_sens);
            designacao=conex.designacao(area);
            consAcumu=0;
            Energ_ultimo_PM=0;
        }
        
        
        public synchronized double acede_ConsAcum(boolean altera,double valor){
            if(altera==true){
                 consAcumu=valor;
            }
            return consAcumu; 
        }
        
        public synchronized double acede_ultimo_PM(boolean altera,double valor){
            if(altera==true){
                 Energ_ultimo_PM=valor;
            }
            return Energ_ultimo_PM; 
        }
        

        public ArrayList<record> getListaRec(){
            return lista;
        }
        public void setListaRec( ArrayList<record> l){
            lista=l;
        }
        
        
        public synchronized void addLista(record e){
            lista.add(e);
        }
        
        public int getCod_sens(){
            return cod_sens;
        }
        
        public int getArea(){
            return area;
        }
        
        public String getDesignacao(){
            return designacao;
        }
        

        
           
        public void valorMedioCons(){
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss.SS");  
            GregorianCalendar calendar=new GregorianCalendar();
            DecimalFormat df = new DecimalFormat("###,##0.00");
            
            DecimalFormat df_ener = new DecimalFormat("###,#####0.00000");
            String dataReal;
            
            
            ArrayList<record> lis=lista;
            
            boolean prim=true;
            
            
            int inicio=0;
            int parar;
            double som=0;
            double mult=0;
            double valCorrRMS=0;
            double potencia=0;
            long tempoI;
            long tempoF;
            double energia;
            double energia_kWh=0;
            int tamanho;
            double valCorr[];
            for(record r:lis){
                if(prim==true){
                    prim=false;
                }
                else if((r.getNR()==1)||(lis.indexOf(r)==(lis.size()-1))){  //até aqui é um pm
                    
                    if(lis.indexOf(r)==(lis.size()-1)){
                        parar=lis.indexOf(r)+1;
                    }
                    else{
                        parar=lis.indexOf(r);
                    }
                    tamanho=parar-inicio;
                    
                    valCorr=new double[tamanho];
                    
                    //System.out.println("\ntamanho do coiso: "+(tamanho));
                    //System.out.println("inicio: "+inicio);
                    //System.out.println("parar: "+parar);
                    
                    //percorrer os dados do PM
                    int j=0;
                    for(int i=inicio; i<parar-1;i++){
                        valCorr[j]=((((((double)lis.get(i).valor_amostra)*5)/1023)-2.5)/2200)*1000;
                        som=som+Math.pow(valCorr[j], 2); 
                        j++;
                    }
                    
                    //System.out.println("som= "+j);
                    
                    //System.out.println("j= "+j);
                    
                    mult=(1/(double)(tamanho))*som;
                    valCorrRMS=Math.sqrt(mult);
                    //System.out.println("ValRMS= "+valCorrRMS);
                    
                    
                    potencia=valCorrRMS*230;
                    
                    
                    tempoI=lis.get(inicio).data;
                    tempoF=lis.get(parar-1).data;
                    //System.out.println("TempoI= "+tempoI);
                    //System.out.println("TempoF= "+tempoF);
                    //System.out.println("ValI= "+lis.get(inicio).valor_amostra);
                    //System.out.println("ValF= "+lis.get(parar-1).valor_amostra);
                    
                    //System.out.println("SENSOR "+cod_sens);           
                    //System.out.println("P= "+df.format(potencia)+" W");
                    
                    //System.out.println("intervalo_tempo em seg= "+(double)(tempoF-tempoI)/1000);
            
                    energia=potencia*((double)(tempoF-tempoI)/1000);
                    //System.out.println("Energia= "+df_ener.format(energia)+" J");
                    energia_kWh=energia/(3.6*Math.pow(10,6));
                    
                    
                    //System.out.println("Consumo= "+energia_kWh+" kWh");
                    acede_ConsAcum(true, consAcumu+energia_kWh);
                    //System.out.println("Consumo_acumulado no sensor= "+acede_ConsAcum(false, 0)+" kWh");
                    
                    inicio=parar;
                    som=0;
                    
                }
                
            }        
            
            if(energia_kWh==0){
            
            }else{
                acede_ultimo_PM(true, energia_kWh);
            }
            
        }
        
        
        
}
