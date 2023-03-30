package edu.ntnu.idatt2105.sojohans.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "equation")
public class Equation {

    @Id
    @GeneratedValue
    @Column(name = "id") private int id;
    @Column(name = "equation") private String equation;
    @Column(name = "solution") private double solution;

    @ManyToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @JoinColumn(name = "user_username")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
