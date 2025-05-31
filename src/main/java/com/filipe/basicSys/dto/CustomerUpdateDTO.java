package com.filipe.basicSys.dto;

import com.filipe.basicSys.model.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerUpdateDTO(

        @Size(min = 3, max = 255, message = "O campo deve conter tamanho minimo de 3 e no máximo 255.")
        String name,

        @Size(min = 3, max = 255, message = "O campo deve conter tamanho minimo de 3 e no máximo 255.")
        String contactName,

        @Size(min = 3, max = 255, message = "O campo deve conter tamanho minimo de 3 e no máximo 255.")
        String address,

        @Size(min = 3, max = 255, message = "O campo deve conter tamanho minimo de 3 e no máximo 255.")
        String city,

        @Size(min = 3, max = 20, message = "O campo deve conter tamanho minimo de 3 e no máximo 20.")
        String postalCode,

        @Size(min = 3, max = 50, message = "O campo deve conter tamanho minimo de 3 e no máximo 50.")
        String country
) {

    public Customer mapperToCustomer() {
        Customer customer = new Customer();

        if (this.name != null) {
            customer.setName(this.name);
        }
        if (this.contactName != null) {
            customer.setContactName(this.contactName);
        }
        if (this.address != null) {
            customer.setAddress(this.address);
        }
        if (this.city != null) {
            customer.setCity(this.city);
        }
        if (this.postalCode != null) {
            customer.setPostalCode(this.postalCode);
        }
        if (this.country != null) {
            customer.setCountry(this.country);
        }

        return customer;
    }
}
