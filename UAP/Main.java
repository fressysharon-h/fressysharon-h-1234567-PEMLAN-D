import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static GoDriveRentalSystem system = new GoDriveRentalSystem();

    public static void main(String[] args) {
        // Data awal kendaraan
        system.kendaraanTersimpan(new Mobil("MBL01", "Toyota Avanza", 350000, 7));
        system.kendaraanTersimpan(new Mobil("MBL02", "Daihatsu Sigra", 300000, 7));
        system.kendaraanTersimpan(new Mobil("MBL03", "Honda Brio", 280000, 5));
        system.kendaraanTersimpan(new Motor("MTR01", "Honda Vario", 80000, "Matik"));
        system.kendaraanTersimpan(new Motor("MTR02", "Yamaha NMAX", 100000, "Matik"));
        system.kendaraanTersimpan(new Motor("MTR03", "Kawasaki KLX", 90000, "Manual"));

        boolean running = true;
        while (running) {
            tampilMenu();
            System.out.print("Pilih menu: ");
            int pilihan;
            try {
                pilihan = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Input tidak valid. Masukkan angka menu.");
                continue;
            }

            switch (pilihan) {
                case 1:
                    menuTambahKendaraan();
                    break;
                case 2:
                    system.tampilkanDaftarKendaraan();
                    break;
                case 3:
                    menuSewaKendaraan();
                    break;
                case 4:
                    menuKembalikanKendaraan();
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan Go Drive Rental System. Sampai jumpa!");
                    running = false;
                    break;
                default:
                    System.out.println("[ERROR] Pilihan tidak valid. Silakan pilih menu 1-5.");
            }
        }
        sc.close();
    }

    static void tampilMenu() {
        System.out.println("\n====== MENU GO DRIVE RENTAL SYSTEM ======");
        System.out.println("1. Tambah Kendaraan");
        System.out.println("2. Tampilkan Daftar Armada");
        System.out.println("3. Sewa Kendaraan");
        System.out.println("4. Kembalikan Kendaraan");
        System.out.println("5. Keluar");
    }

    static void menuTambahKendaraan() {
        System.out.print("Masukkan jenis kendaraan (mobil/motor): ");
        String jenis = sc.nextLine().trim().toLowerCase();

        System.out.print("Masukkan kode kendaraan: ");
        String kode = sc.nextLine().trim().toUpperCase();

        System.out.print("Masukkan nama kendaraan: ");
        String nama = sc.nextLine().trim();

        double harga;
        while (true) {
            System.out.print("Masukkan harga sewa per hari: ");
            try {
                harga = Double.parseDouble(sc.nextLine().trim());
                if (harga <= 0) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Harga tidak valid. Masukkan angka positif.");
            }
        }

        if (jenis.equals("mobil")) {
            int kursi;
            while (true) {
                System.out.print("Masukkan kapasitas kursi: ");
                try {
                    kursi = Integer.parseInt(sc.nextLine().trim());
                    if (kursi <= 0) throw new NumberFormatException();
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("[ERROR] Jumlah kursi tidak valid. Masukkan angka positif.");
                }
            }
            system.tambahKendaraan(new Mobil(kode, nama, harga, kursi));

        } else if (jenis.equals("motor")) {
            System.out.print("Masukkan jenis transmisi (Matik/Manual): ");
            String transmisi = sc.nextLine().trim();
            system.tambahKendaraan(new Motor(kode, nama, harga, transmisi));

        } else {
            System.out.println("[ERROR] Jenis kendaraan tidak valid. Harus 'mobil' atau 'motor'.");
        }
    }

    static void menuSewaKendaraan() {
        System.out.print("Masukkan kode kendaraan yang ingin disewa: ");
        String kode = sc.nextLine().trim().toUpperCase();

        int durasi;
        while (true) {
            System.out.print("Masukkan durasi sewa (dalam hari): ");
            try {
                durasi = Integer.parseInt(sc.nextLine().trim());
                if (durasi <= 0) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Durasi tidak valid. Masukkan angka positif.");
            }
        }

        System.out.print("Apakah Anda Member VIP? (y/n): ");
        String vipInput = sc.nextLine().trim().toLowerCase();
        boolean isVIP = vipInput.equals("y");

        try {
            system.sewaKendaraan(kode, durasi, isVIP);
        } catch (KendaraanTidakTersediaException e) {
            throw new RuntimeException(e);
        }
    }

    static void menuKembalikanKendaraan() {
        System.out.print("Masukkan kode kendaraan yang ingin dikembalikan: ");
        String kode = sc.nextLine().trim().toUpperCase();
        system.kembalikanKendaraan(kode);
    }
}
