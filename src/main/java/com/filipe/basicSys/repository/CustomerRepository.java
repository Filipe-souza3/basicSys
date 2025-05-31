package com.filipe.basicSys.repository;

import com.filipe.basicSys.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
