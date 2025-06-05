package com.filipe.basicSys.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "lastname", length = 255)
    private String lastName;
    @Column(name = "firstname", length = 255)
    private String firstName;
    @Column(name = "birthdate")
    private LocalDate birthday;
    @Column(name = "photo", length = 255)
    private String photo;
    @Column(name = "notes")
    private String notes;
}
