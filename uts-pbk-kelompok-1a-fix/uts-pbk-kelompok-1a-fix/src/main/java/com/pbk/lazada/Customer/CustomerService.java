package com.pbk.lazada.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbk.lazada.user.User;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void tambah(Customer customer) {
        this.customerRepository.save(customer);
    }

    public Customer getByUser(User user) {
        return this.customerRepository.findByUser(user);
    }

    public Customer getById(Long id) {
        return this.customerRepository.findById(id).orElse(null);
    }

    public void ubah(Long id, Customer customer) {
        Customer customer_lama = this.customerRepository.findById(id).orElse(null);

        customer_lama.setNamaLengkap(customer.getNamaLengkap());
        customer_lama.setNomorHp(customer.getNomorHp());
        customer_lama.setAlamat(customer.getAlamat());

        this.customerRepository.save(customer_lama);
    }
}
