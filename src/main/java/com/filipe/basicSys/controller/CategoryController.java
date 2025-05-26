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
//    public ResponseEntity<Page<Category>> getAll(
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String description,
//            @RequestParam(defaultValue = "0") Integer page) {
    public ResponseEntity<Page<Category>> getAll(
            CategoryUpdateDTO dto,
            @RequestParam(defaultValue = "0") Integer page) {

//        Page<Category> categories = categoryService.getAll(name, description, page);
        Page<Category> categories = categoryService.getAll(dto.mapperToCategory(), page);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Category> getById(@PathVariable(name = "codigo") Integer code){
        Category category = this.categoryService.getById(code);
        return ResponseEntity.ok(category);
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

        Category category = this.categoryService.update(dto.mapperToCategory(), code);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<String> delete(@PathVariable(name = "codigo") Integer code) {
        this.categoryService.delete(code);
        return ResponseEntity.noContent().build();

    }
}
