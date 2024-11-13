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

        //Account account = accountRepository.findById(message.getPostedBy())

        return messageRepository.save(message);

    }

    // Get All Messages
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    // Get Message By Id
    public Message getMessageById(Long messageId){

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
    public boolean deleteMessageById(Long messageId){

        try {
            if (messageRepository.existsById(messageId)){
                messageRepository.deleteById(messageId);
                return true;
            } else return false;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }

    }

    // Update Message
    public boolean updateMessageById(Long messageId, String updatedMessage){
        Optional<Message> updateMessage = messageRepository.findById(messageId);

        if (updateMessage.isEmpty()){
            return false;
        }

        Message message = updateMessage.get();
        message.setMessageText(updatedMessage);

        messageRepository.save(message);

        return true;
    }
}
