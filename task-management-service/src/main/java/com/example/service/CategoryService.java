package com.example.service;

import com.example.model.CategoryTable;
import com.example.model.User;
import com.example.request.CategoryRequest;
import com.example.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest categoryRequest);

    /**
     * Retrieves all categories.
     *
     * @return A list of categories.
     */
    List<CategoryResponse> getAllCategories(User categoryOwner);

    /**
     * Retrieves a category by its ID.
     *
     * @param categoryId The ID of the category.
     * @return The category with the specified ID.
     */
    CategoryTable getCategoryById(Long categoryId);

    /**
     * Updates an existing category.
     *
     * @param categoryRequest The category with updated details.
     * @param categoryId      The ID of the category to update.
     * @return The updated category.
     */
    CategoryTable updateCategory(User owner, Long categoryId, CategoryRequest categoryRequest) throws IllegalAccessException;


    /**
     * Deletes a category by its ID.
     *
     * @param categoryId The ID of the category to be deleted.
     */
    boolean deleteCategory(User owner, Long categoryId) throws IllegalAccessException;
}
