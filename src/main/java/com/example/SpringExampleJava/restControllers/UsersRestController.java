package com.example.SpringExampleJava.restControllers;

import com.example.SpringExampleJava.data.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UsersRestController {

    private static Logger logger = LoggerFactory.getLogger(UsersRestController.class);
    private ArrayList<User> users = new ArrayList<>();

    public UsersRestController() {
        users.add(new User(1, "Dominik", "example.com"));
        users.add(new User(2, "DominikAbc", "a.b.c"));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(users);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createUser(@RequestBody User user) {
        logger.info(user.toString());
        return "Ok";
    }
}
