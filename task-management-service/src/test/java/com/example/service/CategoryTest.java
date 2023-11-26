package com.example.service;

import com.example.model.CategoryTable;
import com.example.repository.CategoryRepository;
import com.example.request.CategoryRequest;
import com.example.service.impl.CategoryServiceImpl;
import com.example.taskmanagementservice.TaskManagementServiceApplication;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = TaskManagementServiceApplication.class)
public class CategoryTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    void testCreateCategory() {
        CategoryRequest categoryRequest = CategoryRequest.builder().categoryName("favourites").build();

        CategoryTable categoryTable = new CategoryTable(); // Similarly, set up CategoryTable
        categoryTable.setCategoryName(categoryTable.getCategoryName());

        when(modelMapper.map(ArgumentMatchers.any(CategoryRequest.class), ArgumentMatchers.eq(CategoryTable.class))).thenReturn(categoryTable);

        when(categoryRepository.save(ArgumentMatchers.<CategoryTable>any())).thenReturn(categoryTable);

//        CategoryTable result = categoryService.createCategory(categoryRequest);
//
//        // Then
//        assertNotNull(result);
//        assertEquals("favourites", result.getCategoryName()); // Assert that the properties are mapped correctly
//        verify(modelMapper).map(any(CategoryRequest.class), eq(CategoryTable.class)); // Verify modelMapper is called
//        verify(categoryRepository).save(ArgumentMatchers.any(CategoryTable.class)); // Verify repository save is called

    }
}
