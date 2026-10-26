// INHERITANCE: Drum mewarisi AlatMusik
public class Drum extends AlatMusik {
    public Drum() {
        super("Drum", "Amerika Serikat");
    }

    @Override
    public void bunyikan() {
        System.out.println("Dum... tak... dum... tak...");
    }

    @Override
    public String getCaraMain() {
        return "Dipukul dengan stik";
    }
}
