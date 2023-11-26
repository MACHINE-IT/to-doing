package com.example.response;

import com.example.model.Task;
import lombok.Data;

import java.util.Set;

@Data
public class CategoryResponse {
    private long id;
    private String categoryName;
    private Set<Task> tasks ;
}
