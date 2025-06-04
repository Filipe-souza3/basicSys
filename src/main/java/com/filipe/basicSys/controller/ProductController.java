package com.filipe.basicSys.controller;

import com.filipe.basicSys.dto.ProductBasicDTO;
import com.filipe.basicSys.dto.ProductDTO;
import com.filipe.basicSys.dto.ProductUpdateDTO;
import com.filipe.basicSys.model.Category;
import com.filipe.basicSys.model.Product;
import com.filipe.basicSys.model.Supplier;
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
            ProductUpdateDTO dto,
            @RequestParam(defaultValue = "0") Integer page) {

            Page<Product> products = this.productService.getAll(dto.mapperToProduct(), page);
            Page<Product> list = products.map(p ->
                    new Product(
                            p.getId(),
                            p.getName(),
                            new Supplier(
                                    p.getSupplier().getId(),
                                    p.getSupplier().getName(),
                                    p.getSupplier().getContactName(),
                                    p.getSupplier().getAddress(),
                                    p.getSupplier().getCity(),
                                    p.getSupplier().getPostalCode(),
                                    p.getSupplier().getCountry(),
                                    p.getSupplier().getPhone()
                            ),
                            new Category(
                                    p.getCategory().getId(),
                                    p.getCategory().getName(),
                                    p.getCategory().getDescription()
                            ),
                            p.getUnit(),
                            p.getPrice()));
            return ResponseEntity.ok(list);
    }

    @GetMapping("/basic")
    public ResponseEntity<Page<ProductBasicDTO>> getAllBasic(ProductUpdateDTO dto, Integer page) {
        Page<Product> products = this.productService.getAll(dto.mapperToProduct(), page);
        Page<ProductBasicDTO> basicProducts = products.map((p) ->
                new ProductBasicDTO(
                        p.getId(),
                        p.getName(),
                        p.getSupplier().getId(),
                        p.getCategory().getId(),
                        p.getUnit(),
                        p.getPrice()
                ));
        return ResponseEntity.ok(basicProducts);
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
