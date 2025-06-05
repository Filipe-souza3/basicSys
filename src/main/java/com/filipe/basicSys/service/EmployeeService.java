package com.filipe.basicSys.service;

import com.filipe.basicSys.model.Employee;
import com.filipe.basicSys.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    @Value("${spring.page.page-size}")
    private Integer pageSize;
    private final EmployeeRepository employeeRepository;

    public Page<Employee> getAll(Employee employee, Integer page) {
        Pageable pageable = PageRequest.of(page, this.pageSize);
        if (employee == null) {
            return this.employeeRepository.findAll(pageable);
        }
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Employee> example = Example.of(employee, matcher);

        return this.employeeRepository.findAll(example, pageable);
    }

    public Employee getById(Integer code) {
        if (code == null) {
            throw new IllegalArgumentException("Código é nulo");
        }
        return this.employeeRepository.findById(code).orElseThrow(() -> new IllegalArgumentException("Empregado não encontrado."));
    }

    public Employee save(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Empregado é nulo.");
        }

        return this.employeeRepository.save(employee);
    }

    @Transactional
    public Employee update(Employee update, Integer code) {
        if (code == null) {
            throw new IllegalArgumentException("Código esta nulo.");
        }
        Employee original = this.employeeRepository.findById(code).orElseThrow(() -> new IllegalArgumentException("Empregado não encontrado."));
        return this.employeeRepository.save(this.checkParamsUpdate(original, update));
    }

    public void delete(Integer code){
        if(code == null){
            throw new IllegalArgumentException("Código esta nulo.");
        }

        this.employeeRepository.deleteById(code);
    }

    /// //PRIVATE METHODS
    private Employee checkParamsUpdate(Employee original, Employee update) {
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
