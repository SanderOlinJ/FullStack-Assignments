package edu.ntnu.idatt2105.sojohans.backend.service;

import org.springframework.stereotype.Service;

import edu.ntnu.idatt2105.sojohans.backend.model.Equation;

@Service
public class EquationSolver {
    
    public static double solveEquation(Equation equation){
        String eqString = equation.getEquation();
        String[] split = eqString.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");
        double result = Double.parseDouble(split[0]);
        switch (split[1]) {
            case "+" -> result += Double.parseDouble(split[2]);
            case "-" -> result -= Double.parseDouble(split[2]);
            case "÷" -> result /= Double.parseDouble(split[2]);
            case "x" -> result *= Double.parseDouble(split[2]);

        }
        return result;
    }
}
