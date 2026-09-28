package com.priya.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.priya.dto.CategoryResponse;
import com.priya.exception.ForbiddenException;
import com.priya.exception.ResourceNotFoundException;
import com.priya.model.Category;
import com.priya.model.User;
import com.priya.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Get all categories for the logged-in user
    public List<CategoryResponse> getUserCategories(User user) {

        return categoryRepository
                .findByUserId(user.getId())
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // Create a category
    public CategoryResponse createCategory(
            String name,
            String type,
            User user) {

        Category category = new Category();

        category.setName(name);
        category.setType(type.toUpperCase());
        category.setUser(user);

        Category savedCategory =
                categoryRepository.save(category);

        return toResponse(savedCategory);
    }

    // Delete a category
    public void deleteCategory(
            int id,
            User user) {

        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found"));

        // Make sure the logged-in user owns this category
        if (category.getUser().getId() != user.getId()) {
            throw new ForbiddenException(
                    "Not authorised to delete this category");
        }

        categoryRepository.deleteById(id);
    }

    // Convert Entity to DTO
    private CategoryResponse toResponse(
            Category category) {

        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getType()
        );
    }
}