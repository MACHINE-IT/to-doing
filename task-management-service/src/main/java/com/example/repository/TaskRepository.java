package com.example.repository;

import com.example.model.Category;
import com.example.model.Priority;
import com.example.model.Task;
import com.example.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Long>, JpaRepository<Task, Long> {
    List<Task> findAllByPriority(Priority priority);
    List<Task> findAllByCategory(Category category);
    Page<Task> findByOwnerId(User ownerId,
                            Pageable pageable);


}
