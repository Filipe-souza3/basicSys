package com.filipe.basicSys.controller;

import com.filipe.basicSys.dto.OrderDetailsBasicDTO;
import com.filipe.basicSys.dto.OrderDetailsUpdateDTO;
import com.filipe.basicSys.model.OrderDetails;
import com.filipe.basicSys.service.OrderDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orderdetails")
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;

    @GetMapping
    public ResponseEntity<Page<OrderDetails>> getAll(@RequestParam(defaultValue = "") String nameCustomer, @RequestParam(defaultValue = "0") Integer page) {
        return ResponseEntity.ok(this.orderDetailsService.getAll(nameCustomer, page));
    }

    @GetMapping("/basic")
    public ResponseEntity<Page<OrderDetailsBasicDTO>> getAllBasic(@Valid OrderDetailsUpdateDTO dto, @RequestParam(defaultValue = "0") Integer page) {
        Page<OrderDetails> ordersDetails = this.orderDetailsService.getAllBasic(dto.mapperToOrderDetails(), page);
        Page<OrderDetailsBasicDTO> b = ordersDetails.map(o -> {
            return new OrderDetailsBasicDTO(o.getId(), o.getOrder().getId(), o.getProduct().getId(), o.getQuntity());
        });
        return ResponseEntity.ok(b);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<OrderDetails> getById(@PathVariable(name = "codigo") Integer code) {
        OrderDetails orderDetails = this.orderDetailsService.getById(code);
        return ResponseEntity.ok(orderDetails);
    }

    @PostMapping
    public ResponseEntity<OrderDetailsBasicDTO> save(@Valid @RequestBody OrderDetailsUpdateDTO dto) {
        OrderDetails orderDetails = this.orderDetailsService.save(dto.mapperToOrderDetails());
        return ResponseEntity.ok(OrderDetailsBasicDTO.mapperOrderDetailsToBasic(orderDetails));
    }

    @PatchMapping("/{codigo}")
    public ResponseEntity<OrderDetailsBasicDTO> update(@Valid @RequestBody OrderDetailsUpdateDTO dto, @PathVariable(name = "codigo") Integer code) {
        OrderDetails orderDetails = this.orderDetailsService.update(dto.mapperToOrderDetails(), code);
        return ResponseEntity.ok(OrderDetailsBasicDTO.mapperOrderDetailsToBasic(orderDetails));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> delete(@PathVariable(name = "codigo") Integer code){
        this.orderDetailsService.delete(code);
        return ResponseEntity.noContent().build();
    }
}
