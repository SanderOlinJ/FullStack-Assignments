package edu.ntnu.idatt2105.sojohans.backend.controller;

import org.springframework.web.bind.annotation.*;

import edu.ntnu.idatt2105.sojohans.backend.model.Equation;
import edu.ntnu.idatt2105.sojohans.backend.service.EquationSolver;

@RestController
@RequestMapping("/api")
public class CalculatorController {
    
    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name){
        return String.format("Hello %s!", name);
    }

    @PostMapping("/equation")
    public double postEquation(@RequestParam Equation equation){
        return EquationSolver.solveEquation(equation);
    }
    
}
