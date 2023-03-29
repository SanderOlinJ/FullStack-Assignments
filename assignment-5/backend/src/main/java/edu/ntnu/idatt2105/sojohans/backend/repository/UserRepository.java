package edu.ntnu.idatt2105.sojohans.backend.repository;

import edu.ntnu.idatt2105.sojohans.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> { }
