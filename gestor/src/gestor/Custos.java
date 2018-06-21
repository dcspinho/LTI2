
package gestor;

import java.awt.Color;
import java.awt.Point;
import javax.swing.JLabel;


public class Custos extends javax.swing.JFrame{
    
    arranque a;

    public Custos(arranque aa) {
        initComponents();
        UltimaHora.setEnabled(false);
        UltimaHora.setForeground(Color.BLACK);
        Ultimas24Horas.setEnabled(false);
        Ultimas24Horas.setForeground(Color.BLACK);
        EnergiaConsumida.setEnabled(false);
        EnergiaConsumida.setForeground(Color.BLACK);
        UltimoMes.setEnabled(false);
        UltimoMes.setForeground(Color.BLACK);
        Custo.setEnabled(false);
        Custo.setForeground(Color.BLACK);
        UltimoPM.setEnabled(false);
        UltimoPM.setForeground(Color.BLACK);
        
        a=aa;
    }
    
    
    public void escreverEnergiaUltimoMes(String s){
        OutputEnergiaUltimoMes.setHorizontalAlignment(JLabel.CENTER);
        OutputEnergiaUltimoMes.setVerticalAlignment(JLabel.CENTER);
        OutputEnergiaUltimoMes.setText(s);   
    }
    
    public void escreverCustoUltimoMes(String s){
        OutputCustoUltimoMes.setHorizontalAlignment(JLabel.CENTER);
        OutputCustoUltimoMes.setVerticalAlignment(JLabel.CENTER);
        OutputCustoUltimoMes.setText(s);        
    }
    
    
    
    public void escreverEnergiaUltimaHora(String s){
        OutputEnergiaUltimaHora.setHorizontalAlignment(JLabel.CENTER);
        OutputEnergiaUltimaHora.setVerticalAlignment(JLabel.CENTER);
        OutputEnergiaUltimaHora.setText(s);   
    }
    
    public void escreverCustoUltimaHora(String s){
        OutputCustoUltimaHora.setHorizontalAlignment(JLabel.CENTER);
        OutputCustoUltimaHora.setVerticalAlignment(JLabel.CENTER);
        OutputCustoUltimaHora.setText(s);        
    }
    
    public void escreverEnergiaUltima24Hora(String s){
        OutputEnergiaUltima24Hora.setHorizontalAlignment(JLabel.CENTER);
        OutputEnergiaUltima24Hora.setVerticalAlignment(JLabel.CENTER);
        OutputEnergiaUltima24Hora.setText(s);        
    }
    
    public void escreverCustoUltima24Hora(String s){
        OutputCustoUltima24Hora.setHorizontalAlignment(JLabel.CENTER);
        OutputCustoUltima24Hora.setVerticalAlignment(JLabel.CENTER);
        OutputCustoUltima24Hora.setText(s);        
    }

    public void escreverEnergiaUltimoPM(String s){
        ScrollEnergiaUltimoPM.setAutoscrolls(true);
        int maximo = ScrollEnergiaUltimoPM.getVerticalScrollBar().getMaximum();
        ScrollEnergiaUltimoPM.getViewport().setViewPosition(new Point(0,maximo));
        AuxiliarEnergiaUltimoPM.append(s);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        OutputCustoUltima24Hora = new javax.swing.JLabel();
        OutputEnergiaUltima24Hora = new javax.swing.JLabel();
        OutputEnergiaUltimaHora = new javax.swing.JLabel();
        OutputEnergiaUltimoMes = new javax.swing.JLabel();
        ScrollEnergiaUltimoMes = new javax.swing.JScrollPane();
        AuxiliarCustoUltimaHora1 = new javax.swing.JTextArea();
        OutputCustoUltimoMes = new javax.swing.JLabel();
        ScrollCustoUltimoMes = new javax.swing.JScrollPane();
        AuxiliarEnergiaUltimaHora1 = new javax.swing.JTextArea();
        OutputCustoUltimaHora = new javax.swing.JLabel();
        UltimoMes = new javax.swing.JButton();
        Ultimas24Horas = new javax.swing.JButton();
        UltimaHora = new javax.swing.JButton();
        refr = new javax.swing.JLabel();
        EnergiaConsumida = new javax.swing.JButton();
        Custo = new javax.swing.JButton();
        ScrollEnergiaUltimaHora = new javax.swing.JScrollPane();
        AuxiliarCustoUltimaHora = new javax.swing.JTextArea();
        ScrollEnergiaUltima24Hora = new javax.swing.JScrollPane();
        AuxiliarEnergiaUltima24Hora = new javax.swing.JTextArea();
        ScrollCustoUltima24Hora = new javax.swing.JScrollPane();
        AuxiliarCustoUltima24Hora = new javax.swing.JTextArea();
        ScrollCustoUltimaHora = new javax.swing.JScrollPane();
        AuxiliarEnergiaUltimaHora = new javax.swing.JTextArea();
        UltimoPM = new javax.swing.JButton();
        ScrollEnergiaUltimoPM = new javax.swing.JScrollPane();
        AuxiliarEnergiaUltimoPM = new javax.swing.JTextArea();
        OutputEnergiaUltimaHora5 = new javax.swing.JLabel();
        Fundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(570, 400));
        getContentPane().setLayout(null);
        getContentPane().add(OutputCustoUltima24Hora);
        OutputCustoUltima24Hora.setBounds(360, 130, 170, 30);
        getContentPane().add(OutputEnergiaUltima24Hora);
        OutputEnergiaUltima24Hora.setBounds(180, 130, 170, 30);
        getContentPane().add(OutputEnergiaUltimaHora);
        OutputEnergiaUltimaHora.setBounds(180, 170, 170, 30);
        getContentPane().add(OutputEnergiaUltimoMes);
        OutputEnergiaUltimoMes.setBounds(180, 90, 170, 30);

        ScrollEnergiaUltimoMes.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollEnergiaUltimoMes.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        AuxiliarCustoUltimaHora1.setEditable(false);
        AuxiliarCustoUltimaHora1.setBackground(new java.awt.Color(239, 239, 239));
        AuxiliarCustoUltimaHora1.setColumns(10);
        AuxiliarCustoUltimaHora1.setRows(5);
        ScrollEnergiaUltimoMes.setViewportView(AuxiliarCustoUltimaHora1);

        getContentPane().add(ScrollEnergiaUltimoMes);
        ScrollEnergiaUltimoMes.setBounds(180, 90, 170, 30);
        getContentPane().add(OutputCustoUltimoMes);
        OutputCustoUltimoMes.setBounds(360, 90, 170, 30);

        ScrollCustoUltimoMes.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollCustoUltimoMes.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        AuxiliarEnergiaUltimaHora1.setEditable(false);
        AuxiliarEnergiaUltimaHora1.setBackground(new java.awt.Color(239, 239, 239));
        AuxiliarEnergiaUltimaHora1.setColumns(10);
        AuxiliarEnergiaUltimaHora1.setRows(5);
        ScrollCustoUltimoMes.setViewportView(AuxiliarEnergiaUltimaHora1);

        getContentPane().add(ScrollCustoUltimoMes);
        ScrollCustoUltimoMes.setBounds(360, 90, 170, 30);
        getContentPane().add(OutputCustoUltimaHora);
        OutputCustoUltimaHora.setBounds(360, 170, 170, 30);

        UltimoMes.setBackground(new java.awt.Color(255, 102, 0));
        UltimoMes.setText("Último mes");
        UltimoMes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(UltimoMes);
        UltimoMes.setBounds(20, 90, 140, 30);

        Ultimas24Horas.setBackground(new java.awt.Color(255, 102, 0));
        Ultimas24Horas.setText("Últimas 24 horas");
        Ultimas24Horas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(Ultimas24Horas);
        Ultimas24Horas.setBounds(20, 130, 140, 30);

        UltimaHora.setBackground(new java.awt.Color(255, 102, 0));
        UltimaHora.setText("Última hora");
        UltimaHora.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(UltimaHora);
        UltimaHora.setBounds(20, 170, 140, 30);

        refr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/refr.png"))); // NOI18N
        refr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refrMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                refrMouseReleased(evt);
            }
        });
        getContentPane().add(refr);
        refr.setBounds(430, 260, 40, 40);

        EnergiaConsumida.setBackground(new java.awt.Color(255, 102, 0));
        EnergiaConsumida.setText("Energia consumida");
        EnergiaConsumida.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(EnergiaConsumida);
        EnergiaConsumida.setBounds(180, 40, 170, 30);

        Custo.setBackground(new java.awt.Color(255, 102, 0));
        Custo.setText("Custo");
        Custo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(Custo);
        Custo.setBounds(360, 40, 170, 30);

        ScrollEnergiaUltimaHora.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollEnergiaUltimaHora.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        AuxiliarCustoUltimaHora.setEditable(false);
        AuxiliarCustoUltimaHora.setBackground(new java.awt.Color(239, 239, 239));
        AuxiliarCustoUltimaHora.setColumns(10);
        AuxiliarCustoUltimaHora.setRows(5);
        ScrollEnergiaUltimaHora.setViewportView(AuxiliarCustoUltimaHora);

        getContentPane().add(ScrollEnergiaUltimaHora);
        ScrollEnergiaUltimaHora.setBounds(360, 170, 170, 30);

        ScrollEnergiaUltima24Hora.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollEnergiaUltima24Hora.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        AuxiliarEnergiaUltima24Hora.setEditable(false);
        AuxiliarEnergiaUltima24Hora.setBackground(new java.awt.Color(239, 239, 239));
        AuxiliarEnergiaUltima24Hora.setColumns(10);
        AuxiliarEnergiaUltima24Hora.setRows(5);
        ScrollEnergiaUltima24Hora.setViewportView(AuxiliarEnergiaUltima24Hora);

        getContentPane().add(ScrollEnergiaUltima24Hora);
        ScrollEnergiaUltima24Hora.setBounds(180, 130, 170, 30);

        ScrollCustoUltima24Hora.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollCustoUltima24Hora.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        AuxiliarCustoUltima24Hora.setEditable(false);
        AuxiliarCustoUltima24Hora.setBackground(new java.awt.Color(239, 239, 239));
        AuxiliarCustoUltima24Hora.setColumns(10);
        AuxiliarCustoUltima24Hora.setRows(5);
        ScrollCustoUltima24Hora.setViewportView(AuxiliarCustoUltima24Hora);

        getContentPane().add(ScrollCustoUltima24Hora);
        ScrollCustoUltima24Hora.setBounds(360, 130, 170, 30);

        ScrollCustoUltimaHora.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollCustoUltimaHora.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        AuxiliarEnergiaUltimaHora.setEditable(false);
        AuxiliarEnergiaUltimaHora.setBackground(new java.awt.Color(239, 239, 239));
        AuxiliarEnergiaUltimaHora.setColumns(10);
        AuxiliarEnergiaUltimaHora.setRows(5);
        ScrollCustoUltimaHora.setViewportView(AuxiliarEnergiaUltimaHora);

        getContentPane().add(ScrollCustoUltimaHora);
        ScrollCustoUltimaHora.setBounds(180, 170, 170, 30);

        UltimoPM.setBackground(new java.awt.Color(255, 102, 0));
        UltimoPM.setText("Último PM");
        UltimoPM.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(UltimoPM);
        UltimoPM.setBounds(20, 210, 140, 30);

        ScrollEnergiaUltimoPM.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        ScrollEnergiaUltimoPM.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        AuxiliarEnergiaUltimoPM.setEditable(false);
        AuxiliarEnergiaUltimoPM.setBackground(new java.awt.Color(239, 239, 239));
        AuxiliarEnergiaUltimoPM.setRows(5);
        ScrollEnergiaUltimoPM.setViewportView(AuxiliarEnergiaUltimoPM);

        getContentPane().add(ScrollEnergiaUltimoPM);
        ScrollEnergiaUltimoPM.setBounds(180, 210, 170, 130);
        getContentPane().add(OutputEnergiaUltimaHora5);
        OutputEnergiaUltimaHora5.setBounds(140, 420, 270, 40);

        Fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/luz2.jpg"))); // NOI18N
        Fundo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Fundo.setMaximumSize(new java.awt.Dimension(350, 270));
        Fundo.setMinimumSize(new java.awt.Dimension(350, 270));
        Fundo.setPreferredSize(new java.awt.Dimension(570, 180));
        Fundo.setRequestFocusEnabled(false);
        getContentPane().add(Fundo);
        Fundo.setBounds(-80, -210, 640, 610);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void refrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refrMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_refrMouseClicked

    private void refrMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refrMouseReleased
        OutputCustoUltima24Hora.setText("");
        OutputCustoUltimaHora.setText("");
        OutputCustoUltimoMes.setText("");
        OutputEnergiaUltima24Hora.setText("");
        OutputEnergiaUltimaHora.setText("");
        OutputEnergiaUltimoMes.setText("");
        AuxiliarEnergiaUltimoPM.setText("");
        
        a.acumUltimoMes(this);
        a.acumUltimaHora(this);
        a.acumUltima24H(this);
        a.ultimos_PM(this);
    }//GEN-LAST:event_refrMouseReleased

   
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea AuxiliarCustoUltima24Hora;
    private javax.swing.JTextArea AuxiliarCustoUltimaHora;
    private javax.swing.JTextArea AuxiliarCustoUltimaHora1;
    private javax.swing.JTextArea AuxiliarEnergiaUltima24Hora;
    private javax.swing.JTextArea AuxiliarEnergiaUltimaHora;
    private javax.swing.JTextArea AuxiliarEnergiaUltimaHora1;
    private javax.swing.JTextArea AuxiliarEnergiaUltimoPM;
    private javax.swing.JButton Custo;
    private javax.swing.JButton EnergiaConsumida;
    private javax.swing.JLabel Fundo;
    private javax.swing.JLabel OutputCustoUltima24Hora;
    private javax.swing.JLabel OutputCustoUltimaHora;
    private javax.swing.JLabel OutputCustoUltimoMes;
    private javax.swing.JLabel OutputEnergiaUltima24Hora;
    private javax.swing.JLabel OutputEnergiaUltimaHora;
    private javax.swing.JLabel OutputEnergiaUltimaHora5;
    private javax.swing.JLabel OutputEnergiaUltimoMes;
    private javax.swing.JScrollPane ScrollCustoUltima24Hora;
    private javax.swing.JScrollPane ScrollCustoUltimaHora;
    private javax.swing.JScrollPane ScrollCustoUltimoMes;
    private javax.swing.JScrollPane ScrollEnergiaUltima24Hora;
    private javax.swing.JScrollPane ScrollEnergiaUltimaHora;
    private javax.swing.JScrollPane ScrollEnergiaUltimoMes;
    private javax.swing.JScrollPane ScrollEnergiaUltimoPM;
    private javax.swing.JButton UltimaHora;
    private javax.swing.JButton Ultimas24Horas;
    private javax.swing.JButton UltimoMes;
    private javax.swing.JButton UltimoPM;
    private javax.swing.JLabel refr;
    // End of variables declaration//GEN-END:variables

   
  
}

    



