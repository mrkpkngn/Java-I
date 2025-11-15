package com.task.repository;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;
import com.task.model.TaskItem;

public class FileTaskRepository implements TaskRepository {
    private Vector<TaskItem> _taskItems;
    private int _totalTaskItems;
    private static final String FILENAME = "tasks.ser";

    public FileTaskRepository(){
        this._taskItems = new Vector<TaskItem>();
        this._totalTaskItems = 0;
        loadTasksFromFile();
    }

    @Override
    public Vector<TaskItem> findAll() {
        return this._taskItems;
    }

    @Override
    public TaskItem findById(int findId) {
        for (TaskItem task : this._taskItems) {
            if (task.getId() == findId) {
                return task;
            }
        }
        return null;
    }

    @Override
    public TaskItem save(TaskItem task) {
        this._taskItems.add(task);
        saveTasksToFile();
        return task;
    }

    @Override
    public int getNextID(){
        this._totalTaskItems++;
        return this._totalTaskItems;
    }

    private void saveTasksToFile() {
        try (FileOutputStream fileOut = new FileOutputStream(FILENAME);
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

            out.writeObject(this._taskItems);
            System.out.println("All tasks saved to " + FILENAME);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTasksFromFile() {
        try (FileInputStream fileIn = new FileInputStream(FILENAME);
                ObjectInputStream in = new ObjectInputStream(fileIn)) {
            
            this._taskItems = (Vector<TaskItem>) in.readObject();

            // Set the ID counter to the highest loaded ID
            this._totalTaskItems = this._taskItems.stream()
                                        .mapToInt(TaskItem::getId)
                                        .max()
                                        .orElse(0);
            
            System.out.println("All tasks loaded from " + FILENAME);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No task file found. Starting fresh.");
        }
    }
}
