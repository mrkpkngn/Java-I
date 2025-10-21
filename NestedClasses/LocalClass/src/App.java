import taskmanager.*;

public class App {
    public static void main(String[] args) throws Exception {
        TaskManager taskManager = new TaskManager("John");
        taskManager.addTask("Complete project report");
        taskManager.addTask("Schedule team meeting");
    }
}
