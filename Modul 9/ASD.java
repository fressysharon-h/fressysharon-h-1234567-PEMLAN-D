// Tugas*15% + Kuis*25% + UTS*30% + UAS*30%

public class ASD extends MataKuliah {
    public ASD() { super("ASD"); }

    @Override
    public double hitungNilaiAkhir() {
        return (nilaiTugas * 0.15) + (nilaiKuis * 0.25) + (nilaiUTS * 0.30) + (nilaiUAS * 0.30);
    }

    @Override
    public String getRumus() {
        return "Tugas*15% + Kuis*25% + UTS*30% + UAS*30%";
    }
}
