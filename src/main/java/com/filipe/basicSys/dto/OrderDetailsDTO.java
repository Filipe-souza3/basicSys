package com.filipe.basicSys.dto;

import com.filipe.basicSys.model.Order;
import com.filipe.basicSys.model.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderDetailsDTO(
        @NotBlank(message = "Order esta vazio.")
        Order order,
        @NotBlank(message = "product esta vazio.")
        Product product,
        @NotNull(message = "Order esta vazio.")
        @Min(value = 1, message = "A quantidade deve ser maior que zero.")
        Integer quantity
) {
}
