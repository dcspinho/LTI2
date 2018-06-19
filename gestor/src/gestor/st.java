
package gestor;

import java.text.DecimalFormat;


public class st {
    
    String s;
    DecimalFormat df_ener = new DecimalFormat("###,#####0.00000");
    
    public st(){
    }
    
    public String getS(){
        return s;
    }
    
    public void setS(String time, double val){
        
        String str = String.valueOf(df_ener.format(val));
        s=time+";"+str+";";
    }

}

