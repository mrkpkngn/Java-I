import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import com.task.*;
import com.task.model.TaskItem;
import com.task.model.TaskStatus;

public class InsertQuery {
    public static void main(String[] args) {
        String url = "jdbc:mysql://vsrvfeia0h-64.vsb.cz:3306/user_tasks_db";
        String user = "guest";
        String password = "guest_password";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 1. Use Question Marks (?) as placeholders
        String query = "INSERT INTO tasks (title, due_date, status, user_email) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(query);

            // 2. Fill in the data safely
            stmt.setString(1, "Task 2"); // Title
            stmt.setString(2, "2024-12-31"); // Date (or use stmt.setDate if using java.sql.Date)
            stmt.setInt(3, 0); // Status
            stmt.setString(4, "bob@example.com"); // Email

            // 3. Execute
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Rows inserted: " + rowsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
