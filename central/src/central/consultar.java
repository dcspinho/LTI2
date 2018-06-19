
package central;

import java.sql.Connection;


public class consultar extends javax.swing.JFrame {
    Connection con;
    public consultar(Connection c) {
        initComponents();
        con=c;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ScrollOutput = new javax.swing.JScrollPane();
        ScrollOutput = new javax.swing.JScrollPane();
        Output = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();

        getContentPane().setLayout(null);

        Output.setEditable(false);
        Output.setBackground(new java.awt.Color(239, 239, 239));
        Output.setMinimumSize(new java.awt.Dimension(1, 22));
        ScrollOutput.setViewportView(Output);

        getContentPane().add(ScrollOutput);
        ScrollOutput.setBounds(70, 90, 250, 250);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/wallp.jpg"))); // NOI18N
        jLabel3.setToolTipText("");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel3.setMaximumSize(new java.awt.Dimension(400, 300));
        jLabel3.setMinimumSize(new java.awt.Dimension(400, 300));
        jLabel3.setPreferredSize(new java.awt.Dimension(400, 300));
        jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 470, 390);

        pack();
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea Output;
    private javax.swing.JScrollPane ScrollOutput;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
