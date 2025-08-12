package com.example.journalapp.services;

import com.example.journalapp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    void loadUserByUsernameTest() {
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn((com.example.journalapp.entity.User) User.builder().username("Raj").password("Raj").roles(String.valueOf(new ArrayList<>())).build());
        UserDetails user = userDetailsService.loadUserByUsername("Raj");
        Assertions.assertNotNull(user);
    }
}
