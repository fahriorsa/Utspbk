package com.pbk.lazada.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pbk.lazada.user.User;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUser(User user);
}
