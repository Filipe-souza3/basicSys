package com.filipe.basicSys.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;


@Entity
@Table(name = "orderdetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderid")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productid")
    private Product product;

    @Column(name = "quantity")
    private Integer quntity;

    public static OrderDetails orderDetailsComplete(OrderDetails o) {
        Hibernate.initialize(o.getOrder());
        return new OrderDetails(
                o.getId(),
//                new Order(
//                        o.getId(),
//                        o.getOrder().getCustomer(),
//                        o.getOrder().getEmployee(),
//                        o.getOrder().getDate(),
//                        o.getOrder().getShipper()
//                )
                o.getOrder(),
                o.getProduct(),
                o.getQuntity()
        );
    }

//    public static OrderDetails orderDetailsToOrderDetailsBasicDTO(){
//        return new
//    }
}

