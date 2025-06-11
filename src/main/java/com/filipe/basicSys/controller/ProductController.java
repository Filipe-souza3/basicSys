package com.filipe.basicSys.controller;

import com.filipe.basicSys.dto.ProductBasicDTO;
import com.filipe.basicSys.dto.ProductDTO;
import com.filipe.basicSys.dto.ProductUpdateDTO;
import com.filipe.basicSys.model.Product;
import com.filipe.basicSys.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> getAll(
            @Valid ProductUpdateDTO dto,
            @RequestParam(defaultValue = "0") Integer page) {

            Page<Product> products = this.productService.getAll(dto.mapperToProduct(), page);
            Page<Product> productsComplete = products.map(Product::productComplete);
            return ResponseEntity.ok(productsComplete);
    }

    @GetMapping("/basic")
    public ResponseEntity<Page<ProductBasicDTO>> getAllBasic(
            @Valid ProductUpdateDTO dto,
            @RequestParam(defaultValue = "0") Integer page) {
        Page<Product> products = this.productService.getAll(dto.mapperToProduct(), page);
        Page<ProductBasicDTO> basicProducts = products.map(Product::productToProductBasicDTO);
        return ResponseEntity.ok(basicProducts);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Product> getById(@PathVariable(name = "codigo") Integer code){
        return ResponseEntity.ok(this.productService.getById(code));
    }

    @PostMapping
    public ResponseEntity<Product> save(@Valid @RequestBody ProductDTO dto) {
        return ResponseEntity.ok(this.productService.save(dto.mapperToProduct()));
    }

    @PatchMapping("/{codigo}")
    public ResponseEntity<ProductBasicDTO> update(
            @Valid @RequestBody ProductUpdateDTO dto,
            @PathVariable(name = "codigo") Integer code) {
        Product product = this.productService.update(dto.mapperToProduct(), code);
        return ResponseEntity.ok(new ProductBasicDTO(
                        product.getId(),
                        product.getName(),
                        product.getSupplier().getId(),
                        product.getCategory().getId(),
                        product.getUnit(),
                        product.getPrice()
                )
        );
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> delete(@PathVariable(name = "codigo") Integer code){
        this.productService.delete(code);
        return ResponseEntity.noContent().build();
    }
}
