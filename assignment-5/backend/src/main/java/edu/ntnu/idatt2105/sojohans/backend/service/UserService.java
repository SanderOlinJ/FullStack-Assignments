package edu.ntnu.idatt2105.sojohans.backend.service;

import edu.ntnu.idatt2105.sojohans.backend.model.User;
import edu.ntnu.idatt2105.sojohans.backend.model.UserRequest;
import edu.ntnu.idatt2105.sojohans.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<String> addUser(UserRequest userRequest){
        Optional<User> existingUser = userRepository.findById(userRequest.getUsername());
        if (existingUser.isPresent()){
            String response = "User with this username exists!";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        userRepository.save(user);

        return ResponseEntity.ok("Successfully created account");
    }

    public ResponseEntity<String> login(UserRequest userRequest){
        Optional<User> existingUser = userRepository.findById(userRequest.getUsername());
        if (existingUser.isEmpty() || !existingUser.get().getPassword().equals(userRequest.getPassword())){
            String response = "Username or password is incorrect.";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        return ResponseEntity.ok(("Login successful"));

    }
}
