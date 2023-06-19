package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @SequenceGenerator(name = "task_id", sequenceName = "task_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_id")
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    private LocalDateTime dueDate;
    private Priority priority;

    @Column(nullable = false)
    private Category category;

//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    @Column(name = "user_id")
    private long userId;

    @CreatedDate
    private LocalDateTime createdAt;
}
