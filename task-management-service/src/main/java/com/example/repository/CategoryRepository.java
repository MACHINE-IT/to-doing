package com.example.repository;

import com.example.model.CategoryTable;
import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryTable, Long> {
    List<CategoryTable> findByCategoryOwner(User categoryOwner);
}
