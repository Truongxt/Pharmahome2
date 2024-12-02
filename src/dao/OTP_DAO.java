package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.*;
import java.util.concurrent.*;

import connect.ConnectDB;
import entity.OTP;
import entity.TaiKhoan;

public class OTP_DAO {

    /**
     * Lấy mã OTP từ cơ sở dữ liệu dựa trên email.
     * 
     * @param email Địa chỉ email
     * @return Mã OTP, hoặc null nếu không tìm thấy
     */
    public static String getOtpFromDatabase(String email) {
        String sql = "SELECT maXacNhan FROM OTP WHERE tentaiKhoan = ?";
        try (
             PreparedStatement ps = ConnectDB.conn.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("maXacNhan");
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy OTP từ cơ sở dữ liệu: " + e.getMessage());
        }
        return null; // Trả về null nếu không tìm thấy mã OTP
    }

    /**
     * Lưu mã OTP vào cơ sở dữ liệu. Nếu tài khoản đã tồn tại, cập nhật mã OTP.
     * 
     * @param email Email của tài khoản
     * @param otp   Mã OTP cần lưu
     * @return True nếu lưu thành công, False nếu có lỗi
     */
  public boolean saveOtpToDatabase(String tenTaiKhoan, String otp) {
    // Sửa lệnh SQL để chỉ định rõ tên bảng và cột
    String sql = "INSERT INTO OTP (tentaiKhoan, maXacNhan, created_at) VALUES (?, ?, ?);";

    try (
        PreparedStatement ps = ConnectDB.conn.prepareStatement(sql)) {
        
        // Lấy timestamp hiện tại
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        // Gán tham số vào câu lệnh SQL
        ps.setString(1, tenTaiKhoan); // Cột tentaiKhoan
        ps.setString(2, otp);         // Cột maXacNhan
        ps.setTimestamp(3, currentTimestamp); // Cột created_at

        // Thực thi lệnh
        ps.executeUpdate();
        return true;
    } catch (SQLException e) {
        // Log lỗi nếu có vấn đề xảy ra
        System.err.println("Lỗi khi lưu OTP vào cơ sở dữ liệu: " + e.getMessage());
        return false;
    }
}


    /**
     * Xóa mã OTP khỏi cơ sở dữ liệu theo email.
     * 
     * @param email Email của tài khoản
     * @return True nếu xóa thành công, False nếu có lỗi
     */
    public  boolean deleteOtpFromDatabase(String email) {
        String sql = "DELETE FROM OTP WHERE tentaiKhoan = ?";
        try (
             PreparedStatement ps = ConnectDB.conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa OTP khỏi cơ sở dữ liệu: " + e.getMessage());
            return false;
        }
    }

    /**
     * Lấy danh sách mã xác nhận từ cơ sở dữ liệu dựa trên tài khoản.
     * 
     * @param ten Tên tài khoản
     * @return Danh sách mã xác nhận
     */
    public String getMaXacNhan(String ten) {
       
        String sql = "SELECT * FROM OTP WHERE tentaiKhoan = ?";
        try (
             PreparedStatement ps = ConnectDB.conn.prepareStatement(sql)) {

            ps.setString(1, ten);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    
                    String maXacNhan = rs.getString("maXacNhan");

                    return maXacNhan;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
     public void deleteOTP60s(){
          ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                deleteExpiredOTPs();
            }
        }, 0, 1, TimeUnit.MINUTES); 
     }
     public static void deleteExpiredOTPs() {
         
        String sql = "DELETE FROM OTP WHERE DATEDIFF(SECOND, created_at, GETDATE()) > 60";
        try (
             PreparedStatement ps = ConnectDB.conn.prepareStatement(sql)) {

            int rowsDeleted = ps.executeUpdate();
            System.out.println("Deleted " + rowsDeleted + " expired OTP(s).");

        } catch (SQLException e) {
            System.err.println("Error deleting expired OTPs: " + e.getMessage());
        }
    }
}
