package com.pbk.lazada.Orders;

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
import com.pbk.lazada.Customer.CustomerService;
import com.pbk.lazada.OrderItem.OrderItem;
import com.pbk.lazada.OrderItem.OrderItemService;
import com.pbk.lazada.Product.Product;
import com.pbk.lazada.Product.ProductService;
import com.pbk.lazada.ProductCategory.Category;
import com.pbk.lazada.ProductCategory.CategoryService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/orders")
public class OrdersController {
    private final OrdersService ordersService;
    private final OrderItemService orderItemService;
    private final ProductService productService;
    private final CustomerService customerService;
    private final CategoryService categoryService;

    @Autowired
    public OrdersController(OrdersService ordersService, CategoryService categoryService, ProductService productService,
            CustomerService customerService, OrderItemService orderItemService) {
        this.ordersService = ordersService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.customerService = customerService;
        this.orderItemService = orderItemService;
    }

    @GetMapping("/{customer}/{produk}")
    public String order(@PathVariable Long customer, @PathVariable Long produk, Model model) {
        // menampilkan kategori (navbar)
        List<Category> categoryProduct = this.categoryService.getAll();
        model.addAttribute("categoryProduct", categoryProduct);

        // get customer by id
        Customer customer1 = this.customerService.getById(customer);
        model.addAttribute("customer", customer1);

        // get produk by id
        Product product = this.productService.detail(produk);
        model.addAttribute("product", product);
        return "orders/order";
    }

    @PostMapping("/simpan")
    public String simpan(@ModelAttribute OrderItem orderItem, @ModelAttribute Orders order) {
        // simpan data order
        this.ordersService.tambah(order);

        // simpan data order dan produk ke OrderItem
        orderItem.setOrder(order);
        this.orderItemService.tambah(orderItem);
        return "redirect:/orders/cart";
    }

    @GetMapping("/cart")
    public String keranjang(Model model, HttpSession session) {
        // menampilkan kategori (navbar)
        List<Category> categoryProduct = this.categoryService.getAll();
        model.addAttribute("categoryProduct", categoryProduct);

        // mengambil session customer
        Customer customer = (Customer) session.getAttribute("customer");
        model.addAttribute("customer", customer);

        // menampilkan data order
        List<OrderItem> dataOrder = this.orderItemService.getByOrder(customer);
        model.addAttribute("dataOrder", dataOrder);
        return "orders/keranjang";
    }

    @GetMapping("/hapus/{id}")
    public String hapus(@PathVariable Long id) {
        this.ordersService.hapus(id);
        return "redirect:/orders/cart";
    }
}
