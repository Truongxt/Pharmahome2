/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import dao.DanhSachPhieuKetToan_DAO;
import entity.KetToan;
import entity.NhanVien;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;
import utilities.AcountingVoucherPrinter;

/**
 *
 * @author HÀ NHƯ
 */
public class DanhSachPhieuKetToan_GUI extends javax.swing.JPanel {

    private DefaultTableModel model;
    private DanhSachPhieuKetToan_DAO danhSachPhieuKetToan_DAO = new DanhSachPhieuKetToan_DAO();

    /**
     * Creates new form DanhSachPhieuKetToan
     */
    public DanhSachPhieuKetToan_GUI() {
        initTableModel();
        initComponents();
        alterTable();
        renderCustomerTable(danhSachPhieuKetToan_DAO.getAll());
    }

    public void alterTable() {
        DefaultTableCellRenderer rightAlign = new DefaultTableCellRenderer();
        rightAlign.setHorizontalAlignment(JLabel.RIGHT);
        tbl_danhSachPhieuKetToan.getColumnModel().getColumn(5).setCellRenderer(rightAlign);
        tbl_danhSachPhieuKetToan.getColumnModel().getColumn(6).setCellRenderer(rightAlign);
        tbl_danhSachPhieuKetToan.getColumnModel().getColumn(7).setCellRenderer(rightAlign);
        tbl_danhSachPhieuKetToan.getColumnModel().getColumn(8).setCellRenderer(rightAlign);
    }

    public void renderCustomerTable(ArrayList<KetToan> list) {
        model.setRowCount(0);
        for (KetToan ketToan : list) {
            String id = ketToan.getMaKetToan();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            NhanVien nv1 = ketToan.getBangKiemTien().getListChiTietBangKiemTien().get(0).getNhanVien();
            NhanVien nv2 = ketToan.getBangKiemTien().getListChiTietBangKiemTien().get(1).getNhanVien();
            Date ngayBatDau = ketToan.getNgayBatDau();
            Date ngayKetThuc = ketToan.getNgayKetThuc();
            Object[] row = new Object[]{id, nv1.getMaNhanVien(), nv2.getMaNhanVien(), formatter.format(ngayBatDau), formatter.format(ngayKetThuc), utilities.FormatNumber.toVND(ketToan.getDoanhThu()), utilities.FormatNumber.toVND(ketToan.getBangKiemTien().getTongTien()), utilities.FormatNumber.toVND(ketToan.getAtm()), utilities.FormatNumber.toVND(ketToan.getChenhLech())};
            model.addRow(row);
        }
    }

    public void initTableModel() {
        model = new DefaultTableModel(new String[]{"Mã", "Người kiểm", "Đồng kiểm", "Bắt đầu", "Kết thúc", "Doanh thu", "Tiền mặt", "Thanh toán qua ATM", "Chênh lệch"
        }, 0);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_danhSachPhieuKetToan = new javax.swing.JTable();

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nhân viên đồng kiểm:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 57, 142, 30));

        jLabel2.setText("Nhân viên kiểm:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 15, 100, 30));

        jTextField1.setText("jTextField1");
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 190, 30));

        jTextField2.setText("jTextField1");
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 190, 30));

        jLabel3.setText("Đến ngày:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 150, 30));

        jLabel4.setText("Từ ngày:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 150, 30));
        jPanel1.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 190, 30));
        jPanel1.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 12, 190, 30));

        jLabel5.setText("Chênh lệch:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 60, 120, 30));

        jLabel6.setText("Tổng:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, 120, 30));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Từ 1 triệu - 2 triệu", "Từ 2 triệu - 5 triệu", "Từ 5 triệu - 10 triệu", "Trên 10 triệu", " " }));
        jPanel1.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 60, 210, 30));

        jButton1.setBackground(new java.awt.Color(0, 153, 51));
        jButton1.setText("Lọc");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 60, -1, 30));

        jButton2.setBackground(new java.awt.Color(102, 153, 255));
        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 10, -1, 30));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Từ 1 triệu - 2 triệu", "Từ 2 triệu - 5 triệu", "Từ 5 triệu - 10 triệu", "Trên 10 triệu", " " }));
        jPanel1.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(752, 12, 210, 30));

        tbl_danhSachPhieuKetToan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã", "Người kiểm", "Đồng kiểm", "Bắt đầu", "Kết thúc", "Doanh thu", "Tiền mặt", "ATM", "Chênh lệch"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_danhSachPhieuKetToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_danhSachPhieuKetToanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_danhSachPhieuKetToan);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1159, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbl_danhSachPhieuKetToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_danhSachPhieuKetToanMouseClicked
        // TODO add your handling code here:
        // Lấy chỉ số dòng được chọn
        int selectedRow = tbl_danhSachPhieuKetToan.getSelectedRow();

        // Kiểm tra xem có dòng nào được chọn không
        if (selectedRow != -1) {
            // Lấy giá trị ô đầu tiên trong dòng được chọn
            Object firstCellValue = tbl_danhSachPhieuKetToan.getValueAt(selectedRow, 0);

            // In giá trị ô đầu tiên ra console hoặc thực hiện các thao tác khác với giá trị này vô hang tiếp đê nhục quá=)))))
            AcountingVoucherPrinter printer = new AcountingVoucherPrinter(new DanhSachPhieuKetToan_DAO().getOne((String) firstCellValue));
            printer.generatePDF();
            AcountingVoucherPrinter.PrintStatus status = printer.printFile();
            if (status == AcountingVoucherPrinter.PrintStatus.NOT_FOUND_FILE) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi không thể in hóa đơn: Không tìm thấy file");
            } else if (status == AcountingVoucherPrinter.PrintStatus.NOT_FOUND_PRINTER) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi không thể in hóa đơn: Không tìm thấy máy in");
            }
    }//GEN-LAST:event_tbl_danhSachPhieuKetToanMouseClicked
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTable tbl_danhSachPhieuKetToan;
    // End of variables declaration//GEN-END:variables
}