package com.pbk.lazada.Orders;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbk.lazada.Customer.Customer;

@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<Orders> getAll() {
        return this.ordersRepository.findAll();
    }

    public List<Orders> getByCustomer(Customer customer) {
        return this.ordersRepository.findByCustomer(customer);
    }

    public void tambah(Orders order) {
        Date date = new Date();
        order.setStatus("belum");
        order.setPaymentStatus(false);
        order.setShippingStatus("belum dikirim");
        order.setTanggalOrder(date);
        this.ordersRepository.save(order);
    }

    public void hapus(Long id) {
        this.ordersRepository.deleteById(id);
    }
}
