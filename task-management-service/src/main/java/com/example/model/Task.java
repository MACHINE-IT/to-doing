package com.example.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.util.Lazy;

import java.io.Serializable;
import java.util.Set;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Builder
@Getter
@Setter
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

    @ManyToOne()
    private CategoryTable category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "shared_tasks",
            joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId")
    )
    private Set<User> sharedWithUsers;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "task_assignments",
     joinColumns = @JoinColumn(name = "task_id"),
     inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> assignedToUsers;

    @OneToMany
    private List<Attachment> attachments;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reminder> reminders;

}
