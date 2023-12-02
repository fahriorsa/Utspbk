package com.pbk.lazada.OrderItem;

import com.pbk.lazada.Orders.Orders;
import com.pbk.lazada.Product.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int jumlah;

    // Relasi Many-To-One OrderItem-Orders
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    // Relasi One-To-One OrderItem-Product
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product produk;

    public OrderItem() {
    }

    public OrderItem(int jumlah, Orders order, Product produk) {
        this.jumlah = jumlah;
        this.order = order;
        this.produk = produk;
    }

}
