
package gestor;

import java.awt.Color;
import java.awt.Point;
import javax.swing.JLabel;


public class Custos extends javax.swing.JFrame{
    
    arranque a;

    public Custos() {
        initComponents();
        UltimaHora.setEnabled(false);
        UltimaHora.setForeground(Color.BLACK);
        Ultimas24Horas.setEnabled(false);
        Ultimas24Horas.setForeground(Color.BLACK);
        EnergiaConsumida.setEnabled(false);
        EnergiaConsumida.setForeground(Color.BLACK);
        Custo.setEnabled(false);
        Custo.setForeground(Color.BLACK);
        UltimoPM.setEnabled(false);
        UltimoPM.setForeground(Color.BLACK);
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
        OutputCustoUltimaHora = new javax.swing.JLabel();
        Ultimas24Horas = new javax.swing.JButton();
        UltimaHora = new javax.swing.JButton();
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
        OutputCustoUltima24Hora.setBounds(360, 120, 170, 30);
        getContentPane().add(OutputEnergiaUltima24Hora);
        OutputEnergiaUltima24Hora.setBounds(180, 120, 170, 30);
        getContentPane().add(OutputEnergiaUltimaHora);
        OutputEnergiaUltimaHora.setBounds(180, 80, 170, 30);
        getContentPane().add(OutputCustoUltimaHora);
        OutputCustoUltimaHora.setBounds(360, 80, 170, 30);

        Ultimas24Horas.setBackground(new java.awt.Color(255, 102, 0));
        Ultimas24Horas.setText("Últimas 24 horas");
        Ultimas24Horas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(Ultimas24Horas);
        Ultimas24Horas.setBounds(20, 120, 140, 30);

        UltimaHora.setBackground(new java.awt.Color(255, 102, 0));
        UltimaHora.setText("Última hora");
        UltimaHora.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(UltimaHora);
        UltimaHora.setBounds(20, 80, 140, 30);

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
        ScrollEnergiaUltimaHora.setBounds(360, 80, 170, 30);

        ScrollEnergiaUltima24Hora.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollEnergiaUltima24Hora.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        AuxiliarEnergiaUltima24Hora.setEditable(false);
        AuxiliarEnergiaUltima24Hora.setBackground(new java.awt.Color(239, 239, 239));
        AuxiliarEnergiaUltima24Hora.setColumns(10);
        AuxiliarEnergiaUltima24Hora.setRows(5);
        ScrollEnergiaUltima24Hora.setViewportView(AuxiliarEnergiaUltima24Hora);

        getContentPane().add(ScrollEnergiaUltima24Hora);
        ScrollEnergiaUltima24Hora.setBounds(180, 120, 170, 30);

        ScrollCustoUltima24Hora.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollCustoUltima24Hora.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        AuxiliarCustoUltima24Hora.setEditable(false);
        AuxiliarCustoUltima24Hora.setBackground(new java.awt.Color(239, 239, 239));
        AuxiliarCustoUltima24Hora.setColumns(10);
        AuxiliarCustoUltima24Hora.setRows(5);
        ScrollCustoUltima24Hora.setViewportView(AuxiliarCustoUltima24Hora);

        getContentPane().add(ScrollCustoUltima24Hora);
        ScrollCustoUltima24Hora.setBounds(360, 120, 170, 30);

        ScrollCustoUltimaHora.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollCustoUltimaHora.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        AuxiliarEnergiaUltimaHora.setEditable(false);
        AuxiliarEnergiaUltimaHora.setBackground(new java.awt.Color(239, 239, 239));
        AuxiliarEnergiaUltimaHora.setColumns(10);
        AuxiliarEnergiaUltimaHora.setRows(5);
        ScrollCustoUltimaHora.setViewportView(AuxiliarEnergiaUltimaHora);

        getContentPane().add(ScrollCustoUltimaHora);
        ScrollCustoUltimaHora.setBounds(180, 80, 170, 30);

        UltimoPM.setBackground(new java.awt.Color(255, 102, 0));
        UltimoPM.setText("Último PM");
        UltimoPM.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(UltimoPM);
        UltimoPM.setBounds(20, 160, 140, 30);

        ScrollEnergiaUltimoPM.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        ScrollEnergiaUltimoPM.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        AuxiliarEnergiaUltimoPM.setEditable(false);
        AuxiliarEnergiaUltimoPM.setBackground(new java.awt.Color(239, 239, 239));
        AuxiliarEnergiaUltimoPM.setRows(5);
        ScrollEnergiaUltimoPM.setViewportView(AuxiliarEnergiaUltimoPM);

        getContentPane().add(ScrollEnergiaUltimoPM);
        ScrollEnergiaUltimoPM.setBounds(180, 160, 170, 130);
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

   
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea AuxiliarCustoUltima24Hora;
    private javax.swing.JTextArea AuxiliarCustoUltimaHora;
    private javax.swing.JTextArea AuxiliarEnergiaUltima24Hora;
    private javax.swing.JTextArea AuxiliarEnergiaUltimaHora;
    private javax.swing.JTextArea AuxiliarEnergiaUltimoPM;
    private javax.swing.JButton Custo;
    private javax.swing.JButton EnergiaConsumida;
    private javax.swing.JLabel Fundo;
    private javax.swing.JLabel OutputCustoUltima24Hora;
    private javax.swing.JLabel OutputCustoUltimaHora;
    private javax.swing.JLabel OutputEnergiaUltima24Hora;
    private javax.swing.JLabel OutputEnergiaUltimaHora;
    private javax.swing.JLabel OutputEnergiaUltimaHora5;
    private javax.swing.JScrollPane ScrollCustoUltima24Hora;
    private javax.swing.JScrollPane ScrollCustoUltimaHora;
    private javax.swing.JScrollPane ScrollEnergiaUltima24Hora;
    private javax.swing.JScrollPane ScrollEnergiaUltimaHora;
    private javax.swing.JScrollPane ScrollEnergiaUltimoPM;
    private javax.swing.JButton UltimaHora;
    private javax.swing.JButton Ultimas24Horas;
    private javax.swing.JButton UltimoPM;
    // End of variables declaration//GEN-END:variables

   
  
}
//}
    



