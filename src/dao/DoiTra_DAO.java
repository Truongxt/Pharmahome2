/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connect.ConnectDB;
import entity.ChiTietDoiTra;
import entity.DoiTra;
import entity.HoaDon;
import entity.NhanVien;
import enums.TrangThaiDoiTra;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import raven.toast.Notifications;

/**
 *
 * @author HÀ NHƯ
 */
public class DoiTra_DAO {
    
    public static String getMaxSequence(String prefix) {
        try {
        prefix += "%";
        String sql = "  SELECT TOP 1  * FROM HoaDonDoiTra WHERE maHoaDonDoiTra LIKE '"+prefix+"' ORDER BY maHoaDonDoiTra DESC;";
        PreparedStatement st = ConnectDB.conn.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            String maHDDT = rs.getString("maHoaDonDoiTra");
            return maHDDT;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
    }

    
//    @Override
    public DoiTra getOne(String id) {
        DoiTra returnOrder = null;
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("SELECT * FROM HoaDonDoiTra WHERE maHoaDonDoiTra = ?");
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {                
                String maHDDT = rs.getString("maHoaDonDoiTra");
                boolean loai = rs.getBoolean("loaiDoiTra");
//                int trangThai = rs.getInt("trangThai");
                Date ngayDT = rs.getDate("ngayDoiTra");
                String maNV = rs.getString("maNhanVien");
                String maHD = rs.getString("maHoaDon");
                double tienTra = rs.getDouble("soTienTra");
                String liDo = rs.getString("moTa");
                HoaDon hoaDon = new HoaDon_DAO().getHoaDon(maHD);
                NhanVien nhVien = new NhanVien_DAO().getNhanVien(maNV);
                ArrayList<ChiTietDoiTra> listDetail = getAllReturnOrderDetail(maHDDT);
                returnOrder = new DoiTra(ngayDT, maHDDT, nhVien, hoaDon, loai, tienTra, listDetail, liDo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnOrder;
    }

//    @Override
    public ArrayList<DoiTra> getAll() {
        ArrayList<DoiTra> result = new ArrayList<>();
        try {
            Statement st = ConnectDB.conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM HoaDonDoiTra");
            
            while (rs.next()) {                
                String maHDDT = rs.getString("maHoaDonDoiTra");
                String maHD = rs.getString("maHoaDon");
//                int trangThai = rs.getInt("trangThai");
                Date ngayDT = rs.getDate("ngayDoiTra");
                String maNV = rs.getString("maNhanVien");
                boolean loai = rs.getBoolean("loaiDoiTra");
                double tienTra = rs.getDouble("soTienTra");
                String liDo = rs.getString("moTa");
                HoaDon hoaDon = new HoaDon(maHD);
                NhanVien nhVien = new NhanVien(maNV);
                ArrayList<ChiTietDoiTra> listDetail = getAllReturnOrderDetail(maHDDT);
                DoiTra returnOrder = new DoiTra(ngayDT, maHDDT, nhVien, hoaDon, loai, tienTra, listDetail, liDo);

                result.add(returnOrder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Boolean create(DoiTra doiTra) {
        int n = 0;
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("INSERT INTO HoaDonDoiTra(maHoaDonDoiTra, maHoaDon, ngayDoiTra, maNhanVien, loaiDoiTra, soTienTra, moTa) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)"); 
            st.setString(1, doiTra.getMaHDDT());
            st.setString(2, doiTra.getHoaDon().getMaHD());
           
            st.setDate(3, new java.sql.Date(doiTra.getNgayDoiTra().getTime()));
            st.setString(4, doiTra.getNhanvien().getMaNhanVien());
            st.setBoolean(5, doiTra.isLoai());
            st.setDouble(6, doiTra.getTienTra());
            st.setString(7, doiTra.getLiDO());
            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }
    public boolean isReturnOrderExist(String maHoaDon) {
        boolean exists = false;
        String sql = "SELECT COUNT(*) FROM HoaDonDoiTra WHERE maHoaDon = ?";

        try (PreparedStatement ps = ConnectDB.conn.prepareStatement(sql)) {
            ps.setString(1, maHoaDon);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                exists = count > 0; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }

//    @Override
    public Boolean update(String id, DoiTra doiTra) {
        int n = 0;
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("UPDATE HoaDonDoiTra "
                    + "SET trangThai = ?, ngayDoiTra = ? "
                    + "WHERE maHoaDonDoiTra = ?"); 
            int i = 1;
          
            st.setDate(i++, new java.sql.Date(doiTra.getNgayDoiTra().getTime()));
            st.setString(i++, id);
            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public static ArrayList<ChiTietDoiTra> getAllReturnOrderDetail(String maHDDT) {
        return new ChiTietDoiTra_DAO().getAllForOrderReturnID(maHDDT);
    }

    public ArrayList<DoiTra> findById(String returnOrderID) {
        ArrayList<DoiTra> result = new ArrayList<>();
        String query = """
                       SELECT * FROM HoaDonDoiTra
                       where maHoaDonDoiTra LIKE ?
                       """;
        try {

            PreparedStatement st = ConnectDB.conn.prepareStatement(query);
            st.setString(1, returnOrderID + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                if (rs != null) {
                    result.add(getReturnOrderData(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private DoiTra getReturnOrderData(ResultSet rs) throws SQLException {
        DoiTra result = null;
        try {
            //Lấy thông tin tổng quát của lớp ReturnOrder
            String maHDDT = rs.getString("maHoaDonDoiTra");
            boolean loai = rs.getBoolean("loaiDoiTra");
          
            Date ngayDT = rs.getDate("ngayDoiTra");
            String maHD = rs.getString("maHoaDon");
            String maNV = rs.getString("maNhanVien");
            double tienTra = rs.getDouble("soTienTra");
            String liDo = rs.getString("moTa");
            HoaDon hoaDon = new HoaDon(maHD);
            NhanVien nhanVien = new NhanVien(maNV);
            ArrayList<ChiTietDoiTra> listDetail = getAllReturnOrderDetail(maHDDT);
            result = new DoiTra(ngayDT, maHDDT, nhanVien, hoaDon, loai, tienTra, listDetail, liDo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<DoiTra> filter(int type) {
        ArrayList<DoiTra> result = new ArrayList<>();
        int index = 1; 
        String query = "SELECT * FROM HoaDonDoiTra WHERE maHoaDonDoiTra LIKE '%'";

        if (type == 1) {  // Loại đơn đổi
            query += " AND loaiDoiTra = 0"; 
        } else if (type == 2) {  // Loại đơn trả
            query += " AND loaiDoiTra = 1";  
        }

        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(getReturnOrderData(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public int getNumberOfReturnOrderInMonth(int month, int year){
        int result=0;


        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("select count(maHoaDonDoiTra) as sl from [HoaDonDoiTra] where YEAR(ngayDoiTra) = ? and Month(ngayDoiTra) = ? ");
            st.setInt(1, year);
            st.setInt(2, month);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int sl = rs.getInt("sl");
                result=sl;

                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    public double getTotalReturnOrderInMonth(int month, int year) {
        double result = 0;

        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("select sum(soTienTra) as total from [HoaDonDoiTra] join [HoaDon] on [HoaDon].maHD=HoaDonDoiTra.maHoaDOn where YEAR(ngayLap) = ? and Month(ngayLap) = ? ");
            st.setInt(1, year);
            st.setInt(2, month);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                double total = rs.getDouble("total");
                result = total;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static DoiTra getOneForOrderID(String maHD) {
        DoiTra doiTra = null;
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("SELECT * FROM HoaDonDoiTra WHERE maHoaDon = ?");
            st.setString(1, maHD);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {                
                String maHDDT = rs.getString("maHoaDonDoiTra");
                Date ngayDT = rs.getDate("ngayDoiTra");
                double tienTra = rs.getDouble("soTienTra");
                String liDo = rs.getString("moTa");
                boolean loai = rs.getBoolean("loaiDoiTra");
                String nhanVien = rs.getString("maNhanVien");
          
                HoaDon hoaDon = new HoaDon_DAO().getHoaDon(maHDDT);
                NhanVien nhVien = new NhanVien(maHDDT);
                ArrayList<ChiTietDoiTra> listDetail = getAllReturnOrderDetail(maHDDT);
                doiTra = new DoiTra(ngayDT, maHDDT, nhVien, hoaDon, loai, tienTra, listDetail, liDo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doiTra;
    }
    // Hàm tính tổng số lượng và tổng tiền cho đơn đổi và đơn trả
    public DoiTra calculateTotals() {
        int slDonDoi = 0;  // Tổng số lượng đơn đổi
        int slDonTra = 0;  // Tổng số lượng đơn trả
        double tienDonTra = 0;  // Tổng tiền đơn trả

        String query = """
        SELECT 
            SUM(CASE WHEN loaiDoiTra = 1 THEN 1 ELSE 0 END) AS tongDonTra,
            SUM(CASE WHEN loaiDoiTra = 0 THEN 1 ELSE 0 END) AS tongDonDoi,
            SUM(CASE WHEN loaiDoiTra = 1 THEN soTienTra ELSE 0 END) AS tongTienTra
        FROM [QLNT].[dbo].[HoaDonDoiTra];
    """;

        try (PreparedStatement pst = ConnectDB.conn.prepareStatement(query); ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                slDonTra = rs.getInt("tongDonTra");        // Tổng số lượng đơn trả
                slDonDoi = rs.getInt("tongDonDoi");        // Tổng số lượng đơn đổi
                tienDonTra = rs.getDouble("tongTienTra");  // Tổng tiền đơn trả
            }
        } catch (Exception e) {
            e.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, "Không thể tính tổng.");
        }

        // Trả về đối tượng DoiTra chứa tổng số lượng đơn đổi, đơn trả và tổng tiền đơn trả
        return new DoiTra(slDonDoi, slDonTra, tienDonTra);
    }



}
