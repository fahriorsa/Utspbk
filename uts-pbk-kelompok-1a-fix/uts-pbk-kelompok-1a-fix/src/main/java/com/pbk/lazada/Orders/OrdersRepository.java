package com.pbk.lazada.Orders;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pbk.lazada.Customer.Customer;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByCustomer(Customer customer);
}
