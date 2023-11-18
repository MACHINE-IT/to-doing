package com.example.repository;

import com.example.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Long>, JpaRepository<Task, Long> {
//    List<Task> findAllByPriority(Priority priority);
    List<Task> findAllByCategory(Category category);
    Page<Task> findByOwnerId(User ownerId,
                            Pageable pageable);

    @Query("SELECT t FROM Task t " +
            "WHERE (:ownerId IS NULL OR t.ownerId = :ownerId) " +
            "AND (:statuses IS NULL OR t.taskStatus IN :statuses) " +
            "OR (:categories IS NULL OR t.category IN :categories)")
    Page<Task> findByOptionalCriteria(
            @Param("ownerId") User ownerId,
            @Param("statuses") List<TaskStatus> statuses,
            @Param("categories") List<Category> categories,
            Pageable pageable
    );

    @Query("SELECT t FROM Task t WHERE t.ownerId = :ownerId AND t.taskStatus IN :taskStatus")
    Page<Task> findByOwnerIdAndTaskStatus(@Param("ownerId") User ownerId, @Param("taskStatus") List<TaskStatus> taskStatus, Pageable pageable);

    @Query("SELECT t FROM Task t WHERE t.ownerId = :ownerId AND t.category IN :categories")
    Page<Task> findByOwnerIdAndCategory(@Param("ownerId") User ownerId, @Param("categories") List<Category> categories, Pageable pageable);
//    Page<Task> findByOwnerIdOrTaskStatusInOrCategoryIn(User ownerId, List<TaskStatus> statuses, List<Category> categories, Pageable pageable);

    //taskRepository.findByOwnerIdAndTaskStatusInAndCategoryIn(User.builder().userId(userId).build(), statusEnums, categoryList, sortByGivenColumn )
}
