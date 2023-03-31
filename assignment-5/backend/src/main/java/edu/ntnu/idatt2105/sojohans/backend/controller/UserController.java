package edu.ntnu.idatt2105.sojohans.backend.controller;

import edu.ntnu.idatt2105.sojohans.backend.model.Equation;
import edu.ntnu.idatt2105.sojohans.backend.model.UserRequest;
import edu.ntnu.idatt2105.sojohans.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/signup")
    public ResponseEntity<String> addUser(@RequestBody UserRequest userRequest){
        System.out.println("test1");
        logger.info("User signup request received: " + userRequest.getUsername() + " | " + userRequest.getPassword());
        return userService.addUser(userRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserRequest userRequest){
        logger.info("User login request received: " + userRequest.getUsername() + " | " + userRequest.getPassword());
        return userService.login(userRequest);
    }

    @GetMapping("/refreshToken")
    public String refreshToken(@RequestParam("email") String username) {
        return userService.generateToken(username);
    }

    @PostMapping("/addEquation/{username}")
    public boolean postEquationWithUser(@RequestBody Equation equation, @PathVariable String username){
        logger.info("Equation received: " + equation.getEquation() + " from user: " + username);
        return userService.addEquation(equation, username);
    }

    @GetMapping("/getEquations/{username}")
    public List<Equation> getEquationFromUser(@PathVariable String username){
        logger.info("Received request for all equations by user: " + username);
        return userService.getEquationsByUser(username);
    }

    @PostMapping("/clearEquations/{username}")
    public boolean clearEquations(@PathVariable String username){
        logger.info("Received request to clear all equations by user: " + username);
        return userService.clearEquationsByUser(username);
    }
}
