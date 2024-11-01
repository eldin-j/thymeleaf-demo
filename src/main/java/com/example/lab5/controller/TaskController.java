package com.example.lab5.controller;

import com.example.lab5.model.Task;
import com.example.lab5.model.TaskStatus;
import com.example.lab5.model.User;
import com.example.lab5.service.TaskService;
import com.example.lab5.service.TaskStatusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskStatusService taskStatusService;
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskStatusService taskStatusService, TaskService taskService) {
        this.taskStatusService = taskStatusService;
        this.taskService = taskService;
    }

    @GetMapping
    public String listTasks(@AuthenticationPrincipal User user, Model model) {
        List<Task> tasks = taskService.findTasksByUser(user);
        model.addAttribute("tasks", tasks);
        return "task/list";
    }

    @GetMapping("/new")
    public String createTaskForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("categories", taskService.findAllCategories());
        model.addAttribute("priorities", taskService.findAllPriorities());
        return "task/new";
    }

    @PostMapping
    public String createTask(@Valid @ModelAttribute Task task,
                             @AuthenticationPrincipal User user,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please correct the errors in the form.");
            return "redirect:/tasks/new";
        }

        if (user == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "User not authenticated.");
            return "redirect:/tasks/new";
        }

        task.setUser(user);

        if (task.getStatus() == null) {
            TaskStatus defaultStatus = taskStatusService.findDefaultStatus();
            task.setStatus(defaultStatus);
        }

        try {
            taskService.save(task);
            redirectAttributes.addFlashAttribute("successMessage", "Task created successfully!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/tasks/new";
        }

        return "redirect:/tasks";
    }

    @GetMapping("/{id}/edit")
    public String editTaskForm(@PathVariable Long id, Model model) {
        Task task = taskService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task Id:" + id));
        model.addAttribute("task", task);
        model.addAttribute("categories", taskService.findAllCategories());
        model.addAttribute("priorities", taskService.findAllPriorities());
        return "task/edit";
    }

    @PostMapping("/{id}/edit")
    public String editTask(@PathVariable Long id,
                           @Valid @ModelAttribute Task task,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please correct the errors in the form.");
            return "redirect:/tasks/" + id + "/edit";
        }

        task.setId(id);

        try {
            taskService.save(task);
            redirectAttributes.addFlashAttribute("successMessage", "Task updated successfully!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/tasks/" + id + "/edit";
        }

        return "redirect:/tasks";
    }

    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        taskService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "Task deleted successfully!");
        return "redirect:/tasks";
    }
}
