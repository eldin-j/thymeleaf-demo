package com.example.lab5.service;

import com.example.lab5.model.TaskStatus;
import com.example.lab5.repository.TaskStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskStatusService {

    private final TaskStatusRepository taskStatusRepository;

    @Autowired
    public TaskStatusService(TaskStatusRepository taskStatusRepository) {
        this.taskStatusRepository = taskStatusRepository;
    }

    public TaskStatus findDefaultStatus() {
        // Fetch a default status from the database, e.g., "Pending"
        return taskStatusRepository.findById(1L) // Assuming 1 is the default status ID
                .orElseThrow(() -> new IllegalStateException("Default status not found"));
    }
}
