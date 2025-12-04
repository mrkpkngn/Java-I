import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class App {
    public static void main(String[] args) throws Exception {
        String jdbcURL = "jdbc:mysql://vsrvfeia0h-64.vsb.cz:3306/user_tasks_db";
        String username = "guest";
        String password = "guest_password";
        try{
            Connection connection =  DriverManager.getConnection(jdbcURL, username, password);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM tasks";
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String duedate = resultSet.getString("due_date");
                String status = resultSet.getString("status");
                String email = resultSet.getString("user_email");
                System.out.println(id + ", " + title + ", " + duedate + ", " + status + ", " + email);
            }

            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
