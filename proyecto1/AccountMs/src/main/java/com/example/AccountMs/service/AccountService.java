package com.example.AccountMs.service;

import com.example.AccountMs.dto.AccountDTO;
import com.example.AccountMs.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    public Account createAccount(AccountDTO accountDTO);
    public List<Account> listAccounts();
    public Account getAccountById(Long id);
    Account makeDeposit(Long id, BigDecimal balance_amount);
    Account withDrawMoney(Long id, BigDecimal balance_amount);
    public void deleteAccount(Long id);
}
