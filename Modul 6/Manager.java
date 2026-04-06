public class Manager extends Pekerja {
    private String departemen;

    public Manager(String departemen, double gaji, int tahun, int bulan, int hari,
                   int jumlahAnak, String nama, String nik, boolean jk, boolean menikah) {
        super(gaji, tahun, bulan, hari, jumlahAnak, nama, nik, jk, menikah);
        this.departemen = departemen;
    }

    @Override
    public double getPendapatan() {
        return super.getPendapatan() + (0.1 * super.getGaji());
    }

    @Override
    public String toString() {
        return super.toString() +
               "\ndepartemen : " + departemen;
    }
}
