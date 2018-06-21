
package gestor;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;




public class Consumos extends javax.swing.JFrame{
    
    arranque a;

    public Consumos(arranque aa) {
        initComponents();
        anual.setEnabled(false);
        anual.setForeground(Color.BLACK);
        Edificio1.setEnabled(false);
        Edificio1.setForeground(Color.BLACK);
        Areas.setEnabled(false);
        Areas.setForeground(Color.BLACK);
        a=aa;
    }
    
    
    public void addMes(String s){
        meses.addItem(s);
    }
    
    
    public void escrever(ArrayList<String> lista){
        for(String s:lista){
            ScrollOutputArea.setAutoscrolls(true);
            int maximoA = ScrollOutputArea.getVerticalScrollBar().getMaximum();
            ScrollOutputArea.getViewport().setViewPosition(new Point(0,maximoA));
            OutputArea.append(s);
        } 
       
    }
    
    
    
    public void escrever_edif(String str){       
        Outout2Edificio.setText(str+" kWh");        
    }
    
    public void escrever_anual(String str){       
        Outout2anual.setText(str+" kWh");        
    }
   
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Outout2Edificio = new javax.swing.JLabel();
        ScrollOutputArea = new javax.swing.JScrollPane();
        OutputArea = new javax.swing.JTextArea();
        meses = new javax.swing.JComboBox<>();
        Outout2anual = new javax.swing.JLabel();
        OutputAnual = new javax.swing.JTextArea();
        anual = new javax.swing.JButton();
        Edificio1 = new javax.swing.JButton();
        refr = new javax.swing.JLabel();
        OutputEdificio = new javax.swing.JTextArea();
        Areas = new javax.swing.JButton();
        Fundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(375, 410));
        getContentPane().setLayout(null);

        Outout2Edificio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(Outout2Edificio);
        Outout2Edificio.setBounds(150, 130, 140, 30);

        OutputArea.setEditable(false);
        OutputArea.setBackground(new java.awt.Color(239, 239, 239));
        OutputArea.setColumns(10);
        OutputArea.setRows(5);
        ScrollOutputArea.setViewportView(OutputArea);

        getContentPane().add(ScrollOutputArea);
        ScrollOutputArea.setBounds(120, 180, 200, 160);

        meses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mesesActionPerformed(evt);
            }
        });
        getContentPane().add(meses);
        meses.setBounds(150, 30, 140, 30);

        Outout2anual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(Outout2anual);
        Outout2anual.setBounds(150, 80, 140, 30);

        OutputAnual.setEditable(false);
        OutputAnual.setBackground(new java.awt.Color(239, 239, 239));
        OutputAnual.setColumns(1);
        OutputAnual.setRows(1);
        OutputAnual.setAutoscrolls(true);
        getContentPane().add(OutputAnual);
        OutputAnual.setBounds(150, 80, 140, 30);

        anual.setBackground(new java.awt.Color(255, 102, 0));
        anual.setText("Anual");
        anual.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(anual);
        anual.setBounds(20, 80, 90, 30);

        Edificio1.setBackground(new java.awt.Color(255, 102, 0));
        Edificio1.setText("Edifício");
        Edificio1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(Edificio1);
        Edificio1.setBounds(20, 130, 90, 30);

        refr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/enter.png"))); // NOI18N
        refr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refrMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                refrMouseReleased(evt);
            }
        });
        getContentPane().add(refr);
        refr.setBounds(310, 20, 50, 50);

        OutputEdificio.setEditable(false);
        OutputEdificio.setBackground(new java.awt.Color(239, 239, 239));
        OutputEdificio.setColumns(1);
        OutputEdificio.setRows(1);
        OutputEdificio.setAutoscrolls(true);
        getContentPane().add(OutputEdificio);
        OutputEdificio.setBounds(150, 130, 140, 30);

        Areas.setBackground(new java.awt.Color(255, 102, 0));
        Areas.setText("Áreas");
        Areas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(Areas);
        Areas.setBounds(20, 180, 90, 30);

        Fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/luz2.jpg"))); // NOI18N
        Fundo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Fundo.setMaximumSize(new java.awt.Dimension(350, 270));
        Fundo.setMinimumSize(new java.awt.Dimension(350, 270));
        Fundo.setPreferredSize(new java.awt.Dimension(350, 270));
        getContentPane().add(Fundo);
        Fundo.setBounds(-150, -80, 660, 450);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mesesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mesesActionPerformed

        
    }//GEN-LAST:event_mesesActionPerformed

    private void refrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refrMouseClicked
        OutputArea.setText("");
        Outout2Edificio.setText("");
        Outout2anual.setText("");
        
        String s = (String) meses.getSelectedItem();
        a.consumosDoMes(this,s);
        a.mostrarCons(this);
        
    }//GEN-LAST:event_refrMouseClicked

    private void refrMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refrMouseReleased
       
        
        
    }//GEN-LAST:event_refrMouseReleased

   
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Areas;
    private javax.swing.JButton Edificio1;
    private javax.swing.JLabel Fundo;
    private javax.swing.JLabel Outout2Edificio;
    private javax.swing.JLabel Outout2anual;
    private javax.swing.JTextArea OutputAnual;
    private javax.swing.JTextArea OutputArea;
    private javax.swing.JTextArea OutputEdificio;
    private javax.swing.JScrollPane ScrollOutputArea;
    private javax.swing.JButton anual;
    private javax.swing.JComboBox<String> meses;
    private javax.swing.JLabel refr;
    // End of variables declaration//GEN-END:variables

   
  
}

    



