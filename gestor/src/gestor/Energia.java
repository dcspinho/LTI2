
package gestor;

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;



public class Energia extends javax.swing.JFrame{
    
    arranque a;
    
    public Energia() {
        initComponents();
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        OutputUltimoPM = new javax.swing.JTextArea();
        UltimoPM = new javax.swing.JButton();
        UltimaHora = new javax.swing.JButton();
        Media = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        OutputUltimaHora = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        OutputMedia = new javax.swing.JTextArea();
        Fundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setFocusCycleRoot(false);
        setMinimumSize(new java.awt.Dimension(475, 318));
        getContentPane().setLayout(null);

        jScrollPane1 =  new JScrollPane(OutputUltimoPM,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        OutputUltimoPM.setEditable(false);
        OutputUltimoPM.setBackground(new java.awt.Color(239, 239, 239));
        OutputUltimoPM.setColumns(1);
        OutputUltimoPM.setRows(1);
        OutputUltimoPM.setAutoscrolls(true);
        jScrollPane1.setViewportView(OutputUltimoPM);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(200, 60, 230, 40);

        UltimoPM.setText("Último PM");
        UltimoPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UltimoPMActionPerformed(evt);
            }
        });
        getContentPane().add(UltimoPM);
        UltimoPM.setBounds(40, 50, 110, 50);

        UltimaHora.setText("Acumulado na última hora");
        UltimaHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UltimaHoraActionPerformed(evt);
            }
        });
        getContentPane().add(UltimaHora);
        UltimaHora.setBounds(10, 120, 180, 50);

        Media.setText("Valor médio");
        Media.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MediaActionPerformed(evt);
            }
        });
        getContentPane().add(Media);
        Media.setBounds(40, 190, 110, 50);

        jScrollPane2 =  new JScrollPane(OutputUltimaHora,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        OutputUltimaHora.setEditable(false);
        OutputUltimaHora.setBackground(new java.awt.Color(239, 239, 239));
        OutputUltimaHora.setColumns(10);
        OutputUltimaHora.setRows(1);
        OutputUltimaHora.setAutoscrolls(false);
        jScrollPane2.setViewportView(OutputUltimaHora);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(200, 130, 230, 40);

        jScrollPane3 =  new JScrollPane(OutputMedia,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        OutputMedia.setEditable(false);
        OutputMedia.setBackground(new java.awt.Color(239, 239, 239));
        OutputMedia.setColumns(10);
        OutputMedia.setRows(1);
        OutputMedia.setAutoscrolls(false);
        jScrollPane3.setViewportView(OutputMedia);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(200, 200, 230, 40);

        Fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/luz2.jpg"))); // NOI18N
        Fundo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Fundo.setMaximumSize(new java.awt.Dimension(475, 296));
        Fundo.setMinimumSize(new java.awt.Dimension(475, 296));
        Fundo.setPreferredSize(new java.awt.Dimension(475, 296));
        getContentPane().add(Fundo);
        Fundo.setBounds(-20, -10, 490, 290);
    }// </editor-fold>//GEN-END:initComponents

    private void UltimoPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UltimoPMActionPerformed
        UltimoPM.setEnabled(false);
        OutputUltimoPM.append("Valor Ultimo PM : ");
    }//GEN-LAST:event_UltimoPMActionPerformed

    private void UltimaHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UltimaHoraActionPerformed
        UltimaHora.setEnabled(false);
        OutputUltimaHora.append("Valor Ultima Hora : ");
    }//GEN-LAST:event_UltimaHoraActionPerformed

    private void MediaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MediaActionPerformed
        Media.setEnabled(false);
        OutputMedia.append("Valor médio : ");   
    }//GEN-LAST:event_MediaActionPerformed

   
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fundo;
    private javax.swing.JButton Media;
    private javax.swing.JTextArea OutputMedia;
    private javax.swing.JTextArea OutputUltimaHora;
    private javax.swing.JTextArea OutputUltimoPM;
    private javax.swing.JButton UltimaHora;
    private javax.swing.JButton UltimoPM;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables

   
  
}
//}
    



