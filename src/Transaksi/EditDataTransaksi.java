package Transaksi;
import DashboardAdmin.Admin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import koneksi.koneksi;
/**
 *
 * @author Kahfi
 */
public class EditDataTransaksi extends javax.swing.JFrame {
    // Properti Koneksi
    Connection con; 
    PreparedStatement pst; 
    ResultSet set; 
    koneksi koneksi = new koneksi(); 
   
    // Properti Data Transaksi
     public String IDPetugas, namaPetugas; 
 

    // properti paket
    public String IDOutlet;

    
    // properti transaksi
    public  String IDTransaksi, IDPelanggan, namaPelanggan, tanggalPesan, batasWaktu, tanggalBayar, kodeInvoice, statusPesan, statusBayar; 
    public  int biayaAdmin, pajak;
    public  double diskon; 

    /**
     * Creates new form EditDataTransaksi
     */
    public EditDataTransaksi() {
        
       
        initComponents();
    
        IDOutlet(); 
        IDPelanggan(); 
        IDPetugas(); 
       
    
//        ambilKomponen();
    
    }
    
     public void IDOutlet() {
        try {
            String sql = "select id_outlet from tb_outlet";
            this.pst = koneksi.getKoneksi().prepareStatement(sql);
            this.set = pst.executeQuery(sql);
            while (set.next()) {
                this.IDOutlet = set.getString("id_outlet");
                comboIDOutletTransaksi.addItem(IDOutlet);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
     public void IDPelanggan(){
        try {
            String sql = "select id_member from tb_member"; 
            this.pst = koneksi.getKoneksi().prepareStatement(sql); 
            this.set = pst.executeQuery(sql); 
            while(set.next()){
                comboIDPelanggan.addItem(set.getString("id_member"));
            }
        } catch (Exception e) {
        }
    }
     public void IDPetugas(){
        try {
            String sql = "select id_user from tb_user"; 
            this.pst = koneksi.getKoneksi().prepareStatement(sql); 
            this.set = pst.executeQuery(sql); 
            while(set.next()){
                comboIDPetugas.addItem(set.getString("id_user"));
            }
        } catch (Exception e) {
        }
    }
    
    
    public void komponenTransaksi(){
        this.IDOutlet = comboIDOutletTransaksi.getSelectedItem().toString(); 
        this.IDTransaksi = fieldIDTransaksi.getText(); 
        this.IDPetugas = comboIDPetugas.getSelectedItem().toString(); 
        this.IDPelanggan = comboIDPelanggan.getSelectedItem().toString(); 
        this.namaPelanggan = fieldNamaPelanggan.getText(); 
        this.tanggalPesan = String.valueOf(fieldTanggalPesan.getDate()); 
        this.batasWaktu = String.valueOf(fieldBatasWaktu.getDate()); 
        this.tanggalBayar = String.valueOf(fieldTanggalBayar.getDate()); 
        this.kodeInvoice = fieldKodeInvoice.getText(); 
        this.statusPesan = comboStatusPesanan.getSelectedItem().toString(); 
        this.statusBayar = comboStatusPembayaran.getSelectedItem().toString(); 
        
        System.out.println(IDTransaksi);
        System.out.println(IDPetugas);
        System.out.println(IDOutlet);
        System.out.println(statusPesan);
        System.out.println(statusBayar);
       
        
    }
    
    public void UpdateTransaksi(){
        komponenTransaksi();
        try {
            String sql = "update tb_transaksi set id_outlet=?, status=?, bayar=?, id_user=? where id_transaksi=?"; 
            this.pst = koneksi.getKoneksi().prepareStatement(sql); 
            this.pst.setString(1, IDOutlet);
            this.pst.setString(2, statusPesan);
            this.pst.setString(3, statusBayar);
            this.pst.setString(4, IDPetugas);
            this.pst.setString(5, IDTransaksi);
            this.pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Transaksi Berhasil DiUpdate, Silahkan Refresh!");
            this.dispose();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void hapusTransaksi(){
        komponenTransaksi();
        try {
            String sql = "delete from tb_transaksi where id_transaksi=?"; 
            this.pst = koneksi.getKoneksi().prepareStatement(sql); 
            this.pst.setString(1, IDTransaksi);
            this.pst.executeUpdate(); 
            JOptionPane.showMessageDialog(null, "Transaksi atas nama : " + namaPelanggan + " Berhasil Dihapus", "Pesan Dari Admin", JOptionPane.WARNING_MESSAGE);
            this.dispose();
            
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

        panelRound1 = new test.PanelRound();
        jLabel18 = new javax.swing.JLabel();
        comboIDOutletTransaksi = new combobox.Combobox();
        jLabel15 = new javax.swing.JLabel();
        comboStatusPesanan = new combobox.Combobox();
        comboIDPetugas = new combobox.Combobox();
        jLabel16 = new javax.swing.JLabel();
        fieldBiayaAdmin = new textfield.TextField();
        jLabel12 = new javax.swing.JLabel();
        fieldPajak = new textfield.TextField();
        jLabel13 = new javax.swing.JLabel();
        fieldDiskon = new textfield.TextField();
        jLabel10 = new javax.swing.JLabel();
        fieldKodeInvoice = new textfield.TextField();
        dashboard16 = new com.k33ptoo.components.KButton();
        dashboard17 = new com.k33ptoo.components.KButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        fieldTanggalBayar = new com.toedter.calendar.JDateChooser();
        fieldTanggalPesan = new com.toedter.calendar.JDateChooser();
        fieldBatasWaktu = new com.toedter.calendar.JDateChooser();
        dashboard18 = new com.k33ptoo.components.KButton();
        comboStatusPembayaran = new combobox.Combobox();
        fieldNamaPelanggan = new textfield.TextField();
        comboIDPelanggan = new combobox.Combobox();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        fieldNamaPetugas = new textfield.TextField();
        fieldIDTransaksi = new textfield.TextField();
        dashboard19 = new com.k33ptoo.components.KButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo/Decoration Bubble (1).png"))); // NOI18N
        panelRound1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 110, 50));

        comboIDOutletTransaksi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masukan ID Outlet" }));
        comboIDOutletTransaksi.setAlignmentX(0.0F);
        comboIDOutletTransaksi.setLabeText("");
        panelRound1.add(comboIDOutletTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 220, 40));

        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Progres Pesanan");
        panelRound1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, 140, 20));

        comboStatusPesanan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "baru ", "proses", "selesai", "diambil" }));
        comboStatusPesanan.setLabeText("");
        panelRound1.add(comboStatusPesanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, 220, 40));

        comboIDPetugas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ID Petugas" }));
        comboIDPetugas.setLabeText("");
        comboIDPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboIDPetugasActionPerformed(evt);
            }
        });
        panelRound1.add(comboIDPetugas, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 510, 220, 40));

        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Status Pembayaran");
        panelRound1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 210, 120, 20));

        fieldBiayaAdmin.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        fieldBiayaAdmin.setLabelText("Biaya Admin");
        panelRound1.add(fieldBiayaAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 300, 220, -1));

        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("ID Pelanggan");
        panelRound1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 130, 20));

        fieldPajak.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        fieldPajak.setLabelText("Pajak");
        panelRound1.add(fieldPajak, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 360, 220, -1));

        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Batas Waktu");
        panelRound1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 440, 90, 20));

        fieldDiskon.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        fieldDiskon.setLabelText("Diskon");
        panelRound1.add(fieldDiskon, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 420, 220, -1));

        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Tanggal Bayar");
        panelRound1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 500, 90, 20));

        fieldKodeInvoice.setEditable(false);
        fieldKodeInvoice.setBackground(new java.awt.Color(255, 255, 255));
        fieldKodeInvoice.setLabelText("Kode Invoice");
        panelRound1.add(fieldKodeInvoice, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 580, 220, -1));

        dashboard16.setText("Hapus Transaksi");
        dashboard16.setToolTipText("");
        dashboard16.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        dashboard16.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        dashboard16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dashboard16.setIconTextGap(15);
        dashboard16.setkBackGroundColor(new java.awt.Color(102, 102, 255));
        dashboard16.setkBorderRadius(20);
        dashboard16.setkEndColor(new java.awt.Color(51, 51, 255));
        dashboard16.setkHoverColor(new java.awt.Color(153, 153, 255));
        dashboard16.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        dashboard16.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        dashboard16.setkHoverStartColor(new java.awt.Color(153, 153, 255));
        dashboard16.setkPressedColor(new java.awt.Color(153, 153, 255));
        dashboard16.setkSelectedColor(new java.awt.Color(153, 153, 255));
        dashboard16.setkStartColor(new java.awt.Color(153, 153, 255));
        dashboard16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboard16ActionPerformed(evt);
            }
        });
        panelRound1.add(dashboard16, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 660, 190, -1));

        dashboard17.setText("Update Transaksi");
        dashboard17.setToolTipText("");
        dashboard17.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        dashboard17.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        dashboard17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dashboard17.setIconTextGap(15);
        dashboard17.setkBackGroundColor(new java.awt.Color(102, 102, 255));
        dashboard17.setkBorderRadius(20);
        dashboard17.setkEndColor(new java.awt.Color(51, 51, 255));
        dashboard17.setkHoverColor(new java.awt.Color(153, 153, 255));
        dashboard17.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        dashboard17.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        dashboard17.setkHoverStartColor(new java.awt.Color(153, 153, 255));
        dashboard17.setkPressedColor(new java.awt.Color(153, 153, 255));
        dashboard17.setkSelectedColor(new java.awt.Color(153, 153, 255));
        dashboard17.setkStartColor(new java.awt.Color(153, 153, 255));
        dashboard17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboard17ActionPerformed(evt);
            }
        });
        panelRound1.add(dashboard17, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 660, 160, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Transaksi");
        panelRound1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 130, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo/Decoration Bubble (1).png"))); // NOI18N
        panelRound1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 130, 50));

        fieldTanggalBayar.setBackground(new java.awt.Color(255, 255, 255));
        fieldTanggalBayar.setDateFormatString("MMM d, yyyy HH:MM");
        panelRound1.add(fieldTanggalBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 530, 220, -1));

        fieldTanggalPesan.setBackground(new java.awt.Color(255, 255, 255));
        fieldTanggalPesan.setDateFormatString("MMM d, yyyy HH:MM");
        panelRound1.add(fieldTanggalPesan, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, 220, -1));

        fieldBatasWaktu.setBackground(new java.awt.Color(255, 255, 255));
        fieldBatasWaktu.setDateFormatString("MMM d, yyyy HH:MM");
        panelRound1.add(fieldBatasWaktu, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 470, 220, -1));

        dashboard18.setText("Lanjutkan Transaksi");
        dashboard18.setToolTipText("");
        dashboard18.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        dashboard18.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        dashboard18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dashboard18.setIconTextGap(15);
        dashboard18.setkBackGroundColor(new java.awt.Color(102, 102, 255));
        dashboard18.setkBorderRadius(20);
        dashboard18.setkEndColor(new java.awt.Color(51, 51, 255));
        dashboard18.setkHoverColor(new java.awt.Color(153, 153, 255));
        dashboard18.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        dashboard18.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        dashboard18.setkHoverStartColor(new java.awt.Color(153, 153, 255));
        dashboard18.setkPressedColor(new java.awt.Color(153, 153, 255));
        dashboard18.setkSelectedColor(new java.awt.Color(153, 153, 255));
        dashboard18.setkStartColor(new java.awt.Color(153, 153, 255));
        dashboard18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboard18ActionPerformed(evt);
            }
        });
        panelRound1.add(dashboard18, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 660, 160, -1));

        comboStatusPembayaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "sudah bayar", "belum bayar" }));
        comboStatusPembayaran.setLabeText("");
        comboStatusPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboStatusPembayaranActionPerformed(evt);
            }
        });
        panelRound1.add(comboStatusPembayaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 240, 220, 40));

        fieldNamaPelanggan.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        fieldNamaPelanggan.setLabelText("Nama Pelangan");
        fieldNamaPelanggan.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        panelRound1.add(fieldNamaPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 220, -1));

        comboIDPelanggan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ID Pelanggan" }));
        comboIDPelanggan.setLabeText("");
        comboIDPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboIDPelangganActionPerformed(evt);
            }
        });
        panelRound1.add(comboIDPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 220, 40));

        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("Tanggal Pemesanan");
        panelRound1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 130, 20));

        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("ID Petugas");
        panelRound1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 480, 130, 20));

        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("ID Outlet");
        panelRound1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 130, 20));

        fieldNamaPetugas.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        fieldNamaPetugas.setLabelText("Nama Petugas");
        panelRound1.add(fieldNamaPetugas, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 580, 220, -1));

        fieldIDTransaksi.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        fieldIDTransaksi.setLabelText("ID Transaksi");
        fieldIDTransaksi.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        panelRound1.add(fieldIDTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 220, -1));

        dashboard19.setText("Lanjutkan Transaksi");
        dashboard19.setToolTipText("");
        dashboard19.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        dashboard19.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        dashboard19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dashboard19.setIconTextGap(15);
        dashboard19.setkBackGroundColor(new java.awt.Color(102, 102, 255));
        dashboard19.setkBorderRadius(20);
        dashboard19.setkEndColor(new java.awt.Color(51, 51, 255));
        dashboard19.setkHoverColor(new java.awt.Color(153, 153, 255));
        dashboard19.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        dashboard19.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        dashboard19.setkHoverStartColor(new java.awt.Color(153, 153, 255));
        dashboard19.setkPressedColor(new java.awt.Color(153, 153, 255));
        dashboard19.setkSelectedColor(new java.awt.Color(153, 153, 255));
        dashboard19.setkStartColor(new java.awt.Color(153, 153, 255));
        dashboard19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboard19ActionPerformed(evt);
            }
        });
        panelRound1.add(dashboard19, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 660, 160, -1));

        getContentPane().add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 750));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void comboIDPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboIDPetugasActionPerformed
         String IDPetugas = comboIDPetugas.getSelectedItem().toString(); 
        try {
            String sql = "select nama from tb_user where id_user=?"; 
            this.pst = koneksi.getKoneksi().prepareStatement(sql); 
            this.pst.setString(1, IDPetugas);
            this.set = pst.executeQuery(); 
            if(set.next()){
                String namaPetugas = set.getString("nama"); 
                fieldNamaPetugas.setText(namaPetugas);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_comboIDPetugasActionPerformed

    private void dashboard16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboard16ActionPerformed
       komponenTransaksi();
       int confirm = JOptionPane.showConfirmDialog(null, "Hapus Transaksi atas nama : " + namaPelanggan + "?", "Pesan dari Admin", JOptionPane.WARNING_MESSAGE); 
       if(confirm==0){
           hapusTransaksi();
           
       }
    }//GEN-LAST:event_dashboard16ActionPerformed

    private void dashboard17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboard17ActionPerformed
         UpdateTransaksi();
komponenTransaksi();
     
    }//GEN-LAST:event_dashboard17ActionPerformed

    private void dashboard18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboard18ActionPerformed
     DetailTransaksi detail = new DetailTransaksi(); 
     detail.setVisible(true); 
     detail.comboIDTransaksi.setSelectedItem(fieldIDTransaksi.getText());
        
        
        this.dispose();
      
               
        
    }//GEN-LAST:event_dashboard18ActionPerformed

    private void comboStatusPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboStatusPembayaranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboStatusPembayaranActionPerformed

    private void comboIDPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboIDPelangganActionPerformed
       String IDPelanggan = comboIDPelanggan.getSelectedItem().toString(); 
        try {
            String sql = "select nama from tb_member where id_member=?"; 
            this.pst = koneksi.getKoneksi().prepareStatement(sql); 
            this.pst.setString(1, IDPelanggan);
            this.set = pst.executeQuery(); 
            if(set.next()){
                String namaPelanggan = set.getString("nama"); 
                fieldNamaPelanggan.setText(namaPelanggan);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
       
    }//GEN-LAST:event_comboIDPelangganActionPerformed

    private void dashboard19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboard19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dashboard19ActionPerformed

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
            java.util.logging.Logger.getLogger(EditDataTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditDataTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditDataTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditDataTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditDataTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static combobox.Combobox comboIDOutletTransaksi;
    public static combobox.Combobox comboIDPelanggan;
    public static combobox.Combobox comboIDPetugas;
    public static combobox.Combobox comboStatusPembayaran;
    public static combobox.Combobox comboStatusPesanan;
    private com.k33ptoo.components.KButton dashboard16;
    private com.k33ptoo.components.KButton dashboard17;
    private com.k33ptoo.components.KButton dashboard18;
    private com.k33ptoo.components.KButton dashboard19;
    public static com.toedter.calendar.JDateChooser fieldBatasWaktu;
    public static textfield.TextField fieldBiayaAdmin;
    public static textfield.TextField fieldDiskon;
    public static textfield.TextField fieldIDTransaksi;
    public static textfield.TextField fieldKodeInvoice;
    public static textfield.TextField fieldNamaPelanggan;
    public static textfield.TextField fieldNamaPetugas;
    public static textfield.TextField fieldPajak;
    public static com.toedter.calendar.JDateChooser fieldTanggalBayar;
    public static com.toedter.calendar.JDateChooser fieldTanggalPesan;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private test.PanelRound panelRound1;
    // End of variables declaration//GEN-END:variables
}
