package com.infy.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public boolean login(String emailOrUsername, String password) {
        RegistrationUser user = loginRepository.findByEmailOrUsername(emailOrUsername, emailOrUsername);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return true; // Login successful
        }
        return false; // Login failed
    }
}
