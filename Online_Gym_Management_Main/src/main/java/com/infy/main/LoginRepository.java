package com.infy.main;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<RegistrationUser, Long> {
    RegistrationUser findByEmailOrUsername(String email, String username);
}
