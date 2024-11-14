package com.example.AccountMs.service.impl;
import com.example.AccountMs.dto.AccountDTO;
import com.example.AccountMs.model.Account;
import com.example.AccountMs.model.Client;
import com.example.AccountMs.repository.AccountRepository;
import com.example.AccountMs.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final RestTemplate restTemplate;
    public AccountServiceImpl(AccountRepository accountRepository , RestTemplate restTemplate){
        this.accountRepository= accountRepository;
        this.restTemplate = restTemplate; }

    private String generateUniqueAccountNumber() {
        String account_number;
        do {
            account_number = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
        } while (accountRepository.existsByAccountNumber(account_number));
        return account_number;
    }

    public Account createAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setAccountNumber(generateUniqueAccountNumber());
        account.setBalance_amount(BigDecimal.ZERO); // Saldo inicial en 0
        account.setAccount_type(accountDTO.getAccountType());

        Client client = new Client();
        client.setId(accountDTO.getClienteId());
        account.setClient(client);

        return accountRepository.save(account);
    }
    public List<Account> listAccounts(){

        return  accountRepository.findAll();
    }

    public Account getAccountById(Long id){
       Optional<Account> account = accountRepository.findById(id);
        return account.orElseThrow(() -> new NoSuchElementException("cliente no encontrado"));
    }

    public Account makeDeposit(Long id, BigDecimal balanceAmount) {
        Account account = getAccountById(id);
        account.setBalance_amount(account.getBalance_amount().add(balanceAmount));
        return accountRepository.save(account);
    }

    private final Predicate<Account> canWithdrawSufficientFunds = account ->
            account.getBalance_amount().compareTo(BigDecimal.ZERO) > 0;
    public Account withDrawMoney(Long id, BigDecimal balanceAmount) {
        Account account = getAccountById(id);

        if (!canWithdrawSufficientFunds.test(account)) {
            throw new IllegalArgumentException("Fondos insuficientes.");
        }

        account.setBalance_amount(account.getBalance_amount().subtract(balanceAmount));
        return accountRepository.save(account);
    }


    public void  deleteAccount(Long id){
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cuenta no encontrada"));
        accountRepository.delete(account);
        restTemplate.postForLocation("http://localhost:8085/api/clients/decrementAccountCount/" + account.getClient().getId(), null);
    }
}
