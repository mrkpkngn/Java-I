package com.task.service;

import java.util.Vector;

import com.task.model.TaskItem;
import com.task.model.TaskStatus;

public interface TaskService {
    TaskItem createTask(String title, String duedate, TaskStatus taskStatus, String email);
    TaskItem getTaskById(int id);
    Vector<TaskItem> getAllTasks();
}
