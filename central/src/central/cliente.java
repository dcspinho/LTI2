package central;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class cliente extends javax.swing.JFrame {

    ArrayList<String> lista;
    Connection con = null;
    String user;

    public cliente(ArrayList<String> list, Connection c, String u) throws SQLException, ParseException {
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
            logout.setVisible(false);
        }
        
        iniciar(lista);
    
    }
    
    public void iniciar(ArrayList<String> list) throws SQLException, ParseException{
        
        ArrayList<String> lista=list;
        DataBase t = new DataBase();
        DecimalFormat df = new DecimalFormat("###,#####0.00000");
        DecimalFormat df_custo = new DecimalFormat("###,##0.00");
        
        
        double TOTALISSIMO=0;
        double PRECO=0;

        for(String s:lista){
                ResultSet resultset = t.output_cliente(con, s);
                double preco = t.output_cliente_preco(con, s);
                
                double total=0;
                double cust=0;
                int mes=0;
                Calendar calendar = Calendar.getInstance();
		double val_ini=0;
                double val_fin=0;
                double valll=0;
                boolean inicio=false;
                while (resultset.next()){
                    calendar.setTime(resultset.getDate("timestamp_date"));
                    
                    if(inicio==false){
                        mes=calendar.get(GregorianCalendar.MONTH); 
                        inicio=true;
                        val_ini=resultset.getDouble("valor");
                        //System.out.println("\n\nValor inicial: "+df.format(val_ini));
                        //System.out.println("Mes inicial: "+mes);
                    }                   
                    else if(mes<calendar.get(GregorianCalendar.MONTH)){
                        
                       // System.out.println("Valor final: "+df.format(val_fin));
                        valll=val_fin-val_ini;
                        
                        //System.out.println("Valor final: "+df.format(val_fin));
                        total=total+valll;
                        valll=0;
                        val_ini=resultset.getDouble("valor");
                        mes=calendar.get(GregorianCalendar.MONTH);
                    }
  
                    val_fin=resultset.getDouble("valor");

                }
                
                if(valll==0){

                        //System.out.println("Valor final: "+df.format(val_fin));
                        valll=val_fin-val_ini;
                        total=total+valll;
                }
 
                TOTALISSIMO=TOTALISSIMO+total;  
                PRECO=PRECO+(preco*total);
             
        }
        

        cons_total.setText(df.format(TOTALISSIMO)+ " kWh");
        custo_tot.setText(df_custo.format(PRECO)+ " €");

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
        consumo_label = new javax.swing.JLabel();
        consumo_label1 = new javax.swing.JLabel();
        cons_total = new javax.swing.JTextField();
        Botao_refres = new javax.swing.JButton();
        cust_label = new javax.swing.JLabel();
        custo_tot = new javax.swing.JTextField();
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
        lista_Edif.setBounds(140, 130, 170, 30);

        Botao_Consultar.setBackground(new java.awt.Color(204, 204, 204));
        Botao_Consultar.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        Botao_Consultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/cons.png"))); // NOI18N
        Botao_Consultar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Botao_Consultar.setPreferredSize(new java.awt.Dimension(45, 35));
        Botao_Consultar.setRequestFocusEnabled(false);
        Botao_Consultar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Botao_ConsultarMouseClicked(evt);
            }
        });
        Botao_Consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Botao_ConsultarActionPerformed(evt);
            }
        });
        Botao_Consultar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Botao_ConsultarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Botao_ConsultarKeyReleased(evt);
            }
        });
        jPanel1.add(Botao_Consultar);
        Botao_Consultar.setBounds(360, 125, 40, 40);

        consumo_label.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        consumo_label.setForeground(new java.awt.Color(255, 255, 255));
        consumo_label.setText("Todos os edifícios");
        jPanel1.add(consumo_label);
        consumo_label.setBounds(10, 70, 120, 30);

        consumo_label1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        consumo_label1.setForeground(new java.awt.Color(255, 255, 255));
        consumo_label1.setText("Consumo anual");
        jPanel1.add(consumo_label1);
        consumo_label1.setBounds(130, 40, 120, 30);

        cons_total.setEditable(false);
        cons_total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cons_total.setCaretColor(new java.awt.Color(204, 204, 204));
        cons_total.setSelectionColor(new java.awt.Color(0, 0, 0));
        cons_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cons_totalActionPerformed(evt);
            }
        });
        jPanel1.add(cons_total);
        cons_total.setBounds(120, 70, 110, 30);

        Botao_refres.setBackground(new java.awt.Color(204, 204, 204));
        Botao_refres.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        Botao_refres.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/refres.png"))); // NOI18N
        Botao_refres.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Botao_refres.setPreferredSize(new java.awt.Dimension(45, 35));
        Botao_refres.setRequestFocusEnabled(false);
        Botao_refres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Botao_refresMouseClicked(evt);
            }
        });
        Botao_refres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Botao_refresActionPerformed(evt);
            }
        });
        Botao_refres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Botao_refresKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Botao_refresKeyReleased(evt);
            }
        });
        jPanel1.add(Botao_refres);
        Botao_refres.setBounds(360, 65, 40, 40);

        cust_label.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cust_label.setForeground(new java.awt.Color(255, 255, 255));
        cust_label.setText("Custo anual");
        jPanel1.add(cust_label);
        cust_label.setBounds(255, 40, 80, 30);

        custo_tot.setEditable(false);
        custo_tot.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        custo_tot.setCaretColor(new java.awt.Color(204, 204, 204));
        custo_tot.setSelectionColor(new java.awt.Color(0, 0, 0));
        custo_tot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                custo_totActionPerformed(evt);
            }
        });
        jPanel1.add(custo_tot);
        custo_tot.setBounds(250, 70, 80, 30);

        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/logout.png"))); // NOI18N
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                logoutMouseReleased(evt);
            }
        });
        jPanel1.add(logout);
        logout.setBounds(20, 10, 50, 50);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/wallp.jpg"))); // NOI18N
        jLabel3.setToolTipText("");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel3.setMaximumSize(new java.awt.Dimension(400, 300));
        jLabel3.setMinimumSize(new java.awt.Dimension(400, 300));
        jLabel3.setPreferredSize(new java.awt.Dimension(400, 300));
        jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel3);
        jLabel3.setBounds(0, -20, 660, 300);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 476, 250);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lista_EdifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lista_EdifActionPerformed
        String s = (String) lista_Edif.getSelectedItem();

    }//GEN-LAST:event_lista_EdifActionPerformed

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

    private void Botao_ConsultarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Botao_ConsultarKeyReleased
 
        
    }//GEN-LAST:event_Botao_ConsultarKeyReleased

    private void Botao_ConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Botao_ConsultarActionPerformed
        infor_edi ini;
        try {
            ini = new infor_edi(con,user,(String) lista_Edif.getSelectedItem());
            ini.setSize(400, 370);
            ini.setTitle("Edifício: "+(String) lista_Edif.getSelectedItem());
            ini.setLocationRelativeTo(null);
            ini.Imprimir_Output();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Botao_ConsultarActionPerformed

    private void Botao_ConsultarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Botao_ConsultarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Botao_ConsultarKeyPressed

    private void Botao_ConsultarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Botao_ConsultarMouseClicked
        

    }//GEN-LAST:event_Botao_ConsultarMouseClicked

    private void cons_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cons_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cons_totalActionPerformed

    private void custo_totActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_custo_totActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_custo_totActionPerformed

    private void Botao_refresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Botao_refresMouseClicked
        
    }//GEN-LAST:event_Botao_refresMouseClicked

    private void Botao_refresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Botao_refresActionPerformed
        cons_total.setText("");
        custo_tot.setText("");
        
        try {
            iniciar(lista);
        } catch (SQLException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Botao_refresActionPerformed

    private void Botao_refresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Botao_refresKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Botao_refresKeyPressed

    private void Botao_refresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Botao_refresKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_Botao_refresKeyReleased


    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Botao_Consultar;
    private javax.swing.JButton Botao_refres;
    public javax.swing.JTextArea Output;
    private javax.swing.JScrollPane ScrollOutput;
    private javax.swing.JTextField cons_total;
    private javax.swing.JLabel consumo_label;
    private javax.swing.JLabel consumo_label1;
    private javax.swing.JLabel cust_label;
    private javax.swing.JTextField custo_tot;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> lista_Edif;
    private javax.swing.JLabel logout;
    // End of variables declaration//GEN-END:variables

}
