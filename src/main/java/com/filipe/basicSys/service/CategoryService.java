package com.filipe.basicSys.service;

import com.filipe.basicSys.model.Category;
import com.filipe.basicSys.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    @Value("${spring.page.page-size}")
    private Integer pageSize;
    private final CategoryRepository categoryRepository;


    public Page<Category> getAllCategory(String name, String description, Integer page) {
        if (page == null) {
            throw new IllegalArgumentException("Página esta vazia.");
        }

        Pageable pageable = PageRequest.of(page, pageSize);
        Category category = this.checkParamsFindCategory(name, description);
        if (category == null) {
            return this.categoryRepository.findAll(pageable);
        }

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Category> example = Example.of(category, matcher);

        return this.categoryRepository.findAll(example, pageable);

    }

    public void save(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Categoria está nula.");
        }
        this.categoryRepository.save(category);
    }

    @Transactional
    public Category update(Category category, Integer code) {
        if (category == null) {
            throw new IllegalArgumentException("Categoria esta nula.");
        }
        Category originalCategory = this.categoryRepository.findById(code).orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada."));
        this.categoryRepository.save(this.checkParamsUpdate(originalCategory, category));

        return originalCategory;
    }

    public void delete(Integer code){
        this.categoryRepository.deleteById(code);
    }

    /// ////PRIVATE METHODS /////////////

    private Category checkParamsFindCategory(String name, String description) {
        Category category = new Category();
        boolean check = false;
        if (name != null) {
            category.setName(name);
            check = true;
        }
        if (description != null) {
            category.setDescription(description);
            check = true;
        }
        return check ? category : null;
    }

    private Category checkParamsUpdate(Category original, Category update){
        if(update.getName() != null){
            original.setName(update.getName());
        }
        if(update.getDescription() != null){
            original.setDescription(update.getDescription());
        }
        return original;
    }
}
