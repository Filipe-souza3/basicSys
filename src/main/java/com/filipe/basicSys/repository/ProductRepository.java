package com.filipe.basicSys.repository;

import com.filipe.basicSys.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
