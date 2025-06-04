package com.filipe.basicSys.dto;

import com.filipe.basicSys.model.Shipper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ShipperDTO(
        @NotBlank(message = "O campo name esta vazio.")
        @Size(min = 5, max = 255, message = "O campo nome phone deve conter tamanho minimo de 5 e no máximo 255.")
        String name,

        @NotBlank(message = "O campo phone esta vazio.")
        @Size(min = 10, max = 50, message = "O campo deve conter tamanho minimo de 10 e no máximo 50.")
        String phone
) {

    public Shipper mapperToShipper() {
        Shipper shipper = new Shipper();
        shipper.setName(this.name);
        shipper.setPhone(this.phone);
        return shipper;
    }
}
