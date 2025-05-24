package com.filipe.basicSys.dto;

import com.filipe.basicSys.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryDTO(
        @Size(min = 3, max = 255, message = "O campo deve conter tamanho minimo de 3 e no máximo 255.")
        @NotBlank(message = "O campo nome esta vazio")
        String name,

        @Size(min = 3, max = 255, message = "O campo deve conter tamanho minimo de 3 e no máximo 255.")
        @NotBlank(message = "O campo description esta vazio.")
        String description
) {

    public Category mapperToCategory() {
        Category category = new Category();
        category.setName(this.name);
        category.setDescription(this.description);

        return category;
    }
}

