package edu.ntnu.idatt2105.sojohans.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import edu.ntnu.idatt2105.sojohans.backend.model.Equation;
import edu.ntnu.idatt2105.sojohans.backend.service.EquationSolver;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api")
public class CalculatorController {

    private Logger logger = LoggerFactory.getLogger(CalculatorController.class);

    @PostMapping("/calculate/{username}")
    public Equation postEquation(@RequestBody Equation equation, @PathVariable String username){
        logger.info("Equation received: " + equation.getEquation() + "from user: " + username);
        EquationSolver.solveEquation(equation, username);
        logger.info("Solution of equation (" + equation.getEquation() + "): " + equation.getSolution());
        return equation;
    }
}
