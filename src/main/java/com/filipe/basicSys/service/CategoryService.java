package com.filipe.basicSys.service;

import com.filipe.basicSys.model.Category;
import com.filipe.basicSys.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    @Value("${spring.page.page-size}")
    private Integer pageSize;
    private final CategoryRepository categoryRepository;

    public Page<Category> getAll(Category category, Integer page) {

        Pageable pageable = PageRequest.of(page, this.pageSize);
        if (category == null) {
            return this.categoryRepository.findAll(pageable);
        }

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Category> example = Example.of(category, matcher);

        return this.categoryRepository.findAll(example, pageable);

    }

    public Category getById(Integer code) {
        if (code == null) {
            throw new IllegalArgumentException("Código inválido.");
        }

        Optional<Category> category = this.categoryRepository.findById(code);
        if (category.isEmpty()) {
            throw new IllegalArgumentException("Não encontrado.");
        }

        return category.get();
    }

    public Category save(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Categoria está nula.");
        }
        return this.categoryRepository.save(category);
    }

    @Transactional
    public Category update(Category category, Integer code) {
        if (code == null) {
            throw new IllegalArgumentException("Código inválido.");
        }
        Category originalCategory = this.categoryRepository.findById(code).orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada."));
        return this.categoryRepository.save(this.checkParamsUpdate(originalCategory, category));
    }

    public void delete(Integer code) {
        if (code == null) {
            throw new IllegalArgumentException("Código inválido.");
        }
        this.categoryRepository.deleteById(code);
    }

    /// ////PRIVATE METHODS /////////////

    private Category checkParamsUpdate(Category original, Category update)  {
        Field[] fieldOriginal = original.getClass().getDeclaredFields();
        Field[] fieldUpdate = update.getClass().getDeclaredFields();

        for(Field FUpdate : fieldUpdate){
            FUpdate.setAccessible(true);

            try{
                Object value = FUpdate.get(update);
                if(value != null){
                    for(Field FOriginal : fieldOriginal){
                        FOriginal.setAccessible(true);

                        if(FUpdate.getName().equals(FOriginal.getName())){
                            FOriginal.set(original, value);
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return original;
    }
}
