<div align="center">

# 🎵 Sistem Alat Musik 🎵

### Studi Kasus PBO: Abstract Class, Inheritance & Polymorphism

![Java](https://img.shields.io/badge/Java-8+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![OOP](https://img.shields.io/badge/Paradigma-OOP-6C63FF?style=for-the-badge)
![Konsep](https://img.shields.io/badge/Konsep-Abstract_Class-e91e63?style=for-the-badge)
![Kelas](https://img.shields.io/badge/Kelas-3D-0d6efd?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Selesai-2ea44f?style=for-the-badge)

*Satu perintah, banyak suara.* 🎸 🎹 🥁 🪈

</div>

---

## 👥 Anggota Kelompok

| No | Nama | NIM |
|:--:|------|-----|
| 1 | Nurlaeli Hidayati | F1D02510129 |
| 2 | Qolbina Aprianti | F1D02510132 |

📚 **Mata Kuliah** : Pemrograman Berorientasi Objek (PBO)
🏫 **Kelas** : 3D
📅 **Tahun** : 2026

---

## 📖 Deskripsi Program

Program ini memperkenalkan dan memainkan empat alat musik: **Gitar, Piano, Drum, dan Suling**.

Semua alat musik punya kesamaan: punya **nama**, punya **asal**, dan bisa **dibunyikan**. Namun **cara bunyi** dan **cara memainkannya berbeda-beda**.

Karena itu `AlatMusik` dibuat sebagai **abstract class**. Class ini menyimpan hal yang sama, sedangkan hal yang berbeda diserahkan ke masing-masing subclass.

> 💡 **Mengapa abstract class?**
> "Alat musik" adalah konsep umum. Tidak ada alat musik yang bunyinya hanya "bunyi alat musik". Bunyinya baru jelas ketika sudah menjadi gitar, piano, drum, atau suling.

---

## 🗺️ Class Diagram

```mermaid
classDiagram
    class AlatMusik {
        <<abstract>>
        #String nama
        #String asal
        +AlatMusik(String, String)
        +bunyikan()* void
        +getCaraMain()* String
        +perkenalan() void
    }
    class Gitar {
        -int jumlahSenar
        +Gitar(int)
        +bunyikan() void
        +getCaraMain() String
    }
    class Piano {
        -int jumlahTuts
        +Piano(int)
        +bunyikan() void
        +getCaraMain() String
    }
    class Drum {
        +Drum()
        +bunyikan() void
        +getCaraMain() String
    }
    class Suling {
        +Suling()
        +bunyikan() void
        +getCaraMain() String
    }
    class Main {
        +mainkan(AlatMusik)$ void
        +main(String[])$ void
    }

    AlatMusik <|-- Gitar
    AlatMusik <|-- Piano
    AlatMusik <|-- Drum
    AlatMusik <|-- Suling
    Main ..> AlatMusik : menggunakan
```
<p align="center">
  <img src="class-diagram.png" alt="Class Diagram Sistem Alat Musik" width="100%">
</p>

**Cara membaca diagram:**

| Simbol | Arti |
|:--:|------|
| `<<abstract>>` | Class abstract, tidak bisa dibuat objeknya |
| `*` setelah nama method | Method abstract (tanpa isi) |
| `$` setelah nama method | Method `static` |
| `#` | Atribut `protected` (bisa diakses subclass) |
| `-` | Atribut `private` |
| `+` | Method/atribut `public` |
| Panah segitiga kosong | Inheritance (`extends`) |
| Panah putus-putus | Ketergantungan (class memakai class lain) |

---

## 🧠 Penjelasan Konsep

### 1️⃣ Abstract Class → `AlatMusik`

Class induk yang **tidak bisa dibuat objeknya** dengan `new`.

| Komponen | Keterangan |
|----------|------------|
| 🔒 **Abstract method** | `bunyikan()` dan `getCaraMain()`. Tanpa isi, **wajib** di-override oleh subclass |
| ✅ **Method konkret** | `perkenalan()`. Punya isi dan dipakai bersama oleh semua subclass |
| 🏗️ **Konstruktor** | Dipanggil subclass lewat `super(...)` untuk mengisi `nama` dan `asal` |

```java
public abstract class AlatMusik {
    protected String nama;
    protected String asal;

    public AlatMusik(String nama, String asal) {
        this.nama = nama;
        this.asal = asal;
    }

    public abstract void bunyikan();        // abstract: tanpa isi
    public abstract String getCaraMain();   // abstract: tanpa isi

    public void perkenalan() { ... }        // konkret: ada isi
}
```

### 2️⃣ Inheritance → `extends`

`Gitar`, `Piano`, `Drum`, dan `Suling` **mewarisi** `AlatMusik`. Mereka otomatis punya atribut `nama`, `asal`, dan method `perkenalan()` **tanpa menulis ulang** (*code reuse*).

```java
public class Gitar extends AlatMusik {
    private int jumlahSenar;

    public Gitar(int jumlahSenar) {
        super("Gitar", "Spanyol");   // memanggil konstruktor induk
        this.jumlahSenar = jumlahSenar;
    }
}
```

### 3️⃣ Polymorphism → satu perintah, banyak perilaku

Semua objek disimpan dalam `ArrayList<AlatMusik>`. Saat `perkenalan()` memanggil `bunyikan()`, Java menjalankan versi milik **objek aslinya** (*dynamic binding*).

```java
ArrayList<AlatMusik> daftar = new ArrayList<>();
daftar.add(new Gitar(6));
daftar.add(new Piano(88));
daftar.add(new Drum());
daftar.add(new Suling());

for (AlatMusik a : daftar) {
    a.perkenalan();   // hasil berbeda tiap objek
}
```

Polymorphism juga terlihat pada method `mainkan(AlatMusik alat)`. Satu method bisa menerima Gitar, Piano, Drum, atau Suling.

---

## 🔍 Penjelasan Tiap File

| File | Jenis | Peran |
|------|-------|-------|
| `AlatMusik.java` | 🔒 Abstract class | Induk. Menyimpan `nama`, `asal`, dan kerangka perilaku |
| `Gitar.java` | 🎸 Subclass | Bunyi "Jreng", punya atribut `jumlahSenar` |
| `Piano.java` | 🎹 Subclass | Bunyi "Tit tut", punya atribut `jumlahTuts` |
| `Drum.java` | 🥁 Subclass | Bunyi "Dum tak", dimainkan dengan stik |
| `Suling.java` | 🪈 Subclass | Bunyi "Fuuu fiuu", dimainkan dengan ditiup |
| `Main.java` | ▶️ Program utama | Membuat objek dan menampilkan polymorphism |

---

## 🔄 Alur Program

```mermaid
flowchart TD
    A([Mulai]) --> B[Buat ArrayList bertipe AlatMusik]
    B --> C[Tambah objek Gitar, Piano, Drum, Suling]
    C --> D{Masih ada alat<br/>di daftar?}
    D -- Ya --> E[Panggil perkenalan]
    E --> F[Java menjalankan bunyikan<br/>sesuai jenis objek]
    F --> D
    D -- Tidak --> G[Panggil mainkan untuk tiap alat]
    G --> H([Selesai])
```

---

## 📂 Struktur Proyek

```
PBO-ALAT-MUSIK/
├── README.md
├── AlatMusik.java    ← abstract class
├── Gitar.java
├── Piano.java
├── Drum.java
├── Suling.java
└── Main.java         ← program utama
```

---

## ▶️ Cara Menjalankan

**Syarat:** sudah terpasang **JDK**. Cek dengan `java -version`.

```bash
# 1. Clone repository
git clone https://github.com/nurlaelihdyti/PBO-ALAT-MUSIK.git

# 2. Masuk ke folder proyek
cd PBO-ALAT-MUSIK

# 3. Compile semua file
javac *.java

# 4. Jalankan program
java Main
```

---

## 🖥️ Contoh Output

```
==== PERKENALAN ALAT MUSIK ===
Nama      : Gitar
Asal      : Spanyol
Cara main : Senar dipetik atau dipetik dengan pick
Bunyi     : Jreng... jreng... (6 senar)
-------------------------------
Nama      : Piano
Asal      : Italia
Cara main : Tuts ditekan dengan jari
Bunyi     : Tit... tut... (88 tuts)
-------------------------------
Nama      : Drum
Asal      : Amerika Serikat
Cara main : Dipukul dengan stik
Bunyi     : Dum... tak... dum... tak...
-------------------------------
Nama      : Suling
Asal      : Indonesia
Cara main : Ditiup sambil menutup lubang dengan jari
Bunyi     : Fuuu... fiuu... fuuu...
-------------------------------
===== KONSER MINI =====
>> Gitar dimainkan: Jreng... jreng... (6 senar)
>> Piano dimainkan: Tit... tut... (88 tuts)
>> Drum dimainkan: Dum... tak... dum... tak...
>> Suling dimainkan: Fuuu... fiuu... fuuu...
```

---

## ✅ Poin yang Diminta Soal

| Poin | Status | Letak dalam kode |
|------|:--:|------------------|
| Abstract Class | ✅ | `AlatMusik.java` |
| Abstract Method | ✅ | `bunyikan()` dan `getCaraMain()` |
| Inheritance | ✅ | `Gitar`, `Piano`, `Drum`, `Suling` memakai `extends` |
| Polymorphism | ✅ | `ArrayList<AlatMusik>` dan `mainkan(AlatMusik)` di `Main.java` |
| Penjelasan kode | ✅ | Komentar di kode dan README ini |

---

## 🎯 Kesimpulan

- **Abstract class** dipakai untuk membuat kerangka umum yang tidak berdiri sendiri.
- **Inheritance** menghindari penulisan kode berulang.
- **Polymorphism** membuat program fleksibel: alat musik baru (misalnya Biola) bisa ditambah tanpa mengubah `Main`, cukup membuat subclass baru.

---

<div align="center">

Dibuat dengan ☕ dan 💻 oleh **Nurlaeli Hidayati** & **Qolbina Aprianti**

⭐ Universitas Mataram, 2026 ⭐

</div>
