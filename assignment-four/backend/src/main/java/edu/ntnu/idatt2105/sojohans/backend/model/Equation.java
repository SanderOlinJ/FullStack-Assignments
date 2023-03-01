package edu.ntnu.idatt2105.sojohans.backend.model;

public class Equation {
    private final String equation;
    private double solution;
    private String error;

    public Equation(String equation){
        this.equation = equation;
    }

    public String getEquation() {
        return equation;
    }

    public void setSolution(double result) {
        this.solution = result;
    }

    public double getSolution() {
        return solution;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
