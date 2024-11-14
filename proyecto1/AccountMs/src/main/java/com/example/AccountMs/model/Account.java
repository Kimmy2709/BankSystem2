package com.example.AccountMs.model;

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
    private String accountNumber;

    @Column(nullable = false)
    private BigDecimal balance_amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType account_type;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    public enum AccountType {
        AHORROS, CORRIENTE
    }


    public Account() {
    }

    public Account(Long id, String account_number, BigDecimal balance_amount, AccountType account_type, Client client) {
        this.id = id;
        this.accountNumber  = account_number;
        this.balance_amount  = balance_amount;
        this.account_type  = account_type;
        this.client = client;
    }
}