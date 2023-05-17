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
import javax.swing.JOptionPane;
import static user.TambahUser.fieldIDUser;
import static user.TambahUser.fieldUsernamePegawai;

/**
 *
 * @author Kahfi
 */
public class UpdateUser extends javax.swing.JFrame {
    private Connection con; 
    private PreparedStatement pst; 
    private ResultSet set;
    koneksi koneksi = new koneksi(); 
    
    String IDuser, namaPegawai, usernamePegawai, passwordPegawai, IDoutlet, hakAkses; 

  
    /**
     * Creates new form updateHakAkses
     */
    public UpdateUser() {
        initComponents();
        IDOutlet();
       
      
       
        
        
       
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
    
    
    public void komponen(){
        this.IDuser = fieldIDUser.getText().trim(); 
        this.namaPegawai = fieldNamaPegawai.getText().trim(); 
        this.usernamePegawai = fieldUsernamePegawai.getText().trim();
        this.passwordPegawai = fieldPasswordPegawai.getText().trim(); 
        this.IDoutlet = comboIDOutlet.getSelectedItem().toString().trim(); 
        this.hakAkses = ComboHakAkses.getSelectedItem().toString().trim();
        
    }
    
    public void hapusDataPegawai(){
        komponen();
        try {
            String sql = "delete from tb_user where id_user = '" + IDuser + "'"; 
            this.pst = koneksi.getKoneksi().prepareStatement(sql);
            pst.execute(); 
            this.dispose();
            JOptionPane.showMessageDialog(null, "Data Pegawai Berhasil Dihapus, Silahkan Refresh", "Pesan Dari Owner", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
        }
    }
    
    public void editDataPegawai(){
        komponen();
        try {
            String sql = "update tb_user set nama ='" + namaPegawai + "', username ='" + usernamePegawai + "', password ='" + passwordPegawai + 
                    "', id_outlet ='" + IDoutlet + "', role ='" + hakAkses + "' where id_user = '" + IDuser + "'"; 
            this.pst = koneksi.getKoneksi().prepareStatement(sql); 
            pst.execute(); 
            this.dispose();
            JOptionPane.showMessageDialog(null, "Data Pegawai Berhasil Di Update, Silahkan Refresh", "Pesan Dari Owner", JOptionPane.WARNING_MESSAGE);
            
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

        jPanel1 = new javax.swing.JPanel();
        labelNamaLengkap = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fieldIDUser = new textfield.TextField();
        fieldNamaPegawai = new textfield.TextField();
        fieldUsernamePegawai = new textfield.TextField();
        fieldPasswordPegawai = new textfield.PasswordField();
        comboIDOutlet = new combobox.Combobox();
        kButton1 = new com.k33ptoo.components.KButton();
        kButton2 = new com.k33ptoo.components.KButton();
        ComboHakAkses = new combobox.Combobox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Role");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        labelNamaLengkap.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelNamaLengkap.setText("Perbarui Pegawai");

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

        comboIDOutlet.setLabeText("");

        kButton1.setText("Update");
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

        kButton2.setText("Delete");
        kButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        kButton2.setIconTextGap(5);
        kButton2.setkBackGroundColor(new java.awt.Color(102, 102, 255));
        kButton2.setkEndColor(new java.awt.Color(102, 102, 255));
        kButton2.setkHoverEndColor(new java.awt.Color(102, 102, 255));
        kButton2.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton2.setkHoverStartColor(new java.awt.Color(102, 102, 255));
        kButton2.setkPressedColor(new java.awt.Color(153, 153, 255));
        kButton2.setkSelectedColor(new java.awt.Color(153, 153, 255));
        kButton2.setkStartColor(new java.awt.Color(153, 153, 255));
        kButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton2ActionPerformed(evt);
            }
        });

        ComboHakAkses.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Hak Akses", "admin", "owner", "kasir" }));
        ComboHakAkses.setLabeText("");

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
                    .addComponent(comboIDOutlet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(kButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addComponent(ComboHakAkses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(93, 93, 93))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(196, 196, 196)
                .addComponent(jLabel2)
                .addContainerGap(194, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelNamaLengkap)
                .addGap(133, 133, 133))
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
                .addGap(18, 18, 18)
                .addComponent(ComboHakAkses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
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

    private void fieldIDUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fieldIDUserMouseClicked
         JOptionPane.showMessageDialog(null, "ID User dibuat otomatis oleh sistem dan tidak dapat diedit", "Pesan Dari Admin", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_fieldIDUserMouseClicked

    private void kButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton2ActionPerformed
        komponen();
        int konfirmasi = JOptionPane.showConfirmDialog(null, "Hapus pegawai atas nama : " + namaPegawai, "Pesan Dari Admin", JOptionPane.WARNING_MESSAGE); 
        if(konfirmasi == 0){
            hapusDataPegawai();
        }
    }//GEN-LAST:event_kButton2ActionPerformed

    private void kButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton1ActionPerformed
       editDataPegawai();
    }//GEN-LAST:event_kButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(UpdateUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static combobox.Combobox ComboHakAkses;
    public static combobox.Combobox comboIDOutlet;
    public static textfield.TextField fieldIDUser;
    public static textfield.TextField fieldNamaPegawai;
    public static textfield.PasswordField fieldPasswordPegawai;
    public static textfield.TextField fieldUsernamePegawai;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private com.k33ptoo.components.KButton kButton1;
    private com.k33ptoo.components.KButton kButton2;
    private javax.swing.JLabel labelNamaLengkap;
    // End of variables declaration//GEN-END:variables
}