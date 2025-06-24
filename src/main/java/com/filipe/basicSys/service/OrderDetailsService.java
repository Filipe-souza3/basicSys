package com.filipe.basicSys.service;

import com.filipe.basicSys.dto.OrderDetailsBasicDTO;
import com.filipe.basicSys.dto.OrderDetailsDTO;
import com.filipe.basicSys.model.Order;
import com.filipe.basicSys.model.OrderDetails;
import com.filipe.basicSys.model.Product;
import com.filipe.basicSys.repository.OrderDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
//specification

@RequiredArgsConstructor
@Service
public class OrderDetailsService {

    @Value("${spring.page.page-size}")
    private Integer pageSize;
    @Autowired
    private final OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private final ProductService productService;
    @Autowired
    private final OrderService orderService;

    public Page<OrderDetails> getAll(String nomeCustomer, Integer page) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.orderDetailsRepository.findAllOrders(nomeCustomer, pageable);
    }

    public Page<OrderDetails> getAllBasic(OrderDetails orderDetails, Integer page) {
        Pageable pageable = PageRequest.of(page, pageSize);
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<OrderDetails> example = Example.of(orderDetails, matcher);
        return this.orderDetailsRepository.findAll(example, pageable);
    }

    public OrderDetails getById(Integer code) {
        if (code == null) {
            throw new IllegalArgumentException("Código é nulo.");
        }
        return this.orderDetailsRepository.findCompleteById(code).orElseThrow(() -> new IllegalArgumentException("Não encontrada"));
    }

    public OrderDetails save(OrderDetails orderDetails) {
        if (orderDetails == null) {
            throw new IllegalArgumentException("OrderDetails esta nula.");
        }
        return this.orderDetailsRepository.save(orderDetails);
    }


    public OrderDetails update(OrderDetails orderDetails, Integer code) {
        if (orderDetails == null) {
            throw new IllegalArgumentException("OrderDetails esta nulo.");
        }
        if (code == null) {
            throw new IllegalArgumentException("Codigo esta nulo.");
        }

        OrderDetails original =this.orderDetailsRepository.findById(code).orElseThrow();
        if (orderDetails.getOrder() != null && orderDetails.getOrder().getId() != null) {
            Order order = this.orderService.getReferenceById(orderDetails.getOrder().getId());
            original.setOrder(order);
        }
        if (orderDetails.getProduct() != null && orderDetails.getProduct().getId() != null) {
            Product product = this.productService.getReferenceById(orderDetails.getProduct().getId());
            original.setProduct(product);
        }
        if (orderDetails.getQuntity() != null) {
            original.setQuntity(orderDetails.getQuntity());
        }
        return this.orderDetailsRepository.save(original);
    }

    public void delete(Integer code){
        if(code == null){
            throw new IllegalArgumentException("Código é nulo.");
        }
        this.orderDetailsRepository.deleteById(code);
    }
}
