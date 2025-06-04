package com.filipe.basicSys.controller;

import com.filipe.basicSys.dto.CustomerDTO;
import com.filipe.basicSys.dto.CustomerUpdateDTO;
import com.filipe.basicSys.model.Customer;
import com.filipe.basicSys.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<Page<Customer>> getAll(CustomerUpdateDTO dto, @RequestParam(defaultValue = "0") Integer page) {
        Page<Customer> customers = this.customerService.getAll(dto.mapperToCustomer(), page);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Customer> getById(@PathVariable(name = "codigo") Integer code) {
        return ResponseEntity.ok(this.customerService.getById(code));
    }

    @PostMapping
    public ResponseEntity<Customer> save(@Valid @RequestBody CustomerDTO dto) {
        return ResponseEntity.ok(this.customerService.save(dto.mapperToCustomer()));
    }

    @PatchMapping("/{codigo}")
    public ResponseEntity<Customer> update(
            @Valid @RequestBody CustomerUpdateDTO dto,
            @PathVariable(name = "codigo") Integer code) {
        return ResponseEntity.ok(this.customerService.update(dto.mapperToCustomer(), code));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> delete(@PathVariable(name = "codigo") Integer code) {
        this.customerService.delete(code);
        return ResponseEntity.noContent().build();
    }
}
