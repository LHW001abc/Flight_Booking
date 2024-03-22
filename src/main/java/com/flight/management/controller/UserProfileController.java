package com.flight.management.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.flight.management.model.User;
import com.flight.management.repository.UserRepository;

@Controller
public class UserProfileController {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
   private BCryptPasswordEncoder pass;


    @GetMapping("user/customer/profile")
    public String viewProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        User savedUser = userRepository.findByUsername(user.getUsername());
        model.addAttribute("user", savedUser);
        model.addAttribute("updateSuccess", false);
        return "view_profile";
    }

    @PostMapping("user/customer/save_profile")
    public String saveProfile(@ModelAttribute("user") User user, Model model) {
        User savedUser = userRepository.findByUsername(user.getUsername());
        userRepository.save(User.builder()
                .userId(savedUser.getUserId())
                .username(savedUser.getUsername())
              //  .password(pass.encode(user.getPassword()))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .dob(user.getDob())
                .phone(user.getPhone())
                .address(user.getAddress())
                .role("user").build());
        model.addAttribute("user", user);
        model.addAttribute("updateSuccess", true);
        return "view_profile";
    }
}
