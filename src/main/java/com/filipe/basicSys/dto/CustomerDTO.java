package com.filipe.basicSys.dto;

import com.filipe.basicSys.model.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerDTO(
        @Size(min = 3, max = 255, message = "O campo deve conter tamanho minimo de 3 e no máximo 255.")
        @NotBlank(message = "O campo name esta vazio")
        String name,

        @Size(min = 3, max = 255, message = "O campo deve conter tamanho minimo de 3 e no máximo 255.")
        @NotBlank(message = "O campo contactName esta vazio")
        String contactName,

        @Size(min = 3, max = 255, message = "O campo deve conter tamanho minimo de 3 e no máximo 255.")
        @NotBlank(message = "O campo address esta vazio")
        String address,

        @Size(min = 3, max = 255, message = "O campo deve conter tamanho minimo de 3 e no máximo 255.")
        @NotBlank(message = "O campo city esta vazio")
        String city,

        @Size(min = 3, max = 20, message = "O campo deve conter tamanho minimo de 3 e no máximo 20.")
        @NotBlank(message = "O campo postalCode esta vazio")
        String postalCode,

        @Size(min = 3, max = 50, message = "O campo deve conter tamanho minimo de 3 e no máximo 50.")
        @NotBlank(message = "O campo country esta vazio")
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
