import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

// Tema : Studio Alat Musik (GUI Swing + Abstract Class, Inheritance, Polymorphism)
// Nama : ................   NIM : ................
// Catatan : AlatMusik, Gitar, Piano, Drum, Suling, dan Main dipakai APA ADANYA (tidak diubah)
public class AlatMusikGUI {

    // warna panggung
    private Color plum = new Color(60, 28, 70);
    private Color krem = new Color(252, 246, 238);
    private Color amber = new Color(214, 120, 40);

    // data : satu list bertipe INDUK, isinya objek anak (POLYMORPHISM)
    private ArrayList<AlatMusik> daftar;

    // komponen GUI
    private JFrame frame;
    private JComboBox<String> cmbAlat;
    private JTextArea areaInfo;
    private JTextArea areaLog;
    private JButton btnBunyi;
    private JButton btnKonser;
    private JButton btnBersih;
    private JButton btnKeluar;

    public AlatMusikGUI() {
        daftar = new ArrayList<>();
        daftar.add(new Gitar(6));
        daftar.add(new Piano(88));
        daftar.add(new Drum());
        daftar.add(new Suling());

        frame = new JFrame("Studio Alat Musik");
        cmbAlat = new JComboBox<>();
        for (AlatMusik a : daftar)
            cmbAlat.addItem(a.nama);
        areaInfo = new JTextArea(5, 30);
        areaLog = new JTextArea(6, 30);
        btnBunyi = new JButton("Bunyikan");
        btnKonser = new JButton("Konser Mini");
        btnBersih = new JButton("Bersihkan");
        btnKeluar = new JButton("Keluar");
    }

    public void launchFrame() {
        // NORTH : judul
        JLabel judul = new JLabel("Studio Alat Musik", JLabel.CENTER);
        judul.setFont(new Font("SansSerif", Font.BOLD, 24));
        judul.setForeground(Color.WHITE);
        judul.setOpaque(true);
        judul.setBackground(plum);
        judul.setBorder(BorderFactory.createEmptyBorder(16, 0, 16, 0));

        // baris pilihan alat musik (FlowLayout)
        JPanel pilih = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        pilih.setBackground(krem);
        pilih.add(new JLabel("Pilih alat musik :"));
        pilih.add(cmbAlat);

        // dua kotak : profil alat dan panggung (GridLayout 2 baris)
        areaInfo.setEditable(false);
        areaLog.setEditable(false);
        JPanel tengah = new JPanel(new GridLayout(2, 1, 0, 10));
        tengah.setBackground(krem);
        tengah.add(buatKotak("Profil Alat", areaInfo));
        tengah.add(buatKotak("Panggung", areaLog));

        // CENTER : pilihan di atas, dua kotak di bawahnya
        JPanel isi = new JPanel(new BorderLayout(0, 10));
        isi.setBackground(krem);
        isi.setBorder(BorderFactory.createEmptyBorder(12, 20, 5, 20));
        isi.add(pilih, BorderLayout.NORTH);
        isi.add(tengah, BorderLayout.CENTER);

        // SOUTH : tombol (FlowLayout)
        JPanel tombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 12));
        tombol.setBackground(krem);
        JButton[] semua = {btnBunyi, btnKonser, btnBersih, btnKeluar};
        for (JButton b : semua) {
            gayaTombol(b);
            tombol.add(b);
        }

        // susun ke frame (BorderLayout)
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().setBackground(krem);
        frame.getContentPane().add(judul, BorderLayout.NORTH);
        frame.getContentPane().add(isi, BorderLayout.CENTER);
        frame.getContentPane().add(tombol, BorderLayout.SOUTH);

        // daftarkan listener
        cmbAlat.addActionListener(new PilihHandler());
        btnBunyi.addActionListener(new BunyiHandler());
        btnKonser.addActionListener(new KonserHandler());
        btnBersih.addActionListener(new BersihHandler());
        btnKeluar.addActionListener(new KeluarHandler());
        frame.addWindowListener(new CloseHandler());

        tampilkanProfil();   // isi awal kotak Profil Alat
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(460, 560);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // ===== METHOD BANTU =====
    private JPanel buatKotak(String teks, JTextArea area) {
        JLabel lbl = new JLabel(teks);
        lbl.setForeground(plum);
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));
        area.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        JScrollPane sp = new JScrollPane(area);
        sp.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, amber));   // garis amber di kiri
        JPanel p = new JPanel(new BorderLayout(0, 4));
        p.setBackground(krem);
        p.add(lbl, BorderLayout.NORTH);
        p.add(sp, BorderLayout.CENTER);
        return p;
    }

    private void gayaTombol(JButton b) {
        b.setBackground(amber);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(9, 16, 9, 16));
    }

    // Method lama (perkenalan, bunyikan, mainkan) mencetak ke console lewat System.out.
    // Method ini "menangkap" cetakan itu menjadi String supaya bisa ditampilkan di GUI.
    private String tangkap(Runnable aksi) {
        PrintStream asli = System.out;                     // simpan console asli
        ByteArrayOutputStream wadah = new ByteArrayOutputStream();
        System.setOut(new PrintStream(wadah));             // arahkan cetakan ke wadah
        aksi.run();                                        // jalankan method lama
        System.out.flush();
        System.setOut(asli);                               // kembalikan console
        return wadah.toString().replace("\r", "").trim();
    }

    private void tampilkanProfil() {
        AlatMusik a = daftar.get(cmbAlat.getSelectedIndex());   // tipe INDUK, objek aslinya anak
        areaInfo.setText(tangkap(() -> a.perkenalan()));
    }

    private void keluar() {
        int result = JOptionPane.showConfirmDialog(frame, "Yakin ingin keluar?", "Konfirmasi",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION)
            System.exit(0);
    }

    // ===== EVENT HANDLER (inner class) =====
    private class PilihHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            tampilkanProfil();
        }
    }

    private class BunyiHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            AlatMusik a = daftar.get(cmbAlat.getSelectedIndex());
            areaLog.append(tangkap(() -> Main.mainkan(a)) + "\n");
        }
    }

    private class KonserHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            areaLog.append("===== KONSER MINI =====\n");
            for (AlatMusik a : daftar) {
                areaLog.append(tangkap(() -> Main.mainkan(a)) + "\n");
            }
        }
    }

    private class BersihHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            areaLog.setText("");
        }
    }

    private class KeluarHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            keluar();
        }
    }

    private class CloseHandler extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            keluar();
        }
    }

    public static void main(String[] args) {
        AlatMusikGUI app = new AlatMusikGUI();
        app.launchFrame();
    }
}
