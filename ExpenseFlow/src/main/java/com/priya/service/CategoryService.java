package com.priya.service;
// package com.priya.expensetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.priya.model.Category;
import com.priya.model.User;
import com.priya.repository.CategoryRepository;


@Service
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getUserCategories(User user) {
        return categoryRepository.findByUserId(user.getId());
    }

    public Category createCategory(String name,
            String type, User user) {
        Category category = new Category();
        category.setName(name);
        category.setType(type.toUpperCase());
        category.setUser(user);
        return categoryRepository.save(category);
    }

    public void deleteCategory(int id, User user) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException(
                "Category not found"));
        if (category.getUser().getId() != user.getId()) {
            throw new RuntimeException(
                "Not authorised to delete this category");
        }
        categoryRepository.deleteById(id);
    }
}