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
        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    /**
    public Account verifyLogin(String username, String password) {
        Account account = accountDAO.getLoginCredentials(username, password);
        return account;
    }
    */
}
