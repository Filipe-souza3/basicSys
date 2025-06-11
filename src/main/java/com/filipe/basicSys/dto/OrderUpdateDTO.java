package com.filipe.basicSys.dto;

import com.filipe.basicSys.model.Customer;
import com.filipe.basicSys.model.Employee;
import com.filipe.basicSys.model.Order;
import com.filipe.basicSys.model.Shipper;

import java.time.LocalDate;

public record OrderUpdateDTO(
        //depois criar anotaçao para deixar ser nulo mas nao ser zero
        Integer customerId,
        Integer employeeId,
        LocalDate date,
        Integer shipperId
) {

    public Order mapperToOrder(){
        Order order = new Order();

        if(this.customerId != null){
            Customer customer = new Customer();
            customer.setId(this.customerId);
            order.setCustomer(customer);
        }
        if(this.employeeId != null){
            Employee employee= new Employee();
            employee.setId(this.employeeId);
            order.setEmployee(employee);
        }
        if(this.date != null){
            order.setDate(this.date);
        }
        if(this.shipperId != null){
            Shipper shipper = new Shipper();
            shipper.setId(this.shipperId);
            order.setShipper(shipper);
        }
        return order;
    }
}
