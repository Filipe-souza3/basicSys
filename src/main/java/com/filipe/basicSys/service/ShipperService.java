package com.filipe.basicSys.service;

import com.filipe.basicSys.model.Customer;
import com.filipe.basicSys.model.Shipper;
import com.filipe.basicSys.repository.ShipperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShipperService {

    @Value("${spring.page.page-size}")
    private Integer pageSize;
    private final ShipperRepository shipperRepository;

    public Page<Shipper> getAll(Shipper shipper, Integer page) {
        Pageable pageable = PageRequest.of(page, pageSize);

        if (shipper == null) {
            return this.shipperRepository.findAll(pageable);
        }

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Shipper> example = Example.of(shipper, matcher);
        return this.shipperRepository.findAll(example, pageable);
    }

    public Shipper save(Shipper shipper) {
        if (shipper == null) {
            throw new IllegalArgumentException("Entregador esta nulo.");
        }
        return this.shipperRepository.save(shipper);
    }

    public Shipper getById(Integer code) {
        if (code == null) {
            throw new IllegalArgumentException("código é nulo");
        }
        Optional<Shipper> shipper = this.shipperRepository.findById(code);
        if (shipper.isEmpty()) {
            throw new IllegalArgumentException("Entregador não encontrado.");
        }
        return shipper.get();
    }


    @Transactional
    public Shipper update(Shipper update, Integer code) {
        if (code == null) {
            throw new IllegalArgumentException("O código é nulo.");
        }

        Shipper original = this.shipperRepository.findById(code).orElseThrow(() -> new IllegalArgumentException("Entregador não encontrado"));
        return this.checkParamsUpdate(original, update);
    }

    public void delete(Integer code){
        if(code == null){
            throw new IllegalArgumentException("Código invalido.");
        }
        this.shipperRepository.deleteById(code);
    }

    /// //PRIVATE METHODS
    private Shipper checkParamsUpdate(Shipper original, Shipper update) {
        Field[] fieldOriginal = original.getClass().getDeclaredFields();
        Field[] fieldUpdate = update.getClass().getDeclaredFields();

        for (Field FUpdate : fieldUpdate) {
            FUpdate.setAccessible(true);

            try {
                Object value = FUpdate.get(update);
                if (value != null) {
                    for (Field FOriginal : fieldOriginal) {
                        FOriginal.setAccessible(true);

                        if (FUpdate.getName().equals(FOriginal.getName())) {
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
