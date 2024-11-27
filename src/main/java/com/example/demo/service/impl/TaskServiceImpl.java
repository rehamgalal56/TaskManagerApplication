package com.example.demo.service.impl;

import com.example.demo.entity.Task;
import com.example.demo.entity.TaskStatus;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public void createTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void updateTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    @Override
    public List<Task> getTasksBeforeDueDate(LocalDate dueDate) {
        return taskRepository.findByDueDateBefore(dueDate);
    }



}
