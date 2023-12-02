package com.pbk.lazada.Orders;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.pbk.lazada.Customer.Customer;
import com.pbk.lazada.OrderItem.OrderItem;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date tanggalOrder;
    private String status;
    private boolean paymentStatus;
    private String shippingStatus;

    // Relasi Many-To-One Orders-Customer
    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    // Relasi One-To-Many Orders-OrderItem
    @OneToMany(mappedBy = "order")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<OrderItem> orderItem;

    public Orders() {
    }

    public Orders(Date tanggalOrder, String status, boolean paymentStatus, String shippingStatus, Customer customer,
            List<OrderItem> orderItem) {
        this.tanggalOrder = tanggalOrder;
        this.status = status;
        this.paymentStatus = paymentStatus;
        this.shippingStatus = shippingStatus;
        this.customer = customer;
        this.orderItem = orderItem;
    }

}
