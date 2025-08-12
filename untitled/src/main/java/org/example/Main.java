import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/jdbc";
        String user = "postgres";
        String password = "1";

        Connection conn = null;
        PreparedStatement updateStmt = null;
        PreparedStatement deleteStmt = null;

        try {
            conn = DriverManager.getConnection(url, user, password);

            // 1. Güncelleme işlemi
            String updateSql = "UPDATE users SET name = ? WHERE id = ?";
            updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setString(1, "Yeni İsim");
            updateStmt.setInt(2, 1);

            int updateCount = updateStmt.executeUpdate();
            System.out.println("Güncellenen satır sayısı: " + updateCount);

            // 2. Silme işlemi
            String deleteSql = "DELETE FROM users WHERE id = ?";
            deleteStmt = conn.prepareStatement(deleteSql);
            deleteStmt.setInt(1, 2);

            int deleteCount = deleteStmt.executeUpdate();
            System.out.println("Silinen satır sayısı: " + deleteCount);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (updateStmt != null) updateStmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (deleteStmt != null) deleteStmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}
