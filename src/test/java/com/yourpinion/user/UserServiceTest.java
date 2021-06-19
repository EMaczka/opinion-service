package com.yourpinion.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Test
    public void shouldGenerateEncryptedPassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "Password123";
        String encodedPassword = encoder.encode(rawPassword);

        assertNotEquals(rawPassword, encodedPassword);
    }

}