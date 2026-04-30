public class Probstat extends MataKuliah {
    public Probstat() { super("Probstat"); }

    @Override
    public double hitungNilaiAkhir() {
        return (nilaiTugas * 0.25) + (nilaiKuis * 0.15) + (nilaiUTS * 0.25) + (nilaiUAS * 0.35);
    }

    @Override
    public String getRumus() {
        return "Tugas*25% + Kuis*15% + UTS*25% + UAS*35%";
    }
}