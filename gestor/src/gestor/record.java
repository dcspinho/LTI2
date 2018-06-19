/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor;


public class record {
    
        
        int cod_ard;
        long data;
        int pa;
        int nr_amostra;
        int valor_amostra;
    
        public record(int c, long d, int pa, int n, int val){
            this.cod_ard=c;
            this.data=d;
            this.pa=pa;
            this.nr_amostra=n;
            this.valor_amostra=val; 
        }
        
        
        public int getCOD(){
            return cod_ard;
        }
    
        public long getTIME(){
            return data;
        }
        
        public int getPA(){
            return pa;
        }
        
        public int getNR(){
            return nr_amostra;
        }
        
        public int getVAL(){
            return valor_amostra;
        }
    
    
    
    public String escString()
    {
        return( this.cod_ard + ";" + this.data + ";" + this.pa + ";" + this.nr_amostra + ";" +this.valor_amostra + "\n");
    }
    
    
    
    
    
}
