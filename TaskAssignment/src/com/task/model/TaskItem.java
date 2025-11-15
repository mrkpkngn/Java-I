package com.task.model;
import java.io.Serializable;

public class TaskItem implements Serializable {
    private int _id;
    private String _title;
    private String _dueDate;
    private TaskStatus _taskStatus;
    private String _email;
    private static final long serialVersionUID = 1L;

    public TaskItem(int id, String title, String duedate, TaskStatus taskStatus, String email){
        this._id = id;
        this._title = title;
        this._dueDate = duedate;
        this._taskStatus = taskStatus;
        this._email = email;
    }

    //Getters
    public int getId(){
        return this._id;
    }

    public String getTitle(){
        return this._title;
    }

    public String getdueDate(){
        return this._dueDate;
    }

    public TaskStatus getTaskStatus(){
        return this._taskStatus;
    }

    public String getEmail(){
        return this._email;
    }

    @Override
    public String toString(){
        return "TaskItem{id=" + this._id + ", title='" + this._title +"', dueDate='" + this._dueDate + "', taskStatus='"  + this._taskStatus + "', email='" + this._email + "'}";
    }
}
