package com.example.service;

import com.example.entity.Account;
import com.example.entity.Message;

import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    AccountRepository accountRepository;
    MessageRepository messageRepository;

    @Autowired
    private MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    // Create Message
    public Message createMessage(Message message){

        //Account account = accountRepository.findById(message.getPostedBy())

        return messageRepository.save(message);

    }

    // Get All Messages
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }
}
