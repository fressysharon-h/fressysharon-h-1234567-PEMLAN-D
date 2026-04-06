import java.time.LocalDate;
import java.time.Period;

public class Pekerja extends Manusia {
    private double gaji;
    private LocalDate tahunMasuk;
    private int jumlahAnak;

    public Pekerja(double gaji, int tahun, int bulan, int hari,
                   int jumlahAnak, String nama, String nik, boolean jk, boolean menikah) {
        super(nama, nik, jk, menikah);
        this.gaji = gaji;
        this.tahunMasuk = LocalDate.of(tahun, bulan, hari);
        this.jumlahAnak = jumlahAnak;
    }

    public double getBonus() {
        int lama = Period.between(tahunMasuk, LocalDate.now()).getYears();

        if (lama <= 5) return gaji * 0.05;
        else if (lama <= 10) return gaji * 0.10;
        else return gaji * 0.15;
    }

    public double getGaji() {
        return gaji + getBonus();
    }

    @Override
    public double getPendapatan() {
        return super.getPendapatan() + getGaji() + (jumlahAnak * 20);
    }

    @Override
    public String toString() {
        return super.toString() +
               "\ntahun masuk : " + tahunMasuk.getDayOfMonth() + " " +
               tahunMasuk.getMonthValue() + " " + tahunMasuk.getYear() +
               "\njumlah anak : " + jumlahAnak +
               "\ngaji : " + gaji;
    }
}
