package com.example.service.impl;

import com.example.model.CategoryTable;
import com.example.model.User;
import com.example.repository.CategoryRepository;
import com.example.repository.UserRepository;
import com.example.request.CategoryRequest;
import com.example.response.CategoryResponse;
import com.example.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        CategoryTable categoryTable = modelMapper.map(categoryRequest, CategoryTable.class);

        try {
            CategoryTable savedTable = categoryRepository.save(categoryTable);
            return modelMapper.map(savedTable, CategoryResponse.class);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = extractErrorMessageFromDataIntegrityViolationException(e);
            throw new RuntimeException(errorMessage, e);
        }
    }

    private String extractErrorMessageFromDataIntegrityViolationException(DataIntegrityViolationException e) {
        if (e.getCause() instanceof ConstraintViolationException) {
            ConstraintViolationException cve = (ConstraintViolationException) e.getCause();
            // Further inspection and specific error message construction
            return "Constraint violation: " + cve.getMessage();
        }
        return "Data integrity violation occurred";
    }


    @Override
    public List<CategoryResponse> getAllCategories(User categoryOwner) {
        List<CategoryTable> categoryTables = categoryRepository.findByCategoryOwner(categoryOwner);
        return categoryTables.stream().map(each -> modelMapper.map(each, CategoryResponse.class)).toList();
    }

    @Override
    public CategoryTable getCategoryById(Long categoryId) {
        return null;
    }

    private CategoryTable getCategoryByIdAndVerifyOwner(Long categoryId, User owner) throws IllegalAccessException {
        CategoryTable categoryTable = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        if (!categoryTable.getCategoryOwner().equals(owner)) {
            throw new IllegalAccessException("You are not authorized to perform this action on this category");
        }

        return categoryTable;
    }


    @Override
    public CategoryTable updateCategory(User owner, Long categoryId, CategoryRequest categoryRequest) throws IllegalAccessException {
        CategoryTable categoryTable = getCategoryByIdAndVerifyOwner(categoryId, owner);
        CategoryTable updatedCategoryTable;
        try {
            categoryTable.setCategoryName(categoryRequest.getCategoryName());
            updatedCategoryTable = categoryRepository.save(categoryTable);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete the category", e);
        }

        return updatedCategoryTable;
    }

    @Override
    public boolean deleteCategory(User owner, Long categoryId) throws IllegalAccessException {
        // Fetch the category by ID
        CategoryTable categoryTable = getCategoryByIdAndVerifyOwner(categoryId, owner);
        // Attempt to delete the category
        try {
            categoryRepository.deleteById(categoryId);
            return true; // Return true if deletion is successful
        } catch (Exception e) {
            // Log the exception and rethrow a custom exception if needed
            throw new RuntimeException("Failed to delete the category", e);
        }
    }
}
