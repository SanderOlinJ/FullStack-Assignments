package edu.ntnu.idatt2105.sojohans.backend.controller;

import edu.ntnu.idatt2105.sojohans.backend.model.User;
import edu.ntnu.idatt2105.sojohans.backend.model.UserRequest;
import edu.ntnu.idatt2105.sojohans.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    private Logger logger = LoggerFactory.getLogger(CalculatorController.class);

    @PostMapping("/signup")
    public User addUser(@RequestBody UserRequest userRequest){
        logger.info("User request received: " + userRequest.getUsername() + " | " + userRequest.getPassword());
        System.out.println(userRequest.getUsername() + " | " + userRequest.getPassword());
        return userService.addUser(userRequest.getUsername(), userRequest.getPassword());
    }
}
