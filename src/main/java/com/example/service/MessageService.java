package com.example.service;

import com.example.entity.Account;
import com.example.entity.Message;

import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    AccountRepository accountRepository;
    MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    // Create Message
    public Message createMessage(Message message){
        Account account = accountRepository.findAccountByAccountId(message.getPostedBy());
        if (account == null){
            return null;
        } 

        return messageRepository.save(message);

    }

    // Get All Messages
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    // Get Message By Id
    public Message getMessageById(Integer messageId){

        try {
            Optional<Message> message = messageRepository.findById(messageId);

            if (message.isEmpty()) {
                return null;
            }
            
            return message.get();
            
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }

    }

    // Delete Message
    public Optional<Message> deleteMessageById(Integer messageId){
        Optional<Message> deletedMessage = messageRepository.findById(messageId);
        System.out.println("here");
        if (deletedMessage.isPresent()){
            messageRepository.deleteById(messageId);
            return deletedMessage;
        } else{
            return null;
        }

    }

    // Update Message
    public Message updateMessageById(Integer messageId, String updatedMessage){
        Optional<Message> updateMessage = messageRepository.findById(messageId);

        if (updateMessage.isEmpty()){
            return null;
        }

        Message message = updateMessage.get();
        message.setMessageText(updatedMessage);

        return messageRepository.save(message);

    }

    // Get All Messages By Account Id
    public List<Message> getAllMessagesByAccountId(Integer accountId){
        return messageRepository.findByPostedBy(accountId);
    }
}
