package central;

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class add_uti extends javax.swing.JFrame {

    String name;
    Connection con;

    public add_uti(String n, Connection c) {
        initComponents();
        name = n;
        nome.setText(name);
        con = c;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        user = new javax.swing.JLabel();
        user1 = new javax.swing.JLabel();
        user3 = new javax.swing.JLabel();
        Iniciar = new javax.swing.JButton();
        cliente = new javax.swing.JRadioButton();
        gestor = new javax.swing.JRadioButton();
        pass2 = new javax.swing.JPasswordField();
        pass1 = new javax.swing.JPasswordField();
        nome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        jPanel1.setMaximumSize(new java.awt.Dimension(500, 400));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 400));
        jPanel1.setLayout(null);

        user.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        user.setForeground(new java.awt.Color(255, 255, 255));
        user.setText("Utilizador");
        jPanel1.add(user);
        user.setBounds(50, 60, 70, 30);

        user1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        user1.setForeground(new java.awt.Color(255, 255, 255));
        user1.setText("Palavra-passe (conf.)");
        jPanel1.add(user1);
        user1.setBounds(20, 160, 190, 30);

        user3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        user3.setForeground(new java.awt.Color(255, 255, 255));
        user3.setText("Palavra-passe");
        jPanel1.add(user3);
        user3.setBounds(40, 110, 100, 30);

        Iniciar.setBackground(new java.awt.Color(51, 51, 51));
        Iniciar.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        Iniciar.setForeground(new java.awt.Color(169, 229, 243));
        Iniciar.setText("ADICIONAR");
        Iniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IniciarActionPerformed(evt);
            }
        });
        jPanel1.add(Iniciar);
        Iniciar.setBounds(170, 210, 160, 40);

        grupo.add(cliente);
        cliente.setForeground(new java.awt.Color(255, 255, 255));
        cliente.setText("Cliente");
        jPanel1.add(cliente);
        cliente.setBounds(170, 10, 70, 40);

        grupo.add(gestor);
        gestor.setForeground(new java.awt.Color(255, 255, 255));
        gestor.setText("Gestor");
        jPanel1.add(gestor);
        gestor.setBounds(270, 10, 80, 40);

        pass2.setForeground(new java.awt.Color(153, 153, 153));
        pass2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pass2.setText("password");
        pass2.setCaretColor(new java.awt.Color(255, 255, 153));
        pass2.setSelectionColor(new java.awt.Color(0, 0, 0));
        pass2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pass2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pass2MousePressed(evt);
            }
        });
        pass2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pass2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pass2KeyReleased(evt);
            }
        });
        jPanel1.add(pass2);
        pass2.setBounds(160, 160, 180, 30);

        pass1.setForeground(new java.awt.Color(153, 153, 153));
        pass1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pass1.setText("password");
        pass1.setCaretColor(new java.awt.Color(255, 255, 153));
        pass1.setSelectionColor(new java.awt.Color(0, 0, 0));
        pass1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pass1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pass1MousePressed(evt);
            }
        });
        pass1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pass1ActionPerformed(evt);
            }
        });
        pass1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pass1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pass1KeyReleased(evt);
            }
        });
        jPanel1.add(pass1);
        pass1.setBounds(160, 110, 180, 30);

        nome.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nome.setText("NOME");
        nome.setCaretColor(new java.awt.Color(204, 204, 204));
        nome.setSelectionColor(new java.awt.Color(0, 0, 0));
        nome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nomeMousePressed(evt);
            }
        });
        nome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nomeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nomeKeyReleased(evt);
            }
        });
        jPanel1.add(nome);
        nome.setBounds(160, 60, 180, 30);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/wallp.jpg"))); // NOI18N
        jLabel3.setToolTipText("");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel3.setMaximumSize(new java.awt.Dimension(400, 300));
        jLabel3.setMinimumSize(new java.awt.Dimension(400, 300));
        jLabel3.setPreferredSize(new java.awt.Dimension(400, 300));
        jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel3);
        jLabel3.setBounds(0, 0, 470, 390);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void nomeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nomeMousePressed
        nome.setCaretPosition(0);
    }//GEN-LAST:event_nomeMousePressed
    public int existe_user() {
        /*0 - nao existe*/

        int code = 0;
        DataBase t = new DataBase();
        try {
            //t.ver_tabelaUtilizador(con);
            code = t.ver_existe_user(con, nome.getText());
            return code;
        } catch (SQLException ex) {
            Logger.getLogger(gestor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    private void nomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeKeyPressed
        if (nome.getForeground() != Color.BLACK) {
            if (nome.getText().equals("NOME")) {
                nome.setText("");
            }
        }
        nome.setForeground(Color.BLACK);

        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            if (existe_user() > 0) {        //ja existe
                JOptionPane.showMessageDialog(null, "Utilizador já existente!", "Sistema Central de Gestão", JOptionPane.ERROR_MESSAGE);
            } else {
                pass1.requestFocus();
            }
        }
    }//GEN-LAST:event_nomeKeyPressed

    private void nomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeKeyReleased

        if (nome.getText().isEmpty() == true) {
            nome.setText("NOME");
            nome.setCaretPosition(0);
            nome.setForeground(new java.awt.Color(204, 204, 204));
        }
    }//GEN-LAST:event_nomeKeyReleased

    private void pass2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pass2MousePressed
        pass2.setCaretPosition(0);
    }//GEN-LAST:event_pass2MousePressed

    private void pass2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pass2KeyPressed

        if (pass2.getForeground() != Color.BLACK) {
            if (pass2.getText().equals("password")) {
                pass2.setText("");
            }
        }
        pass2.setForeground(Color.BLACK);

        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                Adicionar_Utilizador();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(add_uti.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_pass2KeyPressed

    private void pass2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pass2KeyReleased
        if (pass2.getText().isEmpty() == true) {
            pass2.setText("password");
            pass2.setCaretPosition(0);
            pass2.setForeground(new java.awt.Color(204, 204, 204));
        }
        if (pass2.getText().length() > 10) {
            JOptionPane.showMessageDialog(null, "    Introduzir uma password com no máximo 10 carateres!", "Sistema Central de Gestão", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_pass2KeyReleased

    private void pass1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pass1MousePressed
        pass1.setCaretPosition(0);
        pass2.setText("password");
        pass2.setCaretPosition(0);
        pass2.setForeground(new java.awt.Color(204, 204, 204));
    }//GEN-LAST:event_pass1MousePressed

    private void pass1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pass1KeyPressed
        if (pass1.getForeground() != Color.BLACK) {
            if (pass1.getText().equals("password")) {
                pass1.setText("");
            }
        }
        pass1.setForeground(Color.BLACK);

        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            check_pass2();
        }
    }//GEN-LAST:event_pass1KeyPressed

    private void pass1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pass1KeyReleased

        if (pass1.getText().isEmpty() == true) {
            pass1.setText("password");
            pass1.setCaretPosition(0);
            pass1.setForeground(new java.awt.Color(204, 204, 204));
        }
        if (pass1.getText().length() > 10) {
            JOptionPane.showMessageDialog(null, "    Introduzir uma password com no máximo 10 carateres!", "Sistema Central de Gestão", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_pass1KeyReleased

    public boolean verificar_pass(JPasswordField pass) {
        if (pass.getText().isEmpty() == true) {
            return false;
        }
        if (pass.getText().equals("password")) {
            return false;
        }
        if (pass.getText().length() > 10) {
            return false;
        }
        return true;
    }
    private void IniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IniciarActionPerformed
        try {
            Adicionar_Utilizador();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(add_uti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_IniciarActionPerformed

    private void pass1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pass1ActionPerformed

    }//GEN-LAST:event_pass1ActionPerformed

    private void pass1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pass1MouseClicked
        check_pass1();
    }//GEN-LAST:event_pass1MouseClicked

    private void pass2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pass2MouseClicked
        check_pass2();
    }//GEN-LAST:event_pass2MouseClicked

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Iniciar;
    private javax.swing.JRadioButton cliente;
    private javax.swing.JRadioButton gestor;
    private javax.swing.ButtonGroup grupo;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nome;
    private javax.swing.JPasswordField pass1;
    private javax.swing.JPasswordField pass2;
    private javax.swing.JLabel user;
    private javax.swing.JLabel user1;
    private javax.swing.JLabel user3;
    // End of variables declaration//GEN-END:variables
public void check_pass1() {
        if (existe_user() > 0) {        //ja existe
            JOptionPane.showMessageDialog(null, "Utilizador já existente!", "Sistema Central de Gestão", JOptionPane.ERROR_MESSAGE);
            nome.requestFocus();
        } else {
            pass1.requestFocus();
        }
    }

    public void check_pass2() {
        if (existe_user() > 0) {        //ja existe
            JOptionPane.showMessageDialog(null, "Utilizador já existente!", "Sistema Central de Gestão", JOptionPane.ERROR_MESSAGE);
            nome.requestFocus();
        } else if (verificar_pass(pass1)) {
            pass2.requestFocus();
        } else if (!verificar_pass(pass1)) {
            pass1.requestFocus();
            JOptionPane.showMessageDialog(null, "Intoduzir password válida!", "Sistema Central de Gestão", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void Adicionar_Utilizador() throws ClassNotFoundException, SQLException {
        //System.out.println("ADICIONAR UTILIZADOR");
        DataBase t = new DataBase();
        String nom = this.nome.getText();
        String pas1 = this.pass1.getText();
        String pas2 = this.pass2.getText();
        String us = "";

        if (cliente.isSelected()) {
            us = "cliente";

        } else if (gestor.isSelected()) {
            us = "gestor";
        } else {
            JOptionPane.showMessageDialog(null, "    Defina o tipo de utilizador!", "Sistema Central de Gestão", JOptionPane.ERROR_MESSAGE);
            us = "";
        }

        if ((us.equals("") == false) && (pass1.getText().equals("password") == false) && (pass2.getText().equals("password") == false) && (nome.getText().equals("NOME") == false)) {
            if (pas1.equals(pas2)) {  //posso avançar
                int erro = 0;
                //t.ver_tabelaUtilizador(con);
                if (us.equals("cliente")) {
                    erro = t.add_cliente(con, nom, pas2);
                } else if (us.equals("gestor")) {
                    erro = t.add_gestor(con, nom, pas2);
                }
                //t.ver_tabelaUtilizador(con);

                if (erro == 0) {
                    JOptionPane.showMessageDialog(null, "    Utilizador adicionado com sucesso!", "Sistema Central de Gestão", JOptionPane.PLAIN_MESSAGE);
                    setVisible(false);
                } else if (erro == 1) {
                    JOptionPane.showMessageDialog(null, "    Utilizador já existente!", "Sistema Central de Gestão", JOptionPane.ERROR_MESSAGE);
                } else if (erro == 2) {
                    JOptionPane.showMessageDialog(null, "    Introduzir uma password com no máximo 10 carateres!", "Sistema Central de Gestão", JOptionPane.ERROR_MESSAGE);
                } else if (erro == 3) {
                    JOptionPane.showMessageDialog(null, "    Introduzir um nome de utilizador com no máximo 10 carateres!", "Sistema Central de Gestão", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "    Passwords diferentes!", "Sistema Central de Gestão", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
