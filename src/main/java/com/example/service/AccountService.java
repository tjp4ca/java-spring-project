package com.example.service;

import com.example.entity.Account;
import com.example.entity.Message;

import com.example.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountService {

    AccountRepository accountRepository;

    @Autowired
    private AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    // Register
    public Account addAccount(Account account){
        // check if the username exists
        if (accountRepository.existsByUsername(account.getUsername())) {
            return null;
        }

        // create a new account
        Account registeredAccount = new Account();
        registeredAccount.setUsername(account.getUsername());
        registeredAccount.setPassword(account.getPassword());

        accountRepository.save(registeredAccount);
        return registeredAccount;

    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    // Login
    public Account verifyLogin(Account account) {
        Account loggedinAccount = accountRepository.findAccountByUsername(account.getUsername());

        if (loggedinAccount == null){
            return null;
        }

        if (loggedinAccount.getPassword().equals(account.getPassword())){
            return loggedinAccount;
        }
        else {
            return null;
        }


    }
}
