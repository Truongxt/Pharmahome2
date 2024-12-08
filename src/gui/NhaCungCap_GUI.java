package gui;

import com.formdev.flatlaf.FlatClientProperties;
import dao.NhaCungCap_DAO;
import entity.NhaCungCap;
import entity.NhanVien;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import raven.toast.Notifications;
import utilities.SVGIcon;

public class NhaCungCap_GUI extends javax.swing.JPanel {

    private DefaultTableModel model;
    private ArrayList<NhaCungCap> listNCC;
    private static CellStyle cellStyleFormatNumber = null;
    private int chiSo = 5;

    public NhaCungCap_GUI() {
        initComponents();
        try {
            connect.ConnectDB.connect();
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCap_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        model = new DefaultTableModel(new String[]{"Mã nhà cung cấp", "Tên nhà cung cấp", "Số điện thoại", "Địa chỉ", "Email", "Trạng thái"}, 0);
        tbl_nhaCungCap.setModel(model);
        listNCC = new NhaCungCap_DAO().getAllNhaCungCap();
        jtf_manhacungcap.setText(xuLyMa());
        buttonGroup1.add(rd_dangLamViec);
        buttonGroup1.add(rd_nghiViec);
        taiThongTinLenBang(listNCC);
        alterTable();
    }

    public final void taiThongTinLenBang(ArrayList<NhaCungCap> list) {
        model.setRowCount(0);
        for (NhaCungCap nhaCungCap : list) {
            Object[] row = initObject(nhaCungCap);
            model.addRow(row);
        }
    }

    public final void alterTable() {
        DefaultTableCellRenderer rightAlign = new DefaultTableCellRenderer();
        rightAlign.setHorizontalAlignment(JLabel.RIGHT);
        //// Align
        tbl_nhaCungCap.getColumnModel().getColumn(2).setCellRenderer(rightAlign);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtf_timSoDienThoai = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        btn_loc = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_nhaCungCap = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtf_tenNhaCungCap = new javax.swing.JTextField();
        jtf_soDienThoai = new javax.swing.JTextField();
        jtf_email = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtf_diaChi = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        rd_dangLamViec = new javax.swing.JRadioButton();
        rd_nghiViec = new javax.swing.JRadioButton();
        btn_lamMoi = new javax.swing.JButton();
        btn_capNhat = new javax.swing.JButton();
        btn_xuatFile = new javax.swing.JButton();
        btn_themMoi = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jtf_manhacungcap = new javax.swing.JTextField();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Tìm theo số điện thoại");

        jButton2.setText("Lọc");

        btn_loc.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btn_loc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nhanvien/funnel.png"))); // NOI18N
        btn_loc.setText("Lọc");
        btn_loc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_locActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtf_timSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(btn_loc)
                .addGap(512, 512, 512)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtf_timSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_loc, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(174, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách nhà cung cấp", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        tbl_nhaCungCap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhà cung cấp", "Tên nhà cung cấp", "Số điện thoại", "Địa chỉ", "Email", "Trạng thái"
            }
        ));
        tbl_nhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_nhaCungCapMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_nhaCungCap);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin nhà cung cấp", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Tên nhà cung cấp :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Số điện thoại :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Email :");

        jtf_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_emailActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Địa chỉ  :");

        jtf_diaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_diaChiActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setText("Trạng thái:");

        rd_dangLamViec.setText("Đang làm việc");

        rd_nghiViec.setText("Nghỉ việc");
        rd_nghiViec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_nghiViecActionPerformed(evt);
            }
        });

        btn_lamMoi.setFont(btn_lamMoi.getFont().deriveFont((float)14));
        btn_lamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nhanvien/remove.png"))); // NOI18N
        btn_lamMoi.setText("Xóa trắng");
        btn_lamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lamMoiActionPerformed(evt);
            }
        });

        btn_capNhat.setFont(btn_capNhat.getFont().deriveFont((float)14));
        btn_capNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nhanvien/setting.png"))); // NOI18N
        btn_capNhat.setText("Cập nhật");
        btn_capNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_capNhatActionPerformed(evt);
            }
        });

        btn_xuatFile.setFont(btn_xuatFile.getFont().deriveFont((float)14));
        btn_xuatFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nhanvien/logo.png"))); // NOI18N
        btn_xuatFile.setText("Xuất file");
        btn_xuatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xuatFileActionPerformed(evt);
            }
        });

        btn_themMoi.setFont(btn_themMoi.getFont().deriveFont((float)14));
        btn_themMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nhanvien/plus.png"))); // NOI18N
        btn_themMoi.setText("Thêm mới");
        btn_themMoi.setOpaque(true);
        btn_themMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themMoiActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Ma cung cấp :");

        jtf_manhacungcap.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtf_tenNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtf_soDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtf_diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtf_email, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rd_dangLamViec)
                                .addGap(18, 18, 18)
                                .addComponent(rd_nghiViec))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtf_manhacungcap, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_themMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_lamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_capNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_xuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf_manhacungcap, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf_tenNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf_soDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf_diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf_email, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rd_dangLamViec)
                    .addComponent(rd_nghiViec))
                .addGap(71, 71, 71)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_capNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_lamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_xuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_themMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 444, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtf_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_emailActionPerformed

    }//GEN-LAST:event_jtf_emailActionPerformed

    private void btn_locActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_locActionPerformed
        model.setRowCount(0);
        String sdt = jtf_soDienThoai.getText();
        NhaCungCap ncc = new NhaCungCap_DAO().timTheoSDT(sdt);
        Object[] obj = initObject(ncc);
        model.addRow(obj);


    }//GEN-LAST:event_btn_locActionPerformed

    public Object[] initObject(NhaCungCap ncc) {
        Object[] obj = new Object[6];
        obj[0] = ncc.getMaNCC();
        obj[1] = ncc.getTenNCC();
        obj[2] = ncc.getSdt();
        obj[3] = ncc.getDiaChi();
        obj[4] = ncc.getEmail();
        if (ncc.isTrangThai()) {
            obj[5] = "Đang hoạt động";

        } else {
            obj[5] = "không hoạt động";
        }
        return obj;
    }
    private void jtf_diaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_diaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_diaChiActionPerformed

    private void rd_nghiViecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_nghiViecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rd_nghiViecActionPerformed

    private void btn_lamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lamMoiActionPerformed
        jtf_manhacungcap.setText("");
        jtf_tenNhaCungCap.setText("");
        jtf_diaChi.setText("");
        jtf_soDienThoai.setText("");
        jtf_email.setText("");
//        ButtonGroup.clearSelection();
    }//GEN-LAST:event_btn_lamMoiActionPerformed

    private void btn_capNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_capNhatActionPerformed
        int index = tbl_nhaCungCap.getSelectedRow();
        String maNCC = tbl_nhaCungCap.getValueAt(index, 0) + "";
        NhaCungCap ncc = NhaCungCap_DAO.getNhaCungCap(maNCC);
        String tenNCC = jtf_tenNhaCungCap.getText();
        String soDT = jtf_soDienThoai.getText();
        String diaChi = jtf_diaChi.getText();
        String email = jtf_email.getText();
        boolean trangThai = true;
        if (rd_nghiViec.isSelected()) {
            trangThai = false;
        }
        if (maNCC.isEmpty() || tenNCC.isEmpty() || soDT.isEmpty() || diaChi.isEmpty() || email.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Vui lòng nhập đầy đủ thông tin");
            return;
        }
        NhaCungCap newNCC = new NhaCungCap(maNCC, tenNCC, diaChi, email, soDT, trangThai);
        try {
            if (NhaCungCap_DAO.suaNhaCungCap(maNCC, newNCC)) {
                Object obj[] = initObject(newNCC);
                model.insertRow(index, obj);
                model.removeRow(index + 1);
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Sửa thành công");

            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Sửa thất bại");

            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCap_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_capNhatActionPerformed

    private void btn_xuatFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xuatFileActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn đường dẫn và tên file");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Hiển thị hộp thoại và kiểm tra nếu người dùng chọn OK
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try {
                // Lấy đường dẫn và tên file được chọn
                File fileToSave = fileChooser.getSelectedFile();
                String filePath = fileToSave.getAbsolutePath();
                // Gọi phương thức để tạo file Excel với đường dẫn và tên file đã chọn
                createExcel(NhaCungCap_DAO.getAllNhaCungCap(), filePath + ".xlsx");
                Desktop.getDesktop().open(new File(filePath + ".xlsx"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btn_xuatFileActionPerformed
    public String xuLyMa() {

        String ma = "NCC" + String.format("%05d", chiSo++);
        return ma;
    }
    private void btn_themMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themMoiActionPerformed

        String maNCC = xuLyMa();
        String tenNCC = jtf_tenNhaCungCap.getText();
        String soDienThoai = jtf_soDienThoai.getText();
        String diaChi = jtf_diaChi.getText();
        String email = jtf_email.getText();
        boolean trangThai = true;
        if (rd_nghiViec.isSelected()) {
            trangThai = false;
        }
        if (maNCC.isEmpty() || tenNCC.isEmpty() || soDienThoai.isEmpty() || diaChi.isEmpty() || email.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Vui lòng nhập đầy đủ thông tin");
            return;
        }
        NhaCungCap ncc = new NhaCungCap(maNCC, tenNCC, soDienThoai, diaChi, email, trangThai);
        Object obj[] = initObject(ncc);
        if (NhaCungCap_DAO.create(ncc)) {
            JOptionPane.showConfirmDialog(this, "Thêm thành công", "Xác nhân", JOptionPane.DEFAULT_OPTION);
            model.addRow(obj);
        }

    }//GEN-LAST:event_btn_themMoiActionPerformed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked

    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void tbl_nhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_nhaCungCapMouseClicked
        int index = tbl_nhaCungCap.getSelectedRow();
        String maNCC = tbl_nhaCungCap.getValueAt(index, 0) + "";
        NhaCungCap ncc = NhaCungCap_DAO.getNhaCungCap(maNCC);
        jtf_manhacungcap.setText(ncc.getMaNCC());
        jtf_tenNhaCungCap.setText(ncc.getTenNCC());
        jtf_soDienThoai.setText(ncc.getSdt());
        jtf_diaChi.setText(ncc.getDiaChi());
        jtf_email.setText(ncc.getEmail());
        if (ncc.isTrangThai()) {
            rd_dangLamViec.setSelected(true);
        } else {
            rd_nghiViec.setSelected(true);

        }
    }//GEN-LAST:event_tbl_nhaCungCapMouseClicked
    public void createExcel(ArrayList<NhaCungCap> listEmp, String filePath) throws IOException {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        SXSSFSheet sheet = workbook.createSheet("Danh sách nhân viên");
        sheet.trackAllColumnsForAutoSizing();
        Row rowNgayIn = sheet.createRow(0);
        Cell cell = rowNgayIn.createCell(0);
        cell.setCellValue("Ngày in: " + LocalDate.now());
        int rowIndex = 1;
        writeHeader(sheet, rowIndex);
        rowIndex = 4;
        for (NhaCungCap ncc : listEmp) {
            SXSSFRow row = sheet.createRow(rowIndex);
            writeNCC(ncc, row);
            rowIndex++;
        }
        writeFooter(sheet, rowIndex);
        createOutputFile(workbook, filePath);
        Notifications.getInstance().show(Notifications.Type.SUCCESS, "Xuất file thành công!");
    }

    private void writeHeader(SXSSFSheet sheet, int rowIndex) {
        CellStyle cellStyle = createStyleForHeader(sheet);
        Row rowNgayIn = sheet.createRow(rowIndex);

        Row row = sheet.createRow(rowIndex + 1);
        //create cells

        Cell cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mã nhà cung cấp");

        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tên nhà cung cấp");

        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Địa chỉ");

        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Số điện thoại");

        cell = row.createCell(4);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Email");

        cell = row.createCell(5);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Trạng thái");

        cell = row.createCell(6);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Trạng thái");

    }

    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    private void writeFooter(SXSSFSheet sheet, int rowIndex) {
        Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(8, CellType.STRING);
        cell.setCellValue("Số lượng nhà cung cấp: " + NhaCungCap_DAO.getSize());
    }

    private void writeNCC(NhaCungCap ncc, SXSSFRow row) {
        if (cellStyleFormatNumber == null) {
            // Format number
            short format = (short) BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workbook.createDataFormat();
            // short format = df.getFormat("#,##0");

            //Create CellStyle
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }

        Cell cell = row.createCell(0);
        cell.setCellValue(ncc.getMaNCC());

        cell = row.createCell(1);
        cell.setCellValue(ncc.getTenNCC());

        cell = row.createCell(2);
        cell.setCellValue(ncc.getDiaChi());
        cell.setCellStyle(cellStyleFormatNumber);

        cell = row.createCell(3);
        cell.setCellValue(ncc.getSdt());

        cell = row.createCell(4);
        cell.setCellValue(ncc.getEmail());

        cell = row.createCell(6);
        if (ncc.isTrangThai()) {
            cell.setCellValue("Đang làm việc");

        } else {
            cell.setCellValue("Nghỉ việc");
        }

    }

    private void createOutputFile(SXSSFWorkbook workbook, String excelFilePath) throws FileNotFoundException, IOException, IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_capNhat;
    private javax.swing.JButton btn_lamMoi;
    private javax.swing.JButton btn_loc;
    private javax.swing.JButton btn_themMoi;
    private javax.swing.JButton btn_xuatFile;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jtf_diaChi;
    private javax.swing.JTextField jtf_email;
    private javax.swing.JTextField jtf_manhacungcap;
    private javax.swing.JTextField jtf_soDienThoai;
    private javax.swing.JTextField jtf_tenNhaCungCap;
    private javax.swing.JTextField jtf_timSoDienThoai;
    private javax.swing.JRadioButton rd_dangLamViec;
    private javax.swing.JRadioButton rd_nghiViec;
    private javax.swing.JTable tbl_nhaCungCap;
    // End of variables declaration//GEN-END:variables
}
