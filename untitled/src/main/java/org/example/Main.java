import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. JDBC ile güncelleme ve silme işlemleri
        String url = "jdbc:postgresql://localhost:5432/jdbc";
        String user = "postgres";
        String password = "1";

        Connection conn = null;
        PreparedStatement updateStmt = null;
        PreparedStatement deleteStmt = null;

        try {
            conn = DriverManager.getConnection(url, user, password);

            // Güncelleme
            String updateSql = "UPDATE users SET name = ? WHERE id = ?";
            updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setString(1, "Yeni İsim");
            updateStmt.setInt(2, 1);
            int updateCount = updateStmt.executeUpdate();
            System.out.println("Güncellenen satır sayısı: " + updateCount);

            // Silme
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

        // 2. DAO kullanımı
        UserDAO userDAO = new UserDAO();

        // Yeni kullanıcı ekle
        userDAO.save(new User(0, "Ahmet", "ahmet@mail.com"));

        // Tüm kullanıcıları listele
        List<User> users = userDAO.findAll();
        for (User u : users) {
            System.out.println(u.getId() + " - " + u.getName() + " - " + u.getEmail());
        }

        // Kullanıcı güncelle
        User userToUpdate = new User(1, "Ahmet Yılmaz", "ahmety@mail.com");
        userDAO.update(userToUpdate);

        // Kullanıcı sil
        userDAO.delete(2);
    }
}
