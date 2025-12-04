package com.task.repository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;
import com.task.model.TaskItem;
import com.task.model.TaskStatus;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class FileTaskRepository implements TaskRepository {
    private Vector<TaskItem> _taskItems;
    private int _totalTaskItems;
    private String _url = "jdbc:mysql://vsrvfeia0h-64.vsb.cz:3306/user_tasks_db";
    private String _username = "guest";
    private String _password = "guest_password"; 
    private Connection _connection;
    private static final String FILENAME = "tasks.ser";

    public FileTaskRepository() {
        this._taskItems = new Vector<TaskItem>();
        try {
            _connection = DriverManager.getConnection(_url, _username, _password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "SELECT MAX(id) AS max_id FROM tasks";
        try{
            PreparedStatement stmt = _connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                this._totalTaskItems = rs.getInt("max_id");
            } else {
                this._totalTaskItems = 0;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        System.out.println("Current max ID from DB: " + this._totalTaskItems);

        loadTaskFromDB();
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
    public TaskItem save(TaskItem taskToSave) {

        TaskItem existingTask = findById(taskToSave.getId());

        if (existingTask != null) {
            _taskItems.remove(existingTask);
            _taskItems.add(taskToSave);
        } else {
            _taskItems.add(taskToSave);
        }

        saveTasksToFile();
        return taskToSave;
    }

    @Override
    public boolean delete(TaskItem taskItem){
        boolean success = _taskItems.remove(taskItem);

        if(success){
            saveTasksToFile();
        }

        return success;
    }

    @Override
    public int getNextID() {
        this._totalTaskItems++;
        return this._totalTaskItems;
    }

    @Override
    public void reload(){
        this._taskItems.clear();
        loadTasksFromFile();
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

    private void loadTaskFromDB() {
        String query = "SELECT id, title, due_date, status, user_email FROM tasks";
        try (PreparedStatement stmt = _connection.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String dueDate = rs.getString("due_date");
                int taskStatusInt = rs.getInt("status");
                TaskStatus taskStatus = TaskStatus.values()[taskStatusInt];
                String email = rs.getString("user_email");
                TaskItem taskItem = new TaskItem(id, title, dueDate, taskStatus, email);
                _taskItems.add(taskItem);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
