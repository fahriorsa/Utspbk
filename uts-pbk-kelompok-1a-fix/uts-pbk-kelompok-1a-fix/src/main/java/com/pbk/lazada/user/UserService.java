package com.pbk.lazada.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public User getByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public User getByPassword(String password) {
        return this.userRepository.findByPassword(password);
    }

    public User tambah(User user) {
        user.setRole("user");
        return this.userRepository.save(user);
    }
}
