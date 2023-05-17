package DashboardAdmin;

import Login.Login;
import Transaksi.DetailTransaksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.Connection;
import koneksi.koneksi;

import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import member.editMember;
import member.member;
import user.TambahUser;
import user.UpdateUser;
import outlet.TambahOutlet;
import outlet.UpdateOutlet;
import Transaksi.EditDataTransaksi;
import Transaksi.EditDetailTransaksi;
import com.k33ptoo.components.KButton;
import java.awt.Color;
import java.text.MessageFormat;
import org.codehaus.groovy.tools.shell.Main;

/**
 *
 * @author Kahfi
 */
public class Admin extends javax.swing.JFrame {

//    properti koneksi
    private Connection con;
    private PreparedStatement pst;
    private ResultSet set;
    koneksi koneksi = new koneksi();

//    public static String IDPetugas, namaPetugas, Username, Role; 
    public static String IDPetugas, namaPetugas, usernamePetugas, rolePetugas;

//      properti paket
    public static String IDOutlet, IDPaket, jenisPaket, namaPaket;
    public static int hargaPaket;

//    properti transaksi
    public static String IDTransaksi, IDPelanggan, tanggalSekarang, batasWaktu, tanggalBayar, kodeInvoice, statusPesan, statusBayar;
    public static int biayaAdmin, pajak;
    public static double diskon;

    public String detailIDTransaksi;

    /**
     * Creates new form
     */
    public Admin() {

        initComponents();
        buttonDashboard.setSelected(true);

//        komponenTransaksi();
        namaPetugas();
        dataPetugas();
        jumlahPaket();
        jumlahPelanggan();
        jumlahPegawai();
        waktu();
        statusPembayaran();
        this.setExtendedState(MAXIMIZED_BOTH);

//        dataPelanggan();
        // membuat koneksi terhubung ke semua frame
//        koneksi.getKoneksi(); 
    }

    public void setColor(KButton button) {
        button.setSelected(true);

    }

    public void resetColor(KButton button) {
        button.setSelected(false);

    }

    public void buttonDashboard() {
        // menambahkan jumlah pelanggan setiap kali ke halaman dashboard
        namaPetugas();
        dataPetugas();
        waktu();
        jumlahPelanggan();
        jumlahPegawai();
        jumlahPaket();

        Utama.removeAll();
        Utama.repaint();
        Utama.revalidate();

        // add new panel
        Utama.add(Dashboard);
        Utama.repaint();
        Utama.revalidate();

    }

    public void buttonPelanggan() {
        Utama.removeAll();
        Utama.repaint();
        Utama.revalidate();

        // add new panel
        Utama.add(pelanggan);
        Utama.repaint();
        Utama.revalidate();

        // memanggil function detail data pelanggan 
        DetailDataPelanggan();
    }

    public void dataPetugas() {
        Login dataLogin = new Login();
        namaPetugas = dataLogin.namaPetugas;
        usernamePetugas = dataLogin.usernamePetugas;
        rolePetugas = dataLogin.hakAkses;

        labelPetugas.setText(namaPetugas);
        LabelSelamatDatang.setText("Selamat Datang " + usernamePetugas);
        labelMenu.setText("Menu " + rolePetugas);

    }

    public void waktu() {

        int detik, menit, jam, hari, minggu, bulan, tahun;
        GregorianCalendar tanggal = new GregorianCalendar();

        String namaHari[] = {"Sabtu", "Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "sabtu"};

        String namaBulan[] = {
            "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};

        detik = tanggal.get(Calendar.SECOND);
        menit = tanggal.get(Calendar.MINUTE);
        jam = tanggal.get(Calendar.HOUR);
        hari = tanggal.get(Calendar.DAY_OF_MONTH);
        minggu = tanggal.get(Calendar.DAY_OF_WEEK);
        bulan = tanggal.get(Calendar.MONTH);
        tahun = tanggal.get(Calendar.YEAR);

        Labeltanggal1.setText(namaHari[minggu] + ", " + hari + " " + namaBulan[bulan] + " " + tahun);

    }

// Awal Source Paket
    public void komponenPaket() {
        this.IDOutlet = comboIDOutlet.getSelectedItem().toString().trim();
        this.IDPaket = fieldIDPaket.getText().trim();
        this.jenisPaket = comboJenisPaket.getSelectedItem().toString().trim();
        this.namaPaket = fieldNamaPaket.getText().trim();
        this.hargaPaket = Integer.valueOf(fieldHargaPaket.getText().trim());

    }

    public void komponenTransaksi() {

        this.IDOutlet = comboIDOutletTransaksi.getSelectedItem().toString();
        this.IDTransaksi = fieldIDTransaksi.getText();
        this.IDPelanggan = fieldIDPelanggan.getText();
        this.IDPetugas = fieldIDPetugas.getText();
        this.kodeInvoice = fieldKodeInvoice.getText();
        this.statusPesan = comboStatusPesanan.getSelectedItem().toString();
        this.statusBayar = comboStatusPembayaran.getSelectedItem().toString();
        // bagian tanggal dan angka 
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
            this.tanggalSekarang = String.valueOf(format.format(fieldTanggalPesan.getDate()));
            this.batasWaktu = String.valueOf(format.format(fieldBatasWaktu.getDate()));
            this.tanggalBayar = String.valueOf(format.format(fieldTanggalBayar.getDate()));

            System.out.println(tanggalSekarang);
            System.out.println(batasWaktu);
            System.out.println(tanggalBayar);

            this.biayaAdmin = 1000;
            this.pajak = 2500;
            this.diskon = 0.02;

        } catch (Exception e) {

        }

    }

    public void autoNumberIDPaket() {
        try {
            String sql = "select max(id_paket) as autoIDPaket from tb_paket";
            this.pst = koneksi.getKoneksi().prepareStatement(sql);
            this.set = pst.executeQuery(sql);
            if (set.next()) {
                fieldIDPaket.setEditable(false);
                int autoNumberIDPaket = set.getInt("autoIDPaket");
                fieldIDPaket.setText(Integer.toString(autoNumberIDPaket + 1));
            }
        } catch (Exception e) {
        }
    }

    public void autoNumberIDTransaksi() {
        try {
            String sql = "select max(id_transaksi) as autoIDTransaksi from tb_transaksi";
            this.pst = koneksi.getKoneksi().prepareStatement(sql);
            this.set = pst.executeQuery(sql);
            if (set.next()) {
                int autoNumberTransaksi = set.getInt("autoIDTransaksi");
                fieldIDTransaksi.setText(String.valueOf(autoNumberTransaksi + 1));
                fieldIDTransaksi.setEditable(false);
            }
        } catch (Exception e) {
        }
    }

    public void bersihkanTransaksi() {
        comboIDOutlet.setSelectedIndex(0);
        fieldIDTransaksi.setText("");
        comboNamaPelangganTransaksi.setSelectedIndex(0);
        fieldIDPelanggan.setText("");
        fieldBatasWaktu.setDate(null);
        fieldTanggalBayar.setDate(null);
        fieldBiayaAdmin.setText("");
        fieldPajak.setText("");
        fieldDiskon.setText("");
        comboNamaPetugas.setSelectedIndex(0);
        fieldIDPetugas.setText("");

    }

    public void autoKodeInvoice() {
        try {
            String sql = "select max(right(kode_invoice, 1)) as autoNumber from tb_transaksi;";
            this.pst = koneksi.getKoneksi().prepareStatement(sql);
            this.set = pst.executeQuery(sql);
            if (set.next()) {
                String angka = String.valueOf(set.getInt("autoNumber") + 1);

                String nol = "";

                if (angka.length() == 1) {
                    nol = "000";
                } else if (angka.length() == 2) {
                    nol = "00";
                } else if (angka.length() == 3) {
                    nol = "0";
                } else if (angka.length() == 4) {
                    nol = "";
                }

                fieldKodeInvoice.setText("A" + nol + angka);

            }
        } catch (Exception e) {
        }
    }

    public void insertDataTransaksi() {
        komponenTransaksi();
        try {
            String sql = "insert into tb_transaksi values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            this.pst = koneksi.getKoneksi().prepareStatement(sql);
            this.pst.setString(1, IDTransaksi);
            this.pst.setString(2, IDOutlet);
            this.pst.setString(3, kodeInvoice);
            this.pst.setString(4, IDPelanggan);
            this.pst.setString(5, tanggalSekarang);
            this.pst.setString(6, batasWaktu);
            this.pst.setString(7, tanggalBayar);
            this.pst.setInt(8, biayaAdmin);
            this.pst.setDouble(9, diskon);
            this.pst.setInt(10, pajak);
            this.pst.setString(11, statusPesan);
            this.pst.setString(12, statusBayar);
            this.pst.setString(13, IDPetugas);
            this.pst.execute();
            JOptionPane.showMessageDialog(null, "Transaksi Berhasil DiProses");
            Utama.removeAll();
            Utama.repaint();
            Utama.revalidate();

            // add new panel
            Utama.add(DataTransaksi);
            Utama.repaint();
            Utama.revalidate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        dataTransaksi();
    }

    public void insertDataPaket() {
        komponenPaket();
        try {
            String sql = "insert into tb_paket values ('" + IDPaket + "','" + IDOutlet + "','" + jenisPaket + "','" + namaPaket + "','" + hargaPaket + "')";
            this.pst = koneksi.getKoneksi().prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Paket Berhasil Ditambah!");
            detailDataPaket();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void updateDataPaket() {
        komponenPaket();
        try {
            String sql = "update tb_paket set id_outlet = '" + IDOutlet + "', jenis = '" + jenisPaket + "', nama_paket ='" + namaPaket + "', harga ='" + hargaPaket + "'where id_paket = '" + IDPaket + "'";
            this.pst = koneksi.getKoneksi().prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Paket Berhasil DiUpdate");
            detailDataPaket();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void hapusDataPaket() {
        komponenPaket();
        try {
            String sql = "delete from tb_paket where id_paket ='" + IDPaket + "'";
            this.pst = koneksi.getKoneksi().prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, namaPaket + " Berhasil Dihapus");
            detailDataPaket();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

//    Akhir Source Paket
    public void jumlahPelanggan() {
        try {
            String sql = "select count(nama) as jumlahPelanggan from tb_member";
            this.pst = koneksi.getKoneksi().prepareStatement(sql);
            this.set = pst.executeQuery(sql);
            if (set.next()) {
                String jumlahPelanggan = set.getString("jumlahPelanggan");
                JumlahDataPelanggan.setText("Jumlah : " + jumlahPelanggan);
            }
        } catch (Exception e) {
        }
    }

    public void jumlahPegawai() {
        try {
            String sql = "select count(nama) as jumlahPegawai from tb_user";
            this.pst = koneksi.getKoneksi().prepareStatement(sql);
            this.set = pst.executeQuery(sql);
            if (set.next()) {
                String jumlahPegawai = set.getString("jumlahPegawai");
                jumlahDataPegawai.setText("Jumlah : " + jumlahPegawai);
            }
        } catch (Exception e) {
        }
    }

    public void jumlahOutlet() {
        try {
            String sql = "select count(nama) as jumlahOutlet from tb_outlet";
            this.pst = koneksi.getKoneksi().prepareStatement(sql);
            this.set = pst.executeQuery(sql);
            if (set.next()) {
                String jumlahOutlet = set.getString("jumlahOutlet");
                JumlahOutlet.setText("Jumlah " + jumlahOutlet);
            }

        } catch (Exception e) {
        }
    }

    public void jumlahPaket() {
        try {
            String sql = "select count(nama_paket) as jumlahPaket from tb_paket";
            this.pst = koneksi.getKoneksi().prepareStatement(sql);
            this.set = pst.executeQuery(sql);
            if (set.next()) {
                String jumlahPaket = set.getString("jumlahPaket");
                JumlahDataPaket.setText("Jumlah : " + jumlahPaket);
            }

        } catch (Exception e) {
        }
    }

    public void IDOutlet() {
        try {
            String sql = "select id_outlet from tb_outlet";
            this.pst = koneksi.getKoneksi().prepareStatement(sql);
            this.set = pst.executeQuery(sql);
            while (set.next()) {
                this.IDOutlet = set.getString("id_outlet");
                comboIDOutlet.addItem(IDOutlet);
                comboIDOutletTransaksi.addItem(IDOutlet);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void Namamember() {
        try {
            String sql = "select nama from tb_member";
            this.pst = koneksi.getKoneksi().prepareStatement(sql);
            this.set = pst.executeQuery(sql);
            while (set.next()) {
                comboNamaPelangganTransaksi.addItem(set.getString("nama"));
            }
        } catch (Exception e) {
        }
    }

    public void namaPetugas() {
        try {
            String sql = "select nama from tb_user";
            this.pst = koneksi.getKoneksi().prepareStatement(sql);
            this.set = pst.executeQuery(sql);
            while (set.next()) {
                comboNamaPetugas.addItem(set.getString("nama"));
            }
        } catch (Exception e) {
        }
    }

    public void DetailDataPelanggan() {
        table_detail_pelanggan.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        table.TableCustom.apply(tableScroll, table.TableCustom.TableType.MULTI_LINE);
//        table.TableCustom.apply(tableScroll, table.TableCustom.TableType.DEFAULT);

        DefaultTableModel tbl = new DefaultTableModel();

        tbl.addColumn("ID Member");
        tbl.addColumn("Nama Pelanggan");
        tbl.addColumn("Alamat");
        tbl.addColumn("Jenis Kelamin");
        tbl.addColumn("No Telepon");

        table_detail_pelanggan.setModel(tbl);

        try {

            String sql = "select * from tb_member";
            this.pst = koneksi.getKoneksi().prepareStatement(sql);
            this.set = pst.executeQuery(sql);

            while (set.next()) {
                tbl.addRow(new Object[]{
                    set.getString("id_member"),
                    set.getString("nama"),
                    set.getString("alamat"),
                    set.getString("jenis_kelamin"),
                    set.getString("tlp"),});
                table_detail_pelanggan.setModel(tbl);
            }

        } catch (Exception e) {
        }
    }

    public void detailDataPegawai() {
        table_detail_pegawai.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.TableCustom.apply(tablePegawaiScroll, table.TableCustom.TableType.MULTI_LINE);
        DefaultTableModel tbl = new DefaultTableModel();

        tbl.addColumn("ID Pegawai");
        tbl.addColumn("Nama Pegawai");
        tbl.addColumn("Username");
        tbl.addColumn("Password");
        tbl.addColumn("ID Outlet");
        tbl.addColumn("Hak Akses");
        table_detail_pegawai.setModel(tbl);

        try {
            String sql = "select * from tb_user order by id_user asc";
            this.pst = koneksi.getKoneksi().prepareStatement(sql);
            this.set = pst.executeQuery(sql);
            while (set.next()) {
                tbl.addRow(new Object[]{
                    set.getString("id_user"),
                    set.getString("nama"),
                    set.getString("username"),
                    set.getString("password"),
                    set.getString("id_outlet"),
                    set.getString("role"),});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void detailDataOutlet() {
        table_detail_outlet.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.TableCustom.apply(tableOutletScroll, table.TableCustom.TableType.MULTI_LINE);
        DefaultTableModel tbl = new DefaultTableModel();

        tbl.addColumn("ID Outlet");
        tbl.addColumn("Nama Outlet");
        tbl.addColumn("Alamat Outlet");
        tbl.addColumn("Nomor Telepon");
        table_detail_outlet.setModel(tbl);
        try {
            String sql = "select * from tb_outlet";
            this.pst = koneksi.getKoneksi().prepareStatement(sql);
            this.set = pst.executeQuery(sql);
            while (set.next()) {
                tbl.addRow(new Object[]{
                    set.getString("id_outlet"),
                    set.getString("nama"),
                    set.getString("alamat"),
                    set.getString("tlp")

                });
            }
        } catch (Exception e) {
        }
    }

    public void detailDataPaket() {
        table_detail_paket.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.TableCustom.apply(scrollPanePaket, table.TableCustom.TableType.MULTI_LINE);
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("ID Outlet");
        tbl.addColumn("ID Paket");
        tbl.addColumn("Jenis Paket");
        tbl.addColumn("Nama Paket");
        tbl.addColumn("Harga Paket");
        table_detail_paket.setModel(tbl);

        try {
            String sql = "select * from tb_paket";
            this.pst = koneksi.getKoneksi().prepareStatement(sql);
            this.set = pst.executeQuery(sql);
            while (set.next()) {
                tbl.addRow(new Object[]{
                    set.getString("id_outlet"),
                    set.getString("id_paket"),
                    set.getString("jenis"),
                    set.getString("nama_paket"),
                    set.getString("harga")

                });
            }
        } catch (Exception e) {
        }

    }

    public void dataTransaksi() {
        table_data_transaksi.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.TableCustom.apply(scrollDataTransaksi, table.TableCustom.TableType.MULTI_LINE);
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("ID Transaksi");
        tbl.addColumn("ID Pelanggan");
        tbl.addColumn("Kode Invoice");
        tbl.addColumn("Di pesan");
        tbl.addColumn("Di bayar");
        tbl.addColumn("Status Pesan");
        tbl.addColumn("Status Bayar");
        tbl.addColumn("Biaya Admin");
        tbl.addColumn("Pajak");
        tbl.addColumn("Diskon");
        tbl.addColumn("ID Petugas");
        table_data_transaksi.setModel(tbl);

        try {
            String sql = "select * from tb_transaksi where bayar='belum bayar' order by id_transaksi asc";
            this.pst = koneksi.getKoneksi().prepareStatement(sql);
            this.set = pst.executeQuery(sql);
            while (set.next()) {
                tbl.addRow(new Object[]{
                    set.getString("id_transaksi"),
                    set.getString("id_member"),
                    set.getString("kode_invoice"),
                    set.getString("tanggal"),
                    set.getString("tanggal_bayar"),
                    set.getString("status"),
                    set.getString("bayar"),
                    set.getString("biaya_tambahan"),
                    set.getString("pajak"),
                    set.getString("diskon"),
                    set.getString("id_user"),});
            }
        } catch (Exception e) {
        }

    }

    public void detailDataTransaksi() {
        table_detail_transaksi.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.TableCustom.apply(scrollPanelDetailTransaksi, table.TableCustom.TableType.MULTI_LINE);
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("Pelanggan");
        tbl.addColumn("ID Transaksi");
        tbl.addColumn("Nama Paket");
        tbl.addColumn("Harga");
        tbl.addColumn("Jumlah");
        tbl.addColumn("Biaya Admin");
        tbl.addColumn("Pajak");
        tbl.addColumn("Diskon");
        tbl.addColumn("Proses");
        tbl.addColumn("Status");

        tbl.addColumn("Catatan");
        table_detail_transaksi.setModel(tbl);

        try {
            String sql = "select * from tb_paket join tb_detail_transaksi on tb_paket.id_paket = tb_detail_transaksi.id_paket "
                    + "join tb_transaksi on tb_transaksi.id_transaksi = tb_detail_transaksi.id_transaksi "
                    + "join tb_member on tb_member.id_member = tb_transaksi.id_member where bayar='sudah bayar' order by tb_transaksi.id_transaksi asc";
            this.pst = koneksi.getKoneksi().prepareStatement(sql);
            this.set = pst.executeQuery(sql);
            while (set.next()) {
                tbl.addRow(new Object[]{
                    set.getString("tb_member.nama"),
                    set.getString("tb_detail_transaksi.id_transaksi"),
                    set.getString("tb_paket.nama_paket"),
                    set.getInt("tb_paket.harga"),
                    set.getDouble("tb_detail_transaksi.qty"),
                    set.getInt("tb_transaksi.biaya_tambahan"),
                    set.getInt("tb_transaksi.pajak"),
                    set.getDouble("tb_transaksi.diskon"),
                    set.getString("tb_transaksi.status"),
                    set.getString("tb_transaksi.bayar"),
                    set.getString("tb_detail_transaksi.keterangan")
                });

            }
        } catch (Exception e) {
        }

    }

    public void mouseClickPelanggan() {

        int baris = table_detail_pelanggan.getSelectedRow();
        String id_member = table_detail_pelanggan.getModel().getValueAt(baris, 0).toString();
        String namaLengkap = table_detail_pelanggan.getModel().getValueAt(baris, 1).toString();
        String alamat = table_detail_pelanggan.getModel().getValueAt(baris, 2).toString();
        String jenisKelamin = table_detail_pelanggan.getModel().getValueAt(baris, 3).toString();
        String telepon = table_detail_pelanggan.getModel().getValueAt(baris, 4).toString();

        editMember set = new editMember();
        set.setVisible(true);

        set.fieldIDMember.setEnabled(false);

        String labelPelanggan = "Perbarui Pelanggan";
//       set.setTitlePelanggan(labelPelanggan);
        set.titlePelanggan.setText(labelPelanggan);

        // mengirimkan setter pada tiap tiap function       
//       set.setIDmember(id_member);
        set.fieldIDMember.setText(id_member);

        set.fieldNamaLengkap.setText(namaLengkap);
        set.fieldAlamat.setText(alamat);
        set.fieldTelepon.setText(telepon);
        if (jenisKelamin.equals("L")) {
            set.opsiLakiLaki.setSelected(true);
        } else if (jenisKelamin.equals("P")) {
            set.OpsiPerempuan.setSelected(true);

        }
    }

    public void mouseClickPegawai() {
        int baris = table_detail_pegawai.getSelectedRow();
        String idPegawai = table_detail_pegawai.getModel().getValueAt(baris, 0).toString();
        String namaPegawai = table_detail_pegawai.getModel().getValueAt(baris, 1).toString();
        String usernamePegawai = table_detail_pegawai.getModel().getValueAt(baris, 2).toString();
        String passwordPegawai = table_detail_pegawai.getModel().getValueAt(baris, 3).toString();
        String id_outlet = table_detail_pegawai.getModel().getValueAt(baris, 4).toString();
        String hakAkses = table_detail_pegawai.getModel().getValueAt(baris, 5).toString();

        UpdateUser user = new UpdateUser();
        user.setVisible(true);

        user.fieldIDUser.setEditable(false);
        user.fieldIDUser.setText(idPegawai);
        user.fieldNamaPegawai.setText(namaPegawai);
        user.fieldUsernamePegawai.setText(usernamePegawai);
        user.fieldPasswordPegawai.setText(passwordPegawai);
        user.comboIDOutlet.setSelectedItem(id_outlet);
        user.ComboHakAkses.setSelectedItem(hakAkses);

    }

    public void mouseClickOutlet() {
        int baris = table_detail_outlet.getSelectedRow();
        String idOutlet = table_detail_outlet.getModel().getValueAt(baris, 0).toString();
        String namaOutlet = table_detail_outlet.getModel().getValueAt(baris, 1).toString();
        String alamatOutlet = table_detail_outlet.getModel().getValueAt(baris, 2).toString();
        String teleponOutlet = table_detail_outlet.getModel().getValueAt(baris, 3).toString();

        UpdateOutlet outlet = new UpdateOutlet();
        outlet.setVisible(true);

        outlet.fieldIDOutlet.setEditable(false);
        outlet.fieldIDOutlet.setText(idOutlet);
        outlet.fieldNamaOutlet.setText(namaOutlet);
        outlet.fieldAlamatOutlet.setText(alamatOutlet);
        outlet.fieldTeleponOutlet.setText(teleponOutlet);

    }

    public void mouseClickPaket() {
        int baris = table_detail_paket.getSelectedRow();
        String idOutlet = table_detail_paket.getModel().getValueAt(baris, 0).toString();
        String idpaket = table_detail_paket.getModel().getValueAt(baris, 1).toString();
        String jenisPaket = table_detail_paket.getModel().getValueAt(baris, 2).toString();
        String namaPaket = table_detail_paket.getModel().getValueAt(baris, 3).toString();
        String hargaPaket = table_detail_paket.getModel().getValueAt(baris, 4).toString();

        comboIDOutlet.setSelectedItem(idOutlet);
        fieldIDPaket.setText(idpaket);
        comboJenisPaket.setSelectedItem(jenisPaket);
        fieldNamaPaket.setText(namaPaket);
        fieldHargaPaket.setText(hargaPaket);
    }

    public void cariDataPelanggan() {
        table_detail_pelanggan.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.TableCustom.apply(tableScroll, table.TableCustom.TableType.MULTI_LINE);
        String search = fieldSearch.getText();
        DefaultTableModel tbl = new DefaultTableModel();

        tbl.addColumn("ID Member");
        tbl.addColumn("Nama Pelanggan");
        tbl.addColumn("Alamat");
        tbl.addColumn("Jenis Kelamin");
        tbl.addColumn("No Telepon");
        table_detail_pelanggan.setModel(tbl);

        try {
            String sql = "select * from tb_member where nama like? or id_member like? or alamat like?";

            this.pst = koneksi.getKoneksi().prepareStatement(sql);
            this.pst.setString(1, "%" + search + "%");
            this.pst.setString(2, "%" + search + "%");
            this.pst.setString(3, "%" + search + "%");
            this.set = pst.executeQuery();

            while (set.next()) {

                tbl.addRow(new Object[]{
                    set.getString("id_member"),
                    set.getString("nama"),
                    set.getString("alamat"),
                    set.getString("jenis_kelamin"),
                    set.getString("tlp"),});
            }
        } catch (Exception e) {
        }

    }

    public void cariDataPegawai() {
        String search = fieldSearchPegawai.getText();
        table_detail_pegawai.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.TableCustom.apply(tablePegawaiScroll, table.TableCustom.TableType.MULTI_LINE);
        DefaultTableModel tbl = new DefaultTableModel();

        tbl.addColumn("ID Pegawai");
        tbl.addColumn("Nama Pegawai");
        tbl.addColumn("Username");
        tbl.addColumn("Password");
        tbl.addColumn("ID Outlet");
        tbl.addColumn("Hak Akses");
        table_detail_pegawai.setModel(tbl);

        try {
//          String sql = "select * from tb_member where id_member like '%" + search + "%' or nama like '%" + search + "%' or jenis_kelamin like '%" + search + "%' or alamat like '%" + search + "%'"; 
            String sql = "select * from tb_user where id_user like '%" + search + "%' or nama like '%" + search + "%' or username like '%" + search + "%' or role like '%" + search + "%'";
            this.pst = koneksi.getKoneksi().prepareStatement(sql);
            this.set = pst.executeQuery(sql);
            while (set.next()) {
                tbl.addRow(new Object[]{
                    set.getString("id_user"),
                    set.getString("nama"),
                    set.getString("username"),
                    set.getString("password"),
                    set.getString("id_outlet"),
                    set.getString("role"),});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void cetakLaporanTransaksi() {
        MessageFormat header = new MessageFormat("Laporan Transaksi Pelanggan");
        MessageFormat footer = new MessageFormat("Data Transaksi Pelanggan Laundry | Page{0, number, integer}");
        try {
            table_detail_transaksi.print(JTable.PrintMode.FIT_WIDTH, header, footer);

        } catch (java.awt.print.PrinterException e) {
            System.err.format("Data tidak bisa diprint", e.getMessage());
        }
    }
    public void statusPembayaran() {
        if(comboStatusPembayaran.getSelectedItem().equals("belum bayar")){
            fieldBiayaAdmin.setText("Rp.1000");
            fieldPajak.setText("2000");
            fieldDiskon.setText("2%"); 
                    
                    
            
       
       
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

        tableCellEditor1 = new dynamic_subjtable.TableCellEditor();
        Side = new javax.swing.JPanel();
        buttonDashboard = new com.k33ptoo.components.KButton();
        buttonPegawai = new com.k33ptoo.components.KButton();
        buttonTransaksi = new com.k33ptoo.components.KButton();
        buttonDetailTransaksi = new com.k33ptoo.components.KButton();
        buttonPelanggan = new com.k33ptoo.components.KButton();
        buttonPaket = new com.k33ptoo.components.KButton();
        buttonOutlet = new com.k33ptoo.components.KButton();
        labelMenu = new javax.swing.JLabel();
        buttonDataTransaksi = new com.k33ptoo.components.KButton();
        Utama = new javax.swing.JPanel();
        Dashboard = new javax.swing.JPanel();
        labelPetugas = new javax.swing.JLabel();
        Dashboard1 = new javax.swing.JLabel();
        JumlahDataPaket = new javax.swing.JLabel();
        DescDataPelanggan1 = new javax.swing.JLabel();
        dashboard7 = new com.k33ptoo.components.KButton();
        dashboard5 = new com.k33ptoo.components.KButton();
        dashboard8 = new com.k33ptoo.components.KButton();
        DescDataPaket = new javax.swing.JLabel();
        JumlahDataPelanggan = new javax.swing.JLabel();
        jumlahDataPegawai = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        Labeltanggal1 = new javax.swing.JLabel();
        DescDataPelanggan2 = new javax.swing.JLabel();
        LabelSelamatDatang = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        Logout = new javax.swing.JLabel();
        background = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCustom1 = new dynamic_subjtable.TableCustom();
        Outlet = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        tableOutletScroll = new javax.swing.JScrollPane();
        table_detail_outlet = new dynamic_subjtable.TableCustom();
        JumlahOutlet = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        descOutlet = new javax.swing.JLabel();
        buttonDashboard8 = new com.k33ptoo.components.KButton();
        buttonDashboard7 = new com.k33ptoo.components.KButton();
        tambahOutlet1 = new javax.swing.JLabel();
        labelUpdate = new javax.swing.JLabel();
        backgroundOutlet = new javax.swing.JLabel();
        pelanggan = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        fieldSearch = new Component.TextField();
        buttonDashboard2 = new com.k33ptoo.components.KButton();
        jLabel3 = new javax.swing.JLabel();
        tableScroll = new javax.swing.JScrollPane();
        table_detail_pelanggan = new dynamic_subjtable.TableCustom();
        pegawai = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        fieldSearchPegawai = new Component.TextField();
        jLabel5 = new javax.swing.JLabel();
        buttonDashboard4 = new com.k33ptoo.components.KButton();
        buttonDashboard5 = new com.k33ptoo.components.KButton();
        buttonDashboard6 = new com.k33ptoo.components.KButton();
        tablePegawaiScroll = new javax.swing.JScrollPane();
        table_detail_pegawai = new dynamic_subjtable.TableCustom();
        Barang = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        fieldHargaPaket = new textfield.TextField();
        comboJenisPaket = new combobox.Combobox();
        dashboard9 = new com.k33ptoo.components.KButton();
        comboIDOutlet = new combobox.Combobox();
        fieldIDPaket = new textfield.TextField();
        fieldNamaPaket = new textfield.TextField();
        scrollPanePaket = new javax.swing.JScrollPane();
        table_detail_paket = new dynamic_subjtable.TableCustom();
        dashboard10 = new com.k33ptoo.components.KButton();
        dashboard11 = new com.k33ptoo.components.KButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        dashboard12 = new com.k33ptoo.components.KButton();
        Transaksi = new javax.swing.JPanel();
        panelRound1 = new test.PanelRound();
        jLabel18 = new javax.swing.JLabel();
        comboIDOutletTransaksi = new combobox.Combobox();
        fieldIDTransaksi = new textfield.TextField();
        jLabel15 = new javax.swing.JLabel();
        comboStatusPesanan = new combobox.Combobox();
        comboNamaPelangganTransaksi = new combobox.Combobox();
        fieldIDPelanggan = new textfield.TextField();
        comboStatusPembayaran = new combobox.Combobox();
        jLabel16 = new javax.swing.JLabel();
        fieldBiayaAdmin = new textfield.TextField();
        jLabel12 = new javax.swing.JLabel();
        fieldPajak = new textfield.TextField();
        jLabel13 = new javax.swing.JLabel();
        fieldDiskon = new textfield.TextField();
        jLabel10 = new javax.swing.JLabel();
        comboNamaPetugas = new combobox.Combobox();
        fieldKodeInvoice = new textfield.TextField();
        jLabel11 = new javax.swing.JLabel();
        fieldIDPetugas = new textfield.TextField();
        dashboard16 = new com.k33ptoo.components.KButton();
        dashboard17 = new com.k33ptoo.components.KButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        fieldBatasWaktu = new com.toedter.calendar.JDateChooser();
        fieldTanggalBayar = new com.toedter.calendar.JDateChooser();
        fieldTanggalPesan = new com.toedter.calendar.JDateChooser();
        DataTransaksi = new javax.swing.JPanel();
        scrollDataTransaksi = new javax.swing.JScrollPane();
        table_data_transaksi = new dynamic_subjtable.TableCustom();
        dashboard19 = new com.k33ptoo.components.KButton();
        dashboard20 = new com.k33ptoo.components.KButton();
        DetailDataTransaksi = new javax.swing.JPanel();
        scrollPanelDetailTransaksi = new javax.swing.JScrollPane();
        table_detail_transaksi = new dynamic_subjtable.TableCustom();
        dashboard21 = new com.k33ptoo.components.KButton();
        dashboard22 = new com.k33ptoo.components.KButton();
        dashboard23 = new com.k33ptoo.components.KButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Side.setBackground(new java.awt.Color(255, 255, 255));

        buttonDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dashboard_icon_menu.png"))); // NOI18N
        buttonDashboard.setText("Dashboard");
        buttonDashboard.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        buttonDashboard.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        buttonDashboard.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonDashboard.setIconTextGap(11);
        buttonDashboard.setkEndColor(new java.awt.Color(255, 255, 255));
        buttonDashboard.setkForeGround(new java.awt.Color(0, 0, 0));
        buttonDashboard.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        buttonDashboard.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        buttonDashboard.setkHoverStartColor(new java.awt.Color(153, 153, 255));
        buttonDashboard.setkPressedColor(new java.awt.Color(153, 153, 255));
        buttonDashboard.setkSelectedColor(new java.awt.Color(153, 153, 255));
        buttonDashboard.setkStartColor(new java.awt.Color(255, 255, 255));
        buttonDashboard.setMargin(new java.awt.Insets(2, 14, 10, 14));
        buttonDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonDashboardMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonDashboardMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonDashboardMousePressed(evt);
            }
        });
        buttonDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDashboardActionPerformed(evt);
            }
        });

        buttonPegawai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/user_1_1.png"))); // NOI18N
        buttonPegawai.setText("Pegawai");
        buttonPegawai.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        buttonPegawai.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        buttonPegawai.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonPegawai.setIconTextGap(15);
        buttonPegawai.setkEndColor(new java.awt.Color(255, 255, 255));
        buttonPegawai.setkForeGround(new java.awt.Color(0, 0, 0));
        buttonPegawai.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        buttonPegawai.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        buttonPegawai.setkHoverStartColor(new java.awt.Color(153, 153, 255));
        buttonPegawai.setkPressedColor(new java.awt.Color(153, 153, 255));
        buttonPegawai.setkSelectedColor(new java.awt.Color(153, 153, 255));
        buttonPegawai.setkStartColor(new java.awt.Color(255, 255, 255));
        buttonPegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonPegawaiMousePressed(evt);
            }
        });
        buttonPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPegawaiActionPerformed(evt);
            }
        });

        buttonTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/transaksi.png"))); // NOI18N
        buttonTransaksi.setText("Transaksi");
        buttonTransaksi.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        buttonTransaksi.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        buttonTransaksi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonTransaksi.setIconTextGap(15);
        buttonTransaksi.setkEndColor(new java.awt.Color(255, 255, 255));
        buttonTransaksi.setkForeGround(new java.awt.Color(0, 0, 0));
        buttonTransaksi.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        buttonTransaksi.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        buttonTransaksi.setkHoverStartColor(new java.awt.Color(153, 153, 255));
        buttonTransaksi.setkPressedColor(new java.awt.Color(153, 153, 255));
        buttonTransaksi.setkSelectedColor(new java.awt.Color(153, 153, 255));
        buttonTransaksi.setkStartColor(new java.awt.Color(255, 255, 255));
        buttonTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonTransaksiMousePressed(evt);
            }
        });
        buttonTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTransaksiActionPerformed(evt);
            }
        });

        buttonDetailTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/monitor (1).png"))); // NOI18N
        buttonDetailTransaksi.setText("  Detail Transaksi");
        buttonDetailTransaksi.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        buttonDetailTransaksi.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        buttonDetailTransaksi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonDetailTransaksi.setIconTextGap(15);
        buttonDetailTransaksi.setkEndColor(new java.awt.Color(255, 255, 255));
        buttonDetailTransaksi.setkForeGround(new java.awt.Color(0, 0, 0));
        buttonDetailTransaksi.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        buttonDetailTransaksi.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        buttonDetailTransaksi.setkHoverStartColor(new java.awt.Color(153, 153, 255));
        buttonDetailTransaksi.setkPressedColor(new java.awt.Color(153, 153, 255));
        buttonDetailTransaksi.setkSelectedColor(new java.awt.Color(153, 153, 255));
        buttonDetailTransaksi.setkStartColor(new java.awt.Color(255, 255, 255));
        buttonDetailTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonDetailTransaksiMousePressed(evt);
            }
        });
        buttonDetailTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDetailTransaksiActionPerformed(evt);
            }
        });

        buttonPelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/customer.png"))); // NOI18N
        buttonPelanggan.setText("Pelanggan");
        buttonPelanggan.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        buttonPelanggan.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        buttonPelanggan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonPelanggan.setIconTextGap(15);
        buttonPelanggan.setkEndColor(new java.awt.Color(255, 255, 255));
        buttonPelanggan.setkForeGround(new java.awt.Color(0, 0, 0));
        buttonPelanggan.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        buttonPelanggan.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        buttonPelanggan.setkHoverStartColor(new java.awt.Color(153, 153, 255));
        buttonPelanggan.setkPressedColor(new java.awt.Color(153, 153, 255));
        buttonPelanggan.setkSelectedColor(new java.awt.Color(153, 153, 255));
        buttonPelanggan.setkStartColor(new java.awt.Color(255, 255, 255));
        buttonPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonPelangganMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonPelangganMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonPelangganMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonPelangganMousePressed(evt);
            }
        });
        buttonPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPelangganActionPerformed(evt);
            }
        });

        buttonPaket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logistics-delivery.png"))); // NOI18N
        buttonPaket.setText("Data Paket");
        buttonPaket.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        buttonPaket.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        buttonPaket.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonPaket.setIconTextGap(15);
        buttonPaket.setkEndColor(new java.awt.Color(255, 255, 255));
        buttonPaket.setkForeGround(new java.awt.Color(0, 0, 0));
        buttonPaket.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        buttonPaket.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        buttonPaket.setkHoverStartColor(new java.awt.Color(153, 153, 255));
        buttonPaket.setkPressedColor(new java.awt.Color(153, 153, 255));
        buttonPaket.setkSelectedColor(new java.awt.Color(153, 153, 255));
        buttonPaket.setkStartColor(new java.awt.Color(255, 255, 255));
        buttonPaket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonPaketMousePressed(evt);
            }
        });
        buttonPaket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPaketActionPerformed(evt);
            }
        });

        buttonOutlet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/squares.png"))); // NOI18N
        buttonOutlet.setText("Outlet");
        buttonOutlet.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        buttonOutlet.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        buttonOutlet.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonOutlet.setIconTextGap(15);
        buttonOutlet.setkEndColor(new java.awt.Color(255, 255, 255));
        buttonOutlet.setkForeGround(new java.awt.Color(0, 0, 0));
        buttonOutlet.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        buttonOutlet.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        buttonOutlet.setkHoverStartColor(new java.awt.Color(153, 153, 255));
        buttonOutlet.setkPressedColor(new java.awt.Color(153, 153, 255));
        buttonOutlet.setkSelectedColor(new java.awt.Color(153, 153, 255));
        buttonOutlet.setkStartColor(new java.awt.Color(255, 255, 255));
        buttonOutlet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonOutletMousePressed(evt);
            }
        });
        buttonOutlet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOutletActionPerformed(evt);
            }
        });

        labelMenu.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        labelMenu.setText("Menu Sesuai Role");

        buttonDataTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/notes_1.png"))); // NOI18N
        buttonDataTransaksi.setText("Data Transaksi");
        buttonDataTransaksi.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        buttonDataTransaksi.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        buttonDataTransaksi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonDataTransaksi.setIconTextGap(15);
        buttonDataTransaksi.setkEndColor(new java.awt.Color(255, 255, 255));
        buttonDataTransaksi.setkForeGround(new java.awt.Color(0, 0, 0));
        buttonDataTransaksi.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        buttonDataTransaksi.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        buttonDataTransaksi.setkHoverStartColor(new java.awt.Color(153, 153, 255));
        buttonDataTransaksi.setkPressedColor(new java.awt.Color(153, 153, 255));
        buttonDataTransaksi.setkSelectedColor(new java.awt.Color(153, 153, 255));
        buttonDataTransaksi.setkStartColor(new java.awt.Color(255, 255, 255));
        buttonDataTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonDataTransaksiMousePressed(evt);
            }
        });
        buttonDataTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDataTransaksiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SideLayout = new javax.swing.GroupLayout(Side);
        Side.setLayout(SideLayout);
        SideLayout.setHorizontalGroup(
            SideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SideLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(SideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SideLayout.createSequentialGroup()
                        .addComponent(labelMenu)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(buttonDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .addComponent(buttonTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonPegawai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonDetailTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonPaket, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonPelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonOutlet, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonDataTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        SideLayout.setVerticalGroup(
            SideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SideLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(labelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(buttonDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonPaket, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonDataTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonDetailTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonOutlet, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        Utama.setBackground(new java.awt.Color(255, 255, 255));
        Utama.setLayout(new java.awt.CardLayout());

        Dashboard.setBackground(new java.awt.Color(255, 255, 255));
        Dashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Laundry image/user.png"))); // NOI18N
        labelPetugas.setText("Nama Pengguna");
        labelPetugas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelPetugasMouseClicked(evt);
            }
        });
        Dashboard.add(labelPetugas, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, -1, -1));

        Dashboard1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        Dashboard1.setText("<html> <u>Dashboard </u> </html>");
        Dashboard.add(Dashboard1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        JumlahDataPaket.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        JumlahDataPaket.setText("Jumlah : ");
        Dashboard.add(JumlahDataPaket, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 250, -1, -1));

        DescDataPelanggan1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        DescDataPelanggan1.setText("Data Pegawai");
        Dashboard.add(DescDataPelanggan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 220, -1, -1));

        dashboard7.setText("Lihat Detail");
        dashboard7.setToolTipText("");
        dashboard7.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        dashboard7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        dashboard7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dashboard7.setIconTextGap(15);
        dashboard7.setkBackGroundColor(new java.awt.Color(102, 102, 255));
        dashboard7.setkEndColor(new java.awt.Color(255, 255, 255));
        dashboard7.setkHoverColor(new java.awt.Color(153, 153, 255));
        dashboard7.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        dashboard7.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        dashboard7.setkHoverStartColor(new java.awt.Color(153, 153, 255));
        dashboard7.setkPressedColor(new java.awt.Color(153, 153, 255));
        dashboard7.setkSelectedColor(new java.awt.Color(153, 153, 255));
        dashboard7.setkStartColor(new java.awt.Color(102, 102, 255));
        dashboard7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboard7ActionPerformed(evt);
            }
        });
        Dashboard.add(dashboard7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 290, 130, -1));

        dashboard5.setText("Lihat Detail");
        dashboard5.setToolTipText("");
        dashboard5.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        dashboard5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        dashboard5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dashboard5.setIconTextGap(15);
        dashboard5.setkBackGroundColor(new java.awt.Color(102, 102, 255));
        dashboard5.setkEndColor(new java.awt.Color(255, 255, 255));
        dashboard5.setkHoverColor(new java.awt.Color(153, 153, 255));
        dashboard5.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        dashboard5.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        dashboard5.setkHoverStartColor(new java.awt.Color(153, 153, 255));
        dashboard5.setkPressedColor(new java.awt.Color(153, 153, 255));
        dashboard5.setkSelectedColor(new java.awt.Color(153, 153, 255));
        dashboard5.setkStartColor(new java.awt.Color(102, 102, 255));
        dashboard5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboard5ActionPerformed(evt);
            }
        });
        Dashboard.add(dashboard5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 290, 130, -1));

        dashboard8.setText("Lihat Detail");
        dashboard8.setToolTipText("");
        dashboard8.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        dashboard8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        dashboard8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dashboard8.setIconTextGap(15);
        dashboard8.setkBackGroundColor(new java.awt.Color(102, 102, 255));
        dashboard8.setkEndColor(new java.awt.Color(255, 255, 255));
        dashboard8.setkHoverColor(new java.awt.Color(153, 153, 255));
        dashboard8.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        dashboard8.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        dashboard8.setkHoverStartColor(new java.awt.Color(153, 153, 255));
        dashboard8.setkPressedColor(new java.awt.Color(153, 153, 255));
        dashboard8.setkSelectedColor(new java.awt.Color(153, 153, 255));
        dashboard8.setkStartColor(new java.awt.Color(102, 102, 255));
        dashboard8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboard8ActionPerformed(evt);
            }
        });
        Dashboard.add(dashboard8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 130, -1));

        DescDataPaket.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        DescDataPaket.setText("Data Paket");
        Dashboard.add(DescDataPaket, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, -1));

        JumlahDataPelanggan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        JumlahDataPelanggan.setText("Jumlah : ");
        Dashboard.add(JumlahDataPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, -1, -1));

        jumlahDataPegawai.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jumlahDataPegawai.setText("Jumlah : ");
        Dashboard.add(jumlahDataPegawai, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 250, -1, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/menu (1).png"))); // NOI18N
        Dashboard.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 30, 20));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/menu (1).png"))); // NOI18N
        Dashboard.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 200, 30, 20));

        Labeltanggal1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Labeltanggal1.setForeground(new java.awt.Color(102, 102, 255));
        Labeltanggal1.setText("12 Januari, 2023");
        Dashboard.add(Labeltanggal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, -1, -1));

        DescDataPelanggan2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        DescDataPelanggan2.setText("Data Pelanggan");
        Dashboard.add(DescDataPelanggan2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, -1, -1));

        LabelSelamatDatang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LabelSelamatDatang.setText("Selamat Datang Nama Petugas");
        Dashboard.add(LabelSelamatDatang, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, -1, -1));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/menu (1).png"))); // NOI18N
        Dashboard.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, 30, 20));

        Logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logout_1.png"))); // NOI18N
        Logout.setText("Logout");
        Logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogoutMouseClicked(evt);
            }
        });
        Dashboard.add(Logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 10, -1, 30));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageBackground/bg dashboard admin utama.png"))); // NOI18N
        Dashboard.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 940, -1));

        tableCustom1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableCustom1);

        Dashboard.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, -90, -1, -1));

        Utama.add(Dashboard, "card2");

        Outlet.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table_detail_outlet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_detail_outlet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_detail_outletMouseClicked(evt);
            }
        });
        tableOutletScroll.setViewportView(table_detail_outlet);

        jPanel2.add(tableOutletScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 880, 290));

        JumlahOutlet.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        JumlahOutlet.setText("Jumlah  3");
        jPanel2.add(JumlahOutlet, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 260, 80, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 50, 30, 40));

        descOutlet.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        descOutlet.setText("Detail Outlet");
        jPanel2.add(descOutlet, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, -1, -1));

        buttonDashboard8.setText("Update");
        buttonDashboard8.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        buttonDashboard8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        buttonDashboard8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonDashboard8.setIconTextGap(11);
        buttonDashboard8.setkBackGroundColor(new java.awt.Color(153, 0, 255));
        buttonDashboard8.setkBorderRadius(12);
        buttonDashboard8.setkEndColor(new java.awt.Color(153, 102, 255));
        buttonDashboard8.setkHoverColor(new java.awt.Color(153, 102, 255));
        buttonDashboard8.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        buttonDashboard8.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        buttonDashboard8.setkHoverStartColor(new java.awt.Color(153, 51, 255));
        buttonDashboard8.setkIndicatorColor(new java.awt.Color(153, 0, 255));
        buttonDashboard8.setkPressedColor(new java.awt.Color(153, 51, 255));
        buttonDashboard8.setkSelectedColor(new java.awt.Color(153, 51, 255));
        buttonDashboard8.setkStartColor(new java.awt.Color(153, 153, 255));
        buttonDashboard8.setMargin(new java.awt.Insets(2, 14, 10, 14));
        buttonDashboard8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonDashboard8MouseClicked(evt);
            }
        });
        buttonDashboard8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDashboard8ActionPerformed(evt);
            }
        });
        jPanel2.add(buttonDashboard8, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 250, 120, -1));

        buttonDashboard7.setText("Tambah");
        buttonDashboard7.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        buttonDashboard7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        buttonDashboard7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonDashboard7.setIconTextGap(11);
        buttonDashboard7.setkBackGroundColor(new java.awt.Color(153, 0, 255));
        buttonDashboard7.setkBorderRadius(12);
        buttonDashboard7.setkEndColor(new java.awt.Color(153, 102, 255));
        buttonDashboard7.setkHoverColor(new java.awt.Color(153, 102, 255));
        buttonDashboard7.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        buttonDashboard7.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        buttonDashboard7.setkHoverStartColor(new java.awt.Color(153, 51, 255));
        buttonDashboard7.setkIndicatorColor(new java.awt.Color(153, 0, 255));
        buttonDashboard7.setkPressedColor(new java.awt.Color(153, 51, 255));
        buttonDashboard7.setkSelectedColor(new java.awt.Color(153, 51, 255));
        buttonDashboard7.setkStartColor(new java.awt.Color(153, 153, 255));
        buttonDashboard7.setMargin(new java.awt.Insets(2, 14, 10, 14));
        buttonDashboard7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonDashboard7MouseClicked(evt);
            }
        });
        buttonDashboard7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDashboard7ActionPerformed(evt);
            }
        });
        jPanel2.add(buttonDashboard7, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 250, 120, -1));

        tambahOutlet1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tambahOutlet1.setText("Update Outlet");
        jPanel2.add(tambahOutlet1, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 220, 120, -1));

        labelUpdate.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelUpdate.setText("Tambah Outlet");
        jPanel2.add(labelUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 220, 120, -1));

        backgroundOutlet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageBackground/bg dashboard panel.png"))); // NOI18N
        backgroundOutlet.setText("jLabel1");
        jPanel2.add(backgroundOutlet, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 930, 490));

        Outlet.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1160, 760));

        Utama.add(Outlet, "card4");

        pelanggan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/magnifying-glass (1).png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 70, -1, 40));

        fieldSearch.setBackground(new java.awt.Color(204, 204, 204));
        fieldSearch.setForeground(new java.awt.Color(102, 102, 102));
        fieldSearch.setText("Cari Data Pelanggan");
        fieldSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldSearchFocusLost(evt);
            }
        });
        fieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fieldSearchKeyReleased(evt);
            }
        });
        jPanel1.add(fieldSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 70, 200, 50));

        buttonDashboard2.setText("Tambah");
        buttonDashboard2.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        buttonDashboard2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        buttonDashboard2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonDashboard2.setIconTextGap(11);
        buttonDashboard2.setkBackGroundColor(new java.awt.Color(153, 0, 255));
        buttonDashboard2.setkEndColor(new java.awt.Color(153, 102, 255));
        buttonDashboard2.setkHoverColor(new java.awt.Color(153, 102, 255));
        buttonDashboard2.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        buttonDashboard2.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        buttonDashboard2.setkHoverStartColor(new java.awt.Color(153, 51, 255));
        buttonDashboard2.setkIndicatorColor(new java.awt.Color(153, 0, 255));
        buttonDashboard2.setkPressedColor(new java.awt.Color(153, 51, 255));
        buttonDashboard2.setkSelectedColor(new java.awt.Color(153, 51, 255));
        buttonDashboard2.setkStartColor(new java.awt.Color(153, 153, 255));
        buttonDashboard2.setMargin(new java.awt.Insets(2, 14, 10, 14));
        buttonDashboard2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonDashboard2MouseClicked(evt);
            }
        });
        buttonDashboard2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDashboard2ActionPerformed(evt);
            }
        });
        jPanel1.add(buttonDashboard2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 130, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 70, 30, 40));

        table_detail_pelanggan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_detail_pelanggan.setGridColor(new java.awt.Color(153, 102, 255));
        table_detail_pelanggan.setSelectionBackground(new java.awt.Color(204, 204, 204));
        table_detail_pelanggan.setSelectionForeground(new java.awt.Color(51, 51, 51));
        table_detail_pelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_detail_pelangganMouseClicked(evt);
            }
        });
        tableScroll.setViewportView(table_detail_pelanggan);

        jPanel1.add(tableScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 850, 560));

        pelanggan.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1160, 760));

        Utama.add(pelanggan, "card5");

        pegawai.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/magnifying-glass (1).png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        fieldSearchPegawai.setBackground(new java.awt.Color(204, 204, 204));
        fieldSearchPegawai.setForeground(new java.awt.Color(102, 102, 102));
        fieldSearchPegawai.setText("Cari Data Pegawai");
        fieldSearchPegawai.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldSearchPegawaiFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldSearchPegawaiFocusLost(evt);
            }
        });
        fieldSearchPegawai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fieldSearchPegawaiKeyReleased(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        buttonDashboard4.setText("Hapus");
        buttonDashboard4.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        buttonDashboard4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        buttonDashboard4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonDashboard4.setIconTextGap(11);
        buttonDashboard4.setkBackGroundColor(new java.awt.Color(153, 0, 255));
        buttonDashboard4.setkEndColor(new java.awt.Color(153, 102, 255));
        buttonDashboard4.setkHoverColor(new java.awt.Color(153, 102, 255));
        buttonDashboard4.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        buttonDashboard4.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        buttonDashboard4.setkHoverStartColor(new java.awt.Color(153, 51, 255));
        buttonDashboard4.setkIndicatorColor(new java.awt.Color(153, 0, 255));
        buttonDashboard4.setkPressedColor(new java.awt.Color(153, 51, 255));
        buttonDashboard4.setkSelectedColor(new java.awt.Color(153, 51, 255));
        buttonDashboard4.setkStartColor(new java.awt.Color(153, 153, 255));
        buttonDashboard4.setMargin(new java.awt.Insets(2, 14, 10, 14));
        buttonDashboard4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonDashboard4MouseClicked(evt);
            }
        });
        buttonDashboard4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDashboard4ActionPerformed(evt);
            }
        });

        buttonDashboard5.setText("Perbarui");
        buttonDashboard5.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        buttonDashboard5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        buttonDashboard5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonDashboard5.setIconTextGap(11);
        buttonDashboard5.setkBackGroundColor(new java.awt.Color(153, 0, 255));
        buttonDashboard5.setkEndColor(new java.awt.Color(153, 102, 255));
        buttonDashboard5.setkHoverColor(new java.awt.Color(153, 102, 255));
        buttonDashboard5.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        buttonDashboard5.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        buttonDashboard5.setkHoverStartColor(new java.awt.Color(153, 51, 255));
        buttonDashboard5.setkIndicatorColor(new java.awt.Color(153, 0, 255));
        buttonDashboard5.setkPressedColor(new java.awt.Color(153, 51, 255));
        buttonDashboard5.setkSelectedColor(new java.awt.Color(153, 51, 255));
        buttonDashboard5.setkStartColor(new java.awt.Color(153, 153, 255));
        buttonDashboard5.setMargin(new java.awt.Insets(2, 14, 10, 14));
        buttonDashboard5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonDashboard5MouseClicked(evt);
            }
        });
        buttonDashboard5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDashboard5ActionPerformed(evt);
            }
        });

        buttonDashboard6.setText("Tambah");
        buttonDashboard6.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        buttonDashboard6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        buttonDashboard6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonDashboard6.setIconTextGap(11);
        buttonDashboard6.setkBackGroundColor(new java.awt.Color(153, 0, 255));
        buttonDashboard6.setkEndColor(new java.awt.Color(153, 102, 255));
        buttonDashboard6.setkHoverColor(new java.awt.Color(153, 102, 255));
        buttonDashboard6.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        buttonDashboard6.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        buttonDashboard6.setkHoverStartColor(new java.awt.Color(153, 51, 255));
        buttonDashboard6.setkIndicatorColor(new java.awt.Color(153, 0, 255));
        buttonDashboard6.setkPressedColor(new java.awt.Color(153, 51, 255));
        buttonDashboard6.setkSelectedColor(new java.awt.Color(153, 51, 255));
        buttonDashboard6.setkStartColor(new java.awt.Color(153, 153, 255));
        buttonDashboard6.setMargin(new java.awt.Insets(2, 14, 10, 14));
        buttonDashboard6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonDashboard6MouseClicked(evt);
            }
        });
        buttonDashboard6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDashboard6ActionPerformed(evt);
            }
        });

        table_detail_pegawai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_detail_pegawai.setSelectionBackground(new java.awt.Color(204, 204, 204));
        table_detail_pegawai.setSelectionForeground(new java.awt.Color(51, 51, 51));
        table_detail_pegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_detail_pegawaiMouseClicked(evt);
            }
        });
        tablePegawaiScroll.setViewportView(table_detail_pegawai);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tablePegawaiScroll)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(buttonDashboard6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(buttonDashboard5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(buttonDashboard4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(170, 170, 170)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(fieldSearchPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addContainerGap(193, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(327, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonDashboard6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonDashboard5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonDashboard4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldSearchPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(tablePegawaiScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(283, 283, 283))
        );

        pegawai.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, -140, 1180, 890));

        Utama.add(pegawai, "card6");

        Barang.setBackground(new java.awt.Color(255, 255, 255));
        Barang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Paket Laundry");
        Barang.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 160, 180, 30));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/paket laundry.png"))); // NOI18N
        Barang.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 30, 220, 110));

        fieldHargaPaket.setLabelText("Harga Paket");
        fieldHargaPaket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fieldHargaPaketMouseClicked(evt);
            }
        });
        fieldHargaPaket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldHargaPaketActionPerformed(evt);
            }
        });
        fieldHargaPaket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fieldHargaPaketKeyPressed(evt);
            }
        });
        Barang.add(fieldHargaPaket, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 490, 280, -1));

        comboJenisPaket.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "kiloan", "selimut", "bed cover", "kaos", "lain" }));
        comboJenisPaket.setLabeText("");
        comboJenisPaket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboJenisPaketActionPerformed(evt);
            }
        });
        Barang.add(comboJenisPaket, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 370, 280, -1));

        dashboard9.setText("Hapus");
        dashboard9.setToolTipText("");
        dashboard9.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        dashboard9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        dashboard9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dashboard9.setIconTextGap(15);
        dashboard9.setkBackGroundColor(new java.awt.Color(102, 102, 255));
        dashboard9.setkEndColor(new java.awt.Color(255, 255, 255));
        dashboard9.setkHoverColor(new java.awt.Color(153, 153, 255));
        dashboard9.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        dashboard9.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        dashboard9.setkHoverStartColor(new java.awt.Color(153, 153, 255));
        dashboard9.setkPressedColor(new java.awt.Color(153, 153, 255));
        dashboard9.setkSelectedColor(new java.awt.Color(153, 153, 255));
        dashboard9.setkStartColor(new java.awt.Color(102, 102, 255));
        dashboard9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboard9ActionPerformed(evt);
            }
        });
        Barang.add(dashboard9, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 570, 80, -1));

        comboIDOutlet.setLabeText("");
        comboIDOutlet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboIDOutletActionPerformed(evt);
            }
        });
        Barang.add(comboIDOutlet, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 240, 280, -1));

        fieldIDPaket.setLabelText("ID Paket");
        fieldIDPaket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fieldIDPaketMouseClicked(evt);
            }
        });
        fieldIDPaket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fieldIDPaketKeyPressed(evt);
            }
        });
        Barang.add(fieldIDPaket, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 300, 280, -1));

        fieldNamaPaket.setLabelText("Nama Paket");
        fieldNamaPaket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fieldNamaPaketKeyPressed(evt);
            }
        });
        Barang.add(fieldNamaPaket, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 430, 280, -1));

        table_detail_paket.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_detail_paket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_detail_paketMouseClicked(evt);
            }
        });
        scrollPanePaket.setViewportView(table_detail_paket);

        Barang.add(scrollPanePaket, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 660, 590));

        dashboard10.setText("Bersihkan Data");
        dashboard10.setToolTipText("");
        dashboard10.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        dashboard10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        dashboard10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dashboard10.setIconTextGap(15);
        dashboard10.setkBackGroundColor(new java.awt.Color(102, 102, 255));
        dashboard10.setkEndColor(new java.awt.Color(102, 51, 255));
        dashboard10.setkHoverColor(new java.awt.Color(153, 153, 255));
        dashboard10.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        dashboard10.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        dashboard10.setkHoverStartColor(new java.awt.Color(153, 153, 255));
        dashboard10.setkPressedColor(new java.awt.Color(153, 153, 255));
        dashboard10.setkSelectedColor(new java.awt.Color(153, 153, 255));
        dashboard10.setkStartColor(new java.awt.Color(102, 102, 255));
        dashboard10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboard10ActionPerformed(evt);
            }
        });
        Barang.add(dashboard10, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 630, 290, -1));

        dashboard11.setText("Update ");
        dashboard11.setToolTipText("");
        dashboard11.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        dashboard11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        dashboard11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dashboard11.setIconTextGap(15);
        dashboard11.setkBackGroundColor(new java.awt.Color(102, 102, 255));
        dashboard11.setkEndColor(new java.awt.Color(255, 255, 255));
        dashboard11.setkHoverColor(new java.awt.Color(153, 153, 255));
        dashboard11.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        dashboard11.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        dashboard11.setkHoverStartColor(new java.awt.Color(153, 153, 255));
        dashboard11.setkPressedColor(new java.awt.Color(153, 153, 255));
        dashboard11.setkSelectedColor(new java.awt.Color(153, 153, 255));
        dashboard11.setkStartColor(new java.awt.Color(102, 102, 255));
        dashboard11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboard11ActionPerformed(evt);
            }
        });
        Barang.add(dashboard11, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 570, 90, -1));

        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setText("ID Outlet");
        Barang.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 220, -1, 30));

        jLabel9.setForeground(new java.awt.Color(153, 153, 153));
        jLabel9.setText("Jenis Paket");
        Barang.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 354, -1, 20));

        dashboard12.setText("Tambah");
        dashboard12.setToolTipText("");
        dashboard12.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        dashboard12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        dashboard12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dashboard12.setIconTextGap(15);
        dashboard12.setkBackGroundColor(new java.awt.Color(102, 102, 255));
        dashboard12.setkEndColor(new java.awt.Color(255, 255, 255));
        dashboard12.setkHoverColor(new java.awt.Color(153, 153, 255));
        dashboard12.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        dashboard12.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        dashboard12.setkHoverStartColor(new java.awt.Color(153, 153, 255));
        dashboard12.setkPressedColor(new java.awt.Color(153, 153, 255));
        dashboard12.setkSelectedColor(new java.awt.Color(153, 153, 255));
        dashboard12.setkStartColor(new java.awt.Color(102, 102, 255));
        dashboard12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboard12ActionPerformed(evt);
            }
        });
        Barang.add(dashboard12, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 570, 100, -1));

        Utama.add(Barang, "card3");

        Transaksi.setBackground(new java.awt.Color(255, 255, 255));
        Transaksi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setRoundBottomLeft(100);
        panelRound1.setRoundBottomRight(100);
        panelRound1.setRoundTopLeft(100);
        panelRound1.setRoundTopRight(100);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo/Decoration Bubble (1).png"))); // NOI18N
        panelRound1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 110, 50));

        comboIDOutletTransaksi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masukan ID Outlet" }));
        comboIDOutletTransaksi.setAlignmentX(0.0F);
        comboIDOutletTransaksi.setLabeText("");
        panelRound1.add(comboIDOutletTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 220, 40));

        fieldIDTransaksi.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        fieldIDTransaksi.setLabelText("ID Transaksi");
        fieldIDTransaksi.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        panelRound1.add(fieldIDTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 220, -1));

        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Status Pesanan");
        panelRound1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, 90, 20));

        comboStatusPesanan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "baru ", "proses", "selesai", "diambil" }));
        comboStatusPesanan.setLabeText("");
        panelRound1.add(comboStatusPesanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, 220, 40));

        comboNamaPelangganTransaksi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masukan Nama Pelanggan" }));
        comboNamaPelangganTransaksi.setLabeText("");
        comboNamaPelangganTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboNamaPelangganTransaksiActionPerformed(evt);
            }
        });
        panelRound1.add(comboNamaPelangganTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 220, 40));

        fieldIDPelanggan.setEditable(false);
        fieldIDPelanggan.setBackground(new java.awt.Color(255, 255, 255));
        fieldIDPelanggan.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        fieldIDPelanggan.setLabelText("ID Pelanggan");
        panelRound1.add(fieldIDPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, 220, -1));

        comboStatusPembayaran.setEditable(true);
        comboStatusPembayaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "belum bayar" }));
        comboStatusPembayaran.setLabeText("");
        comboStatusPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboStatusPembayaranActionPerformed(evt);
            }
        });
        panelRound1.add(comboStatusPembayaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, 220, 40));

        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Status Pembayaran");
        panelRound1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 210, 120, 20));

        fieldBiayaAdmin.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        fieldBiayaAdmin.setLabelText("Biaya Admin");
        panelRound1.add(fieldBiayaAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 290, 220, -1));

        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Tanggal Pemesanan");
        panelRound1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, 130, 20));

        fieldPajak.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        fieldPajak.setLabelText("Pajak");
        panelRound1.add(fieldPajak, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 350, 220, -1));

        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Batas Waktu");
        panelRound1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, 90, 20));

        fieldDiskon.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        fieldDiskon.setLabelText("Diskon");
        panelRound1.add(fieldDiskon, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 420, 220, -1));

        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Tanggal Bayar");
        panelRound1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 470, 90, 20));

        comboNamaPetugas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masukan Nama Petugas" }));
        comboNamaPetugas.setLabeText("");
        comboNamaPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboNamaPetugasActionPerformed(evt);
            }
        });
        panelRound1.add(comboNamaPetugas, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 490, 220, 40));

        fieldKodeInvoice.setEditable(false);
        fieldKodeInvoice.setBackground(new java.awt.Color(255, 255, 255));
        fieldKodeInvoice.setLabelText("");
        panelRound1.add(fieldKodeInvoice, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 550, 220, -1));

        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Kode Invoice");
        panelRound1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 530, 80, 20));

        fieldIDPetugas.setEditable(false);
        fieldIDPetugas.setBackground(new java.awt.Color(255, 255, 255));
        fieldIDPetugas.setForeground(new java.awt.Color(51, 51, 51));
        fieldIDPetugas.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        fieldIDPetugas.setLabelText("ID Petugas");
        panelRound1.add(fieldIDPetugas, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 550, 220, -1));

        dashboard16.setText("Bersihkan Pesanan");
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
        panelRound1.add(dashboard16, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 620, 240, -1));

        dashboard17.setText("Proses Pesanan");
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
        panelRound1.add(dashboard17, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 620, 270, -1));

        jLabel17.setBackground(new java.awt.Color(102, 102, 255));
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Transaksi Laundry");
        panelRound1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 200, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo/Decoration Bubble (1).png"))); // NOI18N
        panelRound1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, 130, 50));

        fieldBatasWaktu.setBackground(new java.awt.Color(255, 255, 255));
        fieldBatasWaktu.setDateFormatString("MMM d, yyyy HH:MM");
        panelRound1.add(fieldBatasWaktu, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 440, 220, -1));

        fieldTanggalBayar.setBackground(new java.awt.Color(255, 255, 255));
        fieldTanggalBayar.setDateFormatString("MMM d, yyyy HH:MM");
        panelRound1.add(fieldTanggalBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 500, 220, -1));

        fieldTanggalPesan.setBackground(new java.awt.Color(255, 255, 255));
        fieldTanggalPesan.setDateFormatString("MMM d, yyyy HH:MM");
        panelRound1.add(fieldTanggalPesan, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 220, -1));

        Transaksi.add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 680, 710));

        Utama.add(Transaksi, "card7");

        DataTransaksi.setBackground(new java.awt.Color(255, 255, 255));
        DataTransaksi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table_data_transaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_data_transaksi.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        table_data_transaksi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        table_data_transaksi.setSelectionBackground(new java.awt.Color(102, 102, 255));
        table_data_transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_data_transaksiMouseClicked(evt);
            }
        });
        scrollDataTransaksi.setViewportView(table_data_transaksi);

        DataTransaksi.add(scrollDataTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 145, 1120, 552));

        dashboard19.setText("Tambah Transaksi");
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
        DataTransaksi.add(dashboard19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 130, -1));

        dashboard20.setText("Refresh Data");
        dashboard20.setToolTipText("");
        dashboard20.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        dashboard20.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        dashboard20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dashboard20.setIconTextGap(15);
        dashboard20.setkBackGroundColor(new java.awt.Color(102, 102, 255));
        dashboard20.setkBorderRadius(20);
        dashboard20.setkEndColor(new java.awt.Color(51, 51, 255));
        dashboard20.setkHoverColor(new java.awt.Color(153, 153, 255));
        dashboard20.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        dashboard20.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        dashboard20.setkHoverStartColor(new java.awt.Color(153, 153, 255));
        dashboard20.setkPressedColor(new java.awt.Color(153, 153, 255));
        dashboard20.setkSelectedColor(new java.awt.Color(153, 153, 255));
        dashboard20.setkStartColor(new java.awt.Color(153, 153, 255));
        dashboard20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboard20ActionPerformed(evt);
            }
        });
        DataTransaksi.add(dashboard20, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 130, -1));

        Utama.add(DataTransaksi, "card8");

        DetailDataTransaksi.setBackground(new java.awt.Color(255, 255, 255));
        DetailDataTransaksi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table_detail_transaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_detail_transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_detail_transaksiMouseClicked(evt);
            }
        });
        scrollPanelDetailTransaksi.setViewportView(table_detail_transaksi);

        DetailDataTransaksi.add(scrollPanelDetailTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 1110, 520));

        dashboard21.setText("Lanjutkan Transaksi");
        dashboard21.setToolTipText("");
        dashboard21.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        dashboard21.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        dashboard21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dashboard21.setIconTextGap(15);
        dashboard21.setkBackGroundColor(new java.awt.Color(102, 102, 255));
        dashboard21.setkBorderRadius(20);
        dashboard21.setkEndColor(new java.awt.Color(51, 51, 255));
        dashboard21.setkHoverColor(new java.awt.Color(153, 153, 255));
        dashboard21.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        dashboard21.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        dashboard21.setkHoverStartColor(new java.awt.Color(153, 153, 255));
        dashboard21.setkPressedColor(new java.awt.Color(153, 153, 255));
        dashboard21.setkSelectedColor(new java.awt.Color(153, 153, 255));
        dashboard21.setkStartColor(new java.awt.Color(153, 153, 255));
        dashboard21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboard21ActionPerformed(evt);
            }
        });
        DetailDataTransaksi.add(dashboard21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 130, -1));

        dashboard22.setText("Cetak Laporan");
        dashboard22.setToolTipText("");
        dashboard22.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        dashboard22.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        dashboard22.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dashboard22.setIconTextGap(15);
        dashboard22.setkBackGroundColor(new java.awt.Color(102, 102, 255));
        dashboard22.setkBorderRadius(20);
        dashboard22.setkEndColor(new java.awt.Color(51, 51, 255));
        dashboard22.setkHoverColor(new java.awt.Color(153, 153, 255));
        dashboard22.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        dashboard22.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        dashboard22.setkHoverStartColor(new java.awt.Color(153, 153, 255));
        dashboard22.setkPressedColor(new java.awt.Color(153, 153, 255));
        dashboard22.setkSelectedColor(new java.awt.Color(153, 153, 255));
        dashboard22.setkStartColor(new java.awt.Color(153, 153, 255));
        dashboard22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboard22ActionPerformed(evt);
            }
        });
        DetailDataTransaksi.add(dashboard22, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 130, -1));

        dashboard23.setText("Refresh Data");
        dashboard23.setToolTipText("");
        dashboard23.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        dashboard23.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        dashboard23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dashboard23.setIconTextGap(15);
        dashboard23.setkBackGroundColor(new java.awt.Color(102, 102, 255));
        dashboard23.setkBorderRadius(20);
        dashboard23.setkEndColor(new java.awt.Color(51, 51, 255));
        dashboard23.setkHoverColor(new java.awt.Color(153, 153, 255));
        dashboard23.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        dashboard23.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        dashboard23.setkHoverStartColor(new java.awt.Color(153, 153, 255));
        dashboard23.setkPressedColor(new java.awt.Color(153, 153, 255));
        dashboard23.setkSelectedColor(new java.awt.Color(153, 153, 255));
        dashboard23.setkStartColor(new java.awt.Color(153, 153, 255));
        dashboard23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboard23ActionPerformed(evt);
            }
        });
        DetailDataTransaksi.add(dashboard23, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 130, -1));

        Utama.add(DetailDataTransaksi, "card9");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Side, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1074, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 183, Short.MAX_VALUE)
                    .addComponent(Utama, javax.swing.GroupLayout.PREFERRED_SIZE, 1144, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Side, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Utama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPegawaiActionPerformed
        detailDataPegawai();
        Utama.removeAll();
        Utama.repaint();
        Utama.revalidate();

        // add new panel
        Utama.add(pegawai);
        Utama.repaint();
        Utama.revalidate();
    }//GEN-LAST:event_buttonPegawaiActionPerformed

    private void buttonTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTransaksiActionPerformed
        Date date = new Date();
        fieldTanggalPesan.setDate(date);

        IDOutlet();
        autoKodeInvoice();

        Namamember();
        autoNumberIDTransaksi();
        Utama.removeAll();
        Utama.repaint();
        Utama.revalidate();

        // add new panel
        Utama.add(Transaksi);
        Utama.repaint();
        Utama.revalidate();
    }//GEN-LAST:event_buttonTransaksiActionPerformed

    private void buttonDetailTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDetailTransaksiActionPerformed
        detailDataTransaksi();

        Utama.removeAll();
        Utama.repaint();
        Utama.revalidate();

        // add new panel
        Utama.add(DetailDataTransaksi);
        Utama.repaint();
        Utama.revalidate();
    }//GEN-LAST:event_buttonDetailTransaksiActionPerformed

    private void buttonPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPelangganActionPerformed

    }//GEN-LAST:event_buttonPelangganActionPerformed

    private void buttonPaketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPaketActionPerformed
        autoNumberIDPaket();
        detailDataPaket();
        IDOutlet();
        // remove panel sebelumnya
        Utama.removeAll();
        Utama.repaint();
        Utama.revalidate();

        // add new panel
        Utama.add(Barang);
        Utama.repaint();
        Utama.revalidate();


    }//GEN-LAST:event_buttonPaketActionPerformed

    private void buttonOutletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOutletActionPerformed
        detailDataOutlet();
        jumlahOutlet();

        // remove panel sebelumnya
        Utama.removeAll();
        Utama.repaint();
        Utama.revalidate();

        // add new panel
        Utama.add(Outlet);
        Utama.repaint();
        Utama.revalidate();
    }//GEN-LAST:event_buttonOutletActionPerformed

    private void buttonDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDashboardActionPerformed


    }//GEN-LAST:event_buttonDashboardActionPerformed

    private void LogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseClicked

        int option = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin keluar?");
        if (option == 0) {
            Login showLoginAnggota = new Login();
            showLoginAnggota.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_LogoutMouseClicked

    private void labelPetugasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPetugasMouseClicked


    }//GEN-LAST:event_labelPetugasMouseClicked

    private void dashboard5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboard5ActionPerformed
        Utama.removeAll();
        Utama.repaint();
        Utama.revalidate();

        // add new panel
        Utama.add(pegawai);
        Utama.repaint();
        Utama.revalidate();

        resetColor(buttonPelanggan);
        resetColor(buttonDashboard);
        resetColor(buttonPaket);
        resetColor(buttonTransaksi);
        resetColor(buttonDataTransaksi);
        resetColor(buttonDetailTransaksi);
        setColor(buttonPegawai);
        resetColor(buttonOutlet);

        detailDataPegawai();
    }//GEN-LAST:event_dashboard5ActionPerformed

    private void dashboard7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboard7ActionPerformed
        autoNumberIDPaket();
        detailDataPaket();
        IDOutlet();

        Utama.removeAll();
        Utama.repaint();
        Utama.revalidate();

        // add new panel
        Utama.add(Barang);
        Utama.repaint();
        Utama.revalidate();

        setColor(buttonPaket);
        resetColor(buttonDashboard);
        resetColor(buttonPelanggan);
        resetColor(buttonTransaksi);
        resetColor(buttonDataTransaksi);
        resetColor(buttonDetailTransaksi);
        resetColor(buttonPegawai);
        resetColor(buttonOutlet);

    }//GEN-LAST:event_dashboard7ActionPerformed

    private void dashboard8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboard8ActionPerformed
        Utama.removeAll();
        Utama.repaint();
        Utama.revalidate();

        // add new panel
        Utama.add(pelanggan);
        Utama.repaint();
        Utama.revalidate();

        setColor(buttonPelanggan);
        resetColor(buttonDashboard);
        resetColor(buttonPaket);
        resetColor(buttonTransaksi);
        resetColor(buttonDataTransaksi);
        resetColor(buttonDetailTransaksi);
        resetColor(buttonPegawai);
        resetColor(buttonOutlet);

        DetailDataPelanggan();
    }//GEN-LAST:event_dashboard8ActionPerformed

    private void buttonDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonDashboardMouseClicked
        buttonDashboard();
    }//GEN-LAST:event_buttonDashboardMouseClicked

    private void fieldSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldSearchFocusGained
        // TODO add your handling code here:
        if (fieldSearch.getText().equals("Cari Data Pelanggan")) {
            fieldSearch.setText("");
        }
    }//GEN-LAST:event_fieldSearchFocusGained

    private void fieldSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldSearchFocusLost
        // TODO add your handling code here:
        if (fieldSearch.getText().equals("")) {
            fieldSearch.setText("Cari Data Pelanggan");
        }
    }//GEN-LAST:event_fieldSearchFocusLost

    private void buttonDashboard2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonDashboard2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonDashboard2MouseClicked

    private void buttonDashboard2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDashboard2ActionPerformed
        new member().setVisible(true);
    }//GEN-LAST:event_buttonDashboard2ActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        DetailDataPelanggan();

    }//GEN-LAST:event_jLabel3MouseClicked

    private void fieldSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldSearchKeyReleased

    }//GEN-LAST:event_fieldSearchKeyReleased

    private void table_detail_pelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_detail_pelangganMouseClicked
        mouseClickPelanggan();
    }//GEN-LAST:event_table_detail_pelangganMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        cariDataPelanggan();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void fieldSearchPegawaiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldSearchPegawaiFocusGained
        if (fieldSearchPegawai.getText().equals("Cari Data Pegawai")) {
            fieldSearchPegawai.setText("");
        }
    }//GEN-LAST:event_fieldSearchPegawaiFocusGained

    private void fieldSearchPegawaiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldSearchPegawaiFocusLost
        if (fieldSearchPegawai.getText().equals("")) {
            fieldSearchPegawai.setText("Cari Data Pegawai");
        }
    }//GEN-LAST:event_fieldSearchPegawaiFocusLost

    private void fieldSearchPegawaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldSearchPegawaiKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldSearchPegawaiKeyReleased

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        cariDataPegawai();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        detailDataPegawai();
        fieldSearchPegawai.setText("");
    }//GEN-LAST:event_jLabel5MouseClicked

    private void buttonDashboard4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonDashboard4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonDashboard4MouseClicked

    private void buttonDashboard4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDashboard4ActionPerformed
        JOptionPane.showMessageDialog(null, "Pilih data yang ingin di hapus dari table", "Pesan Dari Admin", JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_buttonDashboard4ActionPerformed

    private void buttonDashboard5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonDashboard5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonDashboard5MouseClicked

    private void buttonDashboard5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDashboard5ActionPerformed
        JOptionPane.showMessageDialog(null, "Pilih data yang ingin di edit dari table", "Pesan Dari Admin", JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_buttonDashboard5ActionPerformed

    private void buttonDashboard6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonDashboard6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonDashboard6MouseClicked

    private void buttonDashboard6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDashboard6ActionPerformed
        TambahUser user = new TambahUser();
        user.setVisible(true);
    }//GEN-LAST:event_buttonDashboard6ActionPerformed

    private void table_detail_pegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_detail_pegawaiMouseClicked
        mouseClickPegawai();
    }//GEN-LAST:event_table_detail_pegawaiMouseClicked

    private void buttonDashboard7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonDashboard7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonDashboard7MouseClicked

    private void buttonDashboard7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDashboard7ActionPerformed
        new TambahOutlet().setVisible(true);
    }//GEN-LAST:event_buttonDashboard7ActionPerformed

    private void buttonDashboard8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonDashboard8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonDashboard8MouseClicked

    private void buttonDashboard8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDashboard8ActionPerformed
        JOptionPane.showMessageDialog(null, "Pilih data yang ingin diupdate pada table", "Pesan Dari Admin", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_buttonDashboard8ActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        detailDataOutlet();
        jumlahOutlet();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void table_detail_outletMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_detail_outletMouseClicked
        mouseClickOutlet();
    }//GEN-LAST:event_table_detail_outletMouseClicked

    private void comboJenisPaketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboJenisPaketActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboJenisPaketActionPerformed

    private void comboIDOutletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboIDOutletActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboIDOutletActionPerformed

    private void dashboard9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboard9ActionPerformed
        komponenPaket();
        int konfirmasi = JOptionPane.showConfirmDialog(null, "Hapus " + namaPaket + " ?");
        if (konfirmasi == 0) {
            hapusDataPaket();
        }
    }//GEN-LAST:event_dashboard9ActionPerformed

    private void dashboard10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboard10ActionPerformed
        fieldNamaPaket.setText("");
        fieldHargaPaket.setText("");
        detailDataPaket();
    }//GEN-LAST:event_dashboard10ActionPerformed

    private void dashboard11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboard11ActionPerformed
        updateDataPaket();
    }//GEN-LAST:event_dashboard11ActionPerformed

    private void fieldIDPaketKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldIDPaketKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            fieldNamaPaket.requestFocus();
        }
    }//GEN-LAST:event_fieldIDPaketKeyPressed

    private void fieldHargaPaketKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldHargaPaketKeyPressed
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
            fieldHargaPaket.setEditable(true);
        } else if (evt.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
            fieldHargaPaket.setEditable(true);
        } else {

            fieldHargaPaket.setEditable(false);
        }
    }//GEN-LAST:event_fieldHargaPaketKeyPressed

    private void fieldHargaPaketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldHargaPaketActionPerformed

    }//GEN-LAST:event_fieldHargaPaketActionPerformed

    private void fieldHargaPaketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fieldHargaPaketMouseClicked
//        JOptionPane.showMessageDialog(null, "Masukan harga berupa nominal, dan tidak boleh mengandung huruf!", "Pesan dari Admin", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_fieldHargaPaketMouseClicked

    private void fieldIDPaketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fieldIDPaketMouseClicked
        JOptionPane.showMessageDialog(null, "ID Paket Dibuat otomatis dan tidak dapat diedit", "Pesan Dari Admin", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_fieldIDPaketMouseClicked

    private void dashboard12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboard12ActionPerformed
        insertDataPaket();
        autoNumberIDPaket();
    }//GEN-LAST:event_dashboard12ActionPerformed

    private void table_detail_paketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_detail_paketMouseClicked
        mouseClickPaket();
    }//GEN-LAST:event_table_detail_paketMouseClicked

    private void buttonDataTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDataTransaksiActionPerformed
        dataTransaksi();

        Utama.removeAll();
        Utama.repaint();
        Utama.revalidate();

        // add new panel
        Utama.add(DataTransaksi);
        Utama.repaint();
        Utama.revalidate();

    }//GEN-LAST:event_buttonDataTransaksiActionPerformed

    private void dashboard19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboard19ActionPerformed
        Date date = new Date();
        fieldTanggalPesan.setDate(date);

        IDOutlet();
        autoKodeInvoice();

        Namamember();
        autoNumberIDTransaksi();
        Utama.removeAll();
        Utama.repaint();
        Utama.revalidate();

        // add new panel
        Utama.add(Transaksi);
        Utama.repaint();
        Utama.revalidate();
    }//GEN-LAST:event_dashboard19ActionPerformed

    private void dashboard20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboard20ActionPerformed
        dataTransaksi();
    }//GEN-LAST:event_dashboard20ActionPerformed

    private void table_data_transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_data_transaksiMouseClicked
        int baris = table_data_transaksi.getSelectedRow();

        this.IDTransaksi = table_data_transaksi.getModel().getValueAt(baris, 0).toString();

        EditDataTransaksi edit = new EditDataTransaksi();

        try {
            String sql = "select * from tb_transaksi where id_transaksi=?";
            this.pst = koneksi.getKoneksi().prepareStatement(sql);
            this.pst.setString(1, IDTransaksi);
            this.set = pst.executeQuery();
            if (set.next()) {
                this.IDOutlet = set.getString("id_outlet");
                this.IDTransaksi = set.getString("id_transaksi");
                this.IDPelanggan = set.getString("id_member");

                Date tanggalSekarang1 = set.getTimestamp("tanggal");

                Date batasWaktu1 = set.getTimestamp("batas_waktu");
                Date tanggalBayar1 = set.getTimestamp("tanggal_bayar");
                this.kodeInvoice = set.getString("kode_invoice");
                this.biayaAdmin = set.getInt("biaya_tambahan");
                this.diskon = set.getDouble("diskon");
                this.pajak = set.getInt("pajak");
                this.statusPesan = set.getString("status");
                this.statusBayar = set.getString("bayar");
                this.IDPetugas = set.getString("id_user");

                edit.setVisible(true);
                edit.comboIDOutletTransaksi.setSelectedItem(IDOutlet);
                edit.fieldIDTransaksi.setText(IDTransaksi);
                edit.comboIDPelanggan.setSelectedItem(IDPelanggan);
                edit.fieldTanggalPesan.setDate(tanggalSekarang1);
                edit.fieldBatasWaktu.setDate(batasWaktu1);
                edit.fieldTanggalBayar.setDate(tanggalBayar1);
                edit.fieldKodeInvoice.setText(kodeInvoice);
                edit.fieldBiayaAdmin.setText("Rp. " + biayaAdmin);
                edit.fieldDiskon.setText("2%");
                edit.fieldPajak.setText("Rp." + pajak);
                edit.comboStatusPesanan.setSelectedItem(statusPesan);
                edit.comboStatusPembayaran.setSelectedItem(statusBayar);
                edit.comboIDPetugas.setSelectedItem(IDPetugas);

                edit.comboIDPelanggan.setEnabled(false);
                edit.fieldNamaPelanggan.setEnabled(false);
                edit.fieldIDTransaksi.setEnabled(false);
                edit.fieldNamaPelanggan.setEnabled(false);
                edit.fieldTanggalPesan.setEnabled(false);
                edit.fieldBatasWaktu.setEnabled(false);
                edit.fieldTanggalBayar.setEnabled(false);
                edit.fieldKodeInvoice.setEnabled(false);
                edit.fieldBiayaAdmin.setEnabled(false);
                edit.fieldPajak.setEnabled(false);
                edit.fieldDiskon.setEnabled(false);

                //              
            }
        } catch (Exception e) {
        }


    }//GEN-LAST:event_table_data_transaksiMouseClicked

    private void dashboard17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboard17ActionPerformed
        //      komponenTransaksi();
        insertDataTransaksi();
    }//GEN-LAST:event_dashboard17ActionPerformed

    private void dashboard16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboard16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dashboard16ActionPerformed

    private void comboNamaPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboNamaPetugasActionPerformed
        //       namaPetugas();
        String namaPetugas = comboNamaPetugas.getSelectedItem().toString();
        try {
            String sql = "select id_user from tb_user where nama ='" + namaPetugas + "'";
            this.pst = koneksi.getKoneksi().prepareStatement(sql);
            this.set = pst.executeQuery(sql);
            if (set.next()) {
                fieldIDPetugas.setText(set.getString("id_user"));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_comboNamaPetugasActionPerformed

    private void comboStatusPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboStatusPembayaranActionPerformed
      
    }//GEN-LAST:event_comboStatusPembayaranActionPerformed

    private void comboNamaPelangganTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboNamaPelangganTransaksiActionPerformed
        Namamember();
        try {
            String namaPelanggan = comboNamaPelangganTransaksi.getSelectedItem().toString();
            String sql = "select id_member from tb_member where nama ='" + namaPelanggan + "'";
            this.pst = koneksi.getKoneksi().prepareStatement(sql);
            this.set = pst.executeQuery(sql);
            if (set.next()) {
                String idPelanggan = set.getString("id_member");
                fieldIDPelanggan.setText(idPelanggan);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_comboNamaPelangganTransaksiActionPerformed

    private void dashboard21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboard21ActionPerformed
        new DetailTransaksi().setVisible(true);
        System.out.println(detailIDTransaksi);
    }//GEN-LAST:event_dashboard21ActionPerformed

    private void table_detail_transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_detail_transaksiMouseClicked
        int baris = table_detail_transaksi.getSelectedRow();
        String namaPelanggan = table_detail_transaksi.getModel().getValueAt(baris, 0).toString();
        String IDTransaksi = table_detail_transaksi.getModel().getValueAt(baris, 1).toString();
        this.detailIDTransaksi = table_detail_transaksi.getModel().getValueAt(baris, 1).toString();
        String namaPaket = table_detail_transaksi.getModel().getValueAt(baris, 2).toString();
        String hargaPaket = table_detail_transaksi.getModel().getValueAt(baris, 3).toString();
        String jumlahPaket = table_detail_transaksi.getModel().getValueAt(baris, 4).toString();
        String biayaAdmin = table_detail_transaksi.getModel().getValueAt(baris, 5).toString();
        String pajak = table_detail_transaksi.getModel().getValueAt(baris, 6).toString();
        String diskon = table_detail_transaksi.getModel().getValueAt(baris, 7).toString();
        String progesPesanan = table_detail_transaksi.getModel().getValueAt(baris, 8).toString();
        String statusPesanan = table_detail_transaksi.getModel().getValueAt(baris, 9).toString();

        EditDetailTransaksi edit = new EditDetailTransaksi();
        edit.setVisible(true);
        edit.fieldNamaPelanggan.setText(namaPelanggan);
        edit.fieldIDTransaksi.setText(IDTransaksi);
        edit.fieldNamaPaket.setText(namaPaket);
        edit.fieldHargaPaket.setText(hargaPaket);
        edit.fieldJumlah.setText(jumlahPaket);
        edit.fieldBiayaAdmin.setText(biayaAdmin);
        edit.fieldPajak.setText(pajak);
        edit.fieldDiskon.setText(diskon);
        edit.comboProgresPesanan.setSelectedItem(progesPesanan);
        edit.fieldStatusPesanan.setText(statusPesanan);

        edit.fieldNamaPelanggan.setEditable(false);
        edit.fieldIDTransaksi.setEditable(false);
        edit.fieldNamaPaket.setEditable(false);
        edit.fieldHargaPaket.setEditable(false);
        edit.fieldJumlah.setEditable(false);
        edit.fieldBiayaAdmin.setEditable(false);
        edit.fieldPajak.setEditable(false);
        edit.fieldDiskon.setEditable(false);
        edit.fieldStatusPesanan.setEditable(false);


    }//GEN-LAST:event_table_detail_transaksiMouseClicked

    private void dashboard22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboard22ActionPerformed
        cetakLaporanTransaksi();

    }//GEN-LAST:event_dashboard22ActionPerformed

    private void dashboard23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboard23ActionPerformed
        detailDataTransaksi();
    }//GEN-LAST:event_dashboard23ActionPerformed

    private void buttonDashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonDashboardMouseEntered

    }//GEN-LAST:event_buttonDashboardMouseEntered

    private void buttonPelangganMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonPelangganMouseEntered

    }//GEN-LAST:event_buttonPelangganMouseEntered

    private void buttonPelangganMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonPelangganMouseExited

    }//GEN-LAST:event_buttonPelangganMouseExited

    private void buttonPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonPelangganMouseClicked
        buttonPelanggan();
    }//GEN-LAST:event_buttonPelangganMouseClicked

    private void buttonPelangganMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonPelangganMousePressed
        setColor(buttonPelanggan);
        resetColor(buttonDashboard);
        resetColor(buttonPaket);
        resetColor(buttonTransaksi);
        resetColor(buttonDataTransaksi);
        resetColor(buttonDetailTransaksi);
        resetColor(buttonPegawai);
        resetColor(buttonOutlet);

    }//GEN-LAST:event_buttonPelangganMousePressed

    private void buttonDashboardMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonDashboardMousePressed
        setColor(buttonDashboard);
        resetColor(buttonPelanggan);
        resetColor(buttonPaket);
        resetColor(buttonTransaksi);
        resetColor(buttonDataTransaksi);
        resetColor(buttonDetailTransaksi);
        resetColor(buttonPegawai);
        resetColor(buttonOutlet);
    }//GEN-LAST:event_buttonDashboardMousePressed

    private void buttonPaketMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonPaketMousePressed
        setColor(buttonPaket);
        resetColor(buttonDashboard);
        resetColor(buttonPelanggan);
        resetColor(buttonTransaksi);
        resetColor(buttonDataTransaksi);
        resetColor(buttonDetailTransaksi);
        resetColor(buttonPegawai);
        resetColor(buttonOutlet);
    }//GEN-LAST:event_buttonPaketMousePressed

    private void buttonTransaksiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonTransaksiMousePressed
        resetColor(buttonPelanggan);
        resetColor(buttonDashboard);
        resetColor(buttonPaket);
        setColor(buttonTransaksi);
        resetColor(buttonDataTransaksi);
        resetColor(buttonDetailTransaksi);
        resetColor(buttonPegawai);
        resetColor(buttonOutlet);
    }//GEN-LAST:event_buttonTransaksiMousePressed

    private void buttonDataTransaksiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonDataTransaksiMousePressed
        resetColor(buttonPelanggan);
        resetColor(buttonDashboard);
        resetColor(buttonPaket);
        resetColor(buttonTransaksi);
        setColor(buttonDataTransaksi);
        resetColor(buttonDetailTransaksi);
        resetColor(buttonPegawai);
        resetColor(buttonOutlet);
    }//GEN-LAST:event_buttonDataTransaksiMousePressed

    private void buttonDetailTransaksiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonDetailTransaksiMousePressed
        resetColor(buttonPelanggan);
        resetColor(buttonDashboard);
        resetColor(buttonPaket);
        resetColor(buttonTransaksi);
        resetColor(buttonDataTransaksi);
        setColor(buttonDetailTransaksi);
        resetColor(buttonPegawai);
        resetColor(buttonOutlet);
    }//GEN-LAST:event_buttonDetailTransaksiMousePressed

    private void buttonPegawaiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonPegawaiMousePressed
        resetColor(buttonPelanggan);
        resetColor(buttonDashboard);
        resetColor(buttonPaket);
        resetColor(buttonTransaksi);
        resetColor(buttonDataTransaksi);
        resetColor(buttonDetailTransaksi);
        setColor(buttonPegawai);
        resetColor(buttonOutlet);
    }//GEN-LAST:event_buttonPegawaiMousePressed

    private void buttonOutletMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonOutletMousePressed
        resetColor(buttonPelanggan);
        resetColor(buttonDashboard);
        resetColor(buttonPaket);
        resetColor(buttonTransaksi);
        resetColor(buttonDataTransaksi);
        resetColor(buttonDetailTransaksi);
        resetColor(buttonPegawai);
        setColor(buttonOutlet);
    }//GEN-LAST:event_buttonOutletMousePressed

    private void fieldNamaPaketKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldNamaPaketKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            fieldHargaPaket.requestFocus();
        }
    }//GEN-LAST:event_fieldNamaPaketKeyPressed

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
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Barang;
    private javax.swing.JPanel Dashboard;
    private javax.swing.JLabel Dashboard1;
    private javax.swing.JPanel DataTransaksi;
    private javax.swing.JLabel DescDataPaket;
    private javax.swing.JLabel DescDataPelanggan1;
    private javax.swing.JLabel DescDataPelanggan2;
    public static javax.swing.JPanel DetailDataTransaksi;
    private javax.swing.JLabel JumlahDataPaket;
    private javax.swing.JLabel JumlahDataPelanggan;
    private javax.swing.JLabel JumlahOutlet;
    private javax.swing.JLabel LabelSelamatDatang;
    private javax.swing.JLabel Labeltanggal1;
    private javax.swing.JLabel Logout;
    private javax.swing.JPanel Outlet;
    private javax.swing.JPanel Side;
    private javax.swing.JPanel Transaksi;
    public static javax.swing.JPanel Utama;
    private javax.swing.JLabel background;
    private javax.swing.JLabel backgroundOutlet;
    private com.k33ptoo.components.KButton buttonDashboard;
    private com.k33ptoo.components.KButton buttonDashboard2;
    private com.k33ptoo.components.KButton buttonDashboard4;
    private com.k33ptoo.components.KButton buttonDashboard5;
    private com.k33ptoo.components.KButton buttonDashboard6;
    private com.k33ptoo.components.KButton buttonDashboard7;
    private com.k33ptoo.components.KButton buttonDashboard8;
    private com.k33ptoo.components.KButton buttonDataTransaksi;
    private com.k33ptoo.components.KButton buttonDetailTransaksi;
    private com.k33ptoo.components.KButton buttonOutlet;
    private com.k33ptoo.components.KButton buttonPaket;
    private com.k33ptoo.components.KButton buttonPegawai;
    private com.k33ptoo.components.KButton buttonPelanggan;
    private com.k33ptoo.components.KButton buttonTransaksi;
    private combobox.Combobox comboIDOutlet;
    private combobox.Combobox comboIDOutletTransaksi;
    private combobox.Combobox comboJenisPaket;
    private combobox.Combobox comboNamaPelangganTransaksi;
    private combobox.Combobox comboNamaPetugas;
    private combobox.Combobox comboStatusPembayaran;
    private combobox.Combobox comboStatusPesanan;
    private com.k33ptoo.components.KButton dashboard10;
    private com.k33ptoo.components.KButton dashboard11;
    private com.k33ptoo.components.KButton dashboard12;
    private com.k33ptoo.components.KButton dashboard16;
    private com.k33ptoo.components.KButton dashboard17;
    private com.k33ptoo.components.KButton dashboard19;
    private com.k33ptoo.components.KButton dashboard20;
    private com.k33ptoo.components.KButton dashboard21;
    private com.k33ptoo.components.KButton dashboard22;
    private com.k33ptoo.components.KButton dashboard23;
    private com.k33ptoo.components.KButton dashboard5;
    private com.k33ptoo.components.KButton dashboard7;
    private com.k33ptoo.components.KButton dashboard8;
    private com.k33ptoo.components.KButton dashboard9;
    private javax.swing.JLabel descOutlet;
    private com.toedter.calendar.JDateChooser fieldBatasWaktu;
    private textfield.TextField fieldBiayaAdmin;
    private textfield.TextField fieldDiskon;
    private textfield.TextField fieldHargaPaket;
    private textfield.TextField fieldIDPaket;
    private textfield.TextField fieldIDPelanggan;
    private textfield.TextField fieldIDPetugas;
    private textfield.TextField fieldIDTransaksi;
    private textfield.TextField fieldKodeInvoice;
    private textfield.TextField fieldNamaPaket;
    private textfield.TextField fieldPajak;
    private Component.TextField fieldSearch;
    private Component.TextField fieldSearchPegawai;
    private com.toedter.calendar.JDateChooser fieldTanggalBayar;
    private com.toedter.calendar.JDateChooser fieldTanggalPesan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jumlahDataPegawai;
    private javax.swing.JLabel labelMenu;
    public static javax.swing.JLabel labelPetugas;
    private javax.swing.JLabel labelUpdate;
    private test.PanelRound panelRound1;
    private javax.swing.JPanel pegawai;
    private javax.swing.JPanel pelanggan;
    private javax.swing.JScrollPane scrollDataTransaksi;
    private javax.swing.JScrollPane scrollPanePaket;
    private javax.swing.JScrollPane scrollPanelDetailTransaksi;
    private dynamic_subjtable.TableCellEditor tableCellEditor1;
    private dynamic_subjtable.TableCustom tableCustom1;
    private javax.swing.JScrollPane tableOutletScroll;
    private javax.swing.JScrollPane tablePegawaiScroll;
    private javax.swing.JScrollPane tableScroll;
    private dynamic_subjtable.TableCustom table_data_transaksi;
    private dynamic_subjtable.TableCustom table_detail_outlet;
    private dynamic_subjtable.TableCustom table_detail_paket;
    private dynamic_subjtable.TableCustom table_detail_pegawai;
    private dynamic_subjtable.TableCustom table_detail_pelanggan;
    private dynamic_subjtable.TableCustom table_detail_transaksi;
    private javax.swing.JLabel tambahOutlet1;
    // End of variables declaration//GEN-END:variables




}
