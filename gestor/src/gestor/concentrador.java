
package gestor;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Calendar;



public class concentrador {
    int com_conce;
    InetAddress ip_conce;
    byte ns_array[]=new byte[2];
    int ns;
    boolean fileCriado=false;
    
    boolean estadoSen;
    boolean estadoCon;
     
    int numConc;

    ConexaoMySQL conex;
    
    boolean sta;
    boolean ativo=true;
    
    
    ArrayList<estatist> lista_estatisticas=new ArrayList<estatist>();
    
    //os que vêm no hello
    ArrayList<byte[]> lista_sensores_cod=new ArrayList<byte[]>();
    ArrayList<sensor> lista_sensores=new ArrayList<sensor>(); 
    
    //os de comunicação sao os do START
    ArrayList<sensor> lista_sensores_comunicao=new ArrayList<sensor>();
    ArrayList<byte[]> lista_sensores_cod_comunicao=new ArrayList<byte[]>();
    
    
    
    long Timestamp_Start;
    

    public concentrador(int co, InetAddress s, ConexaoMySQL cc ){
        com_conce=co;
        ip_conce=s;
        sta=false;
        conex=cc;
        numConc=conex.concentrador(com_conce);
        estadoSen=false;
        estadoCon=false;
    }

    
    public boolean getEstadoSen(){
        return estadoSen;
    }
    
    public void setEstadoSen(boolean e){
        estadoSen=e;
    }
    
    public boolean getEstadoCon(){
        return estadoCon;
    }
    
    public void setEstadoCon(boolean e){
        estadoCon=e;
    }
    
    
    public synchronized boolean getAtivo(){
        return ativo;
    }
    
    public synchronized void ativar(){
        ativo=true;
    }
    
    public synchronized void desativar(){
        ativo=false;
    }
    
    
    public void addEstat(estatist e){
        lista_estatisticas.add(e);
    }
    
    public long getTimestampStart(){
        return Timestamp_Start;
    }
    


    
    public int getNumConc(){
        return numConc;
    }
    
    
    public void setNS_array(byte b1, byte b2){
            ns_array[0]=b1;
            ns_array[1]=b2;
    }
    public byte[] getNS_array(){
        return ns_array;
    }
    public void setNS(int b){
            ns=b;
    }
    public int getNS(){
        return ns;
    }
    
    
    public boolean getSTA(){
        return sta;
    }
    
    public void setSTA(boolean b){
        sta=b;
    }
    
    public int getCOM_CONC(){
        return com_conce;
    }
    
    public InetAddress getIP_CONC(){
        return ip_conce;
    }
    
    public void atualizaLista_START( ArrayList<sensor> lista){
        lista_sensores_comunicao=lista;
    }
    
    public void atualizaLista_cod_START( ArrayList<byte[]> lista){
        lista_sensores_cod_comunicao=lista;
     }
    
    public void atualizaLista( ArrayList<byte[]> lista){
        lista_sensores_cod=lista;
        /*System.out.println("Lista atualizada: ");
        for(byte[] b: lista_sensores){
            System.out.println(Integer.toBinaryString(b[0] & 255 | 256).substring(1) + "  ______  " + unsignedByteToInt(b[0])); 
            System.out.println(Integer.toBinaryString(b[1] & 255 | 256).substring(1) + "  ______  " + unsignedByteToInt(b[1])); 
        }*/
    }
    
    public void atualizaLista_sens( ArrayList<sensor> lista){
        lista_sensores=lista;
        /*System.out.println("Lista atualizada: ");
        for(byte[] b: lista_sensores){
            System.out.println(Integer.toBinaryString(b[0] & 255 | 256).substring(1) + "  ______  " + unsignedByteToInt(b[0])); 
            System.out.println(Integer.toBinaryString(b[1] & 255 | 256).substring(1) + "  ______  " + unsignedByteToInt(b[1])); 
        }*/
    }
    
    public ArrayList<sensor> acedeLista_sens(){
        return lista_sensores;
    }
    
    public ArrayList<sensor> acedeLista_sens_START(){
        return lista_sensores_comunicao;
    }
    
    
    public ArrayList<byte[]> acedeLista(){
        return lista_sensores_cod;
    }
       
    
    public byte[] start(int f, byte ns1, byte ns2, int n) throws IOException{
        //System.out.println("COM_concentrador: " + com_conce);
        
        setNS(n);
        setNS_array(ns1, ns2);
        
        byte trama[]= new byte[11+(2*n)];
        
        trama[0]=(byte)1;
        
        byte[] TS=getTimestamp();
        
        //timestamp
        trama[1]=TS[0];
        trama[2]=TS[1];
        trama[3]=TS[2];
        trama[4]=TS[3];
        trama[5]=TS[4];
        trama[6]=TS[5];
        trama[7]=TS[6];
        
        
        trama[8]=(byte)f;
        
        
        trama[9]=ns1;
        trama[10]=ns2;

        
        int j=0;
        byte tempp[]=new byte[2];
        
        //codigos
        for(int i=11; i<11+(2*n);i++){
            tempp=lista_sensores_cod.get(j);
            trama[i]=tempp[0];
            i++;
            trama[i]=tempp[1];
            j++;      
        }        
        
        lista_sensores_cod_comunicao=lista_sensores_cod;
        lista_sensores_comunicao=lista_sensores;
        
        return trama;
    }
    
    
    public byte[] stop(int f) throws IOException{
        byte trama[]= new byte[11+(2*getNS())];
        
        trama[0]=(byte)2;
        
        byte[] TS=getTimestamp();
        
        //timestamp
        trama[1]=TS[0];
        trama[2]=TS[1];
        trama[3]=TS[2];
        trama[4]=TS[3];
        trama[5]=TS[4];
        trama[6]=TS[5];
        trama[7]=TS[6];
        
        
        trama[8]=(byte)f;
        
        byte temp[]=getNS_array();
        trama[9]=temp[0];
        trama[10]=temp[1];

        
        int j=0;
        byte tempp[]=new byte[2];
        //codigos
        for(int i=11; i<11+(2*getNS());i++){
            tempp=lista_sensores_cod.get(j);
            trama[i]=tempp[0];
            i++;
            trama[i]=tempp[1];
            j++;      
        }        
        
        
        return trama;
    }
    
    
    
    public int unsignedByteToInt(byte b) {
                return (int) b & 0xFF;
    }
    
    public byte[] getTimestamp() throws IOException{
        
        byte[] time=new byte[7];
        
        Calendar calendar = Calendar.getInstance();
        long milliseconds=calendar.getTimeInMillis();  
        //System.out.println("timestamp: "+milliseconds);
        
        byte[] time1;
        time1=ByteBuffer.allocate(8).putLong(milliseconds).array(); 
        
        Timestamp_Start=milliseconds;
        
        /*for (byte b : time1) {
            System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1) + "  ______  " + unsignedByteToInt(b));    
        }*/
        
        time[0]=time1[1];
        time[1]=time1[2];
        time[2]=time1[3];
        time[3]=time1[4];
        time[4]=time1[5];
        time[5]=time1[6];
        time[6]=time1[7];
        
        /*System.out.println("Tempo que vou enviar mesmo");
        for (byte b : time) {
            System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1) + "  ______  " + unsignedByteToInt(b));    
        }*/

        
        
        return time;
    }
}
