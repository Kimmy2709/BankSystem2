package com.example.Proyecto_II.model;

import jakarta.persistence.*;
import lombok.Builder;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Builder
@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String account_number;

    @Column(nullable = false)
    private BigDecimal balance_amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType account_type;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    enum AccountType {
        AHORROS, CORRIENTE
    }
}

