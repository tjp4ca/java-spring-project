package com.example.controller;

import com.example.service.AccountService;
import com.example.service.MessageService;

import com.example.entity.Account;
import com.example.entity.Message;

//import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        } else{
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
        } else{
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
        if (message.getMessageText() == null){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Conflict");
        } else{
            return ResponseEntity.ok(createdMessage);
        }
    }

    // Get All Messages
    @GetMapping("/messages")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    /**
    @GetMapping("/messages/{messageId}")
    */

}
