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

    @Override
    public TaskItem updateTask(int id, String title, String duedate, TaskStatus taskStatus, String email){
        TaskItem selectedTask = _repo.findById(id);
        if(selectedTask != null){
            selectedTask.setTitle(title);
            selectedTask.setDueDate(duedate);
            selectedTask.setTaskStatus(taskStatus);
            selectedTask.setEmail(email);
            return _repo.save(selectedTask);
        }

        return null;
    }

    @Override
    public Boolean deleteTask(int id){
        TaskItem selectedTask = _repo.findById(id);
        if(selectedTask != null){
            return _repo.delete(selectedTask);
        }
        return false;
    }

    @Override
    public void reloadTasks(){
        _repo.reload();
    }
}
