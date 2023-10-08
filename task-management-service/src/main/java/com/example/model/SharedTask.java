//package com.example.model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Table(name = "shared_task_user", uniqueConstraints = @UniqueConstraint(columnNames = {"task_id", "user_id"}))
//public class SharedTask {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "task_id", referencedColumnName = "id")
//    private Task task;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "userId")
//    private User user;
//}
