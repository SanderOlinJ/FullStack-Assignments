package edu.ntnu.idatt2105.sojohans.backend.controller;

import edu.ntnu.idatt2105.sojohans.backend.model.Equation;
import edu.ntnu.idatt2105.sojohans.backend.model.User;
import edu.ntnu.idatt2105.sojohans.backend.model.UserRequest;
import edu.ntnu.idatt2105.sojohans.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/signup")
    public ResponseEntity<String> addUser(@RequestBody UserRequest userRequest){
        logger.info("User signup request received: " + userRequest.getUsername() + " | " + userRequest.getPassword());
        return userService.addUser(userRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequest userRequest){
        logger.info("User login request received: " + userRequest.getUsername() + " | " + userRequest.getPassword());
        return userService.login(userRequest);
    }

    @PostMapping("/addEquation/{username}")
    public List<Equation> postEquationWithUser(@RequestBody Equation equation, @PathVariable String username){
        logger.info("Equation received: " + equation.getEquation() + " from user: " + username);
        return userService.addEquation(equation, username);
    }
}
