package com.filipe.basicSys.service;

import com.filipe.basicSys.model.Customer;
import com.filipe.basicSys.model.Employee;
import com.filipe.basicSys.model.Order;
import com.filipe.basicSys.model.Shipper;
import com.filipe.basicSys.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class OrderService {

    @Value("${spring.page.page-size}")
    private Integer pageSize;
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final ShipperService shipperService;
    private final EntityManager entityManager;


    public Page<Order> getAll(Order order, Integer page) {

        Pageable pageable = PageRequest.of(page, pageSize);
        if (order == null) {
            return this.orderRepository.findAll(pageable);
        }

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Order> example = Example.of(order, matcher);
        return this.orderRepository.findAll(example, pageable);
    }

    public Order getById(Integer code) {
        if (code == null) {
            throw new IllegalArgumentException("Código é nulo.");
        }
        Order order = this.orderRepository.findById(code).orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado."));
        return Order.orderComplete(order);
    }

    public Order save(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Pedido é nulo.");
        }

        order.setDate(LocalDate.now());
        Order newOrder = this.orderRepository.save(order);
        return this.orderRepository.findByIdWithRelations(newOrder.getId());
    }


    @Transactional
    public Order update(Order order, Integer code) {
        if (order == null) {
            throw new IllegalArgumentException("Código é nulo.");
        }

        Order foundOrder = this.orderRepository.findById(code).orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        if (order.getCustomer() != null && order.getCustomer().getId() != null) {
            Customer customer = this.customerService.getReferenceById(order.getCustomer().getId());
            foundOrder.setCustomer(customer);
        }
        if (order.getEmployee() != null && order.getEmployee().getId() != null) {
            Employee employee = this.employeeService.getReferenceById(order.getEmployee().getId());
            foundOrder.setEmployee(employee);
        }
        if (order.getShipper() != null && order.getShipper().getId() != null) {
            Shipper shipper = this.shipperService.getReferenceById(order.getShipper().getId());
            foundOrder.setShipper(shipper);
        }
        return Order.orderComplete(this.orderRepository.save(foundOrder));
    }

    public void delete(Integer code){
        if(code == null){
            throw new IllegalArgumentException("Código inválido.");
        }
        this.orderRepository.deleteById(code);
    }

    public Order getReferenceById(Integer code){
        return this.orderRepository.getReferenceById(code);
    }


    /// PRIVATEE METHODS

    private Order checkParamsUpdate(Order original, Order update) {
        Field[] originalFields = original.getClass().getDeclaredFields();
        Field[] updateFields = update.getClass().getDeclaredFields();

        for (Field fUpdate : updateFields) {
            fUpdate.setAccessible(true);
            try {
                Object value = fUpdate.get(update);
                if (value != null) {
                    for (Field fOriginal : originalFields) {
                        if (fUpdate.getName().equals(fOriginal.getName())) {
                            fOriginal.setAccessible(true);

                            fOriginal.set(original, value);
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return original;
    }
}
