package com.example.SpringExampleJava.restControllers;

import com.example.SpringExampleJava.data.User;
import com.example.SpringExampleJava.exceptions.UserNotFoundException;
import com.example.SpringExampleJava.services.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UsersRestController {

    private static Logger logger = LoggerFactory.getLogger(UsersRestController.class);
    private final UsersService service;

    public UsersRestController(UsersService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser() throws UserNotFoundException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = service.getUserByEmail(email);
        if (user == null) {
            throw new UserNotFoundException(email);
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createUser(@RequestBody User user) {
        logger.info(user.toString());
        return "Ok";
    }
}
