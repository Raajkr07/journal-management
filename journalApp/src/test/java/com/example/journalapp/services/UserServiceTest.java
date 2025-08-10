package com.example.journalapp.services;

import com.example.journalapp.entity.User;
import com.example.journalapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUsernameTest() {
        User user = userRepository.findByUserName("Raj");
        assertTrue(!user.getJournalEntry().isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
            "1, 0, 1",
            "2, 1, 1",
            "5, 2, 1",
            "10, 0, 0"
    })
    public void sumTest(int expected, int a, int b) {
        assertEquals(expected, a + b);
    }
}

