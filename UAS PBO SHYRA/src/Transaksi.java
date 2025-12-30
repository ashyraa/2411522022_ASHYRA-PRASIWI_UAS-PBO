import java.util.Date;

// Poin c: Superclass yang mengimplementasikan interface Layanan
public abstract class Transaksi implements Layanan {
    // Poin a: Atribut (Data)
    protected String idTransaksi;
    protected String namaPelanggan;
    protected Date tanggal; // Poin e: Penggunaan class Date

    // Poin a: Constructor untuk inisialisasi objek
    public Transaksi(String id, String nama) {
        this.idTransaksi = id;
        this.namaPelanggan = nama;
        this.tanggal = new Date(); // Set tanggal saat ini
    }
}