package central;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableModel;

public class cliente extends javax.swing.JFrame {

    ArrayList<String> lista;
    Connection con = null;
    String user;

    public cliente(ArrayList<String> list, Connection c, String u) throws SQLException {
        initComponents();
        con = c;
        user = u;

        lista = list;
        BasicComboBoxRenderer.UIResource UIResource = new BasicComboBoxRenderer.UIResource();
        UIResource.setHorizontalAlignment(SwingConstants.CENTER);
        lista_Edif.setRenderer(UIResource);
        if (lista != null) {
            for (String s : lista) {
                lista_Edif.addItem(s);
            }
        }

        DataBase t = new DataBase();
        if (t.ver_Tipo_user(con, user) == 2) {
            Botao_AlterarPreco.setVisible(false);
            logout.setVisible(false);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ScrollOutput = new javax.swing.JScrollPane();
        ScrollOutput = new javax.swing.JScrollPane();
        Output = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        lista_Edif = new javax.swing.JComboBox<>();
        Botao_Consultar = new javax.swing.JButton();
        prec = new javax.swing.JTextField();
        prec_txt = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        Botao_AlterarPreco = new javax.swing.JButton();
        logout = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        Output.setEditable(false);
        Output.setBackground(new java.awt.Color(239, 239, 239));
        Output.setMinimumSize(new java.awt.Dimension(1, 22));
        ScrollOutput.setViewportView(Output);

        getContentPane().setLayout(null);

        jPanel1.setMaximumSize(new java.awt.Dimension(500, 400));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 400));
        jPanel1.setLayout(null);

        lista_Edif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lista_EdifActionPerformed(evt);
            }
        });
        jPanel1.add(lista_Edif);
        lista_Edif.setBounds(230, 50, 130, 30);

        Botao_Consultar.setBackground(new java.awt.Color(204, 204, 204));
        Botao_Consultar.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        Botao_Consultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/cons.png"))); // NOI18N
        Botao_Consultar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Botao_Consultar.setPreferredSize(new java.awt.Dimension(45, 35));
        Botao_Consultar.setRequestFocusEnabled(false);
        Botao_Consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Botao_ConsultarActionPerformed(evt);
            }
        });
        Botao_Consultar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Botao_ConsultarKeyReleased(evt);
            }
        });
        jPanel1.add(Botao_Consultar);
        Botao_Consultar.setBounds(370, 45, 40, 40);

        prec.setEditable(false);
        prec.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        prec.setCaretColor(new java.awt.Color(204, 204, 204));
        prec.setSelectionColor(new java.awt.Color(0, 0, 0));
        prec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precActionPerformed(evt);
            }
        });
        jPanel1.add(prec);
        prec.setBounds(60, 50, 60, 30);

        prec_txt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        prec_txt.setForeground(new java.awt.Color(255, 255, 255));
        prec_txt.setText("Preço");
        prec_txt.setToolTipText("");
        jPanel1.add(prec_txt);
        prec_txt.setBounds(20, 50, 40, 30);

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Consumo (kWh)", "Dia", "Hora"
            }
        ));
        jScrollPane1.setViewportView(tabela);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(20, 90, 390, 200);

        Botao_AlterarPreco.setBackground(new java.awt.Color(204, 204, 204));
        Botao_AlterarPreco.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        Botao_AlterarPreco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/eur.png"))); // NOI18N
        Botao_AlterarPreco.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Botao_AlterarPreco.setLabel("");
        Botao_AlterarPreco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Botao_AlterarPrecoActionPerformed(evt);
            }
        });
        Botao_AlterarPreco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Botao_AlterarPrecoKeyReleased(evt);
            }
        });
        jPanel1.add(Botao_AlterarPreco);
        Botao_AlterarPreco.setBounds(130, 45, 40, 40);

        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/logout.png"))); // NOI18N
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                logoutMouseReleased(evt);
            }
        });
        jPanel1.add(logout);
        logout.setBounds(20, 0, 50, 50);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/wallp.jpg"))); // NOI18N
        jLabel3.setToolTipText("");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel3.setMaximumSize(new java.awt.Dimension(400, 300));
        jLabel3.setMinimumSize(new java.awt.Dimension(400, 300));
        jLabel3.setPreferredSize(new java.awt.Dimension(400, 300));
        jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel3);
        jLabel3.setBounds(0, 0, 660, 390);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 476, 347);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lista_EdifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lista_EdifActionPerformed
        String s = (String) lista_Edif.getSelectedItem();

    }//GEN-LAST:event_lista_EdifActionPerformed

    private void Botao_AlterarPrecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Botao_AlterarPrecoActionPerformed
        try {
            DataBase t = new DataBase();
            double preco = 0;
            String novo = JOptionPane.showInputDialog(rootPane, "Alterar o preço :", "Novo preço", -1);
            
            if (novo != null) {
                if (novo.equals("") == false) {
                    novo=novo.replaceAll(",", ".");
                    preco = Double.parseDouble(novo);
                    if (t.alterar_preco(con, user, (String) lista_Edif.getSelectedItem(), preco)) {
                        JOptionPane.showMessageDialog(null, "Preço alterado com sucesso!", "Sistema Central de Gestão", JOptionPane.PLAIN_MESSAGE);
                        Imprimir_Output();
                    } else {
                        JOptionPane.showMessageDialog(null, "Impossível alterar o preço!", "Sistema Central de Gestão", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        } catch (SQLException | ParseException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Botao_AlterarPrecoActionPerformed

    private void Botao_AlterarPrecoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Botao_AlterarPrecoKeyReleased


    }//GEN-LAST:event_Botao_AlterarPrecoKeyReleased

    private void logoutMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseReleased
        int resp = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende terminar sessão?", "Sistema Central de Gestão", WIDTH);

        if (resp == 0) {
            try {
                setVisible(false);

                inicial ini;
                ini = new inicial();
                ini.setSize(400, 300);
                ini.setTitle("Sistema Central de Gestão");
                ini.setLocationRelativeTo(null);
                ini.setVisible(true);
            } catch (IOException | ClassNotFoundException | SQLException | ParseException ex) {
                Logger.getLogger(gestor.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_logoutMouseReleased

    private void precActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_precActionPerformed

    private void Botao_ConsultarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Botao_ConsultarKeyReleased
        /*if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                Imprimir_Output();
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
    }//GEN-LAST:event_Botao_ConsultarKeyReleased

    private void Botao_ConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Botao_ConsultarActionPerformed

        try {
            Imprimir_Output();

        } catch (SQLException | ParseException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Botao_ConsultarActionPerformed

    public void Imprimir_Output() throws SQLException, ParseException {
        DataBase t = new DataBase();
        String s = (String) lista_Edif.getSelectedItem();
        Output.setText(null);
        
       
        DefaultTableModel val= (DefaultTableModel) tabela.getModel();
        val.setRowCount(0);
        prec.setText("");


        
        if (s != null) {
            double preco = t.output_cliente_preco(con, s);
            if (preco != 0) {
               // Output.append("Edificio : " + s);
               
                //Output.append("\nPreço : " + preco);
                prec.setText(String.valueOf(preco)+ " €");
                //Output.append("\nConsumo (kWh) : \tDia:\t Hora:\n");
                ResultSet resultset = t.output_cliente(con, s);

                //System.out.println(resultset);
                while (resultset.next()){
                    //Output.append(Double.toString(resultset.getDouble("valor")) + "\t\t");
                    //System.out.println(Double.toString(resultset.getDouble("valor")));
                    //Output.append(String.valueOf(resultset.getDate("timestamp_date")) + "\t");
                    //Output.append(String.valueOf(resultset.getTime("timestamp_time")) + "\n");
                    val.addRow(new String[] {Double.toString(resultset.getDouble("valor")),String.valueOf(resultset.getDate("timestamp_date")),String.valueOf(resultset.getTime("timestamp_time"))});
                    
                    
                    
                }
            } else {
                JOptionPane.showMessageDialog(null, "Edifício " + s + " sem dados.", "Sistema Central de Gestão", JOptionPane.PLAIN_MESSAGE);
                //Output.append("Edifício " + s + " sem dados.");
                                
            }
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Botao_AlterarPreco;
    private javax.swing.JButton Botao_Consultar;
    public javax.swing.JTextArea Output;
    private javax.swing.JScrollPane ScrollOutput;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> lista_Edif;
    private javax.swing.JLabel logout;
    private javax.swing.JTextField prec;
    private javax.swing.JLabel prec_txt;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables

}
