package com.filipe.basicSys.dto;

import com.filipe.basicSys.model.Category;
import jakarta.validation.constraints.Size;

public record CategoryUpdateDTO(
        @Size(min = 3, max = 255, message = "O campo deve conter tamanho minimo de 3 e no máximo 255.")
        String name,
        @Size(min = 3, max = 255, message = "O campo deve conter tamanho minimo de 3 e no máximo 255.")
        String description
) {

    public Category mapperToCategory() {
        Category category = new Category();
        if (this.name != null) {
            category.setName(this.name);
        }
        if (this.description != null) {
            category.setDescription(this.description);
        }

        return category;
    }
}
