import java.util.ArrayList;

public class Main {

    // POLYMORPHISM (lewat parameter): menerima alat musik jenis apa saja
    static void mainkan(AlatMusik alat) {
        System.out.print(">> " + alat.nama + " dimainkan: ");
        alat.bunyikan();
    }

    public static void main(String[] args) {
        // POLYMORPHISM: tipe referensi induk, objek aslinya anak
        ArrayList<AlatMusik> daftar = new ArrayList<>();
        daftar.add(new Gitar(6));
        daftar.add(new Piano(88));
        daftar.add(new Drum());
        daftar.add(new Suling());

        System.out.println("=== PERKENALAN ALAT MUSIK ===");
        for (AlatMusik a : daftar) {
            a.perkenalan(); // satu perintah, hasil beda tiap objek
        }

        System.out.println("===== KONSER MINI =====");
        for (AlatMusik a : daftar) {
            mainkan(a);
        }
    }
}