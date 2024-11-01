package com.example.lab5.repository;

import com.example.lab5.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Additional query methods can be defined here if needed
}
