package com.filipe.basicSys.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "contactname", length = 255, nullable = false)
    private String contactName;

    @Column(name = "address", length = 255, nullable = false)
    private String address;

    @Column(name = "city", length = 255, nullable = false)
    private String city;

    @Column(name = "postalcode", length = 20, nullable = false)
    private String postalCode;

    @Column(name = "country", length = 50, nullable = false)
    private String country;
}
