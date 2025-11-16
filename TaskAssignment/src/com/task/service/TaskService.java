package com.task.service;

import java.util.Vector;

import com.task.model.TaskItem;
import com.task.model.TaskStatus;

public interface TaskService {
    TaskItem createTask(String title, String duedate, TaskStatus taskStatus, String email);
    TaskItem updateTask(int id, String title, String duedate, TaskStatus taskStatus, String email);
    Boolean deleteTask(int id);
    TaskItem getTaskById(int id);
    Vector<TaskItem> getAllTasks();
    void reloadTasks();
}
