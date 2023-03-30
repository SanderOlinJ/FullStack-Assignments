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

    @PostMapping("/calculate")
    public Equation postEquation(@RequestBody Equation equation){
        logger.info("Equation received: " + equation.getEquation());
        EquationSolver.solveEquation(equation);
        logger.info("Solution of equation (" + equation.getEquation() + "): " + equation.getSolution());
        return equation;
    }
}
