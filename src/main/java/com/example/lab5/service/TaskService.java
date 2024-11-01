package com.example.lab5.service;

import com.example.lab5.model.Category;
import com.example.lab5.model.Task;
import com.example.lab5.model.TaskPriority;
import com.example.lab5.model.User;
import com.example.lab5.repository.CategoryRepository;
import com.example.lab5.repository.TaskPriorityRepository;
import com.example.lab5.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;
    private final TaskPriorityRepository taskPriorityRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository,
                       CategoryRepository categoryRepository,
                       TaskPriorityRepository taskPriorityRepository) {
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
        this.taskPriorityRepository = taskPriorityRepository;
    }

    public List<Task> findTasksByUser(User user) {
        return taskRepository.findByUser(user);
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll(); // Retrieve all categories from the database
    }

    public List<TaskPriority> findAllPriorities() {
        return taskPriorityRepository.findAll(); // Retrieve all task priorities from the database
    }

    private void validateDueDate(LocalDate dueDate) {
        LocalDate today = LocalDate.now();

        if (dueDate.isBefore(today)) {
            throw new IllegalArgumentException("Due date must be in the future.");
        }
        if (dueDate.isAfter(today.plusYears(10))) {
            throw new IllegalArgumentException("Due date cannot be more than 10 years from today.");
        }
    }
}
