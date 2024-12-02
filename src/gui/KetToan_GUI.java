/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import dao.BangKiemTien_DAO;
import dao.KetToan_DAO;
import dao.KiemTien_DAO;
import dao.NhanVien_DAO;
import entity.BangKiemTien;
import entity.ChiTietBangKiemTien;
import entity.HoaDon;
import entity.KetToan;
import entity.KiemTien;
import entity.NhanVien;
import entity.TaiKhoan;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import raven.toast.Notifications;
import utilities.FormatNumber;

/**
 *
 * @author HÀ NHƯ
 */
public class KetToan_GUI extends javax.swing.JPanel {

    /**
     * Creates new form KetToan_GUI
     */
    private DefaultTableModel tblModelDemTien = new DefaultTableModel();
    private double sum = 0;
    private NhanVien nv2;
    private KetToan_DAO ketToan_DAO = new KetToan_DAO();
    private Date ngayKetThuc = new Date();
    private ArrayList<HoaDon> listHoaDon;
    private KetToan ketToan;
    private KiemTien_DAO kiemTien_DAO = new KiemTien_DAO();
    private BangKiemTien_DAO bangKiemTien_DAO = new BangKiemTien_DAO();
    private TaiKhoan tk;
    private NhanVien nv;
    private NhanVien_DAO nv_DAO;

    double doanhThu = 0;
    double atm = 0;
    double tienLayRa = 0;
    double chenhLech = 0;

    public KetToan_GUI(TaiKhoan tk) {
        initComponents();
        initTableModel();
        initForm();
        alterTable();
        nv_DAO = new NhanVien_DAO();
        this.tk = tk;
        nv = new NhanVien_DAO().getNhanVien(tk.getNhanVien().getMaNhanVien());
        tbl_demTien.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if (column == 2) { // Số lượng
                    TableModel model = (TableModel) e.getSource();
                    try {
                        int soLuong = Integer.parseInt(model.getValueAt(row, column).toString());
                        double menhGia = Integer.parseInt(model.getValueAt(row, 1).toString().replace(".", ""));
                        double total = 0;
                        if (soLuong < 0) {
                            Notifications.getInstance().show(Notifications.Type.ERROR, "Số lượng không hợp lệ!");
                            soLuong = 0;
                            model.setValueAt(0, row, column);

                        } else {
                            total = soLuong * menhGia;
                        }

//                        Notifications.getInstance().show(Notifications.Type.ERROR, "Số lượng không hợp lệ!");
                        sum = ketToan_DAO.getTong(getGiaTriTrongBang());

                        jtf_chenhLech.setText(FormatNumber.toVND(sum - tienLayRa - 1765000));
                        jtf_tongTien.setText(FormatNumber.toVND(sum));
                        jtf_tienMat.setText(FormatNumber.toVND(sum));
                        model.setValueAt(FormatNumber.toVND(total), row, 3); // Tổng
                    } catch (NumberFormatException ex) {
                        model.setValueAt(0, row, column);
                        Notifications.getInstance().show(Notifications.Type.ERROR, "Số lượng không hợp lệ!");
                    }
                }
            }

        });
        jtf_nhanVien1.setText(nv.getMaNhanVien() + " - " + nv.getTenNhanVien());
    }

    public void initTableModel() {
        // Products
        tblModelDemTien = new DefaultTableModel(new Object[]{"STT", "Mệnh giá", "Số lượng", "Tổng"
        }, 0);
    }

    public void alterTable() {
        DefaultTableCellRenderer rightAlign = new DefaultTableCellRenderer();
        rightAlign.setHorizontalAlignment(JLabel.RIGHT);

        // Set selection mode
        tbl_demTien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Align columns
        tbl_demTien.getColumnModel().getColumn(1).setCellRenderer(rightAlign);
        tbl_demTien.getColumnModel().getColumn(2).setCellRenderer(rightAlign);
        tbl_demTien.getColumnModel().getColumn(3).setCellRenderer(rightAlign);
        tbl_demTien.getColumnModel().getColumn(0).setCellRenderer(rightAlign);
    }

    public void initForm() {
        //Lấy thời gian kết thúc phiếu kết toán trước đó (Thời gian bắt đầu lần kết toán này)
        Date batDau = ketToan_DAO.getKetToanCuoi().getNgayKetThuc();
        //Hiển thị thời gian bắt đầu -> kết thúc kết toán
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String endStr = formatter.format(ngayKetThuc);
        String startStr = formatter.format(batDau);
        jtf_ngayGio.setText(startStr + " - " + endStr);
        //Hiển thị mã phiếu kết toán
        jtf_maPhieu.setText(ketToan_DAO.TaoMa(ngayKetThuc));
        listHoaDon = ketToan_DAO.getAllHoaDon(ketToan_DAO.getKetToanCuoi().getNgayKetThuc(), ngayKetThuc);
        doanhThu = ketToan_DAO.getDoanhThu(listHoaDon);
        atm = ketToan_DAO.getATM(listHoaDon);
        tienLayRa = doanhThu - atm;
        chenhLech = 0 - tienLayRa - 1765000;
        jtf_doanhThu.setText(FormatNumber.toVND(doanhThu));
        jtf_atm.setText(FormatNumber.toVND(atm));
        jtf_tienLayRa.setText(FormatNumber.toVND(tienLayRa));
        jtf_chenhLech.setText(FormatNumber.toVND(chenhLech));
    }

    public ArrayList<KiemTien> getGiaTriTrongBang() {
        DefaultTableModel model = (DefaultTableModel) tbl_demTien.getModel();
        int rowCount = model.getRowCount();
        ArrayList<KiemTien> listKiemTien = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            String giaTriStr = (String) model.getValueAt(i, 1);
            String soLuongStr = "0";
            try {
                soLuongStr = (String) model.getValueAt(i, 2);
            } catch (Exception e) {
            }
            double giaTri = Double.parseDouble(giaTriStr.replace(".", "").replace(",", "."));
            int soLuong;
            if (soLuongStr == null || soLuongStr.equals("0")) {
                soLuong = 0;
            } else {
                soLuong = Integer.parseInt(soLuongStr.trim());
            }
            if (soLuong > 0) {
                listKiemTien.add(new KiemTien(soLuong, giaTri));
            }

        }
        return listKiemTien;
    }

    public BangKiemTien getBangKiemTien() {
        String maBangKiemTien = bangKiemTien_DAO.taoMa(ngayKetThuc);
        ArrayList<KiemTien> listKiemTien = getGiaTriTrongBang();
        ArrayList<ChiTietBangKiemTien> listNhanVien = new ArrayList<>();
        listNhanVien.add(new ChiTietBangKiemTien(true, nv, new BangKiemTien(maBangKiemTien)));
        listNhanVien.add(new ChiTietBangKiemTien(false, nv2, new BangKiemTien(maBangKiemTien)));
        return new BangKiemTien(maBangKiemTien, listKiemTien, listNhanVien, ngayKetThuc, new Date());
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_demTien = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jtf_tongTien = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jtf_ngayGio = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jtf_chenhLech = new javax.swing.JTextField();
        jtf_maPhieu = new javax.swing.JTextField();
        jtf_nhanVien1 = new javax.swing.JTextField();
        jtf_nhanVien2 = new javax.swing.JTextField();
        jtf_tienMat = new javax.swing.JTextField();
        jtf_doanhThu = new javax.swing.JTextField();
        jtf_atm = new javax.swing.JTextField();
        jtf_tienLayRa = new javax.swing.JTextField();
        btn_ketToan = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết kiểm tiền", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        tbl_demTien.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tbl_demTien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "500.000", null, null},
                {"2", "200.000", null, null},
                {"3", "100.000", null, null},
                {"4", "50.000", null, null},
                {"5", "20.000", null, null},
                {"6", "10.000", null, null},
                {"7", "5.000", null, null},
                {"8", "2.000", null, null},
                {"9", "1.000", null, null},
                {"10", "500", null, null}
            },
            new String [] {
                "STT", "Mệnh giá", "Số lượng", "Tổng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_demTien);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Tổng:");

        jtf_tongTien.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(225, 225, 225)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jtf_tongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtf_tongTien, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(53, Short.MAX_VALUE)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtf_ngayGio.setText("Ngày và giờ");
        jtf_ngayGio.setEnabled(false);
        jPanel2.add(jtf_ngayGio, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 30, 250, 32));

        jLabel2.setText("Mã phiếu:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 80, 168, 31));

        jLabel3.setText("Nhân viên 2:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 166, 168, 31));

        jLabel4.setText("Nhân viên 1: ");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 123, 168, 31));

        jLabel5.setText("Tiền mặt:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 209, 168, 31));

        jLabel6.setText("Doanh thu:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 258, 168, 31));

        jLabel7.setText("ATM:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 301, 168, 31));

        jLabel8.setText("Tiền lấy ra:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 344, 168, 31));

        jLabel9.setText("Chênh lệch: ");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 387, 168, 31));

        jtf_chenhLech.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jtf_chenhLech.setForeground(new java.awt.Color(255, 0, 0));
        jtf_chenhLech.setText("0d");
        jtf_chenhLech.setEnabled(false);
        jPanel2.add(jtf_chenhLech, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 250, 30));

        jtf_maPhieu.setEnabled(false);
        jPanel2.add(jtf_maPhieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 250, 30));

        jtf_nhanVien1.setText("Mã nhân viên - Tên nhân viên");
        jtf_nhanVien1.setEnabled(false);
        jPanel2.add(jtf_nhanVien1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 250, 30));

        jtf_nhanVien2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_nhanVien2ActionPerformed(evt);
            }
        });
        jtf_nhanVien2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtf_nhanVien2KeyPressed(evt);
            }
        });
        jPanel2.add(jtf_nhanVien2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 250, 30));

        jtf_tienMat.setEnabled(false);
        jPanel2.add(jtf_tienMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 250, 30));

        jtf_doanhThu.setEnabled(false);
        jPanel2.add(jtf_doanhThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 250, 30));

        jtf_atm.setEnabled(false);
        jPanel2.add(jtf_atm, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, 250, 30));

        jtf_tienLayRa.setEnabled(false);
        jtf_tienLayRa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_tienLayRaActionPerformed(evt);
            }
        });
        jPanel2.add(jtf_tienLayRa, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 250, 30));

        btn_ketToan.setBackground(new java.awt.Color(0, 51, 255));
        btn_ketToan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btn_ketToan.setText("Kết toán");
        btn_ketToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ketToanActionPerformed(evt);
            }
        });
        jPanel2.add(btn_ketToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 460, 370, 40));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Kết toán");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(471, 471, 471))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtf_tienLayRaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_tienLayRaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_tienLayRaActionPerformed

    private void btn_ketToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ketToanActionPerformed
        // TODO add your handling code here:
        if (nv2 != null) {
            ketToan_DAO.taoPhieuKetToan(getBangKiemTien(), ngayKetThuc);
            Notifications.getInstance().show(Notifications.Type.SUCCESS, "Tạo phiếu kết toán thành công");
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Chưa có người đồng kiểm!");
            Notifications.getInstance().show(Notifications.Type.ERROR, "Kết toán thất bại!");
        }
    }//GEN-LAST:event_btn_ketToanActionPerformed

    private void jtf_nhanVien2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_nhanVien2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_nhanVien2ActionPerformed

    private void jtf_nhanVien2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_nhanVien2KeyPressed
        if (evt.getKeyCode() == 10) {
            String maNhanVien2 = jtf_nhanVien2.getText().trim();
            nv2 = nv_DAO.getNhanVien(maNhanVien2);
            if (nv2 != null) {
                Notifications.getInstance().show(Notifications.Type.INFO, "Thêm nhân viên thành công");
                jtf_nhanVien2.setText(nv2.getMaNhanVien() + " - " + nv2.getTenNhanVien());

            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Không tìm thấy nhân viên");

            }
        }
    }//GEN-LAST:event_jtf_nhanVien2KeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ketToan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JTextField jtf_atm;
    private javax.swing.JTextField jtf_chenhLech;
    private javax.swing.JTextField jtf_doanhThu;
    private javax.swing.JTextField jtf_maPhieu;
    private javax.swing.JTextField jtf_ngayGio;
    private javax.swing.JTextField jtf_nhanVien1;
    private javax.swing.JTextField jtf_nhanVien2;
    private javax.swing.JTextField jtf_tienLayRa;
    private javax.swing.JTextField jtf_tienMat;
    private javax.swing.JTextField jtf_tongTien;
    private javax.swing.JTable tbl_demTien;
    // End of variables declaration//GEN-END:variables
}
