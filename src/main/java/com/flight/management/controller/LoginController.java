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
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder pass;

    @GetMapping("/")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register/new_admin")
    public String registerAdmin(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("userExist", false);
        model.addAttribute("user", user);
        return "register_admin";
    }

    @GetMapping("/register/new_customer")
    public String registerCustomer(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("userExist", false);
        model.addAttribute("user", user);
        return "register_customer";
    }

    @PostMapping("/register/save_admin")
    public String saveAdminRegistration(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("userExist", false);
        model.addAttribute("registerSuccess", false);
        String username = user.getUsername();
        if (!userRepository.existsByUsername(username)) {
            userRepository.save(User.builder().username(username).password(pass.encode(user.getPassword())).role("admin").build());
            model.addAttribute("registerSuccess", true);
        } else {
            model.addAttribute("userExist", true);
        }
        model.addAttribute("user", user);
        return "register_admin";
    }

    @PostMapping("/register/save_customer")
    public String saveCustomerRegistration(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("userExist", false);
        model.addAttribute("registerSuccess", false);
        String username = user.getUsername();
        if (!userRepository.existsByUsername(username)) {
            userRepository.save(User.builder().username(username).password(pass.encode(user.getPassword()))
            		.firstName(user.getFirstName()).lastName(user.getLastName()).email(user.getEmail())
            		.dob(user.getDob()).phone(user.getPhone()).address(user.getAddress()).role("user").build());
        } else {
            model.addAttribute("userExist", true);
        }
        model.addAttribute("user", user);
        model.addAttribute("registerSuccess", true);
        return "register_customer";
    }

    @GetMapping("/login_page")
    public String loginPage(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("LoginFailed", false);
        model.addAttribute("user", user);
        return "login_page";
    }

    @PostMapping("/post_login")
    public String login(@ModelAttribute("user") User user, Model model, HttpSession session) {
        model.addAttribute("LoginFailed", false);
        model.addAttribute("user", user);
        User savedUser = userRepository.findByUsername(user.getUsername());
        if (savedUser != null && pass.matches(user.getPassword(),savedUser.getPassword())) {
            session.setAttribute("user", savedUser);
            return savedUser.getRole().toLowerCase().equals("admin") ? "redirect:/admin/dashboard_admin" : "redirect:/user/dashboard_customer";
        } else {
            model.addAttribute("LoginFailed", true);
            return "login_page";
        }
    }

    @GetMapping("/log_out")
    public String logout(HttpSession session) {
    	System.out.println("Log Out");
        session.removeAttribute("user");
        return "redirect:/";
    }
    
    @GetMapping("/access_denied")
    public String accessDenied() {
        return "access_denied";
    }

}
