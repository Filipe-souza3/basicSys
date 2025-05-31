package com.filipe.basicSys.dto;

import com.filipe.basicSys.model.Category;
import com.filipe.basicSys.model.Supplier;

import java.math.BigDecimal;

public record ProductBasicDTO(
        Integer id,
        String name,
        Integer supplier,
        Integer category,
        String unit,
        BigDecimal price
) {
}
