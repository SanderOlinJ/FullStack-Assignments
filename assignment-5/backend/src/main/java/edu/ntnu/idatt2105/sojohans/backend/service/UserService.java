package edu.ntnu.idatt2105.sojohans.backend.service;

import edu.ntnu.idatt2105.sojohans.backend.model.Equation;
import edu.ntnu.idatt2105.sojohans.backend.model.User;
import edu.ntnu.idatt2105.sojohans.backend.model.UserRequest;
import edu.ntnu.idatt2105.sojohans.backend.repository.EquationRepository;
import edu.ntnu.idatt2105.sojohans.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    EquationRepository equationRepository;

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

    public List<Equation> addEquation(Equation equation, String username){
        Optional<User> existingUser = userRepository.findById(username);
        if (existingUser.isPresent()){
            User user = existingUser.get();
            equation.setUser(user);
            equationRepository.save(equation);
            return getEquationsByUser(user);
        }
        return null;
    }

    private List<Equation> getEquationsByUser(User user){
        List<Equation> equationsByUser = new ArrayList<>();
        List<Equation> allEquations = equationRepository.findAll();

        for (Equation equation : allEquations){
            if (equation.getUser().equals(user)){
                equationsByUser.add(equation);
            }
        }
        return equationsByUser;
    }
}
