public class Hewan implements MakhlukHidup {

    @Override
    public void makan() {
        System.out.println("Makan pakai mulut");
    }

    @Override
    public void berjalan() {
        System.out.println("Jalan pakai 4 kaki");
    }

    @Override
    public void bersuara() {
        System.out.println("Suaranya tidak jelas");
    }
}
