/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import koneksi.koneksi;

/**
 *
 * @author Kahfi
 */
public class lupaAkun extends javax.swing.JFrame {

    PreparedStatement pst;
    ResultSet set;
    String username, password;

    /**
     * Creates new form lupaAkun
     */
    public lupaAkun() {
        initComponents();

        labelError.setVisible(false);
        buttonCariUsername.requestFocus();
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    public void komponen() {
        this.username = fieldUsername.getText().trim();
        this.password = fieldPassword.getText().trim();
    }

    public void searchUsername() {
        komponen();
        try {

            String sql = "select username from tb_user where username=?";
            this.pst = koneksi.getKoneksi().prepareStatement(sql);
            this.pst.setString(1, username);
            this.set = pst.executeQuery();
            if (set.next()) {

                MainPanel.removeAll();
                MainPanel.repaint();
                MainPanel.revalidate();

                // add new panel
                MainPanel.add(PanelGantiPassword);
                MainPanel.repaint();
                MainPanel.revalidate();

            } else {
                labelError.setVisible(true);
                labelError.setForeground(Color.RED);
                labelError.setText("Username " + username + " Tidak Ditemukan!");
            }

        } catch (Exception e) {
        }
    }
    
    public void gantiPassword(){
        komponen();
     
        try {
            String sql = "update tb_user set password=? where username=?";
            this.pst = koneksi.getKoneksi().prepareStatement(sql); 
            this.pst.setString(1, password);
            this.pst.setString(2, username);
            this.pst.executeUpdate(); 
            JOptionPane.showMessageDialog(null, "Password Berhasil Diubah", "Pesan Dari Admin", JOptionPane.PLAIN_MESSAGE);
            JOptionPane.showMessageDialog(null, "Silahkan login kembali!",  "Pesan Dari Admin", JOptionPane.PLAIN_MESSAGE);
            this.dispose();
        } catch (Exception e) {
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

        MainPanel = new javax.swing.JPanel();
        PanelCariUsername = new javax.swing.JPanel();
        buttonCariUsername = new com.k33ptoo.components.KButton();
        labelError = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        PanelGantiPassword = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        fieldPassword = new textfield.PasswordField();
        jPanel2 = new javax.swing.JPanel();
        buttonCariUsername1 = new com.k33ptoo.components.KButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MainPanel.setBackground(new java.awt.Color(255, 255, 255));
        MainPanel.setLayout(new java.awt.CardLayout());

        PanelCariUsername.setBackground(new java.awt.Color(255, 255, 255));
        PanelCariUsername.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonCariUsername.setText("Cari Username");
        buttonCariUsername.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        buttonCariUsername.setkBackGroundColor(new java.awt.Color(51, 0, 255));
        buttonCariUsername.setkBorderRadius(20);
        buttonCariUsername.setkEndColor(new java.awt.Color(51, 51, 255));
        buttonCariUsername.setkHoverEndColor(new java.awt.Color(102, 102, 255));
        buttonCariUsername.setkHoverForeGround(new java.awt.Color(204, 204, 204));
        buttonCariUsername.setkHoverStartColor(new java.awt.Color(51, 51, 255));
        buttonCariUsername.setkStartColor(new java.awt.Color(51, 51, 255));
        buttonCariUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCariUsernameActionPerformed(evt);
            }
        });
        PanelCariUsername.add(buttonCariUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(552, 580, 320, -1));

        fieldUsername.setBackground(new java.awt.Color(237, 245, 244));
        fieldUsername.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        fieldUsername.setText("Masukkan Username !");
        fieldUsername.setBorder(null);
        fieldUsername.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        fieldUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldUsernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldUsernameFocusLost(evt);
            }
        });
        fieldUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldUsernameActionPerformed(evt);
            }
        });
        fieldUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fieldUsernameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fieldUsernameKeyReleased(evt);
            }
        });
        PanelCariUsername.add(fieldUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 520, 300, 40));

        labelError.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        labelError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelError.setText("Error Message");
        PanelCariUsername.add(labelError, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 640, 320, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bg lupa password (1).png"))); // NOI18N
        PanelCariUsername.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 1280, 780));

        MainPanel.add(PanelCariUsername, "card2");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fieldPassword.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        fieldPassword.setText("masukan password");
        fieldPassword.setCaretColor(new java.awt.Color(255, 255, 255));
        fieldPassword.setLabelText("");
        fieldPassword.setLineColor(new java.awt.Color(255, 255, 255));
        fieldPassword.setShowAndHide(true);
        fieldPassword.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fieldPasswordPropertyChange(evt);
            }
        });
        jPanel1.add(fieldPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 440, 260, 40));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        buttonCariUsername1.setForeground(new java.awt.Color(51, 51, 51));
        buttonCariUsername1.setText("Ganti Password");
        buttonCariUsername1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        buttonCariUsername1.setkBackGroundColor(new java.awt.Color(164, 225, 244));
        buttonCariUsername1.setkBorderRadius(20);
        buttonCariUsername1.setkEndColor(new java.awt.Color(164, 225, 244));
        buttonCariUsername1.setkForeGround(new java.awt.Color(51, 51, 51));
        buttonCariUsername1.setkHoverEndColor(new java.awt.Color(102, 102, 255));
        buttonCariUsername1.setkHoverForeGround(new java.awt.Color(204, 204, 204));
        buttonCariUsername1.setkHoverStartColor(new java.awt.Color(51, 51, 255));
        buttonCariUsername1.setkStartColor(new java.awt.Color(164, 225, 244));
        buttonCariUsername1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCariUsername1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(buttonCariUsername1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(buttonCariUsername1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 55, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 500, 320, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Masukan Password Baru");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 400, 160, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageBackground/Desktop - 4Form new password3.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 870, 660));

        javax.swing.GroupLayout PanelGantiPasswordLayout = new javax.swing.GroupLayout(PanelGantiPassword);
        PanelGantiPassword.setLayout(PanelGantiPasswordLayout);
        PanelGantiPasswordLayout.setHorizontalGroup(
            PanelGantiPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelGantiPasswordLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PanelGantiPasswordLayout.setVerticalGroup(
            PanelGantiPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelGantiPasswordLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 962, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 604, Short.MAX_VALUE))
        );

        MainPanel.add(PanelGantiPassword, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void fieldUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldUsernameFocusGained
        // TODO add your handling code here:
        if (fieldUsername.getText().equals("Masukkan Username !")) {
            fieldUsername.setText("");

        }
    }//GEN-LAST:event_fieldUsernameFocusGained

    private void fieldUsernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldUsernameFocusLost
        // TODO add your handling code here:
        if (fieldUsername.getText().equals("")) {
            fieldUsername.setText("Masukkan Username !");

        }
    }//GEN-LAST:event_fieldUsernameFocusLost

    private void fieldUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldUsernameActionPerformed

    private void fieldUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldUsernameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

        }
    }//GEN-LAST:event_fieldUsernameKeyPressed

    private void fieldUsernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldUsernameKeyReleased
        komponen();
        if (username.equals("") || username.equals("Masukkan Username !")) {
            labelError.setVisible(false);
        }
    }//GEN-LAST:event_fieldUsernameKeyReleased

    private void buttonCariUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCariUsernameActionPerformed
       komponen();
       if(username.equals("Masukkan Username !") || username.equals("")){
           labelError.setVisible(true);
           labelError.setForeground(Color.RED);
           labelError.setText("Username tidak boleh kosong!");
           
       }else{
           searchUsername();
       }
       
       


    }//GEN-LAST:event_buttonCariUsernameActionPerformed

    private void buttonCariUsername1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCariUsername1ActionPerformed
        komponen();
        if(password.equals("masukan password")){
            JOptionPane.showMessageDialog(null, "Password tidak boleh kosong!", "Pesan Dari Admin", JOptionPane.WARNING_MESSAGE);
        }else{
            gantiPassword();
        }
    }//GEN-LAST:event_buttonCariUsername1ActionPerformed

    private void fieldPasswordPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fieldPasswordPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldPasswordPropertyChange

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
            java.util.logging.Logger.getLogger(lupaAkun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(lupaAkun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(lupaAkun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(lupaAkun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new lupaAkun().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MainPanel;
    private javax.swing.JPanel PanelCariUsername;
    private javax.swing.JPanel PanelGantiPassword;
    private com.k33ptoo.components.KButton buttonCariUsername;
    private com.k33ptoo.components.KButton buttonCariUsername1;
    private textfield.PasswordField fieldPassword;
    public static final javax.swing.JTextField fieldUsername = new javax.swing.JTextField();
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelError;
    // End of variables declaration//GEN-END:variables
}
