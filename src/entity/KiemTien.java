/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author lemin
 */
public class KiemTien {

    private int soLuong;
    private double giaTri;
    private double tong;

    public KiemTien() {
    }

    public KiemTien(int soLuong, double giaTri) {
        setSoLuong(soLuong);
        setGiaTri(giaTri);
        setTong();
    }

    public int getSoLuong() {
        return soLuong;
    }

    public double getGiaTri() {
        return giaTri;
    }

    public double getTong() {
        return tong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setGiaTri(double giaTri) {
        this.giaTri = giaTri;
    }

    public void setTong() {
        this.tong = this.giaTri * this.soLuong;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.giaTri) ^ (Double.doubleToLongBits(this.giaTri) >>> 32));
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
        final KiemTien other = (KiemTien) obj;
        return Double.doubleToLongBits(this.giaTri) == Double.doubleToLongBits(other.giaTri);
    }
}
