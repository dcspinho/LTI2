
package gestor;



import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;



public class mensa extends javax.swing.JFrame{
    
    static arranque a;

    static ConexaoMySQL c;
    static TreeMap<Long, TreeMap<Integer, Double>> mapa;
    static long ultimoTemp;
    static String[] begin;
    static estatist est;
    static comEd ed;
    static mensa m;
   
    public mensa() throws SQLException {
        
        initComponents();
        
        c= new ConexaoMySQL();
        c.getConexaoMySQL();

    }
    

    public static void main(String args[]) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
       
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    try {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    } catch (UnsupportedLookAndFeelException ex) {
                        Logger.getLogger(mensa.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
            }

   
           java.awt.EventQueue.invokeLater(new Runnable() {
            
               
               public void run() {

                   try {
                        //ler ficheiro config
                        begin=conf();
                        est = new estatist();   
                        ed=new comEd(begin[2],begin[3],begin[4], est);                 
                        ed.iniciaCOM();    
                        comeca();
                   } catch (IOException ex) {
                       Logger.getLogger(mensa.class.getName()).log(Level.SEVERE, null, ex);
                   }
                    
                    
                    
              }
        });
    }

    
    public static void comeca() throws IOException{
                                      
                    try {
                        m = new mensa();
                        m.setSize(536, 438);
                        m.setTitle("Sistema Gestor de Edifício");
                        m.setLocationRelativeTo(null);
                        m.setVisible(true);
                        
                        
                
                        mapa=LeFile();

                       
                        a=new arranque(c,m,mapa, ultimoTemp, est, ed);
                        m.setArranque(a);
                        
                   } catch (SQLException ex) {
                       Logger.getLogger(mensa.class.getName()).log(Level.SEVERE, null, ex);
                   }
                    

                    a.comecar(begin);
    
           
    
    }
    
    

    public void escreverArea(String s){
        ScrollOutput.setAutoscrolls(true);
        int maximo = ScrollOutput.getVerticalScrollBar().getMaximum();
        ScrollOutput.getViewport().setViewPosition(new Point(0,maximo));
        Output.append(s);        
    }

    public void setArranque(arranque aa){
        a=aa;
    }
    
    public arranque getArranque(){
        return a;
    }
    
    public void Aviso(String s){
            JOptionPane.showMessageDialog(null,s, "Sistema Gestor de Edifício",JOptionPane.INFORMATION_MESSAGE);
            /*sensor x ligado do concentrador*/
            /*sensor x desligado do concentrador*/
            /*concentrador x desconectado*/
            /*concentrador x conectado*/    
    }

    
    //CONF feita no txt
    public static String[] conf() throws IOException{    
                String arquivoCSV = "conf.txt";
                BufferedReader br = null;
                String linha = "";
                String csvDivisor = ";";
                String[] begin= new String[7];

                try {
                    br = new BufferedReader(new FileReader(arquivoCSV));

                    while ((linha = br.readLine()) != null) {
                        begin = linha.split(csvDivisor); 
                    }
                    //g.atualiza("[COM:" + begin[0] +" , Baud-rate:" + begin[1] + " , PA:" + Integer.parseInt(begin[2]) +" microssegundos"+ " , PM:" + Integer.parseInt(begin[3]) +" milissegundos"+ " , NS:" + begin[4]+  " , Porta_sockets:" + Integer.parseInt(begin[5]) + " , IP:" + begin[6] + "]");

                } catch (FileNotFoundException e) {
                    //g.atualiza("Erro: falha na leitura do ficheiro de conf!");
                } catch (IOException e) {
                    //g.atualiza("Erro: falha na leitura do ficheiro de conf!");
                } finally {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                               //g.atualiza("Erro: falha na leitura do ficheiro de conf!");
                        }
                    }
                }
            return begin;
    }
    
    public static TreeMap<Long, TreeMap<Integer, Double>> LeFile() throws IOException{
            
            File arquivo = new File("ESTAT.csv");
            TreeMap<Integer, Double> list=new  TreeMap<Integer, Double> ();
            TreeMap<Long, TreeMap<Integer, Double>> map=new TreeMap<Long, TreeMap<Integer, Double>> ();
            
            
                if (!arquivo.exists()) {     
                    arquivo.createNewFile();
                    for(int i=0;i<3;i++){
                        list.put(i+1,(double)0);
                    }
                    map.put((long)0,list);                 
                }
                else{

                    BufferedReader StrR = new BufferedReader(new FileReader(arquivo));

                    String Str;
                    String[] TableLine;

                    //ler cada linha do file
                    while((Str = StrR.readLine())!= null){
                        
                        //Aqui usamos o método split que divide a linha lida em um array de String
                        
                        //passando como parametro o divisor ";".
                        TableLine = Str.split(";");

                       
                        list= new TreeMap<Integer, Double>();
                        
                        int i=0; 
                        int area=0;
                        long time=0;
                                                
                        //imprimie cada parametro do array de String
                        for (String cell : TableLine) {  
                            
                            if(i==0){
                               time=Long.parseLong(cell); 
                               ultimoTemp=time;
                            }                  
                            else if(i%2==0){  //é valor e posso adicionar
                                cell=cell.replaceAll( "," , "." );
                                double valorDouble = Double.parseDouble(cell);
                                list.put(area,valorDouble);
                                
                            }
                            else if (i%2!=0){ //é area
                                area=Integer.parseInt(cell);
                            }
                            
                            i++;

                        }

                        map.put(time,list);                
                        
               
                    }
                    
                 
                } 
                
                /*for(Long l:map.keySet()){
                    TreeMap<Integer, Double> ll= new TreeMap<Integer, Double>();
                    ll=map.get(l);
                    System.out.println("\n "+l);
                    for(int i:ll.keySet()){
                        System.out.print(i+";"+ll.get(i)+";");
                    }
  
                }*/
                
                 return map; 
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Consumos = new javax.swing.JButton();
        ScrollOutput = new javax.swing.JScrollPane();
        ScrollOutput = new javax.swing.JScrollPane();
        Output = new javax.swing.JTextArea();
        SensoresAtivos = new javax.swing.JButton();
        Custos = new javax.swing.JButton();
        BotaoStartStop = new javax.swing.JToggleButton();
        Fundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 400));
        getContentPane().setLayout(null);

        Consumos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/iconlampada.png"))); // NOI18N
        Consumos.setText("Consumos mensais");
        Consumos.setToolTipText("");
        Consumos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsumosActionPerformed(evt);
            }
        });
        getContentPane().add(Consumos);
        Consumos.setBounds(30, 140, 180, 40);

        Output.setEditable(false);
        Output.setBackground(new java.awt.Color(239, 239, 239));
        Output.setMinimumSize(new java.awt.Dimension(1, 22));
        ScrollOutput.setViewportView(Output);

        getContentPane().add(ScrollOutput);
        ScrollOutput.setBounds(240, 20, 250, 360);

        SensoresAtivos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icon2.png"))); // NOI18N
        SensoresAtivos.setText("Estado dos sensores");
        SensoresAtivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SensoresAtivosActionPerformed(evt);
            }
        });
        getContentPane().add(SensoresAtivos);
        SensoresAtivos.setBounds(30, 70, 180, 40);

        Custos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icondinheiro1.png"))); // NOI18N
        Custos.setText("Custos");
        Custos.setToolTipText("");
        Custos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustosActionPerformed(evt);
            }
        });
        getContentPane().add(Custos);
        Custos.setBounds(60, 210, 120, 40);

        BotaoStartStop.setBackground(new java.awt.Color(255, 149, 149));
        BotaoStartStop.setSelected(true);
        BotaoStartStop.setText("STOP");
        BotaoStartStop.setActionCommand("START/STOP");
        BotaoStartStop.setAutoscrolls(true);
        BotaoStartStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoStartStopActionPerformed(evt);
            }
        });
        getContentPane().add(BotaoStartStop);
        BotaoStartStop.setBounds(20, 310, 210, 30);

        Fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/factura-luz.png"))); // NOI18N
        Fundo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Fundo.setMaximumSize(new java.awt.Dimension(500, 400));
        Fundo.setMinimumSize(new java.awt.Dimension(500, 400));
        Fundo.setPreferredSize(new java.awt.Dimension(500, 400));
        getContentPane().add(Fundo);
        Fundo.setBounds(0, 0, 530, 400);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void SensoresAtivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SensoresAtivosActionPerformed
        SensoresAtivos s=new SensoresAtivos(a.getConc(),a);            
        s.setSize(470, 230);
        s.setTitle("Sensores Ativos");
        s.setVisible(true);
       
    }//GEN-LAST:event_SensoresAtivosActionPerformed
    
    private void CustosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustosActionPerformed
        Custos c=new Custos(a);            
        c.setSize(350, 270);
        c.setTitle("Custos");
        c.setVisible(true);

        a.acumUltimoMes(c);
        a.acumUltimaHora(c);
        a.acumUltima24H(c);
        a.ultimos_PM(c);
        
    }//GEN-LAST:event_CustosActionPerformed

    private void BotaoStartStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoStartStopActionPerformed
        if(BotaoStartStop.isSelected()){
            BotaoStartStop.setText("STOP");
            BotaoStartStop.setBackground(new java.awt.Color(255, 149, 149));   
            a.acorda();  
        }
        else{
            BotaoStartStop.setText("START");
            BotaoStartStop.setBackground(new java.awt.Color(174, 255, 174));    
            a.acorda();            
        }

    }//GEN-LAST:event_BotaoStartStopActionPerformed

    private void ConsumosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsumosActionPerformed
        
        Consumos c=new Consumos();
        c.setSize(350, 270);
        c.setTitle("Consumos");
        c.setVisible(true);
            
        a.mostrarCons(c);
    }//GEN-LAST:event_ConsumosActionPerformed

   
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton BotaoStartStop;
    private javax.swing.JButton Consumos;
    private javax.swing.JButton Custos;
    private javax.swing.JLabel Fundo;
    public javax.swing.JTextArea Output;
    private javax.swing.JScrollPane ScrollOutput;
    private javax.swing.JButton SensoresAtivos;
    // End of variables declaration//GEN-END:variables


  
}

    



