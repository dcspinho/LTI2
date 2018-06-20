package central;

import java.awt.Color;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class gestor extends javax.swing.JFrame {

    Connection con = null;
    String user;
    cliente cli;

    public gestor(Connection c, String u) throws SQLException {

        initComponents();
        con = c;
        user = u;
        DataBase t = new DataBase();
        ArrayList<String> list = t.todos_users(con);
        BasicComboBoxRenderer.UIResource UIResource = new BasicComboBoxRenderer.UIResource();
        UIResource.setHorizontalAlignment(SwingConstants.CENTER);
        lista_users.setRenderer(UIResource);
        for (String s : list) {

            lista_users.addItem(s);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Utilizador = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        nome = new javax.swing.JTextField();
        consultar = new javax.swing.JButton();
        remover = new javax.swing.JButton();
        adicionar = new javax.swing.JButton();
        gerir = new javax.swing.JButton();
        Botao_copiar = new javax.swing.JButton();
        lista_users = new javax.swing.JComboBox<>();
        logout = new javax.swing.JLabel();
        fundo = new javax.swing.JLabel();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setMaximumSize(new java.awt.Dimension(500, 400));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 400));
        jPanel1.setLayout(null);

        nome.setForeground(new java.awt.Color(153, 153, 153));
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
        nome.setBounds(70, 40, 190, 30);

        consultar.setBackground(new java.awt.Color(51, 51, 51));
        consultar.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        consultar.setForeground(new java.awt.Color(169, 229, 243));
        consultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/inf.png"))); // NOI18N
        consultar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarActionPerformed(evt);
            }
        });
        jPanel1.add(consultar);
        consultar.setBounds(180, 150, 60, 50);

        remover.setBackground(new java.awt.Color(51, 51, 51));
        remover.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        remover.setForeground(new java.awt.Color(169, 229, 243));
        remover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/user_r.png"))); // NOI18N
        remover.setMaximumSize(new java.awt.Dimension(117, 27));
        remover.setMinimumSize(new java.awt.Dimension(117, 27));
        remover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerActionPerformed(evt);
            }
        });
        jPanel1.add(remover);
        remover.setBounds(180, 80, 60, 50);

        adicionar.setBackground(new java.awt.Color(51, 51, 51));
        adicionar.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        adicionar.setForeground(new java.awt.Color(169, 229, 243));
        adicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/add_user.png"))); // NOI18N
        adicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarActionPerformed(evt);
            }
        });
        jPanel1.add(adicionar);
        adicionar.setBounds(90, 80, 60, 50);

        gerir.setBackground(new java.awt.Color(51, 51, 51));
        gerir.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        gerir.setForeground(new java.awt.Color(169, 229, 243));
        gerir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/atualiza.png"))); // NOI18N
        gerir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gerir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gerirActionPerformed(evt);
            }
        });
        jPanel1.add(gerir);
        gerir.setBounds(90, 150, 60, 50);

        Botao_copiar.setBackground(new java.awt.Color(204, 204, 204));
        Botao_copiar.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        Botao_copiar.setText("Copiar");
        Botao_copiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Botao_copiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Botao_copiarActionPerformed(evt);
            }
        });
        jPanel1.add(Botao_copiar);
        Botao_copiar.setBounds(260, 10, 90, 30);

        lista_users.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lista_usersActionPerformed(evt);
            }
        });
        jPanel1.add(lista_users);
        lista_users.setBounds(70, 10, 190, 30);

        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/logout.png"))); // NOI18N
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                logoutMouseReleased(evt);
            }
        });
        jPanel1.add(logout);
        logout.setBounds(20, 10, 50, 50);

        fundo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/wallp2.jpg"))); // NOI18N
        fundo.setToolTipText("");
        fundo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        fundo.setMaximumSize(new java.awt.Dimension(400, 300));
        fundo.setMinimumSize(new java.awt.Dimension(400, 300));
        fundo.setPreferredSize(new java.awt.Dimension(400, 300));
        fundo.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel1.add(fundo);
        fundo.setBounds(-40, -130, 470, 390);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 438, 347);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarActionPerformed
        if (nome.getText().equals("NOME")) {
            JOptionPane.showMessageDialog(null, "Insira o nome do utilizador!", "Sistema Central de Gestão", JOptionPane.PLAIN_MESSAGE);
        } else {
            /*
            ver quais os edificios deste user;
            selecionar o edificio que quero ver;
            imprimir os consumos;
             */

            try {

                ArrayList<String> lista = new ArrayList<String>();
                DataBase t = new DataBase();
                lista = t.pesquisar_edificio_user(con, nome.getText());

                
                cli = new cliente(lista, con, user);
                cli.setSize(445, 350);
                cli.setTitle("Sistema Central de Gestão: " + nome.getText());
                cli.setLocationRelativeTo(null);
                cli.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(gestor.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_consultarActionPerformed

    private void adicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarActionPerformed
        int code = existe_user();
        if (code == 0) {
            add_uti m = new add_uti(nome.getText(), con);
            m.setSize(400, 300);
            m.setTitle("Adicionar Utilizador");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else if (code != -1) {
            JOptionPane.showMessageDialog(null, "    Utilizador já existente!", "Sistema Central de Gestão", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_adicionarActionPerformed

    private void gerirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gerirActionPerformed
        DataBase t = new DataBase();
        int code = existe_user();
        if (code > 0) {
            try {
                ArrayList<String> lista = new ArrayList<>();
                String user = t.nome_cod_user(con, code);
                if (user != null) {
                    lista = t.pesquisar_edificio_user(con, user);
                    gerir m = new gerir(con, lista, nome.getText());
                    m.setSize(300, 300);
                    m.setLocationRelativeTo(null);
                    m.setTitle("Gerir Edifícios");
                    m.setVisible(true);
                }
            } catch (SQLException ex) {
                Logger.getLogger(gestor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (code == 0) {
            JOptionPane.showMessageDialog(null, "Utilizador não existe!", "Sistema Central de Gestão", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_gerirActionPerformed

    private void UtilizadorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UtilizadorMousePressed

    }//GEN-LAST:event_UtilizadorMousePressed

    private void UtilizadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UtilizadorActionPerformed

    }//GEN-LAST:event_UtilizadorActionPerformed

    private void UtilizadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UtilizadorKeyPressed


    }//GEN-LAST:event_UtilizadorKeyPressed

    private void UtilizadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UtilizadorKeyReleased

    }//GEN-LAST:event_UtilizadorKeyReleased

    private void nomeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nomeMousePressed
        nome.setCaretPosition(0);
    }//GEN-LAST:event_nomeMousePressed

    private void nomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeKeyPressed

        if (nome.getForeground() != Color.BLACK) {
            if (nome.getText().equals("NOME")) {
                nome.setText("");
            }
        }

        nome.setForeground(Color.BLACK);

    }//GEN-LAST:event_nomeKeyPressed

    private void nomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeKeyReleased
        //System.out.println("UtilizadorKeyReleased");
        if (nome.getText().isEmpty() == true) {
            nome.setText("NOME");
            nome.setCaretPosition(0);
            nome.setForeground(new java.awt.Color(204, 204, 204));
        }
    }//GEN-LAST:event_nomeKeyReleased

    private void removerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerActionPerformed
        DataBase t = new DataBase();
        int code = existe_user();
        if (nome.getText().equals(user)) {
            JOptionPane.showMessageDialog(null, "Impossível remover a ti próprio!", "Sistema Central de Gestão", JOptionPane.ERROR_MESSAGE);
        } else if (code > 0) {
            int resp = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende remover o utilizador: " + nome.getText() + " ?", "Sistema Central de Gestão", WIDTH);
            if (resp == 0) {
                try {
                    t.delete_utilizador(con, code);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(gestor.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "Utilizador removido com sucesso!", "Sistema Central de Gestão", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (code == 0) {
            JOptionPane.showMessageDialog(null, "Utilizador não existe!", "Sistema Central de Gestão", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_removerActionPerformed

    private void lista_usersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lista_usersActionPerformed

        String s = (String) lista_users.getSelectedItem();
    }//GEN-LAST:event_lista_usersActionPerformed

    private void Botao_copiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Botao_copiarActionPerformed
        nome.setForeground(Color.BLACK);
        nome.setText((String) lista_users.getSelectedItem());
    }//GEN-LAST:event_Botao_copiarActionPerformed

    private void logoutMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseReleased
        int resp = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende terminar sessão?", "Sistema Central de Gestão", WIDTH);

        if (resp == 0) {
            try {
                setVisible(false);
                
                
                inicial ini;
                ini = new inicial();
                ini.setSize(400, 300);
                ini.setLocationRelativeTo(null);
                ini.setTitle("Sistema Central de Gestão");
                ini.setVisible(true);
                
                
            } catch (IOException | ClassNotFoundException | SQLException | ParseException ex) {
                Logger.getLogger(gestor.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }
    }//GEN-LAST:event_logoutMouseReleased

    public int existe_user() {
        /*0 - nao existe*/
        String s = nome.getText();

        if (s.equals("NOME")) {
            JOptionPane.showMessageDialog(null, "Insira o nome do utilizador!", "Sistema Central de Gestão", JOptionPane.PLAIN_MESSAGE);
        } else {
            int code = 0;
            DataBase t = new DataBase();
            try {
                //t.ver_tabelaUtilizador(con);
                code = t.ver_existe_user(con, nome.getText());
                return code;
            } catch (SQLException ex) {
                Logger.getLogger(gestor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Botao_copiar;
    private javax.swing.JTextField Utilizador;
    private javax.swing.JButton adicionar;
    private javax.swing.JButton consultar;
    private javax.swing.JLabel fundo;
    private javax.swing.JButton gerir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> lista_users;
    private javax.swing.JLabel logout;
    private javax.swing.JTextField nome;
    private javax.swing.JButton remover;
    // End of variables declaration//GEN-END:variables

}
