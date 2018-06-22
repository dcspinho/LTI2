
package gestor;


import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;




public class Config extends javax.swing.JFrame{
    
    arranque a;
    ArrayList<concentrador> lista_conc;
    ConexaoMySQL conex;
    String area_selecionada="";
    
    public Config(arranque aa, ConexaoMySQL c) {
        
        conex=c;
        a=aa;
        initComponents();
        areas_bot.setEnabled(false);
        areas_bot.setForeground(Color.BLACK);
        sens_bot.setEnabled(false);
        sens_bot.setForeground(Color.BLACK);
        lista_area();
    }
    

    public void lista_area(){
        areas_list.removeAllItems();
        
        HashMap<Integer, String> lista_area=conex.area_designacao;
        
        for(Integer i: lista_area.keySet()){         
            areas_list.addItem(lista_area.get(i));
        }
        sens_list.removeAllItems();
    }
    

    public boolean Inteiro( String s ) {
        // cria um array de char
        char[] c = s.toCharArray();
        boolean d = true;
        for ( int i = 0; i < c.length; i++ ){
            // verifica se o char não é um dígito
            if ( !Character.isDigit( c[ i ] ) ) {
                d = false;
                break;
            }
        }
        return d;
    }
    
    
    
    public void lista_sens(){
            sens_list.removeAllItems();
        
            area_selecionada= (String) areas_list.getSelectedItem();
            
            int cod=0;
            
            HashMap<Integer, String> lista_area=conex.area_designacao;
            for (Integer i:lista_area.keySet()){
                if(lista_area.get(i).equals(area_selecionada)){
                    cod=i;
                }
            }
            
            ArrayList<Integer> lista_sens=conex.sensores(cod);
            
            for(int i: lista_sens){
                //System.out.println("sensor: "+i);
                sens_list.addItem("Sensor "+i);
            }
    }
    
    
    
    public boolean existeSensor(int num_sen){
        HashMap<Integer, String> lista_area=conex.area_designacao;
        
        for(Integer i:lista_area.keySet()){
            ArrayList<Integer> lista_sens=conex.sensores(i);
            for(int ii:lista_sens){
                if(ii==num_sen){
                    return true;
                }
            }
        
        }
        
        return false;
        
    
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        areas_bot = new javax.swing.JButton();
        areas_list = new javax.swing.JComboBox<>();
        sens_list = new javax.swing.JComboBox<>();
        sens_bot = new javax.swing.JButton();
        area_rem = new javax.swing.JButton();
        area_add = new javax.swing.JButton();
        cons = new javax.swing.JButton();
        sensor_add = new javax.swing.JButton();
        sens_rem = new javax.swing.JButton();
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

        area_rem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/remo.png"))); // NOI18N
        area_rem.setToolTipText("");
        area_rem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                area_remActionPerformed(evt);
            }
        });
        getContentPane().add(area_rem);
        area_rem.setBounds(330, 35, 40, 40);

        area_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/add.png"))); // NOI18N
        area_add.setToolTipText("");
        area_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                area_addActionPerformed(evt);
            }
        });
        getContentPane().add(area_add);
        area_add.setBounds(380, 35, 40, 40);

        cons.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/view.png"))); // NOI18N
        cons.setToolTipText("");
        cons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consActionPerformed(evt);
            }
        });
        getContentPane().add(cons);
        cons.setBounds(280, 35, 40, 40);

        sensor_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/add.png"))); // NOI18N
        sensor_add.setToolTipText("");
        sensor_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sensor_addActionPerformed(evt);
            }
        });
        getContentPane().add(sensor_add);
        sensor_add.setBounds(330, 145, 40, 40);

        sens_rem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/remo.png"))); // NOI18N
        sens_rem.setToolTipText("");
        sens_rem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sens_remActionPerformed(evt);
            }
        });
        getContentPane().add(sens_rem);
        sens_rem.setBounds(280, 145, 40, 40);

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
            lista_sens();
 
    }//GEN-LAST:event_consActionPerformed

    private void area_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_area_addActionPerformed

            String novo = JOptionPane.showInputDialog(rootPane, "Nova área:", "Adicionar área " , -1);
            
            if (novo != null) {
                /*if (novo.equals("") == false) {
                    
                    //ver se nao existe e adicionar
                    if () {
                        JOptionPane.showMessageDialog(null, "Área adicionada com sucesso!", "Adicionar área", JOptionPane.PLAIN_MESSAGE);
                       
                        //atualizar a lista na interface
                        HashMap<Integer, String> lista_area=conex.area_designacao;
                        sens_area.removeAllItems();
                        for(Integer i: lista_area.keySet()){         
                            areas_list.addItem(lista_area.get(i));
                        }
                        
                         //nao esquecer de atualizar na base de dados
                
                
                    } else {    //nao foi adicionado
                        JOptionPane.showMessageDialog(null, "Impossível adicionar área!", "Adicionar área", JOptionPane.ERROR_MESSAGE);
                    }
                }*/
            }

    }//GEN-LAST:event_area_addActionPerformed

    private void area_remActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_area_remActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_area_remActionPerformed

    private void sens_listActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sens_listActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sens_listActionPerformed

    private void sensor_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sensor_addActionPerformed
                    
        
            if(area_selecionada.equals("")==false){
                String novo = JOptionPane.showInputDialog(rootPane, "Número do sensor:", "Adicionar sensor: "+area_selecionada  , -1);
            
                if (Inteiro(novo)) {

                    //ver se nao existe e adicionar
                    if (existeSensor(Integer.parseInt(novo))==false) {

                            int cod=0;
                            HashMap<Integer, String> lista_area=conex.area_designacao;
                            for (Integer i:lista_area.keySet()){
                                if(lista_area.get(i).equals(area_selecionada)){
                                    cod=i;
                                }
                            }
                            
                            conex.adicionar_areaCsensores(cod,Integer.parseInt(novo));
                            JOptionPane.showMessageDialog(null, "Sensor "+Integer.parseInt(novo)+" adiciondo com sucesso!", "Adicionar sensor: "+area_selecionada, JOptionPane.PLAIN_MESSAGE);
                            
                            lista_sens();
                            
                            
          
                            
                            //**************nao esquecer de atualizar na base de dados
                        
                            
  
                        
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Sensor existente!", "Adicionar sensor: "+area_selecionada, JOptionPane.ERROR_MESSAGE);
                    }
                   
                } else {    //nao foi adicionado
                     JOptionPane.showMessageDialog(null, "Número de sensor inválido!", "Adicionar sensor: "+area_selecionada, JOptionPane.ERROR_MESSAGE);   
                }

            }
            else{
                JOptionPane.showMessageDialog(null, "Selecione a área!", "Configurações", JOptionPane.ERROR_MESSAGE);   
            }
                
           
        
    }//GEN-LAST:event_sensor_addActionPerformed

    private void sens_remActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sens_remActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sens_remActionPerformed

   
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton area_add;
    private javax.swing.JButton area_rem;
    private javax.swing.JButton areas_bot;
    private javax.swing.JComboBox<String> areas_list;
    private javax.swing.JButton cons;
    private javax.swing.JLabel naoseioquee;
    private javax.swing.JButton sens_bot;
    private javax.swing.JComboBox<String> sens_list;
    private javax.swing.JButton sens_rem;
    private javax.swing.JButton sensor_add;
    // End of variables declaration//GEN-END:variables

   
  
}

    



