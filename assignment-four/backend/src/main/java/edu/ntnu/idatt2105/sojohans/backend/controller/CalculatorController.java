package edu.ntnu.idatt2105.sojohans.backend.controller;

import org.springframework.web.bind.annotation.*;

import edu.ntnu.idatt2105.sojohans.backend.model.Equation;
import edu.ntnu.idatt2105.sojohans.backend.service.EquationSolver;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api")
public class CalculatorController {

    @PostMapping("/calculate")
    public Equation postEquation(@RequestBody Equation equation){
        EquationSolver.solveEquation(equation);
        return equation;
    }
}
