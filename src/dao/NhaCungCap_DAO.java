/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.itextpdf.text.log.Logger;
import connect.ConnectDB;
import entity.NhaCungCap;
import java.lang.System.Logger.Level;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Xuân Trường
 */

public class NhaCungCap_DAO {

    public static Boolean create(NhaCungCap ncc) {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("insert NhaCungCap values (?,?,?,?,?,?) ");
            ps.setString(1, ncc.getMaNCC());
            ps.setString(2, ncc.getTenNCC());
            ps.setString(3, ncc.getDiaChi());
            ps.setString(4, ncc.getEmail());
            ps.setString(5, ncc.getSdt());
            ps.setBoolean(6, ncc.isTrangThai());
            n = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }


    public static ArrayList<NhaCungCap> getAllNhaCungCap() {
        ArrayList<NhaCungCap> list = new ArrayList<>();

        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("Select * from NhaCungCap");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNCC = rs.getString("maNCC");
                String tenNCC = rs.getString("tenNCC");
                String diaChi = rs.getString("diaChi");
                String email = rs.getString("email");
                String sdt = rs.getString("sdt");
                boolean trangThai = rs.getBoolean("trangThai");
                NhaCungCap ncc = new NhaCungCap(maNCC, tenNCC, diaChi, email, sdt, trangThai);
                list.add(ncc);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(NhanVien_DAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        return list;
    }
    public static NhaCungCap getNhaCungCap(String ma) {
        NhaCungCap nhaCungCap = null;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT * FROM NhaCungCap WHERE maNCC = ?");
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String maNCC = rs.getString("maNCC");
                String tenNCC = rs.getString("tenNCC");
                String diaChi = rs.getString("diaChi");
                String email = rs.getString("email");
                String sdt = rs.getString("sdt");
                boolean trangThai = rs.getBoolean("trangThai");
                return new NhaCungCap(maNCC, tenNCC, diaChi, email, sdt, trangThai);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(NhanVien_DAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

        }
        return null;
    }

    public static boolean suaNhaCungCap(String maNCC, NhaCungCap newNCC) throws SQLException {
        int n = 0;
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("UPDATE NhaCungCap set"
                    + "      [tenNCC] = ?,"
                    + "      [diaChi] = ?,"
                    + "      [email] = ?,"
                    + "      [sdt] = ?,"
                    + "      [trangThai] = ?"
                    + " WHERE maNCC = ?");
            st.setString(1, newNCC.getTenNCC());
            st.setString(2, newNCC.getDiaChi());
            st.setString(3, newNCC.getEmail());
            st.setString(4, newNCC.getSdt());
            st.setBoolean(5, newNCC.isTrangThai());
            st.setString(6, maNCC);
            n = st.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(NhanVien_DAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

        }
        return n > 0;
    }

    public static int getSize() {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("Select * from NhaCungCap");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                n++;
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(NhanVien_DAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return n;
    }

    public NhaCungCap getLastNhaCungCap() {
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("select top 1 * from NhaCungCap order by maNCC DESC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String maNCC = rs.getString("maNCC");
                String tenNCC = rs.getString("tenNCC");
                String diaChi = rs.getString("diaChi");
                String email = rs.getString("email");
                String sdt = rs.getString("sdt");
                boolean trangThai = rs.getBoolean("trangThai");

                return new NhaCungCap(maNCC, tenNCC, diaChi, email, sdt, trangThai);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(NhanVien_DAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

        }

        return null;
    }

    public ArrayList<NhaCungCap> timKiemTheoMa(String maNCC) {
        ArrayList<NhaCungCap> listNCC = new ArrayList<>();
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("select * from NhaCungCap where maNCC like ?");
            ps.setString(1, "%" + maNCC + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("maNCC");
                String tenNCC = rs.getString("tenNCC");
                String diaChi = rs.getString("diaChi");
                String email = rs.getString("email");
                String sdt = rs.getString("sdt");
                boolean trangThai = rs.getBoolean("trangThai");

                NhaCungCap ncc = new NhaCungCap(ma, tenNCC, diaChi, email, sdt, trangThai);
                listNCC.add(ncc);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(NhanVien_DAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return listNCC;
    }

    public ArrayList<NhaCungCap> timKiemTheoTen(String tenNCC) {
        ArrayList<NhaCungCap> listNCC = new ArrayList<>();
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("select * from NhaCungCap where tenNCC like ?");
            ps.setString(1, "%" + tenNCC + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNCC = rs.getString("maNCC");
                String ten = rs.getString("tenNCC");
                String diaChi = rs.getString("diaChi");
                String email = rs.getString("email");
                String sdt = rs.getString("sdt");
                boolean trangThai = rs.getBoolean("trangThai");

                NhaCungCap ncc = new NhaCungCap(maNCC, ten, diaChi, email, sdt, trangThai);
                listNCC.add(ncc);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(NhanVien_DAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return listNCC;
    }

    public NhaCungCap timTheoSDT(String soDT) {
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("select * from NhaCungCap where sdt like ?");
            ps.setString(1, "%" + soDT + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNCC = rs.getString("maNCC");
                String tenNCC = rs.getString("tenNCC");
                String diaChi = rs.getString("diaChi");
                String email = rs.getString("email");
                String sdt = rs.getString("sdt");
                boolean trangThai = rs.getBoolean("trangThai");
                NhaCungCap ncc = new NhaCungCap(maNCC, tenNCC, diaChi, email, sdt, trangThai);
                return ncc;
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(NhanVien_DAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return null;
}
}
