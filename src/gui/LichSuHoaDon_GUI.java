/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;
import javax.swing.table.DefaultTableModel;
import utilities.ConvertDate;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import raven.toast.Notifications;
import utilities.FormatNumber;

/**
 *
 * @author HÀ NHƯ
 */
public class LichSuHoaDon_GUI extends javax.swing.JPanel {

    private ArrayList<HoaDon> listHD;
    private DefaultTableModel modelHD;
    ArrayList<ChiTietHoaDon> listCTHD;
    private DefaultTableModel modelCTHD;
    private ArrayList<HoaDon> current;

    public LichSuHoaDon_GUI() {
        initComponents();
        listHD = new ArrayList<>();
        listCTHD = new ArrayList<>();
        current = new ArrayList<>();
        modelHD = new DefaultTableModel(new String[]{"Mã hóa đơn", "Tên nhân viên", "Số điện thoại khách hàng", "Ngày mua", "Thành tiền"}, 0);
        modelCTHD = new DefaultTableModel(new String[]{"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"}, 0);
        table_DanhSachHoaDon.setModel(modelHD);
        table_ChiTietHoaDon.setModel(modelCTHD);
        initHoaDon();
    }

    public void initHoaDon() {
        listHD = new HoaDon_DAO().getAllHoaDon();
        for (HoaDon hd : listHD) {
            Object[] row = initObjectHD(hd);
            modelHD.addRow(row);
        }

    }

    public Object[] initObjectHD(HoaDon hd) {
        Object[] obj = new Object[5];
        obj[0] = hd.getMaHD();
        obj[1] = hd.getNhanVien().getTenNhanVien();
        obj[2] = hd.getKhachHang().getSdt();
        obj[3] = hd.getNgayLap();
        obj[4] = hd.getTongTien();
        return obj;

    }

    public Object[] initObjectCTHD(ChiTietHoaDon cthd) {
        Object[] obj = new Object[5];
        obj[0] = cthd.getThuoc().getMaThuoc();
        obj[1] = cthd.getThuoc().getTenThuoc();
        obj[2] = cthd.getSoLuong();
        obj[3] = cthd.getDonGia();
        obj[4] = cthd.thanhTien();
        return obj;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_maHoaDon = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_timSDT = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jDate_batDau = new com.toedter.calendar.JDateChooser();
        jDate_ketThuc = new com.toedter.calendar.JDateChooser();
        btn_timKiem = new javax.swing.JButton();
        btn_load = new javax.swing.JButton();
        xuatExcel = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jcb_doanhThu = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_DanhSachHoaDon = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_ChiTietHoaDon = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txt_TenKH = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_SoDienThoai = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_TongTien = new javax.swing.JTextField();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Mã hóa đơn");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 19, 119, 23));
        jPanel1.add(txt_maHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 11, 214, 28));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Số điện thoại KH");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 69, 130, 29));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Tổng doanh thu");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(376, 19, 119, -1));

        txt_timSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timSDTActionPerformed(evt);
            }
        });
        jPanel1.add(txt_timSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 69, 214, 29));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Từ ngày");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(756, 19, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Đến ngày");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(756, 78, -1, 29));

        jDate_batDau.setPreferredSize(new java.awt.Dimension(214, 22));
        jPanel1.add(jDate_batDau, new org.netbeans.lib.awtextra.AbsoluteConstraints(838, 10, 208, 31));
        jPanel1.add(jDate_ketThuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 70, 210, 37));

        btn_timKiem.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btn_timKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nhanvien/funnel.png"))); // NOI18N
        btn_timKiem.setText("Tìm kiếm");
        btn_timKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timKiemActionPerformed(evt);
            }
        });
        jPanel1.add(btn_timKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 9, 192, 32));

        btn_load.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reload_employee.png"))); // NOI18N
        btn_load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loadActionPerformed(evt);
            }
        });
        jPanel1.add(btn_load, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 67, 87, 41));

        xuatExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nhanvien/logo.png"))); // NOI18N
        xuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xuatExcelActionPerformed(evt);
            }
        });
        jPanel1.add(xuatExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1155, 67, 87, 41));
        jPanel1.add(filler2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1248, 27, -1, 1));

        jcb_doanhThu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Trên 100.000 VND", "Trên 200.000 VND", "Trên 500.000 VND" }));
        jPanel1.add(jcb_doanhThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(524, 10, 214, 31));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        table_DanhSachHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Tên nhân viên", "Mã khách hàng", "Ngày mua", "Thành tiền"
            }
        ));
        table_DanhSachHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_DanhSachHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_DanhSachHoaDon);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        table_ChiTietHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane2.setViewportView(table_ChiTietHoaDon);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Tên khách hàng");

        txt_TenKH.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setText("Số điện thoại");

        txt_SoDienThoai.setEnabled(false);
        txt_SoDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SoDienThoaiActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("Tổng tiền");

        txt_TongTien.setEnabled(false);
        txt_TongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TongTienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(296, 296, 296)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(302, 302, 302))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(38, 38, 38))
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_SoDienThoai)
                    .addComponent(txt_TongTien)
                    .addComponent(txt_TenKH))
                .addGap(45, 45, 45))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_TenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addGap(6, 6, 6)))
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addGap(8, 8, 8))
                    .addComponent(txt_SoDienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addGap(7, 7, 7))
                    .addComponent(txt_TongTien, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addGap(39, 39, 39)
                .addComponent(filler1, javax.swing.GroupLayout.DEFAULT_SIZE, 4, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
            .addComponent(jScrollPane2)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(23, 23, 23)))
                .addGap(7, 7, 7))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_SoDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SoDienThoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SoDienThoaiActionPerformed

    private void txt_TongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TongTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TongTienActionPerformed

    private void btn_loadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loadActionPerformed
        modelHD.setRowCount(0);
        initHoaDon();
    }//GEN-LAST:event_btn_loadActionPerformed

    private void btn_timKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timKiemActionPerformed
        modelHD.setNumRows(0);
        String maHoaDon = txt_maHoaDon.getText();
        String sdt = txt_timSDT.getText();
        double doanhThu = 0;
        if (jcb_doanhThu.getSelectedIndex() == 1) {
            doanhThu = 100000;
        } else if (jcb_doanhThu.getSelectedIndex() == 2) {
            doanhThu = 200000;
        } else if (jcb_doanhThu.getSelectedIndex() == 3) {
            doanhThu = 500000;
        }
        LocalDate ngayBatDau = ConvertDate.convert(jDate_batDau.getDate());
        LocalDate ngayKetThuc = ConvertDate.convert(jDate_ketThuc.getDate());
        if (ngayBatDau.isAfter(ngayKetThuc)) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Ngày bắt đầu phải trước ngày kết thúc");
            return;
        }

        ArrayList<HoaDon> filter = new HoaDon_DAO().filter(maHoaDon, sdt, doanhThu, ngayBatDau, ngayKetThuc);
        System.out.println(filter);
        if (filter.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Không tìm thấy hóa đơn!");
            return;
        }
        for (HoaDon hd : filter) {
            Object[] obj = initObjectHD(hd);
            modelHD.addRow(obj);
        }

    }//GEN-LAST:event_btn_timKiemActionPerformed

    private void table_DanhSachHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_DanhSachHoaDonMouseClicked
        modelCTHD.setRowCount(0);
        int index = table_DanhSachHoaDon.getSelectedRow();
        listCTHD = new HoaDon_DAO().getChiTietHoaDon(table_DanhSachHoaDon.getValueAt(index, 0) + "");
        for (ChiTietHoaDon cthd : listCTHD) {
            Object[] obj = initObjectCTHD(cthd);
            modelCTHD.addRow(obj);
        }
        KhachHang kh = new HoaDon_DAO().getHoaDon(table_DanhSachHoaDon.getValueAt(index, 0) + "").getKhachHang();
        txt_SoDienThoai.setText(kh.getSdt());
        txt_TenKH.setText(kh.getTenKhachHang());
        txt_TongTien.setText(table_DanhSachHoaDon.getValueAt(index, 4) + "");

    }//GEN-LAST:event_table_DanhSachHoaDonMouseClicked

    public static void createExcel(ArrayList<HoaDon> list, String filePath) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Order Data");

        // Gộp ô cho tiêu đề
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

        // Thêm dòng thông tin đầu tiên
        Row infoRow = sheet.createRow(0);
        Cell infoCell = infoRow.createCell(0);
        infoCell.setCellValue("Danh sách hoá đơn");

        // Thiết lập style cho phần tiêu đề
        CellStyle titleStyle = workbook.createCellStyle();
        Font titleFont = (Font) workbook.createFont();
        titleFont.setFontHeightInPoints((short) 18);
        titleStyle.setFont((org.apache.poi.ss.usermodel.Font) titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        infoCell.setCellStyle(titleStyle);

        Row row_date = sheet.createRow(1);
        Cell cell_date = row_date.createCell(0);
        cell_date.setCellValue("Ngày in: " + new Date());

        // Tạo header row
        Row headerRow = sheet.createRow(2);
        String[] columns = {"Mã hoá đơn", " Mã nhân viên", "Tên khách hàng", " Ngày mua hàng", "Số điện thoại khách hàng", "Tổng tiền"};

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // Đổ dữ liệu từ ArrayList vào Excel
        int rowNum = 3;
        for (HoaDon order : list) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(order.getMaHD());
            row.createCell(1).setCellValue(order.getNhanVien().getMaNhanVien());
            row.createCell(2).setCellValue(order.getKhachHang().getTenKhachHang());
            row.createCell(3).setCellValue(order.getNgayLap().toString());

            row.createCell(4).setCellValue(order.getKhachHang().getSdt());
            row.createCell(5).setCellValue(FormatNumber.toVND(order.getTongTien()));
        }

        // Ghi vào file
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
            Notifications.getInstance().show(Notifications.Type.SUCCESS, "Đã tạo file thành công !");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void txt_timSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timSDTActionPerformed

    private void xuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xuatExcelActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn đường dẫn và tên file");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        String priceFrom, priceTo;

            if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xuất toàn bộ hoá đơn ?", "Xuất file excel", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                int userSelection = fileChooser.showSaveDialog(null);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    // Lấy đường dẫn và tên file được chọn
                    File fileToSave = fileChooser.getSelectedFile();
                    String filePath = fileToSave.getAbsolutePath();

                    // Gọi phương thức để tạo file Excel với đường dẫn và tên file đã chọn
                    createExcel(new HoaDon_DAO().getAllHoaDon(), filePath + ".xlsx");
            }
        }
    }//GEN-LAST:event_xuatExcelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_load;
    private javax.swing.JButton btn_timKiem;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private com.toedter.calendar.JDateChooser jDate_batDau;
    private com.toedter.calendar.JDateChooser jDate_ketThuc;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jcb_doanhThu;
    private javax.swing.JTable table_ChiTietHoaDon;
    private javax.swing.JTable table_DanhSachHoaDon;
    private javax.swing.JTextField txt_SoDienThoai;
    private javax.swing.JTextField txt_TenKH;
    private javax.swing.JTextField txt_TongTien;
    private javax.swing.JTextField txt_maHoaDon;
    private javax.swing.JTextField txt_timSDT;
    private javax.swing.JButton xuatExcel;
    // End of variables declaration//GEN-END:variables
}
