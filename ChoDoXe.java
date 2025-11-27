public class ChoDoXe {
    private String maChoDo;
    private String khuVuc;
    private LoaiPhuongTien loaiXePhuHop;
    private TrangThaiChoDo trangThai;
    private String maNguoiDungDatCho;

    public ChoDoXe(String maChoDo, String khuVuc, LoaiPhuongTien loaiXePhuHop) {
        this.maChoDo = maChoDo;
        this.khuVuc = khuVuc;
        this.loaiXePhuHop = loaiXePhuHop;
        this.trangThai = TrangThaiChoDo.CON_TRONG;
        this.maNguoiDungDatCho = null;
    }

    public String getMaChoDo() {
        return maChoDo;
    }

    public String getKhuVuc() {
        return khuVuc;
    }

    public TrangThaiChoDo getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(TrangThaiChoDo trangThai) {
        this.trangThai = trangThai;
        if (trangThai != TrangThaiChoDo.DA_DAT_TRUOC) {
            this.maNguoiDungDatCho = null;
        }
    }

    public boolean isConTrong() {
        return this.trangThai == TrangThaiChoDo.CON_TRONG;
    }

    public LoaiPhuongTien getLoaiXePhuHop() {
        return loaiXePhuHop;
    }

    public String getMaNguoiDungDatCho() {
        return maNguoiDungDatCho;
    }

    public void setMaNguoiDungDatCho(String maNguoiDungDatCho) {
        this.maNguoiDungDatCho = maNguoiDungDatCho;
    }

    @Override
    public String toString() {
        String datBoi = (maNguoiDungDatCho != null) ? (" (Da dat boi: " + maNguoiDungDatCho + ")") : "";
        return "Ma cho: " + maChoDo + ", Khu: " + khuVuc + ", Loai: " + loaiXePhuHop.getTenLoai() + datBoi + ", Trang thai: " + trangThai;
    }
}
