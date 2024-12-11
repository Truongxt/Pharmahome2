/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connect.ConnectDB;
import entity.KhachHang;
import entity.NhanVien;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import utilities.ConvertDate;

/**
 *
 * @author lemin
 */
public class KhachHang_DAO {

    public Boolean create(KhachHang kh) {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("insert KhachHang values (?,?,?,?,?) ");
            ps.setString(1, kh.getMaKH());
            ps.setString(2, kh.getTenKhachHang());
            ps.setString(3, kh.getSdt());
            ps.setString(4, kh.getDiaChi());
            ps.setDate(4, (Date) kh.getNgayLapTaiKhoan());

            n = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public ArrayList<KhachHang> getAllKhachHang() {
        ArrayList<KhachHang> list = new ArrayList<>();

        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("Select * from KhachHang");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maKhachHang = rs.getString("maKhachHang");
                String tenKhachHang = rs.getString("tenKhachHang");
                String sdt = rs.getString("sdt");
                String diaChi = rs.getString("diaChi");
                Date ngayTao = rs.getDate("ngayLapTaiKhoan");
                KhachHang kh = new KhachHang(maKhachHang, tenKhachHang, sdt, diaChi, ngayTao);
                list.add(kh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<KhachHang> timKiemTheoMa(String maKhachHang) {
        ArrayList<KhachHang> listKH = new ArrayList<>();
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("select * from KhachHang where maKhachHang = ?");
            ps.setString(1, "%" + maKhachHang + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String tenKhachHang = rs.getString("tenKhachHang");
                String sdt = rs.getString("sdt");
                String diaChi = rs.getString("diaChi");
                Date ngayTao = rs.getDate("ngayLapTaiKhoan");
                KhachHang kh = new KhachHang(maKhachHang, tenKhachHang, sdt, diaChi, ngayTao);
                listKH.add(kh);

            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listKH;
    }

    public KhachHang getKhachHangSDT(String soDienThoai) {
        KhachHang khachHang = null;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT * FROM KhachHang WHERE sdt = ?");
            ps.setString(1, soDienThoai);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String maKhachHang = rs.getString("maKhachHang");
                String tenKhachHang = rs.getString("tenKhachHang");
                String sdt = rs.getString("sdt");
                String diaChi = rs.getString("diaChi");
                Date ngayTao = rs.getDate("ngayLapTaiKhoan");
                KhachHang kh = new KhachHang(maKhachHang, tenKhachHang, sdt, diaChi, ngayTao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khachHang;
    }

    public static KhachHang getKhachHang(String maKH) {
        KhachHang khachHang = null;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT * FROM KhachHang WHERE maKhachHang = ?");
            ps.setString(1, maKH);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String maKhachHang = rs.getString("maKhachHang");
                String tenKhachHang = rs.getString("tenKhachHang");
                String sdt = rs.getString("sdt");
                String diaChi = rs.getString("diaChi");
                Date ngayTao = rs.getDate("ngayLapTaiKhoan");
                KhachHang kh = new KhachHang(maKhachHang, tenKhachHang, sdt, diaChi, ngayTao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khachHang;
    }

    public String TaoID(java.util.Date date, boolean gender) {
        //Khởi tạo mã Khách hàng KH
        String prefix = "KH";
        //4 Kí tự kế tiếp là năm sinh khách hàng
        int nam = LocalDate.now().getYear();
        int thang = LocalDate.now().getMonthValue();
        int ngay = LocalDate.now().getDayOfMonth();
        prefix += nam + thang + ngay + generateRandomString(6);

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

    public static Boolean taoMoi(KhachHang kh) {
        try {
            String phoneCheck = "select * from KhachHang where sdt = ?";
            PreparedStatement phoneStatement = ConnectDB.conn.prepareStatement(phoneCheck);
            phoneStatement.setString(1, kh.getSdt());
            if (phoneStatement.executeQuery().next()) {
                return false;
            }

            String sql = "INSERT INTO KhachHang (maKhachHang, tenKhachHang, sdt, diaChi, ngayLapTaiKhoan) " + "VALUES (?, ?, ?, ?,?)";
            PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(sql);

            preparedStatement.setString(1, kh.getMaKH());
            preparedStatement.setString(2, kh.getTenKhachHang());
            preparedStatement.setString(3, kh.getSdt());
            preparedStatement.setString(4, kh.getDiaChi());
            preparedStatement.setDate(5, (Date) kh.getNgayLapTaiKhoan());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean capNhat(String ma, KhachHang newKh) {
        try {
            String sql = "UPDATE KhachHang SET tenKhachHang=?, sdt=?, diaChi=?, ngayLapTaiKhoan = ? " + "WHERE maKhachHang=?";
            PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(sql);

            preparedStatement.setString(1, newKh.getTenKhachHang());
            preparedStatement.setString(2, newKh.getSdt());
            preparedStatement.setString(3, newKh.getDiaChi());
            preparedStatement.setDate(4, (Date) newKh.getNgayLapTaiKhoan());

            preparedStatement.setString(4, ma);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String generateID() {
        String result = "KH";
        LocalDate time = LocalDate.now();
        DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("ddMMyy");

        result += dateFormater.format(time);
        String query = """
                       select top 1 * from [KhachHang]
                       where maKhachHang like ?
                       order by maKhachHang desc
                       """;

        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement(query);
            st.setString(1, result + "%");
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String lastID = rs.getString("maKhachHang");
                String sNumber = lastID.substring(lastID.length() - 2);
                int num = Integer.parseInt(sNumber) + 1;
                result += String.format("%05d", num);
            } else {
                result += String.format("%05d", 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public ArrayList<KhachHang> getAllTKKhachHang() {
        ArrayList<KhachHang> list = new ArrayList<>();

        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("Select * from KhachHang");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maKhachHang = rs.getString("maKhachHang");
                String tenKhachHang = rs.getString("tenKhachHang");
                String sdt = rs.getString("sdt");
                 String diaChi = rs.getString("diaChi");
                Date ngayTaoTK = rs.getDate("NgayLapTaiKhoan");
                KhachHang kh = new KhachHang(maKhachHang, tenKhachHang, sdt, diaChi, ngayTaoTK);
                list.add(kh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<KhachHang> getAllTKKhachHangMonth(int month, int year) {
        ArrayList<KhachHang> list = new ArrayList<>();

        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT TOP (1000) "
                    + "    [maKhachHang],"
                    + "    [tenKhachHang],"
                    + "    [sdt],"
                    + "    [diaChi],"
                    + "    [NgayLapTaiKhoan]"
                    + "FROM "
                    + "    [QLNT].[dbo].[KhachHang]"
                    + "WHERE "
                    + "    MONTH(NgayLapTaiKhoan)=? and year(ngaylaptaikhoan)=?");
            ps.setInt(1, month);
            ps.setInt(2, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maKhachHang = rs.getString("maKhachHang");
                String tenKhachHang = rs.getString("tenKhachHang");
                String sdt = rs.getString("sdt");
                String diaChi = rs.getString("diaChi");
                Date ngayTaoTK = rs.getDate("NgayLapTaiKhoan");
                KhachHang kh = new KhachHang(maKhachHang, tenKhachHang, sdt, diaChi, ngayTaoTK);
                list.add(kh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<KhachHang> getAllTKKhachHangYear(int year) {
        ArrayList<KhachHang> list = new ArrayList<>();

        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT TOP (1000) "
                    + "    [maKhachHang],"
                    + "    [tenKhachHang],"
                    + "    [sdt],"
                    + "    [diaChi],"
                    + "    [NgayLapTaiKhoan]"
                    + "FROM "
                    + "    [QLNT].[dbo].[KhachHang]"
                    + "WHERE "
                    + "   year(ngaylaptaikhoan)=?");

            ps.setInt(1, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maKhachHang = rs.getString("maKhachHang");
                String tenKhachHang = rs.getString("tenKhachHang");
                String sdt = rs.getString("sdt");
                String diaChi = rs.getString("diaChi");
                Date ngayTaoTK = rs.getDate("NgayLapTaiKhoan");
                KhachHang kh = new KhachHang(maKhachHang, tenKhachHang, sdt, diaChi, ngayTaoTK);
                list.add(kh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<KhachHang> getTKKhachHangDoanhThu(String dau) {
        ArrayList<KhachHang> list = new ArrayList<>();

        try {
            String sql = "SELECT "
                    + "    k.maKhachHang, " // Lấy mã khách hàng
                    + "    k.sdt, "
                    + "    k.tenKhachHang, "
                    + "    k.NgayLapTaiKhoan, " // Thêm NgayLapTaiKhoan vào SELECT
                    + "    COUNT(h.maHD) AS soHoaDon, "
                    + "    SUM(h.tongTien) AS doanhThu, "
                    + "    k.diaChi "
                    + "FROM "
                    + "    HoaDon h "
                    + "JOIN "
                    + "    KhachHang k "
                    + "ON "
                    + "    h.maKH = k.maKhachHang "
                    + "GROUP BY "
                    + "    k.maKhachHang, k.sdt, k.tenKhachHang, k.diaChi, k.NgayLapTaiKhoan " // Thêm NgayLapTaiKhoan vào GROUP BY
                    + dau;  // Điều kiện động từ tham số dau (HAVING)
            // Điều kiện động từ tham số dau (HAVING)

            PreparedStatement ps = ConnectDB.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) { 
                String maKhachHang = rs.getString("maKhachHang");
                String tenKhachHang = rs.getString("tenKhachHang");
                String sdt = rs.getString("sdt");
                String diaChi = rs.getString("diaChi");
                Date ngayTaoTK = rs.getDate("NgayLapTaiKhoan");
                KhachHang kh = new KhachHang(maKhachHang, tenKhachHang, sdt, diaChi, ngayTaoTK);
                list.add(kh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
}
