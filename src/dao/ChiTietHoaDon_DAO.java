package dao;

import entity.ChiTietHoaDon;
import entity.Thuoc;
import entity.HoaDon;
import connect.ConnectDB;
import entity.ThuocVaLuotBan;
import java.sql.*;
import java.util.ArrayList;
import entity.ThuocvaDoanhThu;
import java.time.LocalDate;
import java.time.YearMonth;

public class ChiTietHoaDon_DAO {

    // Phương thức tạo mới một đối tượng ChiTietHoaDon
    public boolean create(ChiTietHoaDon chiTiet) {
        int n = 0;

        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("INSERT INTO ChiTietHoaDon VALUES (?,?,?,?)");
            ps.setString(1, chiTiet.getHoaDon().getMaHD());
            ps.setString(2, chiTiet.getThuoc().getMaThuoc());
            ps.setInt(3, chiTiet.getSoLuong());
            ps.setDouble(4, chiTiet.getDonGia());
            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Phương thức lấy tất cả các đối tượng ChiTietHoaDon
    public static ArrayList<ChiTietHoaDon> getAllChiTietHoaDon() {
        ArrayList<ChiTietHoaDon> list = new ArrayList<>();

        try {
            ConnectDB.connect();
            String sql = "SELECT * FROM ChiTietHoaDon";
            PreparedStatement ps = ConnectDB.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int soLuong = rs.getInt("soLuong");
                double donGia = rs.getDouble("donGia");

                String maThuoc = rs.getString("maThuoc");
                String maHoaDon = rs.getString("maHoaDon");

                Thuoc thuoc = new Thuoc_DAO().getThuoc(maThuoc);  // Lấy thông tin thuốc
                HoaDon hoaDon = new HoaDon_DAO().getHoaDon(maHoaDon); // Lấy thông tin hóa đơn

                ChiTietHoaDon chiTiet = new ChiTietHoaDon(soLuong, donGia, thuoc, hoaDon);
                list.add(chiTiet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Phương thức lấy một đối tượng ChiTietHoaDon theo mã thuốc và mã hóa đơn
    public ChiTietHoaDon getChiTietHoaDon(String maThuoc, String maHoaDon) {
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT * FROM ChiTietHoaDon WHERE maThuoc = ? AND maHoaDon = ?");
            ps.setString(1, maThuoc);
            ps.setString(2, maHoaDon);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int soLuong = rs.getInt("soLuong");
                double donGia = rs.getDouble("donGia");

                Thuoc thuoc = new Thuoc_DAO().getThuoc(maThuoc);
                HoaDon hoaDon = new HoaDon_DAO().getHoaDon(maHoaDon);

                return new ChiTietHoaDon(soLuong, donGia, thuoc, hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức cập nhật thông tin một đối tượng ChiTietHoaDon
    public boolean suaChiTietHoaDon(String maThuoc, String maHoaDon, ChiTietHoaDon newChiTiet) {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement(
                    "UPDATE ChiTietHoaDon SET soLuong = ?, donGia = ? WHERE maThuoc = ? AND maHoaDon = ?");
            ps.setInt(1, newChiTiet.getSoLuong());
            ps.setDouble(2, newChiTiet.getDonGia());
            ps.setString(3, maThuoc);
            ps.setString(4, maHoaDon);
            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Phương thức xóa một đối tượng ChiTietHoaDon
    public boolean deleteChiTietHoaDon(String maThuoc, String maHoaDon) {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("DELETE FROM ChiTietHoaDon WHERE maThuoc = ? AND maHoaDon = ?");
            ps.setString(1, maThuoc);
            ps.setString(2, maHoaDon);
            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Phương thức lấy số lượng chi tiết hóa đơn
    public int getSize() {
        int n = 0;
        try {
            PreparedStatement ps = ConnectDB.conn.prepareStatement("SELECT COUNT(*) AS total FROM ChiTietHoaDon");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                n = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public ArrayList<ThuocvaDoanhThu> getTop10ThuocCoDoanhThuCaoNhat() {
        ArrayList<ThuocvaDoanhThu> topThuocList = new ArrayList<>();

        try {
            String sql = "SELECT TOP 10 maThuoc, SUM(soLuong * donGia) AS doanhThu "
                    + "FROM ChiTietHoaDon "
                    + "GROUP BY maThuoc "
                    + "ORDER BY doanhThu DESC";
            PreparedStatement ps = ConnectDB.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String maThuoc = rs.getString("maThuoc");
                double doanhThu = rs.getDouble("doanhThu");

                Thuoc thuoc = new Thuoc_DAO().getThuoc(maThuoc);
                ThuocvaDoanhThu thuocDoanhThu = new ThuocvaDoanhThu(thuoc, doanhThu);
                topThuocList.add(thuocDoanhThu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topThuocList;
    }

    public double getDoanhThu(String maThuoc) {
        double doanhThu = 0;

        try {
            String sql = "SELECT SUM(soLuong * donGia) AS doanhThu "
                    + "FROM ChiTietHoaDon "
                    + "WHERE maThuoc = ? "
                    + // ThÃªm dáº¥u cá»™ng
                    "GROUP BY maThuoc "
                    + "ORDER BY doanhThu DESC";

            PreparedStatement ps = ConnectDB.conn.prepareStatement(sql);
            ps.setString(1, maThuoc); // Sá»­ dá»¥ng setString náº¿u maThuoc lÃ  String
            ResultSet rs = ps.executeQuery(); // Gá»i executeQuery() sau khi Ä‘áº·t tham sá»‘

            while (rs.next()) {

                doanhThu = rs.getDouble("doanhThu");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(doanhThu);
        return doanhThu;
    }

    public int getsoLuongBan(String maThuoc) {
        int soLuong = 0;

        try {
            String sql = "SELECT  sum(soLuong) as soLuong FROM ChiTietHoaDon WHERE maThuoc = ? ";

            PreparedStatement ps = ConnectDB.conn.prepareStatement(sql);
            ps.setString(1, maThuoc); // Sá»­ dá»¥ng setString náº¿u maThuoc lÃ  String
            ResultSet rs = ps.executeQuery(); // Gá»i executeQuery() sau khi Ä‘áº·t tham sá»‘

            while (rs.next()) {

                soLuong = rs.getInt("soLuong");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(soLuong);
        return soLuong;
    }

    public ArrayList<ThuocVaLuotBan> getThuocCoLuotBanCaoNhatTrongThang(int thang, int nam) {
        ArrayList<ThuocVaLuotBan> topThuocList = new ArrayList<>();

        try {
            // TÃ­nh toÃ¡n ngÃ y Ä‘áº§u vÃ  ngÃ y cuá»‘i cá»§a thÃ¡ng
            YearMonth yearMonth = YearMonth.of(nam, thang);
            LocalDate firstDayOfMonth = yearMonth.atDay(1);
            LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();

            // Láº¥y danh sÃ¡ch sá»‘ lÆ°á»£ng bÃ¡n cá»§a tá»«ng loáº¡i thuá»‘c trong thÃ¡ng cá»¥ thá»ƒ
            String sql = "SELECT c.maThuoc, SUM(c.soLuong) AS soLuong "
                    + "FROM HoaDon h "
                    + "JOIN ChiTietHoaDon c ON h.maHD = c.maHD "
                    + "WHERE h.ngayLap BETWEEN ? AND ? "
                    + "GROUP BY c.maThuoc "
                    + "ORDER BY soLuong DESC";  // Sáº¯p xáº¿p theo sá»‘ lÆ°á»£ng bÃ¡n giáº£m dáº§n

            PreparedStatement ps = ConnectDB.conn.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(firstDayOfMonth)); // NgÃ y Ä‘áº§u thÃ¡ng
            ps.setDate(2, java.sql.Date.valueOf(lastDayOfMonth));   // NgÃ y cuá»‘i thÃ¡ng

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String maThuoc = rs.getString("maThuoc");
                int luotBan = rs.getInt("soLuong");

                // Láº¥y thÃ´ng tin thuá»‘c
                Thuoc thuoc = new Thuoc_DAO().getThuocTheoMa(maThuoc);
                ThuocVaLuotBan thuocLuotBan = new ThuocVaLuotBan(thuoc, luotBan);
                topThuocList.add(thuocLuotBan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topThuocList;
    }

    public ThuocVaLuotBan getTop1ThuocCoLuotBanCaoNhatTrongThang(int thang, int nam) {
        ThuocVaLuotBan topThuocList = null;

        try {
            // TÃ­nh toÃ¡n ngÃ y Ä‘áº§u vÃ  ngÃ y cuá»‘i cá»§a thÃ¡ng

            // Láº¥y danh sÃ¡ch sá»‘ lÆ°á»£ng bÃ¡n cá»§a tá»«ng loáº¡i thuá»‘c trong thÃ¡ng cá»¥ thá»ƒ
            String sql = "SELECT top 1 c.maThuoc, SUM(c.soLuong) AS soLuong "
                    + "FROM HoaDon h "
                    + "JOIN ChiTietHoaDon c ON h.maHD = c.maHD "
                    + "WHERE month(h.ngayLap)=? AND year(h.ngayLap)=? "
                    + "GROUP BY c.maThuoc "
                    + "ORDER BY soLuong DESC";  // Sáº¯p xáº¿p theo sá»‘ lÆ°á»£ng bÃ¡n giáº£m dáº§n

            PreparedStatement ps = ConnectDB.conn.prepareStatement(sql);
            ps.setInt(1, thang); // NgÃ y Ä‘áº§u thÃ¡ng
            ps.setInt(2, nam);   // NgÃ y cuá»‘i thÃ¡ng

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String maThuoc = rs.getString("maThuoc");
                int luotBan = rs.getInt("soLuong");

                // Láº¥y thÃ´ng tin thuá»‘c
                Thuoc thuoc = new Thuoc_DAO().getThuocTheoMa(maThuoc);
                topThuocList = new ThuocVaLuotBan(thuoc, luotBan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topThuocList;

    }

    public ThuocvaDoanhThu getTop1ThuocCoDoanhThuCaoNhatTrongThang(int thang, int nam) {
        ThuocvaDoanhThu topThuocList = null;

        try {

            String sql = "SELECT c.maThuoc, SUM(c.soLuong * c.donGia) AS doanhThu "
                    + "FROM HoaDon h "
                    + "JOIN ChiTietHoaDon c ON h.maHD = c.maHD "
                    + "WHERE month(h.ngayLap)=? AND year(h.ngayLap)=? "
                    + "GROUP BY c.maThuoc "
                    + "ORDER BY doanhThu DESC";  // Sáº¯p xáº¿p theo doanh thu giáº£m dáº§n

            PreparedStatement ps = ConnectDB.conn.prepareStatement(sql);
            ps.setInt(1, thang); // NgÃ y Ä‘áº§u thÃ¡ng
            ps.setInt(2, nam);   // NgÃ y cuá»‘i thÃ¡ng

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String maThuoc = rs.getString("maThuoc");
                double doanhThu = rs.getDouble("doanhThu");

                // Láº¥y thÃ´ng tin thuá»‘c
                Thuoc thuoc = new Thuoc_DAO().getThuocTheoMa(maThuoc);
                topThuocList = new ThuocvaDoanhThu(thuoc, doanhThu);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topThuocList;
    }
}
