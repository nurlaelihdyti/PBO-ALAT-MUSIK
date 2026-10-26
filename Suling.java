// INHERITANCE: Suling mewarisi AlatMusik
public class Suling extends AlatMusik {
    public Suling() {
        super("Suling", "Indonesia");
    }

    @Override
    public void bunyikan() {
        System.out.println("Fuuu... fiuu... fuuu...");
    }

    @Override
    public String getCaraMain() {
        return "Ditiup sambil menutup lubang dengan jari";
    }
}
