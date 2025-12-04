import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import com.task.*;
import com.task.model.TaskItem;
import com.task.model.TaskStatus;

import java.util.Vector;

public class QueryTry {
    public static void main(String[] args) {
        String url = "jdbc:mysql://vsrvfeia0h-64.vsb.cz:3306/user_tasks_db";
        String user = "guest";
        String password = "guest_password";
        Vector<TaskItem> taskItems = new Vector<TaskItem>();
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "SELECT * FROM tasks";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String dueDate = rs.getString("due_date");
                int taskStatusInt = rs.getInt("status");
                TaskStatus taskStatus = TaskStatus.values()[taskStatusInt];
                String email = rs.getString("user_email");
                TaskItem taskItem = new TaskItem(id, title, dueDate, taskStatus, email);
                taskItems.add(taskItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(TaskItem task : taskItems){
            System.out.println(task.getId() + " | " + task.getTitle() + " | " + task.getdueDate() + " | " + task.getTaskStatus() + " | " + task.getEmail());
        }

    }
}
