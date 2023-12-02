package com.pbk.lazada.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProductService
 */
@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    public void simpan(Product product) {
        this.productRepository.save(product);
    }

    public void hapus(Long id) {
        this.productRepository.deleteById(id);
    }

    public void ubah(Long id, Product product) {
        Product produk_lama = this.productRepository.findById(id).orElse(null);

        produk_lama.setNama(product.getNama());
        produk_lama.setBrand(product.getBrand());
        produk_lama.setHarga(product.getHarga());
        produk_lama.setStok(product.getStok());
        produk_lama.setDeskripsi(product.getDeskripsi());

        this.productRepository.save(produk_lama);
    }

    public Product detail(Long id) {
        return this.productRepository.findById(id).orElse(null);
    }

    public List<Product> kategori(Long id) {
        return this.productRepository.findByKategoriId(id);
    }

}