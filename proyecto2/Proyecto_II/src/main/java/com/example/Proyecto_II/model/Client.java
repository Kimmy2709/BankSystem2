package com.example.Proyecto_II.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String first_name;
    @Column(nullable = false)
    private String last_name;
    @Column(nullable = false, unique = true)
    private String dni;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private int accountCount = 0;
    public Client() {
    }

    // Constructor con argumentos (si necesitas uno)
    public Client(int id,  String first_name, String last_name, String dni, String email, int accountCount) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.dni = dni;
        this.email = email;
        this.accountCount = accountCount;
    }

}
