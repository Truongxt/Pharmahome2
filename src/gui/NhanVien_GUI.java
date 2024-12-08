/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import connect.ConnectDB;
import dao.NhanVien_DAO;
import dao.VaiTro_DAO;
import entity.NhanVien;
import entity.VaiTro;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import message.Notification;
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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import raven.toast.Notifications;

import utilities.ConvertDate;

/**
 *
 * @author HÀ NHƯ
 */
public class NhanVien_GUI extends javax.swing.JPanel {

    private NhanVien_DAO nv_Dao;
    private ArrayList<NhanVien> listNV;
    private DefaultTableModel tableModel;
    private VaiTro_DAO vaiTro_DAO;
    private static CellStyle cellStyleFormatNumber = null;

    public NhanVien_GUI() throws SQLException {
        initComponents();
        ConnectDB.connect();
        nv_Dao = new NhanVien_DAO();
        vaiTro_DAO = new VaiTro_DAO();
        tableModel = new DefaultTableModel(new String[]{"Mã nhân viên", "Tên nhân viên", "Số điện thoại", "Email", "Địa chỉ", "CCCD"}, 0);
        tableNV.setModel(tableModel);
        buttonGroup1.add(rdb_dangLamViec);
        buttonGroup1.add(rdb_nghiViec);
        jtf_maNhanVien.setText(nv_Dao.generateID());
        initTable();
        initJcb();
    }

    public void initTable() {
        listNV = nv_Dao.getAllNhanVien();
        for (NhanVien nv : listNV) {
            Object obj[] = new Object[6];
            obj[0] = nv.getMaNhanVien();
            obj[1] = nv.getTenNhanVien();
            obj[2] = nv.getSdt();
            obj[3] = nv.getEmail();
            obj[4] = nv.getDiaChi();
            obj[5] = nv.getCccd();
            tableModel.addRow(obj);
        }
    }

    public void initJcb() {
        jcb_trangThai.addItem("Đang làm việc");
        jcb_trangThai.addItem("Nghỉ việc");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jtf_timTheoMa = new javax.swing.JTextField();
        jtf_timTheoTen = new javax.swing.JTextField();
        jcb_trangThai = new javax.swing.JComboBox<>();
        btn_taiLai = new javax.swing.JButton();
        jtf_timTheoSDT = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableNV = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtf_maNhanVien = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jtf_tenNhanVien = new javax.swing.JTextField();
        jtf_sdt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jtf_emil = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jtf_diaChi = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jtf_cccd = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        rdb_dangLamViec = new javax.swing.JRadioButton();
        rdb_nghiViec = new javax.swing.JRadioButton();
        btn_xoaTrang = new javax.swing.JButton();
        btn_them = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jDate_ngayVaoLam = new com.toedter.calendar.JDateChooser();
        btn_xuatExcel = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtf_timTheoMa.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Nhập mã nhân viên");
        jtf_timTheoMa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_timTheoMaKeyTyped(evt);
            }
        });
        jPanel1.add(jtf_timTheoMa, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 28, 235, 40));
        jtf_timTheoMa.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Nhập mã nhân viên");

        jtf_timTheoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_timTheoTenActionPerformed(evt);
            }
        });
        jtf_timTheoTen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_timTheoTenKeyTyped(evt);
            }
        });
        jPanel1.add(jtf_timTheoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(296, 28, 229, 40));
        jtf_timTheoTen.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập tên nhân viên");

        jcb_trangThai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jtf_timTheoSDT.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Nhập số điện thoại");
        jcb_trangThai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcb_trangThaiItemStateChanged(evt);
            }
        });
        jcb_trangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb_trangThaiActionPerformed(evt);
            }
        });
        jPanel1.add(jcb_trangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(854, 28, 229, 40));

        btn_taiLai.setBackground(new java.awt.Color(51, 255, 0));
        btn_taiLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reload_employee.png"))); // NOI18N
        btn_taiLai.setText("Tải lại");
        btn_taiLai.setPreferredSize(new java.awt.Dimension(72, 28));
        btn_taiLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_taiLaiActionPerformed(evt);
            }
        });
        jPanel1.add(btn_taiLai, new org.netbeans.lib.awtextra.AbsoluteConstraints(1133, 30, 227, 42));

        jtf_timTheoSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_timTheoSDTActionPerformed(evt);
            }
        });
        jtf_timTheoSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_timTheoSDTKeyTyped(evt);
            }
        });
        jPanel1.add(jtf_timTheoSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(575, 28, 229, 40));
        jtf_timTheoTen.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập tên nhân viên");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        tableNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Số điện thoại", "Email", "Địa chỉ", "CCCD"
            }
        ));
        tableNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableNV);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 905, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Mã nhân viên:");

        jtf_maNhanVien.setEnabled(false);
        jtf_maNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_maNhanVienActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setText("Tên nhân viên: ");

        jtf_sdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_sdtActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Số điện thoại:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText(" Email:");

        jtf_emil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_emilActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setText("Địa chỉ:");

        jtf_diaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_diaChiActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setText("CCCD:");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setText("Trạng thái:");

        rdb_dangLamViec.setText("Đang làm việc");

        rdb_nghiViec.setText("Nghỉ việc");
        rdb_nghiViec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdb_nghiViecActionPerformed(evt);
            }
        });

        btn_xoaTrang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nhanvien/remove.png"))); // NOI18N
        btn_xoaTrang.setText("Xóa trắng");
        btn_xoaTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaTrangActionPerformed(evt);
            }
        });

        btn_them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nhanvien/plus.png"))); // NOI18N
        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        btn_sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nhanvien/setting.png"))); // NOI18N
        btn_sua.setText("Sửa");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setText("Ngày vào làm :");

        btn_xuatExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nhanvien/logo.png"))); // NOI18N
        btn_xuatExcel.setText("Xuất excel");
        btn_xuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xuatExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(306, 306, 306)
                        .addComponent(btn_xoaTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jtf_maNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jtf_tenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jtf_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jtf_emil, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jtf_diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jtf_cccd, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(rdb_dangLamViec)
                                    .addGap(18, 18, 18)
                                    .addComponent(rdb_nghiViec))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jDate_ngayVaoLam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btn_xuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(btn_them, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf_maNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf_tenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf_emil, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf_diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf_cccd, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdb_dangLamViec)
                    .addComponent(rdb_nghiViec))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDate_ngayVaoLam, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_xoaTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_xuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtf_timTheoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_timTheoTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_timTheoTenActionPerformed

    private void rdb_nghiViecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdb_nghiViecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdb_nghiViecActionPerformed

    private void jtf_maNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_maNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_maNhanVienActionPerformed

    private void jtf_sdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_sdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_sdtActionPerformed

    private void jtf_emilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_emilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_emilActionPerformed

    private void jtf_diaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_diaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_diaChiActionPerformed

    private void jcb_trangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb_trangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcb_trangThaiActionPerformed

    private void tableNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNVMouseClicked
        int index = tableNV.getSelectedRow();
        String maNhanVien = tableNV.getValueAt(index, 0) + "";
        NhanVien nv = nv_Dao.getNhanVien(maNhanVien);
        jtf_maNhanVien.setText(nv.getMaNhanVien());
        jtf_tenNhanVien.setText(nv.getTenNhanVien());
        jtf_cccd.setText(nv.getCccd());
        jtf_sdt.setText(nv.getSdt());
        jtf_emil.setText(nv.getEmail());
        jtf_diaChi.setText(nv.getDiaChi());
        if (nv.isTrangThai()) {
            rdb_dangLamViec.setSelected(true);
        } else {
            rdb_nghiViec.setSelected(true);

        }
        Date ngayVaoLam = java.sql.Date.valueOf(nv.getNgayVaolam());
        ngayVaoLam.setYear(ngayVaoLam.getYear() + 1900);
        jDate_ngayVaoLam.setDate(ngayVaoLam);

    }//GEN-LAST:event_tableNVMouseClicked

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        int index = tableNV.getSelectedRow();
        if(index == -1){
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Bạn cần phải chọn dòng để chỉnh sửa!");
            return;
        }
        String maNhanVien = tableNV.getValueAt(index, 0) + "";
        NhanVien nv = nv_Dao.getNhanVien(maNhanVien);
        String tenNhanVien = jtf_tenNhanVien.getText();
        String email = jtf_emil.getText();
        String diaChi = jtf_diaChi.getText();
        String sdt = jtf_sdt.getText();
        String cccd = jtf_cccd.getText();
        boolean trangThai = true;
        if (rdb_nghiViec.isSelected()) {
            trangThai = false;
        }
        LocalDate nvl = ConvertDate.convert(jDate_ngayVaoLam.getDate());
        NhanVien newNhanVien = new NhanVien(maNhanVien, tenNhanVien, email, sdt, diaChi, cccd, trangThai, nvl);
        try {
            if (nv_Dao.suaNhanVien(maNhanVien, newNhanVien)) {
                JOptionPane.showConfirmDialog(this, "Sửa thành công", "Thông báo", JOptionPane.DEFAULT_OPTION);
                Object obj[] = new Object[6];
                obj[0] = newNhanVien.getMaNhanVien();
                obj[1] = newNhanVien.getTenNhanVien();
                obj[2] = newNhanVien.getSdt();
                obj[3] = newNhanVien.getEmail();
                obj[4] = newNhanVien.getDiaChi();
                obj[5] = newNhanVien.getCccd();
                tableModel.insertRow(index, obj);
                tableModel.removeRow(index + 1);

            } else {
                JOptionPane.showConfirmDialog(this, "Sửa thất bại", "Thông báo", JOptionPane.DEFAULT_OPTION);

            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVien_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_suaActionPerformed

    private void btn_taiLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_taiLaiActionPerformed
        tableModel.setRowCount(0);
        ArrayList<NhanVien> listNV = nv_Dao.getAllNhanVien();
        for (NhanVien nv : listNV) {
            Object[] obj = initObject(nv);
            tableModel.addRow(obj);
        }

    }//GEN-LAST:event_btn_taiLaiActionPerformed

    public Object[] initObject(NhanVien nv) {
        Object obj[] = new Object[6];
        obj[0] = nv.getMaNhanVien();
        obj[1] = nv.getTenNhanVien();
        obj[2] = nv.getSdt();
        obj[3] = nv.getEmail();
        obj[4] = nv.getDiaChi();
        obj[5] = nv.getCccd();
        return obj;
    }
    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        if (checkData()) {
            String maNV = jtf_maNhanVien.getText();
            String tenNhanVien = jtf_tenNhanVien.getText();
            String email = jtf_emil.getText();
            String diaChi = jtf_diaChi.getText();
            String sdt = jtf_sdt.getText();
            String cccd = jtf_cccd.getText();
            boolean trangThai = true;
            if (rdb_nghiViec.isSelected()) {
                trangThai = false;
            }
            LocalDate nvl = ConvertDate.convert(jDate_ngayVaoLam.getDate());
            NhanVien nv = new NhanVien(maNV, tenNhanVien, email, sdt, diaChi, cccd, trangThai, nvl);
            Object obj[] = new Object[6];
            obj[0] = nv.getMaNhanVien();
            obj[1] = nv.getTenNhanVien();
            obj[2] = nv.getSdt();
            obj[3] = nv.getEmail();
            obj[4] = nv.getDiaChi();
            obj[5] = nv.getCccd();
            if (nv_Dao.create(nv)) {
                JOptionPane.showConfirmDialog(this, "Thêm thành công", "Xác nhân", JOptionPane.DEFAULT_OPTION);
                tableModel.addRow(obj);
            }

        }


    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_xoaTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaTrangActionPerformed
        jtf_maNhanVien.setText("");
        jtf_tenNhanVien.setText("");
        jtf_cccd.setText("");
        jtf_sdt.setText("");
        jtf_emil.setText("");
        jtf_diaChi.setText("");
        buttonGroup1.clearSelection();
        jtf_timTheoMa.setText("");
        jtf_timTheoTen.setText("");

    }//GEN-LAST:event_btn_xoaTrangActionPerformed

    private void jtf_timTheoTenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_timTheoTenKeyTyped
        tableModel.setRowCount(0);

        List<NhanVien> danhSachNhanVien = nv_Dao.timKiemTheoTen(jtf_timTheoTen.getText().trim());
        for (NhanVien nv : danhSachNhanVien) {
            Object obj[] = initObject(nv);
            tableModel.addRow(obj);
        }
    }//GEN-LAST:event_jtf_timTheoTenKeyTyped

    private void jtf_timTheoMaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_timTheoMaKeyTyped
        tableModel.setRowCount(0);
        List<NhanVien> danhSachNhanVien = nv_Dao.timKiemTheoMa(jtf_timTheoMa.getText().trim());
        for (NhanVien nv : danhSachNhanVien) {
            Object obj[] = initObject(nv);
            tableModel.addRow(obj);
        }
    }//GEN-LAST:event_jtf_timTheoMaKeyTyped

    private void btn_xuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xuatExcelActionPerformed
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
                createExcel(nv_Dao.getAllNhanVien(), filePath + ".xlsx");
                Desktop.getDesktop().open(new File(filePath + ".xlsx"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btn_xuatExcelActionPerformed

    private void jtf_timTheoSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_timTheoSDTActionPerformed

    }//GEN-LAST:event_jtf_timTheoSDTActionPerformed

    private void jtf_timTheoSDTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_timTheoSDTKeyTyped
        tableModel.setRowCount(0);

        List<NhanVien> danhSachNhanVien = nv_Dao.timTheoSDT(jtf_timTheoSDT.getText());

        for (NhanVien nv : danhSachNhanVien) {
            Object obj[] = initObject(nv);
            tableModel.addRow(obj);
        }
    }//GEN-LAST:event_jtf_timTheoSDTKeyTyped

    private void jcb_trangThaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcb_trangThaiItemStateChanged
        tableModel.setRowCount(0);
        boolean trangThai = jcb_trangThai.getSelectedIndex() == 0 ? true : false;
        List<NhanVien> danhSachNhanVien = nv_Dao.timKiemTheoTrangThai(trangThai);

        for (NhanVien nv : danhSachNhanVien) {
            Object obj[] = initObject(nv);
            tableModel.addRow(obj);
        }
    }//GEN-LAST:event_jcb_trangThaiItemStateChanged

//    public String xuLyMaNV() {
//        //luu nhan vien tam
//        ArrayList<NhanVien> listTmp = nv_Dao.getAllNhanVien();
//        // luu ma so da qua xu ly
//        TreeSet<Integer> listMaNV = new TreeSet();
//        for (NhanVien nv : listTmp) {
//            listMaNV.add(Integer.valueOf((nv.getMaNhanVien()).substring(8)));
//        }
//        int size = listMaNV.getLast() + 1;
//        System.out.println(size);
//        LocalDate ngayVaoLam = LocalDate.now();
//        String maNV = "NV" + ngayVaoLam.getDayOfMonth() + "" + ngayVaoLam.getMonthValue() + "" + (ngayVaoLam.getYear() + "").substring(2);
//        maNV += String.format("%03d", size);
//        return maNV;
//    }

    //check regex
    public boolean checkData() {
        String maNV = jtf_maNhanVien.getText();
        String tenNhanVien = jtf_tenNhanVien.getText();
        String email = jtf_emil.getText();
        String diaChi = jtf_diaChi.getText();
        String sdt = jtf_sdt.getText();
        String cccd = jtf_cccd.getText();
        boolean trangThai = true;
        if (rdb_nghiViec.isSelected()) {
            trangThai = false;
        }
        String regexTenNV = "([A-Z]{1}[a-z]*\\s*)+([A-Z]{1}[a-z]*)*";
        String regexEmailNV = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        String regexSDT = "(84|0[3|5|7|8|9])+([0-9]{8})";
        if (tenNhanVien.equalsIgnoreCase("") || !tenNhanVien.matches(regexTenNV)) {
            JOptionPane.showMessageDialog(this, "Tên nhân viên không được để trống và phải được viết hoa đầu kí tự", "Cảnh báo", JOptionPane.ERROR_MESSAGE);
            jtf_tenNhanVien.requestFocus();
            return false;
        } else if (email.equals("") || !email.matches(regexEmailNV)) {
            JOptionPane.showMessageDialog(this, "Email không được để trống và điền đúng định dạng", "Cảnh báo", JOptionPane.ERROR_MESSAGE);
            jtf_emil.requestFocus();
            return false;

        } else if (sdt.equals("") || !sdt.matches(regexSDT)) {
            JOptionPane.showMessageDialog(this, "SDT không được để trống và điền đúng định dạng của số điện thoại Việt Nam", "Cảnh báo", JOptionPane.ERROR_MESSAGE);
            jtf_sdt.requestFocus();
            return false;

        } else if (cccd.equals("") || cccd.length() != 12) {
            JOptionPane.showMessageDialog(this, "cccd không được để trống và phải đúng 12 chữ số", "Cảnh báo", JOptionPane.ERROR_MESSAGE);
            jtf_cccd.requestFocus();
            return false;

        } else if (!rdb_dangLamViec.isSelected() && !rdb_nghiViec.isSelected()) {
            JOptionPane.showMessageDialog(this, "Không được để trống trạng thái của nhân viên", "Cảnh báo", JOptionPane.ERROR_MESSAGE);
            return false;

        } else if (!ConvertDate.convert(jDate_ngayVaoLam.getDate()).isBefore(LocalDate.now()) && !ConvertDate.convert(jDate_ngayVaoLam.getDate()).isEqual(LocalDate.now())) {
            JOptionPane.showMessageDialog(this, "Ngày vào làm phải trước ngày hiện tại", "Cảnh báo", JOptionPane.ERROR_MESSAGE);
            return false;

        }
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_taiLai;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_xoaTrang;
    private javax.swing.JButton btn_xuatExcel;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser jDate_ngayVaoLam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcb_trangThai;
    private javax.swing.JTextField jtf_cccd;
    private javax.swing.JTextField jtf_diaChi;
    private javax.swing.JTextField jtf_emil;
    private javax.swing.JTextField jtf_maNhanVien;
    private javax.swing.JTextField jtf_sdt;
    private javax.swing.JTextField jtf_tenNhanVien;
    private javax.swing.JTextField jtf_timTheoMa;
    private javax.swing.JTextField jtf_timTheoSDT;
    private javax.swing.JTextField jtf_timTheoTen;
    private javax.swing.JRadioButton rdb_dangLamViec;
    private javax.swing.JRadioButton rdb_nghiViec;
    private javax.swing.JTable tableNV;
    // End of variables declaration//GEN-END:variables

    public void createExcel(ArrayList<NhanVien> listEmp, String filePath) throws IOException {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        SXSSFSheet sheet = workbook.createSheet("Danh sách nhân viên");
        sheet.trackAllColumnsForAutoSizing();
        Row rowNgayIn = sheet.createRow(0);
        Cell cell = rowNgayIn.createCell(0);
        cell.setCellValue("Ngày in: " + LocalDate.now());
        int rowIndex = 1;
        writeHeader(sheet, rowIndex);
        rowIndex = 4;
        for (NhanVien nv : listEmp) {
            SXSSFRow row = sheet.createRow(rowIndex);
            writeEmployee(nv, row);
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
        cell.setCellValue("Mã nhân viên");

        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tên nhân viên");

        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Số điện thoại");

        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Email");

        cell = row.createCell(4);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Địa chỉ");

        cell = row.createCell(5);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("CCCD");

        cell = row.createCell(6);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Trạng thái");

        cell = row.createCell(7);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ngày vào làm");
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
        cell.setCellValue("Số lượng nhân viên: " + nv_Dao.getSize());
    }

    private void writeEmployee(NhanVien nv, SXSSFRow row) {
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
        cell.setCellValue(nv.getMaNhanVien());

        cell = row.createCell(1);
        cell.setCellValue(nv.getTenNhanVien());

        cell = row.createCell(2);
        cell.setCellValue(nv.getSdt());
        cell.setCellStyle(cellStyleFormatNumber);

        cell = row.createCell(3);
        cell.setCellValue(nv.getEmail());

        cell = row.createCell(4);
        cell.setCellValue(nv.getDiaChi());

        cell = row.createCell(5);
        cell.setCellValue(nv.getCccd());

        cell = row.createCell(6);
        if (nv.isTrangThai()) {
            cell.setCellValue("Đang làm việc");

        } else {
            cell.setCellValue("Nghỉ việc");
        }

        cell = row.createCell(7);
        cell.setCellValue(nv.getNgayVaolam());

    }

    private void createOutputFile(SXSSFWorkbook workbook, String excelFilePath) throws FileNotFoundException, IOException, IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
}
