/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import dao.ChiTietDoiTra_DAO;
import dao.DoiTra_DAO;
import entity.ChiTietDoiTra;
import entity.DoiTra;
import entity.HoaDon;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;
import static org.apache.poi.hssf.usermodel.HeaderFooter.file;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import raven.toast.Notifications;
import utilities.FormatNumber;
import utilities.ReturnOrderPrinter;


/**
 *
 * @author HÀ NHƯ
 */
public class DanhSachHoaDonDoiTra_GUI extends javax.swing.JPanel {

    private DefaultTableModel tblModel_returnOrder;
    private DefaultTableModel tblModel_SP;
    private DoiTra currentReturnOrder;
    private DefaultComboBoxModel cmbModel_typeReturnOrder;
    private ArrayList<ChiTietDoiTra> listDetail;
    private DoiTra_DAO doitra_dao;
    private ChiTietDoiTra_DAO ctdt_dao;
    private File file;

    private JPopupMenu popup = new JPopupMenu();
    
    public DanhSachHoaDonDoiTra_GUI() {
        initComponents();
        init();
        updateTotals();
    }
    
     private void init(){
         doitra_dao = new DoiTra_DAO(); 
         ctdt_dao = new ChiTietDoiTra_DAO ();
        //model
        tblModel_returnOrder = new DefaultTableModel(new String[]{"Mã hoá đơn đổi trả", "Mã hoá đơn", "Ngày đổi trả", "Loại", "Tổng tiền"}, 0);
        tbl_returnOrder.setModel(tblModel_returnOrder);
        tblModel_SP = new DefaultTableModel(new String[]{"Mã sản phẩm","Tên sản phẩm", "Số lượng", "Thành tiền"}, 0);
        tbl_SP.setModel(tblModel_SP);
        //combobox
        cmbModel_typeReturnOrder = new DefaultComboBoxModel(new String[]{"Loại", "Đơn đổi", "Đơn trả"});
        cbo_Loai.setModel(cmbModel_typeReturnOrder);
        
        tbl_returnOrder.getSelectionModel().addListSelectionListener((e) -> {
            int rowIndex = tbl_returnOrder.getSelectedRow();
            if(rowIndex == -1)
                return;
            
            String returnOrderID = tblModel_returnOrder.getValueAt(rowIndex, 0).toString();
            this.currentReturnOrder = doitra_dao.getOne(returnOrderID);
            this.listDetail = ctdt_dao.getAllForOrderReturnID(currentReturnOrder.getMaHDDT());
            renderCurrentReturnOrder();
        });
         rdn_Doi.setSelected(true);
         renderReturnOrderTables(doitra_dao.getAll());

    }
    private void renderCurrentReturnOrder() {
        txt_search.setText(currentReturnOrder.getMaHDDT());
        txt_MaHDDT.setText(currentReturnOrder.getMaHDDT());
        txt_MaNV.setText(currentReturnOrder.getNhanvien().getMaNhanVien());
        txt_MaHD.setText(currentReturnOrder.getHoaDon().getMaHD());
        jdate_NgayDT.setDate(currentReturnOrder.getNgayDoiTra());
        
        //0-đổi; 1-trả
        if(currentReturnOrder.isLoai()){
             rdn_Tra.setSelected(true);
             rdn_Doi.setSelected(false);
        }           
        else{
            rdn_Doi.setSelected(true);
            rdn_Tra.setSelected(false);
        }
            
        txt_TienHoan.setText(FormatNumber.toVND(currentReturnOrder.getTienTra()));
        txt_LiDo.setText(currentReturnOrder.getLiDO());
        renderProductTable(currentReturnOrder.getMaHDDT());
    }
    private void renderReturnOrderDetail() {
        txt_MaHDDT.setText("");
        txt_MaNV.setText("");
        txt_MaHD.setText("");
        txt_search.setText("");    
        rdn_Doi.setSelected(true);
        txt_LiDo.setText("");
        txt_TienHoan.setText("");
        tblModel_SP.setRowCount(0);
    }
    private void renderProductTable(String returnOrderID) {
        tblModel_SP.setRowCount(0);  // Xóa tất cả các dòng hiện tại trong bảng
        ArrayList<ChiTietDoiTra> detailList = ctdt_dao.getAllForOrderReturnID(returnOrderID);

        for (ChiTietDoiTra returnOrderDetail : detailList) {
            String maThuoc = returnOrderDetail.getSanPham().getMaThuoc();
            String tenThuoc = returnOrderDetail.getSanPham().getTenThuoc();
            int soLuong = returnOrderDetail.getSoLuong();
            double thanhTien = soLuong * returnOrderDetail.getGia();
            tblModel_SP.addRow(new Object[]{maThuoc, tenThuoc, soLuong, thanhTien});
        }
    }
    
    private void renderReturnOrderTables(ArrayList<DoiTra> allReturnOrder) {
        tblModel_returnOrder.setRowCount(0);
        String loai;
        for (DoiTra returnOrder : allReturnOrder) {
            if(returnOrder.isLoai())
                loai = "Đơn trả";
            else 
                loai = "Đơn đổi";
   
            String[] newRow = {returnOrder.getMaHDDT(), returnOrder.getHoaDon().getMaHD(), returnOrder.getNgayDoiTra().toString(), loai, returnOrder.getTienTra() + ""};
            tblModel_returnOrder.addRow(newRow);
        }
    }

    private DoiTra getNewValue() {
        return currentReturnOrder;
    }

    private void Print() {
        ReturnOrderPrinter printer = new ReturnOrderPrinter(getNewValue());
        printer.generatePDF();
        ReturnOrderPrinter.PrintStatus status = printer.printFile();
        if (status == ReturnOrderPrinter.PrintStatus.NOT_FOUND_FILE) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi không thể in hóa đơn: Không tìm thấy file");
        } else if (status == ReturnOrderPrinter.PrintStatus.NOT_FOUND_PRINTER) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi không thể in hóa đơn: Không tìm thấy máy in");
        }
    }

    public void exportReturnedAndExchangedDrugs() {
    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet("Danh sách thuốc bị đổi và trả");

    Row titleRow = sheet.createRow(0);  
    Cell titleCell = titleRow.createCell(0);
    titleCell.setCellValue("DANH SÁCH CÁC SẢN PHẨM BỊ ĐỔI TRẢ");

    Row dateRow = sheet.createRow(1);  
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    String currentDate = sdf.format(new Date());  

    Cell dateCell = dateRow.createCell(0);
    dateCell.setCellValue("Ngày xuất file: " + currentDate);

    Row headerRow = sheet.createRow(2);
    String[] headers = {"Mã Thuốc", "Tên Thuốc", "Số Lượng", "Giá", "Ngày Đổi Trả", "Loại Đơn", "Lý Do"};

    for (int i = 0; i < headers.length; i++) {
        Cell cell = headerRow.createCell(i);
        cell.setCellValue(headers[i]);
    }

    ArrayList<ChiTietDoiTra> returnedAndExchangedDrugs = ctdt_dao.getReturnedAndExchangedDrugs();

    int rowNum = 3; 
    for (ChiTietDoiTra thuoc : returnedAndExchangedDrugs) {
        Row row = sheet.createRow(rowNum++);

        row.createCell(0).setCellValue(thuoc.getSanPham().getMaThuoc());
        row.createCell(1).setCellValue(thuoc.getSanPham().getTenThuoc());
        row.createCell(2).setCellValue(thuoc.getSoLuong());
        row.createCell(3).setCellValue(thuoc.getGia());
        if (thuoc.getReturnOrder().getNgayDoiTra() != null) {
            row.createCell(4).setCellValue(thuoc.getReturnOrder().getNgayDoiTra().toString());
        }
        row.createCell(5).setCellValue(thuoc.getDoiTra().isLoai() ? "Trả" : "Đổi");
        row.createCell(6).setCellValue(thuoc.getReturnOrder().getLiDO()); // Thêm lý do
    }

    // Lưu file Excel
    File file = new File("danh_sach_thuoc_bi_doi_va_tra.xlsx");
    try (FileOutputStream fileOut = new FileOutputStream(file)) {
        workbook.write(fileOut);
        Notifications.getInstance().show(Notifications.Type.SUCCESS, "Dữ liệu đã được xuất ra file Excel thành công!");
        if (file.exists()) {
            Notifications.getInstance().show(Notifications.Type.SUCCESS, "File đã được lưu tại: " + file.getAbsolutePath());
         
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);  
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR,"File không tồn tại.");
        }
    } catch (IOException e) {
        e.printStackTrace();
        Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi khi xuất dữ liệu ra file Excel.");
    } finally {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


    public void updateTotals() {
        DoiTra totals = doitra_dao.calculateTotals();
        double totalAmount = totals.getTtdt();

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String formattedAmount = currencyFormat.format(totalAmount);

        lbl_TongTien.setText(formattedAmount);
        lbl_SLDD.setText(totals.getSldd() + "");
        lbl_SLDT.setText(totals.getSldt()+"");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txt_search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        cbo_Loai = new javax.swing.JComboBox<>();
        btn_Loc = new javax.swing.JButton();
        btn_Reset = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_SP = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_MaNV = new javax.swing.JTextField();
        txt_MaHD = new javax.swing.JTextField();
        txt_MaHDDT = new javax.swing.JTextField();
        jdate_NgayDT = new com.toedter.calendar.JDateChooser();
        txt_TienHoan = new javax.swing.JTextField();
        txt_LiDo = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btn_excel = new javax.swing.JButton();
        btn_print = new javax.swing.JButton();
        rdn_Doi = new javax.swing.JRadioButton();
        rdn_Tra = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_returnOrder = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lbl_TongTien = new javax.swing.JLabel();
        lbl_SLDD = new javax.swing.JLabel();
        lbl_SLDT = new javax.swing.JLabel();

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_searchActionPerformed(evt);
            }
        });
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_searchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });
        jPanel2.add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 298, 39));

        btn_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nhanvien/circle.png"))); // NOI18N
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });
        jPanel2.add(btn_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 90, 40));

        cbo_Loai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbo_Loai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Loại", "Đơn đổi", "Đơn trả" }));
        cbo_Loai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_LoaiActionPerformed(evt);
            }
        });
        jPanel2.add(cbo_Loai, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 170, 40));

        btn_Loc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nhanvien/funnel.png"))); // NOI18N
        btn_Loc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LocActionPerformed(evt);
            }
        });
        jPanel2.add(btn_Loc, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 10, 80, 40));

        btn_Reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nhanvien/refresh.png"))); // NOI18N
        btn_Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ResetActionPerformed(evt);
            }
        });
        jPanel2.add(btn_Reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 10, 90, 40));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel10.setText("Nhập mã HDDT :");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 160, 20));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết đơn đổi trả", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Mã nhân viên:");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 108, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Mã hóa đơn:");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 76, 108, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Mã đơn đổi trả:");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 122, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Ngày đổi trả:");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 169, 108, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Loại:");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 211, 93, 33));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Sản phẩm:");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 256, -1, -1));

        tbl_SP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Thành tiền"
            }
        ));
        tbl_SP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_SPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_SP);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 284, 529, 116));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setText("Tiền hoàn");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("Lý do");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, -1, -1));

        txt_MaNV.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txt_MaNV.setEnabled(false);
        jPanel4.add(txt_MaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 27, 390, -1));

        txt_MaHD.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txt_MaHD.setEnabled(false);
        jPanel4.add(txt_MaHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 73, 390, -1));

        txt_MaHDDT.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txt_MaHDDT.setEnabled(false);
        jPanel4.add(txt_MaHDDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 119, 390, -1));

        jdate_NgayDT.setEnabled(false);
        jPanel4.add(jdate_NgayDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 169, 390, 30));

        txt_TienHoan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txt_TienHoan.setEnabled(false);
        jPanel4.add(txt_TienHoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 420, 380, -1));

        txt_LiDo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txt_LiDo.setEnabled(false);
        jPanel4.add(txt_LiDo, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 460, 380, -1));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 33, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(385, 211, -1, -1));

        btn_excel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nhanvien/logo.png"))); // NOI18N
        btn_excel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excelActionPerformed(evt);
            }
        });
        jPanel4.add(btn_excel, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 510, 200, 40));

        btn_print.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btn_print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nhanvien/printer.png"))); // NOI18N
        btn_print.setText("In hóa đơn");
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });
        jPanel4.add(btn_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(334, 510, 180, 40));

        rdn_Doi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rdn_Doi.setText("Đổi");
        rdn_Doi.setEnabled(false);
        jPanel4.add(rdn_Doi, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, 211, -1, -1));

        rdn_Tra.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rdn_Tra.setText("Trả");
        rdn_Tra.setEnabled(false);
        rdn_Tra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdn_TraActionPerformed(evt);
            }
        });
        jPanel4.add(rdn_Tra, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 211, -1, -1));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(696, 0, 570, 570));

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_returnOrder.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tbl_returnOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn đổi trả", "Mã hóa đơn", "Ngày đổi trả", "Loại", "Tổng tiền"
            }
        ));
        jScrollPane1.setViewportView(tbl_returnOrder);

        jPanel6.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 682, 475));

        jLabel5.setBackground(new java.awt.Color(204, 204, 204));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setText("Số lượng đơn trả: ");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 490, -1, 30));

        jLabel11.setBackground(new java.awt.Color(204, 204, 204));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel11.setText("Tổng tiền: ");
        jPanel6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 490, -1, 30));

        jLabel12.setBackground(new java.awt.Color(204, 204, 204));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel12.setText("Số lượng đơn đổi: ");
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 530, -1, 40));

        lbl_TongTien.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jPanel6.add(lbl_TongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 490, 170, 30));

        lbl_SLDD.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jPanel6.add(lbl_SLDD, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 540, 170, 20));

        lbl_SLDT.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jPanel6.add(lbl_SLDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 490, 170, 30));

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, -1, 570));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 3, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
      
    }//GEN-LAST:event_txt_searchActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        String returnOrderID = txt_search.getText();
        if (txt_search.equals("")) {
            Notifications.getInstance().show(Notifications.Type.INFO, "Vui lòng nhập mã hoá đơn cần tìm");
            return;
        }
        renderReturnOrderTables(doitra_dao.findById(returnOrderID));
    }//GEN-LAST:event_btn_searchActionPerformed

    private void cbo_LoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_LoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_LoaiActionPerformed

    private void btn_excelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excelActionPerformed
        exportReturnedAndExchangedDrugs();
    }//GEN-LAST:event_btn_excelActionPerformed

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        Print();
    }//GEN-LAST:event_btn_printActionPerformed

    private void rdn_TraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdn_TraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdn_TraActionPerformed

    private void btn_LocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LocActionPerformed
        int type = cbo_Loai.getSelectedIndex();  
        renderReturnOrderTables(doitra_dao.filter(type));  

    }//GEN-LAST:event_btn_LocActionPerformed

    private void btn_ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ResetActionPerformed
        renderReturnOrderTables(doitra_dao.getAll());
        renderReturnOrderDetail();
    }//GEN-LAST:event_btn_ResetActionPerformed

    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String returnOrderID = txt_search.getText();
            if(txt_search.equals("")) {
                Notifications.getInstance().show(Notifications.Type.INFO, "Vui lòng nhập mã hoá đơn cần tìm");
                return;
            }
            renderReturnOrderTables(doitra_dao.findById(returnOrderID));
        }
    }//GEN-LAST:event_txt_searchKeyPressed

    private void tbl_SPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_SPMouseClicked
        int selectedRow = tbl_returnOrder.getSelectedRow();
        if (selectedRow != -1) {
            String returnOrderID = tbl_returnOrder.getValueAt(selectedRow, 0).toString(); 
            renderProductTable(returnOrderID); 
        }
    }//GEN-LAST:event_tbl_SPMouseClicked

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
       
    }//GEN-LAST:event_txt_searchKeyReleased
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Loc;
    private javax.swing.JButton btn_Reset;
    private javax.swing.JButton btn_excel;
    private javax.swing.JButton btn_print;
    private javax.swing.JButton btn_search;
    private javax.swing.JComboBox<String> cbo_Loai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdate_NgayDT;
    private javax.swing.JLabel lbl_SLDD;
    private javax.swing.JLabel lbl_SLDT;
    private javax.swing.JLabel lbl_TongTien;
    private javax.swing.JRadioButton rdn_Doi;
    private javax.swing.JRadioButton rdn_Tra;
    private javax.swing.JTable tbl_SP;
    private javax.swing.JTable tbl_returnOrder;
    private javax.swing.JTextField txt_LiDo;
    private javax.swing.JTextField txt_MaHD;
    private javax.swing.JTextField txt_MaHDDT;
    private javax.swing.JTextField txt_MaNV;
    private javax.swing.JTextField txt_TienHoan;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
