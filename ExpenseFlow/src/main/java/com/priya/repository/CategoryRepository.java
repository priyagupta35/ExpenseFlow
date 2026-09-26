package com.priya.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.priya.model.Category;

@Repository
public interface CategoryRepository
        extends JpaRepository<Category, Integer> {
    List<Category> findByUserId(int userId);
    List<Category> findByUserIdAndType(int userId, String type);
}
