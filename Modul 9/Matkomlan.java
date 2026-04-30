public class Matkomlan extends MataKuliah {
    public Matkomlan() { super("Matkomlan"); }

    @Override
    public double hitungNilaiAkhir() {
        return (nilaiTugas * 0.10) + (nilaiKuis * 0.20) + (nilaiUTS * 0.35) + (nilaiUAS * 0.35);
    }

    @Override
    public String getRumus() {
        return "Tugas*10% + Kuis*20% + UTS*35% + UAS*35%";
    }
}