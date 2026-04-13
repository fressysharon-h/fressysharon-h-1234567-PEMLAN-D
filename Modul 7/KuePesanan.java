public class KuePesanan extends Kue {
    public double berat;

    public KuePesanan(String nama, double harga, double berat) {
        super(nama, harga);
        this.berat = berat;
    }

    @Override
    public double hitungHarga() {
        return harga * berat;
    }

    @Override
    public String toString() {
        return String.format(
            "[KuePesanan ] %-22s | Harga/kg: Rp%,8.0f | Berat: %4.1f kg | Total: Rp%,12.0f",
            nama, harga, berat, hitungHarga());
    }
}
