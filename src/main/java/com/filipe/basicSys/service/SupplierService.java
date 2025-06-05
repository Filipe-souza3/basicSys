package com.filipe.basicSys.service;

import com.filipe.basicSys.model.Supplier;
import com.filipe.basicSys.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierService {

    @Value("${spring.page.page-size}")
    private Integer pageSize;
    private final SupplierRepository supplierRepository;

    public Page<Supplier> getAll(Supplier supplier, Integer page) {

        Pageable pageable = PageRequest.of(page, this.pageSize);
        if (supplier == null) {
            return this.supplierRepository.findAll(pageable);
        }
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Supplier> example = Example.of(supplier, matcher);
        return this.supplierRepository.findAll(example, pageable);
    }

    public Supplier getById(Integer code) {
        if (code == null) {
            throw new IllegalArgumentException("Código inválido.");
        }
        Optional<Supplier> supplier = this.supplierRepository.findById(code);
        if(supplier.isEmpty()){
            throw new IllegalArgumentException("Fornecer não encontrado.");
        }

        return supplier.get();
    }

    public Supplier save(Supplier supplier) {
        if (supplier == null) {
            throw new IllegalArgumentException("Fornecedor esta nulo.");
        }
        return this.supplierRepository.save(supplier);
    }

    @Transactional
    public Supplier update(Supplier supplier, Integer code) {
        if (code == null) {
            throw new IllegalArgumentException("Código inválido.");
        }

        Supplier supplierOriginal = this.supplierRepository.findById(code).orElseThrow(() -> new IllegalArgumentException("Fornecedor não encontrado."));
        return this.supplierRepository.save(this.checkParamsUpdate(supplierOriginal, supplier));

    }

    public void delete(Integer code) {
        if (code == null) {
            throw new IllegalArgumentException("Código inválido.");
        }
        this.supplierRepository.deleteById(code);
    }

    /// ///PRIVATE METHODS
    private Supplier checkParamsUpdate(Supplier original, Supplier update) {

        Field[] fieldOriginal = original.getClass().getDeclaredFields();
        Field[] fieldUpdate = update.getClass().getDeclaredFields();

        for (Field FUpdate : fieldUpdate) {
            FUpdate.setAccessible(true);

            try {
                Object value = FUpdate.get(update);
                if (value != null) {
                    for (Field FOriginal : fieldOriginal) {
                        if (FUpdate.getName().equals(FOriginal.getName())) {
                            FOriginal.setAccessible(true);
                            FOriginal.set(original, value);
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
