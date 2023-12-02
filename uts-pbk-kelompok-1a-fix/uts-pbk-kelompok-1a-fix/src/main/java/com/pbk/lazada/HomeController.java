package com.pbk.lazada;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pbk.lazada.Customer.Customer;
import com.pbk.lazada.Product.Product;
import com.pbk.lazada.Product.ProductService;
import com.pbk.lazada.ProductCategory.Category;
import com.pbk.lazada.ProductCategory.CategoryService;

import jakarta.servlet.http.HttpSession;

/*
 * HomeController
 * 
 * @Controller menandakan bahwa class ini adalah controller
 */

@Controller
public class HomeController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public HomeController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    /*
     * @GetMapping("/") menandakan bahwa method home() akan dipanggil ketika user
     * mengakses URL http://localhost:8080/
     */
    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        // menampilkan list produk dan kategori
        List<Product> dataProduct = this.productService.getAll();
        List<Category> categoryProduct = this.categoryService.getAll();
        model.addAttribute("dataProduct", dataProduct);
        model.addAttribute("categoryProduct", categoryProduct);

        // mengambil session customer
        Customer customer = (Customer) session.getAttribute("customer");
        model.addAttribute("customer", customer);
        return "home";
    }
}
