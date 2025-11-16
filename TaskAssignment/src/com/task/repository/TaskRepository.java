package com.task.repository;
import com.task.model.TaskItem;
import com.task.model.TaskStatus;
import java.util.Vector;


public interface TaskRepository {
    TaskItem findById(int id);
    Vector<TaskItem> findAll();
    TaskItem save(TaskItem taskItem);
    boolean delete(TaskItem taskItem);
    int getNextID();
    void reload();
}
