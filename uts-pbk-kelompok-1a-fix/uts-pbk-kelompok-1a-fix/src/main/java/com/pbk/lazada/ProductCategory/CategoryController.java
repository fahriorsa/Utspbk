package com.pbk.lazada.ProductCategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pbk.lazada.Customer.Customer;
import com.pbk.lazada.Product.Product;
import com.pbk.lazada.Product.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final ProductService productService;

    @Autowired
    public CategoryController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Long id, Model model, HttpSession session) {
        // get kategori (navbar)
        List<Category> categoryProduct = this.categoryService.getAll();
        model.addAttribute("categoryProduct", categoryProduct);

        // get nama kategori
        Category kategori = this.categoryService.getId(id);
        model.addAttribute("category", kategori);

        // get produk by id kategori
        List<Product> product = this.productService.kategori(id);
        model.addAttribute("dataProduct", product);

        // mengambil session customer(navbar)
        Customer customer = (Customer) session.getAttribute("customer");
        model.addAttribute("customer", customer);

        return "category/category";
    }

    @GetMapping("/tambah")
    public String tambah(Model model) {
        // get kategori (navbar)
        List<Category> categoryProduct = this.categoryService.getAll();
        model.addAttribute("categoryProduct", categoryProduct);
        return "category/tambah";
    }

    @PostMapping("/simpan")
    public String simpan(@ModelAttribute Category category) {
        this.categoryService.simpan(category);
        return "redirect:/home";
    }

    @GetMapping("/hapus/{id}")
    public String hapus(@PathVariable Long id) {
        this.categoryService.hapus(id);
        return "redirect:/home";
    }

    @GetMapping("/edit/{id}")
    public String tambah(@PathVariable Long id, Model model) {
        // get kategori (navbar)
        List<Category> categoryProduct = this.categoryService.getAll();
        model.addAttribute("categoryProduct", categoryProduct);

        // get nama kategori
        Category kategori = this.categoryService.getId(id);
        model.addAttribute("category", kategori);
        return "category/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Category category) {
        this.categoryService.update(id, category);
        return "redirect:/home";
    }
}
