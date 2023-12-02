package com.pbk.lazada.Product;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.pbk.lazada.OrderItem.OrderItem;
import com.pbk.lazada.ProductCategory.Category;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

/**
 * Product
 */
@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nama;
    private String deskripsi;
    private String brand;
    private int stok;
    private int harga;

    // Relasi Many-To-One Product-ProductCategory
    @ManyToOne
    @JoinColumn(name = "kategori_id")
    private Category kategori;

    // Relasi One-To-One Product-OrderItem
    @OneToOne(mappedBy = "produk")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private OrderItem orderItem;

    public Product() {
    }

    public Product(String nama, String deskripsi, String brand, int stok, int harga, Category kategori) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.brand = brand;
        this.stok = stok;
        this.harga = harga;
        this.kategori = kategori;
    }
}