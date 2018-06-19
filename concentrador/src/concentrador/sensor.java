/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concentrador;

import java.net.InetAddress;


public class sensor {
    
   
    int com_sensor;
    InetAddress ip_sensor;
    boolean ativo;
    boolean simu;
    
    
    public sensor(int c, InetAddress s, boolean b){
        com_sensor=c;
        ip_sensor=s;
        ativo=true;
        simu=b;
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
    
    public int getCOM_SENSOR(){
        return com_sensor;
    }
    
    public InetAddress getIP_SENSOR(){
        return ip_sensor;
    }
    
}
