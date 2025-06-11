package com.filipe.basicSys.model;

import com.filipe.basicSys.dto.ProductBasicDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 255)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplierid")
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryid")
    private Category category;

    @Column(name = "unit", length = 255)
    private String unit;

    @Column(name = "price", precision = 12, scale = 2)
    private BigDecimal price;

    public static Product productComplete(Product p) {
        return new Product(
                p.getId(),
                p.getName(),
                new Supplier(
                        p.getSupplier().getId(),
                        p.getSupplier().getName(),
                        p.getSupplier().getContactName(),
                        p.getSupplier().getAddress(),
                        p.getSupplier().getCity(),
                        p.getSupplier().getPostalCode(),
                        p.getSupplier().getCountry(),
                        p.getSupplier().getPhone()
                ),
                new Category(
                        p.getCategory().getId(),
                        p.getCategory().getName(),
                        p.getCategory().getDescription()
                ),
                p.getUnit(),
                p.getPrice());
    }

    public static ProductBasicDTO productToProductBasicDTO(Product p){
        return new ProductBasicDTO(
                p.getId(),
                p.getName(),
                p.getSupplier().getId(),
                p.getCategory().getId(),
                p.getUnit(),
                p.getPrice()
        );
    }
}
