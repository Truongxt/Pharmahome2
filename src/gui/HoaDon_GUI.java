/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import com.formdev.flatlaf.FlatClientProperties;
import com.mallowigi.idea.utils.MTUI;
import com.mallowigi.idea.utils.MTUI.Notification;
import dao.ChiTietHoaDon_DAO;
import dao.DonViTinh_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.LoaiThuoc_DAO;
import dao.NhanVien_DAO;
import dao.Thuoc_DAO;
import dao.Voucher_DAO;
import dao.XuatXu_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.TaiKhoan;
import entity.Thuoc;
import entity.Voucher;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import main.Main;
import raven.toast.Notifications;
import utilities.OrderPrinter;

/**
 *
 * @author HÀ NHƯ
 */
public class HoaDon_GUI extends javax.swing.JPanel {

    private KhachHang_DAO kh_DAO;
    private HoaDon_DAO hd_DAO;
    private ArrayList<ChiTietHoaDon> listCTHD;
    private DefaultTableModel model_cart;
    private DefaultTableModel model_sp;
    private HoaDon hd;
    private JButton[] btnOptionsList;
    private KhachHang kh;
    private NhanVien nv;
    private TaiKhoan tk;
    private boolean isOldOrder = false;
    private int stt = 1;
    private int soLuongDonTam = 0;
    private DefaultTableModel tblModel_savedOrder;
    private DefaultTableModel tblModel_chiTietTam;
    private ArrayList<Integer> indexThuoc;

    public HoaDon_GUI(TaiKhoan tk) {
        initComponents();
        this.btnOptionsList = new JButton[]{btn_op1, btn_op2, btn_op3, btn_op4, btn_op5, btn_op6, btn_op7, btn_op8, btn_op9};
        this.tk = tk;
        initNhanvien();
        indexThuoc = new ArrayList<>();
        kh_DAO = new KhachHang_DAO();
        hd_DAO = new HoaDon_DAO();
        listCTHD = new ArrayList<>();
        renderID();
        model_sp = new DefaultTableModel(new String[]{"Mã Thuốc", "Tên", "Loại", "Số lượng", "Đơn vị", "Giá", "Thuế"}, 0) {
            @Override
            // cho phép sửa ô số lượng
            public boolean isCellEditable(int row, int column) {
                if (column == 3) {
                    return true;
                } else {
                    return false;
                }
            }

        };

        model_cart = new DefaultTableModel(new String[]{"STT","Mã Thuốc", "Tên thuốc", "Số lượng", "Đơn giá", "Thành tiền"}, 0) {
            @Override
            // cho phép sửa ô số lượng
            public boolean isCellEditable(int row, int column) {
                if (column == 3) {
                    return true;
                } else {
                    return false;
                }
            }

        };
        tableSP.setModel(model_sp);
        tableCart.setModel(model_cart);
        initTableSP();
        jtf_maHoaDon.setText(hd_DAO.generateID(nv));
        jtf_ngayTao.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        suaSoLuong();
        initGoiY();
    }

    @SuppressWarnings("unchecked")
    public void renderID() {
        try {
            hd = hd_DAO.createNewOrder(nv);
        } catch (Exception ex) {
            Logger.getLogger(HoaDon_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initDoanhThu() {
        double doanhThu = 0;
        if (listCTHD != null) {
            for (ChiTietHoaDon ct : listCTHD) {
                doanhThu = doanhThu + ct.thanhTien();
                System.out.println(ct.getThuoc().getMaThuoc() + "Số lượng :" + ct.getSoLuong());
            }
        }
        jtf_khachTra.setText(doanhThu + "");
    }

    public void initNhanvien() {
        nv = new NhanVien_DAO().timKiemTheoMa1(tk.getNhanVien().getMaNhanVien());
    }
//goi y tien tra

    public void initGoiY() {
        goiYTienTra(Double.valueOf(jtf_khachTra.getText().equalsIgnoreCase("") ? "0" : jtf_khachTra.getText()));

    }

    private void calculateOptionCashGive() {

        double orderPay = Double.valueOf(jtf_khachTra.getText().equalsIgnoreCase("") ? "0" : jtf_khachTra.getText());

        if (orderPay == 0) {
            Arrays.stream(btnOptionsList).forEach(item -> item.setText("-"));
            return;
        }

        Integer[] roundValues = new Integer[]{1000, 2000, 5000, 10000, 20000, 50000, 100000, 200000, 500000};

        Set<Double> values = new HashSet<>();
        for (Integer item : roundValues) {
            values.add(orderPay + (item - orderPay % item));
        }

        List<Double> list = new ArrayList<>(values);
        Collections.sort(list);

        int index = 0;

        for (Double value : list) {
            if (index >= btnOptionsList.length) {
                break;
            }

            btnOptionsList[index].setText(String.format("%.0fk (%d)", value / 1000, index + 1));
            btnOptionsList[index].setVisible(true);

            btnOptionsList[index].setMnemonic(index + 49);
            index++;
        }

        if (Math.round(orderPay) % 1000 == 0) {
            btnOptionsList[0].setText(String.format("%.0fk (1)", orderPay / 1000));
        }
//ẩn các button không chứa tiền
        for (; index < btnOptionsList.length; index++) {
            if (index >= btnOptionsList.length) {
                break;
            }
            btnOptionsList[index].setVisible(false);
            btnOptionsList[index].setText("0");
            btnOptionsList[index].setMnemonic(0);
        }

        for (JButton item : btnOptionsList) {
            item.revalidate();
            item.repaint();
        }
    }

    public void initTableSP() {
        ArrayList<Thuoc> listSP = new Thuoc_DAO().getAllThuoc();
        for (Thuoc t : listSP) {
            Object[] obj = initObject(t);
            model_sp.addRow(obj);
        }
    }

    public void goiYTienTra(double tienKhachDua) {
        Integer[] roundValues = new Integer[]{1000, 2000, 5000, 10000, 20000, 50000, 100000, 200000, 500000};

        for (int i = 0; i < roundValues.length; i++) {
            final JButton temp = btnOptionsList[i];
            temp.setText(roundValues[i] / 1000 + "k");
            temp.addActionListener((var e) -> {
                String value = temp.getText();
                value = value.substring(0, value.indexOf("k"));
                Double dValue = 1000 * Double.parseDouble(value);
                jtf_tienKhachDua.setText(dValue + "");
            });
            temp.setVisible(false);

        }
        calculateOptionCashGive();
        jtf_tienKhachDua.getDocument().addDocumentListener(new DocumentListener() {
            private void updateCustomerReturn() {
                try {
                    if (Double.valueOf(jtf_khachTra.getText()) == 0) {
                        jtf_tienKhachDua.setText("0");
                        return;
                    }

                    jtf_tienThua.setText(
                            utilities.FormatNumber.toVND(
                                    Double.valueOf(Math.round(
                                            Double.valueOf(jtf_tienKhachDua.getText()) - Double.valueOf(jtf_khachTra.getText())
                                    ))
                            )
                    );
                } catch (Exception ex) {

                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateCustomerReturn();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateCustomerReturn();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateCustomerReturn();
            }
        });

    }

    // cho phep sua so luong
    public void suaSoLuong() {
        tableCart.getModel().addTableModelListener((evt) -> {
            int row = evt.getFirstRow();
            int col = evt.getColumn();
            if (row == -1 || col == -1 || col != 3) {
                return;
            }

            try {
                int soLuongMoi = Integer.parseInt(tableCart.getValueAt(row, col) + "");
                ChiTietHoaDon current = listCTHD.get(row);

                // reset lại tiền khách đưa
                jtf_tienKhachDua.setText("");

                if (soLuongMoi < 0) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, "Số lượng không được bé hơn 0");
                    tableCart.setValueAt(current.getSoLuong(), row, col);
                    return;
                }
                if (soLuongMoi == 0) {
                    if (JOptionPane.showConfirmDialog(this, "Xóa sản phẩm " + current.getThuoc().getMaThuoc() + " ra khỏi giỏ hàng", "Xóa sản phẩm khỏi giỏ", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        listCTHD.remove(current);
                        indexThuoc.remove(row);
                        renderCartTable();
                        return;
                    } else {
                        tableCart.setValueAt(current.getSoLuong(), row, col);
                        return;
                    }
                }

                if (current.getThuoc().getSoLuongTon() >= soLuongMoi) {
                    // Update new quantity
                    current.setSoLuong(soLuongMoi);
                    renderCartTable();

                } else {
                    // Revert to old quantity
                    tableCart.setValueAt(current.getSoLuong(), row, col);
                    Notifications.getInstance().show(Notifications.Type.ERROR, "Số lượng sản phẩm không đủ!");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Notifications.getInstance().show(Notifications.Type.INFO, "Số lượng không hợp lệ");
            } catch (Exception ex) {
                ex.printStackTrace();
                Notifications.getInstance().show(Notifications.Type.ERROR, "Không thể cập nhật số lượng mới!");
            }
        });
        renderCartTable();

    }

    private void renderCartTable() {
        stt = 1;
        model_cart.setRowCount(0);
        for (ChiTietHoaDon item : listCTHD) {
            Object[] newRow = initObjectSP(item.getThuoc(), item.getSoLuong(), stt++);
            model_cart.addRow(newRow);
        };
        initDoanhThu();
        initGoiY();

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DanhSachDonTam = new javax.swing.JFrame();
        lbl_title = new javax.swing.JLabel();
        panel_danhSach = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_tam = new javax.swing.JTable();
        panel_chiTiet = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_chiTietTam = new javax.swing.JTable();
        btn_xoaToanBo = new javax.swing.JButton();
        btn_xuLy = new javax.swing.JButton();
        btn_xoaDon = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtf_tenKH = new javax.swing.JTextField();
        jtf_sdt = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jtf_khachTra = new javax.swing.JTextField();
        jtf_voucher = new javax.swing.JTextField();
        jtf_maHoaDon = new javax.swing.JTextField();
        jtf_ngayTao = new javax.swing.JTextField();
        jtf_tienKhachDua = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jtf_tienThua = new javax.swing.JTextField();
        jcb_phuongThuc = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        btn_huy = new javax.swing.JButton();
        btn_thanhToan = new javax.swing.JButton();
        panel_goiYTien = new javax.swing.JPanel();
        btn_op1 = new javax.swing.JButton();
        btn_op2 = new javax.swing.JButton();
        btn_op3 = new javax.swing.JButton();
        btn_op4 = new javax.swing.JButton();
        btn_op5 = new javax.swing.JButton();
        btn_op6 = new javax.swing.JButton();
        btn_op7 = new javax.swing.JButton();
        btn_op8 = new javax.swing.JButton();
        btn_op9 = new javax.swing.JButton();
        btn_luuTam = new javax.swing.JButton();
        btn_xuLyDonTam = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCart = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableSP = new javax.swing.JTable();
        btn_themThuoc = new javax.swing.JButton();

        DanhSachDonTam.setSize(new java.awt.Dimension(850, 500));
        DanhSachDonTam.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_title.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_title.setText("Danh sách hóa đơn đổi trả");
        DanhSachDonTam.getContentPane().add(lbl_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 771, -1));

        tbl_tam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Tên khách hàng", "Số điện thoại", "Ngày tạo"
            }
        ));
        tbl_tam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_tamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_tam);

        javax.swing.GroupLayout panel_danhSachLayout = new javax.swing.GroupLayout(panel_danhSach);
        panel_danhSach.setLayout(panel_danhSachLayout);
        panel_danhSachLayout.setHorizontalGroup(
            panel_danhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        panel_danhSachLayout.setVerticalGroup(
            panel_danhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_danhSachLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addContainerGap())
        );

        DanhSachDonTam.getContentPane().add(panel_danhSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, -1));

        tbl_chiTietTam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Tên sản phẩm", "Số lượng"
            }
        ));
        jScrollPane4.setViewportView(tbl_chiTietTam);

        javax.swing.GroupLayout panel_chiTietLayout = new javax.swing.GroupLayout(panel_chiTiet);
        panel_chiTiet.setLayout(panel_chiTietLayout);
        panel_chiTietLayout.setHorizontalGroup(
            panel_chiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 392, Short.MAX_VALUE)
            .addGroup(panel_chiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_chiTietLayout.createSequentialGroup()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panel_chiTietLayout.setVerticalGroup(
            panel_chiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 305, Short.MAX_VALUE)
            .addGroup(panel_chiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel_chiTietLayout.createSequentialGroup()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        DanhSachDonTam.getContentPane().add(panel_chiTiet, new org.netbeans.lib.awtextra.AbsoluteConstraints(406, 50, -1, -1));

        btn_xoaToanBo.setText("Xóa toàn bộ đơn tạm");
        btn_xoaToanBo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaToanBoActionPerformed(evt);
            }
        });
        DanhSachDonTam.getContentPane().add(btn_xoaToanBo, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 380, 230, -1));

        btn_xuLy.setText("Xử lý đơn");
        btn_xuLy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xuLyActionPerformed(evt);
            }
        });
        DanhSachDonTam.getContentPane().add(btn_xuLy, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 230, -1));

        btn_xoaDon.setText("Xóa đơn tạm");
        btn_xoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaDonActionPerformed(evt);
            }
        });
        DanhSachDonTam.getContentPane().add(btn_xoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 380, 230, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Số điện thoại :");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Tên khách hàng : ");

        jtf_tenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_tenKHActionPerformed(evt);
            }
        });
        jtf_tenKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtf_tenKHKeyReleased(evt);
            }
        });

        jtf_sdt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtf_sdtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_sdtKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtf_sdt, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtf_tenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)))
                .addGap(57, 57, 57))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jtf_sdt, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jtf_tenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Mã hóa đơn:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Ngày tạo: ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Voucher: ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Khách phải trả:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setText("Phương thức:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("Tiền khách đưa:");

        jtf_khachTra.setEnabled(false);
        jtf_khachTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_khachTraActionPerformed(evt);
            }
        });
        jtf_khachTra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtf_khachTraKeyPressed(evt);
            }
        });

        jtf_voucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_voucherActionPerformed(evt);
            }
        });
        jtf_voucher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtf_voucherKeyPressed(evt);
            }
        });

        jtf_maHoaDon.setEnabled(false);

        jtf_ngayTao.setEnabled(false);

        jtf_tienKhachDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_tienKhachDuaActionPerformed(evt);
            }
        });
        jtf_tienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtf_tienKhachDuaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_tienKhachDuaKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setText("Tiền thừa:");

        jtf_tienThua.setEnabled(false);

        jcb_phuongThuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "ATM" }));
        jcb_phuongThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb_phuongThucActionPerformed(evt);
            }
        });

        jButton1.setText("000");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btn_huy.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btn_huy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/GUI/delete.png"))); // NOI18N
        btn_huy.setText("HỦY");
        btn_huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_huyActionPerformed(evt);
            }
        });

        btn_thanhToan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btn_thanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/GUI/credit-card.png"))); // NOI18N
        btn_thanhToan.setText("THANH TOÁN");
        btn_thanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thanhToanActionPerformed(evt);
            }
        });

        panel_goiYTien.setLayout(new java.awt.GridLayout(4, 4, 2, 2));

        btn_op1.setText("Gợi ý 1");
        panel_goiYTien.add(btn_op1);

        btn_op2.setText("Gợi ý 2");
        panel_goiYTien.add(btn_op2);

        btn_op3.setText("Gợi ý 3");
        panel_goiYTien.add(btn_op3);

        btn_op4.setText("Gợi ý 4");
        panel_goiYTien.add(btn_op4);

        btn_op5.setText("Gợi ý 5");
        panel_goiYTien.add(btn_op5);

        btn_op6.setText("Gợi ý 6");
        panel_goiYTien.add(btn_op6);

        btn_op7.setText("Gợi ý 7");
        panel_goiYTien.add(btn_op7);

        btn_op8.setText("Gợi ý 8");
        panel_goiYTien.add(btn_op8);

        btn_op9.setText("Gợi ý 9");
        panel_goiYTien.add(btn_op9);

        btn_luuTam.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btn_luuTam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/GUI/floppy-disc.png"))); // NOI18N
        btn_luuTam.setText("LƯU TẠM");
        btn_luuTam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_luuTamActionPerformed(evt);
            }
        });

        btn_xuLyDonTam.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btn_xuLyDonTam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/GUI/time.png"))); // NOI18N
        btn_xuLyDonTam.setText("XỬ LÝ ĐƠN TẠM");
        btn_xuLyDonTam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xuLyDonTamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtf_tienThua, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                        .addGap(74, 74, 74))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                .addGap(6, 6, 6))
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jtf_tienKhachDua, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jcb_phuongThuc, 0, 300, Short.MAX_VALUE)
                                .addGap(74, 74, 74))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(jtf_maHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(jtf_ngayTao, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                        .addGap(20, 20, 20)
                        .addComponent(jtf_voucher, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panel_goiYTien, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(jtf_khachTra))))
                .addGap(54, 54, 54))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(btn_luuTam, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_huy, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_xuLyDonTam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(btn_thanhToan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addGap(3, 3, 3))
                    .addComponent(jtf_maHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addGap(3, 3, 3))
                    .addComponent(jtf_ngayTao, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jtf_voucher, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addGap(3, 3, 3))
                    .addComponent(jtf_khachTra, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_goiYTien, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                .addGap(52, 52, 52)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addGap(2, 2, 2))
                    .addComponent(jcb_phuongThuc, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtf_tienKhachDua, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                        .addGap(4, 4, 4))
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jtf_tienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_luuTam, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(btn_xuLyDonTam, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(btn_huy, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_thanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        tableCart.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Tên", "Loại", "Số lượng", "Đơn vị", "Giá", "Thuế", "Thành tiền"
            }
        ));
        tableCart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCartMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableCart);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        tableSP.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tableSP);

        btn_themThuoc.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btn_themThuoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nhanvien/plus.png"))); // NOI18N
        btn_themThuoc.setText("Thêm");
        btn_themThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themThuocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(341, 341, 341)
                        .addComponent(btn_themThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_themThuoc, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtf_tenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_tenKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_tenKHActionPerformed

    private void jtf_tienKhachDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_tienKhachDuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_tienKhachDuaActionPerformed

    private void jtf_voucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_voucherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_voucherActionPerformed

    private void btn_thanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thanhToanActionPerformed
        taoHoaDon();
    }//GEN-LAST:event_btn_thanhToanActionPerformed

    private void btn_huyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_huyActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn hủy hóa đơn " + hd.getMaHD(), "Xác nhận hủy hóa đơn", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            // Tạo lại trang mới
            if (isOldOrder) {
                boolean isDeleted = hd_DAO.deleteHoaDon(hd.getMaHD());

                if (isDeleted) {
                    Notifications.getInstance().show(Notifications.Type.INFO, "Đã hủy thành công hóa đơn lưu tạm");
                } else {
                    Notifications.getInstance().show(Notifications.Type.ERROR, "Không thể hủy đơFn lưu tạm");
                }
            } else {
                Notifications.getInstance().show(Notifications.Type.INFO, "Đã hủy thành công hóa đơn");
            }

            refeshForm();
        }
    }//GEN-LAST:event_btn_huyActionPerformed

    private void jcb_phuongThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb_phuongThucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcb_phuongThucActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jtf_sdtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_sdtKeyTyped

    }//GEN-LAST:event_jtf_sdtKeyTyped

    private void jtf_sdtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_sdtKeyReleased
        int key = evt.getKeyCode();
        if (key == 10) {
            kh = kh_DAO.getKhachHangSDT(jtf_sdt.getText());
            if (kh != null) {
                jtf_tenKH.setText(kh.getTenKhachHang());
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Đã tìm thấy khách hàng");
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Không tìm thấy khách hàng");

            }

        }
    }//GEN-LAST:event_jtf_sdtKeyReleased
    //khoi tao object cho sp
    public Object[] initObjectSP(Thuoc t, int soLuong, int stt) {
        Object[] obj = new Object[6];
        obj[0] = stt;
        obj[1] = t.getMaThuoc();
        obj[2] = t.getTenThuoc();
        obj[3] = soLuong;
        obj[4] = t.getGia();
        obj[5] = new ChiTietHoaDon(soLuong, t.getGia(), t, hd).thanhTien();
        return obj;
    }
    private void btn_themThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themThuocActionPerformed
        if (!jtf_sdt.getText().equalsIgnoreCase("")) {
            int index = tableSP.getSelectedRow();
            if (!indexThuoc.contains(index)) {
                Thuoc t = new Thuoc_DAO().getThuoc(tableSP.getValueAt(index, 0) + "");
                if (t.getSoLuongTon() - 1 >= 0) {
                    //mặc đinh thêm số lượng là 1 vào giỏ hàng
                    ChiTietHoaDon cthd = new ChiTietHoaDon(1, t.getGia(), t, hd);
                    Object objSP[] = initObjectSP(t, 1, stt++);
                    indexThuoc.add(index);
                    listCTHD.add(cthd);
                    model_cart.addRow(objSP);
                    initDoanhThu();
                    initGoiY();

                } else {
                    Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Không đủ số lượng tồn");

                }

            } else {
                // cho biến tạm để lưu vị trí
                int n = -1;
                String maThuoc = tableSP.getValueAt(index, 0) + "";
                System.out.println(maThuoc);
                for (int i = 0; i < tableCart.getRowCount(); i++) {
                    if(maThuoc.equalsIgnoreCase(tableCart.getValueAt(i, 1)+"")){
                        n=i;
                        break;
                    }
                }
                
                tableCart.setValueAt(Integer.valueOf(tableCart.getValueAt(n, 3).toString()) + 1, n, 3);
                suaSoLuong();
                initDoanhThu();
                initGoiY();
            }

        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_LEFT, "Vui lòng nhập số điện thoại trước khi chọn sản phẩm");
        }

    }//GEN-LAST:event_btn_themThuocActionPerformed

    private void jtf_khachTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_khachTraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_khachTraActionPerformed

    private void jtf_tienKhachDuaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_tienKhachDuaKeyTyped

    }//GEN-LAST:event_jtf_tienKhachDuaKeyTyped

    private void jtf_tienKhachDuaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_tienKhachDuaKeyPressed
        if (evt.getKeyCode() == 10) {
            double tienPhaiTra = Double.valueOf(jtf_khachTra.getText());
            double tienKhachTra = Double.valueOf(jtf_tienKhachDua.getText());
            jtf_tienThua.setText((tienKhachTra - tienPhaiTra) + "");
        }
    }//GEN-LAST:event_jtf_tienKhachDuaKeyPressed

    private void jtf_khachTraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_khachTraKeyPressed

    }//GEN-LAST:event_jtf_khachTraKeyPressed

    private void jtf_voucherKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_voucherKeyPressed
        if (evt.getKeyCode() == 10) {
            if (jtf_khachTra.getText().equalsIgnoreCase("")) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_LEFT, "Vui lòng thêm sản phẩm trước khi nhập voucher");

            } else {
                System.out.println(new Voucher_DAO().getVoucher(jtf_voucher.getText()).getNgayKetThuc());
                if (new Voucher_DAO().getVoucher(jtf_voucher.getText()).getNgayKetThuc().isAfter(LocalDate.now())) {

                    Double giaGiam = new Voucher_DAO().getVoucher(jtf_voucher.getText()).getGiaGiam();
                    double khachHangTra = Double.valueOf(jtf_khachTra.getText());
                    jtf_khachTra.setText((khachHangTra - (giaGiam * khachHangTra)) + "");
                    initGoiY();
                } else {
                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_LEFT, "Vocher hết hạn sử dụng");

                }

            }
        }
    }//GEN-LAST:event_jtf_voucherKeyPressed

    private void jtf_tenKHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_tenKHKeyReleased
        int key = evt.getKeyCode();
        if (key == 10) {
            kh = kh_DAO.getKhachHangSDT(jtf_sdt.getText());
            if (kh != null) {
                jtf_tenKH.setText(kh.getTenKhachHang());
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Đã tìm thấy khách hàng");
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Không tìm thấy khách hàng");

            }

        }
    }//GEN-LAST:event_jtf_tenKHKeyReleased

    private void tableCartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCartMouseClicked

    }//GEN-LAST:event_tableCartMouseClicked

    private void btn_luuTamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_luuTamActionPerformed
        if (!orderValidate(false)) {
            return;
        }
        if (soLuongDonTam >= 5) {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Hiện đã lưu tối đa 5 đơn tạm, vui lòng xử lí các đơn lưu tạm trước đó");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn lưu tạm hóa đơn này để xử lí sau không?", "Xác nhận lưu tạm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            //         Nếu chưa có khách hàng sẽ cảnh báo
            if (jtf_tenKH.getText().isEmpty() || jtf_sdt.getText().isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.WARNING, "Bạn chưa thêm thông tin khách hàng!");
                return;
            }

            //         Nếu chưa có sản phẩm sẽ cảnh báo
            if (model_cart.getRowCount() == 0) {
                Notifications.getInstance().show(Notifications.Type.WARNING, "Bạn chưa thêm sản phẩm vào danh sách!");
                return;
            }

            try {
                boolean isSaved = isOldOrder ? updateOrder(false) : saveOrder(false);
                if (isSaved) {
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "Đã lưu tạm thành công đơn hàng " + hd.getMaHD());
                    refeshForm();
                } else {
                    Notifications.getInstance().show(Notifications.Type.ERROR, "Có lỗi xảy ra khi lưu đơn hàng vào cơ sở dữ liệu" + hd.getMaHD());
                }
            } catch (Exception ex) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Không thể lưu tạm hóa đơn " + hd.getMaHD() + ": " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btn_luuTamActionPerformed

    private void btn_xuLyDonTamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xuLyDonTamActionPerformed
        DanhSachDonTam.setLocationRelativeTo(Main.app);
        renderSavedOrderTable();
        DanhSachDonTam.setVisible(true);
    }//GEN-LAST:event_btn_xuLyDonTamActionPerformed

    private void btn_xuLyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xuLyActionPerformed
        if (!listCTHD.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, 3000, "Đơn hàng hiện tại vẫn chưa xử lí xong, hãy hoàn thành hoặc hủy bỏ đơn hiện tại để xử lí đơn lưu tạm");
            return;
        }
        int row = tbl_tam.getSelectedRow();

//        Nếu đang không chọn dòng nào thì thông báo
        if (row == -1) {
            Notifications.getInstance().show(Notifications.Type.INFO, "Vui lòng chọn hóa đơn muốn xử lí");
            return;
        }

        String orderID = tbl_tam.getValueAt(row, 0).toString();
        isOldOrder = true;
        loadSavedOrder(orderID);
        DanhSachDonTam.dispose();
        Notifications.getInstance().show(Notifications.Type.INFO, "Đã tải lên thông tin của hóa đơn " + orderID);
    }//GEN-LAST:event_btn_xuLyActionPerformed

    private void loadSavedOrder(String id) {
        HoaDon savedOrder = hd_DAO.getHoaDon(id);
//        update state
        hd = savedOrder;
        hd.setListCTHD(hd_DAO.getChiTietHoaDon(id));
        kh = hd.getKhachHang();
        listCTHD = hd.getListCTHD();
//        Update view
        jtf_khachTra.setText("");

        renderOrder();
        renderCartTable();
        setOrderCustomer(hd.getKhachHang());
    }

    private void renderOrder() {
        jtf_maHoaDon.setText(hd.getMaHD());
        jtf_ngayTao.setText(LocalDate.now() + "");
    }

    private void setOrderCustomer(KhachHang kh) {
        jtf_tenKH.setText(kh.getTenKhachHang());
        jtf_sdt.setText(kh.getSdt());
    }
    private void tbl_tamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_tamMouseClicked
        tblModel_chiTietTam.setRowCount(0);
        int index = tbl_tam.getSelectedRow();
        ArrayList<ChiTietHoaDon> tam = hd_DAO.getChiTietHoaDon(tbl_tam.getValueAt(index, 0) + "");
        for (ChiTietHoaDon cthd : tam) {
            Object obj[] = new Object[2];
            obj[0] = cthd.getThuoc().getTenThuoc();
            obj[1] = cthd.getSoLuong();
            tblModel_chiTietTam.addRow(obj);
        }
    }//GEN-LAST:event_tbl_tamMouseClicked

    private void btn_xoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaDonActionPerformed
        int row = tbl_tam.getSelectedRow();

//        Nếu đang không chọn dòng nào thì thông báo
        if (row == -1) {
            Notifications.getInstance().show(Notifications.Type.INFO, "Vui lòng chọn hóa đơn muốn xử lí");
            return;
        }
        String orderID = tbl_tam.getValueAt(row, 0).toString();
        if (JOptionPane.showConfirmDialog(DanhSachDonTam, String.format("Bạn có muốn xóa đơn hàng đã lưu tạm này? (%s)", orderID), "Xóa đơn lưu tạm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            boolean isDeleted = hd_DAO.deleteHoaDon(orderID);

            if (isDeleted) {
                Notifications.getInstance().show(Notifications.Type.SUCCESS, "Đã xóa thành công đơn lưu tạm " + orderID);
                renderSavedOrderTable();
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Không thể xóa đơn lưu tạm " + orderID + " lỗi không xác định");
            }
        }
    }//GEN-LAST:event_btn_xoaDonActionPerformed

    private void btn_xoaToanBoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaToanBoActionPerformed
        if (JOptionPane.showConfirmDialog(DanhSachDonTam, "Bạn có muốn xóa toàn bộ đơn hàng đã lưu tạm này (Không thể phục hồi)?", "Xóa đơn lưu tạm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            //duyệt ngược mới thành công, do sau khi xóa 1 dòng thì dòng phía sau sẽ bị đẩy lên dẫn đến xóa không hết hoàn toàn
            for (int row = tblModel_savedOrder.getRowCount() - 1; row >= 0; row--) {
                String orderID = tbl_tam.getValueAt(row, 0) + "";
                boolean isDeleted = hd_DAO.deleteHoaDon(orderID);
                if (isDeleted) {
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "Đã xóa thành công toàn bộ đơn lưu tạm " + orderID);
                    renderSavedOrderTable();
                } else {
                    Notifications.getInstance().show(Notifications.Type.ERROR, "Không thể xóa đơn lưu tạm " + orderID + " lỗi không xác định");
                }
            }

        }
    }//GEN-LAST:event_btn_xoaToanBoActionPerformed
    private void renderSavedOrderTable() {
        tblModel_savedOrder = new DefaultTableModel(new String[]{"Mã hóa đơn", "Tên khách hàng", "Số điện thoại", "Ngày tạo"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblModel_chiTietTam = new DefaultTableModel(new String[]{"Tên sản phẩm", "Số lượng"}, 0);
        tbl_tam.setModel(tblModel_savedOrder);
        tbl_chiTietTam.setModel(tblModel_chiTietTam);

        for (HoaDon item : hd_DAO.getAllHoaDonTam()) {
            tblModel_savedOrder.addRow(new Object[]{item.getMaHD(), item.getKhachHang().getTenKhachHang(), item.getKhachHang().getSdt(), item.getNgayLap()});
        }
    }

    private boolean saveOrder(boolean isComplete) {
        Notifications.getInstance().show(Notifications.Type.INFO, "Đang lưu trữ hóa đơn...");

        try {
            hd.setNhanVien(nv);
            hd.setNgayLap(LocalDate.now());
            hd.setListCTHD(listCTHD);
            boolean isATMPayment = jcb_phuongThuc.getSelectedIndex() == 1;
            hd.setAtm(isATMPayment);
            hd.setTrangThai(isComplete);

            if (!jtf_khachTra.getText().isEmpty()) {
                hd.setTongTien(Double.valueOf(jtf_khachTra.getText()));

            } else {
                hd.setTongTien(0);

            }
            hd.setKhachHang(kh);
            if (!jtf_tienKhachDua.getText().isEmpty()) {
                hd.setTienDaDua(Double.valueOf(jtf_tienKhachDua.getText()));

            } else {
                hd.setTienDaDua(0);

            }

            if (jtf_voucher.getText().isEmpty()) {
                hd.setVoucher(new Voucher(""));

            } else {
                hd.setVoucher(new Voucher_DAO().getVoucher(jtf_voucher.getText()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (!hd_DAO.create(hd)) {
            return false;
        }
        for (ChiTietHoaDon cthd : hd.getListCTHD()) {
            if (!new ChiTietHoaDon_DAO().create(cthd)) {
                return false;
            }
        }
        return true;
    }

    private boolean updateOrder(boolean isComplete) {

        Notifications.getInstance().show(Notifications.Type.INFO, "Đang lưu trữ hóa đơn...");
        try {
            hd.setNhanVien(nv);
            hd.setNgayLap(LocalDate.now());
            hd.setListCTHD(listCTHD);
            boolean isATMPayment = jcb_phuongThuc.getSelectedIndex() == 1;
            hd.setAtm(isATMPayment);
            hd.setTrangThai(isComplete);
            if (!jtf_khachTra.getText().isEmpty()) {
                hd.setTongTien(Double.valueOf(jtf_khachTra.getText()));

            } else {
                hd.setTongTien(0);

            }
            hd.setKhachHang(kh);
            if (!jtf_tienKhachDua.getText().isEmpty()) {
                hd.setTienDaDua(Double.valueOf(jtf_tienKhachDua.getText()));

            } else {
                hd.setTienDaDua(0);

            }

            if (jtf_voucher.getText().isEmpty()) {
                hd.setVoucher(new Voucher(""));

            } else {
                hd.setVoucher(new Voucher_DAO().getVoucher(jtf_voucher.getText()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return hd_DAO.suaHoaDon(hd.getMaHD(), hd);
    }

//    public String xuLyMaHoaDon() {
//        String maHD = "HD";
//        String nam = (LocalDate.now().getYear() + "").substring(2, 4);
//        String thang = (LocalDate.now().getMonthValue() + "");
//        String ngay = (LocalDate.now().getDayOfMonth() + "");
//        maHD = maHD + (nam + thang + ngay);
//        String maNV = nv.getMaNhanVien().substring(2);
//        ArrayList<HoaDon> listHD = new HoaDon_DAO().getAllHoaDon();
//
//        maHD = maHD + maNV + String.format("%05d", dailyCounter);
//        return maHD;
//
//    }
    public Object[] initObject(Thuoc t) {
        Object obj[] = new Object[8];
        obj[0] = t.getMaThuoc();
        obj[1] = t.getTenThuoc();
        obj[2] = new LoaiThuoc_DAO().getLoaiThuoc(t.getLoaiThuoc().getMaLoai()).getTenLoai();
        obj[3] = t.getSoLuongTon();
        obj[4] = new DonViTinh_DAO().getDonViTinhById(t.getDonViTinh().getMaDonViTinh()).getTen();
        obj[5] = t.getGia();
        obj[6] = t.getThue();
        return obj;
    }

    private boolean orderValidate(boolean checkCustomerGive) {
        //         Nếu chưa có khách hàng sẽ cảnh báo
        if (jtf_tenKH.getText().isEmpty() || jtf_sdt.getText().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Bạn chưa thêm thông tin khách hàng");
            return false;
        }

        //         Nếu chưa có sản phẩm sẽ cảnh báo
        if (model_cart.getRowCount() == 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Bạn chưa thêm sản phẩm vào danh sách!");
            return false;
        }

        if (checkCustomerGive) {
            boolean isRealMoney = jcb_phuongThuc.getSelectedIndex() == 0;

            if (isRealMoney) {
                //         Nếu phương thức là tiền mặt và chưa nhập tiền khách đưa sẽ cảnh báo
                if (jcb_phuongThuc.getSelectedIndex() == 0 && checkCustomerGive && jtf_tienKhachDua.getText().isEmpty()) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, "Vui lòng nhập số tiền khách đã đưa!");
                    return false;
                }
            }
        }

        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame DanhSachDonTam;
    private javax.swing.JButton btn_huy;
    private javax.swing.JButton btn_luuTam;
    private javax.swing.JButton btn_op1;
    private javax.swing.JButton btn_op2;
    private javax.swing.JButton btn_op3;
    private javax.swing.JButton btn_op4;
    private javax.swing.JButton btn_op5;
    private javax.swing.JButton btn_op6;
    private javax.swing.JButton btn_op7;
    private javax.swing.JButton btn_op8;
    private javax.swing.JButton btn_op9;
    private javax.swing.JButton btn_thanhToan;
    private javax.swing.JButton btn_themThuoc;
    private javax.swing.JButton btn_xoaDon;
    private javax.swing.JButton btn_xoaToanBo;
    private javax.swing.JButton btn_xuLy;
    private javax.swing.JButton btn_xuLyDonTam;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JComboBox<String> jcb_phuongThuc;
    private javax.swing.JTextField jtf_khachTra;
    private javax.swing.JTextField jtf_maHoaDon;
    private javax.swing.JTextField jtf_ngayTao;
    private javax.swing.JTextField jtf_sdt;
    private javax.swing.JTextField jtf_tenKH;
    private javax.swing.JTextField jtf_tienKhachDua;
    private javax.swing.JTextField jtf_tienThua;
    private javax.swing.JTextField jtf_voucher;
    private javax.swing.JLabel lbl_title;
    private javax.swing.JPanel panel_chiTiet;
    private javax.swing.JPanel panel_danhSach;
    private javax.swing.JPanel panel_goiYTien;
    private javax.swing.JTable tableCart;
    private javax.swing.JTable tableSP;
    private javax.swing.JTable tbl_chiTietTam;
    private javax.swing.JTable tbl_tam;
    // End of variables declaration//GEN-END:variables

    public void taoHoaDon() {
        if (!orderValidate(true)) {
            return;
        }

        try {
            boolean isSaved = isOldOrder ? updateOrder(isOldOrder) : saveOrder(true);

            if (isSaved) {
                Notifications.getInstance().show(Notifications.Type.SUCCESS, "Đã tạo thành công đơn hàng" + hd.getMaHD());
//              
//                Rerender panel
                refeshForm();
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Có lỗi xảy ra khi lưu đơn hàng vào cơ sở dữ liệu" + hd.getMaHD());
            }
        } catch (Exception ex) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Không thể tạo đơn hàng " + hd.getMaHD() + ": " + ex.getMessage());
        }

//        tạo file pdf và hiển thị + in file pdf đó
        OrderPrinter printer = new OrderPrinter(hd);
        printer.generatePDF();
        OrderPrinter.PrintStatus status = printer.printFile();
        if (status == OrderPrinter.PrintStatus.NOT_FOUND_FILE) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi không thể in hóa đơn: Không tìm thấy file");
        } else if (status == OrderPrinter.PrintStatus.NOT_FOUND_PRINTER) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi không thể in hóa đơn: Không tìm thấy máy in");
        }
    }

    private void refeshForm() {
        Main.app.refeshOrder();
    }
}
