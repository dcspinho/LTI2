
package central;

import java.sql.Connection;
import java.sql.SQLException;


public class edificio {
    
       int codigo;
       Connection con;
       
       
       public edificio(Connection c){
           con=c;
       }
       
       
       public int getCOD(){
           return codigo;
       }
       
       public void setCOD(int c){
           codigo=c;
       }
       
       public void addVal(String s) throws SQLException, ClassNotFoundException{
           long timestamp;
           double valor;
           DataBase t=new DataBase();
           String array[] = new String[2];
           array = s.split(";");
           /*System.out.println("array :"+array);
           System.out.println("\ntime:"+array[0]);*/
           timestamp= Long.parseLong(array[0]);
           //System.out.println("con : "+con);
           
           array[1]=array[1].replaceAll(",", ".");
           valor=Double.parseDouble(array[1]);
           //System.out.println("val:"+array[1]);
           t.add_consumo(con, codigo, valor, timestamp);
           //t.ver_tabelaConsumo(con);
       }
       

}
