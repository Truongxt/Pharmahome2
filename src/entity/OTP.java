package entity;

public class OTP {
    private TaiKhoan tenTaiKhoan;
    private String maXacNhan;
    private String email;

    public OTP(TaiKhoan tenTaiKhoan, String maXacNhan, String email) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.maXacNhan = maXacNhan;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TaiKhoan getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(TaiKhoan tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMaXacNhan() {
        return maXacNhan;
    }

    public void setMaXacNhan(String maXacNhan) {
        this.maXacNhan = maXacNhan;
    }
}
