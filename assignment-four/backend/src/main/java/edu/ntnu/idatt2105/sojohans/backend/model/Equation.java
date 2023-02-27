package edu.ntnu.idatt2105.sojohans.backend.model;

public class Equation {
    private final String equation;
    private double result;

    public Equation(String equation){
        this.equation = equation;
    }

    public String getEquation() {
        return equation;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public double getResult() {
        return result;
    }
}
