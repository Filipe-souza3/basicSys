package com.filipe.basicSys.dto;

import com.filipe.basicSys.model.Customer;
import com.filipe.basicSys.model.Employee;
import com.filipe.basicSys.model.Order;
import com.filipe.basicSys.model.Shipper;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record OrderDTO(
        @NotNull(message = "O campo customerId esta vazio.")
        Integer customerId,
        @NotNull(message = "O campo employeee esta vazio.")
        Integer employeeId,
        @NotNull(message = "O campo shipperId esta vazio.")
        Integer shipperId
) {

    public Order mapperToOrder(){
        Order order = new Order();

        Customer customer = new Customer();
        customer.setId(this.customerId);
        order.setCustomer(customer);

        Employee employee = new Employee();
        employee.setId(this.employeeId);
        order.setEmployee(employee);

        Shipper shipper = new Shipper();
        shipper.setId(this.shipperId);
        order.setShipper(shipper);

        return order;
    }
}
