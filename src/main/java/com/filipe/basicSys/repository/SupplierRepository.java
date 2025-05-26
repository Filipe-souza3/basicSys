package com.filipe.basicSys.repository;

import com.filipe.basicSys.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
