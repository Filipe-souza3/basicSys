package com.filipe.basicSys.controller;

import com.filipe.basicSys.dto.EmployeeDTO;
import com.filipe.basicSys.dto.EmployeeUpdateDTO;
import com.filipe.basicSys.model.Employee;
import com.filipe.basicSys.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Page<Employee>> getAll(@Valid EmployeeUpdateDTO dto, @RequestParam(defaultValue = "0") Integer page){
        return ResponseEntity.ok(this.employeeService.getAll(dto.mapperToEmployee(), page));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Employee> getById(@PathVariable(name = "codigo") Integer code){
        return ResponseEntity.ok(this.employeeService.getById(code));
    }

    @PostMapping
    public ResponseEntity<Employee> save(@Valid @RequestBody EmployeeDTO dto){
        return ResponseEntity.ok(this.employeeService.save(dto.mapperToEmployee()));
    }

    @PatchMapping("/{codigo}")
    public ResponseEntity<Employee> update(
            @RequestBody EmployeeUpdateDTO dto,
            @PathVariable(name = "codigo") Integer code){
        return ResponseEntity.ok(this.employeeService.update(dto.mapperToEmployee(), code));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> delete(@PathVariable(name = "codigo") Integer code){
        this.employeeService.delete(code);
       return ResponseEntity.noContent().build();
    }
}
