package com.example.request;

import com.example.model.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {

        @NotEmpty(message = "please provide category name")
        private String categoryName;

        private User categoryOwner;

}
