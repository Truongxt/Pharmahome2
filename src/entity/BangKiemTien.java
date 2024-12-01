/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Logger;

/**
 *
 * @author lemin
 */
public class BangKiemTien implements Comparable<BangKiemTien> {

    private String maBangKiemTien;
    private ArrayList<KiemTien> listKiemTien;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private ArrayList<ChiTietBangKiemTien> listChiTietBangKiemTien;
    private double chenhLech;
    private double tongTien;

    public BangKiemTien() {
    }

    public BangKiemTien(String maBangKiemTien) {
        this.maBangKiemTien = maBangKiemTien;
    }

    public BangKiemTien(String maBangKiemTien, ArrayList<KiemTien> listKiemTien, ArrayList<ChiTietBangKiemTien> listChiTietBangKiemTien, Date ngayBatDau, Date ngayKetThuc) {
        this.maBangKiemTien = maBangKiemTien;
        this.listKiemTien = listKiemTien;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.listChiTietBangKiemTien = listChiTietBangKiemTien;
        setTongTien();
    }

    public String getMaBangKiemTien() {
        return maBangKiemTien;
    }

    public ArrayList<KiemTien> getListKiemTien() {
        return listKiemTien;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public ArrayList<ChiTietBangKiemTien> getListChiTietBangKiemTien() {
        return listChiTietBangKiemTien;
    }

    public void setMaBangKiemTien(String maBangKiemTien) {
        this.maBangKiemTien = maBangKiemTien;
    }

    public void setListKiemTien(ArrayList<KiemTien> listKiemTien) {
        this.listKiemTien = listKiemTien;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public void setListChiTietBangKiemTien(ArrayList<ChiTietBangKiemTien> listChiTietBangKiemTien) {
        this.listChiTietBangKiemTien = listChiTietBangKiemTien;
    }

    public double getChenhLech() {
        return chenhLech;
    }

    public void setChenhLech(double chenhLech) {
        this.chenhLech = chenhLech;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien() {
        double sum = 0;
        for (KiemTien ct : listKiemTien) {
            sum += ct.getTong();
        }
        this.tongTien = sum;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.maBangKiemTien);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BangKiemTien other = (BangKiemTien) obj;
        return Objects.equals(this.maBangKiemTien, other.maBangKiemTien);
    }

    @Override
    public int compareTo(BangKiemTien o) {
        return this.ngayBatDau.compareTo(o.ngayBatDau);
    }
}
