
package gestor;


import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;




public class Config extends javax.swing.JFrame{
    
    arranque a;
    ArrayList<concentrador> lista_conc;
    ConexaoMySQL conex;
    
    public Config(arranque aa, ConexaoMySQL c) {
        
        conex=c;
        a=aa;
        initComponents();
        areas_bot.setEnabled(false);
        areas_bot.setForeground(Color.BLACK);
        sens_bot.setEnabled(false);
        sens_bot.setForeground(Color.BLACK);
        inicio();
    }
    

    public void inicio(){
        HashMap<Integer, String> lista_area=conex.area_designacao;
        
        for(Integer i: lista_area.keySet()){         
            areas_list.addItem(lista_area.get(i));
        }
        sens_list.removeAllItems();
    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        areas_bot = new javax.swing.JButton();
        areas_list = new javax.swing.JComboBox<>();
        sens_list = new javax.swing.JComboBox<>();
        sens_bot = new javax.swing.JButton();
        rem = new javax.swing.JButton();
        add = new javax.swing.JButton();
        cons = new javax.swing.JButton();
        add1 = new javax.swing.JButton();
        rem1 = new javax.swing.JButton();
        naoseioquee = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(50, 100));
        getContentPane().setLayout(null);

        areas_bot.setBackground(new java.awt.Color(255, 102, 0));
        areas_bot.setText("Áreas");
        areas_bot.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(areas_bot);
        areas_bot.setBounds(30, 40, 90, 30);

        areas_list.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                areas_listActionPerformed(evt);
            }
        });
        getContentPane().add(areas_list);
        areas_list.setBounds(120, 40, 140, 30);

        sens_list.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sens_listActionPerformed(evt);
            }
        });
        getContentPane().add(sens_list);
        sens_list.setBounds(120, 150, 140, 30);

        sens_bot.setBackground(new java.awt.Color(255, 102, 0));
        sens_bot.setText("Sensores");
        sens_bot.setToolTipText("");
        sens_bot.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(sens_bot);
        sens_bot.setBounds(30, 150, 90, 30);

        rem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/remo.png"))); // NOI18N
        rem.setToolTipText("");
        rem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remActionPerformed(evt);
            }
        });
        getContentPane().add(rem);
        rem.setBounds(330, 35, 40, 40);

        add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/add.png"))); // NOI18N
        add.setToolTipText("");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        getContentPane().add(add);
        add.setBounds(380, 35, 40, 40);

        cons.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/view.png"))); // NOI18N
        cons.setToolTipText("");
        cons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consActionPerformed(evt);
            }
        });
        getContentPane().add(cons);
        cons.setBounds(280, 35, 40, 40);

        add1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/add.png"))); // NOI18N
        add1.setToolTipText("");
        add1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add1ActionPerformed(evt);
            }
        });
        getContentPane().add(add1);
        add1.setBounds(330, 145, 40, 40);

        rem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/remo.png"))); // NOI18N
        rem1.setToolTipText("");
        rem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rem1ActionPerformed(evt);
            }
        });
        getContentPane().add(rem1);
        rem1.setBounds(280, 145, 40, 40);

        naoseioquee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/luz2.jpg"))); // NOI18N
        naoseioquee.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        naoseioquee.setMaximumSize(new java.awt.Dimension(350, 270));
        naoseioquee.setMinimumSize(new java.awt.Dimension(350, 270));
        naoseioquee.setPreferredSize(new java.awt.Dimension(250, 270));
        getContentPane().add(naoseioquee);
        naoseioquee.setBounds(-80, -80, 580, 380);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void areas_listActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_areas_listActionPerformed
            
            
            
    }//GEN-LAST:event_areas_listActionPerformed

    private void consActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consActionPerformed
            sens_list.removeAllItems();
        
            String area= (String) areas_list.getSelectedItem();
            
            int cod=0;
            
            HashMap<Integer, String> lista_area=conex.area_designacao;
            for (Integer i:lista_area.keySet()){
                if(lista_area.get(i).equals(area)){
                    cod=i;
                }
            }
            
            ArrayList<Integer> lista_sens=conex.sensores(cod);
            
            for(int i: lista_sens){
                //System.out.println("sensor: "+i);
                sens_list.addItem("Sensor "+i);
            }
        
        
        
    }//GEN-LAST:event_consActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed

            String novo = JOptionPane.showInputDialog(rootPane, "Nova área:", "Adicionar área " , -1);
            
            if (novo != null) {
                /*if (novo.equals("") == false) {
                    novo=novo.replaceAll(",", ".");
                    preco = Double.parseDouble(novo);
                    if (t.alterar_preco(con, user, edificio, preco)) {
                        JOptionPane.showMessageDialog(null, "Preço alterado com sucesso!", "Sistema Central de Gestão", JOptionPane.PLAIN_MESSAGE);
                        Imprimir_Output();
                    } else {
                        JOptionPane.showMessageDialog(null, "Impossível alterar o preço!", "Sistema Central de Gestão", JOptionPane.ERROR_MESSAGE);
                    }
                }*/
            }

    
    }//GEN-LAST:event_addActionPerformed

    private void remActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_remActionPerformed

    private void sens_listActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sens_listActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sens_listActionPerformed

    private void add1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add1ActionPerformed

    private void rem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rem1ActionPerformed

   
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton add1;
    private javax.swing.JButton areas_bot;
    private javax.swing.JComboBox<String> areas_list;
    private javax.swing.JButton cons;
    private javax.swing.JLabel naoseioquee;
    private javax.swing.JButton rem;
    private javax.swing.JButton rem1;
    private javax.swing.JButton sens_bot;
    private javax.swing.JComboBox<String> sens_list;
    // End of variables declaration//GEN-END:variables

   
  
}

    



