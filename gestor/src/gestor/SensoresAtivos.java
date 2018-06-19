
package gestor;


import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;




public class SensoresAtivos extends javax.swing.JFrame{
    
    arranque a;

    public SensoresAtivos() {
        initComponents();
        SensoresTransmitir.setEnabled(false);
        SensoresTransmitir.setForeground(Color.BLACK);
        SensoresAtivos.setEnabled(false);
        SensoresAtivos.setForeground(Color.BLACK);
    }

    public void escreverArea_hello(ArrayList<String> lista){
        
        for(String s:lista){
            ScrollOutputHello.setAutoscrolls(true);
            int maximoH = ScrollOutputHello.getVerticalScrollBar().getMaximum();
            ScrollOutputHello.getViewport().setViewPosition(new Point(0,maximoH));
            OutputHELLO.append(s);
        }        
    }
    
     public void escreverArea_start(ArrayList<String> lista){
        for(String s:lista){
            ScrollOutputStart.setAutoscrolls(true);
            int maximoS = ScrollOutputStart.getVerticalScrollBar().getMaximum();
            ScrollOutputStart.getViewport().setViewPosition(new Point(0,maximoS));
            OutputSTART.append(s);
        }        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ScrollOutputHello = new javax.swing.JScrollPane();
        OutputHELLO = new javax.swing.JTextArea();
        ScrollOutputStart = new javax.swing.JScrollPane();
        OutputSTART = new javax.swing.JTextArea();
        SensoresTransmitir = new javax.swing.JButton();
        SensoresAtivos = new javax.swing.JButton();
        naoseioquee = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(520, 325));
        getContentPane().setLayout(null);

        OutputHELLO.setEditable(false);
        OutputHELLO.setBackground(new java.awt.Color(239, 239, 239));
        OutputHELLO.setColumns(10);
        OutputHELLO.setRows(5);
        ScrollOutputHello.setViewportView(OutputHELLO);

        getContentPane().add(ScrollOutputHello);
        ScrollOutputHello.setBounds(260, 50, 220, 210);

        OutputSTART.setEditable(false);
        OutputSTART.setBackground(new java.awt.Color(239, 239, 239));
        OutputSTART.setColumns(10);
        OutputSTART.setRows(5);
        ScrollOutputStart.setViewportView(OutputSTART);

        getContentPane().add(ScrollOutputStart);
        ScrollOutputStart.setBounds(20, 50, 220, 210);

        SensoresTransmitir.setBackground(new java.awt.Color(255, 102, 0));
        SensoresTransmitir.setText("Solicitados no START");
        getContentPane().add(SensoresTransmitir);
        SensoresTransmitir.setBounds(300, 20, 150, 30);

        SensoresAtivos.setBackground(new java.awt.Color(255, 102, 0));
        SensoresAtivos.setText("Ativos");
        SensoresAtivos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(SensoresAtivos);
        SensoresAtivos.setBounds(60, 20, 150, 30);
        SensoresAtivos.getAccessibleContext().setAccessibleDescription("");

        naoseioquee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/luz2.jpg"))); // NOI18N
        naoseioquee.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        naoseioquee.setMaximumSize(new java.awt.Dimension(350, 270));
        naoseioquee.setMinimumSize(new java.awt.Dimension(350, 270));
        naoseioquee.setPreferredSize(new java.awt.Dimension(350, 270));
        getContentPane().add(naoseioquee);
        naoseioquee.setBounds(-80, -90, 580, 380);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

   
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea OutputHELLO;
    private javax.swing.JTextArea OutputSTART;
    private javax.swing.JScrollPane ScrollOutputHello;
    private javax.swing.JScrollPane ScrollOutputStart;
    private javax.swing.JButton SensoresAtivos;
    private javax.swing.JButton SensoresTransmitir;
    private javax.swing.JLabel naoseioquee;
    // End of variables declaration//GEN-END:variables

   
  
}
//}
    



