
package concentrador;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;




public class comm{
    
    
    int t=0;
    
    ArrayList<Thread> sensAtivo=new ArrayList<Thread>();
    boolean begin=false;
    
    boolean adicionaCom=true;
    boolean beginn=true;
    int posicao;
    static ArrayList<String> nome_files;
    
    ArrayList<sensor> lista_sensores=new ArrayList<sensor>();
    ArrayList<byte[]> cod_sensores= new ArrayList<byte[]>();
    
    ArrayList<byte[]> cod_sensores_temp= new  ArrayList<byte[]> ();
    ArrayList<sensor> lista_sensores_temp=new ArrayList<sensor>();
    
    
    ArrayList<byte[]> lista_records_uteis=new ArrayList<byte[]>();
    ArrayList<byte[]> lista_records_inuteis=new ArrayList<byte[]>();
    
    
    int num_thr=0;

    Scanner scanner = new Scanner (System.in);
    
    InputStream entrada;
    OutputStream saida;
    
    byte[] tramaData;
    
    int existeCOM=0;
    int existeSocket=0;
            
    DatagramSocket s;
    int ns;
    int comecou=0;
    int comsock;
    String ip;
    InetAddress cli;
    
    String current;
    byte[]obj;
    start sta; 
    
    int len = -1; 
    Thread ler,esc, socket, espera;
    log g;
    
    byte[] data = new byte[1024];
    int nBytes;
    byte[] datanovo;
    byte[] buffernovo;
    int c ;
    static fich fi;
    
    
    CommPortIdentifier cp;
    SerialPort porta;
    boolean IDPortaOK;          //true porta existe
    boolean PortaOK;            //true porta aberta
    
    
    
    comm(start sta, log gg){
        cod_sensores= new ArrayList<byte[]>();
        this.sta=sta;
        fi=new fich("amostras");
        g=gg;
        posicao=0;
        nome_files= new ArrayList<String>();
    }

    public void setPosicao(int i){
        posicao=i;
    }
    
    public int getPosicao(){
        return posicao;
    }
    
    public synchronized ArrayList<sensor> acede_listaSens(boolean alte, ArrayList<sensor> nova){
        if(alte==true){
            lista_sensores=nova;
        }       
        return lista_sensores;
    }

    public synchronized ArrayList<byte[]> acede_listadeCodigos(boolean alte, ArrayList<byte[]> nova){
         if(alte==true){
            cod_sensores=nova;
        } 

        return cod_sensores;
    }
    

        
    public void datas(ArrayList<byte[]> lista, int f, InetAddress is, int c, DatagramSocket sss) throws IOException, InterruptedException{
        
        boolean ent=false;
        
        double tempoEspera=1/(double)f;
        
        tempoEspera=tempoEspera*1000;
        
        InetAddress ser=is; 
        int comGest=c;
        DatagramSocket ss=sss;
        
        ArrayList<byte[]> sensor_data=lista;
        File file;

        int i=1;

        ArrayList<String> files;
        
        //iniciar temporizador
        espera=(new Thread(new comm.esperar((int)tempoEspera)));
        espera.start();
        
        int tamanho=0;
        
        if(begin==true){
            setPosicao(getPosicao()-1);
        }
        
        int pos=getPosicao();
        
        do{
            
            synchronized(nome_files){
                files=nome_files; 
                tamanho=nome_files.size();
            }
            
            if(tamanho==0){                
                //System.out.println("entrouuuu");
            }
            else{  
  
                if(pos<tamanho-1){
                    //System.out.println("tamanhoI: "+ tamanhoIni);
                    //System.out.println("tamanho: "+ tamanho);
                    
                    /*for(String str:files){
                        System.out.println("NomeFIle: "+ str);
                    }*/
                   
                    //tamanhoIni=tamanhoIni+1;
                    //System.out.println("esperannnnndooo");
                    
                    if(ent==true){
                        //System.out.println("-------------> Posiçao: "+ pos);
                        if(files.get(pos).equals("")&&(pos!=tamanho)){
                            pos++;
                            //System.out.println("Entrou no somatorio");
                        }
                        else{
                            file=lerFile(files.get(pos), sensor_data);
                           
                            System.out.println("---------------------------> File enviado: "+ files.get(pos));
                           
                            //mando estes records
                            //System.out.println("inuteis: "+lista_records_inuteis.size());
                            //System.out.println("uteis: "+lista_records_uteis.size());               

                            //construo a data
                            byte[] datagrama=constroiTrama();
                            //envio o datagrama mas ainda falta esperar o tempo
                            DatagramPacket data = new DatagramPacket(datagrama, datagrama.length, ser ,comGest);

                            //espera que o temporizador terminar para enviar o hello
                            espera.join();
                            ss.send(data);

                            espera.stop();

                            //iniciar temporizador
                            espera=(new Thread(new comm.esperar((int)tempoEspera)));
                            espera.start();

                            file.delete();

                            if(lista_records_inuteis.size()!=0){
                                criaNovoFich(files.get(pos));
                            }
                            else{
                                //System.out.println(" Ficheiro eliminado: "+ files.get(pos));
                                synchronized(nome_files){  
                                    nome_files.set(pos,"");
                                }
                            }
                            //limpo os arrays   
                            lista_records_inuteis=new ArrayList<byte[]>();
                            lista_records_uteis=new ArrayList<byte[]>();

                            pos++;     
                        }

                    }
                    
                    else{
                                               

                        if(files.get(getPosicao()).equals("")&&(pos!=tamanho)){
                            setPosicao(getPosicao()+1);
                            //System.out.println("Entrou no somatoriooo");
                        }
                        else{
                            file=lerFile(files.get(getPosicao()), sensor_data);
                            
                            System.out.println("-------------> File enviado: "+ files.get(getPosicao()));
                            
                            //mando estes records
                            //System.out.println("inuteis: "+lista_records_inuteis.size());
                            //System.out.println("uteis: "+lista_records_uteis.size());               

                            //construo a data
                            byte[] datagrama=constroiTrama();

                            //envio o datagrama mas ainda falta esperar o tempo
                            DatagramPacket data = new DatagramPacket(datagrama, datagrama.length, ser ,comGest);

                            //espera que o temporizador terminar para enviar o hello
                            espera.join();

                            ss.send(data);

                            espera.stop();

                            //iniciar temporizador
                            espera=(new Thread(new comm.esperar((int)tempoEspera)));
                            espera.start();

                            file.delete();

                            if(lista_records_inuteis.size()!=0){
                                if(ent==false){
                                    
                                    setPosicao(getPosicao());
                                    ent=true; 
                                    begin=true;
                                }
                                //System.out.println("Alterar o ficheiro com os inuteis");
                                //meto os inuteis no mesmo file que havia
                                criaNovoFich(files.get(getPosicao()));

                            }
                            else{

                                //System.out.println(" Ficheiro eliminado: "+ files.get(getPosicao()));
                                
                                synchronized(nome_files){  
                                    nome_files.set(getPosicao(),"");
                                }
                            }
                            
                            pos=getPosicao();
                            //limpo os arrays   
                            lista_records_inuteis=new ArrayList<byte[]>();
                            lista_records_uteis=new ArrayList<byte[]>();

                            setPosicao(getPosicao()+1);
                            
                        }
                    }
                    
                    
                }
            }
        }while(i==1);        
    }
    
    
     public class esperar implements Runnable 
    {           
            int tempoEspera;
            
            public esperar(int tim){
                   tempoEspera=tim;
            }
            
         
            @Override
            public void run (){
                    
                try {
                    Thread.sleep(tempoEspera);
                    
                } catch (InterruptedException ex) {
                        Logger.getLogger(comunicacao.class.getName()).log(Level.SEVERE, null, ex);
                }
  
            }
    }
     
     
    public void criaNovoFich(String nome) throws IOException{
       
            File arquivo = new File(nome);
            try {
                if (!arquivo.exists()) {     
                    //cria um arquivo (vazio)
                    arquivo.createNewFile();
                }
                               
                //escreve no arquivo
                FileWriter fw = new FileWriter(arquivo, true);
                BufferedWriter bw = new BufferedWriter(fw);
                
                //criar novamente amostras direitas e po las no file
                ArrayList<amostra> lista_amostra=new ArrayList<amostra>();
                
                for(byte[] r:lista_records_inuteis){
                    
                    int cod_ard;
                    byte[] cod=new byte[4];
                    cod[0]=0;
                    cod[1]=0;
                    cod[2]=r[0];
                    cod[3]=r[1];
                    cod_ard= ByteBuffer.wrap(cod).getInt();
                    
                    long timestamp;
                    byte[] time = new byte[8];
                    time[0]=(byte)0;
                    time[1]=r[2];
                    time[2]=r[3];
                    time[3]=r[4];
                    time[4]=r[5];
                    time[5]=r[6];
                    time[6]=r[7];
                    time[7]=r[8];
                    timestamp= ByteBuffer.wrap(time).getLong();
                    
                    //System.out.println("timestamp "+ timestamp);
                    
                    int paa;
                    byte[] pa=new byte[4];
                    pa[0]=r[9];
                    pa[1]=r[10];
                    pa[2]=r[11];
                    pa[3]=r[12];
                    paa= ByteBuffer.wrap(pa).getInt();
                    
                    byte n[]=new byte[4];
                    n[0]=0;
                    n[1]=0;
                    n[2]=0;
                    n[3]=r[13];
                    int nn=ByteBuffer.wrap(n).getInt();

                    int val;
                    byte[] value=new byte[4];
                    value[0]=0;
                    value[1]=0;
                    value[2]=r[14];
                    value[3]=r[15];
                    val= ByteBuffer.wrap(value).getInt();
                    
                    //System.out.println("nova amostra: "+ cod_ard + " , "+ timestamp + " , " + paa + " , " + nn + " , "+ val );
                    amostra a = new amostra(cod_ard , timestamp , paa, nn, val);
                    lista_amostra.add(a);
                }
                
                
                //percorrer todas as amostras e guardar no file
                for(amostra a: lista_amostra){
                    bw.write(a.escString());
                }
               
                //System.out.print("\n\n\n Ficheiro criado! Nome: "+ getNome() +" \n\n");
                
                bw.close();
                fw.close();
               
                
           } catch (IOException ex) {
               ex.printStackTrace();
           }

        }
    
    
    

    
    public byte[] constroiTrama(){
            int nr=lista_records_uteis.size();
            
            byte[] datagrama=new byte[10+(16*nr)];
            
            datagrama[0]=(byte)3;
            //System.out.println("\ntipo: "+ unsignedByteToInt(datagrama[0]));
        
            
            
            Calendar calendar = Calendar.getInstance();
            long milliseconds=calendar.getTimeInMillis();  
            //System.out.println("\ntimestamp: "+milliseconds);
            byte[] time1;
            time1=ByteBuffer.allocate(8).putLong(milliseconds).array(); 

            /*for (byte b : time1) {
                 System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1) + "  ______  " + unsignedByteToInt(b));    
            }*/
        
            datagrama[1]=time1[1];
            datagrama[2]=time1[2];
            datagrama[3]=time1[3];
            datagrama[4]=time1[4];
            datagrama[5]=time1[5];
            datagrama[6]=time1[6];
            datagrama[7]=time1[7];
            
            
                       
            /*System.out.println("Tempo que vou enviar mesmo");
            for (byte b : time) {
                System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1) + "  ______  " + unsignedByteToInt(b));    
            }*/
            
            byte[] n;
            n=ByteBuffer.allocate(4).putInt(nr).array(); 
            datagrama[8]=n[2];
            datagrama[9]=n[3];
            
            /*System.out.println("numero:");
            for (byte b : n) {
                System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1) + "  ______  " + unsignedByteToInt(b));    
            }*/
            
            
            int j=10;
            for(byte b[]:lista_records_uteis){
                System.arraycopy(b, 0, datagrama, j, b.length);
                j=j+16;
            }
            
            //System.out.println("datagrama tamanho:"+datagrama.length);
            /*for (byte b : datagrama) {
                System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1) + "  ______  " + unsignedByteToInt(b));    
            }*/
           
            
            
            return datagrama;


    }
    
    
    
    public File lerFile(String nome, ArrayList<byte[]> lista) throws IOException, InterruptedException{
            
            ArrayList<byte[]> sensor_data=lista;
        
            boolean pertence=false;
        
            File file = new File(nome);
            
            record r;
            BufferedReader StrR = new BufferedReader(new FileReader(nome));
            
            String Str;
            String[] TableLine;
            
            
            //ler cada linha do file
            while((Str = StrR.readLine())!= null){
                r=new record();
                
                //Aqui usamos o método split que divide a linha lida em um array de String
                //passando como parametro o divisor ";".
                TableLine = Str.split(";");
                
                //imprimie cada parametro do array de String
                for (String cell : TableLine) {
                    //System.out.print(cell+" "); 
                    //System.out.println("\n");
                    r.addLista(cell);
                }
                
                r.preencheRecord();                
                byte rec[]=r.constroiRecord();
                byte cod[]=new byte[2];
                cod[0]=rec[0];
                cod[1]=rec[1];
                
                /*System.out.println("sensor do record!!");
                for (byte b : cod) {
                    System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1) + "  ______  " + unsignedByteToInt(b));    
                }*/
                
                
                for(byte[] b: sensor_data){
                    if((b[0]==cod[0])&&(b[1]==cod[1])){
                        //System.out.println("esse sensor pertence!!");
                        pertence=true;
                    }
                }
                if(pertence==true){
                    lista_records_uteis.add(rec);
                    //System.out.println("adicionei uteis!!");
                    
                }
                else{
                    lista_records_inuteis.add(rec);
                    //System.out.println("adicionei inuteis!!");
                }
                
                               
                pertence=false;
                
            }
            
            
            //Fechamos o buffer
            StrR.close();
            
            return file;
            
            
            
    }
            
       
            
            
    
    
    public void AbrirPorta(int baud) throws IOException{
        
        try{
            porta=(SerialPort)cp.open(this.getClass().getName(), 2000);
            PortaOK=true;
            
            porta.setSerialPortParams(baud,
                                      porta.DATABITS_8,
                                      porta.STOPBITS_1,            
                                      porta.PARITY_NONE);        
            
        }catch(Exception e){
            PortaOK=false;
            g.atualiza("Erro: Falha ao abrir a porta COM! (Baud-Rate)");

        }
        
    }
    
    
    public void conetarPorta(String com) throws IOException{
                  
            try{
                cp=CommPortIdentifier.getPortIdentifier("COM"+com);
                if(cp==null){
                   g.atualiza("Erro: A COM"+ com + " não existe!");
                   IDPortaOK=false;
                }

                IDPortaOK=true;

            }catch(Exception e){
                g.atualiza("Erro: Falha na conexão da porta COM!");
                IDPortaOK=false;
                
            }
    }
    
    

    public void iniciaEsc(int c, String i, int baud) throws IOException, InterruptedException{
        
        
        if(baud!=0){      
            if(IDPortaOK==true){
                    AbrirPorta(baud);
                    if(PortaOK==true){
                        try {
                           //habilitar leitura na porta serie
                            entrada = porta.getInputStream();

                            //habilitar escrita na porta serie
                            saida = porta.getOutputStream();

                        } catch (IOException e) {
                           g.atualiza("Erro: Falha na ligação com a porta COM!");
                        }

                        existeCOM=1;
                        g.atualiza("Sensor real conectado!");
                        System.out.println("Sensor real conectado!");
                    }
            }
        
        }
        else{
            
        }
        
        if(c!=0){
            comsock=c;
            ip=i; 
            //comsock=7777 porta do servidor
            s = new DatagramSocket(comsock);
            existeSocket=1;
            
        }  
        else{
            //System.out.println("Nao existe simulado");
        }
         
        
        if((existeCOM==1)||(existeSocket==1)){
            //habilita e inicia escrita       ************
            esc=(new Thread(new EnviarDados(saida, g)));
            esc.start();
        }
        
        else{
            g.atualiza("Nao existem sensores conectados!");
            System.exit(0);
        }
        
        System.out.println("\n START: preessione ENTER \n STOP: preessione ENTER \n\n END: preessione 0 + ENTER\n");
                
    }
    
   
    public class detetaSens implements Runnable 
    {
        boolean comeca=true;
        Thread t;
        sensor sen;
        int pm;
        byte cod[];
        boolean ciclo=true;
        
        
        public detetaSens(sensor s, byte c[]){
             sen=s;
             pm=sta.pm;
             cod=c;
        }
        
        public void run ()
        {
            if(((byte)0==cod[0])&&((byte)4==cod[1])){
                ciclo=false;
            }
            while(ciclo==true){
                
                if(comeca==true){
                    //iniciar temporizador
                    t=new Thread(new comm.esperar(1000));
                    t.start();
                    comeca=false;
                }
                else{
                    //iniciar temporizador
                    t=new Thread(new comm.esperar(pm+300));
                    t.start();
                }

                try {
                    t.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(comm.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if(sen.getAtivo()==true){
                    //System.out.println("Sensor "+(cod[0]+cod[1])+" ligado!!");
                    sen.desativar();
                }
                else{
                    System.out.println("Sensor "+(cod[0]+cod[1])+" desligado!!");
                    try {
                        g.atualiza("Sensor "+(cod[0]+cod[1])+" desligado!!");
                    } catch (IOException ex) {
                        Logger.getLogger(comm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ArrayList<sensor> listaNovaSens=acede_listaSens(false, null);
                    ArrayList<byte[]> listaNovaCod=acede_listadeCodigos(false, null);
                    //System.out.println("Tamanho da lista de sens: "+listaNovaSens.size());
                    //System.out.println("Tamanho da lista de cod: "+listaNovaCod.size());
                    
                    //vamos remove lo das listas
                    int tamanho=listaNovaSens.size();
                    for(int i=0;i<tamanho;i++){
                        sensor s=listaNovaSens.get(i);
                        if(s.com_sensor==sen.com_sensor){
                            listaNovaSens.remove(i);
                            i=tamanho;
                            acede_listaSens(true, listaNovaSens);
                        }
                    }
                    
                    int tam=listaNovaCod.size();
                    for(int i=0;i<tam;i++){
                        byte bb[]=listaNovaCod.get(i);
                        if((bb[0]==cod[0])&&(bb[1]==cod[1])){
                            listaNovaCod.remove(bb);
                            i=tam;
                            acede_listadeCodigos(true, listaNovaCod);
                        }
                    }
                    
                    //System.out.println("Tamanho da lista de sens: "+listaNovaSens.size());
                    //System.out.println("Tamanho da lista de cod: "+ listaNovaCod.size());
                    
                    ciclo=false;
                    
                }
            }
           // System.out.println("saiu");
        }
            
    
}


public class EnviarDados implements Runnable 
{           int contagemSimu=0;
            String i="stop";  
            log g;
            byte[] acabar=new byte[2];
            OutputStream outt;  
            boolean finito=false;
            
            boolean f=false;
            
            public EnviarDados(  OutputStream outt, log gg )
            {
                this.outt = outt;
                this.g=gg;
            }
          
            @Override
            public void run ()
            {     
                
            try
                {   
                    if(existeSocket==1){
                            //ouvir no socket
                            //System.out.println("EnviarDados: Comecei a thread que ouve do sockets");
                            socket=(new Thread(new socketsThread()));
                            socket.start();
                            
                    }
                    
                    if(existeCOM==1){
                        
                        Thread.sleep(1500);
                        
                        //mandar START
                        nBytes=0;
                        obj=sta.constTrama();
                        byte b= (byte)obj[13];
                        ns= unsignedByteToInt(b);

                        this.outt.write(obj);
                        saida.flush();
                        
                        ler=(new Thread(new LerDados(entrada,g)));
                        ler.start();
                        
                     }
                    
                   
                    c = 0;
                    while ((( c = System.in.read(acabar)) > -1 )&&(finito==false))
                    {     
                   
                        
                        if(("start".equals(i))&&(acabar[0]==10)){                                                     
                              
                            ArrayList<sensor> list=acede_listaSens(false, null);
                            for(sensor s:lista_sensores_temp) {
                                list.add(s);
                            }
                            acede_listaSens(true, list);
                            lista_sensores_temp=new ArrayList<sensor>();
                                                      
                            g.atualiza("");
                            g.atualiza("Inicio da Comunicação com sensores:");
                            
                            System.out.println("_____START");
                            
                            nBytes=0;
                             
                            obj=sta.constTrama();
                            
                            byte b= (byte)obj[13];
                            ns= unsignedByteToInt(b);
                            //System.out.println(ns);
                            
                            if(existeSocket==1){
                                ArrayList<sensor> listt=acede_listaSens(false, null);
                                
                                for(sensor ss: listt ){
                                    DatagramPacket resp= new DatagramPacket(obj, 14,ss.ip_sensor, ss.com_sensor);
                                    s.send(resp);
                                    //System.out.println("iniciaCumSocket: Start enviado com sucesso! " );       
                                }   
                            }
                            
                            
                            if(existeCOM==1){
                                this.outt.write(obj);
                                saida.flush();
                                
                                ler=(new Thread(new LerDados(entrada,g)));
                                ler.start();
                            }
                            
                            ArrayList<byte[]> list_c=acede_listadeCodigos(false, null);
                            for(byte[] bbb: cod_sensores_temp) {
                                list_c.add(bbb);
                            }
                            acede_listadeCodigos(true, list_c);
                            cod_sensores_temp= new ArrayList<byte[]> ();
                            
                            i="stop";
                            acabar=new byte[2]; 
                            
                            t=acede_listaSens(false, null).size();
                            
                            //System.out.println("t: "+t);
                            
                            
                            //crio uma thread que fica responsavel por detetar se algum sensor se desliga
                            sensAtivo=new ArrayList<Thread>();

                            
                            
                            for(int j=0; j<t;j++){
                                sensAtivo.add(new Thread(new detetaSens(acede_listaSens(false, null).get(j), acede_listadeCodigos(false, null).get(j))));
                            }
                            for(int j=0; j<t;j++){
                                sensAtivo.get(j).start();
                            }  
                             
                            

                        }
                        
                        else if(("stop".equals(i))&&(acabar[0]==10)){

                            //System.out.println("EnviarDados: Consegui ler o ENTER do stop");
                            System.out.println("_____STOP");
                            
                            if(existeCOM==1){
                                //terminar leitura
                                ler.stop();
                            }

                            //cria trama stop
                            stop sto=new stop(2);
                            //escrever no log
                            sto.escLOG(g); 
                            
                            obj=sto.constTrama(); 
                            
                            
                            if(existeSocket==1){
                                ArrayList<sensor> listtt=acede_listaSens(false,null);
                                for(sensor ss: listtt){
                                    DatagramPacket resp= new DatagramPacket(obj, 2,ss.ip_sensor, ss.com_sensor);
                                    s.send(resp);
                                    //System.out.println("iniciaCumSocket: Stop enviado com sucesso! " );       
                                }              
                            }

                            
                            if(existeCOM==1){
                                this.outt.write(obj);                               
                                saida.flush();                       
                            }
                            //g.atualiza("Fim da Comunicação:");
                            i="start";      
                                                     
                            acabar=new byte[2];
                            
                            
                            for(int j=0; j<t;j++){
                                    sensAtivo.get(j).stop();
                            } 
                            
                        }   
                        
                        else if(((acabar[0]==48)&&(acabar[1]==10))&&(("stop".equals(i)))){
                           
                            //System.out.println("EnviarDados: Consegui ler o ENTER do stop para finalizar");
                            System.out.println("_____STOP"); 
                            if(existeCOM==1){
                                ler.stop();
                                saida.flush();
                            }
                            
                            stop sto=new stop(0);   
                            obj=sto.constTrama();
                            sto.escLOG(g);
                            
                            if(existeSocket==1){
                                ArrayList<sensor> listttt=acede_listaSens(false, null);
                                for(sensor ss: listttt ){
                                    DatagramPacket resp= new DatagramPacket(obj, 2,ss.ip_sensor, ss.com_sensor);
                                    s.send(resp);
                                    //System.out.println("iniciaCumSocket: Stop enviado com sucesso! " );       
                                }    
                            } 
                            if(existeCOM==1){
                                this.outt.write(obj);                    
                            }
                            //g.atualiza("Fim da Comunicação:");
                                                        
                            System.out.println("\n\nConcentrador encerrado");
                            
                            finito=true;
                            
                            try {

                                    if(existeCOM==1){
                                        porta.close();
                                        ler.stop();
                                    }

                                    g.atualiza("-----------------------FIM----------------------");
                                    g.atualiza("");


                                    if(existeSocket==1){
                                        socket.stop();
                                        s.close();
                                    }
                                    
                                    Thread.sleep(300);
                                    synchronized(fi){
                                         fi.fimprog();
                                    }
                                    
                                        for(int j=0; j<t;j++){
                                            sensAtivo.get(j).stop();
                                        }
                                    
                                    System.exit(0);


                            } catch (IOException e) {
                                     g.atualiza("Erro: Falha a fechar a porta COM! ");
                            } catch (InterruptedException ex) {
                                Logger.getLogger(comm.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                        }
                       
                    }                
                }
                catch ( IOException e )
                {
                    System.out.print("Erro a escrever!\n\n"); 
                } catch (InterruptedException ex) { 
        Logger.getLogger(comm.class.getName()).log(Level.SEVERE, null, ex);
    } 
                
            }
    
    }



    public class socketsThread implements Runnable{
        
        byte tramaGeral[]=new byte[500];
        boolean ouve=true;
        boolean voltaOuvir=false;
        DatagramPacket r;
        
        
        public void run (){
                 
            do{
                
                do{
                        tramaGeral= new byte[500]; 
                        //cria datagrama para receber request do cliente
                        r = new DatagramPacket(tramaGeral, tramaGeral.length);
                        voltaOuvir=false;
                        
                        try {
                            s.receive(r);
                            //System.out.println("socketsThread: Eiii ouvi algo");
                            
                            
                        } catch (IOException ex) {
                            Logger.getLogger(comm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                        ArrayList<sensor> sen_tempo=acede_listaSens(false, null);
                        for(sensor s:sen_tempo){
                            if (s.com_sensor==r.getPort()){ 
                                 voltaOuvir=true;                               
                                 //System.out.println("socketsThread: Ohh ohhh sao dados!!");
                                 Thread t=(new Thread(new iniciaProcData(tramaGeral)));
                                 
                                 t.start();
                                 tramaGeral=new byte[500];
                                 
                            }       
                        }  
                       
                }while(voltaOuvir==true);
                
               
                try {    
                    g.atualiza("Sensor simulado conectado!");
                    System.out.println("Sensor simulado conectado!");
                    
                    
                } catch (IOException ex){
                    Logger.getLogger(comm.class.getName()).log(Level.SEVERE, null, ex);
                }
                //System.out.println("socketsThread: Adicionei o sensor: "+num_thr);
                    
                
                //criar novo sensor
                sensor sens=new sensor(r.getPort(),r.getAddress(), true);
                //adicionar sensor a lista
                //lista_sensores_temp.add(s);
                num_thr++;
               
                byte codd[]=new byte[2];
                codd[0]=tramaGeral[2];
                codd[1]=tramaGeral[1];
                
                //cod_sensores_temp.add(codd);

                                                                                          
                ArrayList<sensor> list=acede_listaSens(false, null);
                //for(sensor s:lista_sensores_temp) {
                list.add(sens);
                //}
                acede_listaSens(true, list);
                
                //lista_sensores_temp=new ArrayList<sensor>();
                                                      
                nBytes=0;
                obj=sta.constTrama();                            
                byte b= (byte)obj[13];
                ns= unsignedByteToInt(b);
                            
                DatagramPacket resp= new DatagramPacket(obj, 14,sens.ip_sensor, sens.com_sensor);
                try {
                    s.send(resp);
                } catch (IOException ex) {
                    Logger.getLogger(comm.class.getName()).log(Level.SEVERE, null, ex);
                }
                //System.out.println("iniciaCumSocket: Start enviado com sucesso! " );       

                ArrayList<byte[]> list_c=acede_listadeCodigos(false, null);
                list_c.add(codd);
                acede_listadeCodigos(true, list_c);
                
                
                t=acede_listaSens(false, null).size();
                sensAtivo.add(new Thread(new detetaSens(sens, codd)));
                sensAtivo.get(t-1).start();
                

            }
            while(ouve==true);
        }
    }    

    
    public class iniciaProcData implements Runnable 
            
    {   
            byte tramaGeral []= new byte[500];    
            byte[] trama;
            
            public iniciaProcData(byte rece[]){
                    tramaGeral=rece;
            }
            
            @Override
            public void run (){

                    trama=new byte[ns*2+17];
                                    
                    System.arraycopy(tramaGeral, 0, trama, 0, ns*2+17);

                    //System.out.println("\n -----------------------> Data recebida do simulado");
                     
                    byte[] cod_ard = new byte[2]; 
                    int j=0;
                    for(int i=1;i<3;i++){
                        cod_ard[j]=trama[i];
                        j++;
                    }
                    
                    ArrayList<byte[]> list=acede_listadeCodigos(false, null);
                    int index=0;
                    
                    
                    
                    
                        for(byte [] bb : list){
                            if((bb[0]==cod_ard[0])&&(bb[1]==cod_ard[1])){
                               index= list.indexOf(bb);
                               //System.out.println("entrou: index="+index);
                            }
                        } 
                        
                        if(adicionaCom==true){
                            acede_listaSens(false, null).get(index-1).ativar();
                        }
                        else{
                            acede_listaSens(false, null).get(index).ativar();
                        }
                   
                    
                    proc p= new proc(g, trama, trama.length, sta);
                    
                    try {  
                        
                        synchronized(fi){
                            p.processa();
                        }
                        
                    } catch (IOException ex) {
                        Logger.getLogger(comm.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }   
            
                                           
    }
    

    public class LerDados implements Runnable 
    {
        
        InputStream in; 
        log g;
        boolean entra=false;
        
        
        public LerDados ( InputStream in, log gg )
        {
            this.in = in;
            this.g=gg;
            
        }
        
        public void run ()
        {
            byte[] buffer=new byte[1024];
            
            len = -1;          
            
            try
            {   
                    
                while (( len = this.in.read(buffer)) > -1 ){
                       
                    //System.out.println("Entrou: "+len);
                    
                    buffernovo=new byte[len];
                    System.arraycopy(buffer,0, buffernovo, 0, len);
                    
                    
                    System.arraycopy(buffernovo, 0, data, nBytes, len);                   
                    nBytes=nBytes+len;

                    datanovo=new byte[nBytes];
                    System.arraycopy(data, 0, datanovo, 0, nBytes);
                   
                    for (byte b : buffernovo) {
                        //System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1));
                        entra=true;
                    }
                    
                   
                    
                    if((entra==true)&&(buffernovo[buffernovo.length-1]==(byte)255)){
                            //System.out.println("");
                            /*System.out.println("");
                            for (byte b : datanovo) {
                                System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1));
                            }*/
                            //System.out.println("\n ------> Data recebida do real");
                            
                            if((adicionaCom==true)&&(beginn==true)){
                                byte[] codd;
                                codd=new byte[2];
                                codd[0]=datanovo[1];
                                codd[1]=datanovo[2];
                                ArrayList<byte[]> list_c=acede_listadeCodigos(false, null);
                                list_c.add(codd);
                                acede_listadeCodigos(true, list_c);
                                
                                /*for (byte b : codd) {
                                    System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1) + "  ______  " + unsignedByteToInt(b));    
                                }*/
                                
                            }
                            
                            beginn=false;
                            
                            proc p= new proc(g, datanovo, nBytes, sta);
                            
                            synchronized(fi){
                                p.processa();                       
                            }
                            
                            entra=false; 
                            datanovo= null;
                            //data= new byte[1024];
                            nBytes=0;
                            

                            /*try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(comm.class.getName()).log(Level.SEVERE, null, ex);
                            }*/
                    }
                    
                }  
 
            }
            catch ( IOException e )
            {
                System.out.print("Erro a ler!\n\n"); 
            }            
        }
}

    
    public int unsignedByteToInt(byte b) {
                return (int) b & 0xFF;
    }

    
       
}


    