package com.pbk.lazada.Customer;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.pbk.lazada.Orders.Orders;
import com.pbk.lazada.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namaLengkap;
    private String nomorHp;
    private String alamat;

    // Relasi One-To-One Customer-User
    @OneToOne
    @JoinColumn(name = "user")
    private User user;

    // Relasi One-To-Many Customer-Orders
    @OneToMany(mappedBy = "customer")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Orders> order;

    public Customer() {
    }

    public Customer(String namaLengkap, String nomorHp, String alamat, User user) {
        this.namaLengkap = namaLengkap;
        this.nomorHp = nomorHp;
        this.alamat = alamat;
        this.user = user;
    }

}
