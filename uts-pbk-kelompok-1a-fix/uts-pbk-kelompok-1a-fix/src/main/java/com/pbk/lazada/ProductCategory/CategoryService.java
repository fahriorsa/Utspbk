package com.pbk.lazada.ProductCategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    public Category getId(Long id) {
        return this.categoryRepository.findById(id).orElse(null);
    }

    public void simpan(Category category) {
        this.categoryRepository.save(category);
    }

    public void hapus(Long id) {
        this.categoryRepository.deleteById(id);
    }

    public void update(Long id, Category category) {
        Category kategori_lama = this.categoryRepository.findById(id).orElse(null);
        kategori_lama.setKategori(category.getKategori());
        this.categoryRepository.save(kategori_lama);
    }
}
