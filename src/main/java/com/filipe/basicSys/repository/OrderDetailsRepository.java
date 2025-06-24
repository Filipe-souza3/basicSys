package com.filipe.basicSys.repository;

import com.filipe.basicSys.model.OrderDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
    @Query("select od from OrderDetails as od " +
            "left join fetch od.product as p " +
            "left join fetch p.supplier " +
            "left join fetch p.category " +
            "left join fetch od.order as o " +
            "left join fetch o.employee " +
            "left join fetch o.shipper " +
            "left join fetch o.customer as customer " +
            "where (:test is null or lower(customer.name) like lower(concat('%', :test ,'%'))) " +
            "order by od.id asc")
    Page<OrderDetails> findAllOrders(@Param("test") String test, @Param("page") Pageable pageable);

    @EntityGraph(attributePaths = {"order", "product"})
    @Query("select od from OrderDetails as od " +
            " left join fetch od.product as p " +
            " left join fetch p.supplier " +
            " left join fetch p.category " +
            " left join fetch od.order as o " +
            " left join fetch o.employee " +
            " left join fetch o.shipper " +
            " left join fetch o.customer as customer " +
            " where od.id =:code")
    Optional<OrderDetails> findCompleteById(@Param("code") Integer code);


}
