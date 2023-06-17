package com.example.repository;

import com.example.model.Category;
import com.example.model.Priority;
import com.example.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByPriority(Priority priority);
    List<Task> findAllByCategory(Category category);
    List<Task> findByUserId(long userId);
}
