package com.example.AccountMs.controller;

import com.example.AccountMs.dto.AccountDTO;
import com.example.AccountMs.model.Account;
import com.example.AccountMs.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("cuentas")
    public Account addAccount(@Validated @RequestBody AccountDTO accountDTO) {
        System.out.println("clientId recibido: " + accountDTO.getClienteId());

        return accountService.createAccount(accountDTO);
    }
    @GetMapping("cuentas")
    public List<Account> listAccounts(@RequestHeader Map<String, String> headers) {
        return accountService.listAccounts();
    }
        @GetMapping("cuentas/{id}")
    public Account getAccountById(@RequestHeader Map<String, String> headers, @PathVariable("id") Long id){
          return accountService.getAccountById(id);
    }
    @PutMapping("cuentas/{id}/depositar")
    public Account deposit( @PathVariable("id") Long id, @RequestBody AccountDTO balanceRequest){
        return accountService.makeDeposit(id, balanceRequest.getBalanceAmount());
    }

    @PutMapping("cuentas/{id}/retirar")
    public Account withdraw(@PathVariable("id") Long id, @RequestBody AccountDTO balanceRequest){
        return accountService.withDrawMoney(id, balanceRequest.getBalanceAmount());
    }
    @DeleteMapping("/cuentas/{id}")
    public void deleteAccount(@PathVariable("id") Long id){
        accountService.deleteAccount(id);
    }
}
