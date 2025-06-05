package com.filipe.basicSys.dto;

import com.filipe.basicSys.model.Employee;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record EmployeeUpdateDTO(
        @Size(min = 3, max = 255, message = "O campo lastName deve conter tamanho minimo de 3 e no máximo 255.")
        String lastName,

        @Size(min = 3, max = 255, message = "O campo firstName deve conter tamanho minimo de 3 e no máximo 255.")
        String firstName,

        @PastOrPresent(message = "Somente datas passadas.")
        LocalDate birthday,

        @Size(min = 3, max = 255, message = "O campo photo deve conter tamanho minimo de 3 e no máximo 255.")
        String photo,

        @Size(min = 3, max = 255, message = "O campo notes deve conter tamanho minimo de 3 e no máximo 255.")
        String notes
) {

    public Employee mapperToEmployee(){
        Employee employee = new Employee();

        if(this.firstName != null){
            employee.setFirstName(this.firstName);
        }
        if(this.lastName != null){
            employee.setLastName(this.lastName);
        }
        if(this.birthday != null){
            employee.setBirthday(this.birthday);
        }
        if(this.photo != null){
            employee.setPhoto(this.photo);
        }
        if(this.notes != null){
            employee.setNotes(this.notes);
        }
        return employee;
    }
}
