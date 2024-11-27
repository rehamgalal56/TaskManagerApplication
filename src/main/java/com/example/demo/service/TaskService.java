package com.example.demo.service;

import com.example.demo.entity.Task;
import com.example.demo.entity.TaskStatus;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    void createTask(Task task);
    void updateTask(Task task);
    void deleteTask(Long id);
    List<Task> getTasksByStatus(TaskStatus status);
    List<Task> getTasksBeforeDueDate(LocalDate dueDate);
}
