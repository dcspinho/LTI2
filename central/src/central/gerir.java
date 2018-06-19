package central;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.List;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class gerir extends javax.swing.JFrame {

    ArrayList<String> lista;
    String name;
    Connection con;

    public gerir(Connection c, ArrayList<String> list, String s) {
        initComponents();
        con = c;
        lista = list;
        for (String ss : lista) {
            lista_Edif.addItem(ss);
        }

        name = s;
        nome.setText(name);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        user = new javax.swing.JLabel();
        nome = new javax.swing.JTextField();
        Botao_eliminar_edif = new javax.swing.JLabel();
        Edif_novo = new javax.swing.JButton();
        lista_Edif = new javax.swing.JComboBox<>();
        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel3 = new javax.swing.JLabel();

        jPanel1.setMaximumSize(new java.awt.Dimension(500, 400));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 400));
        jPanel1.setLayout(null);

        user.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        user.setForeground(new java.awt.Color(255, 255, 255));
        user.setText("Utilizador");
        jPanel1.add(user);
        user.setBounds(40, 30, 70, 30);

        nome.setEditable(false);
        nome.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nome.setText("NOME");
        nome.setCaretColor(new java.awt.Color(204, 204, 204));
        nome.setSelectionColor(new java.awt.Color(0, 0, 0));
        jPanel1.add(nome);
        nome.setBounds(110, 30, 140, 30);

        Botao_eliminar_edif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/delete.png"))); // NOI18N
        Botao_eliminar_edif.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Botao_eliminar_edifMouseReleased(evt);
            }
        });
        jPanel1.add(Botao_eliminar_edif);
        Botao_eliminar_edif.setBounds(220, 80, 50, 50);

        Edif_novo.setBackground(new java.awt.Color(51, 51, 51));
        Edif_novo.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        Edif_novo.setForeground(new java.awt.Color(169, 229, 243));
        Edif_novo.setText("NOVO EDIFÍCIO");
        Edif_novo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Edif_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Edif_novoActionPerformed(evt);
            }
        });
        jPanel1.add(Edif_novo);
        Edif_novo.setBounds(70, 160, 150, 50);

        lista_Edif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lista_EdifActionPerformed(evt);
            }
        });
        jPanel1.add(lista_Edif);
        lista_Edif.setBounds(40, 90, 150, 30);

        jToggleButton1.setText("LOGOUT");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButton1);
        jToggleButton1.setBounds(250, 30, 73, 40);

        jLabel3.setForeground(new java.awt.Color(169, 229, 243));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/wallp.jpg"))); // NOI18N
        jLabel3.setToolTipText("");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel3.setMaximumSize(new java.awt.Dimension(400, 300));
        jLabel3.setMinimumSize(new java.awt.Dimension(400, 300));
        jLabel3.setPreferredSize(new java.awt.Dimension(400, 300));
        jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel3);
        jLabel3.setBounds(-10, -40, 470, 390);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Edif_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Edif_novoActionPerformed

        try {
            //JOptionPane pane = new JOptionPane();
            //String designacao = JOptionPane.showInputDialog("Nome do edifício");
            String designacao = JOptionPane.showInputDialog(rootPane, "Nome do edifício :", "Adicionar edifício", -1);
            System.out.println("--->" + designacao);
            /*String s = (String)JOptionPane.showInputDialog(
                    frame,
                    "Complete the sentence:\n"
                    + "\"Green eggs and...\"",
                    "Customized Dialog",
                    JOptionPane.PLAIN_MESSAGE,
                    icon,
                    possibilities,
                    "ham");*/
            DataBase t = new DataBase();
            if (designacao != null) {
                if (!designacao.equals("")) {
                    if (!existe_edificio(designacao)) {   //não existe entao pode adicionar
                        String preco = JOptionPane.showInputDialog(rootPane, "Preço :", "Adicionar edifício", -1);
                        preco=preco.replaceAll(",", ".");
                        System.out.println("--->" + preco);
                        if (preco != null) {
                            if (!preco.equals("")) {
                                int controlo = t.add_edifício(con, nome.getText(), designacao, Float.parseFloat(preco));
                                t.ver_tabelaEdifício(con);
                                if (controlo == 2) {
                                    JOptionPane.showMessageDialog(null, "Edifício adicionado com sucesso!", "Sistema Central de Gestão", JOptionPane.PLAIN_MESSAGE);
                                    Atualizar_edif(t, con);
                                } else if (controlo == 1) {
                                    JOptionPane.showMessageDialog(null, "Edifício já existente neste utilizador!", "Sistema Central de Gestão", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Introduzir nome de edifício!", "Sistema Central de Gestão", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Edifício já existente neste utilizador!", "Sistema Central de Gestão", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(gerir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Edif_novoActionPerformed

    public boolean existe_edificio(String edificio){
        System.out.println(lista_Edif);
        int tamanho=lista_Edif.getItemCount();
        //System.out.println("tamanho : "+tamanho);
        String atual;
        
        for(int i=0; i<tamanho;i++){
            lista_Edif.setSelectedIndex(i);
            atual=(String) lista_Edif.getSelectedItem();
            if(atual.equals(edificio)){
                //System.out.println("Já existe");
                return true;
            }
        }
        
        //System.out.println("Não existe");
        return false;
    }
    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed

    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void Botao_eliminar_edifMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Botao_eliminar_edifMouseReleased
        DataBase t = new DataBase();
        /*try {         //ver apenas se estava a guardar bem na BD
            t.ver_tabelaConsumo(con);
        } catch (SQLException ex) {
            Logger.getLogger(gerir.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        String designacao = (String) lista_Edif.getSelectedItem();
        //System.out.println(designacao);
        int resp = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende remover: " + designacao + " ?", "Sistema Central de Gestão", WIDTH);
        if (resp == 0) {

            try {
                if (t.delete_edifício(con, designacao, nome.getText())) {
                    JOptionPane.showMessageDialog(null, "Edifício removido com sucesso!", "Sistema Central de Gestão", JOptionPane.PLAIN_MESSAGE);
                    Atualizar_edif(t, con);
                } else {
                    JOptionPane.showMessageDialog(null, "Impossível remover!", "Sistema Central de Gestão", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(gerir.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_Botao_eliminar_edifMouseReleased

    private void lista_EdifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lista_EdifActionPerformed

        //Edificio selecionado
        String s = (String) lista_Edif.getSelectedItem();
        //DataBase t=new DataBase();
        //lista_Edif = t.pesquisar_edificio_user(con, nome.getText());

    }//GEN-LAST:event_lista_EdifActionPerformed

    public void Atualizar_edif(DataBase t, Connection con) throws SQLException {
        try {
            ArrayList<String> atualiza_edif = new ArrayList<String>();
            atualiza_edif = t.pesquisar_edificio_user(con, nome.getText());
            lista_Edif.removeAllItems();
            for (String x : atualiza_edif) {
                lista_Edif.addItem(x);
            }
        } catch (SQLException ex) {
            Logger.getLogger(gerir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Botao_eliminar_edif;
    private javax.swing.JButton Edif_novo;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JComboBox<String> lista_Edif;
    private javax.swing.JTextField nome;
    private javax.swing.JLabel user;
    // End of variables declaration//GEN-END:variables

}
