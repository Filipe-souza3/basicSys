package com.filipe.basicSys.repository;

import com.filipe.basicSys.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
