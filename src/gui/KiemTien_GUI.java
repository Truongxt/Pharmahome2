/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import dao.BangKiemTien_DAO;
import dao.KiemTien_DAO;
import dao.NhanVien_DAO;
import entity.KiemTien;
import entity.NhanVien;
import entity.TaiKhoan;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import main.Main;
import raven.toast.Notifications;
import utilities.FormatNumber;

/**
 *
 * @author HÀ NHƯ
 */
public class KiemTien_GUI extends javax.swing.JPanel {

    /**
     * Creates new form KiemTien_GUI
     */
    private DefaultTableModel tblModelDemTien = new DefaultTableModel();
    private double sum = 0;
    private NhanVien nv2;
    private Date ngayTao = new Date();
    private BangKiemTien_DAO bangKiemTien_DAO = new BangKiemTien_DAO();
    private KiemTien_DAO kiemTien_DAO = new KiemTien_DAO();
    double chenhLech = 0;
    private TaiKhoan tk;
    private NhanVien nv;
    private NhanVien_DAO nv_DAO;

    public KiemTien_GUI(TaiKhoan tk) {
        initComponents();
        initTableModel();
        initForm(nv);
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
                        sum = kiemTien_DAO.getTongTien(getGiaTriTrongBang());
                        jtf_chenhLech.setText(FormatNumber.toVND(sum - 1765000));
                        jtf_tongTien.setText(FormatNumber.toVND(sum));
                        model.setValueAt(FormatNumber.toVND(total), row, 3); // Tổng
                    } catch (NumberFormatException ex) {
                        model.setValueAt(0, row, column);
                        Notifications.getInstance().show(Notifications.Type.ERROR, "Số lượng không hợp lệ!");
                    }
                }
            }

        });
        jtf_maNhanVien1.setText(nv.getMaNhanVien());
        jtf_tenNhanVien1.setText(nv.getTenNhanVien());
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

    public void initForm(NhanVien nv) {
        ngayTao = new Date();
        //Hiển thị thời gian bắt đầu -> kết thúc kết toán
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String format = formatter.format(ngayTao);

        jtf_ngayGio.setText(format);
        jtf_maPhieu.setText(bangKiemTien_DAO.taoMa(ngayTao));

        chenhLech = 0 - 1765000;
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_demTien = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jtf_tongTien = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jtf_ngayGio = new javax.swing.JTextField();
        jtf_maNhanVien1 = new javax.swing.JTextField();
        jtf_tenNhanVien1 = new javax.swing.JTextField();
        jtf_maNhanVien2 = new javax.swing.JTextField();
        jtf_chenhLech = new javax.swing.JTextField();
        jtf_maPhieu = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jtf_tenNhanVien2 = new javax.swing.JTextField();
        btn_baoCao = new javax.swing.JButton();

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

        jScrollPane2.setViewportView(jScrollPane1);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setText("Tổng:");

        jtf_tongTien.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtf_tongTien)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addGap(2, 2, 2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jtf_tongTien)
                        .addGap(1, 1, 1))))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Kiểm tiền dự phòng");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtf_ngayGio.setText("Ngày và giờ");
        jPanel2.add(jtf_ngayGio, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 308, 31));

        jtf_maNhanVien1.setEnabled(false);
        jtf_maNhanVien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_maNhanVien1ActionPerformed(evt);
            }
        });
        jPanel2.add(jtf_maNhanVien1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 280, 33));

        jtf_tenNhanVien1.setEnabled(false);
        jPanel2.add(jtf_tenNhanVien1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 280, 33));

        jtf_maNhanVien2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtf_maNhanVien2KeyPressed(evt);
            }
        });
        jPanel2.add(jtf_maNhanVien2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, 280, 33));

        jtf_chenhLech.setEnabled(false);
        jPanel2.add(jtf_chenhLech, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 400, 280, 33));

        jtf_maPhieu.setEnabled(false);
        jPanel2.add(jtf_maPhieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 280, 33));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Mã phiếu:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 170, 33));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Chênh lệch:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 170, 36));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Tên nhân viên 2:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 170, 33));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Mã nhân viên 1: ");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 170, 33));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Tên nhân viên 1:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 170, 33));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Mã nhân viên 2:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 170, 33));
        jPanel2.add(jtf_tenNhanVien2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 340, 280, 33));

        btn_baoCao.setBackground(new java.awt.Color(0, 102, 255));
        btn_baoCao.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btn_baoCao.setText("Tạo báo cáo");
        btn_baoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_baoCaoActionPerformed(evt);
            }
        });
        jPanel2.add(btn_baoCao, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 473, 400, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(414, 414, 414))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtf_maNhanVien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_maNhanVien1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_maNhanVien1ActionPerformed

    private void btn_baoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_baoCaoActionPerformed
        // TODO add your handling code here:
        if (nv2 == null) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Chưa có nhân viên đồng kiểm!");
            Notifications.getInstance().show(Notifications.Type.ERROR, "Lưu phiếu kiểm tiền không thành công!");
        } else {
            ArrayList<NhanVien> listNhanVien = new ArrayList<>();
            listNhanVien.add(nv);
            listNhanVien.add(nv2);
            bangKiemTien_DAO.createBangKiemTien(getGiaTriTrongBang(), listNhanVien, ngayTao);
            Notifications.getInstance().show(Notifications.Type.INFO, "Tạo phiếu kiểm tiền thành công");
            try {
                new Main().getMain().showForm(new KiemTien_GUI(new Main().getTk()));
            } catch (SQLException ex) {
                Logger.getLogger(KiemTien_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_baoCaoActionPerformed

    private void jtf_maNhanVien2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_maNhanVien2KeyPressed
        if (evt.getKeyCode() == 10) {
            String maNhanVien2 = jtf_maNhanVien2.getText().trim();
            nv2 = nv_DAO.getNhanVien(maNhanVien2);
            if (nv2 != null) {
                Notifications.getInstance().show(Notifications.Type.INFO, "Thêm nhân viên thành công");
                jtf_tenNhanVien2.setText(nv2.getTenNhanVien());

            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Không tìm thấy nhân viên");

            }

        }
    }//GEN-LAST:event_jtf_maNhanVien2KeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_baoCao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jtf_chenhLech;
    private javax.swing.JTextField jtf_maNhanVien1;
    private javax.swing.JTextField jtf_maNhanVien2;
    private javax.swing.JTextField jtf_maPhieu;
    private javax.swing.JTextField jtf_ngayGio;
    private javax.swing.JTextField jtf_tenNhanVien1;
    private javax.swing.JTextField jtf_tenNhanVien2;
    private javax.swing.JTextField jtf_tongTien;
    private javax.swing.JTable tbl_demTien;
    // End of variables declaration//GEN-END:variables
}
