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
//@Table(name = "shared_task_user")
//public class ShareTaskUser {
//
//    @Id
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "task_id", referencedColumnName = "id")
//    private Task task;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User user;
//
//
//}
