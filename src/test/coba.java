/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.k33ptoo.components.KButton;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author Kahfi
 */
public class coba extends javax.swing.JFrame {
    int value; 

    /**
     * Creates new form coba
     */
    public coba() {
        initComponents();
      
    }
    
    public void setColor(KButton button){
        button.setSelected(true);
      
    }
    public void resetColor(KButton button){
        button.setSelected(false);
        
    }
    
    public int faktorial(int value){
        int result = 1; 
        if(value==1){
           return result; 
        }else{
            result = value * faktorial(value-1); 
            return result; 
            
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buttonAbout = new com.k33ptoo.components.KButton();
        fieldAngka = new textfield.TextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        buttonAbout.setText("Hitung");
        buttonAbout.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        buttonAbout.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        buttonAbout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonAbout.setIconTextGap(11);
        buttonAbout.setkEndColor(new java.awt.Color(255, 255, 255));
        buttonAbout.setkForeGround(new java.awt.Color(0, 0, 0));
        buttonAbout.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        buttonAbout.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        buttonAbout.setkHoverStartColor(new java.awt.Color(153, 153, 255));
        buttonAbout.setkPressedColor(new java.awt.Color(153, 153, 255));
        buttonAbout.setkSelectedColor(new java.awt.Color(153, 153, 255));
        buttonAbout.setkStartColor(new java.awt.Color(255, 255, 255));
        buttonAbout.setMargin(new java.awt.Insets(2, 14, 10, 14));
        buttonAbout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonAboutMousePressed(evt);
            }
        });
        buttonAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAboutActionPerformed(evt);
            }
        });

        fieldAngka.setLabelText("Masukan Angka");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(378, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonAbout, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addComponent(fieldAngka, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(353, 353, 353))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addComponent(fieldAngka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonAbout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(189, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAboutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonAboutMousePressed
//        setColor(buttonAbout);
//        resetColor(buttonMenu); 
//        resetColor(buttonDashboard);
    }//GEN-LAST:event_buttonAboutMousePressed

    private void buttonAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAboutActionPerformed
      int value = Integer.valueOf(fieldAngka.getText());
      int result = faktorial(value);
        JOptionPane.showMessageDialog(null, "Hasil Faktorial dari " + value + " Adalah " + result);
      
    }//GEN-LAST:event_buttonAboutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(coba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(coba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(coba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(coba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new coba().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.k33ptoo.components.KButton buttonAbout;
    private textfield.TextField fieldAngka;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}