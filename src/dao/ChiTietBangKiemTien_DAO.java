/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connect.ConnectDB;
import entity.BangKiemTien;
import entity.ChiTietBangKiemTien;
import entity.NhanVien;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author lemin
 */
public class ChiTietBangKiemTien_DAO {

    private NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();

    public ChiTietBangKiemTien_DAO() {
    }

    public ChiTietBangKiemTien getOne(String maBangKiemTien) {
        ChiTietBangKiemTien chiTietBangKiemTien = null;

        try {
            String sql = "SELECT * FROM ChiTietBangKiemTien WHERE maBangKiemTien = ?";
            PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(sql);
            preparedStatement.setString(1, maBangKiemTien);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                boolean chiSo = resultSet.getBoolean("chiSo");
                String maNhanVien = resultSet.getString("maNhanVien");

                chiTietBangKiemTien = new ChiTietBangKiemTien(chiSo, new NhanVien(maNhanVien), new BangKiemTien(maBangKiemTien));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return chiTietBangKiemTien;
    }

    public ArrayList<ChiTietBangKiemTien> getAllCashCountSheetDetailInCashCountSheet(String maBangKiemTien) {
        ArrayList<ChiTietBangKiemTien> chiTietBangKiemTiens = new ArrayList<>();

        try {
            String sql = "SELECT * FROM ChiTietBangKiemTien WHERE maBangKiemTien = ?";
            PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(sql);
            preparedStatement.setString(1, maBangKiemTien);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                boolean chiSo = resultSet.getBoolean("chiSoKiem");
                String maNhanVien = resultSet.getString("maNhanVien");

                NhanVien nhanVien = nhanVien_DAO.getNhanVien(maNhanVien);
                BangKiemTien bangKiemTien = new BangKiemTien(maBangKiemTien);

                ChiTietBangKiemTien chiTietBangKiemTien = new ChiTietBangKiemTien(chiSo, nhanVien, bangKiemTien);
                chiTietBangKiemTiens.add(chiTietBangKiemTien);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return chiTietBangKiemTiens;
    }

    public Boolean create(ChiTietBangKiemTien chiTietBangKiemTien) {
        try {
            String sql = "INSERT INTO ChiTietBangKiemTien (chiSoKiem, maBangKiemTien, maNhanVien) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(sql);

            preparedStatement.setBoolean(1, chiTietBangKiemTien.getChiSo());
            preparedStatement.setString(2, chiTietBangKiemTien.getBangKiemTien().getMaBangKiemTien());
            preparedStatement.setString(3, chiTietBangKiemTien.getNhanVien().getMaNhanVien());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return true; // Thành công
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Thất bại
    }

}
