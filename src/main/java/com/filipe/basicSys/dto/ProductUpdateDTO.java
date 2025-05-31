package com.filipe.basicSys.dto;

import com.filipe.basicSys.model.Category;
import com.filipe.basicSys.model.Product;
import com.filipe.basicSys.model.Supplier;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductUpdateDTO(
        @NotBlank(message = "O campo nome esta vazio.")
        @Size(min = 3, max = 255, message = "O campo deve conter tamanho minimo de 3 e no máximo 255.")
        String name,

        @NotNull(message = "O campo supplier esta vazio.")
        Integer supplier,

        @NotNull(message = "O campo category esta vazio.")
        Integer category,

        @NotBlank(message = "O campo unit esta vazio.")
        @Size(min = 3, max = 255, message = "O campo deve conter tamanho minimo de 3 e no máximo 255.")
        String unit,

        @NotNull(message = "O campo price esta vazio.")
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
