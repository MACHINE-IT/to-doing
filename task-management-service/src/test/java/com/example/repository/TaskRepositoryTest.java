package com.example.repository;

import com.example.model.Category;
import com.example.model.Priority;
import com.example.model.Task;
import com.example.model.User;
import com.example.taskmanagementservice.TaskManagementServiceApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ContextConfiguration(classes = TaskManagementServiceApplication.class)
public class TaskRepositoryTest {

    final TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public TaskRepositoryTest(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Test
    void saveTaskTest() {

        Task task = Task.builder()
                .title("cooking")
                .description("I want to cook before 2pm")
                .category(Category.PERSONAL)
                .priority(Priority.HIGH)
                .ownerId(null) // to be changed
                .build();

        Task task1 = taskRepository.save(task);
        Assertions.assertEquals(task.getTitle(), task1.getTitle());
    }

    @Test
    void getTaskTest() {
        Task task = taskRepository.findById(4L).orElse(null);
        System.out.println(task.toString());
        Assertions.assertNotNull(task);
    }

    @Test
    void shouldThrowDataIntegrityViolationException() {
        Task task = Task.builder()
                .title("test")
                .description("just testing")
                .category(Category.PERSONAL)
                .priority(Priority.HIGH)
                .build();

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> taskRepository.save(task));
    }

    @Test
    @Transactional
    void getAllTasksTest() {
        Optional<User> user = userRepository.findById(1L);
        Pageable pageable = PageRequest.of(0, 3, Sort.by("title").ascending());
        Page<Task> listOfTasks = taskRepository.findByOwnerId(user.get(), pageable);
        System.out.println(listOfTasks);
        Assertions.assertTrue(listOfTasks.stream().count() > 0);
    }
}
