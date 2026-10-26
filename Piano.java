public class Piano extends AlatMusik {
    private int jumlahTuts;

    public Piano(int jumlahTuts) {
        super("Piano", "Italia");
        this.jumlahTuts = jumlahTuts;
    }

    @Override
    public void bunyikan() {
        System.out.println("Tit... tut... (" + jumlahTuts + " tuts)");
    }

    @Override
    public String getCaraMain() {
        return "Tuts ditekan dengan jari";
    }
}