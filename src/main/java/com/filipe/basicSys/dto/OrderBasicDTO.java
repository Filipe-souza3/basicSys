package com.filipe.basicSys.dto;

import java.time.LocalDate;

public record OrderBasicDTO(
        Integer id,
        Integer customerId,
        Integer employeeId,
        LocalDate date,
        Integer shipperId
) {
}
