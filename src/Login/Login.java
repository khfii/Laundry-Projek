package Login;
import koneksi.koneksi;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import DashboardAdmin.Admin;
import user.TambahUser;
import test.Register;
import CostumNotification.Notification; 
import DashboardAdmin.Admin;
import DashboardKasir.Kasir;
import Main.welcome;
import com.sun.glass.events.KeyEvent;

/**
 *
 * @author Kahfi
 */
public class Login extends javax.swing.JFrame {
    int loginLimit; 
    public static String IDpetugas, namaPetugas, usernamePetugas, password, idOutlet, hakAkses;

    /**
     * Creates new form login
     */
    public Login() {
        initComponents();
        labelLupaAkun.setVisible(false);
        labelKlikDisini.setVisible(false); 
        IDOutlet();
        this.setExtendedState(MAXIMIZED_BOTH);

    }

    public void komponen() {
       
        this.usernamePetugas = fieldUsername.getText().trim(); 
        this.password = fieldPassword.getText().trim();
        this.hakAkses = comboHakAkses.getSelectedItem().toString();
        
      


    }
    
   
    
    
    

    

    public void IDOutlet() {
        try {
            Connection con = koneksi.getKoneksi();
            Statement stat = con.createStatement();
            String query = "select id_outlet from tb_outlet";
            ResultSet set = stat.executeQuery(query);
            while (set.next()) {
                String id_outlet = set.getString("id_outlet");
                labelIDOutlet1.setText(id_outlet + " | ");

                
            }

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

        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        kButton1 = new com.k33ptoo.components.KButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comboHakAkses = new combo_suggestion.ComboBoxSuggestion();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        kButton2 = new com.k33ptoo.components.KButton();
        labelKlikDisini = new javax.swing.JLabel();
        labelLupaAkun = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        label1 = new javax.swing.JLabel();
        labelIDOutlet1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImages(null);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel13.setText("Login");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 80, 50, 40));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 49, 1200, -1));

        jPanel3.setBackground(new java.awt.Color(255, 51, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Lakukan Login untuk mengakses");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 430, -1, -1));

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Dashboard!");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 450, -1, -1));

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("atau");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 490, -1, -1));

        kButton1.setBackground(new java.awt.Color(66, 95, 235));
        kButton1.setText("kembali ke halaman sebelumnya");
        kButton1.setBorderPainted(false);
        kButton1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        kButton1.setkBackGroundColor(new java.awt.Color(66, 95, 235));
        kButton1.setkEndColor(new java.awt.Color(66, 95, 235));
        kButton1.setkHoverEndColor(new java.awt.Color(45, 182, 186));
        kButton1.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton1.setkHoverStartColor(new java.awt.Color(45, 182, 186));
        kButton1.setkPressedColor(new java.awt.Color(66, 95, 235));
        kButton1.setkSelectedColor(new java.awt.Color(66, 95, 235));
        kButton1.setkStartColor(new java.awt.Color(66, 95, 235));
        kButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(kButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 510, 210, 30));

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Login Petugas");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 110, 150, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Laundry image/icons8_baseball_cap_20px.png"))); // NOI18N
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(798, 170, 20, 20));

        comboHakAkses.setBorder(null);
        comboHakAkses.setForeground(new java.awt.Color(102, 102, 102));
        comboHakAkses.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "owner", "admin", "kasir" }));
        comboHakAkses.setFocusable(false);
        jPanel3.add(comboHakAkses, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 170, 180, 20));

        fieldUsername.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        fieldUsername.setForeground(new java.awt.Color(51, 51, 51));
        fieldUsername.setText("Masukkan Username !");
        fieldUsername.setBorder(null);
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
        jPanel3.add(fieldUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 230, 190, 20));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/user.png"))); // NOI18N
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 230, -1, 20));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/padlock.png"))); // NOI18N
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 280, 20, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/textfield.png"))); // NOI18N
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 150, 250, 60));

        kButton2.setText("LogIn");
        kButton2.setBorderPainted(false);
        kButton2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        kButton2.setkBackGroundColor(new java.awt.Color(66, 95, 235));
        kButton2.setkEndColor(new java.awt.Color(66, 95, 235));
        kButton2.setkHoverEndColor(new java.awt.Color(45, 182, 186));
        kButton2.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton2.setkHoverStartColor(new java.awt.Color(45, 182, 186));
        kButton2.setkPressedColor(new java.awt.Color(66, 95, 235));
        kButton2.setkSelectedColor(new java.awt.Color(66, 95, 235));
        kButton2.setkStartColor(new java.awt.Color(66, 95, 235));
        kButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(kButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 340, 250, 30));

        labelKlikDisini.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        labelKlikDisini.setForeground(new java.awt.Color(0, 51, 255));
        labelKlikDisini.setText("<html>\n<u>klik disini </u>\n</html>");
        labelKlikDisini.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelKlikDisiniMouseClicked(evt);
            }
        });
        jPanel3.add(labelKlikDisini, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 380, -1, -1));

        labelLupaAkun.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        labelLupaAkun.setForeground(new java.awt.Color(102, 102, 102));
        labelLupaAkun.setText("Lupa Akun?");
        jPanel3.add(labelLupaAkun, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 380, -1, -1));

        fieldPassword.setForeground(new java.awt.Color(51, 51, 51));
        fieldPassword.setText("Masukkan Password !");
        fieldPassword.setBorder(null);
        fieldPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldPasswordFocusLost(evt);
            }
        });
        fieldPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldPasswordActionPerformed(evt);
            }
        });
        fieldPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fieldPasswordKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fieldPasswordKeyReleased(evt);
            }
        });
        jPanel3.add(fieldPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 280, 180, 30));

        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bg login.png"))); // NOI18N
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 570));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/padlock.png"))); // NOI18N
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(796, 286, 20, 20));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 89, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Laundry App");

        label1.setBackground(new java.awt.Color(255, 255, 255));
        label1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        label1.setForeground(new java.awt.Color(102, 102, 102));
        label1.setText(" | ID Outlet  ");

        labelIDOutlet1.setBackground(new java.awt.Color(255, 255, 255));
        labelIDOutlet1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        labelIDOutlet1.setForeground(new java.awt.Color(102, 102, 102));
        labelIDOutlet1.setText("| 0001");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Laundry image/icons8_cancel_30px.png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(labelIDOutlet1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1067, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(labelIDOutlet1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(729, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 770));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void fieldPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldPasswordKeyReleased

    }//GEN-LAST:event_fieldPasswordKeyReleased

    private void fieldPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldPasswordFocusLost
        // TODO add your handling code here:
        if (fieldPassword.getText().equals("")) {
            fieldPassword.setText("Masukkan Password !");
            fieldPassword.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_fieldPasswordFocusLost

    private void fieldPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldPasswordFocusGained
        if(fieldPassword.getText().equals("Masukkan Password !")){
            fieldPassword.setText("");
            fieldPassword.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_fieldPasswordFocusGained

    private void kButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton2ActionPerformed
        komponen();

        if (usernamePetugas.equals("Masukkan Username !")) {
            Notification notif = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Username tidak boleh kosong!");
            notif.showNotification();
        } else if (password.equals("Masukkan Password !")) {
            Notification notif = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Password tidak boleh kosong!!");
            notif.showNotification();

        } else {
            try {
                String sql = "select * from tb_user where username = '" + usernamePetugas + "' and password = '" + password + "' and role = '" + hakAkses + "'";

                Connection con = koneksi.getKoneksi();
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet set = pst.executeQuery(sql);

                if (set.next()) {
                  
                    IDpetugas = set.getString("id_user");
                    namaPetugas = set.getString("nama");
                    usernamePetugas = set.getString("username");
                    Admin admin = new Admin();
                    
                    System.out.println(set.getString("nama"));

                    if (hakAkses.equals("admin")) {

                        Notification notif = new Notification(this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Login Berhasil!");
                        notif.showNotification();
                        new Admin().setVisible(true);
                        this.dispose();

                      

                    } else if (hakAkses.equals("owner")) {

                        Notification notif = new Notification(this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Login Berhasil!");
                        notif.showNotification();
                        admin.setVisible(true);
                        this.dispose();

                    } else if (hakAkses.equals("kasir")) {
                        Notification notif = new Notification(this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Login Berhasil!");
                        notif.showNotification();
                        new Kasir().setVisible(true); 
                        this.dispose();
                       
                    }

                } else {
                    loginLimit+=1; 
                    Notification notif = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Login Gagal, " + usernamePetugas + " bukanlah " + hakAkses );
                    notif.showNotification();
                  if(loginLimit==3){
                      labelLupaAkun.setVisible(true);
                      labelKlikDisini.setVisible(true); 
                  }
                    

                }

            } catch (Exception e) {
            }
        }

       
    }//GEN-LAST:event_kButton2ActionPerformed

    private void fieldUsernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldUsernameKeyReleased

    }//GEN-LAST:event_fieldUsernameKeyReleased

    private void fieldUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldUsernameActionPerformed

    private void fieldUsernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldUsernameFocusLost
        // TODO add your handling code here:
        if (fieldUsername.getText().equals("")) {
            fieldUsername.setText("Masukkan Username !");
            fieldUsername.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_fieldUsernameFocusLost

    private void fieldUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldUsernameFocusGained
        // TODO add your handling code here:
        if (fieldUsername.getText().equals("Masukkan Username !")) {
            fieldUsername.setText("");
            fieldUsername.setForeground(Color.black);
        }
    }//GEN-LAST:event_fieldUsernameFocusGained

    private void kButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton1ActionPerformed
        // TODO add your handling code here:
        new welcome().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_kButton1ActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
      System.exit(0);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void fieldUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldUsernameKeyPressed
       if(evt.getKeyCode()== KeyEvent.VK_ENTER){
           fieldPassword.requestFocus();  
       }
    }//GEN-LAST:event_fieldUsernameKeyPressed

    private void fieldPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldPasswordKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
           komponen();

        if (usernamePetugas.equals("Masukkan Username !")) {
            Notification notif = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Username tidak boleh kosong!");
            notif.showNotification();
        } else if (password.equals("Masukkan Password !")) {
            Notification notif = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Password tidak boleh kosong!!");
            notif.showNotification();

        } else {
            try {
                String sql = "select * from tb_user where username = '" + usernamePetugas + "' and password = '" + password + "' and role = '" + hakAkses + "'";

                Connection con = koneksi.getKoneksi();
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet set = pst.executeQuery(sql);

                if (set.next()) {
                  
                    IDpetugas = set.getString("id_user");
                    namaPetugas = set.getString("nama");
                    usernamePetugas = set.getString("username");
                    Admin admin = new Admin();
                    
                    System.out.println(set.getString("nama"));

                    if (hakAkses.equals("admin")) {

                        Notification notif = new Notification(this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Login Berhasil!");
                        notif.showNotification();
                        new Admin().setVisible(true);
                        this.dispose();

                      

                    } else if (hakAkses.equals("owner")) {

                        Notification notif = new Notification(this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Login Berhasil!");
                        notif.showNotification();
                        admin.setVisible(true);
                        this.dispose();

                    } else if (hakAkses.equals("kasir")) {
                        Notification notif = new Notification(this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Login Berhasil!");
                        notif.showNotification();
                        new Kasir().setVisible(true); 
                        this.dispose();
                       
                    }

                } else {
                    loginLimit+=1; 
                    Notification notif = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Login Gagal, " + usernamePetugas + " bukanlah " + hakAkses );
                    notif.showNotification();
                  if(loginLimit==3){
                      labelLupaAkun.setVisible(true);
                      labelKlikDisini.setVisible(true); 
                  }
                    

                }

            } catch (Exception e) {
            }
        }

       }
    }//GEN-LAST:event_fieldPasswordKeyPressed

    private void labelKlikDisiniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelKlikDisiniMouseClicked
      new lupaAkun().setVisible(true); 
    }//GEN-LAST:event_labelKlikDisiniMouseClicked

    private void fieldPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldPasswordActionPerformed
      
    }//GEN-LAST:event_fieldPasswordActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private combo_suggestion.ComboBoxSuggestion comboHakAkses;
    public static final javax.swing.JPasswordField fieldPassword = new javax.swing.JPasswordField();
    public static final javax.swing.JTextField fieldUsername = new javax.swing.JTextField();
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private com.k33ptoo.components.KButton kButton1;
    private com.k33ptoo.components.KButton kButton2;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel labelIDOutlet1;
    private javax.swing.JLabel labelKlikDisini;
    private javax.swing.JLabel labelLupaAkun;
    // End of variables declaration//GEN-END:variables
}
