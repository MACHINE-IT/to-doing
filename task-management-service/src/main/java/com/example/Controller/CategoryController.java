package com.example.Controller;

import com.example.config.RestEndpoints;
import com.example.model.CategoryTable;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.request.CategoryRequest;
import com.example.response.CategoryResponse;
import com.example.service.CategoryService;
import com.example.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(RestEndpoints.BASE_CATEGORY_API)
public class CategoryController {

    private final CategoryService categoryService;

    private final UserService userService;

    @Autowired
    public CategoryController(CategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest, HttpServletRequest request) {
        User user = userService.getUserIdByToken(request);
        categoryRequest.setCategoryOwner(user);
        CategoryResponse newCategory = categoryService.createCategory(categoryRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getCategories(HttpServletRequest request) {
        User user = userService.getUserIdByToken(request);
        List<CategoryResponse> categoryResponses = categoryService.getAllCategories(user);
        return ResponseEntity.ok(categoryResponses);
    }

    @DeleteMapping(RestEndpoints.DELETE_CATEGORY)
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") long categoryId, HttpServletRequest request) throws IllegalAccessException {
        // Fetch the authenticated user
        User user = userService.getUserIdByToken(request);

        // Attempt to delete the category
        boolean isDeleted = categoryService.deleteCategory(user, categoryId);

        if (isDeleted) {
            // If deletion is successful, return an appropriate response
            // (e.g., a success message or the list of remaining categories)
            return ResponseEntity.ok().body("Category successfully deleted.");
        } else {
            // If deletion failed, return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while deleting the category.");
        }
    }

    @PutMapping(RestEndpoints.UPDATE_CATEGORY)
    public ResponseEntity<?> updateCategory(@PathVariable("categoryId") long categoryId, @RequestBody CategoryRequest categoryRequest, HttpServletRequest request) throws IllegalAccessException {
        User user = userService.getUserIdByToken(request);

        // Attempt to delete the category
        CategoryTable categoryTable  = categoryService.updateCategory(user, categoryId, categoryRequest);

        return ResponseEntity.accepted().body("category updated successfully");
    }


}
