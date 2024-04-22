package com.infy.main;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<RegistrationUser, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    RegistrationUser findByEmailOrUsername(String email, String username);
}

