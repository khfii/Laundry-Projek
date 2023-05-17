/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import koneksi.koneksi; 

/**
 *
 * @author Kahfi
 */
public class TambahUser extends javax.swing.JFrame {
    private Connection con; 
    private PreparedStatement pst; 
    private ResultSet set;
    koneksi koneksi = new koneksi(); 
    
    String IDuser, namaPegawai, usernamePegawai, passwordPegawai, IDoutlet, hakAkses; 
    
    
  
    /**
     * Creates new form updateHakAkses
     */
    public TambahUser() {
        initComponents();
        IDOutlet();
        autoNumberPegawai();
     
      }
    
    public void autoNumberPegawai(){
        try {
            String sql = "select max(id_user) as autoNumber from tb_user"; 
            this.pst = koneksi.getKoneksi().prepareStatement(sql); 
            this.set = pst.executeQuery(sql); 
            if(set.next()){
                int IDuser = set.getInt("autoNumber"); 
                fieldIDUser.setText(Integer.toString(IDuser + 1));
                fieldIDUser.setEditable(false);
            }
        } catch (Exception e) {
        }
    }
    
    public void komponen(){
        this.IDuser = fieldIDUser.getText().trim(); 
        this.namaPegawai = fieldNamaPegawai.getText().trim(); 
        this.usernamePegawai = fieldUsernamePegawai.getText().trim();
        this.passwordPegawai = fieldPasswordPegawai.getText().trim(); 
        this.IDoutlet = comboIDOutlet.getSelectedItem().toString().trim(); 
        this.hakAkses = comboHakAkses.getSelectedItem().toString().trim();
        
    }
    
    public void IDOutlet(){
        try {
            String sql = "select id_outlet from tb_outlet"; 
            this.pst = koneksi.getKoneksi().prepareStatement(sql); 
            this.set = pst.executeQuery(sql); 
            while(set.next()){
                String IDOutlet = set.getString("id_outlet"); 
                comboIDOutlet.addItem(IDOutlet);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void InsertData(){
        komponen();
        try {
            String sql = "insert into tb_user values ('" + IDuser + "','" + namaPegawai + "','" + usernamePegawai 
                    + "','" + passwordPegawai + "','" + IDoutlet +  "','" + hakAkses + "')"; 
            this.pst = koneksi.getKoneksi().prepareStatement(sql); 
            pst.execute(); 
            dispose();
            JOptionPane.showMessageDialog(null, "Data Pegawai Berhasil Dibuat, Silahkan Refresh!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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
        labelNamaLengkap = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fieldIDUser = new textfield.TextField();
        fieldNamaPegawai = new textfield.TextField();
        fieldUsernamePegawai = new textfield.TextField();
        fieldPasswordPegawai = new textfield.PasswordField();
        comboHakAkses = new combobox.Combobox();
        kButton1 = new com.k33ptoo.components.KButton();
        comboIDOutlet = new combobox.Combobox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Role");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        labelNamaLengkap.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelNamaLengkap.setText("Pegawai");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/man_1.png"))); // NOI18N

        fieldIDUser.setLabelText("ID User");
        fieldIDUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fieldIDUserMouseClicked(evt);
            }
        });

        fieldNamaPegawai.setLabelText("Nama Lengkap");

        fieldUsernamePegawai.setLabelText("Username");

        fieldPasswordPegawai.setLabelText("Password");

        comboHakAkses.setForeground(new java.awt.Color(102, 102, 102));
        comboHakAkses.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Hak Akses", "owner", "admin ", "kasir" }));
        comboHakAkses.setLabeText("");

        kButton1.setText("Tambah");
        kButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        kButton1.setIconTextGap(5);
        kButton1.setkBackGroundColor(new java.awt.Color(102, 102, 255));
        kButton1.setkEndColor(new java.awt.Color(102, 102, 255));
        kButton1.setkHoverEndColor(new java.awt.Color(102, 102, 255));
        kButton1.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton1.setkHoverStartColor(new java.awt.Color(102, 102, 255));
        kButton1.setkPressedColor(new java.awt.Color(153, 153, 255));
        kButton1.setkSelectedColor(new java.awt.Color(153, 153, 255));
        kButton1.setkStartColor(new java.awt.Color(153, 153, 255));
        kButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton1ActionPerformed(evt);
            }
        });

        comboIDOutlet.setForeground(new java.awt.Color(102, 102, 102));
        comboIDOutlet.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masukan ID Outlet" }));
        comboIDOutlet.setLabeText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 96, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fieldUsernamePegawai, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                    .addComponent(fieldNamaPegawai, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                    .addComponent(fieldIDUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fieldPasswordPegawai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboHakAkses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboIDOutlet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(93, 93, 93))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(labelNamaLengkap)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelNamaLengkap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldIDUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fieldNamaPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fieldUsernamePegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fieldPasswordPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comboIDOutlet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(comboHakAkses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(kButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void kButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton1ActionPerformed
        komponen();
        if(IDuser.isEmpty()){
            JOptionPane.showMessageDialog(null, "ID User tidak boleh kosong!");
        }else if (namaPegawai.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nama tidak boleh kosong!");
        }else if(usernamePegawai.isEmpty()){
            JOptionPane.showMessageDialog(null, "Username tidak boleh kosong!");
        }else if(passwordPegawai.isEmpty()){
            JOptionPane.showMessageDialog(null, "password tidak boleh kosong!");
            
        }else if(IDoutlet.equals("Masukan ID Outlet")){
            JOptionPane.showMessageDialog(null, "Masukan ID Outlet!");
            
        }else if(hakAkses.equals("Pilih Hak Akses")){
            JOptionPane.showMessageDialog(null, "Masukan Hak Akses!");
            
        }else{
            InsertData();
        }
    }//GEN-LAST:event_kButton1ActionPerformed

    private void fieldIDUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fieldIDUserMouseClicked
       JOptionPane.showMessageDialog(null, "ID User dibuat otomatis oleh sistem dan tidak dapat diedit", "Pesan Dari Admin", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_fieldIDUserMouseClicked

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
            java.util.logging.Logger.getLogger(TambahUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TambahUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TambahUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TambahUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TambahUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private combobox.Combobox comboHakAkses;
    private combobox.Combobox comboIDOutlet;
    public static textfield.TextField fieldIDUser;
    private textfield.TextField fieldNamaPegawai;
    private textfield.PasswordField fieldPasswordPegawai;
    public static textfield.TextField fieldUsernamePegawai;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private com.k33ptoo.components.KButton kButton1;
    private javax.swing.JLabel labelNamaLengkap;
    // End of variables declaration//GEN-END:variables
}