package com.example.repository;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     boolean existsByEmail(String email);
     boolean existsByUsername(String username);

     Optional<User> findUserByUsername(String username);
     Optional<User> findUserByEmail(String email);
     @Modifying
     @Transactional
     @Query(nativeQuery = true, value = "DELETE FROM shared_tasks " +
             "WHERE user_id IN (" +
             "    SELECT u.user_id " +
             "    FROM user u " +
             "    WHERE u.user_id IN :userIds " +
             "    AND EXISTS (" +
             "        SELECT 1 " +
             "        FROM shared_tasks st " +
             "        WHERE st.task_id = :taskId " +
             "        AND u.user_id = st.user_id" +
             "    )" +
             ")")
     void deleteSharedTasksForUsers(@Param("userIds") List<Long> userIds, @Param("taskId") Long taskId);
}
