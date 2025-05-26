package com.filipe.basicSys.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Suppliers")
@Data
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "contactname", length = 255)
    private String contactName;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "city", length = 255)
    private String city;

    @Column(name = "postalcode", length = 255)
    private String postalCode;

    @Column(name = "country", length = 255)
    private String country;

    @Column(name = "phone", length = 255)
    private String phone;
}
