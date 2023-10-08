package com.example.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long projectId;

    @OneToOne
    private User projectOwner;


    @ManyToMany
    @JoinTable(
            name = "shared_project",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private Set<User> projectMembers;

    @OneToMany(mappedBy = "projectId")
    private List<Comment> comment;
}
