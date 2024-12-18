package com.example.controller;

import com.example.service.AccountService;
import com.example.service.MessageService;

import com.example.entity.Account;
import com.example.entity.Message;

//import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
@RequestMapping("/")
public class SocialMediaController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MessageService messageService;

    // Register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Account account){
        // username is not blank, password is at least 4 characters long
        if (account.getUsername().isEmpty() || account.getPassword().length() < 4){
            return ResponseEntity.status(HttpStatus.valueOf(400))
                    .body("Client error. Make sure usename exists or password is greater than 4");
        }

        Account registeredAccount = accountService.addAccount(account);
        if (registeredAccount == null){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Conflict! Username already exists");
        } else {
            return ResponseEntity.ok(registeredAccount);
        }
        
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account){
        Account loggedinAccount = accountService.verifyLogin(account);

        if (loggedinAccount == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Not authorized to login!");
        } else {
            return ResponseEntity.ok(loggedinAccount);
        }
    }

    // Create Message
    @PostMapping("/messages")
    public ResponseEntity<?> createMessage(@RequestBody Message message){
        if (message.getMessageText().isEmpty() || message.getMessageText().length() > 255){
            return ResponseEntity.status(HttpStatus.valueOf(400))
                    .body("Client error");
        }

        Message createdMessage = messageService.createMessage(message);
        if (createdMessage == null){
            return ResponseEntity.status(HttpStatus.valueOf(400))
                    .body("Client error");
        }
        if (message.getMessageText() == null){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Conflict");
        } else {
            return ResponseEntity.ok(createdMessage);
        }
    }

    // Get All Messages
    @GetMapping("/messages")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    // Get Message By Id
    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable Integer messageId){
        Message message = messageService.getMessageById(messageId);
        //Optional<Message> message = messageService.getMessageById(messageId);

        System.out.println("hello world");

        if (message == null){
        //if (message.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK)
                    .build();
        } else {
            //return ResponseEntity.ok(message.get());
            return ResponseEntity.ok(message);
        }
    }

    // Delete Message
    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<?> deleteMessageById(@PathVariable Integer messageId){

        Optional<Message> deletedMessage = messageService.deleteMessageById(messageId);
        // messageService.deleteMessageById(messageId);

        // return ResponseEntity.accepted()
        //             .body("Successfully deleted");
        System.out.println("deletedMessage");
        if (deletedMessage == null){
            // return ResponseEntity.ok()
            //             .body("Successfully deleted");
            System.out.println("did it get here???");
            return ResponseEntity.status(HttpStatus.OK).build();
            // return ResponseEntity.status(HttpStatus.valueOf(200))
            //             .body("Successfully deleted");

        } else {
            
            System.out.println("here???");
            return ResponseEntity.status(HttpStatus.OK).body("1");
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Message not found");
        }
    }

    // Update Message
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<?> updateMessageById(@PathVariable Integer messageId, @RequestBody Message message){
        if (message.getMessageText().isEmpty() || message.getMessageText().length() > 255){
            // return ResponseEntity.status(HttpStatus.valueOf(400))
            //         .body("Client error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Client error");
        }

        Message updatedMessage = messageService.updateMessageById(messageId, message.getMessageText());
        
        if (updatedMessage == null){
            return ResponseEntity.status(HttpStatus.valueOf(400))
                    .body("Conflict");
        } else {
            return ResponseEntity.status(HttpStatus.OK)
            .body("1");
        }
    }

    // Get All Messages by Account Id
    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByAccountId(@PathVariable Integer accountId) {
        List<Message> messages = messageService.getAllMessagesByAccountId(accountId);

        return ResponseEntity.ok(messages);
    }
}
