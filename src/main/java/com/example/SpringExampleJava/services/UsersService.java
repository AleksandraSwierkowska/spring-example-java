package com.example.SpringExampleJava.services;

import com.example.SpringExampleJava.data.User;
import com.example.SpringExampleJava.repositories.UsersRepository;
import org.springframework.stereotype.Service;

@Service("Users Service")
public class UsersService {

    private final UsersRepository repository;

    public UsersService(UsersRepository repository) {
        this.repository = repository;
    }

    public User getUserByEmail(String email) {
        return repository.findOneByEmail(email);
    }
}
