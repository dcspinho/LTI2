/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concentrador;

import java.io.IOException;


public class stop {
    
   byte TP[]=new byte[1]; 
   byte SR[]=new byte[1];; 
   
   
   public stop(int i){
       TP[0]=(byte)3;
       SR[0]=(byte)i;
   }
   
   
   public byte getTP(){
       return this.TP[0];
   } 
   public byte getSR(){
       return this.SR[0];
   }
   
   
   //SR: 0-00 (Finalizar programa);  1-01(estourou o PM);    2-10    3-11
   public void setSR(int n){
       this.SR[0]=(byte)n;
   }
   
   
    public byte[] constTrama(){
       byte trama[]=new byte[2];       //TP:0      SR:1
       
       trama[0]=this.getTP();
       trama[1]=this.getSR();    

       /*System.out.println("");
       System.out.println("");       
       
       //imprimir trama;
       for (byte b : trama) {
            System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1));            
       }      
       
       System.out.println("");
       System.out.println("");*/
       
       return trama;
   }
    
    

   public void escLOG(log g ) throws IOException{
        
       
       //finalizar programa
       if(this.getSR()==0){
           //g.atualiza("");
           g.atualiza("Final da Comunicação: stop enviado");
           g.atualiza("Programa finalizado.");
       }
       
       //recebido o erro
       if(this.getSR()==1){
           //g.atualiza("");
           g.atualiza("Final da Comunicação: erro recebido e stop enviado");   
       }   
       
       //paragem
       if(this.getSR()==2){
           g.atualiza("Paragem na Comunicação: stop enviado");   
       }
       

   } 
 
   
   
   

   
   
}
