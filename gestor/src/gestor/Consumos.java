
package gestor;

import com.sun.glass.events.KeyEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentListener;
import javax.swing.text.StyledDocument;



public class Consumos extends javax.swing.JFrame{
    
    arranque a;

    public Consumos() {
        initComponents();
        Edificio.setEnabled(false);
        Edificio.setForeground(Color.BLACK);
        Areas.setEnabled(false);
        Areas.setForeground(Color.BLACK);
    }
    
    public void escrever(ArrayList<String> lista, String str){
        for(String s:lista){
            ScrollOutputArea.setAutoscrolls(true);
            int maximoA = ScrollOutputArea.getVerticalScrollBar().getMaximum();
            ScrollOutputArea.getViewport().setViewPosition(new Point(0,maximoA));
            OutputArea.append(s);
        } 

        Outout2Edificio.setHorizontalAlignment(JLabel.CENTER);
        Outout2Edificio.setVerticalAlignment(JLabel.CENTER);
        Outout2Edificio.setText(str+" kWh");        
    }
   
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Outout2Edificio = new javax.swing.JLabel();
        ScrollOutputArea = new javax.swing.JScrollPane();
        OutputArea = new javax.swing.JTextArea();
        ScrollOutputEdificio = new javax.swing.JScrollPane();
        OutputEdificio = new javax.swing.JTextArea();
        Edificio = new javax.swing.JButton();
        Areas = new javax.swing.JButton();
        Fundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(375, 410));
        getContentPane().setLayout(null);
        getContentPane().add(Outout2Edificio);
        Outout2Edificio.setBounds(40, 310, 280, 40);

        OutputArea.setEditable(false);
        OutputArea.setBackground(new java.awt.Color(239, 239, 239));
        OutputArea.setColumns(10);
        OutputArea.setRows(5);
        ScrollOutputArea.setViewportView(OutputArea);

        getContentPane().add(ScrollOutputArea);
        ScrollOutputArea.setBounds(40, 50, 280, 220);

        ScrollOutputArea =  new JScrollPane(OutputEdificio,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        OutputEdificio.setEditable(false);
        OutputEdificio.setBackground(new java.awt.Color(239, 239, 239));
        OutputEdificio.setColumns(1);
        OutputEdificio.setRows(1);
        OutputEdificio.setAutoscrolls(true);
        ScrollOutputEdificio.setViewportView(OutputEdificio);

        getContentPane().add(ScrollOutputEdificio);
        ScrollOutputEdificio.setBounds(40, 310, 280, 40);

        Edificio.setBackground(new java.awt.Color(255, 102, 0));
        Edificio.setText("Edifício");
        Edificio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(Edificio);
        Edificio.setBounds(110, 280, 150, 30);

        Areas.setBackground(new java.awt.Color(255, 102, 0));
        Areas.setText("Áreas");
        Areas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(Areas);
        Areas.setBounds(110, 20, 150, 30);

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

   
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Areas;
    private javax.swing.JButton Edificio;
    private javax.swing.JLabel Fundo;
    private javax.swing.JLabel Outout2Edificio;
    private javax.swing.JTextArea OutputArea;
    private javax.swing.JTextArea OutputEdificio;
    private javax.swing.JScrollPane ScrollOutputArea;
    private javax.swing.JScrollPane ScrollOutputEdificio;
    // End of variables declaration//GEN-END:variables

   
  
}
//}
    



