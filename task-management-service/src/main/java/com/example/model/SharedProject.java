//package com.example.model;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "shared_project",uniqueConstraints = @UniqueConstraint(
//        columnNames = {"members", "projects"}
//))
//public class SharedProject {
//
//    @Id
//    private long sharedProjectId;
//
//    @ManyToOne
//    private User members;
//
//    @ManyToOne
//    private Project projects;
//}
