public abstract class MataKuliah {
    protected String nama;
    protected double nilaiTugas;
    protected double nilaiKuis;
    protected double nilaiUTS;
    protected double nilaiUAS;

    public MataKuliah(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public abstract double hitungNilaiAkhir();

    public abstract String getRumus();

    public void setNilai(double tugas, double kuis, double uts, double uas) {
        this.nilaiTugas = tugas;
        this.nilaiKuis  = kuis;
        this.nilaiUTS   = uts;
        this.nilaiUAS   = uas;
    }
}