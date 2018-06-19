
package concentrador;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;




public class start {
   
   Calendar calendar1;
   Date date;
   long milliseconds1 ;
   
   
   byte TP; 
   byte[] TS = new byte[4];         
   byte[] PM = new byte[4]; 
   byte[] PA = new byte[4]; 
   byte NS; 
   int pm;
    
   
   public start(){
       
       TP=00; 
       calendar1 = Calendar.getInstance();
       //calendar1.set(2018, 02, 17);
       date = calendar1.getTime();    
       milliseconds1=calendar1.getTimeInMillis();
   }
   
   public int getINTPM(){
       return pm;
   }
   

   public long getMILIS(){
       return this.milliseconds1;
   }
   
   public byte getTP(){         
       return this.TP;
   } 
   public byte getTS(int i){    //retorna o byte da posição i
       return this.TS[i];
   }   
   public byte getPM(int i){    //retorna o byte da posição i
       return this.PM[i];
   }  
   public byte getPA(int i){    //retorna o byte da posição i
       return this.PA[i];
   }   
   public byte getNS(){
       return this.NS;
   }
   
   
   public void setTS(int t){
       this.TS=ByteBuffer.allocate(4).putInt(t).array(); 
       /*System.out.println("TS decimal: " + t); 
       for (byte b : this.TS) {
            System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1));
       }
       System.out.println(""); */
       
   }
   public void setPM(int d){
       pm=d;
       this.PM=ByteBuffer.allocate(4).putInt(d).array();
       
       /*System.out.println("PM decimal: " + d); 
       for (byte b : this.PM) {
            System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1));
       }
       System.out.println("");*/ 
       
   }
   public void setPA(int d){
       this.PA=ByteBuffer.allocate(4).putInt(d).array();
       
       /*System.out.println("PA decimal: " + d); 
       for (byte b : this.PA) {
            System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1));
       }
       System.out.println(""); */
   }
   
   public void setNS(int d){       
       this.NS=(byte)d;
       
       /*System.out.println("NS decimal: " +this.NS);     //aparece o nº decimal certo do NS
       System.out.println("NS binario: "+ Integer.toBinaryString(this.NS & 255 | 256).substring(1));    //NS em binario
       
       System.out.println("\nTP decimal: " +this.TP);     //aparece o nº decimal certo do TP
       System.out.println("TP binario: "+Integer.toBinaryString(this.TP & 255 | 256).substring(1));    //TP em binario
       */
   }
   
   
 
   public byte[] constTrama(){
       byte trama[]=new byte[14];       //TP:0      TS:1-4      PM:5-8      PA:9-12     NS:13    
       
       trama[0]=this.getTP();
       trama[13]=this.getNS();    

       int j=0;
       for(int i=5;i<9;i++){
           trama[i]=this.getPM(j);
           j++;
       }
       
       j=0;
       for(int i=9;i<13;i++){
           trama[i]=this.getPA(j);
           j++;
       }
       
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH.mm.ss.SS"); 
       //System.out.println("Calendar1: "+sdf.format(date)); 
       //System.out.println("Calendar1(mili): " + this.getMILIS());
             
       Calendar calendar2 = Calendar.getInstance();       
       long milliseconds2 = calendar2.getTimeInMillis();
       long diff = milliseconds2 - milliseconds1; 
       
       /*System.out.println("Calendar2: "+sdf.format(calendar2.getTime())); 
       System.out.println("Calendar2(mili): " + milliseconds2); */
       //System.out.println("DIFF(mili): " + diff);
       
       //System.out.println(""); 
       
       this.setTS((int)diff);
       
       j=0;
       for(int i=1;i<5;i++){
           trama[i]=this.getTS(j);
           j++;
       }

       //System.out.println("START: ");
       //imprimir trama;
       /*for (byte b : trama) {
            System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1));            
       }*/
       
       System.out.println("");
       System.out.println("");
       
       return trama;
   }
   

}
