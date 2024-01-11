package com.example.entityConfig;

import com.example.entity.Account;

public class AccountConfig {
    public static boolean accountIsValid(Account account) {
        String username = account.getUsername();
        String password = account.getPassword();
        return username != null 
            && !username.isBlank() 
            && password != null 
            && password.length() >= 4;
    }
}
