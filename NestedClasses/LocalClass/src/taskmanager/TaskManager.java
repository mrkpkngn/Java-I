package taskmanager;

public class TaskManager {
    private String _managerName;

    public TaskManager(String managerName) {
        this._managerName = managerName;
    }

    public void addTask(String taskName){
        class Task{
            private String _taskName;

            public Task(String taskName) {
                this._taskName = taskName;
            }

            public void displayTask(){
                System.out.println("Task Name: " + this._taskName);
                System.out.println("Managed by: " + _managerName);
            }
        }

        Task task = new Task(taskName);
        task.displayTask();
    }

}

