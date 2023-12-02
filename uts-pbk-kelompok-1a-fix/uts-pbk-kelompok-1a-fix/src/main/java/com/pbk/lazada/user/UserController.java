package com.pbk.lazada.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.pbk.lazada.Customer.Customer;
import com.pbk.lazada.Customer.CustomerService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    private final UserService userService;
    private final CustomerService customerService;

    @Autowired
    public UserController(UserService userService, CustomerService customerService) {
        this.userService = userService;
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String login() {
        return "user/login";
    }

    @PostMapping("/in")
    public String in(@ModelAttribute User user, HttpSession session) {

        // get user by username dan password yang diinput
        User cekUser = this.userService.getByUsername(user.getUsername());
        User cekPass = this.userService.getByPassword(user.getPassword());

        // get customer by username
        Customer customer = this.customerService.getByUser(cekUser);

        // cek user di database
        if (cekUser != null && cekPass != null) {
            // jika username dan password benar
            if (cekUser.getRole().equalsIgnoreCase("user")) {
                // session customer
                session.setAttribute("customer", customer);
                return "redirect:/home";
            } else {
                return "redirect:/product/";
            }
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/daftar")
    public String daftar() {
        return "user/daftar";
    }

    @PostMapping("/regist")
    public String regist(@ModelAttribute User user, Model model) {
        // menambahkan akun user yang telah diinput pada registrasi
        this.userService.tambah(user);

        // mengirimkan username ke halaman input customer
        User user1 = this.userService.getByUsername(user.getUsername());
        model.addAttribute("user", user1);
        return "customer/data";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // mengakhiri session
        session.invalidate();
        return "redirect:/";
    }
}
