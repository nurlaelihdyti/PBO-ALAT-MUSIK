// INHERITANCE: Gitar mewarisi AlatMusik
public class Gitar extends AlatMusik {
    private int jumlahSenar;

    public Gitar(int jumlahSenar) {
        super("Gitar", "Spanyol"); 
        this.jumlahSenar = jumlahSenar;
    }

    @Override
    public void bunyikan() {
        System.out.println("Jreng... jreng... (" + jumlahSenar + " senar)");
    }

    @Override
    public String getCaraMain() {
        return "Senar dipetik atau dipetik dengan pick";
    }
}