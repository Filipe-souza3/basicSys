package com.filipe.basicSys.service;

import com.filipe.basicSys.model.Product;
import com.filipe.basicSys.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
@RequiredArgsConstructor
public class ProductService {


    @Value("${spring.page.page-size}")
    private Integer pageSize;
    private final ProductRepository productRepository;


    public Page<Product> getAll(Product product, Integer page) {

        Pageable pageable = PageRequest.of(page, this.pageSize);
        if (product == null) {
            return this.productRepository.findAll(pageable);
        }

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Product> example = Example.of(product, matcher);
        return this.productRepository.findAll(example, pageable);
    }

    public Product save(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Produto é inválido.");
        }
        return this.productRepository.save(product);
    }

    public Product update(Product product, Integer code) {
        if (code == null) {
            throw new IllegalArgumentException("Código inválido.");
        }

        Product productOriginal = this.productRepository.findById(code).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));
        this.productRepository.save(this.checkParamsUpdate(productOriginal, product));
        return productOriginal;
    }

    public void delete(Integer code) {
        if(code == null){
            throw new IllegalArgumentException("Código inválido.");
        }
        this.productRepository.deleteById(code);
    }

    /// /////////PRIVATE METHODS

    private Product checkParamsUpdate(Product original, Product update) {

        Field[] fieldOriginal = original.getClass().getDeclaredFields();
        Field[] fieldUpdate = update.getClass().getDeclaredFields();

        for (Field fUpdate : fieldUpdate) {
            fUpdate.setAccessible(true);
            try {
                Object value = fUpdate.get(update);
                if (value != null) {
                    for (Field fOriginal : fieldOriginal) {
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
