
public enum LoaiPhuongTien {
    XE_MAY("Xe máy"),
    O_TO("Ô tô");

    private final String tenLoai;

    LoaiPhuongTien(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }
}