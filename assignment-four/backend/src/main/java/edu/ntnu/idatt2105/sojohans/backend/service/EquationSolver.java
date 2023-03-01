package edu.ntnu.idatt2105.sojohans.backend.service;

import org.springframework.stereotype.Service;

import edu.ntnu.idatt2105.sojohans.backend.model.Equation;

@Service
public class EquationSolver {
    
    public static void solveEquation(Equation equation){
        String eqString = equation.getEquation();
        String[] split = eqString.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");
        double result = Double.parseDouble(split[0]);
        for (int i = 1; i < split.length; i+=2){
            switch (split[i]) {
                case "+": {
                    result += Double.parseDouble(split[i + 1]);
                    break;
                }
                case "-": {
                    result -= Double.parseDouble(split[i + 1]);
                    break;
                }
                case "x": {
                    result *= Double.parseDouble(split[i + 1]);
                    break;
                }
                case "÷": {
                    result /= Double.parseDouble(split[i + 1]);
                    break;
                }
                default: {
                    equation.setError("Could not calculate");
                    return;
                }
            }
        }
        equation.setSolution(result);
    }
}
