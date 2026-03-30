import java.util.Scanner;

public class CVLabkomdas {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        final int HARGA_A = 100000;
        final int HARGA_B = 125000;
        final int HARGA_C = 175000;

        // Harga diskon jika beli > 100
        final int DISKON_A = 95000;
        final int DISKON_B = 120000;
        final int DISKON_C = 160000;

        System.out.println("========================================");
        System.out.println("          CV. LABKOMDAS - Jaket         ");
        System.out.println("========================================");
        System.out.println("Harga Satuan (Normal) :");
        System.out.printf("Jaket A : Rp %,12d%n", HARGA_A);
        System.out.printf("Jaket B : Rp %,12d%n", HARGA_B);
        System.out.printf("Jaket C : Rp %,12d%n", HARGA_C);
        System.out.println("\nCatatan: Diskon berlaku jika beli > 100 buah");
        System.out.printf("Jaket A : Rp %,12d /biji%n", DISKON_A);
        System.out.printf("Jaket B : Rp %,12d /biji%n", DISKON_B);
        System.out.printf("Jaket C : Rp %,12d /biji%n", DISKON_C);
        System.out.println("========================================\n");

        System.out.print("Masukkan jumlah Jaket A yang dibeli : ");
        int jumlahA = input.nextInt();

        System.out.print("Masukkan jumlah Jaket B yang dibeli : ");
        int jumlahB = input.nextInt();

        System.out.print("Masukkan jumlah Jaket C yang dibeli : ");
        int jumlahC = input.nextInt();

        System.out.println("\n========================================");
        System.out.println("              STRUK PEMBELIAN            ");
        System.out.println("========================================");

        int hargaA = (jumlahA > 100) ? DISKON_A : HARGA_A;
        int hargaB = (jumlahB > 100) ? DISKON_B : HARGA_B;
        int hargaC = (jumlahC > 100) ? DISKON_C : HARGA_C;

        printDetailJaket("A", jumlahA, hargaA, jumlahA > 100);
        printDetailJaket("B", jumlahB, hargaB, jumlahB > 100);
        printDetailJaket("C", jumlahC, hargaC, jumlahC > 100);

        int grandTotal = (jumlahA * hargaA) + (jumlahB * hargaB) + (jumlahC * hargaC);
        System.out.println("========================================");
        System.out.printf("GRAND TOTAL : Rp %,22d%n", grandTotal);
        System.out.println("========================================");

        input.close();
    }

    private static void printDetailJaket(String tipe, int jumlah, int hargaSatuan, boolean diskon) {
        System.out.printf("Jaket %-1s : %3d buah%n", tipe, jumlah);
        if (diskon) {
            System.out.printf("  [DISKON]   Harga Rp %,12d/biji%n", hargaSatuan);
        } else {
            System.out.printf("  Harga      Rp %,12d/biji%n", hargaSatuan);
        }
        System.out.printf("  Total    : Rp %,23d%n%n", jumlah * hargaSatuan);
    }
}
