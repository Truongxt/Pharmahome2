/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connect.ConnectDB;
import entity.BangKiemTien;
import entity.KiemTien;
import entity.ChiTietBangKiemTien;
import entity.KetToan;
import entity.NhanVien;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author lemin
 */
public class BangKiemTien_DAO {

    private KiemTien_DAO kiemTien_DAO = new KiemTien_DAO();
    private ChiTietBangKiemTien_DAO chiTietBangKiemTien_DAO = new ChiTietBangKiemTien_DAO();

    public BangKiemTien getOne(String id) {
        BangKiemTien bangKiemTien = null;

        try {
            String sql = "SELECT * FROM BangKiemTien WHERE maBangKiemTien = ?";
            PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(sql);
            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Timestamp diemBatDau = resultSet.getTimestamp("ngayBatDau");
                Timestamp diemKetThuc = resultSet.getTimestamp("ngayKetThuc");

                Date ngayBatDau = new Date(diemBatDau.getTime());
                Date ngayKetThuc = new Date(diemKetThuc.getTime());

                bangKiemTien = new BangKiemTien(id, kiemTien_DAO.getAll(id), chiTietBangKiemTien_DAO.getAllCashCountSheetDetailInCashCountSheet(id), ngayBatDau, ngayKetThuc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bangKiemTien;
    }

    public ArrayList<BangKiemTien> getAll() {
        ArrayList<BangKiemTien> bangKiemTiens = new ArrayList<>();

        try {
            String sql = "SELECT * FROM BangKiemTien";
            PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String maBangKiemTien = resultSet.getString("maBangKiemTien");
                Timestamp diemBatDau = resultSet.getTimestamp("ngayBatDau");
                Timestamp diemKetThuc = resultSet.getTimestamp("ngayKetThuc");

                Date ngayBatDau = new Date(diemBatDau.getTime());
                Date ngayKetThuc = new Date(diemKetThuc.getTime());
                BangKiemTien bangKiemTien = new BangKiemTien(maBangKiemTien, new KiemTien_DAO().getAll(maBangKiemTien), new ChiTietBangKiemTien_DAO().getAllCashCountSheetDetailInCashCountSheet(maBangKiemTien), ngayBatDau, ngayKetThuc);

                bangKiemTiens.add(bangKiemTien);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bangKiemTiens;
    }

    public Boolean create(BangKiemTien bangKiemTien
    ) {
        try {
            // Thêm bản ghi vào bảng BangKiemTien
            String bangKiemTienSql = "INSERT INTO BangKiemTien (maBangKiemTien, ngayBatDau, ngayKetThuc) VALUES (?, ?, ?)";
            PreparedStatement bangKiemTienStatement = ConnectDB.conn.prepareStatement(bangKiemTienSql);
            bangKiemTienStatement.setString(1, bangKiemTien.getMaBangKiemTien());

            Timestamp end = new Timestamp(bangKiemTien.getNgayKetThuc().getTime());
            bangKiemTienStatement.setTimestamp(2, end);

            Timestamp start = new Timestamp(bangKiemTien.getNgayBatDau().getTime());
            bangKiemTienStatement.setTimestamp(3, start);
            int bangKiemTienRowsAffected = bangKiemTienStatement.executeUpdate();
            for (KiemTien kiemTien : bangKiemTien.getListKiemTien()) {
                kiemTien_DAO.create(kiemTien, bangKiemTien.getMaBangKiemTien());
            }
            // Thêm bản ghi vào bảng ChiTietBangKiemTien
            for (ChiTietBangKiemTien chiTietBangKiemTien : bangKiemTien.getListChiTietBangKiemTien()) {
                chiTietBangKiemTien_DAO.create(chiTietBangKiemTien);
            }
            // Nếu tất cả các bảng đều thêm bản ghi thành công
            if (bangKiemTienRowsAffected > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getMaMoiNhat(String ma) {
        try {
            ma += "%";
            String sql = "SELECT TOP 1  * FROM BangKiemTien WHERE maBangKiemTien LIKE '" + ma + "' ORDER BY maBangKiemTien DESC";
            PreparedStatement st = ConnectDB.conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String maBangKiemTien = rs.getString("maBangKiemTien");
                return maBangKiemTien;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String taoMa(Date date) {
        //Khởi tạo mã phiếu kiểm tiền
        String prefix = "KTIEN";
        //8 Kí tự tiếp theo là ngày và giờ bắt đầu kiểm tiền
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String format = dateFormat.format(date);
        prefix += format;
        String maxID = getMaMoiNhat(prefix);
        //4 Kí tự tiếp theo là số thứ tự tăng dần
        if (maxID == null) {
            prefix += "0000";
        } else {
            String bonKiTuCuoi = maxID.substring(maxID.length() - 4);
            int num = Integer.parseInt(bonKiTuCuoi);
            num++;
            prefix += String.format("%04d", num);
        }
        return prefix;
    }

    public void createBangKiemTien(ArrayList<KiemTien> listKiemTien, ArrayList<NhanVien> listNhanVien, Date ngayBatDau) {
        Date ngayKetThuc = new Date();
        BangKiemTien bangKiemTien = new BangKiemTien(taoMa(ngayBatDau));
        //Set danh sách CashCount
        bangKiemTien.setListKiemTien(listKiemTien);
        //Khởi tạo CashCountSheetDetal
        ArrayList<ChiTietBangKiemTien> listChiTietKiemTien = new ArrayList<>();
        listChiTietKiemTien.add(new ChiTietBangKiemTien(true, listNhanVien.get(0), bangKiemTien));
        listChiTietKiemTien.add(new ChiTietBangKiemTien(false, listNhanVien.get(1), bangKiemTien));

        bangKiemTien.setListChiTietBangKiemTien(listChiTietKiemTien);
        bangKiemTien.setNgayBatDau(ngayBatDau);
        bangKiemTien.setNgayKetThuc(ngayKetThuc);

        create(bangKiemTien);
//        GeneratePDF(bangKiemTien);

    }

    public ArrayList<BangKiemTien> filter() {
        ArrayList<BangKiemTien> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM BangKiemTien";
            PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String maBangKiemTien = resultSet.getString("maBangKiemTien");
                Timestamp diemBatDau = resultSet.getTimestamp("ngayBatDau");
                Timestamp diemKetThuc = resultSet.getTimestamp("ngayKetThuc");

                Date ngayBatDau = new Date(diemBatDau.getTime());
                Date ngayKetThuc = new Date(diemKetThuc.getTime());
                BangKiemTien bangKiemTien = new BangKiemTien(maBangKiemTien, new KiemTien_DAO().getAll(maBangKiemTien), new ChiTietBangKiemTien_DAO().getAllCashCountSheetDetailInCashCountSheet(maBangKiemTien), ngayBatDau, ngayKetThuc);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
