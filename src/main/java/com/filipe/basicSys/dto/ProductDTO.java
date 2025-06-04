package com.filipe.basicSys.dto;

import com.filipe.basicSys.model.Category;
import com.filipe.basicSys.model.Product;
import com.filipe.basicSys.model.Supplier;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductDTO(
        @NotBlank(message = "O campo name esta vazio.")
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

    public Product mapperToProduct(){
        Product product = new Product();

        product.setName(this.name);

        Supplier supplier1 = new Supplier();
        supplier1.setId(this.supplier);
        product.setSupplier(supplier1);

        Category category1 = new Category();
        category1.setId(this.category);
        product.setCategory(category1);

        product.setUnit(this.unit);
        product.setPrice(this.price);

        return product;
    }
}
