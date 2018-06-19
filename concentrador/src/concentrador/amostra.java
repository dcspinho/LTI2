
package concentrador;


public class amostra {
    
        int cod_ard;
        long data;
        int pa;
        int nr_amostra;
        int valor_amostra;
        
        public amostra(int c, long d, int pa, int n, int val){
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
