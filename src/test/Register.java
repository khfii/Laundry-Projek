/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Login.Login;

/**
 *
 * @author Kahfi
 */
public class Register extends javax.swing.JFrame {

    /**
     * Creates new form register
     */
    public Register() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Register = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        kButton2 = new com.k33ptoo.components.KButton();
        kButton3 = new com.k33ptoo.components.KButton();
        bacckground1 = new javax.swing.JLabel();
        bacckground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Register");

        Register.setBackground(new java.awt.Color(255, 255, 255));
        Register.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Pengguna");
        Register.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 300, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Anggota");
        Register.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N
        jLabel4.setText("Masuk sebagai pengguna?");
        Register.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 230, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel5.setText("Seseorang yang masuk sebagai ");
        Register.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 260, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel6.setText("dan memesan paket yang ditawarkan");
        Register.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 300, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel8.setText("Masuk sebagai anggota?");
        Register.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 80, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel9.setText("Seseorang yang masuk sebagai ");
        Register.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 110, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel10.setText("anggota memiliki akses untuk melihat");
        Register.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 130, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel11.setText("semua detail aplikasi, termasuk proses");
        Register.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 150, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel12.setText("tambah, edit, delete dan detail transaksi");
        Register.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 170, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel13.setText("pengguna memiliki akses untuk melihat");
        Register.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 280, -1, -1));

        kButton2.setText("Masuk sebagai pengguna");
        kButton2.setBorderPainted(false);
        kButton2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        kButton2.setkBackGroundColor(new java.awt.Color(66, 95, 235));
        kButton2.setkEndColor(new java.awt.Color(66, 95, 235));
        kButton2.setkHoverEndColor(new java.awt.Color(102, 51, 255));
        kButton2.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton2.setkHoverStartColor(new java.awt.Color(102, 51, 255));
        kButton2.setkPressedColor(new java.awt.Color(102, 51, 255));
        kButton2.setkSelectedColor(new java.awt.Color(66, 95, 235));
        kButton2.setkStartColor(new java.awt.Color(66, 95, 235));
        kButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton2ActionPerformed(evt);
            }
        });
        Register.add(kButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 340, 200, 30));

        kButton3.setText("Masuk sebaga anggota");
        kButton3.setBorderPainted(false);
        kButton3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        kButton3.setkBackGroundColor(new java.awt.Color(66, 95, 235));
        kButton3.setkEndColor(new java.awt.Color(66, 95, 235));
        kButton3.setkHoverEndColor(new java.awt.Color(102, 51, 255));
        kButton3.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton3.setkHoverStartColor(new java.awt.Color(102, 51, 255));
        kButton3.setkPressedColor(new java.awt.Color(102, 51, 255));
        kButton3.setkSelectedColor(new java.awt.Color(66, 95, 235));
        kButton3.setkStartColor(new java.awt.Color(66, 95, 235));
        kButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton3ActionPerformed(evt);
            }
        });
        Register.add(kButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, 200, 30));

        bacckground1.setForeground(new java.awt.Color(51, 51, 51));
        bacckground1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/illustration/salah login 5x.png"))); // NOI18N
        Register.add(bacckground1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, 550, 360));

        bacckground.setForeground(new java.awt.Color(51, 51, 51));
        bacckground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageBackground/bg_choose_login.png"))); // NOI18N
        Register.add(bacckground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 590));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Register, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Register, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void kButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton2ActionPerformed
      new loginPenggguna().setVisible(true);
      this.dispose();

    }//GEN-LAST:event_kButton2ActionPerformed

    private void kButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton3ActionPerformed
      new Login().setVisible(true);
      this.dispose();
    }//GEN-LAST:event_kButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Register;
    private javax.swing.JLabel bacckground;
    private javax.swing.JLabel bacckground1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.k33ptoo.components.KButton kButton2;
    private com.k33ptoo.components.KButton kButton3;
    // End of variables declaration//GEN-END:variables
}
