/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connect.ConnectDB;
import entity.KetToan;
import entity.BangKiemTien;
import entity.HoaDon;
import entity.KiemTien;
import gui.KetToan_GUI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Main;
import raven.toast.Notifications;
import utilities.AcountingVoucherPrinter;
import utilities.ConvertDate;
//import utilities.ConvertDate;

/**
 *
 * @author lemin
 */
public class KetToan_DAO {

    private BangKiemTien_DAO bangKiemTien_DAO = new BangKiemTien_DAO();
    private HoaDon_DAO hoaDon_DAO = new HoaDon_DAO();

    public KetToan_DAO() {
    }

    public KetToan getOne(String maKetToan) {
        KetToan ketToan = null;
        try {
            String sql = "SELECT * FROM KetToan WHERE maKetToan = ?";
            PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(sql);
            preparedStatement.setString(1, maKetToan);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                Timestamp diemBatDau = resultSet.getTimestamp("ngayBatDau");
                Timestamp diemKetThuc = resultSet.getTimestamp("ngayKetThuc");

                Date ngayBatDau = new java.sql.Date(diemBatDau.getTime());
                Date ngayKetThuc = new java.sql.Date(diemKetThuc.getTime());
                String maBangKiemTien = resultSet.getString("maBangKiemTien");

                ketToan = new KetToan(maKetToan, ngayBatDau, ngayKetThuc, bangKiemTien_DAO.getOne(maBangKiemTien), hoaDon_DAO.getAllOrderInAcountingVoucher(maKetToan));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketToan;
    }

    public ArrayList<KetToan> getAll() {
        ArrayList<KetToan> ketToans = new ArrayList<>();

        try {
            String sql = "SELECT * FROM KetToan";
            PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String maKetToan = resultSet.getString("maKetToan");
                Timestamp diemBatDau = resultSet.getTimestamp("ngayBatDau");
                Timestamp diemKetThuc = resultSet.getTimestamp("ngayKetThuc");

                Date ngayBatDau = new java.sql.Date(diemBatDau.getTime());
                Date ngayKetThuc = new java.sql.Date(diemKetThuc.getTime());

                String maBangKiemTien = resultSet.getString("maBangKiemTien");
                BangKiemTien bangKiemTien = bangKiemTien_DAO.getOne(maBangKiemTien);

                ArrayList<HoaDon> listHoaDon = new HoaDon_DAO().getTatCaHoaDonTrongKetToan(maKetToan);
                KetToan ketToan = new KetToan(maKetToan, ngayBatDau, ngayKetThuc, bangKiemTien, new HoaDon_DAO().getTatCaHoaDonTrongKetToan(maKetToan));
                ketToans.add(ketToan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ketToans;
    }

    public Boolean create(KetToan ketToan) {
        try {
            String sql = "INSERT INTO KetToan (maKetToan, ngayBatDau, ngayKetThuc, maBangKiemTien) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(sql);

            preparedStatement.setString(1, ketToan.getMaKetToan());

            Timestamp end = new Timestamp(ketToan.getNgayKetThuc().getTime());
            preparedStatement.setTimestamp(3, end);

            Timestamp start = new Timestamp(ketToan.getNgayBatDau().getTime());
            preparedStatement.setTimestamp(2, start);

            preparedStatement.setString(4, ketToan.getBangKiemTien().getMaBangKiemTien());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return true; // Thành công
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Thất bại
    }

    public String getMaMoiNhat(String ma) {
        try {
            ma += "%";
            String sql = "SELECT TOP 1  * FROM KetToan WHERE maKetToan LIKE '" + ma + "' ORDER BY maKetToan DESC";
            PreparedStatement st = ConnectDB.conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String maKetToan = rs.getString("maKetToan");
                return maKetToan;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String TaoMa(Date date) {
        //Khởi tạo mã phiếu kết toán
        String prefix = "KTOAN";
        //8 Kí tự tiếp theo là ngày và giờ bắt đầu kết toán
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

    public ArrayList<HoaDon> getAllHoaDon(Date start, Date end) {
        ArrayList<HoaDon> allHoaDon = hoaDon_DAO.getAllHoaDonTrongKetToan( new java.sql.Date(start.getTime()) , new java.sql.Date(end.getTime()));
        ArrayList<HoaDon> listHoaDon = new ArrayList<>();
 
        return allHoaDon;
    }

    public double getDoanhThu(ArrayList<HoaDon> list) {
        double sum = 0;
        for (HoaDon hoaDon : list) {
            sum += hoaDon.getTongTien();
        }
        return sum;
    }

    public double getATM(ArrayList<HoaDon> list) {
        double sum = 0;
        for (HoaDon hoaDon : list) {
            if (hoaDon.isAtm()) {
                sum += hoaDon.getTongTien();
            }
        }
        return sum;
    }

    public double getTong(ArrayList<KiemTien> list) {
        double sum = 0;
        for (KiemTien kiemTien : list) {
            sum += kiemTien.getTong();
        }
        return sum;
    }

    public KetToan getKetToanCuoi() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String format = dateFormat.format(date);
        String code = "KTOAN" + format;
        KetToan ketToanCuoi = getOne(getMaMoiNhat(code));
        if (ketToanCuoi == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 6);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            date = calendar.getTime();
            ketToanCuoi = new KetToan(date);
        }
        return ketToanCuoi;
    }

    public void taoPhieuKetToan(BangKiemTien bangKiemTien, Date ngayKetThuc) {
        Date ngayBatDau = getKetToanCuoi().getNgayKetThuc();
        ArrayList<HoaDon> listHoaDon = getAllHoaDon(ngayBatDau, ngayKetThuc);
        String ma = TaoMa(ngayKetThuc);

        KetToan ketToan = new KetToan(ma, ngayBatDau, ngayKetThuc, bangKiemTien, listHoaDon);
        bangKiemTien_DAO.create(bangKiemTien);
        create(ketToan);

        for (HoaDon hoaDon : listHoaDon) {
            if (hoaDon_DAO.updateOrderAcountingVoucher(hoaDon.getMaHD(), ketToan.getMaKetToan()));
        }
        Notifications.getInstance().show(Notifications.Type.SUCCESS, "Tạo phiếu kết toán thành công");
        try {
            //        Application.showForm(new KetToan_GUI());
            new Main().getMain().showForm(new KetToan_GUI(new Main().getTk()));
        } catch (SQLException ex) {
            Logger.getLogger(KetToan_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        generatePDF(this.getOne(ma));

    }

    public void generatePDF(KetToan KetToan) {
//        tạo file pdf và hiển thị + in file pdf đó
        AcountingVoucherPrinter printer = new AcountingVoucherPrinter(KetToan);
        printer.generatePDF();
        AcountingVoucherPrinter.PrintStatus status = printer.printFile();
        if (status == AcountingVoucherPrinter.PrintStatus.NOT_FOUND_FILE) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi không thể in hóa đơn: Không tìm thấy file");
        } else if (status == AcountingVoucherPrinter.PrintStatus.NOT_FOUND_PRINTER) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi không thể in hóa đơn: Không tìm thấy máy in");
        }
    }
}
