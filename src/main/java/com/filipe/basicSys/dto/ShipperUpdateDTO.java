package com.filipe.basicSys.dto;

import com.filipe.basicSys.model.Shipper;
import jakarta.validation.constraints.Size;

public record ShipperUpdateDTO(
        @Size(min = 5, max = 255, message = "O campo deve conter tamanho minimo de 5 e no máximo 255.")
        String name,

        @Size(min = 10, max = 50, message = "O campo deve conter tamanho minimo de 10 e no máximo 50.")
        String phone
) {
    public Shipper mapperToShipper() {
        Shipper shipper = new Shipper();
        if (this.name != null) {
            shipper.setName(this.name);
        }
        if (this.phone != null) {
            shipper.setPhone(this.phone);
        }
        return shipper;
    }
}
