package com.infy.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.ui.Model;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "registration"; // returns the registration.html template
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody RegistrationPojo registrationPojo, Model model) {
        // Delegate registration logic to the service layer
        String result = registrationService.registerUser(registrationPojo);
        // Assuming you have a success page to redirect to after registration
        if ("User registered successfully".equals(result)) {
            return "redirect:/success"; // Redirect to success page
        } else {
            // Handle registration failure
            model.addAttribute("error", result); // Pass error message to registration page
            return "registration"; // Return registration page with error message
        }
    }
}
