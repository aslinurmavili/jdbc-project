import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Veritabanı bağlantı bilgileri
        String url = "jdbc:postgresql://localhost:5432/jdbc";
        String user = "postgres";
        String password = "1";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Bağlantıyı aç
            conn = DriverManager.getConnection(url, user, password);

            // Statement oluştur
            stmt = conn.createStatement();

            // Sorguyu çalıştır
            String sql = "SELECT * FROM users";
            rs = stmt.executeQuery(sql);

            // ResultSet ile satırları oku
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");

                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Kaynakları kapat
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}
