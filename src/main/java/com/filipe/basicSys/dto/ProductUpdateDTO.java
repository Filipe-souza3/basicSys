package com.filipe.basicSys.dto;

import com.filipe.basicSys.model.Category;
import com.filipe.basicSys.model.Product;
import com.filipe.basicSys.model.Supplier;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductUpdateDTO(
        @Size(min = 3, max = 255, message = "O campo deve conter tamanho minimo de 3 e no máximo 255.")
        String name,

        Integer supplier,
        Integer category,

        @Size(min = 3, max = 255, message = "O campo deve conter tamanho minimo de 3 e no máximo 255.")
        String unit,

        @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
        @Digits(integer = 10, fraction = 2, message = "O preço deve ter no máximo 10 dígitos inteiros e 2 decimais")
        BigDecimal price
) {

    //depois substitur por field todos mapper para uma funçao field
    public Product mapperToProduct() {
        Product product = new Product();
        if (this.name != null) {
            product.setName(this.name);
        }
        if (this.supplier != null) {
            Supplier supplier1 = new Supplier();
            supplier1.setId(this.supplier);
            product.setSupplier(supplier1);
        }

        if (this.category != null) {
            Category category1 = new Category();
            category1.setId(this.category);
            product.setCategory(category1);
        }
        if (this.unit != null) {
            product.setUnit(this.unit);
        }

        if (this.price != null) {
            product.setPrice(this.price);
        }

        return product;
    }
}
