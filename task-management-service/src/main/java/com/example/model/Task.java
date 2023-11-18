package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.util.Set;

import java.time.LocalDateTime;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @CreatedDate
    private LocalDateTime creationDate;

    private LocalDateTime dueDate;

    private LocalDateTime completionDate;

    private boolean isImportant;

    private TaskStatus taskStatus;

    @Column(nullable = false)
    private Category category;

    @ManyToOne()
    @JoinColumn(name = "owner_id")
    private User ownerId;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "shared_tasks",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private Set<User> taskMembers;

    @Column(name = "reminder")
    private LocalDateTime reminder;
}
