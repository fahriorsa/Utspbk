package com.pbk.lazada.ProductCategory;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.pbk.lazada.Product.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String kategori;

    // Relasi One-To-Many CategoryProduct-Product
    @OneToMany(mappedBy = "kategori")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Product> produk;

    public Category() {
    }

    public Category(String kategori) {
        this.kategori = kategori;
    }

}
