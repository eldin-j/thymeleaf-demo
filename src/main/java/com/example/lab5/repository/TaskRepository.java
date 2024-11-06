package com.example.lab5.repository;

import com.example.lab5.model.Task;
import com.example.lab5.model.User;
import com.example.lab5.model.Category;
import com.example.lab5.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Find all tasks by a specific user
    List<Task> findByUser(User user);

    // Find a task by its ID and user
    Optional<Task> findByIdAndUser(Long id, User user);

    // Find tasks by user and category
    List<Task> findByUserAndCategory(User user, Category category);

    // Find tasks by user and status
    List<Task> findByUserAndStatus(User user, TaskStatus status);

    // Find tasks by user, filtered by status or category (using OR condition)
    List<Task> findByUserAndStatusIdOrCategoryId(User user, Long statusId, Long categoryId);

    // Find tasks by user and sort by due date
    List<Task> findByUserOrderByDueDateAsc(User user);

    // Find tasks by user and filter by priority (optional for additional filtering functionality)
    List<Task> findByUserAndPriorityId(User user, Long priorityId);
}