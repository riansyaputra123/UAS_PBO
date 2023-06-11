/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pkg2109106078_rian.syaputra.ainun.naim_tugas_pbo;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableModel;

/**
 *
 * @author Lenovo-Gk
 */
public class Admin extends javax.swing.JFrame {
    private static boolean cek = false;
    
    private void tabel_cari(){
        String keyword = txtCari.getText();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No.");
        model.addColumn("ID");
        model.addColumn("Nama Sepatu");
        model.addColumn("Merk Sepatu.");
        model.addColumn("Ukuran Sepatu");
        model.addColumn("Tipe Sepatu");
        model.addColumn("Warna Sepatu");
        model.addColumn("Cocok Untuk");
        model.addColumn("Harga");
        if (keyword.isEmpty()){
        try {
            int no = 1;
            String query = "SELECT * FROM tb_sepatu";
            Connection conn = null;
            try {
                conn = koneksi.getKoneksi();
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            java.sql.Statement stm = conn.createStatement();
            ResultSet resultset = stm.executeQuery(query);
            while (resultset.next()) {
                model.addRow (new Object[] {no++,resultset.getString(1), resultset.getString(2), resultset.getString(3), resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getString(7), resultset.getString(8)});
                
                JdataSepatu.setModel(model);
                
            }
        } catch (SQLException e){
            System.out.println("ERROR : " +e.getMessage());
        }
        } else {
            try{
            int no = 1;
            String query = "SELECT * FROM tb_sepatu WHERE nama_sepatu LIKE '%" + keyword + "%' or merk_sepatu LIKE '%" + keyword + "%' or harga LIKE '%" + keyword + "%' or tipe_sepatu LIKE '%" + keyword + "%' or ukuran_sepatu LIKE '%" + keyword + "%'";
            Connection conn = null;
            try {
                conn = koneksi.getKoneksi();
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            java.sql.Statement stm = conn.createStatement();
            ResultSet resultset = stm.executeQuery(query);
            while (resultset.next()) {
                model.addRow (new Object[] {no++,resultset.getString(1), resultset.getString(2), resultset.getString(3), resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getString(7), resultset.getString(8)});
                
                JdataSepatu.setModel(model);
                
            }
            conn.close();
            txtCari.setText("");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "There is an error : " + e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
        }
    }
    
    public void hapus() {
        int baris = JdataSepatu.getSelectedRow();
        Connection conn = null;
        try {
            conn = koneksi.getKoneksi();
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        int opsi = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menyimpan menghapus data tersebut ???", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (opsi == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM tb_sepatu WHERE id=?";
            try {
                PreparedStatement statement4 = conn.prepareStatement(sql);
                statement4.setString(1, JdataSepatu.getValueAt(baris, 1).toString());                
                statement4.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus !!! ", "SQL Information", JOptionPane.INFORMATION_MESSAGE);
                tampil_table();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "There is an error : " + ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }
        }  else {
            JOptionPane.showMessageDialog(this, "Deleted data Canceled!!! ", "SQL Information", JOptionPane.INFORMATION_MESSAGE);
            tampil_table();
        }
    }
    
    public void ubah (boolean test){
        enable();
        if (test) {
        try {
            Connection conn = koneksi.getKoneksi();
            String sql = "INSERT INTO tb_sepatu (nama_sepatu, merk_sepatu, ukuran_sepatu, tipe_sepatu, warna_sepatu, cocok_untuk, harga) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement3 = conn.prepareStatement(sql);
            statement3.setString(1, txtNama.getText());
            statement3.setString(2, boxMerk.getSelectedItem().toString());
            if (U37.isSelected()){
                String size = "37";
                statement3.setString(3, size);
            } else if (U38.isSelected()){
                String size = "38";
                statement3.setString(3, size);
            }else if (U39.isSelected()){
                String size = "39";
                statement3.setString(3, size);
            }else if (U40.isSelected()){
                String size = "40";
                statement3.setString(3, size);
            }else if (U41.isSelected()){
                String size = "41";
                statement3.setString(3, size);
            }else if (U42.isSelected()){
                String size = "42";
                statement3.setString(3, size);
            }else if (U43.isSelected()){
                String size = "43";
                statement3.setString(3, size);
            }else if (U44.isSelected()){
                String size = "44";
                statement3.setString(3, size);
            }
            String fit = "";
            if (CRunning.isSelected()){
                fit += "Running ";
            }
            if (CHiking.isSelected()){
                fit += "Hiking ";
            }
            if (CSantai.isSelected()){
                fit += "Santai ";
            }
            if (CBasket.isSelected()){
                fit += "Basket ";
            }
            statement3.setString(6, fit);
            statement3.setString(4, boxTipe.getSelectedItem().toString());
            statement3.setString(5, txtNama.getText());
            //statement2.setString(6, fit);
            statement3.setInt(7, Integer.parseInt(txtHarga.getText()));
            int masuk = statement3.executeUpdate();
            if (masuk > 0 ){
                JOptionPane.showMessageDialog(null, "Berhasil menambahkan data");
                tampil_table();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal menambahkan data!!!" + ex.getMessage());
        }
        } 
        else {
            try {
            int baris = JdataSepatu.getSelectedRow();
            Connection conn = koneksi.getKoneksi();
            String sql = "UPDATE tb_sepatu SET nama_sepatu= ?, merk_sepatu= ?, ukuran_sepatu= ?, tipe_sepatu= ?, warna_sepatu= ?, cocok_untuk= ?, harga= ? where id= ?";
            PreparedStatement statement3 = conn.prepareStatement(sql);
            statement3.setString(1, txtNama.getText());
            statement3.setString(2, boxMerk.getSelectedItem().toString());
            if (U37.isSelected()){
                String size = "37";
                statement3.setString(3, size);
            } else if (U38.isSelected()){
                String size = "38";
                statement3.setString(3, size);
            }else if (U39.isSelected()){
                String size = "39";
                statement3.setString(3, size);
            }else if (U40.isSelected()){
                String size = "40";
                statement3.setString(3, size);
            }else if (U41.isSelected()){
                String size = "41";
                statement3.setString(3, size);
            }else if (U42.isSelected()){
                String size = "42";
                statement3.setString(3, size);
            }else if (U43.isSelected()){
                String size = "43";
                statement3.setString(3, size);
            }else if (U44.isSelected()){
                String size = "44";
                statement3.setString(3, size);
            }
            String fit = "";
            if (CRunning.isSelected()){
                fit += "Running ";
            }
            if (CHiking.isSelected()){
                fit += "Hiking ";
            }
            if (CSantai.isSelected()){
                fit += "Santai ";
            }
            if (CBasket.isSelected()){
                fit += "Basket ";
            }
            statement3.setString(6, fit);
            statement3.setString(4, boxTipe.getSelectedItem().toString());
            statement3.setString(5, txtNama.getText());
            statement3.setString(8, JdataSepatu.getValueAt(baris, 1).toString());
            //statement2.setString(6, fit);
            statement3.setInt(7, Integer.parseInt(txtHarga.getText()));
            statement3.executeUpdate();
            JOptionPane.showMessageDialog(null, "Berhasil Update data");
            tampil_table();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal Update data!!!" + ex.getMessage());
        }
        }
              
    }
    
    public void disable(){
        U37.setEnabled(false);
        U38.setEnabled(false);
        U39.setEnabled(false);
        U40.setEnabled(false);
        U41.setEnabled(false);
        U42.setEnabled(false);
        U43.setEnabled(false);
        U44.setEnabled(false);
        CBasket.setEnabled(false);
        CHiking.setEnabled(false);
        CRunning.setEnabled(false);
        CSantai.setEnabled(false);
        txtNama.setEnabled(false);
        txtHarga.setEnabled(false);
        txtWarna.setEnabled(false);
        boxMerk.setEnabled(true);
        boxTipe.setEnabled(true);
    }
    
    public void enable(){
        U37.setEnabled(true);
        U38.setEnabled(true);
        U39.setEnabled(true);
        U40.setEnabled(true);
        U41.setEnabled(true);
        U42.setEnabled(true);
        U43.setEnabled(true);
        U44.setEnabled(true);
        CBasket.setEnabled(true);
        CHiking.setEnabled(true);
        CRunning.setEnabled(true);
        CSantai.setEnabled(true);
        txtNama.setEnabled(true);
        txtHarga.setEnabled(true);
        txtWarna.setEnabled(true);
        boxMerk.setEnabled(true);
        boxTipe.setEnabled(true);
        
    }
    
    public void bersih(){
        U37.setSelected(false);
        U38.setSelected(false);
        U39.setSelected(false);
        U40.setSelected(false);
        U41.setSelected(false);
        U42.setSelected(false);
        U43.setSelected(false);
        U44.setSelected(false);
        CBasket.setSelected(false);
        CHiking.setSelected(false);
        CRunning.setSelected(false);
        CSantai.setSelected(false);
        txtNama.setText("");
        txtHarga.setText("");
        txtWarna.setText("");
        boxMerk.setSelectedItem("Adidas");
        boxTipe.setSelectedItem("LOW");  
    }
    
  
  
    public void livedata(){
        int i = JdataSepatu.getSelectedRow();
        TableModel tm = JdataSepatu.getModel();
        String nama = tm.getValueAt(i, 2).toString();
        String harga = tm.getValueAt(i, 8).toString();
        String warna = tm.getValueAt(i, 6).toString();
        Object merk = tm.getValueAt(i, 3).toString();
        Object Uk = tm.getValueAt(i, 4).toString();
        Object fit = tm.getValueAt(i, 7).toString();
        Object tipe = tm.getValueAt(i, 5).toString();
        txtNama.setText(nama);
        txtHarga.setText(harga);
        txtWarna.setText(warna);
        boxMerk.setSelectedItem(merk);
        boxTipe.setSelectedItem(tipe);
        if (Uk.equals("37")) {
                    U37.setSelected(true);
                    U38.setSelected(false);
                    U39.setSelected(false);
                    U40.setSelected(false);
                    U41.setSelected(false);
                    U42.setSelected(false);
                    U43.setSelected(false);
                    U44.setSelected(false);
        } else if (Uk.equals("38")) {
                    U37.setSelected(false);
                    U38.setSelected(true);
                    U39.setSelected(false);
                    U40.setSelected(false);
                    U41.setSelected(false);
                    U42.setSelected(false);
                    U43.setSelected(false);
                    U44.setSelected(false);
        }else if (Uk.equals("39")) {
                    U37.setSelected(false);
                    U38.setSelected(false);
                    U39.setSelected(true);
                    U40.setSelected(false);
                    U41.setSelected(false);
                    U42.setSelected(false);
                    U43.setSelected(false);
                    U44.setSelected(false);
        }else if (Uk.equals("40")) {
                    U37.setSelected(false);
                    U38.setSelected(false);
                    U39.setSelected(false);
                    U40.setSelected(true);
                    U41.setSelected(false);
                    U42.setSelected(false);
                    U43.setSelected(false);
                    U44.setSelected(false);
        }else if (Uk.equals("41")) {
                    U37.setSelected(false);
                    U38.setSelected(false);
                    U39.setSelected(false);
                    U40.setSelected(false);
                    U41.setSelected(true);
                    U42.setSelected(false);
                    U43.setSelected(false);
                    U44.setSelected(false);
        }else if (Uk.equals("42")) {
                    U37.setSelected(false);
                    U38.setSelected(false);
                    U39.setSelected(false);
                    U40.setSelected(false);
                    U41.setSelected(false);
                    U42.setSelected(true);
                    U43.setSelected(false);
                    U44.setSelected(false);
        }else if (Uk.equals("43")) {
                    U37.setSelected(false);
                    U38.setSelected(false);
                    U39.setSelected(false);
                    U40.setSelected(false);
                    U41.setSelected(false);
                    U42.setSelected(false);
                    U43.setSelected(true);
                    U44.setSelected(false);
        }else if (Uk.equals("44")) {
                    U37.setSelected(false);
                    U38.setSelected(false);
                    U39.setSelected(false);
                    U40.setSelected(false);
                    U41.setSelected(false);
                    U42.setSelected(false);
                    U43.setSelected(false);
                    U44.setSelected(true);
        }
        
        if (fit.equals("Santai")) {
            CSantai.setSelected(true);
        } else {
            CSantai.setSelected(false);
        }
        if (fit.equals("Running")) {
            CRunning.setSelected(true);
        } else {
            CRunning.setSelected(false);
        }
        if (fit.equals("Basket")) {
            CBasket.setSelected(true);
        } else {
            CBasket.setSelected(false);
        }
        if (fit.equals("Hiking")) {
            CHiking.setSelected(true);
        } else {
            CHiking.setSelected(false);
        }
    }
    public void tampil_table (){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No.");
        model.addColumn("ID");
        model.addColumn("Nama Sepatu");
        model.addColumn("Merk Sepatu.");
        model.addColumn("Ukuran Sepatu");
        model.addColumn("Tipe Sepatu");
        model.addColumn("Warna Sepatu");
        model.addColumn("Cocok Untuk");
        model.addColumn("Harga");
        
        try {
            int no = 1;
            String query = "SELECT * FROM tb_sepatu";
            Connection conn = null;
            try {
                conn = koneksi.getKoneksi();
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            java.sql.Statement stm = conn.createStatement();
            ResultSet resultset = stm.executeQuery(query);
            while (resultset.next()) {
                model.addRow (new Object[] {no++,resultset.getString(1), resultset.getString(2), resultset.getString(3), resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getString(7), resultset.getString(8)});
                
                JdataSepatu.setModel(model);
                
            }
        } catch (SQLException e){
            System.out.println("ERROR : " +e.getMessage());
        }
    }

    /**
     * Creates new form Admin
     */
    public Admin() {
        initComponents();
        tampil_table();
        disable();
        btnDel.setVisible(false);
        btnSave.setVisible(false);
        btnBatal.setVisible(false);
        btnUpdate.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JdataSepatu = new javax.swing.JTable();
        txtNama = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        boxMerk = new javax.swing.JComboBox<>();
        U38 = new javax.swing.JRadioButton();
        U37 = new javax.swing.JRadioButton();
        U40 = new javax.swing.JRadioButton();
        U41 = new javax.swing.JRadioButton();
        U42 = new javax.swing.JRadioButton();
        U44 = new javax.swing.JRadioButton();
        U43 = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        U39 = new javax.swing.JRadioButton();
        txtWarna = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        boxTipe = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        CSantai = new javax.swing.JCheckBox();
        CRunning = new javax.swing.JCheckBox();
        CBasket = new javax.swing.JCheckBox();
        CHiking = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        txtWarn = new javax.swing.JLabel();
        btnBatal = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        btnSave1 = new javax.swing.JButton();
        btnOut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(45, 46, 55));
        setPreferredSize(new java.awt.Dimension(840, 700));

        jPanel1.setBackground(new java.awt.Color(100, 92, 170));
        jPanel1.setMinimumSize(new java.awt.Dimension(360, 40));
        jPanel1.setPreferredSize(new java.awt.Dimension(770, 44));

        jLabel9.setFont(new java.awt.Font("Futura Bk BT", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("HOME");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(306, 306, 306)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(45, 46, 55));

        JdataSepatu.setFont(new java.awt.Font("JetBrains Mono NL Light", 0, 12)); // NOI18N
        JdataSepatu.setModel(new javax.swing.table.DefaultTableModel(
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
        JdataSepatu.setGridColor(new java.awt.Color(0, 0, 0));
        JdataSepatu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JdataSepatuMouseClicked(evt);
            }
        });
        JdataSepatu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JdataSepatuKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JdataSepatuKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(JdataSepatu);

        txtNama.setFont(new java.awt.Font("JetBrains Mono SemiBold", 0, 14)); // NOI18N
        txtNama.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("JetBrains Mono SemiBold", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(84, 186, 185));
        jLabel1.setText("Merk Sepatu");

        jLabel2.setFont(new java.awt.Font("JetBrains Mono SemiBold", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(84, 186, 185));
        jLabel2.setText("Nama Sepatu");

        boxMerk.setFont(new java.awt.Font("JetBrains Mono SemiBold", 1, 14)); // NOI18N
        boxMerk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Adidas", "Reebok", "League", "Specs", "Airwalk", "New Balance", "Nike" }));

        U38.setBackground(new java.awt.Color(45, 46, 55));
        buttonGroup1.add(U38);
        U38.setFont(new java.awt.Font("JetBrains Mono SemiBold", 1, 14)); // NOI18N
        U38.setForeground(new java.awt.Color(255, 171, 225));
        U38.setText("38");
        U38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                U38ActionPerformed(evt);
            }
        });

        U37.setBackground(new java.awt.Color(45, 46, 55));
        buttonGroup1.add(U37);
        U37.setFont(new java.awt.Font("JetBrains Mono SemiBold", 1, 14)); // NOI18N
        U37.setForeground(new java.awt.Color(255, 171, 225));
        U37.setText("37");

        U40.setBackground(new java.awt.Color(45, 46, 55));
        buttonGroup1.add(U40);
        U40.setFont(new java.awt.Font("JetBrains Mono SemiBold", 1, 14)); // NOI18N
        U40.setForeground(new java.awt.Color(255, 171, 225));
        U40.setText("40");

        U41.setBackground(new java.awt.Color(45, 46, 55));
        buttonGroup1.add(U41);
        U41.setFont(new java.awt.Font("JetBrains Mono SemiBold", 1, 14)); // NOI18N
        U41.setForeground(new java.awt.Color(255, 171, 225));
        U41.setText("41");

        U42.setBackground(new java.awt.Color(45, 46, 55));
        buttonGroup1.add(U42);
        U42.setFont(new java.awt.Font("JetBrains Mono SemiBold", 1, 14)); // NOI18N
        U42.setForeground(new java.awt.Color(255, 171, 225));
        U42.setText("42");

        U44.setBackground(new java.awt.Color(45, 46, 55));
        buttonGroup1.add(U44);
        U44.setFont(new java.awt.Font("JetBrains Mono SemiBold", 1, 14)); // NOI18N
        U44.setForeground(new java.awt.Color(255, 171, 225));
        U44.setText("44");

        U43.setBackground(new java.awt.Color(45, 46, 55));
        buttonGroup1.add(U43);
        U43.setFont(new java.awt.Font("JetBrains Mono SemiBold", 1, 14)); // NOI18N
        U43.setForeground(new java.awt.Color(255, 171, 225));
        U43.setText("43");

        jLabel8.setFont(new java.awt.Font("Futura Bk BT", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(84, 186, 185));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("DATA TOKO SEPATU ABADI");

        U39.setBackground(new java.awt.Color(45, 46, 55));
        buttonGroup1.add(U39);
        U39.setFont(new java.awt.Font("JetBrains Mono SemiBold", 1, 14)); // NOI18N
        U39.setForeground(new java.awt.Color(255, 171, 225));
        U39.setText("39");
        U39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                U39ActionPerformed(evt);
            }
        });

        txtWarna.setFont(new java.awt.Font("JetBrains Mono SemiBold", 0, 14)); // NOI18N
        txtWarna.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel3.setFont(new java.awt.Font("JetBrains Mono SemiBold", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(84, 186, 185));
        jLabel3.setText("Warna Sepatu");

        txtHarga.setFont(new java.awt.Font("JetBrains Mono SemiBold", 0, 14)); // NOI18N
        txtHarga.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel4.setFont(new java.awt.Font("JetBrains Mono SemiBold", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(84, 186, 185));
        jLabel4.setText("Harga Sepatu");

        boxTipe.setFont(new java.awt.Font("JetBrains Mono SemiBold", 1, 14)); // NOI18N
        boxTipe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOW", "HIGH" }));

        jLabel5.setFont(new java.awt.Font("JetBrains Mono SemiBold", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(84, 186, 185));
        jLabel5.setText("Tipe Sepatu");

        CSantai.setBackground(new java.awt.Color(45, 46, 55));
        CSantai.setFont(new java.awt.Font("JetBrains Mono SemiBold", 0, 14)); // NOI18N
        CSantai.setForeground(new java.awt.Color(255, 171, 225));
        CSantai.setText("Santai");

        CRunning.setBackground(new java.awt.Color(45, 46, 55));
        CRunning.setFont(new java.awt.Font("JetBrains Mono SemiBold", 0, 14)); // NOI18N
        CRunning.setForeground(new java.awt.Color(255, 171, 225));
        CRunning.setText("Running");

        CBasket.setBackground(new java.awt.Color(45, 46, 55));
        CBasket.setFont(new java.awt.Font("JetBrains Mono SemiBold", 0, 14)); // NOI18N
        CBasket.setForeground(new java.awt.Color(255, 171, 225));
        CBasket.setText("Basket");

        CHiking.setBackground(new java.awt.Color(45, 46, 55));
        CHiking.setFont(new java.awt.Font("JetBrains Mono SemiBold", 0, 14)); // NOI18N
        CHiking.setForeground(new java.awt.Color(255, 171, 225));
        CHiking.setText("Hiking");

        jLabel6.setFont(new java.awt.Font("JetBrains Mono SemiBold", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(84, 186, 185));
        jLabel6.setText("Ukuran Sepatu:");

        jLabel7.setFont(new java.awt.Font("JetBrains Mono SemiBold", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(84, 186, 185));
        jLabel7.setText("Cocok Untuk:");

        btnSave.setBackground(new java.awt.Color(165, 215, 232));
        btnSave.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 14)); // NOI18N
        btnSave.setForeground(new java.awt.Color(45, 46, 55));
        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnNew.setBackground(new java.awt.Color(165, 215, 232));
        btnNew.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 14)); // NOI18N
        btnNew.setForeground(new java.awt.Color(45, 46, 55));
        btnNew.setText("NEW");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnDel.setBackground(new java.awt.Color(165, 215, 232));
        btnDel.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 14)); // NOI18N
        btnDel.setForeground(new java.awt.Color(45, 46, 55));
        btnDel.setText("DELETE");
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(165, 215, 232));
        btnUpdate.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(45, 46, 55));
        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        txtWarn.setFont(new java.awt.Font("JetBrains Mono SemiBold", 1, 14)); // NOI18N
        txtWarn.setForeground(new java.awt.Color(84, 186, 185));

        btnBatal.setBackground(new java.awt.Color(165, 215, 232));
        btnBatal.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 14)); // NOI18N
        btnBatal.setForeground(new java.awt.Color(45, 46, 55));
        btnBatal.setText("BATAL");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        txtCari.setFont(new java.awt.Font("JetBrains Mono SemiBold", 0, 14)); // NOI18N
        txtCari.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        btnSave1.setBackground(new java.awt.Color(165, 215, 232));
        btnSave1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 14)); // NOI18N
        btnSave1.setForeground(new java.awt.Color(45, 46, 55));
        btnSave1.setText("CARI");
        btnSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave1ActionPerformed(evt);
            }
        });

        btnOut.setBackground(new java.awt.Color(255, 0, 51));
        btnOut.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 11)); // NOI18N
        btnOut.setForeground(new java.awt.Color(45, 46, 55));
        btnOut.setText("LOGOUT");
        btnOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtNama)
                                        .addComponent(boxMerk, 0, 323, Short.MAX_VALUE))
                                    .addComponent(jLabel6))
                                .addGap(117, 117, 117)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel3)
                                        .addComponent(txtWarna)
                                        .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4)
                                        .addComponent(boxTipe, 0, 318, Short.MAX_VALUE))
                                    .addComponent(jLabel5)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(U41, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(U37, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(U38, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(U39, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(U42, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(U43, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(U40, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(U44, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(347, 347, 347))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(btnOut, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtWarn))
                        .addGap(255, 255, 255))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CSantai, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CRunning, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CBasket, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CHiking, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boxMerk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtWarna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boxTipe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(U38)
                            .addComponent(U37)
                            .addComponent(U40)
                            .addComponent(U39))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(U41)
                            .addComponent(U42)
                            .addComponent(U44)
                            .addComponent(U43))))
                .addGap(13, 13, 13)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CSantai)
                    .addComponent(CRunning)
                    .addComponent(CHiking)
                    .addComponent(CBasket))
                .addGap(24, 24, 24)
                .addComponent(txtWarn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNew)
                        .addComponent(btnDel)
                        .addComponent(btnUpdate)
                        .addComponent(btnSave))
                    .addComponent(btnBatal, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(btnOut, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JdataSepatuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JdataSepatuMouseClicked
        // TODO add your handling code here:
        btnDel.setVisible(true);
        btnUpdate.setVisible(true);
        livedata();
    }//GEN-LAST:event_JdataSepatuMouseClicked

    private void JdataSepatuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JdataSepatuKeyPressed
     
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            livedata();
            btnDel.setVisible(true);
            btnUpdate.setVisible(true);
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            livedata();
            btnDel.setVisible(true);
            btnUpdate.setVisible(true);
        }
    }//GEN-LAST:event_JdataSepatuKeyPressed

    private void JdataSepatuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JdataSepatuKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            livedata();
            btnDel.setVisible(true);
            btnUpdate.setVisible(true);
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            livedata();
            btnDel.setVisible(true);
            btnUpdate.setVisible(true);
        }
    }//GEN-LAST:event_JdataSepatuKeyReleased

    private void U38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_U38ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_U38ActionPerformed

    private void U39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_U39ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_U39ActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        cek = true;
        btnUpdate.setVisible(false);
        btnNew.setVisible(false);
        btnDel.setVisible(false);
        btnSave.setVisible(true);
        btnBatal.setVisible(true);
        bersih();
        enable();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        ubah(cek);
        btnUpdate.setVisible(false);
        btnNew.setVisible(true);
        btnDel.setVisible(false);
        btnSave.setVisible(false);
        btnBatal.setVisible(false);
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        // TODO add your handling code here:
        hapus();
    }//GEN-LAST:event_btnDelActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        btnUpdate.setVisible(false);
        btnNew.setVisible(true);
        btnDel.setVisible(false);
        btnSave.setVisible(false);
        btnBatal.setVisible(false);
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        enable();
        cek = false;
        btnUpdate.setVisible(false);
        btnNew.setVisible(false);
        btnDel.setVisible(false);
        btnSave.setVisible(true);
        btnBatal.setVisible(true);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave1ActionPerformed
        // TODO add your handling code here:
      tabel_cari();
    }//GEN-LAST:event_btnSave1ActionPerformed

    private void btnOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOutActionPerformed
        // TODO add your handling code here:
        Login lg = null;
        try {
            lg = new Login();
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
                 lg.setVisible(true);
                 lg.pack();
                 lg.setLocationRelativeTo(null);
                 this.setVisible(false);
                 lg.setDefaultCloseOperation(Login.EXIT_ON_CLOSE);
    }//GEN-LAST:event_btnOutActionPerformed

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
    private javax.swing.JCheckBox CBasket;
    private javax.swing.JCheckBox CHiking;
    private javax.swing.JCheckBox CRunning;
    private javax.swing.JCheckBox CSantai;
    private javax.swing.JTable JdataSepatu;
    private javax.swing.JRadioButton U37;
    private javax.swing.JRadioButton U38;
    private javax.swing.JRadioButton U39;
    private javax.swing.JRadioButton U40;
    private javax.swing.JRadioButton U41;
    private javax.swing.JRadioButton U42;
    private javax.swing.JRadioButton U43;
    private javax.swing.JRadioButton U44;
    private javax.swing.JComboBox<String> boxMerk;
    private javax.swing.JComboBox<String> boxTipe;
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnOut;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSave1;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtNama;
    private javax.swing.JLabel txtWarn;
    private javax.swing.JTextField txtWarna;
    // End of variables declaration//GEN-END:variables
}
