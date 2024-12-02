package dao;

import entity.TaiKhoan;
import entity.NhanVien;
import entity.VaiTro;
import connect.ConnectDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaiKhoan_DAO {

    public boolean create(TaiKhoan tk) {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("INSERT INTO TaiKhoan (tenTaiKhoan, password, trangThai, vaiTro, nhanVien) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, tk.getTen());
            ps.setString(2, tk.getPassword());
            ps.setBoolean(3, tk.isTrangThai());
            ps.setString(4, tk.getVaiTro().getMaVaiTro());
            ps.setString(5, tk.getNhanVien().getMaNhanVien());
            n = ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(TaiKhoan_DAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return n > 0;
    }

    public ArrayList<TaiKhoan> getAllTaiKhoan() {
        ArrayList<TaiKhoan> list = new ArrayList<>();
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT * FROM TaiKhoan");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ten = rs.getString("tenTaiKhoan");
                String password = rs.getString("password");
                boolean trangThai = rs.getBoolean("trangThai");
                String mavaitro = rs.getString("maVaiTro");
                String manhanvien = rs.getString("maNhanVien");
                // Chuyển đổi vaiTro và nhanVien nếu cần thiết
                VaiTro vaiTro = new VaiTro(mavaitro);
                NhanVien nhanVien = new NhanVien(manhanvien);
                TaiKhoan tk = new TaiKhoan(ten, password, trangThai, vaiTro, nhanVien);
                list.add(tk);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoan_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public TaiKhoan getTaiKhoan(String ten) {
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT * FROM TaiKhoan WHERE tenTaiKhoan = ?");
            ps.setString(1, ten);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String password = rs.getString("password");
                boolean trangThai = rs.getBoolean("trangThai");
                String mavaitro = rs.getString("maVaiTro");
                String manhanvien = rs.getString("maNhanVien");
                // Chuyển đổi vaiTro và nhanVien nếu cần thiết
                VaiTro vaiTro = new VaiTro(mavaitro);
                NhanVien nhanVien = new NhanVien(manhanvien);
                return new TaiKhoan(ten, password, trangThai, vaiTro, nhanVien);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoan_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateTaiKhoan(TaiKhoan tk) {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("UPDATE TaiKhoan SET password = ?, trangThai = ?, vaiTro = ?, nhanVien = ? WHERE ten = ?");
            ps.setString(1, tk.getPassword());
            ps.setBoolean(2, tk.isTrangThai());
            ps.setString(3, tk.getVaiTro().toString());
            ps.setString(4, tk.getNhanVien().toString());
            ps.setString(5, tk.getTen());
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoan_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n > 0;
    }

    public boolean deleteTaiKhoan(String ten) {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("DELETE FROM TaiKhoan WHERE ten = ?");
            ps.setString(1, ten);
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoan_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n > 0;
    }

    public boolean doiMatKHau(String ten, String mk) {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("UPDATE TaiKhoan SET password = ? WHERE tenTaiKhoan = ?");
            ps.setString(1, mk);
            ps.setString(2, ten);

            n = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoan_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n > 0;
    }

}
