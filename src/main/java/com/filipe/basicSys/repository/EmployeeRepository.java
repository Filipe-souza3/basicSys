package com.filipe.basicSys.repository;

import com.filipe.basicSys.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
