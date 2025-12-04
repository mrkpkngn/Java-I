package com.task.repository;

import java.io.IOException;
import java.util.Vector;
import com.task.model.TaskItem;
import com.task.model.TaskStatus;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DBTaskRepository implements TaskRepository {

    private Vector<TaskItem> _taskItems;
    private int _totalTaskItems;
    private String _url = "jdbc:mysql://vsrvfeia0h-64.vsb.cz:3306/user_tasks_db";
    private String _username = "guest";
    private String _password = "guest_password";
    private Connection _connection;

    public DBTaskRepository() {
        this._taskItems = new Vector<TaskItem>();
        try {
            _connection = DriverManager.getConnection(_url, _username, _password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "Select Max(id) as Max_id from tasks";
        try {
            PreparedStatement stmt = _connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                this._totalTaskItems = rs.getInt("Max_id");
            } else {
                this._totalTaskItems = 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Total Task Items : " + this._totalTaskItems);
        reload();
    }

    @Override
    public TaskItem findById(int id) {

        String query = "SELECT * FROM tasks WHERE id = ?";
        try {
            PreparedStatement stmt = _connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String title = rs.getString("title");
                String dueDate = rs.getString("due_date");
                int taskStatusInt = rs.getInt("status");
                TaskStatus taskStatus = TaskStatus.values()[taskStatusInt];
                String email = rs.getString("user_email");
                TaskItem taskItem = new TaskItem(id, title, dueDate, taskStatus, email);
                return taskItem;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Vector<TaskItem> findAll() {
        reload();
        return this._taskItems;
    }

    @Override
    public TaskItem save(TaskItem taskItem) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public boolean delete(TaskItem taskItem) {
        int idToDelete = taskItem.getId();
        String query = "DELETE FROM tasks WHERE id = ?";
        try {
            PreparedStatement stmt = _connection.prepareStatement(query);
            stmt.setInt(1, idToDelete);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                for (TaskItem task : _taskItems) {
                    if (task.getId() == idToDelete) {
                        _taskItems.remove(task);
                        break;
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public int getNextID() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNextID'");
    }

    @Override
    public void reload() {
        String query = "SELECT * FROM tasks";
        try {
            PreparedStatement stmt = _connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            this._taskItems.clear();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TaskItem update(TaskItem taskItem) {
        String query = "UPDATE tasks SET title = ?, due_date = ?, status = ?, user_email = ? WHERE id = ?";
        try {
            PreparedStatement stmt = _connection.prepareStatement(query);
            stmt.setString(1, taskItem.getTitle());
            stmt.setString(2, taskItem.getdueDate());
            stmt.setInt(3, taskItem.getTaskStatus().ordinal());
            stmt.setString(4, taskItem.getEmail());
            stmt.setInt(5, taskItem.getId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                for (TaskItem task : _taskItems) {
                    if (task.getId() == taskItem.getId()) {
                        task.setTitle(taskItem.getTitle());
                        task.setDueDate(taskItem.getdueDate());
                        task.setTaskStatus(taskItem.getTaskStatus());
                        task.setEmail(taskItem.getEmail());
                        return task;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

   @Override
public TaskItem create(TaskItem taskItem) {
    String query = "INSERT INTO tasks (title, due_date, status, user_email) VALUES (?, ?, ?, ?)";
    try {
        PreparedStatement stmt = _connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        
        stmt.setString(1, taskItem.getTitle());
        stmt.setString(2, taskItem.getdueDate());
        stmt.setInt(3, taskItem.getTaskStatus().ordinal());
        stmt.setString(4, taskItem.getEmail());
        
        int rowsAffected = stmt.executeUpdate();
        
        if (rowsAffected > 0) {
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int newId = generatedKeys.getInt(1);
                taskItem.setId(newId);
                _taskItems.add(taskItem); 
                
                return taskItem;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}
}