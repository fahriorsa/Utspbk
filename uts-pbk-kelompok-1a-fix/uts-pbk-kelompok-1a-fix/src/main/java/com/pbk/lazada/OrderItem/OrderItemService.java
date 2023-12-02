package com.pbk.lazada.OrderItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbk.lazada.Customer.Customer;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public void tambah(OrderItem orderItem) {
        this.orderItemRepository.save(orderItem);
    }

    public List<OrderItem> getByOrder(Customer customer) {
        return this.orderItemRepository.findByOrderCustomer(customer);
    }
}
