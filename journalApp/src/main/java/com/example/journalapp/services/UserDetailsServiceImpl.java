package com.example.journalapp.services;

import com.example.journalapp.entity.User;
import com.example.journalapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || username.trim().isEmpty()) {
            throw new UsernameNotFoundException("Username cannot be null or empty");
        }

        User user = userRepository.findByUserName(username.trim());

        if (user != null) {
            if (user.getPassword() == null) {
                throw new UsernameNotFoundException("User password is null for username: " + username);
            }

            if (user.getRoles() == null || user.getRoles().isEmpty()) {
                throw new UsernameNotFoundException("User has no roles assigned for username: " + username);
            }

            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
        }
        throw new UsernameNotFoundException("User not found with username: " + username + "arigato!!!");
    }
}
