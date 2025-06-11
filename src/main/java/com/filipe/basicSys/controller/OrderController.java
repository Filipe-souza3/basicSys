package com.filipe.basicSys.controller;

import com.filipe.basicSys.dto.OrderBasicDTO;
import com.filipe.basicSys.dto.OrderDTO;
import com.filipe.basicSys.dto.OrderUpdateDTO;
import com.filipe.basicSys.model.Order;
import com.filipe.basicSys.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<Page<Order>> getAll(@Valid OrderUpdateDTO dto, @RequestParam(defaultValue = "0") Integer page){
        Page<Order> orders = this.orderService.getAll(dto.mapperToOrder(), page);
        Page<Order> ordersComplete = orders.map(Order::orderComplete);
        return ResponseEntity.ok(ordersComplete);
    }

    @GetMapping("/basic")
    public ResponseEntity<Page<OrderBasicDTO>> getAllBasic(@Valid OrderUpdateDTO dto, @RequestParam(defaultValue = "0") Integer page){
        Page<Order> orders = this.orderService.getAll(dto.mapperToOrder(), page);
        Page<OrderBasicDTO> ordersComplete = orders.map(Order::orderToOrderBasicDTO);
        return ResponseEntity.ok(ordersComplete);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Order> getById(@PathVariable(name = "codigo") Integer code){
        return ResponseEntity.ok(this.orderService.getById(code));
    }

    @PostMapping
    public ResponseEntity<Order> save(@Valid @RequestBody OrderDTO dto){
        Order order = this.orderService.save(dto.mapperToOrder());
        return ResponseEntity.ok(Order.orderComplete(order));
    }

    @PatchMapping("/{codigo}")
    public ResponseEntity<Order> update (@Valid @RequestBody OrderUpdateDTO dto, @PathVariable(name = "codigo") Integer code){
        Order order = this.orderService.update(dto.mapperToOrder(), code);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> delete(@PathVariable(name = "codigo") Integer code){
        this.orderService.delete(code);
        return ResponseEntity.noContent().build();
    }

}
