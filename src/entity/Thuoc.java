package entity;

import java.time.LocalDate;
import java.util.Objects;

public class Thuoc {

    public Thuoc(String maThuoc, String tenThuoc, double gia) {
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.gia = gia;
    }

    public String getMaThuoc() {
        return maThuoc;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    /**
     * @param tenThuoc the tenThuoc to set
     */
    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    /**
     * @return the gia
     */
    public double getGia() {
        return gia;
    }

    /**
     * @param gia the gia to set
     */
    public void setGia(double gia) {
        this.gia = gia;
    }

    /**
     * @return the hsd
     */
    public LocalDate getHsd() {
        return hsd;
    }

    /**
     * @param hsd the hsd to set
     */
    public void setHsd(LocalDate hsd) {
        this.hsd = hsd;
    }

    /**
     * @return the nsx
     */
    public LocalDate getNsx() {
        return nsx;
    }

    /**
     * @param nsx the nsx to set
     */
    public void setNsx(LocalDate nsx) {
        this.nsx = nsx;
    }

    /**
     * @return the thue
     */
    public double getThue() {
        return thue;
    }

    /**
     * @param thue the thue to set
     */
    public void setThue(double thue) {
        this.thue = thue;
    }

    /**
     * @return the soLuongTon
     */
    public int getSoLuongTon() {
        return soLuongTon;
    }

    /**
     * @param soLuongTon the soLuongTon to set
     */
    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    /**
     * @return the mota
     */
    public String getMota() {
        return mota;
    }

    /**
     * @param mota the mota to set
     */
    public void setMota(String mota) {
        this.mota = mota;
    }

    /**
     * @return the loaiThuoc
     */
    public LoaiThuoc getLoaiThuoc() {
        return loaiThuoc;
    }

    /**
     * @param loaiThuoc the loaiThuoc to set
     */
    public void setLoaiThuoc(LoaiThuoc loaiThuoc) {
        this.loaiThuoc = loaiThuoc;
    }

    /**
     * @return the xuatXu
     */
    public XuatXu getXuatXu() {
        return xuatXu;
    }

    /**
     * @param xuatXu the xuatXu to set
     */
    public void setXuatXu(XuatXu xuatXu) {
        this.xuatXu = xuatXu;
    }

    /**
     * @return the donViTinh
     */
    public DonViTinh getDonViTinh() {
        return donViTinh;
    }

    /**
     * @param donViTinh the donViTinh to set
     */
    public void setDonViTinh(DonViTinh donViTinh) {
        this.donViTinh = donViTinh;
    }

    /**
     * @return the ncc
     */
    public NhaCungCap getNcc() {
        return ncc;
    }

    /**
     * @param ncc the ncc to set
     */
    public void setNcc(NhaCungCap ncc) {
        this.ncc = ncc;
    }

    private String maThuoc;
    private String tenThuoc;
    private double gia;
    private LocalDate hsd;
    private LocalDate nsx;
    private double thue;
    private int soLuongTon;
    private String mota;
    private LoaiThuoc loaiThuoc;
    private XuatXu xuatXu;
    private DonViTinh donViTinh;
    private NhaCungCap ncc;
//        private GiamGia giamGia;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.maThuoc);
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
        final Thuoc other = (Thuoc) obj;
        return Objects.equals(this.maThuoc, other.maThuoc);
    }

    public Thuoc(String maThuoc, String tenThuoc, double gia, LocalDate hsd, LocalDate nsx, double thue, int soLuongTon, String mota, LoaiThuoc loaiThuoc, XuatXu xuatXu, DonViTinh donViTinh, NhaCungCap ncc) {
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.gia = gia;
        this.hsd = hsd;
        this.nsx = nsx;
        this.thue = thue;
        this.soLuongTon = soLuongTon;
        this.mota = mota;
        this.loaiThuoc = loaiThuoc;
        this.xuatXu = xuatXu;
        this.donViTinh = donViTinh;
        this.ncc = ncc;
//        this.giamGia = giamGia;
    }

    public Thuoc(String maThuoc) {
        this.maThuoc = maThuoc;
    }

}
