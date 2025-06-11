package com.filipe.basicSys.model;

import com.filipe.basicSys.dto.OrderBasicDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerid")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeid")
    private Employee employee;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipperid")
    private Shipper shipper;

    public static Order orderComplete(Order o){
        return new Order(
                o.getId(),
                new Customer(
                    o.getCustomer().getId(),
                    o.getCustomer().getName(),
                    o.getCustomer().getContactName(),
                    o.getCustomer().getAddress(),
                    o.getCustomer().getCity(),
                    o.getCustomer().getPostalCode(),
                    o.getCustomer().getCountry()
                ),
                new Employee(
                     o.getEmployee().getId(),
                     o.getEmployee().getLastName(),
                     o.getEmployee().getFirstName(),
                     o.getEmployee().getBirthday(),
                     o.getEmployee().getPhoto(),
                     o.getEmployee().getNotes()
                ),
                o.getDate(),
                new Shipper(
                       o.getShipper().getId(),
                       o.getShipper().getName(),
                       o.getShipper().getPhone()
                )
        );
    }

    public static OrderBasicDTO orderToOrderBasicDTO(Order o){
        return new OrderBasicDTO(
                o.getId(),
                o.getCustomer().getId(),
                o.getEmployee().getId(),
                o.getDate(),
                o.getShipper().getId()
        );
    }
}
