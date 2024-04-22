package com.infy.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String registerUser(RegistrationPojo registrationPojo) {
        // Check if email or username already exists in the database
        if (registrationRepository.existsByEmail(registrationPojo.getEmail())) {
            return "Email already exists";
        }

        if (registrationRepository.existsByUsername(registrationPojo.getUsername())) {
            return "Username already exists";
        }

        // Hash the password before saving it to the database
        String hashedPassword = passwordEncoder.encode(registrationPojo.getPassword());

        // Create a RegistrationUser entity and save it to the database
        RegistrationUser user = new RegistrationUser(registrationPojo.getFirstName(), registrationPojo.getLastName(),
                registrationPojo.getEmail(), registrationPojo.getMobileNo(),
                registrationPojo.getUsername(), hashedPassword);
        try {
            registrationRepository.save(user);
            return "User registered successfully";
        } catch (Exception e) {
            return "Error registering user: " + e.getMessage();
        }
    }
}
