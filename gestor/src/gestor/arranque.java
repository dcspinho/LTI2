
package gestor;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class arranque {
        
        estatist est;
        
        SensoresAtivos sensAt_frame;
        String i;
    
        ConexaoMySQL conex;
        mensa m;
        
        int freq;
        DatagramSocket s;
        int comsock;
        int num_thr=0;
        
        boolean comecou=false;
        
        Thread ouvir, esc, estatis_thread;
        Scanner scanner = new Scanner (System.in);
        
        comEd comEdif;
        
        //chave é o instante do cálulo e o valor é o um treeset com a área e respetivo consumo acumulado
        TreeMap<Long, TreeMap<Integer, Double>> mapa= new TreeMap<Long, TreeMap<Integer, Double>>() ;
        
        long ultimoTempo;
        
        
        ArrayList<concentrador> lista_conc= new ArrayList<concentrador>();
       
        public ArrayList<concentrador> getConc(){
            return lista_conc;
        } 
        
        
        public arranque(ConexaoMySQL cc, mensa mm, TreeMap<Long, TreeMap<Integer, Double>> list, long temp, estatist ee, comEd com){
            conex=cc;
            m=mm;
            est=ee;
            mapa=list;
            ultimoTempo=temp;
            comEdif=com;

        }
            
        
        public synchronized TreeMap<Integer, Double> atualiza_retornaMAPA(boolean adiciona, TreeMap<Integer, Double> novo_cons, long time){
            
            if(adiciona==true){
                //System.out.println("TOU a ADICIONAR");
                mapa.put(time,novo_cons);
            }
            TreeMap<Integer, Double> ultimo_consAc= new  TreeMap<Integer, Double> ();

            //System.out.println("ARVORE: ");
            for(Long temp:mapa.keySet()){
                //System.out.println("temp: "+temp);               
                if(temp==ultimoTempo(false, 0)){
                    ultimo_consAc=mapa.get(temp);
                }
            }

            return ultimo_consAc;
            
        }
        
        public synchronized long ultimoTempo(boolean altera, long t){
              if(altera==true){
                  ultimoTempo=t;
              }

              return ultimoTempo;
        }
        
        
        public void START_unico(concentrador cc){
            
                byte stop[];
             
                try {
                    stop = cc.stop(freq);

                    DatagramPacket resp= new DatagramPacket(stop, stop.length ,cc.ip_conce, cc.com_conce);
                    s.send(resp);
                } catch (IOException ex) {
                    Logger.getLogger(arranque.class.getName()).log(Level.SEVERE, null, ex);
                }
              
                cc.setSTA(false);
  
        }
        
        
        public void acorda(){
            //habilita e inicia escrita       ************
            esc=(new Thread(new EnviarDados()));
            esc.start();
        
        }
        
        
        
        public void comecar(String[] begin) throws IOException{
            
                     
            comsock=Integer.parseInt(begin[0]);
            freq=Integer.parseInt(begin[1]);
                       
            s= new DatagramSocket(comsock); 
            
            ouvir=(new Thread(new arranque.ouveSockets()));
            ouvir.start();
           
            
            i="stop";
            
        
        }
        
        
        public class EnviarDados implements Runnable 
        {           

            @Override
            public void run ()
            {     
                
                        if("start".equals(i)){                                                     

                            System.out.println("    START");
                            
                            for(concentrador cc: lista_conc){                       
                                cc.setSTA(false);
                            }

                            i="stop";
                                     
                        }
                        
                        else if("stop".equals(i)){
                           
                            System.out.println("    STOP");
                            
                            for(concentrador cc: lista_conc){
                                if (cc.getSTA()==true){
                                    byte stop[];
             
                                    try {
                                        stop = cc.stop(freq);
                                        /* System.out.println("Stop ");
                                        for(byte b: stop){
                                        System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1) + "  ______  " + unsignedByteToInt(b));
                                        }*/
                                        DatagramPacket resp= new DatagramPacket(stop, stop.length ,cc.ip_conce, cc.com_conce);
                                        s.send(resp);
                                    } catch (IOException ex) {
                                        Logger.getLogger(arranque.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                
                            }
                            
                                                       
                            i="start";      
         
                        }   
                
            }
    
    }
        
        
        
        
        
        //so para ouvir do sockets
        public class ouveSockets implements Runnable 
        {     
            boolean i=true;

            
            @Override
            public void run (){
                
                System.out.println("\n Esperando...");
                
                byte buffer[];
                
                do{
                    buffer=new byte[1000000];
                     
                    // cria datagrama para receber request do concentrador
                    DatagramPacket r = new DatagramPacket(buffer, buffer.length);
                    
                    
                    try {
                        s.receive(r);
                        
                        Thread t=(new Thread(new arranque.process(r, buffer)));
                        t.start();
                                                
                        
                    } catch (IOException ex) {
                        Logger.getLogger(arranque.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                }while(i==true);
                
                    
            }
            
        }
        
        
        public class process implements Runnable 
        {     
            byte buffer[];
            DatagramPacket recebido;
            boolean existe=false;
            
            public process(DatagramPacket r , byte buff[]) {               
                recebido=r;
                buffer=buff;
            }

                       
            @Override
            public void run (){
                    
                    //é hello
                    if(buffer[0]==(byte)0){
                        byte ns[]=new byte[4];
                        
                        ns[0]=(byte)0;
                        ns[1]=(byte)0;
                        ns[2]=buffer[8];
                        ns[3]=buffer[9];
                                           
                        int n= ByteBuffer.wrap(ns).getInt();

                        byte[]hello = new byte[10+(2*n)];
                        System.arraycopy(buffer, 0, hello, 0, hello.length);
                        
                        System.out.println("\n HELLO");

                        
                        //ver se ja conheço o concentrador
                        for(concentrador c:lista_conc){
                            if (c.getCOM_CONC()==recebido.getPort()){
                                existe=true; 
                                
                                c.ativar();
                                
                                //vou atualizar a lista de sensores
                                ArrayList<byte[]> listaa=new ArrayList<byte[]>();
                                ArrayList<sensor> lista_sens=new ArrayList<sensor>();
                               
                                int j=0;
                                byte bb[];
                                sensor sen;
                                //adicionar a lista de sensores
                                for(int i=0;i<n;i++){
                                    bb=new byte[2];
                                    bb[0]=hello[10+j];
                                    j++;
                                    bb[1]=hello[10+j];
                                    j++;
                                    listaa.add(bb);
                                    sen=new sensor(bb, conex);
                                    
                                    lista_sens.add(sen);
                                }
                                
                                c.atualizaLista(listaa);
                                c.atualizaLista_sens(lista_sens);
                                
                                
                                if(c.getSTA()==false){
                                    
                                    if(c.lista_sensores_cod.size()!=0){
                                        byte start[] = null;
                                        try {
                                            start = c.start(freq, ns[2],ns[3],n);
                                        } catch (IOException ex) {
                                            Logger.getLogger(arranque.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        c.setEstadoSen(false);
                                        //m.escreverArea("\n ---> Start enviado: C"+c.numConc+"\n");
                                        
                                        //m.Aviso("Novo Concentrador conectado! ");
                                                                               
                                        //System.out.println("START: ");
                                        
                                        /*for(byte b: start){
                                            System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1) + "  ______  " + unsignedByteToInt(b)); 
                                        }*/
                                        

                                        DatagramPacket resp= new DatagramPacket(start, start.length ,c.ip_conce, c.com_conce);
                                        try {
                                            s.send(resp);
                                        } catch (IOException ex) {
                                            Logger.getLogger(arranque.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        c.setSTA(true);
                                        //****************  NOVO CONCENTRADOR CONECTADO!! *************
                                        if(c.getEstadoCon()==false){
                                            m.Aviso("Novo Concentrador conectado! (C"+c.numConc+")");
                                            c.setEstadoCon(true);
                                        }
                                    }
                                }
                               
                                    
                                if((c.getEstadoSen()==false)&&(c.getSTA()==true)){
                                       
                                        //vamos ver se mudaram os sensores
                                        ArrayList<sensor> lista_START=c.acedeLista_sens_START();
                                        ArrayList<sensor> lista_HELLO=c.acedeLista_sens();  

                                        //estado dos sensores alterado!
                                        if(lista_START.size()!=lista_HELLO.size()){
                                            c.setEstadoSen(true);
                                            m.Aviso("Estado dos sensores alterado! (Concentrador "+c.numConc+")");                                            
                                        }                                  
                                }
                                
                                
                            }
                        }

                        
                        //novo concentrador!!
                        if(existe==false){
                            
                            //System.out.println("\n ------------------->  Novo concentrador");

                            //registar concentrador
                            concentrador c=new concentrador(recebido.getPort(),recebido.getAddress(),conex);
                            
                            //adicionar concentrador a lista
                            lista_conc.add(c);                            
                            
                            ArrayList<byte[]> listaa=new ArrayList<byte[]>();
                            ArrayList<sensor> lista_sens=new ArrayList<sensor>();
                            
                            int j=0;
                            byte bb[];
                            sensor sen;
                            
                            //adicionar a lista de sensores
                            for(int i=0;i<n;i++){
                                bb=new byte[2];
                                bb[0]=hello[10+j];
                                j++;
                                bb[1]=hello[10+j];
                                j++;
                                listaa.add(bb);
                                sen=new sensor(bb, conex);
                                lista_sens.add(sen);
                            }
                            
                            
                            c.atualizaLista(listaa);
                            c.atualizaLista_sens(lista_sens);

                            Thread t= new Thread ( new detetaConc(c));
                            t.start();
                            
                            
                            //tenho que enviar start se ele tiver sensores simulados ligados           
                            if(n!=0){
                                byte start[] = null;
                                try {
                                    start = c.start(freq, ns[2],ns[3],n);
                                } catch (IOException ex) {
                                    Logger.getLogger(arranque.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                c.setEstadoSen(false);
                                //m.escreverArea("\n ---> Start enviado: C"+c.numConc+"\n");                       
                                
                                //System.out.println("START: ");
                                /*for(byte b: start){
                                    System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1) + "  ______  " + unsignedByteToInt(b)); 
                                }*/

                                DatagramPacket resp= new DatagramPacket(start, start.length ,c.ip_conce, c.com_conce);
                                try {
                                    s.send(resp);
                                } catch (IOException ex) {
                                    Logger.getLogger(arranque.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                c.setSTA(true);
                                
                                //****************  NOVO CONCENTRADOR CONECTADO!! *************
                                if(c.getEstadoCon()==false){
                                    m.Aviso("Novo Concentrador conectado! (C"+c.numConc+")");
                                    c.setEstadoCon(true);
                                }
                                
                            }
                            
                        }
                        
                    } 
                    
                    //sao dados
                    else if(buffer[0]==(byte)3){
                            int y=0;
                            int posicao=0;
                            for(concentrador c:lista_conc){
                                if (c.getCOM_CONC()==recebido.getPort()){
                                     posicao=y;   
                                }
                                y++;
                            }
                        
                            byte ns[]=new byte[4];
                        
                            ns[0]=(byte)0;
                            ns[1]=(byte)0;
                            ns[2]=buffer[8];
                            ns[3]=buffer[9];

                            int n= ByteBuffer.wrap(ns).getInt();

                            byte[]dados = new byte[10+(16*n)];
                            //System.out.println("\n nr records: "+n);
                            System.arraycopy(buffer, 0, dados, 0, dados.length);
                            
                            //System.out.println("\n DATA");
                            
                            
                            long timestamp;
                            byte[] time = new byte[8];
                            time[0]=(byte)0;
                            time[1]=buffer[1];
                            time[2]=buffer[2];
                            time[3]=buffer[3];
                            time[4]=buffer[4];
                            time[5]=buffer[5];
                            time[6]=buffer[6];
                            time[7]=buffer[7];
                            timestamp= ByteBuffer.wrap(time).getLong();
                            //este timestamp q vem na data que recebo é o nome de cada file das amostras
                            
                            //System.out.println("NOME FILE: "+String.valueOf(timestamp));
                            
                            fich fi=new fich(String.valueOf(timestamp), lista_conc.get(posicao).numConc );
                            
                            
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss:SS");
                            GregorianCalendar calendarnovo=new GregorianCalendar();
                            calendarnovo.setTimeInMillis(timestamp);
                            String dataReal=sdf.format(calendarnovo.getTime());
                            
                            //escrever na interface isto
                            m.escreverArea("\n Mens. dados recebida: \n C"+lista_conc.get(posicao).numConc+": "+dataReal+"\n");
                            
                                                        
                            for(int i=10;i<10+(16*n);i=i+16){
                                byte[] t= new byte[16];
                                
                                t[0]=buffer[i];
                                t[1]=buffer[i+1];
                                t[2]=buffer[i+2];
                                t[3]=buffer[i+3];
                                t[4]=buffer[i+4];
                                t[5]=buffer[i+5];
                                t[6]=buffer[i+6];
                                t[7]=buffer[i+7];
                                t[8]=buffer[i+8];
                                t[9]=buffer[i+9];
                                t[10]=buffer[i+10];
                                t[11]=buffer[i+11];
                                t[12]=buffer[i+12];
                                t[13]=buffer[i+13];
                                t[14]=buffer[i+14];
                                t[15]=buffer[i+15];
                                
                                
                                record rr=constroiRec(t);   
                                
                                try {
                                    fi.add(rr);       
                                } catch (IOException ex) {
                                    Logger.getLogger(arranque.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                                for(sensor sens: lista_conc.get(posicao).lista_sensores_comunicao){
                                        if(sens.cod_sens==rr.cod_ard){
                                            sens.addLista(rr);
                                        }
                                }
                           }
                                
                            try {
                                fi.gerarFich();
                            } catch (IOException ex) {
                                Logger.getLogger(arranque.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            for (sensor ss: lista_conc.get(posicao).lista_sensores_comunicao){
                                //System.out.println("calcular cons");
                                ss.valorMedioCons();
                                ss.setListaRec(new ArrayList<record>());
                            }
                            

                            
                            if(est.getFILECRIA()==false){
                               
                                estatis_thread= new Thread(new consAcum());
                                estatis_thread.start();
             

                                est.setFILECRIA(true);
                            
                            }
                            
                            
                        }
                                

                           
                    
                    }
                    
                    
                    
        }
        
        
        
        
        
        public void acumUltima24H(Custos c){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss.SS");  
            GregorianCalendar calendar=new GregorianCalendar();
            long tempAgora=calendar.getTimeInMillis();
            DecimalFormat df_ener = new DecimalFormat("###,#####0.000");
            DecimalFormat df_custo = new DecimalFormat("###,##0.00");
            
            boolean entra=false;
            double acum=0;
            TreeMap<Long, TreeMap<Integer, Double>> map= mapa;
            TreeMap<Integer, Double> valores1= new TreeMap<Integer, Double>();
            
            //System.out.println("\nTempo: "+tempAgora);
            String dataReal=sdf.format(tempAgora);
            //System.out.println("Data Agora: "+ dataReal);
            
            long millisHora=24*60*60*1000;
            //System.out.println("1hora: "+millisHora);
            
            long dif=tempAgora-millisHora;
            //System.out.println("\nUma hora atras: "+dif);
            dataReal=sdf.format(dif);
            //System.out.println("Data uma hora atras que queria: "+ dataReal);
        
            
            long tempRef=0;
            for(long t:map.keySet()){            
                //quando encontrar um tempo maior que o que estou à procura
                if((dif<t)&&(entra==false)){                  
                    tempRef=t;
                    valores1=map.get(t);
                    entra=true;
                    
                    dataReal=sdf.format(t);
                    //System.out.println("\nTempo à uma atras que encontrei: "+ t);
                    //System.out.println("Data à uma atras que encontrei: "+ dataReal);
                }

            }
            
            double acumAntes=0;
            for(int i:valores1.keySet()){
                  acumAntes=acumAntes+valores1.get(i);
            }
            
            //System.out.println("Acumulado à uma hora: "+ acumAntes);
            
            
            Long lastT=map.lastKey();
            TreeMap<Integer, Double> valores2= new TreeMap<Integer, Double>();
            for(long t:map.keySet()){            
                //quando encontrar um tempo maior que o que estou à procura
                if(t==lastT){
                    valores2=map.get(t);
                     
                    dataReal=sdf.format(t);
                    //System.out.println("\nUltimo Tempo que existe: "+ t);
                    //System.out.println("Ultima data que existe: "+ dataReal);
                }

            }
            
            double acumAgora=0;
            for(int i:valores2.keySet()){
                  acumAgora=acumAgora+valores2.get(i);
            }
            
            //System.out.println("Acumulado de agora: "+ acumAgora);
            
            acum=acumAgora-acumAntes;
            
           // System.out.println("\nRESULTADO: "+ acum+" kWh");
            double custo=acum*0.16;
            //System.out.println("\nCUSTO: "+ df_custo.format(custo)+" €");
            
            c.escreverEnergiaUltima24Hora(df_ener.format(acum*(3.6*Math.pow(10,6)))+" J");
            c.escreverCustoUltima24Hora(df_custo.format(custo)+" €");
            
        }
        
        
        
        
        
        
        
        
        
        
        
        
        public void acumUltimaHora(Custos c){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss.SS");  
            GregorianCalendar calendar=new GregorianCalendar();
            long tempAgora=calendar.getTimeInMillis();
            DecimalFormat df_ener = new DecimalFormat("###,#####0.000");
            DecimalFormat df_custo = new DecimalFormat("###,##0.00");
            
            boolean entra=false;
            double acum=0;
            TreeMap<Long, TreeMap<Integer, Double>> map= mapa;
            TreeMap<Integer, Double> valores1= new TreeMap<Integer, Double>();
            
            //System.out.println("\nTempo: "+tempAgora);
            String dataReal=sdf.format(tempAgora);
            //System.out.println("Data Agora: "+ dataReal);
            
            long millisHora=60*60*1000;
            //System.out.println("1hora: "+millisHora);
            
            long dif=tempAgora-millisHora;
            //System.out.println("\nUma hora atras: "+dif);
            dataReal=sdf.format(dif);
           // System.out.println("Data uma hora atras que queria: "+ dataReal);
        
            
            long tempRef=0;
            for(long t:map.keySet()){            
                //quando encontrar um tempo maior que o que estou à procura
                if((dif<t)&&(entra==false)){                  
                    tempRef=t;
                    valores1=map.get(t);
                    entra=true;
                    
                    dataReal=sdf.format(t);
                    //System.out.println("\nTempo à uma atras que encontrei: "+ t);
                   // System.out.println("Data à uma atras que encontrei: "+ dataReal);
                }

            }
            
            double acumAntes=0;
            for(int i:valores1.keySet()){
                  acumAntes=acumAntes+valores1.get(i);
            }
            
            //System.out.println("Acumulado à uma hora: "+ acumAntes);
            
            
            Long lastT=map.lastKey();
            TreeMap<Integer, Double> valores2= new TreeMap<Integer, Double>();
            for(long t:map.keySet()){            
                //quando encontrar um tempo maior que o que estou à procura
                if(t==lastT){
                    valores2=map.get(t);
                     
                    dataReal=sdf.format(t);
                    ///System.out.println("\nUltimo Tempo que existe: "+ t);
                    //System.out.println("Ultima data que existe: "+ dataReal);
                }

            }
            
            double acumAgora=0;
            for(int i:valores2.keySet()){
                  acumAgora=acumAgora+valores2.get(i);
            }
            
            //System.out.println("Acumulado de agora: "+ acumAgora);
            
            acum=acumAgora-acumAntes;
            
            //System.out.println("\nRESULTADO: "+ acum+" kWh");
            double custo=acum*comEdif.getPrec();/*comEdif.getPrec();*/
            //System.out.println("\nCUSTO: "+ df_custo.format(custo)+" €");
            //System.out.println("\nCUSTO: "+ custo+" €");
            
            c.escreverEnergiaUltimaHora(df_ener.format(acum*(3.6*Math.pow(10,6)))+" J");
            c.escreverCustoUltimaHora(df_custo.format(custo)+" €");
            
        }
        
        
        
        
        
        
        
        
        
        
        
        public class consAcum implements Runnable {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss.SS"); 
            DecimalFormat df = new DecimalFormat("###,##0.00");
            ArrayList<sensor> list_sens;
            boolean ent=true;

            
            public consAcum(){
                
            }
        
            
    
            public void run(){
                
                do{
 
                        TreeMap<Integer, Double> novo_mapa=atualiza_retornaMAPA(false, null, 0);  


                        ArrayList<concentrador> list_conc=lista_conc;
                        list_sens=new ArrayList<sensor>();
                        int area;


                        GregorianCalendar calendar=new GregorianCalendar();
                        long TimestampLONG=calendar.getTimeInMillis();

                        //percorrer a lista de concentradores
                        for(concentrador con:list_conc){

                            list_sens=con.acedeLista_sens_START();
                            //percorrer a lista de sensores desse concenrador
                            for(sensor sen:list_sens){
                                //retirar o consumo dele e respetiva área associada
                                area=sen.getArea();                  
                                
                                //System.out.println("area a procurar: "+area);
                                //System.out.println("MAPA");
                                //for(int a:novo_mapa.keySet()){
                                   // System.out.println("-->"+a);
                                //}
                                
                                //atualizar o hashmap naquela area
                                if(novo_mapa.containsKey(area)){ //se o mapa ja tiver esta área
                                    novo_mapa.put(area,novo_mapa.get(area)+sen.acede_ConsAcum(false,0));
                                    sen.acede_ConsAcum(true, 0);
                                    //System.out.println("Area: "+area);
                                    //System.out.println("Valor_SOMAR: "+df.format(novo_mapa.get(area)));
                                }
                                else{   
                                    //se nao tiver
                                    novo_mapa.put(area,sen.acede_ConsAcum(false,0));
                                    sen.acede_ConsAcum(true, 0);
                                    //System.out.println("Area1: "+area);
                                    //System.out.println("Valor_NOVO: "+df.format(novo_mapa.get(area)));
                                }
                            }
                        }

                        atualiza_retornaMAPA(true, novo_mapa, TimestampLONG);          
                        ultimoTempo(true, TimestampLONG);
                        
                        est.addFich(TimestampLONG, novo_mapa);
                        
                        
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(arranque.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                }while(ent==true);
            }
            
   
            
  
        }
        
        
        public void mostrarCons(Consumos c_frame){
                
                DecimalFormat df = new DecimalFormat("###,#####0.00000");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd     HH:mm:ss:SS"); 
                
                double acum=0;
                long t=ultimoTempo(false,0);
                String dataReal=sdf.format(t);
                //System.out.println("time: " +t);
                TreeMap<Integer, Double> ultimo=atualiza_retornaMAPA(false, null, 0);
                
                ArrayList<String> lista_cons_area= new ArrayList<String>();

                
                lista_cons_area.add("      "+dataReal+"\n");
                
                for (Integer i: ultimo.keySet()){
                    acum=acum+ultimo.get(i);
                    lista_cons_area.add("\n Área "+i+":\n Consumo acumulado: "+df.format(ultimo.get(i))+" kWh"+"\n");
                }
                
                c_frame.escrever(lista_cons_area, df.format(acum));
            
        }
        
        
        public void ultimos_PM(Custos cc){
                
                DecimalFormat df = new DecimalFormat("###,##0.00");
                         
                ArrayList<String> lista_cons= new ArrayList<String>();

                ArrayList<concentrador> listC= lista_conc;
                
                
                for(concentrador c: listC){
                    ArrayList<sensor> listS=c.acedeLista_sens_START();
                    for(sensor s:listS){
                        double ene=(3.6*Math.pow(10,6))*(s.acede_ultimo_PM(false, 0));
                        lista_cons.add("Sensor "+s.cod_sens+"   : "+df.format(ene) +" J");
                    }
                }
                
                for(String s:lista_cons){
                    cc.escreverEnergiaUltimoPM(s+"\n");
                }
            
        }

        
        
        
           
        public record constroiRec(byte[] t){

                int cod_ard;
                byte[] cod=new byte[4];
                cod[0]=0;
                cod[1]=0;
                cod[2]=t[0];
                cod[3]=t[1];
                cod_ard= ByteBuffer.wrap(cod).getInt();
                
                
                long timestamp;
                byte[] time = new byte[8];
                time[0]=(byte)0;
                time[1]=t[2];
                time[2]=t[3];
                time[3]=t[4];
                time[4]=t[5];
                time[5]=t[6];
                time[6]=t[7];
                time[7]=t[8];
                timestamp= ByteBuffer.wrap(time).getLong();        
                
                int paa;
                byte[] pa=new byte[4];
                pa[0]=t[9];
                pa[1]=t[10];
                pa[2]=t[11];
                pa[3]=t[12];
                paa= ByteBuffer.wrap(pa).getInt();
                
                
                byte n[]=new byte[4];
                n[0]=0;
                n[1]=0;
                n[2]=0;
                n[3]=t[13];
                int nn=ByteBuffer.wrap(n).getInt();

                
                int val;
                byte[] value=new byte[4];
                value[0]=0;
                value[1]=0;
                value[2]=t[14];
                value[3]=t[15];
                val= ByteBuffer.wrap(value).getInt();
                
               record rr=new record(cod_ard,timestamp,paa,nn,val);

               return rr;

        }
        
        
        
     
        
       
    
    //os que vêm no hello
    public ArrayList<String> listaSens_Conc(){
        
        ArrayList<String> lista_sens_con= new ArrayList<String>();

        for(concentrador c:lista_conc){                      
            //acede aos sensores do concentrador
            for(sensor ss : c.acedeLista_sens()){              
                lista_sens_con.add("\n Sensor "+ss.cod_sens+":\n Concentrador "+c.numConc+"; Área "+ss.area+" ("+ss.designacao+")\n");
            }
        }
        return lista_sens_con;
    } 
     
    
    //os que vêm no START
    public ArrayList<String> listaSens_Conc_START(){
        ArrayList<String> lista_sens_con= new ArrayList<String>();
        
        for(concentrador c:lista_conc){
                      
            //acede aos sensores do concentrador
            for(sensor ss : c.acedeLista_sens_START()){              
                lista_sens_con.add("\n Sensor "+ss.cod_sens+":\n Concentrador "+c.numConc+"; Área "+ss.area+" ("+ss.designacao+")\n");
            }
        }
        return lista_sens_con;
    } 
    
    
    
    
      
    public class detetaConc implements Runnable 
    {
        
        concentrador sen;
        boolean ciclo=true;
        
        public detetaConc(concentrador con){
             sen=con;
        }
        
        public void run ()
        {
            do{
                try {
                    //esperar hello
                    Thread.sleep(1400);
                    

                    if(sen.getAtivo()==true){
                        //System.out.println("Concentrador "+sen.numConc+" ligado!!");
                        sen.desativar();
                    }
                    else{
                        //System.out.println("Concentrador "+sen.numConc+" desligado!!");
                        m.Aviso("Concentrador desconectado! (C"+sen.numConc+")");
                        sen.setEstadoCon(false);
                       // ArrayList<concentrador> listaNovaSens=acede_listaSens(false, null);
                        
                        //System.out.println("Tamanho da lista de sens: "+listaNovaSens.size());
                        // System.out.println("Tamanho da lista de cod: "+listaNovaCod.size());
                        
                        //vamos remove lo das listas
                        
                        int tamanho=lista_conc.size();
                        for(int i=0;i<tamanho;i++){
                            concentrador s=lista_conc.get(i);
                            if(s.numConc==sen.numConc){
                                lista_conc.remove(i);
                                i=tamanho;
                                //acede_listaSens(true, listaNovaSens);
                            }
                        }
                                               
                        //System.out.println("Tamanho da lista de sens: "+listaNovaSens.size());
                        //System.out.println("Tamanho da lista de cod: "+ listaNovaCod.size());
                        ciclo=false;
                        
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(arranque.class.getName()).log(Level.SEVERE, null, ex);
                }
            }while(ciclo==true);
           // System.out.println("saiu");
        }
    }
    
    
    public int unsignedByteToInt(byte b) {
                return (int) b & 0xFF;
    }
}
