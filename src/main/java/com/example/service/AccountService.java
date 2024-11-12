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

    public Account addAccount(Account account){
        // Check if the username exists
        if (accountRepository.existsByUsername(account.getUsername())) {
            return null; // Username already exists
        }

        // Create a new account object and set the properties
        Account new_account = new Account();
        new_account.setUsername(account.getUsername());
        new_account.setPassword(account.getPassword());

        // Save the account to the database
        accountRepository.save(new_account);
        return new_account;

    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public Account verifyLogin(Account account) {
        Account found_account = accountRepository.findAccountByUsername(account.getUsername());

        if (found_account == null){
            return null;
        }

        if (found_account.getPassword().equals(account.getPassword())){
            return found_account;
        }
        else {
            return null;
        }


    }
}
