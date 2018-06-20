
package gestor;


import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JOptionPane;




public class SensoresAtivos extends javax.swing.JFrame{
    
    arranque a;
    ArrayList<concentrador> lista_conc;
    
    
    public SensoresAtivos(ArrayList<concentrador> l, arranque aa) {
        a=aa;
        lista_conc=l;
        initComponents();
        SensoresTransmitir.setEnabled(false);
        SensoresTransmitir.setForeground(Color.BLACK);
        SensoresAtivos.setEnabled(false);
        SensoresAtivos.setForeground(Color.BLACK);
        sens_soli();
        sens_nao_soli();
        start.setBackground(new java.awt.Color(174, 255, 174));
    }
    
    
    public void sens_nao_soli(){
    
        boolean existe=false;
        
        for(concentrador c:lista_conc){    
            //acede aos sensores do concentrador
            for(sensor ss : c.acedeLista_sens()){              
               for(sensor sss : c.acedeLista_sens_START()){
                   if(existe==false){
                       if(ss.cod_sens==sss.cod_sens){
                           existe=true;
                       }
                   }
               }
               if(existe==false){
                    nao_solicitados.addItem("Sensor"+Integer.toString(ss.cod_sens));
               } 
               existe=false;
     
            }
        }
                
    }
    
    
    public void sens_soli(){
    
        for(concentrador c:lista_conc){    
            //acede aos sensores do concentrador
            for(sensor ss : c.acedeLista_sens_START()){              
                //lista_sens_con.add("\n Sensor "+ss.cod_sens+":\n Concentrador "+c.numConc+"; Área "+ss.area+" ("+ss.designacao+")\n");
                solicitados.addItem("Sensor"+Integer.toString(ss.cod_sens));
            
            }
        }
                
    }

    
    
     

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nao_solicitados = new javax.swing.JComboBox<>();
        inf_nao_sol = new javax.swing.JLabel();
        inf_sol = new javax.swing.JLabel();
        SensoresTransmitir = new javax.swing.JButton();
        solicitados = new javax.swing.JComboBox<>();
        start = new javax.swing.JToggleButton();
        SensoresAtivos = new javax.swing.JButton();
        naoseioquee = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(520, 325));
        getContentPane().setLayout(null);

        nao_solicitados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nao_solicitadosActionPerformed(evt);
            }
        });
        getContentPane().add(nao_solicitados);
        nao_solicitados.setBounds(70, 50, 150, 30);

        inf_nao_sol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/inf.png"))); // NOI18N
        inf_nao_sol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inf_nao_solMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                inf_nao_solMouseReleased(evt);
            }
        });
        getContentPane().add(inf_nao_sol);
        inf_nao_sol.setBounds(30, 45, 30, 40);

        inf_sol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/inf.png"))); // NOI18N
        inf_sol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inf_solMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                inf_solMouseReleased(evt);
            }
        });
        getContentPane().add(inf_sol);
        inf_sol.setBounds(390, 45, 30, 40);

        SensoresTransmitir.setBackground(new java.awt.Color(255, 102, 0));
        SensoresTransmitir.setText("Solicitados");
        SensoresTransmitir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SensoresTransmitirActionPerformed(evt);
            }
        });
        getContentPane().add(SensoresTransmitir);
        SensoresTransmitir.setBounds(230, 20, 150, 30);

        solicitados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solicitadosActionPerformed(evt);
            }
        });
        getContentPane().add(solicitados);
        solicitados.setBounds(230, 50, 150, 30);

        start.setBackground(new java.awt.Color(255, 149, 149));
        start.setSelected(true);
        start.setText("START");
        start.setActionCommand("START/STOP");
        start.setAutoscrolls(true);
        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startActionPerformed(evt);
            }
        });
        getContentPane().add(start);
        start.setBounds(70, 100, 150, 30);

        SensoresAtivos.setBackground(new java.awt.Color(255, 102, 0));
        SensoresAtivos.setText("Não solicitados");
        SensoresAtivos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        SensoresAtivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SensoresAtivosActionPerformed(evt);
            }
        });
        getContentPane().add(SensoresAtivos);
        SensoresAtivos.setBounds(70, 20, 150, 30);
        SensoresAtivos.getAccessibleContext().setAccessibleDescription("");

        naoseioquee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/luz2.jpg"))); // NOI18N
        naoseioquee.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        naoseioquee.setMaximumSize(new java.awt.Dimension(350, 270));
        naoseioquee.setMinimumSize(new java.awt.Dimension(350, 270));
        naoseioquee.setPreferredSize(new java.awt.Dimension(350, 270));
        getContentPane().add(naoseioquee);
        naoseioquee.setBounds(-80, -90, 580, 380);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void solicitadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solicitadosActionPerformed

        //Edificio selecionado
        String s = (String) solicitados.getSelectedItem();
        //DataBase t=new DataBase();
        //lista_Edif = t.pesquisar_edificio_user(con, nome.getText());
    }//GEN-LAST:event_solicitadosActionPerformed

    private void SensoresTransmitirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SensoresTransmitirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SensoresTransmitirActionPerformed

    private void SensoresAtivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SensoresAtivosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SensoresAtivosActionPerformed

    private void nao_solicitadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nao_solicitadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nao_solicitadosActionPerformed

    private void inf_nao_solMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inf_nao_solMouseReleased
        

        if(nao_solicitados.getSelectedItem()==null){
            
        }
        else{
            String s = (String) nao_solicitados.getSelectedItem();
            s=s.substring(6);
            //System.out.println("obtido:"+s);

            int cod=Integer.parseInt(s); 
            
            sensor sens=null;
            concentrador con=null;
            
            for(concentrador c:lista_conc){       
                 //acede aos sensores do concentrador
                 for(sensor ss : c.acedeLista_sens()){
                     if(cod==ss.cod_sens){
                         sens=ss;
                         con=c;
                     }
                 } 
            }
            JOptionPane.showMessageDialog(null, " Concentrador "+con.numConc+"\n Área "+sens.area+": "+sens.designacao, "Sensor "+s, JOptionPane.INFORMATION_MESSAGE);
            
        }

       
    }//GEN-LAST:event_inf_nao_solMouseReleased

    private void inf_nao_solMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inf_nao_solMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_inf_nao_solMouseClicked

    private void inf_solMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inf_solMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_inf_solMouseClicked

    private void inf_solMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inf_solMouseReleased
        if(solicitados.getSelectedItem()==null){
            
        }
        else{
            String s = (String) solicitados.getSelectedItem();
            s=s.substring(6);
            //System.out.println("obtido:"+s);
            
        
            int cod=Integer.parseInt(s); 
            
            sensor sens=null;
            concentrador con=null;
            for(concentrador c:lista_conc){       
                 //acede aos sensores do concentrador
                 for(sensor ss : c.acedeLista_sens_START()){
                     if(cod==ss.cod_sens){
                         sens=ss;
                         con=c;
                     }
                 } 
            }
            JOptionPane.showMessageDialog(null, " Concentrador "+con.numConc+"\n Área "+sens.area+": "+sens.designacao, "Sensor "+s, JOptionPane.INFORMATION_MESSAGE);
            
        }
    }//GEN-LAST:event_inf_solMouseReleased

    private void startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startActionPerformed
            if(nao_solicitados.getSelectedItem()==null){
            
            }
            else{
                
                String s = (String) solicitados.getSelectedItem();
                s=s.substring(6);
                //System.out.println("obtido:"+s);


                int cod=Integer.parseInt(s); 
                sensor sens=null;
                concentrador con=null;

                for(concentrador c:lista_conc){       
                     //acede aos sensores do concentrador
                     for(sensor ss : c.acedeLista_sens()){
                         if(cod==ss.cod_sens){
                             sens=ss;
                             con=c;
                         }
                     } 
                }
                
                a.START_unico(con);
                
                setVisible(false);
                //System.out.println("Start individual enviado");
            }
            
    }//GEN-LAST:event_startActionPerformed

   
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SensoresAtivos;
    private javax.swing.JButton SensoresTransmitir;
    private javax.swing.JLabel inf_nao_sol;
    private javax.swing.JLabel inf_sol;
    private javax.swing.JComboBox<String> nao_solicitados;
    private javax.swing.JLabel naoseioquee;
    private javax.swing.JComboBox<String> solicitados;
    private javax.swing.JToggleButton start;
    // End of variables declaration//GEN-END:variables

   
  
}
//}
    



