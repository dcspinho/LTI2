
package gestor;


import java.awt.Color;
import java.util.ArrayList;




public class Config extends javax.swing.JFrame{
    
    arranque a;
    ArrayList<concentrador> lista_conc;
    
    
    public Config(ArrayList<concentrador> l, arranque aa) {
        a=aa;
        lista_conc=l;
        initComponents();

    }
    

    
     

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        naoseioquee = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(50, 100));
        getContentPane().setLayout(null);

        naoseioquee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/luz2.jpg"))); // NOI18N
        naoseioquee.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        naoseioquee.setMaximumSize(new java.awt.Dimension(350, 270));
        naoseioquee.setMinimumSize(new java.awt.Dimension(350, 270));
        naoseioquee.setPreferredSize(new java.awt.Dimension(250, 270));
        getContentPane().add(naoseioquee);
        naoseioquee.setBounds(-80, -90, 580, 380);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

   
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel naoseioquee;
    // End of variables declaration//GEN-END:variables

   
  
}

    



