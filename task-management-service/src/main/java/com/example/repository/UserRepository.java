package com.example.repository;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     boolean existsByEmail(String email);
     boolean existsByUsername(String username);

     Optional<User> findUserByUsername(String username);
     Optional<User> findUserByEmail(String email);
}
