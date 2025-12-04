import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Insert {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://vsrvfeia0h-64.vsb.cz:3306/user_tasks_db";
        String username = "guest";
        String password = "guest_password";

        try {
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            String sql = "INSERT INTO users (email, name) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "mark@example.com");
            statement.setString(2, "Mark Pakingan");
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
