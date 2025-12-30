import java.sql.*; // Library JDBC
import java.util.ArrayList; // Poin g: Collection Framework
import java.util.Scanner; // Untuk input dari console

public class PetshopApp {
    // Pengaturan koneksi database MySQL
    static final String URL = "jdbc:mysql://localhost:3306/db_petshop";
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        // Poin g: Menggunakan ArrayList untuk menyimpan list objek
        ArrayList<Penitipan> daftarPenitipan = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        
        // Poin f: Exception Handling untuk menangani error database
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("=== KONEKSI DATABASE BERHASIL ===");

            while (true) { // Poin d: Perulangan
                System.out.println("\n--- SISTEM MANAJEMEN PETSHOP HAPPY PAWS ---");
                System.out.println("1. Tambah Data Penitipan (Create)");
                System.out.println("2. Lihat Semua Data (Read)");
                System.out.println("3. Hapus Data Penitipan (Delete)");
                System.out.println("4. Keluar");
                System.out.print("Pilih Menu [1-4]: ");
                int menu = input.nextInt();

                if (menu == 1) {
                    System.out.println("\n--- Input Data Baru ---");
                    System.out.print("ID Transaksi (Contoh: P01): "); String id = input.next();
                    System.out.print("Nama Pemilik: "); String nama = input.next();
                    System.out.print("Jenis Hewan: "); String jenis = input.next();
                    System.out.print("Durasi Menginap (Hari): "); int hariInput = input.nextInt();

                    // Poin a: Membuat Objek
                    Penitipan pNew = new Penitipan(id, nama, jenis, hariInput);
                    
                    // Poin h: CRUD Create (Kolom disesuaikan menjadi durasi_hari)
                    String sql = "INSERT INTO penitipan (id_transaksi, nama_pelanggan, jenis_hewan, durasi_hari, total_bayar) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    
                    pstmt.setString(1, pNew.idTransaksi.toUpperCase()); 
                    pstmt.setString(2, pNew.namaPelanggan);
                    pstmt.setString(3, jenis);
                    pstmt.setInt(4, hariInput); // Sesuai kolom durasi_hari
                    pstmt.setDouble(5, pNew.hitungTotal());
                    
                    pstmt.executeUpdate();
                    System.out.println("Berhasil! Data telah tersimpan.");

                } else if (menu == 2) {
                    // Poin h: CRUD Read
                    System.out.println("\n--- DAFTAR DATA DI DATABASE ---");
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM penitipan");
                    
                    while (rs.next()) {
                        System.out.println(
                            "ID: " + rs.getString("id_transaksi") + 
                            " | Pemilik: " + rs.getString("nama_pelanggan") + 
                            " | Hewan: " + rs.getString("jenis_hewan") + 
                            " | Durasi: " + rs.getInt("durasi_hari") + " Hari" + // Sesuai kolom durasi_hari
                            " | Total: Rp" + rs.getDouble("total_bayar")
                        );
                    }

                } else if (menu == 3) {
                    // Poin h: CRUD Delete
                    System.out.print("\nMasukkan ID Transaksi yang ingin dihapus: ");
                    String idHapus = input.next();
                    
                    String sql = "DELETE FROM penitipan WHERE id_transaksi = ?";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, idHapus);
                    
                    int rowsDeleted = pstmt.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println("Data ID " + idHapus + " berhasil dihapus!");
                    } else {
                        System.out.println("Data tidak ditemukan.");
                    }

                } else if (menu == 4) {
                    System.out.println("Keluar program.");
                    break; 
                }
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}