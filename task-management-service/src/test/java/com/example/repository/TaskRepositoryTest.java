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
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = TaskManagementServiceApplication.class)
public class TaskRepositoryTest {

    final TaskRepository taskRepository;

    @Autowired
    public TaskRepositoryTest(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Test
    void saveTaskTest() {
//        User user = User.builder()
//                .email("sandeep@gmail.com")
//                .userId(1L)
//                .username("sandeep")
//                .build();


        Task task = Task.builder()
                .title("test")
                .description("just testing")
                .category(Category.PERSONAL)
                .priority(Priority.HIGH)
                .userId(3L)
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
                .userId(3L)
                .build();

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> taskRepository.save(task));
    }

    @Test
    void getAllTasksTest() {
        List<Task> listOfTasks = taskRepository.findByUserId(1L);
        Assertions.assertTrue(listOfTasks.size() > 0);
    }


}
