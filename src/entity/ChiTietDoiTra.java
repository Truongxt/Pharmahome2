/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Objects;

/**
 *
 * @author HÀ NHƯ
 */
public class ChiTietDoiTra {
     public static final String ORDERID_EMPTY = "Hoá đơn không được phép rỗng";
    public static final String PRODUCT_EMPTY = "Sản phẩm không được phép rỗng";
    public static final String QUANTITY_VALID = "Số lượng phải là số dương";

    
    private DoiTra doiTra;
    private Thuoc sanPham;
    private int soLuong;
    private double gia;

    public ChiTietDoiTra(DoiTra doiTra, Thuoc sanPham, int soLuong, double gia) {
        this.doiTra = doiTra;
        this.sanPham = sanPham;
        this.soLuong = soLuong;
        this.gia = gia;
    }
    
    

    public ChiTietDoiTra(DoiTra doiTra, Thuoc sanPham) {
        this.doiTra = doiTra;
        this.sanPham = sanPham;
    }

    public ChiTietDoiTra(Thuoc sanPham, int soLuong, double gia) {
        this.sanPham = sanPham;
        this.soLuong = soLuong;
        this.gia = gia;
    }

    
    
    public ChiTietDoiTra() {
    }

    

    public DoiTra getDoiTra() {
        return doiTra;
    }

    public void setDoiTra(DoiTra doiTra) throws Exception {
        this.doiTra = doiTra;
        if(doiTra == null)
            throw new Exception(ORDERID_EMPTY);
        this.doiTra = doiTra;
    }
    
    public Thuoc getSanPham() {
        return sanPham;
    }

    public void setSanPham(Thuoc sanPham) throws Exception {
        if(sanPham == null)
            throw new Exception(PRODUCT_EMPTY);
        this.sanPham = sanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) throws Exception {
        
        if(soLuong < 0)
            throw new Exception(QUANTITY_VALID);
        this.soLuong = soLuong;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }
    public double thanhTien(){
        return this.gia * this.soLuong;
    }

    
    
    
    public DoiTra getReturnOrder() {
        return doiTra;
    }

    public void setReturnOrder(DoiTra returnOrder) throws Exception {
        if(returnOrder == null)
            throw new Exception(ORDERID_EMPTY);
        this.doiTra = returnOrder;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.doiTra);
        hash = 41 * hash + Objects.hashCode(this.sanPham);
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
        final ChiTietDoiTra other = (ChiTietDoiTra) obj;
        if (!Objects.equals(this.doiTra, other.doiTra)) {
            return false;
        }
        return Objects.equals(this.sanPham, other.sanPham);
    }

    @Override
    public String toString() {
        return "ChiTietDoiTra{" + "doiTra=" + doiTra + ", sanPham=" + sanPham + ", soLuong=" + soLuong + ", gia=" + gia + '}';
    }

}
