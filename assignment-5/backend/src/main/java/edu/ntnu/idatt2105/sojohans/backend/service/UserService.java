package edu.ntnu.idatt2105.sojohans.backend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import edu.ntnu.idatt2105.sojohans.backend.model.Equation;
import edu.ntnu.idatt2105.sojohans.backend.model.User;
import edu.ntnu.idatt2105.sojohans.backend.model.UserRequest;
import edu.ntnu.idatt2105.sojohans.backend.repository.EquationRepository;
import edu.ntnu.idatt2105.sojohans.backend.repository.UserRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.*;
import java.security.spec.*;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    EquationRepository equationRepository;

    public static final String keyStr = "thisstringwasstolenfromanapplicationmadeintendaysbyfourpeoplefromntnu";
    private static final Duration JWT_TOKEN_VALIDITY = Duration.ofMinutes(5);

    public ResponseEntity<String> addUser(UserRequest userRequest){
        Optional<User> existingUser = userRepository.findById(userRequest.getUsername());
        if (existingUser.isPresent()){
            String response = "User with this username exists!";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        System.out.println(userRequest.getPassword());
        User user = generateUser(userRequest);
        userRepository.save(user);
        return ResponseEntity.ok("Successfully created account");
    }

    public ResponseEntity<Map<String, Object>> login(UserRequest userRequest){
        Optional<User> existingUser = userRepository.findById(userRequest.getUsername());
        Map<String, Object> response = new HashMap<>();
        if (existingUser.isEmpty()){
            response.put("message", "Username or password is incorrect.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        User user = existingUser.get();
        byte[] hashedPassword = hashPassword(userRequest.getPassword(), user.getSalt());

        if (hashedPassword == null || !Arrays.equals(hashedPassword, user.getPassword())){
            response.put("message", "Username or password is incorrect.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        String token = generateToken(user.getUsername());
        UserRequest userRequest1 = new UserRequest(user.getUsername(), token);
        response.put("message", "Success");
        response.put("userRequest", userRequest1);
        return ResponseEntity.ok(response);
    }

    public boolean addEquation(Equation equation, String username){
        Optional<User> existingUser = userRepository.findById(username);
        if (existingUser.isPresent()){
            User user = existingUser.get();
            equation.setUser(user);
            equationRepository.save(equation);
            return true;
        }
        return false;
    }

    public List<Equation> getEquationsByUser(String username){
        Optional<User> existingUser = userRepository.findById(username);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            List<Equation> equationsByUser = new ArrayList<>();
            List<Equation> allEquations = equationRepository.findAll();

            for (Equation equation : allEquations) {
                if (equation.getUser().equals(user)) {
                    equationsByUser.add(equation);
                }
            }
            return equationsByUser;
        }
        return null;
    }

    @Transactional
    public boolean clearEquationsByUser(String username){
        Optional<User> existingUser = userRepository.findById(username);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            equationRepository.deleteByUserUsername(user.getUsername());
            return true;
        }
        return false;
    }

    private User generateUser(UserRequest updatedUser) {
        User user = new User();
        user.setUsername(updatedUser.getUsername());

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        user.setSalt(salt);

        byte[] hashedPassword = hashPassword(updatedUser.getPassword(), salt);
        user.setPassword(hashedPassword);
        return user;
    }

    public String generateToken(final String userId) {
        final Instant now = Instant.now();
        final Algorithm hmac512 = Algorithm.HMAC512(keyStr);
        final JWTVerifier verifier = JWT.require(hmac512).build();
        return JWT
                .create()
                .withSubject(userId)
                .withIssuer("idatt2105_shopkeeper_app")
                .withIssuedAt(now)
                .withExpiresAt(now.plusMillis(JWT_TOKEN_VALIDITY.toMillis()))
                .sign(hmac512);
    }

    public static byte[] hashPassword(String password, byte[] salt) {
        int iterationCount = 65536;
        int keyLength = 128;
        KeySpec spec = new PBEKeySpec(
                password.toCharArray(),
                salt,
                iterationCount,
                keyLength
        );
        SecretKeyFactory factory;

        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        }
    }
}
