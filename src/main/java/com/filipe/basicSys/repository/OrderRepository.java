package com.filipe.basicSys.repository;

import com.filipe.basicSys.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Integer> {

//    @Query(value = "select o.*, c.*, e.*, s.* from orders as o " +
//            "left join customers as c on o.customerid = c.id " +
//            "left join employees as e on o.employeeid = e.id " +
//            "left join shippers as s on o.shipperid = s.id " +
//            "where o.id = :id", nativeQuery = true)
    @Query("select o from Order o " +
            "left join fetch o.customer " +
            "left join fetch o.employee " +
            "left join fetch o.shipper " +
            "where o.id = :id")
    Order findByIdWithRelations(@Param("id") Integer id);
}
