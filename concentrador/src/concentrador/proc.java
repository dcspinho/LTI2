
package concentrador;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;



    public class proc extends Thread
    {
         log g;
        int j=0;     
        byte[] datanovo;
        start sta;
        int nBytes;
        
        
        public proc(log gg, byte[] dat, int tam, start s)
        {
            this.g=gg;
            this.nBytes=tam;
            this.datanovo= new byte[tam];
            this.datanovo=dat;
            this.sta=s;
        }
        
        
        
        public void processa() throws IOException{                
                
 
                /*for (byte b : datanovo) {
                        System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1));
                }*/
                
                //ERROR
                if(datanovo[0]==(byte)15){
                        
                        //System.out.println("erro");
                        byte[] cod_ard=new byte[2];
                        byte[] timestamp=new byte[4];
                        byte[] tipo_err=new byte[1];
                        byte[] end=new byte[1];
                                                
                        //codi_ard 
                        int j=0;
                        for(int i=1;i<3;i++){
                            cod_ard[j]=datanovo[i];
                            j++;
                        } 

                        //System.out.println("");
                        /*System.out.println("Cod.Ard: ");         
                        for (byte b : cod_ard) {
                            System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1));
                        }*/


                        //timestamp
                        j=0;
                        for(int i=3;i<7;i++){
                            timestamp[j]=datanovo[i];
                            j++;
                        } 
                        /*System.out.println("");
                        System.out.println("Timestamp: ");         
                        for (byte b : timestamp) {
                            System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1));
                        }*/

                        int TimestampINT=ByteBuffer.wrap(timestamp).getInt(); 
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS"); 
                        GregorianCalendar calendar=new GregorianCalendar();
                        //System.out.println("TimestampArduino decimal: " + TimestampINT);  

                                 
                        
                        calendar.setTimeInMillis(sta.getMILIS());
                        //System.out.println("Data base: "+sdf.format(calendar.getTime()));
                        long TimestampLONG=(long)TimestampINT+sta.getMILIS();
                        //System.out.println("Timestamp real: " + TimestampLONG);
                        calendar.setTimeInMillis(TimestampLONG);
                        String dataReal=sdf.format(calendar.getTime());
                        //System.out.println("Data real: "+dataReal);
                        //NAS AMOSTRAS É A DATA REAL e no erro tb
                              
                            //System.out.println("erro");
                            tipo_err[0]=datanovo[7];

                            System.out.println("\nERRO: [Tipo:"+datanovo[0]+" ; End.Ard:"+(cod_ard[0]+cod_ard[1])+" ; TS:"+TimestampINT+" ; Erro:"+(int)tipo_err[0]+"]");

                        try {
                            g.atualiza("");
                        
                            g.atualiza("ERRO recebido: ");
                            g.atualiza("Cod_Arduino: "+" ; End.Ard:"+(cod_ard[0]+cod_ard[1]));
                            g.atualiza("Timestamp do erro (convertido): "+dataReal);

                            if(tipo_err[0]==(byte)1){
                                g.atualiza("Erro1: valores da configuração impossíveis!");
                            }
                            if(tipo_err[0]==(byte)2){
                                g.atualiza("Erro2: falha na leitura do START!");
                            }
                            if(tipo_err[0]==(byte)3){
                                g.atualiza("Erro3: NS da configuração demasiado baixo! (NS minimo=20)");
                            }
                            if(tipo_err[0]==(byte)4){
                                g.atualiza("Erro4: PA da conconfiguração demasiado baixo! (PA minimo=20 microssegundos)");
                            }
                            if(tipo_err[0]==(byte)5){
                                g.atualiza("Erro5: PM da conconfiguração demasiado baixo! (PM minimo=20 ms)");
                            }
                            if(tipo_err[0]==(byte)6){
                                g.atualiza("Erro6: NS da configuração demasiado alto! (NS maximo=200)");
                            }
                            
                            comm.fi.setST(1);
                            
                        } catch (IOException ex) {
                            Logger.getLogger(comm.class.getName()).log(Level.SEVERE, null, ex);
                        }  

                } 
                
               
                //DATA
                if(datanovo[0]==(byte)10){                     
                     
                     byte[] cod_ard=new byte[2];
                     byte[] timestamp=new byte[4];
                     byte[] end=new byte[1];
                     byte[] pm=new byte[4];
                     byte[] pa=new byte[4];
                     byte[] ns=new byte[1];
                        
                     byte[] valores;
                     int values[];
                     
                     
                     //codi_ard 
                     int j=0;
                     for(int i=1;i<3;i++){
                         cod_ard[j]=datanovo[i];
                         j++;
                     }
                   
                     //timestamp
                       
                        j=0;
                        for(int i=3;i<7;i++){
                            timestamp[j]=datanovo[i];
                            j++;
                            //System.out.println(Integer.toBinaryString(datanovo[i] & 255 | 256).substring(1));
                        } 
                        
                        /*System.out.println("");
                        System.out.println("Timestamp: ");         
                        for (byte b : timestamp) {
                            System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1));
                        }*/

                        int TimestampINT=ByteBuffer.wrap(timestamp).getInt(); 
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss.SS"); 
                        GregorianCalendar calendar=new GregorianCalendar();


                        //System.out.println("TimestampArduino decimal: " + TimestampINT);         
                        //System.out.println("\nTimestamp base: " + sta.getMILIS()); 
                        calendar.setTimeInMillis(sta.getMILIS());
                        //System.out.println("Data base: "+sdf.format(calendar.getTime()));
                        long TimestampLONG=(long)TimestampINT+sta.getMILIS();
                        //System.out.println("Timestamp real: " + TimestampLONG);
                        calendar.setTimeInMillis(TimestampLONG);
                        String dataReal=sdf.format(calendar.getTime());
                        //System.out.println("Data real: "+dataReal);
                        //NAS AMOSTRAS É A DATA REAL e no erro tb
                        
                        j=0;
                        for(int i=7;i<11;i++){
                            pm[j]=datanovo[i];
                            j++;
                        }
                        
                        j=0;
                        for(int i=11;i<15;i++){
                            pa[j]=datanovo[i];
                            j++;
                        }
                        
                        ns[0]=datanovo[15]; 
                        int nrAmostras=unsignedByteToInt(ns[0]);
                        //System.out.println("Nr amostras: "+ nrAmostras); 
                        j=0;
                        valores= new byte [nrAmostras*2];     
                        for(int i=16;i<(16+(nrAmostras*2));i++){
                            valores[j]=datanovo[i];
                            j++;
                            //System.out.println("valor: "+ valores[j]);
                        }
                        

                        int paa=java.nio.ByteBuffer.wrap(pa).getInt();  
                        values= new int[nrAmostras];
                        System.out.println("\n-----DATA recebida: Sensor "+(cod_ard[0]+cod_ard[1]));
                        //System.out.println("\nDATA: [Tipo:"+datanovo[0]+" ; End.Ard:"+(cod_ard[0]+cod_ard[1])+" ; TS:"+TimestampINT+" ; PM:"+ java.nio.ByteBuffer.wrap(pm).getInt()+" ; PA:"+ paa+" ; NS:"+ nrAmostras+"]");
                        g.atualiza("\nDATA: [Tipo:"+datanovo[0]+" ; End.Ard:"+(cod_ard[0]+cod_ard[1])+" ; TS:"+TimestampINT+" ; PM:"+ java.nio.ByteBuffer.wrap(pm).getInt()+" ; PA:"+ paa+" ; NS:"+ nrAmostras+"]");
                        j=0;
                        int y=0;
                        byte[] val= new byte[4];
                        for (byte b:valores){
                            
                            if((j % 2 != 0)&&(j!=0)){
                                val[0]=0;
                                val[1]=0;
                                val[3]=valores[j]; 
                                //imprimir
                                
                                values[y]=java.nio.ByteBuffer.wrap(val).getInt();
                                
                                //System.out.println(values[y]);
                            
                                y++;
 
                            }
                            else{
                               
                                val[2]=valores[j];                           
                            }
                            j++;  
                        }

                        //System.out.println("");
                        //System.out.println("");
                        
                        
                        DecimalFormat df = new DecimalFormat("###,##0.00");
                       
                        
                        //guardar no arraylist
                        GregorianCalendar calendarnovo=new GregorianCalendar();
                        String dataReall= dataReal;
                        long tempoAmostra_i=TimestampLONG;
                        long tempoAmostra=tempoAmostra_i;
                        //double tempAmost_double=(double)tempoAmostra;
                        
                        for(int i=1;i<nrAmostras+1;i++){
                            
                            amostra a = new amostra( (cod_ard[0]+cod_ard[1]) , tempoAmostra , paa, i, values[i-1]);
                            //tempAmost_double=tempAmost_double+(paa/1000);
                            tempoAmostra=tempoAmostra_i+((i*paa)/1000);
                            calendarnovo.setTimeInMillis(tempoAmostra);
                            dataReall=sdf.format(calendarnovo.getTime());

                            //colocar amostras arraylist
                            comm.fi.add(a);
                            //System.out.println("Data amostra: "+dataReall+ "        TimeLong: "+tempoAmostra);
                        }
                        //System.out.println("Eu ja adicionei as amostras!\n\n");
                        comm.fi.setST(0);
                        
                    }
            
            //return fi;
        }
        
        
        public int unsignedByteToInt(byte b) {
                return (int) b & 0xFF;
        }
    }
    
