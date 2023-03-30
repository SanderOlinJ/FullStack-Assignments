package edu.ntnu.idatt2105.sojohans.backend.repository;

import edu.ntnu.idatt2105.sojohans.backend.model.Equation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquationRepository extends JpaRepository<Equation, Integer> {
    void deleteByUserUsername(String username);
}
