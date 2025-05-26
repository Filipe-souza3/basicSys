package com.filipe.basicSys.dto;

import com.filipe.basicSys.model.Supplier;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SupplierDTO(
        @NotBlank(message = "O campo nome esta vazio.")
        @Size(min = 8, max = 255, message = "O campo deve conter tamanho minimo de 8 e no máximo 255.")
        String name,

        @NotBlank(message = "O campo nome esta vazio.")
        @Size(min = 8, max = 255, message = "O campo deve conter tamanho minimo de 8 e no máximo 255.")
        String contactName,

        @NotBlank(message = "O campo nome esta vazio.")
        @Size(min = 4, max = 255, message = "O campo deve conter tamanho minimo de 4 e no máximo 255.")
        String address,

        @NotBlank(message = "O campo nome esta vazio.")
        @Size(min = 8, max = 255, message = "O campo deve conter tamanho minimo de 8 e no máximo 255.")
        String city,

        @NotBlank(message = "O campo nome esta vazio.")
        @Size(min = 3, max = 255, message = "O campo deve conter tamanho minimo de 3 e no máximo 255.")
        String postalCode,

        @NotBlank(message = "O campo nome esta vazio.")
        @Size(min = 3, max = 255, message = "O campo deve conter tamanho minimo de 3 e no máximo 255.")
        String country,

        @NotBlank(message = "O campo nome esta vazio.")
        @Size(min = 12, max = 255, message = "O campo deve conter tamanho minimo de 12 e no máximo 255.")
        String phone
) {

    public Supplier mapperToCategory() {
        Supplier supplier = new Supplier();
        supplier.setName(this.name);
        supplier.setContactName(this.contactName);
        supplier.setAddress(this.address);
        supplier.setCity(this.city);
        supplier.setPostalCode(this.postalCode);
        supplier.setCountry(this.country);
        supplier.setPhone(this.phone);
        return supplier;
    }
}
