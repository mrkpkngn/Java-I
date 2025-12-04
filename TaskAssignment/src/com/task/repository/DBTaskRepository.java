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

public class DBTaskRepository implements TaskRepository{

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

        String query ="Select Max(id) as Max_id from task ";
        try {
            PreparedStatement stmt =_connection.prepareStatement(query);
            ResultSet rs= stmt.executeQuery();
            if (rs.next()){
                this._totalTaskItems=rs.getInt("Max_id");
            } else {
                this._totalTaskItems=0;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("Total Task Items : " + this._totalTaskItems  );
     }

    


    @Override
    public TaskItem findById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Vector<TaskItem> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public TaskItem save(TaskItem taskItem) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public boolean delete(TaskItem taskItem) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public int getNextID() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNextID'");
    }

    @Override
    public void reload() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reload'");
    }
    
}
