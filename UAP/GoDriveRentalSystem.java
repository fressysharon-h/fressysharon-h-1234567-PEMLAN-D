import java.util.ArrayList;

public class GoDriveRentalSystem {
    private ArrayList<Kendaraan> daftarKendaraan;

    public GoDriveRentalSystem() {
        daftarKendaraan = new ArrayList<>();
    }
    public void kendaraanTersimpan(Kendaraan k) {
        daftarKendaraan.add(k);
        }

    public void tambahKendaraan(Kendaraan k) {
        daftarKendaraan.add(k);
        System.out.println("[INFO] Kendaraan berhasil ditambahkan: " + k.getNamaKendaraan() + " (" + k.getKodeKendaraan() + ")");
    }

    public void tampilkanDaftarKendaraan() {
        if (daftarKendaraan.isEmpty()) {
            System.out.println("[INFO] Belum ada kendaraan terdaftar.");
            return;
        }
        System.out.println("\n=== DAFTAR ARMADA GODRIVE ===");
        int no = 1;
        for (Kendaraan k : daftarKendaraan) {
            System.out.print(no++ + ". ");
            k.tampilInfo();
        }
    }

    public void sewaKendaraan(String kode, int lamaSewa) throws KendaraanTidakTersediaException {
        sewaKendaraan(kode, lamaSewa, false);
    }

    public void sewaKendaraan(String kode, int lamaSewa, boolean isVIP) throws KendaraanTidakTersediaException {
        Kendaraan target = null;
        for (Kendaraan k : daftarKendaraan) {
            if (k.getKodeKendaraan().equalsIgnoreCase(kode)) {
                target = k;
                break;
            }
        }

        if (target == null || !target.isTersedia()) {
            throw new KendaraanTidakTersediaException(
                "Kendaraan dengan kode " + kode + " gagal disewa. " +
                "Alasan: Kendaraan sedang disewa atau tidak ditemukan!"
            );
        }

        double biayaDasar = target.hitungBiayaDasar(lamaSewa);
        double totalBiaya = biayaDasar;

        // Diskon penyewaan lebih dari 7 hari (10%)
        double diskonLama = 0;
        if (lamaSewa > 7) {
            diskonLama = totalBiaya * 0.10;
            totalBiaya -= diskonLama;
        }

        // Diskon member VIP (10%)
        double diskonVIP = 0;
        if (isVIP) {
            diskonVIP = totalBiaya * 0.10;
            totalBiaya -= diskonVIP;
        }

        target.setTersedia(false);

        System.out.println("\n=== TRANSAKSI SEWA GODRIVE ===");
        System.out.println("Kendaraan Berhasil Disewa!");
        System.out.printf("Unit           : %s (%s)%n", target.getNamaKendaraan(), target.getKodeKendaraan());
        System.out.printf("Lama Sewa      : %d hari%n", lamaSewa);

        // Detail biaya
        if (target instanceof Mobil) {
            Mobil m = (Mobil) target;
            double biayaPokok = lamaSewa * target.getHargaSewaPerHari();
            System.out.printf("Biaya Dasar Harian : Rp %,.0f%n", biayaPokok);
            if (m.getJumlahKursi() > 5) {
                System.out.printf("Tambahan Kursi (>5): Rp 50,000%n");
            }
        } else if (target instanceof Motor) {
            Motor mt = (Motor) target;
            double biayaPokok = lamaSewa * target.getHargaSewaPerHari();
            System.out.printf("Biaya Dasar Harian : Rp %,.0f%n", biayaPokok);
            if (mt.getJenisTransmisi().equalsIgnoreCase("Matik")) {
                System.out.printf("Asuransi Matik     : Rp %,.0f (Rp10.000 x %d hari)%n",
                        10000.0 * lamaSewa, lamaSewa);
            }
        }

        if (lamaSewa > 7) {
            System.out.printf("Diskon Sewa >7 hari (10%%): -Rp %,.0f%n", diskonLama);
        }
        if (isVIP) {
            System.out.printf("Diskon Member VIP (10%%): -Rp %,.0f%n", diskonVIP);
        }

        System.out.println("----------------------------------------");
        System.out.printf("TOTAL BIAYA AKHIR: Rp %,.0f%n", totalBiaya);
    }

    public void kembalikanKendaraan(String kode) {
        for (Kendaraan k : daftarKendaraan) {
            if (k.getKodeKendaraan().equalsIgnoreCase(kode)) {
                if (k.isTersedia()) {
                    System.out.println("[INFO] Kendaraan " + k.getNamaKendaraan() +
                            " (" + kode + ") tidak sedang disewa.");
                } else {
                    k.setTersedia(true);
                    System.out.println("[INFO] Kendaraan " + k.getNamaKendaraan() +
                            " (" + kode + ") berhasil dikembalikan. Status: Tersedia.");
                }
                return;
            }
        }
        System.out.println("[ERROR] Kendaraan dengan kode " + kode + " tidak ditemukan.");
    }
}