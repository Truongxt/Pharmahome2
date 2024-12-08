
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.NhanVien;
import java.sql.PreparedStatement;
import connect.ConnectDB;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Xuân Trường
 */
public class NhanVien_DAO {

    public Boolean create(NhanVien nv) {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("insert NhanVien values (?,?,?,?,?,?,?,?) ");
            ps.setString(1, nv.getMaNhanVien());
            ps.setString(2, nv.getTenNhanVien());
            ps.setString(3, nv.getEmail());
            ps.setString(4, nv.getDiaChi());
            ps.setString(5, nv.getSdt());
            ps.setString(6, nv.getCccd());
            ps.setBoolean(7, nv.isTrangThai());
            ps.setDate(8, java.sql.Date.valueOf(nv.getNgayVaolam()));
            n = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public ArrayList<NhanVien> getAllNhanVien() {
        ArrayList<NhanVien> list = new ArrayList<>();

        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("Select * from NhanVien");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString("maNhanVien");
                String tenNhanVien = rs.getString("tenNhanVien");
                String email = rs.getString("email");
                String diaChi = rs.getString("diaChi");
                String sdt = rs.getString("sdt");
                String cccd = rs.getString("cccd");
                boolean trangThai = rs.getBoolean("trangThai");
                Date ngayVaoLam = rs.getDate("ngayVaoLam");
                LocalDate nvl = LocalDate.of(ngayVaoLam.getYear() + 1900, ngayVaoLam.getMonth() + 1, ngayVaoLam.getDate());
                NhanVien nv = new NhanVien(maNV, tenNhanVien, email, sdt, diaChi, cccd, trangThai, nvl);
                list.add(nv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public NhanVien getNhanVien(String maNhanVien) {
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("Select * from NhanVien");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString("maNhanVien");
                if (maNV.equalsIgnoreCase(maNhanVien)) {
                    String tenNhanVien = rs.getString("tenNhanVien");
                    String email = rs.getString("email");
                    String diaChi = rs.getString("diaChi");
                    String sdt = rs.getString("sdt");
                    String cccd = rs.getString("cccd");
                    boolean trangThai = rs.getBoolean("trangThai");
                    Date ngayVaoLam = rs.getDate("ngayVaoLam");
                    LocalDate nvl = LocalDate.of(ngayVaoLam.getYear(), ngayVaoLam.getMonth() + 1, ngayVaoLam.getDate());
                    NhanVien nv = new NhanVien(maNV, tenNhanVien, email, sdt, diaChi, cccd, trangThai, nvl);
                    return nv;
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean suaNhanVien(String maNhanvien, NhanVien newNV) throws SQLException {
        int n = 0;
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("UPDATE NhanVien set"
                    + "      [tenNhanVien] = ?,"
                    + "      [email] = ?,"
                    + "      [sdt] = ?,"
                    + "      [diaChi] = ?,"
                    + "      [cccd] = ?,"
                    + "      [trangThai] = ?,"
                    + "      [ngayVaoLam] = ?"
                    + " WHERE maNhanVien = ?");
            st.setString(1, newNV.getTenNhanVien());
            st.setString(2, newNV.getEmail());
            st.setString(3, newNV.getSdt());
            st.setString(4, newNV.getDiaChi());
            st.setString(5, newNV.getCccd());
            st.setBoolean(6, newNV.isTrangThai());
            st.setDate(7, new Date(newNV.getNgayVaolam().getYear() - 1900, newNV.getNgayVaolam().getMonthValue() - 1, newNV.getNgayVaolam().getDayOfMonth()));
            st.setString(8, maNhanvien);
            n = st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n > 0;
    }

    public int getSize() {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("Select * from NhanVien");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                n++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    //layNhanVienCuoi
    public NhanVien getLastNhanVien() {
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("select top 1 * from [dbo].[NhanVien] order by maNhanVien DESC");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString("maNhanVien");
                String tenNhanVien = rs.getString("tenNhanVien");
                String email = rs.getString("email");
                String diaChi = rs.getString("diaChi");
                String sdt = rs.getString("sdt");
                String cccd = rs.getString("cccd");
                boolean trangThai = rs.getBoolean("trangThai");
                Date ngayVaoLam = rs.getDate("ngayVaoLam");
                LocalDate nvl = LocalDate.of(ngayVaoLam.getYear(), ngayVaoLam.getMonth() + 1, ngayVaoLam.getDate());
                NhanVien nv = new NhanVien(maNV, tenNhanVien, email, sdt, diaChi, cccd, trangThai, nvl);
                return nv;

            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<NhanVien> timKiemTheoMa(String maNhanVien) {
        ArrayList<NhanVien> listNV = new ArrayList<>();
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("select * from NhanVien where maNhanVien like ?");
            ps.setString(1, "%" + maNhanVien + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString("maNhanVien");
                String tenNhanVien = rs.getString("tenNhanVien");
                String email = rs.getString("email");
                String diaChi = rs.getString("diaChi");
                String sdt = rs.getString("sdt");
                String cccd = rs.getString("cccd");
                boolean trangThai = rs.getBoolean("trangThai");
                Date ngayVaoLam = rs.getDate("ngayVaoLam");
                LocalDate nvl = LocalDate.of(ngayVaoLam.getYear(), ngayVaoLam.getMonth() + 1, ngayVaoLam.getDate());
                NhanVien nv = new NhanVien(maNV, tenNhanVien, email, sdt, diaChi, cccd, trangThai, nvl);
                listNV.add(nv);

            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listNV;
    }

    public NhanVien timKiemTheoMa1(String maNhanVien) {
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("select * from NhanVien where maNhanVien like ?");
            ps.setString(1, "%" + maNhanVien + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString("maNhanVien");
                String tenNhanVien = rs.getString("tenNhanVien");
                String email = rs.getString("email");
                String diaChi = rs.getString("diaChi");
                String sdt = rs.getString("sdt");
                String cccd = rs.getString("cccd");
                boolean trangThai = rs.getBoolean("trangThai");
                Date ngayVaoLam = rs.getDate("ngayVaoLam");
                LocalDate nvl = LocalDate.of(ngayVaoLam.getYear(), ngayVaoLam.getMonth() + 1, ngayVaoLam.getDate());
                NhanVien nv = new NhanVien(maNV, tenNhanVien, email, sdt, diaChi, cccd, trangThai, nvl);
                return nv;

            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<NhanVien> timKiemTheoTen(String ten) {
        ArrayList<NhanVien> listNV = new ArrayList<>();
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("select * from NhanVien where tenNhanVien like ?");
            ps.setString(1, "%" + ten + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString("maNhanVien");
                String tenNhanVien = rs.getString("tenNhanVien");
                String email = rs.getString("email");
                String diaChi = rs.getString("diaChi");
                String sdt = rs.getString("sdt");
                String cccd = rs.getString("cccd");
                boolean trangThai = rs.getBoolean("trangThai");
                Date ngayVaoLam = rs.getDate("ngayVaoLam");
                LocalDate nvl = LocalDate.of(ngayVaoLam.getYear(), ngayVaoLam.getMonth() + 1, ngayVaoLam.getDate());
                NhanVien nv = new NhanVien(maNV, tenNhanVien, email, sdt, diaChi, cccd, trangThai, nvl);
                listNV.add(nv);

            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listNV;
    }

    public ArrayList<NhanVien> timKiemTheoTrangThai(boolean tt) {
        ArrayList<NhanVien> listNV = new ArrayList<>();
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("select * from NhanVien where trangThai = ?");
            ps.setBoolean(1, tt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString("maNhanVien");
                String tenNhanVien = rs.getString("tenNhanVien");
                String email = rs.getString("email");
                String diaChi = rs.getString("diaChi");
                String sdt = rs.getString("sdt");
                String cccd = rs.getString("cccd");
                boolean trangThai = rs.getBoolean("trangThai");
                Date ngayVaoLam = rs.getDate("ngayVaoLam");
                LocalDate nvl = LocalDate.of(ngayVaoLam.getYear(), ngayVaoLam.getMonth() + 1, ngayVaoLam.getDate());
                NhanVien nv = new NhanVien(maNV, tenNhanVien, email, sdt, diaChi, cccd, trangThai, nvl);
                listNV.add(nv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listNV;
    }

    public ArrayList<NhanVien> timTheoSDT(String soDT) {
        ArrayList<NhanVien> listNV = new ArrayList<>();
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("select * from NhanVien where sdt like ?");
            ps.setString(1, "%" + soDT + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString("maNhanVien");
                String tenNhanVien = rs.getString("tenNhanVien");
                String email = rs.getString("email");
                String diaChi = rs.getString("diaChi");
                String sdt = rs.getString("sdt");
                String cccd = rs.getString("cccd");
                boolean trangThai = rs.getBoolean("trangThai");
                Date ngayVaoLam = rs.getDate("ngayVaoLam");
                LocalDate nvl = LocalDate.of(ngayVaoLam.getYear(), ngayVaoLam.getMonth() + 1, ngayVaoLam.getDate());
                NhanVien nv = new NhanVien(maNV, tenNhanVien, email, sdt, diaChi, cccd, trangThai, nvl);
                listNV.add(nv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listNV;
    }

    public String generateID() {
        String result = "NV";
        LocalDate time = LocalDate.now();
        String query = """
                       select top 1 * from [HoaDon]
                       where maHD like ?
                       order by maHD desc
                       """;

        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement(query);
            st.setString(1, result + "%");
            ResultSet rs = st.executeQuery();
            LocalDate ngayVaoLam = LocalDate.now();
            String date = String.format("%02d",ngayVaoLam.getDayOfMonth()) + "" + ngayVaoLam.getMonthValue() + "" + (ngayVaoLam.getYear() + "").substring(2);
            if (rs.next()) {
                String lastID = rs.getString("maHD");
                String sNumber = lastID.substring(lastID.length() - 2);
                int num = Integer.parseInt(sNumber) + 1;
                result += date + lastID.substring(2) + String.format("%03d", num);
            } else {
                result += date + String.format("%05d", 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
