public class PhuongTien {
    private String bienSo;
    private LoaiPhuongTien loaiXe;
    private String tenXe;
    private String moTa;
    private String maNguoiDung;

    public PhuongTien(String bienSo, LoaiPhuongTien loaiXe, String maNguoiDung) {
        this.bienSo = bienSo;
        this.loaiXe = loaiXe;
        this.maNguoiDung = maNguoiDung;
        this.tenXe = "";
        this.moTa = "";
    }

    public String getBienSo() {
        return bienSo;
    }

    public LoaiPhuongTien getLoaiXe() {
        return loaiXe;
    }

    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public String getTenXe() {
        return tenXe;
    }

    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return "Bien so: " + bienSo + ", Loai: " + loaiXe + ", Ma ND: " + maNguoiDung;
    }
}
