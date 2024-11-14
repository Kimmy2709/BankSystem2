package com.example.AccountMs.dto;
import com.example.AccountMs.model.Account;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountDTO {
    private Long clienteId;
    private Account.AccountType accountType;
    private BigDecimal balanceAmount;
}
