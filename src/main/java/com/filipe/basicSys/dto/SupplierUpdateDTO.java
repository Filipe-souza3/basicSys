package com.filipe.basicSys.dto;

import com.filipe.basicSys.model.Supplier;
import jakarta.validation.constraints.Size;

public record SupplierUpdateDTO(
        @Size(min = 8, max = 255, message = "O campo deve conter tamanho minimo de 8 e no máximo 255.")
        String name,

        @Size(min = 8, max = 255, message = "O campo deve conter tamanho minimo de 8 e no máximo 255.")
        String contactName,

        @Size(min = 4, max = 255, message = "O campo deve conter tamanho minimo de 4 e no máximo 255.")
        String address,

        @Size(min = 8, max = 255, message = "O campo deve conter tamanho minimo de 8 e no máximo 255.")
        String city,

        @Size(min = 3, max = 255, message = "O campo deve conter tamanho minimo de 3 e no máximo 255.")
        String postalCode,

        @Size(min = 3, max = 255, message = "O campo deve conter tamanho minimo de 3 e no máximo 255.")
        String country,

        @Size(min = 12, max = 255, message = "O campo deve conter tamanho minimo de 12 e no máximo 255.")
        String phone
) {
    public Supplier mapperToCategory() {
        Supplier supplier = new Supplier();
        if (this.name != null) {
            supplier.setName(this.name);
        }
        if (this.contactName != null) {
            supplier.setContactName(this.contactName);
        }
        if (this.address != null) {
            supplier.setAddress(this.address);
        }
        if (this.city != null) {
            supplier.setCity(this.city);
        }
        if (this.postalCode != null) {
            supplier.setPostalCode(this.postalCode);
        }
        if (this.country != null) {
            supplier.setCountry(this.country);
        }
        if (this.phone != null) {
            supplier.setPhone(this.phone);
        }
        return supplier;
    }
}
