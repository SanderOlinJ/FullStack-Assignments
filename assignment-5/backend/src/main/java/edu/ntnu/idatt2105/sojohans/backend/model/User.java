package edu.ntnu.idatt2105.sojohans.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user")
public class User {

    @Id @Column(name = "username") private String username;
    @Column(name = "password") private byte[] password;
    @Column(name = "salt") private byte[] salt;

}
