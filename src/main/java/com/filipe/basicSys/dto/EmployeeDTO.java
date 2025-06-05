package com.filipe.basicSys.dto;

import com.filipe.basicSys.model.Employee;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record EmployeeDTO(
        @Size(min = 3, max = 255, message = "O campo lastName deve conter tamanho minimo de 3 e no máximo 255.")
        @NotBlank(message = "O campo name esta vazio")
        String lastName,

        @Size(min = 3, max = 255, message = "O campo firstName deve conter tamanho minimo de 3 e no máximo 255.")
        @NotBlank(message = "O campo firstName esta vazio")
        String firstName,

        @NotNull(message = "O campo birthday esta vazio")
        @PastOrPresent(message = "Somente datas passadas.")
        LocalDate birthday,

        @Size(min = 3, max = 255, message = "O campo photo deve conter tamanho minimo de 3 e no máximo 255.")
        @NotBlank(message = "O campo photo esta vazio")
        String photo,

        @Size(min = 3, max = 255, message = "O campo notes deve conter tamanho minimo de 3 e no máximo 255.")
        @NotBlank(message = "O campo notes esta vazio")
        String notes
) {

    public Employee mapperToEmployee() {
        Employee employee = new Employee();

        employee.setFirstName(this.firstName);
        employee.setLastName(this.lastName);
        employee.setBirthday(this.birthday);
        employee.setPhoto(this.photo);
        employee.setNotes(this.notes);

        return employee;
    }
}
