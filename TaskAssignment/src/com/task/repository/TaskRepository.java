package com.task.repository;
import com.task.model.TaskItem;
import java.util.Vector;


public interface TaskRepository {
    TaskItem findById(int id);
    Vector<TaskItem> findAll();
    TaskItem save(TaskItem taskItem);
    int getNextID();
}
