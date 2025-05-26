package com.filipe.basicSys.controller;


import com.filipe.basicSys.dto.SupplierDTO;
import com.filipe.basicSys.dto.SupplierUpdateDTO;
import com.filipe.basicSys.model.Supplier;
import com.filipe.basicSys.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/supplier")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping
    public Page<Supplier> getAll(
            SupplierUpdateDTO dto,
            @RequestParam(defaultValue = "0") Integer page) {

        return this.supplierService.getAll(dto.mapperToCategory(), page);
    }

    @GetMapping("/{codigo}")
    public Supplier getById(
            @PathVariable(name = "codigo") Integer code){
        return this.supplierService.getById(code);
    }

    @PostMapping
    public ResponseEntity<Supplier> save(@Valid @RequestBody SupplierDTO dto) {

        Supplier supplier = dto.mapperToCategory();
        this.supplierService.save(supplier);
        return ResponseEntity.ok(supplier);
    }

    @PatchMapping("/{codigo}")
    public ResponseEntity<Supplier> update(
            @Valid @RequestBody SupplierUpdateDTO dto,
            @PathVariable(name = "codigo") Integer code) {

        Supplier supplier = this.supplierService.update(dto.mapperToCategory(), code);
        return ResponseEntity.ok(supplier);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<String> delete(
            @PathVariable(name = "codigo") Integer code) {
        this.supplierService.delete(code);
        return ResponseEntity.noContent().build();
    }
}
