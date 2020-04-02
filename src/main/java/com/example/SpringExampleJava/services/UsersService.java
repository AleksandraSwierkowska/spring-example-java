package com.example.SpringExampleJava.services;

import com.example.SpringExampleJava.data.User;
import com.example.SpringExampleJava.repositories.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("Users Service")
public class UsersService implements UserDetailsService {

    private final UsersRepository repository;

    public UsersService(UsersRepository repository) {
        this.repository = repository;
    }

    public User getUserByEmail(String email) {
        return repository.findOneByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException("Null email");
        }
        User user = getUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user with email " + username);
        }
        return user;
    }
}
