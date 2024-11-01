package com.example.lab5.repository;

import com.example.lab5.model.TaskPriority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskPriorityRepository extends JpaRepository<TaskPriority, Long> {
    // Additional query methods can be defined here if needed
}
