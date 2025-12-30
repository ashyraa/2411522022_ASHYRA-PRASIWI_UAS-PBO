// Poin c: Subclass (Inheritance) yang mewarisi Transaksi
public class Penitipan extends Transaksi {
    private String jenisHewan;
    private int durasi;
    private double hargaPerHari = 50000;

    // Poin a: Constructor Subclass
    public Penitipan(String id, String nama, String jenis, int durasi) {
        super(id, nama); // Memanggil constructor superclass
        this.jenisHewan = jenis;
        this.durasi = durasi;
    }

    @Override
    public void tampilkanDetail() {
        // Poin e: Manipulasi String (toUpperCase)
        System.out.println("ID Transaksi   : " + idTransaksi.toUpperCase());
        System.out.println("Nama Pelanggan : " + namaPelanggan.trim()); // Manipulasi String (trim)
        System.out.println("Jenis Hewan    : " + jenisHewan);
        System.out.println("Tanggal Masuk  : " + tanggal);
    }

    @Override
    public double hitungTotal() {
        // Poin d: Perhitungan matematika
        return durasi * hargaPerHari;
    }
}