
public enum TrangThaiChoDo {
    CON_TRONG("Còn trống"),
    DA_CHIEM("Đã chiếm"),
    DA_DAT_TRUOC("Đã đặt trước"),
    DANG_BAO_TRI("Đang bảo trì");

    private final String moTa;

    TrangThaiChoDo(String moTa) {
        this.moTa = moTa;
    }

    public String getMoTa() {
        return moTa;
    }
}