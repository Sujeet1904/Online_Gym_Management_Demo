package com.infy.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.ui.Model;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // returns the login.html template
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginPojo loginRequest, Model model) {
        boolean loginSuccessful = loginService.login(loginRequest.getEmailOrUsername(), loginRequest.getPassword());
        if (loginSuccessful) {
            return "redirect:/dashboard"; // Redirect to dashboard or home page after successful login
        } else {
            // Handle login failure
            model.addAttribute("error", "Invalid email/username or password. Please try again.");
            return "login"; // Return login page with error message
        }
    }
}
