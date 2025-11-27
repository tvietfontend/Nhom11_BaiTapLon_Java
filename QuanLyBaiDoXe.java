import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class QuanLyBaiDoXe {
    private List<ChoDoXe> danhSachChoDoXe = new ArrayList<>();
    private List<PhuongTien> danhSachPhuongTien = new ArrayList<>();

    public QuanLyBaiDoXe() {
        for (int i = 1; i <= 3; i++) {
            danhSachChoDoXe.add(new ChoDoXe("O" + i, "Khu A", LoaiPhuongTien.O_TO));
        }
        for (int i = 1; i <= 5; i++) {
            danhSachChoDoXe.add(new ChoDoXe("M" + i, "Khu B", LoaiPhuongTien.XE_MAY));
        }
        danhSachPhuongTien.add(new PhuongTien("51A-123.45", LoaiPhuongTien.O_TO, "USER001"));
        danhSachPhuongTien.add(new PhuongTien("29B-678.90", LoaiPhuongTien.XE_MAY, "USER002"));
    }

    public ChoDoXe timChoTrongPhuHop(LoaiPhuongTien loaiXe) {
        for (ChoDoXe choDo : danhSachChoDoXe) {
            if (choDo.isConTrong() && choDo.getLoaiXePhuHop() == loaiXe) {
                return choDo;
            }
        }
        return null;
    }

    public boolean datChoDo(String maNguoiDung, LoaiPhuongTien loaiXe) {
        ChoDoXe choTrong = timChoTrongPhuHop(loaiXe);
        if (choTrong != null) {
            choTrong.setMaNguoiDungDatCho(maNguoiDung);
            choTrong.setTrangThai(TrangThaiChoDo.DA_DAT_TRUOC);
            return true;
        }
        System.out.println("Khong tim thay cho trong phu hop cho loai xe: " + loaiXe.getTenLoai());
        return false;
    }

    public boolean giaiPhongCho(String maChoDo) {
        for (ChoDoXe choDo : danhSachChoDoXe) {
            if (choDo.getMaChoDo().equalsIgnoreCase(maChoDo)) {
                if (choDo.getTrangThai() == TrangThaiChoDo.CON_TRONG) {
                    System.out.println("Cho nay da trong!");
                    return false;
                }
                choDo.setTrangThai(TrangThaiChoDo.CON_TRONG);
                System.out.println("Giai phong / Huy dat cho thanh cong! Ma cho: " + maChoDo);
                return true;
            }
        }
        System.out.println("Khong tim thay cho do voi ma " + maChoDo);
        return false;
    }

    public void themPhuongTien(PhuongTien pt) {
        boolean isExists = danhSachPhuongTien.stream()
                .anyMatch(p -> p.getBienSo().equalsIgnoreCase(pt.getBienSo()));
        if (isExists) {
            System.out.println("Phuong tien voi bien so " + pt.getBienSo() + " da ton tai.");
            return;
        }
        danhSachPhuongTien.add(pt);
        System.out.println("Them phuong tien thanh cong: " + pt.getBienSo() + " (" + pt.getLoaiXe().getTenLoai() + ")");
    }

    public List<PhuongTien> timKiemPhuongTien(String tuKhoaBienSo, LoaiPhuongTien loaiXe) {
        return danhSachPhuongTien.stream()
                .filter(pt -> (tuKhoaBienSo == null || pt.getBienSo().toLowerCase().contains(tuKhoaBienSo.toLowerCase())))
                .filter(pt -> (loaiXe == null || pt.getLoaiXe() == loaiXe))
                .collect(Collectors.toList());
    }

    public boolean xoaPhuongTien(String bienSo) {
        boolean removed = danhSachPhuongTien.removeIf(pt -> pt.getBienSo().equalsIgnoreCase(bienSo));
        if (removed) {
            System.out.println("Xoa phuong tien co bien so " + bienSo + " thanh cong.");
        } else {
            System.out.println("Khong tim thay phuong tien co bien so " + bienSo + " de xoa.");
        }
        return removed;
    }

    public void thongKeTrangThaiChoDo() {
        long trong = danhSachChoDoXe.stream().filter(c -> c.getTrangThai() == TrangThaiChoDo.CON_TRONG).count();
        long datTruoc = danhSachChoDoXe.stream().filter(c -> c.getTrangThai() == TrangThaiChoDo.DA_DAT_TRUOC).count();
        long daChiem = danhSachChoDoXe.stream().filter(c -> c.getTrangThai() == TrangThaiChoDo.DA_CHIEM).count();
        long baoTri = danhSachChoDoXe.stream().filter(c -> c.getTrangThai() == TrangThaiChoDo.DANG_BAO_TRI).count();

        System.out.println();
        System.out.println("THONG KE CHO DO");
        System.out.println("Tong so cho: " + danhSachChoDoXe.size());
        System.out.println("  Con trong  : " + trong);
        System.out.println("  Da dat truoc: " + datTruoc);
        System.out.println("  Da chiem    : " + daChiem);
        System.out.println("  Dang bao tri: " + baoTri);
    }

    public void inDanhSachChoDoTheoTrangThai(TrangThaiChoDo trangThai) {
        System.out.println();
        System.out.println("Danh sach cho do - " + trangThai.getMoTa());
        List<ChoDoXe> ketQua = danhSachChoDoXe.stream()
                .filter(c -> c.getTrangThai() == trangThai)
                .collect(Collectors.toList());

        if (ketQua.isEmpty()) {
            System.out.println("Khong co cho do nao trong trang thai nay.");
            return;
        }

        for (ChoDoXe c : ketQua) {
            String datBoi = (c.getMaNguoiDungDatCho() != null)
                    ? " (Dat boi: " + c.getMaNguoiDungDatCho() + ")" : "";
            System.out.println("  Ma: " + c.getMaChoDo() + " | Khu: " + c.getKhuVuc()
                    + " | Loai: " + c.getLoaiXePhuHop().getTenLoai() + datBoi);
        }
    }

    public void thongKePhuongTienTheoLoai() {
        System.out.println();
        System.out.println("THONG KE PHUONG TIEN THEO LOAI");
        for (LoaiPhuongTien loai : LoaiPhuongTien.values()) {
            long count = danhSachPhuongTien.stream().filter(pt -> pt.getLoaiXe() == loai).count();
            System.out.println("  " + loai.getTenLoai() + ": " + count);
        }
    }

    public void inThongTinPhuongTien(PhuongTien pt) {
        System.out.println("  Bien so: " + pt.getBienSo() +
                " | Loai: " + pt.getLoaiXe().getTenLoai() +
                " | Ma nguoi dung: " + pt.getMaNguoiDung());
    }

    private static void inDongTrang() {
    }

    private static void hienThiHeader() {
        System.out.println();
        System.out.println("HE THONG QUAN LY BAI DO XE MINI");
    }

    private static void hienThiMenu() {
        inDongTrang();
        System.out.printf("%-3s %-35s %s%n", "STT", "Chuc nang", "Mo ta");
        inDongTrang();
        System.out.printf("%-3s %-35s %s%n", "1", "Dat cho do xe", "Dat truoc cho phu hop");
        System.out.printf("%-3s %-35s %s%n", "2", "Huy dat / Giai phong", "Theo ma cho");
        System.out.printf("%-3s %-35s %s%n", "3", "Them phuong tien", "Them xe moi");
        System.out.printf("%-3s %-35s %s%n", "4", "Tim kiem phuong tien", "Theo bien so / loai");
        System.out.printf("%-3s %-35s %s%n", "5", "Xoa phuong tien", "Theo bien so");
        System.out.printf("%-3s %-35s %s%n", "6", "Thong ke", "Bao cao tong hop");
        System.out.printf("%-3s %-35s %s%n", "0", "Thoat", "Ket thuc chuong trinh");
        inDongTrang();
        System.out.print("Chon chuc nang (0-6): ");
    }

    public static void main(String[] args) {
        QuanLyBaiDoXe quanLy = new QuanLyBaiDoXe();
        Scanner scanner = new Scanner(System.in);
        int luaChon = -1;

        hienThiHeader();
        quanLy.datChoDo("USER999", LoaiPhuongTien.O_TO);

        do {
            System.out.println();
            hienThiMenu();
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                System.out.println("Vui long nhap lua chon.");
                continue;
            }
            try {
                luaChon = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Lua chon khong hop le.");
                continue;
            }

            switch (luaChon) {
                case 1 -> {
                    System.out.print("Nhap ma nguoi dung: ");
                    String maND = scanner.nextLine().trim();
                    System.out.print("Nhap loai xe (O_TO/XE_MAY): ");
                    try {
                        LoaiPhuongTien loai = LoaiPhuongTien.valueOf(scanner.nextLine().trim().toUpperCase());
                        quanLy.datChoDo(maND, loai);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Loai xe khong hop le.");
                    }
                    System.out.print("Nhan ENTER de tiep tuc...");
                    scanner.nextLine();
                }
                case 2 -> {
                    System.out.print("Nhap ma cho can giai phong: ");
                    quanLy.giaiPhongCho(scanner.nextLine().trim().toUpperCase());
                    System.out.print("Nhan ENTER de tiep tuc...");
                    scanner.nextLine();
                }
                case 3 -> {
                    System.out.print("Nhap bien so xe: ");
                    String bs = scanner.nextLine().trim();
                    System.out.print("Nhap ma nguoi dung so huu: ");
                    String maSoHuu = scanner.nextLine().trim();
                    System.out.print("Nhap loai xe (O_TO/XE_MAY): ");
                    try {
                        LoaiPhuongTien loai = LoaiPhuongTien.valueOf(scanner.nextLine().trim().toUpperCase());
                        quanLy.themPhuongTien(new PhuongTien(bs, loai, maSoHuu));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Loai xe khong hop le.");
                    }
                    System.out.print("Nhan ENTER de tiep tuc...");
                    scanner.nextLine();
                }
                case 4 -> {
                    System.out.print("Nhap bien so (de trong neu khong dung): ");
                    String tkBS = scanner.nextLine().trim();
                    System.out.print("Nhap loai xe (O_TO/XE_MAY - de trong neu bo qua): ");
                    String tkLoai = scanner.nextLine().trim().toUpperCase();
                    LoaiPhuongTien loaiTK = null;
                    try {
                        if (!tkLoai.isEmpty()) loaiTK = LoaiPhuongTien.valueOf(tkLoai);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Loai xe tim kiem khong hop le. Bo qua loai.");
                    }
                    List<PhuongTien> ketQua =
                            quanLy.timKiemPhuongTien(tkBS.isEmpty() ? null : tkBS, loaiTK);
                    System.out.println();
                    System.out.println("Ket qua tim kiem (" + ketQua.size() + "):");
                    ketQua.forEach(quanLy::inThongTinPhuongTien);
                    System.out.print("Nhan ENTER de tiep tuc...");
                    scanner.nextLine();
                }
                case 5 -> {
                    System.out.print("Nhap bien so xe can xoa: ");
                    quanLy.xoaPhuongTien(scanner.nextLine().trim());
                    System.out.print("Nhan ENTER de tiep tuc...");
                    scanner.nextLine();
                }
                case 6 -> {
                    quanLy.thongKeTrangThaiChoDo();
                    quanLy.thongKePhuongTienTheoLoai();
                    quanLy.inDanhSachChoDoTheoTrangThai(TrangThaiChoDo.CON_TRONG);
                    quanLy.inDanhSachChoDoTheoTrangThai(TrangThaiChoDo.DA_DAT_TRUOC);
                    System.out.print("Nhan ENTER de tiep tuc...");
                    scanner.nextLine();
                }
                case 0 -> System.out.println("Da thoat chuong trinh.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (luaChon != 0);

        scanner.close();
    }
}
