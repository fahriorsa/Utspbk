package com.pbk.lazada.Product;

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
import com.pbk.lazada.ProductCategory.Category;
import com.pbk.lazada.ProductCategory.CategoryService;

import jakarta.servlet.http.HttpSession;

/**
 * ProductController
 */
@Controller
@RequestMapping(path = "/product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String getAll(Model model, HttpSession session) {
        // menampilkan list produk dan kategori
        List<Product> dataProduct = this.productService.getAll();
        List<Category> categoryProduct = this.categoryService.getAll();
        model.addAttribute("dataProduct", dataProduct);
        model.addAttribute("categoryProduct", categoryProduct);

        // mengambil session customer
        Customer customer = (Customer) session.getAttribute("customer");
        model.addAttribute("customer", customer);
        return "product/index";
    }

    @GetMapping("/tambah")
    public String form_tambah(Model model) {
        // menampilkan kategori (navbar)
        List<Category> categoryProduct = this.categoryService.getAll();
        model.addAttribute("categoryProduct", categoryProduct);
        return "product/tambah";
    }

    @PostMapping("/simpan")
    public String simpan(@ModelAttribute Product product) {
        this.productService.simpan(product);
        return "redirect:/product/";
    }

    @GetMapping("/edit/{id}")
    public String form_edit(@PathVariable Long id, Model model) {
        // menampilkan kategori (navbar)
        List<Category> categoryProduct = this.categoryService.getAll();
        model.addAttribute("categoryProduct", categoryProduct);

        Product product = this.productService.detail(id);
        model.addAttribute("product", product);
        return "product/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Product product) {
        this.productService.ubah(id, product);
        return "redirect:/product/";
    }

    @GetMapping("/hapus/{id}")
    public String hapus(@PathVariable Long id) {
        this.productService.hapus(id);
        return "redirect:/product/";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model, HttpSession session) {
        // menampilkan kategori (navbar)
        List<Category> categoryProduct = this.categoryService.getAll();
        model.addAttribute("categoryProduct", categoryProduct);

        Product product = this.productService.detail(id);
        model.addAttribute("detailProduct", product);

        // mengambil session customer
        Customer customer = (Customer) session.getAttribute("customer");
        model.addAttribute("customer", customer);
        return "product/detail";
    }
}