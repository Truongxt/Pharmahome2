
package gui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLightLaf;
import com.mallowigi.idea.utils.MTUI.Spinner;
import dao.ChiTietDoiTra_DAO;
import dao.ChiTietHoaDon_DAO;
import dao.DoiTra_DAO;
import dao.HoaDon_DAO;
import dao.NhanVien_DAO;
import dao.Thuoc_DAO;
import entity.ChiTietDoiTra;
import entity.ChiTietHoaDon;
import entity.DoiTra;
import entity.HoaDon;
import entity.NhanVien;
import entity.TaiKhoan;
import entity.Thuoc;
import enums.TrangThaiDoiTra;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import raven.toast.Notifications;
import utilities.FormatNumber;
import utilities.ReturnOrderPrinter;

/**
 *
 * @author HÀ NHƯ
 */

public class DoiTra_GUI extends javax.swing.JPanel {

    private ArrayList<ChiTietHoaDon> cart;
    private ChiTietHoaDon cthd;
    private DefaultTableModel tblModel_HD;
    private DefaultTableModel tblModel_SP;
    private HoaDon_DAO hd_DAO;
    private int maxSoLuong;
    private double tongTienTra;
    private HoaDon hoaDon;
    private TaiKhoan tk;
    private NhanVien nv;
    ArrayList<ChiTietHoaDon> listSPHoan;
    ArrayList<ChiTietDoiTra> listDoiTra;
    private DoiTra doiTra;  
    private int maxQuantity;
    private double totalRefund;
    private boolean isUpdating = false;
    private JPopupMenu popup = new JPopupMenu();
    private boolean isSelectingFromPopup = false; 
    private HoaDon hd;
    private  double refund = 0;

    public DoiTra_GUI(TaiKhoan tk) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(DoiTra_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        try {
            connect.ConnectDB.connect();
        } catch (SQLException ex) {
            Logger.getLogger(DoiTra_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.tk = tk;
        cart = new ArrayList<>();
        hd_DAO = new HoaDon_DAO();
        listSPHoan = new ArrayList<>();
        listDoiTra = new ArrayList<>();

        txtMaHDDT.setText(TaoID());
        initNhanVien();
        doiTra = new DoiTra(txtMaHDDT.getText());
        buttonGroup1.add(rdn_Doi);
        buttonGroup1.add(rdn_Tra);
    }

    public void initNhanVien() {
        this.nv = new NhanVien_DAO().getNhanVien(tk.getNhanVien().getMaNhanVien());
        txtMaNV.setText(this.nv.getMaNhanVien());
        txtTenNV.setText(this.nv.getTenNhanVien());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        txt_MaHD = new javax.swing.JTextField();
        btn_searchReturnOrder = new javax.swing.JButton();
        btn_barcode = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_HD = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        lbl_MaHDDT = new javax.swing.JLabel();
        txtMaHDDT = new javax.swing.JTextField();
        lbl_MaHD = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        lbl_NgayDT = new javax.swing.JLabel();
        lbl_BangSanPham = new javax.swing.JLabel();
        lbl_TienHoan = new javax.swing.JLabel();
        lbl_LyDo = new javax.swing.JLabel();
        txt_soTienTra = new javax.swing.JTextField();
        txt_MoTa = new javax.swing.JTextField();
        btn_XoaTrang = new javax.swing.JButton();
        btn_Them = new javax.swing.JButton();
        btn_TaoHDDT = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        lbl_LoaiDT = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_SanPham = new javax.swing.JTable();
        jdatechooser_return = new com.toedter.calendar.JDateChooser();
        button_group = new javax.swing.JPanel();
        rdn_Doi = new javax.swing.JRadioButton();
        rdn_Tra = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        lbl_MaNV = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        lbl_TenNV = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();

        txt_MaHD.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Nhập mã hóa đơn");
        txt_MaHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_MaHDMouseClicked(evt);
            }
        });
        txt_MaHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MaHDActionPerformed(evt);
            }
        });
        txt_MaHD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_MaHDKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_MaHDKeyReleased(evt);
            }
        });

        btn_searchReturnOrder.setBackground(new java.awt.Color(102, 153, 255));
        btn_searchReturnOrder.setText("Tìm kiếm");
        btn_searchReturnOrder.setMaximumSize(new java.awt.Dimension(79, 43));
        btn_searchReturnOrder.setPreferredSize(new java.awt.Dimension(90, 50));
        btn_searchReturnOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchReturnOrderActionPerformed(evt);
            }
        });

        btn_barcode.setBackground(new java.awt.Color(0, 153, 51));
        btn_barcode.setForeground(new java.awt.Color(255, 255, 255));
        btn_barcode.setText("Barcode");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(txt_MaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_searchReturnOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(btn_barcode, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_barcode, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_MaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_searchReturnOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        table_HD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Tổng tiền"
            }
        ));
        jScrollPane1.setViewportView(table_HD);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin đổi trả", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_MaHDDT.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbl_MaHDDT.setText("Mã HDDT:");
        jPanel3.add(lbl_MaHDDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 119, 30));

        txtMaHDDT.setEnabled(false);
        jPanel3.add(txtMaHDDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 32, 310, 30));

        lbl_MaHD.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbl_MaHD.setText("Mã hóa đơn:");
        jPanel3.add(lbl_MaHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 119, 30));
        jPanel3.add(txtMaHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 70, 310, 30));

        lbl_NgayDT.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbl_NgayDT.setText("Ngày đổi trả:");
        jPanel3.add(lbl_NgayDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 119, 30));

        lbl_BangSanPham.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbl_BangSanPham.setText("Sản phẩm:");
        jPanel3.add(lbl_BangSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 119, 30));

        lbl_TienHoan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbl_TienHoan.setText("Tiền hoàn:");
        jPanel3.add(lbl_TienHoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 80, 20));

        lbl_LyDo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbl_LyDo.setText("Lý do:");
        jPanel3.add(lbl_LyDo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 70, 30));

        txt_soTienTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_soTienTraActionPerformed(evt);
            }
        });
        jPanel3.add(txt_soTienTra, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 400, 300, 30));
        jPanel3.add(txt_MoTa, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 440, 300, 30));

        btn_XoaTrang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btn_XoaTrang.setText("Xóa trắng");
        btn_XoaTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaTrangActionPerformed(evt);
            }
        });
        jPanel3.add(btn_XoaTrang, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, -1, 40));

        btn_Them.setBackground(new java.awt.Color(0, 153, 102));
        btn_Them.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btn_Them.setText("Thêm sp");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });
        jPanel3.add(btn_Them, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 500, -1, 40));

        btn_TaoHDDT.setBackground(new java.awt.Color(102, 153, 255));
        btn_TaoHDDT.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btn_TaoHDDT.setText("Tạo đơn đổi trả");
        btn_TaoHDDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TaoHDDTActionPerformed(evt);
            }
        });
        jPanel3.add(btn_TaoHDDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 500, -1, 40));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("Sản phẩm:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 119, 30));

        lbl_LoaiDT.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbl_LoaiDT.setText("Loại:");
        jPanel3.add(lbl_LoaiDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jScrollPane2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jScrollPane2PropertyChange(evt);
            }
        });

        table_SanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng"
            }
        ));
        table_SanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_SanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_SanPham);

        jScrollPane3.setViewportView(jScrollPane2);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 450, 170));
        jPanel3.add(jdatechooser_return, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 112, 310, 30));

        button_group.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_groupMouseClicked(evt);
            }
        });

        rdn_Doi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rdn_Doi.setText("Đổi");
        rdn_Doi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdn_DoiActionPerformed(evt);
            }
        });

        rdn_Tra.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rdn_Tra.setText("Trả");
        rdn_Tra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdn_TraActionPerformed(evt);
            }
        });

        rdn_Doi.setSelected(true);

        javax.swing.GroupLayout button_groupLayout = new javax.swing.GroupLayout(button_group);
        button_group.setLayout(button_groupLayout);
        button_groupLayout.setHorizontalGroup(
            button_groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(button_groupLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(rdn_Tra)
                .addGap(59, 59, 59)
                .addComponent(rdn_Doi)
                .addContainerGap(141, Short.MAX_VALUE))
        );
        button_groupLayout.setVerticalGroup(
            button_groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(button_groupLayout.createSequentialGroup()
                .addGroup(button_groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdn_Tra)
                    .addComponent(rdn_Doi))
                .addGap(0, 3, Short.MAX_VALUE))
        );

        jPanel3.add(button_group, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 310, 30));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        lbl_MaNV.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbl_MaNV.setText("Mã nhân viên: ");

        txtMaNV.setEnabled(false);

        lbl_TenNV.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbl_TenNV.setText("Tên nhân viên: ");

        txtTenNV.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbl_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMaNV))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbl_TenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTenNV)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_TenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    private void init() {
        hoaDon = hd_DAO.getHoaDon(txt_MaHD.getText());
        System.out.println(txt_MaHD.getText());
        //set txt ma hoa don
        txtMaHD.setText(hoaDon.getMaHD());
        cart = hd_DAO.getChiTietHoaDon(txt_MaHD.getText());
        //model
        System.out.println(cart);
        tblModel_HD = new DefaultTableModel(new String[]{"Mã hoá đơn", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Tổng tiền"}, 0);
        table_HD.setModel(tblModel_HD);

        for (ChiTietHoaDon cthd : cart) {
            Object[] obj = initObject(cthd);
            tblModel_HD.addRow(obj);
        }

        tblModel_SP = new DefaultTableModel(new String[]{"Mã sản phẩm", "Tên sản phẩm", "Số lượng"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };
         // Thêm listener vào TableModel của JTable
        tblModel_SP.addTableModelListener(new javax.swing.event.TableModelListener() {
            @Override
            public void tableChanged(javax.swing.event.TableModelEvent e) {
                updateTienTra();
            }
        });
        table_SanPham.setModel(tblModel_SP);
        suaSoLuong();
    }

    private void renderCartTable() {

       
        tblModel_SP.setRowCount(0);
        for (ChiTietHoaDon item : cart) {
            Object[] newRow = new Object[]{item.getThuoc().getMaThuoc(), item.getThuoc().getTenThuoc(), item.getSoLuong()};
            tblModel_SP.addRow(newRow);
            refund += item.getDonGia() * item.getSoLuong();
            // return;
        }

        totalRefund = refund;
        double tienDoi = 0;
        if (rdn_Doi.isSelected()) {
            txt_soTienTra.setText(FormatNumber.toVND(tienDoi));
        }
        txt_soTienTra.setText(FormatNumber.toVND(refund));

    }

    public void suaSoLuong() {
        table_SanPham.getModel().addTableModelListener((evt) -> {
            if (isUpdating) {
                return;
            }
            int row = evt.getFirstRow();
            int col = evt.getColumn();
            if (row == -1 || col == -1 || col != 2) {
                return;
            }

            try {
                int soLuongMoi = Integer.parseInt(table_SanPham.getValueAt(row, col) + "");
                int soLuongCu = Integer.parseInt(table_HD.getValueAt(row, 3) + "");
                ChiTietHoaDon current = cart.get(row);


                if (soLuongMoi < 0) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, "Số lượng không được bé hơn 0");
                    table_SanPham.setValueAt(current.getSoLuong(), row, col);
                    return;
                }
                if (soLuongMoi == 0) {
                    if (JOptionPane.showConfirmDialog(this, "Xóa sản phẩm " + current.getThuoc().getMaThuoc() + " ra khỏi giỏ hàng", "Xóa sản phẩm khỏi giỏ", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        cart.remove(current);
                        renderCartTable();
                        return;
                    } else {
                        table_SanPham.setValueAt(current.getSoLuong(), row, col);
                        return;
                    }
                }

                if (soLuongMoi > soLuongCu) {
                    // Update new quantity
                    Notifications.getInstance().show(Notifications.Type.WARNING, "Số lượng đổi không hợp lệ");
                    table_SanPham.setValueAt(current.getSoLuong(), row, col);
                    return;
                } 
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Notifications.getInstance().show(Notifications.Type.INFO, "Số lượng không hợp lệ");
            } catch (Exception ex) {
                ex.printStackTrace();
                Notifications.getInstance().show(Notifications.Type.ERROR, "Không thể cập nhật số lượng mới!");
            }
        });
        
        
    }


   
    private void renderOrderDetail(String maHD) {
        try {
             List<ChiTietHoaDon> chiTietHoaDonList = hd_DAO.getChiTietHoaDon(maHD);

            if (chiTietHoaDonList == null || chiTietHoaDonList.isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.INFO, "Không có sản phẩm nào trong hóa đơn này.");
                return;
            }
            tblModel_HD.setRowCount(0);

            for (ChiTietHoaDon chiTiet : chiTietHoaDonList) {
                Object[] rowData = new Object[]{
                    chiTiet.getHoaDon().getMaHD(),
                    chiTiet.getThuoc().getMaThuoc(), 
                    chiTiet.getThuoc().getTenThuoc(), 
                    chiTiet.getSoLuong(), 
                    FormatNumber.toVND(chiTiet.getDonGia()), 
                    FormatNumber.toVND(chiTiet.thanhTien())
                };
                tblModel_HD.addRow(rowData);
            }
            Notifications.getInstance().show(Notifications.Type.SUCCESS, "Hiển thị chi tiết hóa đơn thành công.");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public Object[] initObjectSP(Thuoc t, int soLuong, int stt) {
        Object[] obj = new Object[5];
        obj[0] = stt;
        obj[1] = t.getTenThuoc();
        obj[2] = soLuong;
        obj[3] = t.getGia();
       obj[4] = new ChiTietHoaDon(soLuong, t.getGia(), t, hd).thanhTien();
        return obj;
    }
    public Object[] initObject(ChiTietHoaDon item) {
        Object[] obj = new Object[6];
        obj[0] = hoaDon.getMaHD();
        obj[1] = item.getThuoc().getMaThuoc();
        obj[2] = item.getThuoc().getTenThuoc();
        obj[3] = item.getSoLuong();
        obj[4] = item.getDonGia();
        obj[5] = item.thanhTien();
        return obj;

    }


    private void btn_searchReturnOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchReturnOrderActionPerformed
        String maHoaDon = txt_MaHD.getText().trim();  
        if (maHoaDon.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Mã hóa đơn không được để trống!");
            return;
        }
        DoiTra_DAO dt_dao = new DoiTra_DAO();
        if (dt_dao.isReturnOrderExist(maHoaDon)) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Hóa đơn này đã thực hiện đổi trả, không thể tìm kiếm!");
            return;  
        }

        init();

    }//GEN-LAST:event_btn_searchReturnOrderActionPerformed

    private void txt_MaHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MaHDActionPerformed

    }//GEN-LAST:event_txt_MaHDActionPerformed

    private void btn_TaoHDDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TaoHDDTActionPerformed

        if (hoaDon == null) {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Vui lòng chọn hóa đơn để đổi trả");
            txt_MaHD.requestFocus();
            return;
        }

        if (tblModel_SP.getRowCount() == 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Chưa chọn sản phẩm để đổi trả");
            return;
        }
          String reason = txt_MoTa.getText().trim();
        if (reason.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Lý do đổi trả không được để trống.");
            return;
        }
            DoiTra newReturnOrder = null;
        try {
            newReturnOrder = getNewValues(); 
        } catch (Exception ex) {
            Logger.getLogger(DoiTra_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
            createNewReturnOrder(newReturnOrder);   
            Print(newReturnOrder);                 
            renderReturnOrderInfor();             
    }//GEN-LAST:event_btn_TaoHDDTActionPerformed
    private void Print(DoiTra returnOrder) {
        ReturnOrderPrinter printer = new ReturnOrderPrinter(returnOrder);
        printer.generatePDF();
        ReturnOrderPrinter.PrintStatus status = printer.printFile();
        if (status == ReturnOrderPrinter.PrintStatus.NOT_FOUND_FILE) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi không thể in hóa đơn: Không tìm thấy file");
        } else if (status == ReturnOrderPrinter.PrintStatus.NOT_FOUND_PRINTER) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi không thể in hóa đơn: Không tìm thấy máy in");
        }
    }

    private DoiTra getNewValues() throws Exception {
        Date returnDate = jdatechooser_return.getDate();
        if (returnDate == null) {
            throw new Exception("Ngày đổi trả không được để trống.");
        }
        String returnOrderID = txtMaHDDT.getText().trim();
        if (returnOrderID.isEmpty()) {
            throw new Exception("Mã hóa đơn đổi trả không được để trống.");
        }

        boolean isReturn = rdn_Tra.isSelected();

        if (!isReturn) {
            refund = 0;
        } else {
            String amountText = txt_soTienTra.getText().trim().replace(",", "").replace(".", "").replaceAll("[^\\d.]", "").trim();
            if (!amountText.isEmpty()) {
                try {
                    refund = Double.parseDouble(amountText);
                } catch (NumberFormatException e) {
                    Notifications.getInstance().show(Notifications.Type.ERROR, "Số tiền trả không hợp lệ!");
                    txt_soTienTra.requestFocus();
                    return null;
                }
            }
        }

       
        String reason = txt_MoTa.getText().trim();
        double tienhoan = 0; 

        for (int i = 0; i < tblModel_SP.getRowCount(); i++) {
            String maThuoc = tblModel_SP.getValueAt(i, 0).toString();
            String tenThuoc = tblModel_SP.getValueAt(i, 1).toString();
            int soLuong = Integer.parseInt(tblModel_SP.getValueAt(i, 2).toString());
            double dongia = Double.parseDouble(tblModel_HD.getValueAt(i, 4).toString());

            tienhoan = dongia * soLuong;
            if (isReturn) { 
                refund += tienhoan;
            }

            Thuoc sanPham = new Thuoc_DAO().getThuoc(maThuoc);
            listDoiTra.add(new ChiTietDoiTra(new DoiTra(returnOrderID), sanPham, soLuong, tienhoan));
        }

        DoiTra newReturnOrder = new DoiTra(returnDate, returnOrderID, nv, hoaDon, isReturn, refund, (ArrayList<ChiTietDoiTra>) listDoiTra, reason);
        return newReturnOrder;
    }



    private void rdn_TraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdn_TraActionPerformed

    }//GEN-LAST:event_rdn_TraActionPerformed


    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed

        int rowIndex = table_HD.getSelectedRow();

        if (rowIndex == -1) {
            JOptionPane.showMessageDialog(this, "Hãy chọn một sản phẩm trong bảng hóa đơn!");
            return;
        }
        LocalDate ngayLapHoaDon = hoaDon.getNgayLap(); 
        LocalDate ngayHienTai = LocalDate.now(); 
        LocalDate ngayDoiTra = ngayHienTai.minusDays(7); 

        if (ngayLapHoaDon.isBefore(ngayDoiTra)) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Không thể thực hiện đổi trả vì ngày lập hóa đơn trước 7 ngày.");
            return;
        }
        double tienTra = 0;
        String maThuoc = table_HD.getValueAt(rowIndex, 1).toString();
        String tenThuoc = table_HD.getValueAt(rowIndex, 2) + "";
        int soLuong = Integer.valueOf(table_HD.getValueAt(rowIndex, 3).toString());
        boolean isProductExist = false;
        for (int i = 0; i < tblModel_SP.getRowCount(); i++) {
            String existingMaThuoc = tblModel_SP.getValueAt(i, 0).toString();
            if (existingMaThuoc.equals(maThuoc)) {
                isProductExist = true;
            }
        }

        if (isProductExist) {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Sản phẩm đã tồn tại trong danh sách đổi trả!");
            
            return;
        }
        
        tblModel_SP.addRow(new Object[]{maThuoc, tenThuoc, soLuong});

        if (listSPHoan == null) {
            listSPHoan = new ArrayList<>();
        }
        listSPHoan.clear();
        listSPHoan.add(cart.get(rowIndex));

        Thuoc sanPham = new Thuoc_DAO().getThuoc(maThuoc);

        if (listDoiTra == null) {
            listDoiTra = new ArrayList<>();
        }
        listDoiTra.clear();

    }//GEN-LAST:event_btn_ThemActionPerformed

    private void txt_MaHDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_MaHDKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        String maHD = txt_MaHD.getText().trim(); 
        if (!maHD.isEmpty()) {
            renderOrderDetail(maHD); 
        }
        
    }
    }//GEN-LAST:event_txt_MaHDKeyPressed
    private void renderReturnOrderInfor() {
        txtMaHD.setText("");
        txtMaHDDT.setText("");
        jdatechooser_return.setDate(java.sql.Date.valueOf(LocalDate.now()));
        tblModel_SP.setRowCount(0);
        tblModel_HD.setRowCount(0);
        rdn_Doi.setSelected(true);
        txt_MoTa.setText("");
        txt_soTienTra.setText("");
        txt_MaHD.setText("");

    }

    private void createNewReturnOrder(DoiTra newReturnOrder) {
        if ((DoiTra_DAO.create(newReturnOrder))) {
            Notifications.getInstance().show(Notifications.Type.SUCCESS, "Thêm hóa đơn đổi trả thành công");
            ChiTietDoiTra_DAO.createReturnOrderDetail(newReturnOrder,listDoiTra);
            
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Thêm không thành công");
        }
    }


    private void updateTienTra() {
        DecimalFormat df = new DecimalFormat("#,###");
        double tienTra = 0;
        if (rdn_Doi.isSelected()) {
            txt_soTienTra.setText("0 VND");
        } else {
            for (int i = 0; i < tblModel_SP.getRowCount(); i++) {
                String maThuocInTable = tblModel_SP.getValueAt(i, 0).toString(); 
                int soLuongInTable = Integer.parseInt(tblModel_SP.getValueAt(i, 2).toString()); 
                Thuoc sanPhamInTable = new Thuoc_DAO().getThuoc(maThuocInTable); 

                if (sanPhamInTable != null) { 
                    double thanhTien = sanPhamInTable.getGia() * soLuongInTable; 
                    tienTra += thanhTien;  
                } else {
                    System.out.println("Không tìm thấy thuốc với mã: " + maThuocInTable);
                }
            }
            txt_soTienTra.setText(df.format(tienTra) + " VND");
        }
    }


    private void btn_XoaTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaTrangActionPerformed
        renderReturnOrderInfor();
    }//GEN-LAST:event_btn_XoaTrangActionPerformed


    private void txt_MaHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_MaHDMouseClicked
        txt_MaHD.selectAll();
    }//GEN-LAST:event_txt_MaHDMouseClicked

    private void button_groupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_groupMouseClicked

    }//GEN-LAST:event_button_groupMouseClicked

    private void jScrollPane2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jScrollPane2PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2PropertyChange

    private void table_SanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_SanPhamMouseClicked


    }//GEN-LAST:event_table_SanPhamMouseClicked

    private void txt_MaHDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_MaHDKeyReleased
        if (isSelectingFromPopup) {
            return;
        }

        String maHD = txt_MaHD.getText().trim();
        if (!maHD.isEmpty()) {
//            txtMaHD.setText("");
            showSuggestions(maHD);
        } else {
            hideSuggestions();
        }
    }//GEN-LAST:event_txt_MaHDKeyReleased

    private void rdn_DoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdn_DoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdn_DoiActionPerformed

    private void txt_soTienTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_soTienTraActionPerformed

    }//GEN-LAST:event_txt_soTienTraActionPerformed
    private void showSuggestions(String keyword) {
        try {
            if (popup.isVisible()) {
                popup.setVisible(false);
            }

            List<HoaDon> suggestions = hd_DAO.getHoaDonSuggestions(keyword);
            popup.removeAll();

            if (suggestions == null || suggestions.isEmpty()) {
                return;
            }

            for (HoaDon hd : suggestions) {
                JMenuItem item = new JMenuItem(hd.getMaHD());
                item.addActionListener(e -> {

                    isSelectingFromPopup = true;
                    txt_MaHD.setText(hd.getMaHD());
                    popup.setVisible(false);
                    renderOrderDetail(hd.getMaHD());
                    isSelectingFromPopup = false;
                });
                popup.add(item);
            }

            popup.setFocusable(false);
            popup.show(txt_MaHD, 0, txt_MaHD.getHeight());
        } catch (Exception ex) {
            ex.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, "Không thể hiển thị gợi ý.");
        }
    }

    private void hideSuggestions() {
        if (popup.isVisible()) {
            popup.setVisible(false);
        }
    }
    
    public String TaoID() {
        String prefix = "HDDT";      
        int nam = LocalDate.now().getYear() % 100; 
        int thang = LocalDate.now().getMonthValue();
        int ngay = LocalDate.now().getDayOfMonth();
        prefix += String.format("%02d%02d%02d", nam, thang, ngay) + generateRandomString(6);
        System.out.println(prefix);
        return prefix;
    }

    public String generateRandomString(int length) {

        char[] number = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        char[] charArray = chars.toCharArray();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomNumber = (int) Math.floor(Math.random() * 2);
            if (randomNumber == 0) {
                sb.append(number[(int) (Math.random() * 9)]);
            } else {
                sb.append(charArray[(int) (Math.random() * charArray.length)]);
            }
        }

        return sb.toString();
    }

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_TaoHDDT;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_XoaTrang;
    private javax.swing.JButton btn_barcode;
    private javax.swing.JButton btn_searchReturnOrder;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel button_group;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.toedter.calendar.JDateChooser jdatechooser_return;
    private javax.swing.JLabel lbl_BangSanPham;
    private javax.swing.JLabel lbl_LoaiDT;
    private javax.swing.JLabel lbl_LyDo;
    private javax.swing.JLabel lbl_MaHD;
    private javax.swing.JLabel lbl_MaHDDT;
    private javax.swing.JLabel lbl_MaNV;
    private javax.swing.JLabel lbl_NgayDT;
    private javax.swing.JLabel lbl_TenNV;
    private javax.swing.JLabel lbl_TienHoan;
    private javax.swing.JRadioButton rdn_Doi;
    private javax.swing.JRadioButton rdn_Tra;
    private javax.swing.JTable table_HD;
    private javax.swing.JTable table_SanPham;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaHDDT;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txt_MaHD;
    private javax.swing.JTextField txt_MoTa;
    private javax.swing.JTextField txt_soTienTra;
    // End of variables declaration//GEN-END:variables

    private String getVND(double totalAmount) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
