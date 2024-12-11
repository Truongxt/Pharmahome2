/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import connect.ConnectDB;
import dao.TaiKhoan_DAO;
import entity.TaiKhoan;
import gui.QuenMatKhau.DoiMatKhau;
import gui.QuenMatKhau.QuenMatKhau_GUI;
import gui.main.MainForm;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import main.Main;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import utilities.hashPassowrd;

/**
 *
 * @author Hà Như
 */
public class LoginForm extends javax.swing.JPanel {

    private static String userName;
    private static String password;
    private ArrayList<TaiKhoan> Listtk;
    private TaiKhoan tk;
    private hashPassowrd hashpwd= new  hashPassowrd();
    private LoginListener loginListener;

    public LoginForm() throws SQLException {
        initComponents();
        ConnectDB.connect();
        Listtk = new TaiKhoan_DAO().getAllTaiKhoan();
    }

    public void addLoginListener(LoginListener listener) {
        this.loginListener = listener;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btn_DangNhap = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txtMatKhau = new gui.QuenMatKhau.textfield.PasswordField();
        txtTaiKhoan = new gui.QuenMatKhau.textfield.TextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/GUI/pharmacy (1).png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setText("PHARMAHOME");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/GUI/alternative-medicine.png"))); // NOI18N

        btn_DangNhap.setBackground(new java.awt.Color(153, 255, 153));
        btn_DangNhap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btn_DangNhap.setText("Đăng Nhập");
        btn_DangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DangNhapActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jButton1.setText("Bạn quên mật khẩu?");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtMatKhau.setText("nhannhan1");
        txtMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatKhauActionPerformed(evt);
            }
        });

        txtTaiKhoan.setText("NV120924005");
        txtTaiKhoan.setToolTipText("");
        txtTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTaiKhoanActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Tên tài khoản");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Mật khẩu");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jButton1))
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_DangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_DangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addGap(70, 70, 70))
        );

        txtMatKhau.setShowAndHide(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(137, 137, 137)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_DangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DangNhapActionPerformed
        userName = txtTaiKhoan.getText();
        password = txtMatKhau.getText();
        try {
            tk = checkLogin(userName, password);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (tk != null) {
            loginListener.onLoginSuccess(tk);

        } else {
            loginListener.onLoginFailure();
        }
    }//GEN-LAST:event_btn_DangNhapActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      QuenMatKhau_GUI panel = null;
    try {
        panel = new QuenMatKhau_GUI(this);
    } catch (SQLException ex) {
        Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
    }

    // Tạo JDialog với panel là nội dung chính
    JDialog frame = new JDialog((Frame) null, "Quên mật khẩu", true);

    frame.getContentPane().add(panel); // Thêm panel vào JDialog
    frame.pack(); // Đảm bảo JDialog tự động thay đổi kích thước phù hợp
    frame.setLocationRelativeTo(null); // Đặt JDialog ở vị trí trung tâm của màn hình

    // Lắng nghe sự kiện khi dialog đóng
    frame.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent e) {
            resetLoginForm(); // Gọi reset khi dialog đóng
        }
    });

    // Hiển thị dialog
    frame.setVisible(true);
    
    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTaiKhoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTaiKhoanActionPerformed

    private void txtMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatKhauActionPerformed

    private TaiKhoan checkLogin(String username, String password) throws NoSuchAlgorithmException {
    String hashedPassword = hashpwd.hashPassword(password); // Băm mật khẩu nhập vào
    for (TaiKhoan tk : Listtk) {
        if (tk.getTen().equalsIgnoreCase(username) && tk.getPassword().equals(hashedPassword)) {
            return tk;
        }
    }
    return null;
}

  

    public interface LoginListener {

        void onLoginSuccess(TaiKhoan tk);

        void onLoginFailure();
    }
 public void resetLoginForm() {
   
        // Reset lại danh sách tài khoản
        Listtk = new TaiKhoan_DAO().getAllTaiKhoan();
        System.out.println("LoginForm đã reset lại danh sách tài khoản.");
   
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_DangNhap;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private gui.QuenMatKhau.textfield.PasswordField txtMatKhau;
    private gui.QuenMatKhau.textfield.TextField txtTaiKhoan;
    // End of variables declaration//GEN-END:variables
}
