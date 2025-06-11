package com.filipe.basicSys.repository;

import com.filipe.basicSys.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select p from Product p " +
            "left join fetch p.supplier " +
            "left join fetch p.category " +
            "where p.id = :id")
    Product findByIdWithRelations(@Param("id") Integer id);
}
