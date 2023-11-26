package com.example.repository;

import com.example.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {

    @Query("select r from Reminder r where r.task.id = :taskId")
    List<Reminder> findByTaskId(long taskId);


}
