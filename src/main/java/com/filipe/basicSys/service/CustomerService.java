package com.filipe.basicSys.service;

import com.filipe.basicSys.model.Category;
import com.filipe.basicSys.model.Customer;
import com.filipe.basicSys.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    @Value("${spring.page.page-size}")
    private Integer pageSize;
    private final CustomerRepository customerRepository;

    public Page<Customer> getAll(Customer customer, Integer page){

        Pageable pageable = PageRequest.of(page, pageSize);
        if(customer == null){
            return this.customerRepository.findAll(pageable);
        }

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Customer> example = Example.of(customer, matcher);
        return this.customerRepository.findAll(example, pageable);
    }

    public Customer getById(Integer code){
        if(code == null){
            throw new IllegalArgumentException("Código inválido.");
        }
        Optional<Customer> customer = this.customerRepository.findById(code);
        if(customer.isEmpty()){
            throw new IllegalArgumentException("Não encontrado.");
        }
        return customer.get();
    }

    public Customer save(Customer customer){
        if(customer == null){
            throw  new IllegalArgumentException("Customer inválido.");
        }

        return this.customerRepository.save(customer);
    }

    @Transactional
    public Customer update(Customer customer, Integer code){
        if(code == null){
            throw new IllegalArgumentException("Código esta nulo.");
        }
        Customer original = this.customerRepository.findById(code).orElseThrow(()->new IllegalArgumentException("Cliente não encontrado."));
        return this.checkParamsUpdate(original, customer);
    }

    public void delete(Integer code){
        if(code == null){
            throw new IllegalArgumentException("Código é nulo.");
        }

        this.customerRepository.deleteById(code);
    }

    /// //PRIVATE METHODS
    private Customer checkParamsUpdate(Customer original, Customer update)  {
        Field[] fieldOriginal = original.getClass().getDeclaredFields();
        Field[] fieldUpdate = update.getClass().getDeclaredFields();

        for(Field FUpdate : fieldUpdate){
            FUpdate.setAccessible(true);

            try{
                Object value = FUpdate.get(update);
                if(value != null){
                    for(Field FOriginal : fieldOriginal){
                        FOriginal.setAccessible(true);

                        if(FUpdate.getName().equals(FOriginal.getName())){
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
