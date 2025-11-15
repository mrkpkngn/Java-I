import javax.swing.SwingUtilities;

import com.task.controller.TaskItemGUI;
import com.task.repository.TaskRepository;
import com.task.repository.FileTaskRepository;
import com.task.service.TaskService;
import com.task.service.TaskServiceImpl;

public class App {
    public static void main(String[] args) throws Exception {
        TaskRepository taskRepository = new FileTaskRepository();
        TaskService taskService = new TaskServiceImpl(taskRepository);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TaskItemGUI(taskService);
            }
        });
    }
}