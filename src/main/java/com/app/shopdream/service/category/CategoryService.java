package com.app.shopdream.service.category;

import com.app.shopdream.constant.Constants;
import com.app.shopdream.exception.AlreadyExistsException;
import com.app.shopdream.exception.ResourceNotFoundException;
import com.app.shopdream.model.Category;
import com.app.shopdream.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.CATEGORY_NOT_FOUND));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return Optional.ofNullable(category)
                .filter(c -> !categoryRepository.existsByName(category.getName()))
                .map(categoryRepository::save)
                .orElseThrow(() -> {
                    Objects.requireNonNull(category, Constants.CATEGORY_NOT_FOUND);
                    return new AlreadyExistsException(category.getName().concat(" already exists!"));
                });
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        return Optional.ofNullable(getCategoryById(categoryId))
                .map(oldCategory -> {
                    oldCategory.setName(category.getName());
                    return categoryRepository.save(oldCategory);
                })
                .orElseThrow(() -> new ResourceNotFoundException(Constants.CATEGORY_NOT_FOUND));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id)
                .ifPresentOrElse(categoryRepository::delete, () -> {
                    throw new ResourceNotFoundException(Constants.CATEGORY_NOT_FOUND);
                });
    }
}
