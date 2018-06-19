
package gestor;

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
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


public class inicial extends javax.swing.JFrame {
    
    public String user;
    ConexaoMySQL c;
    TreeMap<Long, TreeMap<Integer, Double>> mapa;
    long ultimoTemp;
    static String[] begin;
    static estatist est;
    static comEd ed;
            
    public inicial() throws IOException {
        initComponents();
        c= new ConexaoMySQL();
        
        try {
            c.getConexaoMySQL();
        } catch (SQLException ex) {
             Logger.getLogger(inicial.class.getName()).log(Level.SEVERE, null, ex);
        }

         
    }

 
    public static void main(String args[]) throws SQLException {
       

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
   
           java.awt.EventQueue.invokeLater(new Runnable() {
            
               
               public void run() {
                
                         
                   try {
                       //ler ficheiro config
                       begin=conf();
                   } catch (IOException ex) {
                       Logger.getLogger(inicial.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   
                   
                est = new estatist();   
                ed=new comEd(begin[2],begin[3],begin[4], est);                 
                ed.iniciaCOM();
                   
                inicial ini;
                   try {
                       ini = new inicial();
                       ini.setSize(555, 385);
                       ini.setTitle("Sistema Gestor de Edifício");
                       ini.setVisible(true);
                       
                   } catch (IOException ex) {
                       Logger.getLogger(inicial.class.getName()).log(Level.SEVERE, null, ex);
                   }
                
                
                
                

                   
              }
        });
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
        
    
    
    
    
    public TreeMap<Long, TreeMap<Integer, Double>> LeFile() throws IOException{
            
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

        jPanel1 = new javax.swing.JPanel();
        Utilizador = new javax.swing.JTextField();
        Iniciar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setMaximumSize(new java.awt.Dimension(500, 400));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 400));
        jPanel1.setLayout(null);

        Utilizador.setBackground(new java.awt.Color(255, 255, 204));
        Utilizador.setForeground(new java.awt.Color(153, 153, 153));
        Utilizador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Utilizador.setText("UTILIZADOR");
        Utilizador.setCaretColor(new java.awt.Color(255, 255, 153));
        Utilizador.setSelectionColor(new java.awt.Color(255, 204, 102));
        Utilizador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                UtilizadorMousePressed(evt);
            }
        });
        Utilizador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UtilizadorActionPerformed(evt);
            }
        });
        Utilizador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UtilizadorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                UtilizadorKeyReleased(evt);
            }
        });
        jPanel1.add(Utilizador);
        Utilizador.setBounds(200, 90, 130, 30);

        Iniciar.setBackground(new java.awt.Color(255, 255, 153));
        Iniciar.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        Iniciar.setText("INICIAR");
        Iniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IniciarActionPerformed(evt);
            }
        });
        jPanel1.add(Iniciar);
        Iniciar.setBounds(200, 150, 130, 70);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/luz.jpg"))); // NOI18N
        jPanel1.add(jLabel3);
        jLabel3.setBounds(-10, -10, 600, 370);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void IniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IniciarActionPerformed

                String user=this.Utilizador.getText();
                
                if(user.equals("UTILIZADOR")){
                    JOptionPane.showMessageDialog(null,"    Insira o seu nome!", "Sistema Gestor de Edifício",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null,"              Bem-vindo "+user+" !", "Sistema Gestor de Edifício",JOptionPane.PLAIN_MESSAGE);
                    //System.out.println("Carregou no botão");
                    mensa m= new mensa(user);
                    setVisible(false);
                    m.setSize(536, 438);
                    m.setTitle("Sistema Gestor de Edifício        Utilizador: "+user);
                    m.setVisible(true);

                    
                    
                    SensoresAtivos sens=new SensoresAtivos();
                    
                    try {
                        mapa=LeFile();
                    } catch (IOException ex) {
                        Logger.getLogger(inicial.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                    arranque a=new arranque(c,m, sens,mapa, ultimoTemp, est, ed);
                    m.setArranque(a);
                    
                    
                    try {
                        a.comecar(begin);
                    } catch (IOException ex) {
                        Logger.getLogger(inicial.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
      
    }//GEN-LAST:event_IniciarActionPerformed

    private void UtilizadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UtilizadorKeyReleased
        //System.out.println("UtilizadorKeyReleased");
        if(Utilizador.getText().isEmpty()==true){
            Utilizador.setText("UTILIZADOR");
            Utilizador.setCaretPosition(0);
            Utilizador.setForeground(new java.awt.Color(204,204,204));
        }
    }//GEN-LAST:event_UtilizadorKeyReleased

    private void UtilizadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UtilizadorKeyPressed
        
        if(Utilizador.getForeground()!=Color.BLACK)
        {
            if(Utilizador.getText().equals("UTILIZADOR"))
            {
                Utilizador.setText("");
            }
        }
        
        Utilizador.setForeground(Color.BLACK);
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
               
                String user=this.Utilizador.getText();
                //System.out.println("Utilizador: "+user);
                
               
                if(user.equals("")){
                    JOptionPane.showMessageDialog(null,"    Insira o seu nome!", "Sistema Gestor de Edifício",JOptionPane.ERROR_MESSAGE);
                    Utilizador.setText("UTILIZADOR");
                    Utilizador.setForeground(Color.GRAY);
                }
                else{
                    JOptionPane.showMessageDialog(null,"\t\t\tBem-vindo "+user+" !", "Sistema Gestor de Edifício",JOptionPane.PLAIN_MESSAGE);
                    //System.out.println("Carregou no botão");
                    mensa m= new mensa(user);
                    setVisible(false);
                    m.setSize(536, 438);
                    m.setTitle("Sistema Gestor de Edifício        Utilizador: "+user);
                    m.setVisible(true);
                        
                    SensoresAtivos sens=new SensoresAtivos();
                    
                    try {
                        mapa=LeFile();
                    } catch (IOException ex) {
                        Logger.getLogger(inicial.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    arranque a=new arranque(c,m, sens,mapa, ultimoTemp, est, ed);
                    m.setArranque(a); 
                    
                                            
                    try {
                        a.comecar(begin);
                    } catch (IOException ex) {
                        Logger.getLogger(inicial.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        

                   }
        }
        
    }//GEN-LAST:event_UtilizadorKeyPressed

    private void UtilizadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UtilizadorActionPerformed
            
    }//GEN-LAST:event_UtilizadorActionPerformed

    private void UtilizadorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UtilizadorMousePressed
        Utilizador.setCaretPosition(0);
    }//GEN-LAST:event_UtilizadorMousePressed

   


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Iniciar;
    private javax.swing.JTextField Utilizador;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}