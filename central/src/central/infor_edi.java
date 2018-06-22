package central;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableModel;

public class infor_edi extends javax.swing.JFrame {

    
    Connection con = null;
    String user;
    String edificio;
    DefaultTableModel val;

    public infor_edi(Connection c, String u, String edif) throws SQLException {
        initComponents();
        con = c;
        user = u;
        edificio=edif;
        val= (DefaultTableModel) tabela_mes.getModel();

        
        BasicComboBoxRenderer.UIResource UIResource = new BasicComboBoxRenderer.UIResource();
        UIResource.setHorizontalAlignment(SwingConstants.CENTER);
        
        DataBase t = new DataBase();
        if (t.ver_Tipo_user(con, user) == 2) {
            Botao_AlterarPreco.setVisible(false);
        }

    }
    
    public String getMES(int i){
            if(i==0){
                return " janeiro";
            }
            else if(i==1){
                return " fevereiro";
            }
            else if(i==2){
                return " março";
            }
            else if(i==3){
                return " abril";
            }
            else if(i==4){
                return " maio";
            }
            else if(i==5){
                return " junho";
            }
            else if(i==6){
                return " julho";
            }
            else if(i==7){
                return " agosto";
            }
            else if(i==8){
                return " setembro";
            }
            else if(i==9){
                return " outubro";
            }
            else if(i==10){
                return " novembro";
            }
            else if(i==11){
                return " dezembro";
            }
            
            return "";
        }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ScrollOutput = new javax.swing.JScrollPane();
        ScrollOutput = new javax.swing.JScrollPane();
        Output = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        prec = new javax.swing.JTextField();
        prec_txt = new javax.swing.JLabel();
        refr = new javax.swing.JLabel();
        cons_anual_txt = new javax.swing.JLabel();
        cons_anual = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela_mes = new javax.swing.JTable();
        Botao_AlterarPreco = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        Output.setEditable(false);
        Output.setBackground(new java.awt.Color(239, 239, 239));
        Output.setMinimumSize(new java.awt.Dimension(1, 22));
        ScrollOutput.setViewportView(Output);

        getContentPane().setLayout(null);

        jPanel1.setMaximumSize(new java.awt.Dimension(500, 400));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 400));
        jPanel1.setLayout(null);

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
        prec.setBounds(200, 40, 70, 30);

        prec_txt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        prec_txt.setForeground(new java.awt.Color(255, 255, 255));
        prec_txt.setText("Preço unitário faturação");
        prec_txt.setToolTipText("");
        jPanel1.add(prec_txt);
        prec_txt.setBounds(50, 40, 140, 30);

        refr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refrMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                refrMouseReleased(evt);
            }
        });
        jPanel1.add(refr);
        refr.setBounds(400, 30, 50, 50);

        cons_anual_txt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cons_anual_txt.setForeground(new java.awt.Color(255, 255, 255));
        cons_anual_txt.setText("Consumo anual (kWh)");
        cons_anual_txt.setToolTipText("");
        jPanel1.add(cons_anual_txt);
        cons_anual_txt.setBounds(50, 80, 140, 30);

        cons_anual.setEditable(false);
        cons_anual.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cons_anual.setCaretColor(new java.awt.Color(204, 204, 204));
        cons_anual.setSelectionColor(new java.awt.Color(0, 0, 0));
        cons_anual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cons_anualActionPerformed(evt);
            }
        });
        jPanel1.add(cons_anual);
        cons_anual.setBounds(200, 80, 70, 30);

        tabela_mes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mês", "Consumo (kWh)", "Custo (€)"
            }
        ));
        jScrollPane1.setViewportView(tabela_mes);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(20, 120, 350, 190);

        Botao_AlterarPreco.setBackground(new java.awt.Color(204, 204, 204));
        Botao_AlterarPreco.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        Botao_AlterarPreco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/eur.png"))); // NOI18N
        Botao_AlterarPreco.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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
        Botao_AlterarPreco.setBounds(290, 35, 40, 40);

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

    private void Botao_AlterarPrecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Botao_AlterarPrecoActionPerformed
        try {
            DataBase t = new DataBase();
            double preco = 0;
            String novo = JOptionPane.showInputDialog(rootPane, "Alterar o preço :", "Novo preço", -1);
            
            if (novo != null) {
                if (novo.equals("") == false) {
                    novo=novo.replaceAll(",", ".");
                    preco = Double.parseDouble(novo);
                    if (t.alterar_preco(con, user, edificio, preco)) {
                        JOptionPane.showMessageDialog(null, "Preço alterado com sucesso!", "Sistema Central de Gestão", JOptionPane.PLAIN_MESSAGE);
                        Imprimir_Output();
                    } else {
                        JOptionPane.showMessageDialog(null, "Impossível alterar o preço!", "Sistema Central de Gestão", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        } catch (SQLException | ParseException ex) {
            Logger.getLogger(infor_edi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Botao_AlterarPrecoActionPerformed

    private void Botao_AlterarPrecoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Botao_AlterarPrecoKeyReleased


    }//GEN-LAST:event_Botao_AlterarPrecoKeyReleased

    private void precActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_precActionPerformed

    private void refrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refrMouseClicked
        
    }//GEN-LAST:event_refrMouseClicked

    private void refrMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refrMouseReleased

    }//GEN-LAST:event_refrMouseReleased

    private void cons_anualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cons_anualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cons_anualActionPerformed

    public void Imprimir_Output() throws SQLException, ParseException {
        DataBase t = new DataBase();
        String s = edificio;
        Output.setText(null);
        DecimalFormat df = new DecimalFormat("###,#####0.00000");
        DecimalFormat df_custo = new DecimalFormat("###,##0.00");
        DefaultTableModel val= (DefaultTableModel) tabela_mes.getModel();
        val.setRowCount(0);
        prec.setText("");


        if (s != null) {
            double preco = t.output_cliente_preco(con, s);
            if (preco != 0) {
                
                setVisible(true);
                //Output.append("Edificio : " + s);
               
                //Output.append("\nPreço : " + preco);
                prec.setText(String.valueOf(preco)+ " €");
                //Output.append("\nConsumo (kWh) : \tDia:\t Hora:\n");
                ResultSet resultset = t.output_cliente(con, s);

                
                double total=0;
                int mes=0;
                Calendar calendar = Calendar.getInstance();
		double val_ini=0;
                double val_fin=0;
                double valll=0;
                boolean inicio=false;
                //System.out.println(resultset);
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
                        
                        //adicionar na tabela
                        val.addRow(new String[] {getMES(mes),df.format(valll),df_custo.format(preco*valll)});
                        
                        //System.out.println("Valor final: "+df.format(val_fin));
                        total=total+valll;
                        valll=0;
                        val_ini=resultset.getDouble("valor");
                        mes=calendar.get(GregorianCalendar.MONTH);
                    }
  
                    val_fin=resultset.getDouble("valor");
                    
                    //Output.append(Double.toString(resultset.getDouble("valor")) + "\t\t");
                    //System.out.println(Double.toString(resultset.getDouble("valor")));
                    //Output.append(String.valueOf(resultset.getDate("timestamp_date")) + "\t");
                    //Output.append(String.valueOf(resultset.getTime("timestamp_time")) + "\n");
                    //double vall=resultset.getDouble("valor");
 
                }
                
                if(valll==0){

                        //System.out.println("Valor final: "+df.format(val_fin));
                        valll=val_fin-val_ini;
                        val.addRow(new String[] {getMES(mes),df.format(valll),df_custo.format(preco*valll)});
                        total=total+valll;
                }
 
                cons_anual.setText(df.format(total));
                
            } else {
                JOptionPane.showMessageDialog(null, "Edifício " + s + " sem dados.", "Sistema Central de Gestão", JOptionPane.PLAIN_MESSAGE);
                //Output.append("Edifício " + s + " sem dados.");
                                
            }
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Botao_AlterarPreco;
    public javax.swing.JTextArea Output;
    private javax.swing.JScrollPane ScrollOutput;
    private javax.swing.JTextField cons_anual;
    private javax.swing.JLabel cons_anual_txt;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField prec;
    private javax.swing.JLabel prec_txt;
    private javax.swing.JLabel refr;
    private javax.swing.JTable tabela_mes;
    // End of variables declaration//GEN-END:variables

}
