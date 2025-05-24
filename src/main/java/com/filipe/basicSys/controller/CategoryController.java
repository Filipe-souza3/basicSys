package com.filipe.basicSys.controller;

import com.filipe.basicSys.dto.CategoryDTO;
import com.filipe.basicSys.dto.CategoryUpdateDTO;
import com.filipe.basicSys.model.Category;
import com.filipe.basicSys.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<Category>> getAllCategory(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(defaultValue = "0") Integer page) {

        Page<Category> categories = categoryService.getAllCategory(name, description, page);
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<Category> save(@Valid @RequestBody CategoryDTO dto) {

        Category category = dto.mapperToCategory();
        this.categoryService.save(category);
        return ResponseEntity.ok(category);
    }

    @PatchMapping("/{codigo}")
    public ResponseEntity<Category> update(
            @Valid @RequestBody CategoryUpdateDTO dto,
            @PathVariable(name = "codigo") Integer code) {

        Category category = dto.mapperToCategory();
        category = this.categoryService.update(category, code);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<String> delete(@PathVariable(name = "codigo") Integer code) {
        this.categoryService.delete(code);
        return ResponseEntity.noContent().build();

    }
}
