package com.pbk.lazada.Customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pbk.lazada.ProductCategory.Category;
import com.pbk.lazada.ProductCategory.CategoryService;
import com.pbk.lazada.user.User;
import com.pbk.lazada.user.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public CustomerController(CustomerService customerService, UserService userService,
            CategoryService categoryService) {
        this.customerService = customerService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @PostMapping("/simpan/{id}")
    public String simpan(@ModelAttribute Customer customer, @PathVariable Long id) {
        // mengambil data user by id
        User user1 = this.userService.getById(id);

        // set user pada customer
        customer.setUser(user1);

        // menambahkan data customer baru
        this.customerService.tambah(customer);
        return "redirect:/";
    }

    @GetMapping("/profil")
    public String profil(HttpSession session, Model model) {
        // menampilkan kategori (navbar)
        List<Category> categoryProduct = this.categoryService.getAll();
        model.addAttribute("categoryProduct", categoryProduct);

        // mengambil session customer
        Customer customer = (Customer) session.getAttribute("customer");

        // menampilkan data customer berdasarkan id session
        Customer customer1 = this.customerService.getById(customer.getId());
        model.addAttribute("customer", customer1);
        return "customer/profil";
    }

    @GetMapping("/edit")
    public String edit(HttpSession session, Model model) {
        // menampilkan kategori (navbar)
        List<Category> categoryProduct = this.categoryService.getAll();
        model.addAttribute("categoryProduct", categoryProduct);

        // mengambil session customer
        Customer customer = (Customer) session.getAttribute("customer");

        // menampilkan data customer berdasarkan id session
        Customer customer1 = this.customerService.getById(customer.getId());
        model.addAttribute("customer", customer1);
        return "customer/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Customer customer) {
        this.customerService.ubah(id, customer);
        return "redirect:/customer/profil";
    }
}
