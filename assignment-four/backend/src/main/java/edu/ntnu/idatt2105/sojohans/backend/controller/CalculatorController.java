package edu.ntnu.idatt2105.sojohans.backend.controller;

import org.springframework.web.bind.annotation.*;

import edu.ntnu.idatt2105.sojohans.backend.model.Equation;
import edu.ntnu.idatt2105.sojohans.backend.service.EquationSolver;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api")
public class CalculatorController {
    
    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name){
        return String.format("Hello %s!", name);
    }

    @PostMapping("/calculate")
    public Equation postEquation(@RequestParam Equation equation){
        EquationSolver.solveEquation(equation);
        return equation;
    }
}
