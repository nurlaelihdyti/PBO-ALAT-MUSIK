// ABSTRACT CLASS: induk dari semua alat musik
public abstract class AlatMusik {
    protected String nama;
    protected String asal;

    // Konstruktor abstract class
    public AlatMusik(String nama, String asal) {
        this.nama = nama;
        this.asal = asal;
    }

    // ABSTRACT METHOD
    public abstract void bunyikan();
    public abstract String getCaraMain();

    // METHOD KONKRET: punya isi, dipakai bersama semua subclass
    public void perkenalan() {
        System.out.println("Nama      : " + nama);
        System.out.println("Asal      : " + asal);
        System.out.println("Cara main : " + getCaraMain());
        System.out.print("Bunyi     : ");
        bunyikan();
        System.out.println("-------------------------------");
    }
}