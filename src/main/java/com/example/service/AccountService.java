package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class AccountService {
    @Autowired(required = true)
    private AccountRepository accountRepository;

    public Account addAccount(Account account){
        
        List<Account> listOfAccount= accountRepository.findAll();
        for (Account checkAccount : listOfAccount){
            if (checkAccount.getUsername().equals(account.getUsername())){
                return null;
            }
        }
        return (Account)accountRepository.save(account);
    }
    public Account verifiedAccount (Account account){
        List<Account> listOfAccount= accountRepository.findAll();
        for (Account checkedAccount : listOfAccount){
            if (checkedAccount.getUsername().equals(account.getUsername()) && checkedAccount.getPassword().equals(account.getPassword())){
                return checkedAccount;
            }
        }
        return null;

    }

    public Account login(Account accountInfo) {
        return accountRepository.login(accountInfo);
    }

    public Account login(String username, String password) {
        return accountRepository.login(username, password);
    }

}


