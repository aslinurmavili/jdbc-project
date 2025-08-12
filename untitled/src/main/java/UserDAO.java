import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final String url = "jdbc:postgresql://localhost:5432/jdbc";
    private final String user = "postgres";
    private final String password = "1";

    // Bağlantı al
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Yeni kullanıcı ekle
    public void save(User userObj) {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userObj.getName());
            stmt.setString(2, userObj.getEmail());

            int rowsInserted = stmt.executeUpdate();
            System.out.println(rowsInserted + " kullanıcı eklendi.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Tüm kullanıcıları listele
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                User userObj = new User();
                userObj.setId(rs.getInt("id"));
                userObj.setName(rs.getString("name"));
                userObj.setEmail(rs.getString("email"));
                users.add(userObj);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Kullanıcı güncelle
    public void update(User userObj) {
        String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userObj.getName());
            stmt.setString(2, userObj.getEmail());
            stmt.setInt(3, userObj.getId());

            int rowsUpdated = stmt.executeUpdate();
            System.out.println(rowsUpdated + " kullanıcı güncellendi.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Kullanıcı sil
    public void delete(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();
            System.out.println(rowsDeleted + " kullanıcı silindi.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
