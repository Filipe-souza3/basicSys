package com.filipe.basicSys.model;

import jakarta.persistence.*;
import lombok.Data;

//fazer auditoria
@Entity
@Table(name = "categories")
@Data
public class Category {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "description", length = 255, nullable = true)
    private String description;
}
