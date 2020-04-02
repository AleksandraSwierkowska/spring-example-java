package com.example.SpringExampleJava.repositories;

import com.example.SpringExampleJava.data.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Integer> {

    User findOneByEmail(String email);
}
