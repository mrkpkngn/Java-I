package com.task.service;

import com.task.model.TaskItem;
import com.task.model.TaskStatus;
import com.task.repository.TaskRepository;
import java.util.Vector;

public class TaskServiceImpl implements TaskService {
    private TaskRepository _repo;

    public TaskServiceImpl(TaskRepository repo){
        this._repo = repo;
    }

    @Override
    public TaskItem createTask(String title, String duedate, TaskStatus taskStatus, String email){
        int newId = _repo.getNextID();
        TaskItem newTask = new TaskItem(newId, title, duedate, taskStatus, email);
        return _repo.save(newTask);
    }

    @Override
    public TaskItem getTaskById(int id){
        return _repo.findById(id);
    }

    @Override
    public Vector<TaskItem> getAllTasks(){
        return _repo.findAll();
    }
}
