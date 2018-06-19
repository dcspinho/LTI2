
package gestor;


import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;



public class mensa extends javax.swing.JFrame{
    
    public arranque a;
    String user;
    

    public mensa(String u) {
        initComponents();
        this.user=u;
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
        Consumos.setText("Consumos");
        Consumos.setToolTipText("");
        Consumos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsumosActionPerformed(evt);
            }
        });
        getContentPane().add(Consumos);
        Consumos.setBounds(50, 140, 140, 40);

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
        SensoresAtivos s=new SensoresAtivos();            
        s.setSize(350, 270);
        s.setTitle("Sensores Ativos                Utilizador: "+user);
        s.setVisible(true);
       
        s.escreverArea_hello( a.listaSens_Conc_START());
        s.escreverArea_start(a.listaSens_Conc());
    }//GEN-LAST:event_SensoresAtivosActionPerformed
    
    private void CustosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustosActionPerformed
        Custos c=new Custos();            
        c.setSize(350, 270);
        c.setTitle("Custos        Utilizador: "+user);
        c.setVisible(true);

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
        c.setTitle("Consumos        Utilizador: "+user);
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

    



