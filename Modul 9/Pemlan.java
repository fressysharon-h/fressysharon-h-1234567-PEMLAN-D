public class Pemlan extends MataKuliah {
    public Pemlan() { super("Pemlan"); }

    @Override
    public double hitungNilaiAkhir() {
        return (nilaiTugas * 0.20) + (nilaiKuis * 0.20) + (nilaiUTS * 0.25) + (nilaiUAS * 0.35);
    }

    @Override
    public String getRumus() {
        return "Tugas*20% + Kuis*20% + UTS*25% + UAS*35%";
    }
}