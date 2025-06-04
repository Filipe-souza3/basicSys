package com.filipe.basicSys.controller;

import com.filipe.basicSys.dto.ShipperDTO;
import com.filipe.basicSys.dto.ShipperUpdateDTO;
import com.filipe.basicSys.model.Shipper;
import com.filipe.basicSys.service.ShipperService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipper")
@RequiredArgsConstructor
public class ShipperController {

    private final ShipperService shipperService;

    @GetMapping
    public ResponseEntity<Page<Shipper>> getAll(ShipperUpdateDTO dto, @RequestParam(defaultValue = "0") Integer page) {
        return ResponseEntity.ok(this.shipperService.getAll(dto.mapperToShipper(), page));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Shipper> getById(@PathVariable(name = "codigo") Integer code) {
        return ResponseEntity.ok(this.shipperService.getById(code));
    }

    @PostMapping
    public ResponseEntity<Shipper> save(@Valid @RequestBody ShipperDTO dto) {
        return ResponseEntity.ok(this.shipperService.save(dto.mapperToShipper()));
    }

    @PatchMapping("/{codigo}")
    public ResponseEntity<Shipper> update(
            @RequestBody ShipperUpdateDTO dto,
            @PathVariable(name = "codigo") Integer code){
        return ResponseEntity.ok(this.shipperService.update(dto.mapperToShipper(), code));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> delete (@PathVariable(name = "codigo") Integer code){
        this.shipperService.delete(code);
        return ResponseEntity.noContent().build();
    }
}
