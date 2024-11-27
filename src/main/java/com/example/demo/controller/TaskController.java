package com.example.demo.controller;


import com.example.demo.entity.Task;
import com.example.demo.entity.TaskStatus;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/tasks")

public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public String getAllTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "list";
    }

    @GetMapping("/create")
    public String createTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "create";
    }

    @PostMapping("/create")
    public String createTask(@ModelAttribute("task") Task task) {
        taskService.createTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String editTaskForm(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "edit";
    }

    @PostMapping("/edit")
    public String updateTask(@ModelAttribute("task") Task task) {
        taskService.updateTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @GetMapping("/filter")
    public String filterTasks(@RequestParam(required = false) TaskStatus status,
                              @RequestParam(required = false) String dueDate, Model model) {
        List<Task> tasks;
        if (status != null) {
            tasks = taskService.getTasksByStatus(status);
        } else if (dueDate != null) {
            tasks = taskService.getTasksBeforeDueDate(LocalDate.parse(dueDate));
        } else {
            tasks = taskService.getAllTasks();
        }
        model.addAttribute("tasks", tasks);
        return "list";
    }

    @GetMapping("/complete/{id}")
    public String markTaskComplete(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        task.setStatus(TaskStatus.COMPLETED);
        taskService.updateTask(task);
        return "redirect:/tasks";
    }
}

