package central;

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class inicial extends javax.swing.JFrame {

    public String user;
    static Connection con;

    public inicial() throws IOException, ClassNotFoundException, SQLException, ParseException {
        initComponents();
        DataBase t=new DataBase();
        con=t.getConexaoMySQL();
    }

    public static void main(String args[]) throws SQLException, ParseException {
        try {

            Thread t = new Thread(new arranque());
            t.start();

            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                inicial ini;
                try {

                    ini = new inicial();
                    ini.setSize(400, 300);
                    ini.setTitle("Sistema Central de Gestão");
                    ini.setLocationRelativeTo(null);
                    ini.setVisible(true);

                } catch (IOException | ClassNotFoundException | SQLException | ParseException ex) {
                    Logger.getLogger(inicial.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pass = new javax.swing.JPasswordField();
        Utilizador = new javax.swing.JTextField();
        Iniciar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setMaximumSize(new java.awt.Dimension(500, 400));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 400));
        jPanel1.setLayout(null);

        pass.setForeground(new java.awt.Color(153, 153, 153));
        pass.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pass.setText("password");
        pass.setCaretColor(new java.awt.Color(255, 255, 153));
        pass.setSelectionColor(new java.awt.Color(0, 0, 0));
        pass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                passMousePressed(evt);
            }
        });
        pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passActionPerformed(evt);
            }
        });
        pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passKeyReleased(evt);
            }
        });
        jPanel1.add(pass);
        pass.setBounds(130, 120, 130, 30);
        pass.getAccessibleContext().setAccessibleDescription("");

        Utilizador.setForeground(new java.awt.Color(153, 153, 153));
        Utilizador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Utilizador.setText("UTILIZADOR");
        Utilizador.setCaretColor(new java.awt.Color(204, 204, 204));
        Utilizador.setSelectionColor(new java.awt.Color(0, 0, 0));
        Utilizador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                UtilizadorMousePressed(evt);
            }
        });
        Utilizador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UtilizadorActionPerformed(evt);
            }
        });
        Utilizador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UtilizadorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                UtilizadorKeyReleased(evt);
            }
        });
        jPanel1.add(Utilizador);
        Utilizador.setBounds(130, 80, 130, 30);

        Iniciar.setBackground(new java.awt.Color(51, 51, 51));
        Iniciar.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        Iniciar.setForeground(new java.awt.Color(169, 229, 243));
        Iniciar.setText("LOGIN");
        Iniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IniciarActionPerformed(evt);
            }
        });
        jPanel1.add(Iniciar);
        Iniciar.setBounds(130, 170, 130, 40);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/wallp_c.jpg"))); // NOI18N
        jLabel3.setToolTipText("");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel3.setMaximumSize(new java.awt.Dimension(400, 300));
        jLabel3.setMinimumSize(new java.awt.Dimension(400, 300));
        jLabel3.setPreferredSize(new java.awt.Dimension(400, 300));
        jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel3);
        jLabel3.setBounds(0, 0, 400, 300);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 98, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void IniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IniciarActionPerformed
        try {
            Login();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(inicial.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_IniciarActionPerformed

    public void Login() throws ClassNotFoundException, SQLException {
        String user = this.Utilizador.getText();
        String pass = this.pass.getText();
        DataBase t = new DataBase();
        //Connection con = t.getConexaoMySQL();

        if (con == null) {
            JOptionPane.showMessageDialog(null, "    Impossível conectar com a base de dados!", "Sistema Central de Gestão", JOptionPane.ERROR_MESSAGE);
        } else {
            HashMap<String, String> todos_pass = new HashMap<String, String>();
            HashMap<String, Integer> todos_tipo = new HashMap<String, Integer>();

            if (user.equals("UTILIZADOR")) {
                JOptionPane.showMessageDialog(null, "    Insira o seu nome!", "Sistema Central de Gestão", JOptionPane.ERROR_MESSAGE);
            } else if (pass.equals("password")) {
                JOptionPane.showMessageDialog(null, "    Insira a password!", "Sistema Central de Gestão", JOptionPane.ERROR_MESSAGE);
            } else {
                int encontrou_user = 0;
                try {
                    todos_pass = t.users_pass(con);
                    todos_tipo = t.users_tipo(con);

                    for (String x : todos_pass.keySet()) {
                        if (x.equals(user) && todos_pass.get(x).equals(pass)) {     //user e pass corretos
                            encontrou_user = 1;
                            if (todos_tipo.get(x) == 1) {       //cliente
                                //System.out.println("Cliente");
                                //System.out.println(t.users_edificios(con));
                                ArrayList<String> lista = new ArrayList<>();
                                lista = t.pesquisar_edificio_user(con, user);

                                cliente m;

                                m = new cliente(lista, con, user);
                                setVisible(false);
                                m.setSize(445, 350);
                                m.setTitle("Sistema Central de Gestão: " + user);
                                m.setLocationRelativeTo(null);
                                m.setVisible(true);
                            } else if (todos_tipo.get(x) == 2) {   //gestor
                                gestor m = new gestor(con, Utilizador.getText());
                                setVisible(false);
                                m.setSize(380, 250);
                                m.setLocationRelativeTo(null);
                                m.setTitle("Sistema Central de Gestão");
                                m.setVisible(true);
                            }

                            JOptionPane.showMessageDialog(null, "              Bem-vindo " + user + " !", "Sistema Central de Gestão", JOptionPane.PLAIN_MESSAGE);
                        } else if (x.equals(user) && todos_pass.get(x).equals(pass) == false) {
                            JOptionPane.showMessageDialog(null, "    Password errada!", "Sistema Central de Gestão", JOptionPane.ERROR_MESSAGE);
                            encontrou_user = 1;
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(inicial.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (encontrou_user == 0) {
                    JOptionPane.showMessageDialog(null, "    Utilizador desconhecido!", "Sistema Central de Gestão", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }

    private void UtilizadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UtilizadorKeyReleased
        //System.out.println("UtilizadorKeyReleased");
        if (Utilizador.getText().isEmpty() == true) {
            Utilizador.setText("UTILIZADOR");
            Utilizador.setCaretPosition(0);
            Utilizador.setForeground(new java.awt.Color(204, 204, 204));
        }
    }//GEN-LAST:event_UtilizadorKeyReleased

    private void UtilizadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UtilizadorKeyPressed

        if (Utilizador.getForeground() != Color.BLACK) {
            if (Utilizador.getText().equals("UTILIZADOR")) {
                Utilizador.setText("");
            }
        }

        Utilizador.setForeground(Color.BLACK);

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            pass.requestFocus();
        }

    }//GEN-LAST:event_UtilizadorKeyPressed

    private void UtilizadorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UtilizadorMousePressed
        Utilizador.setCaretPosition(0);
    }//GEN-LAST:event_UtilizadorMousePressed

    private void passKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passKeyPressed

        if (pass.getForeground() != Color.BLACK) {
            if (pass.getText().equals("password")) {
                pass.setText("");
            }
        }

        pass.setForeground(Color.BLACK);

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                Login();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(inicial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_passKeyPressed

    private void passMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passMousePressed
        Utilizador.setCaretPosition(0);


    }//GEN-LAST:event_passMousePressed

    private void passKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passKeyReleased
        //System.out.println("UtilizadorKeyReleased");
        if (pass.getText().isEmpty() == true) {
            pass.setText("password");
            pass.setCaretPosition(0);
            pass.setForeground(new java.awt.Color(204, 204, 204));
        }
    }//GEN-LAST:event_passKeyReleased

    private void UtilizadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UtilizadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UtilizadorActionPerformed

    private void passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Iniciar;
    private javax.swing.JTextField Utilizador;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField pass;
    // End of variables declaration//GEN-END:variables

}
