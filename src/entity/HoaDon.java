package entity;

import java.time.LocalDate;
import java.util.ArrayList;

public class HoaDon {

    public HoaDon(String maHD, LocalDate ngayLap, double tongTien, Voucher voucher, KhachHang khachHang, NhanVien nhanVien, KetToan ketToan, ArrayList<ChiTietHoaDon> listCTHD, boolean atm, double tienDaDua, boolean trangThai) {
        this.maHD = maHD;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.voucher = voucher;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.ketToan = ketToan;
        this.listCTHD = listCTHD;
        this.atm = atm;
        this.tienDaDua = tienDaDua;
        this.trangThai = trangThai;
    }

    private String maHD;
    private LocalDate ngayLap;
    private double tongTien;
    private Voucher voucher;
    private KhachHang khachHang;
    private NhanVien nhanVien;
    private KetToan ketToan;
    private ArrayList<ChiTietHoaDon> listCTHD;
    private boolean atm;
    private double tienDaDua;
    private boolean trangThai;

    public KetToan getKetToan() {
        return ketToan;
    }

    public void setKetToan(KetToan ketToan) {
        this.ketToan = ketToan;
    }

    public HoaDon(String maHD, LocalDate ngayLap, double tongTien, Voucher voucher, KhachHang khachHang, NhanVien nhanVien, boolean atm, double tienDaDua, boolean trangThai) {
        this.maHD = maHD;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.voucher = voucher;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.listCTHD = listCTHD;
        this.atm = atm;
        this.tienDaDua = tienDaDua;
        this.trangThai = trangThai;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public ArrayList<ChiTietHoaDon> getListCTHD() {
        return listCTHD;
    }

    public void setListCTHD(ArrayList<ChiTietHoaDon> listCTHD) {
        this.listCTHD = listCTHD;
    }

    public double tinhThanhTien() {
        double result = 0;
        for (ChiTietHoaDon CTHD : listCTHD) {
            result += CTHD.thanhTien();
        }
        return result;
    }

    public double tinhTienThua() {
        throw new UnsupportedOperationException();
    }

    public double tinhThue() {
        throw new UnsupportedOperationException();
    }

    public double TinhTongTienTra() {
        this.tongTien = tinhThanhTien() - ((this.voucher != null ? 0 : this.voucher.getGiaGiam()) * tinhThanhTien());
        return this.tongTien;
    }

    public HoaDon(String maHD, LocalDate ngayLap, double tongTien, Voucher voucher, KhachHang khachHang, NhanVien nhanVien) {
        this.maHD = maHD;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.voucher = voucher;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
    }

    public HoaDon(String maHD, LocalDate ngayLap, double tongTien, Voucher voucher, KhachHang khachHang, NhanVien nhanVien, ArrayList<ChiTietHoaDon> listCTHD, boolean atm, double tienDaDua, boolean trangThai) {
        this.maHD = maHD;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.voucher = voucher;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.listCTHD = listCTHD;
    }

    public HoaDon(String maHD, LocalDate ngayLap, double tongTien, Voucher voucher, KhachHang khachHang, NhanVien nhanVien, ArrayList<ChiTietHoaDon> listCTHD) {
        this.maHD = maHD;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.voucher = voucher;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.listCTHD = listCTHD;
    }

    public HoaDon(String maHD, LocalDate ngayLap, double tongTien, Voucher voucher, KhachHang khachHang, NhanVien nhanVien, ArrayList<ChiTietHoaDon> listCTHD, boolean atm, double tienDaDua) {
        this.maHD = maHD;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.voucher = voucher;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.listCTHD = listCTHD;
        this.atm = atm;
        this.tienDaDua = tienDaDua;
    }

    public HoaDon(String maHD) {
        this.maHD = maHD;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public LocalDate getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDate ngayLap) {
        this.ngayLap = ngayLap;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public boolean isAtm() {
        return atm;
    }

    public void setAtm(boolean atm) {
        this.atm = atm;
    }

    public double getTienDaDua() {
        return tienDaDua;
    }

    public void setTienDaDua(double tienDaDua) {
        this.tienDaDua = tienDaDua;
    }

}
