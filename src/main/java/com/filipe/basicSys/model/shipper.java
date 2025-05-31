package com.filipe.basicSys.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shippers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class shipper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name", length = 255)
    private String name;
    @Column(name = "phone", length = 50)
    private String phone;
}
