package com.filipe.basicSys.service;

import com.filipe.basicSys.dto.ProductDTO;
import com.filipe.basicSys.model.Product;
import com.filipe.basicSys.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {


    @Value("${spring.page.page-size}")
    private Integer pageSize;
    private final ProductRepository productRepository;


    public Page<Product> getAll(Product product, Integer page) {

        if (page == null) {
            throw new IllegalArgumentException("A page esta vazia.");
        }

        Pageable pageable = PageRequest.of(page, this.pageSize);
        if (product == null) {
            return this.productRepository.findAll(pageable);
        }

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Product> example = Example.of(product, matcher);
        return this.productRepository.findAll(example, pageable);
    }

    public void save(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Produto é inválido.");
        }
        this.productRepository.save(product);
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
