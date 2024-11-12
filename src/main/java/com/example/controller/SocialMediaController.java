package com.example.controller;

import com.example.service.AccountService;
// import com.example.service.MessageService;

import com.example.entity.Account;
// import com.example.entity.Message;

//import Lab.Model.Sample;
//import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    // @Autowired
    // private MessageService messageService;

    /**
    @GetMapping("/sample/")
    public Sample getSample(){
        return new Sample(1L, "sample text");
    }
    
     @GetMapping("/string/{text}")
    public String getStringPathVariable(@PathVariable String text){
        return text;
    }

    @GetMapping("/long/{id}")
    public long getPathVariable(@PathVariable int id){
        //you will need to change the method's parameters and return the extracted path variable.
        return id;
    }

    // @PostMapping(value = "/requestbody")
    public Sample postSample(@RequestBody Sample sample){
        //you will need to change the method's parameters and return the extracted request body.
        return sample;
    }
    */

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Account account){
        if (account.getUsername().isEmpty() || account.getPassword().length() < 4){
            return ResponseEntity.status(HttpStatus.valueOf(400))
                    .body("Client error. Make sure usename exists or password is greater than 4");
        }
        Account registered_account = accountService.addAccount(account);
        if (registered_account == null){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Conflict! Username already exists");
        } else{
            return ResponseEntity.ok(registered_account);
        }
        
    }

    /**
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Account account){
        accountService.register(account);
        return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Successfully register");
    }
    */

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account){
        Account found_account = accountService.verifyLogin(account);

        if (found_account == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Not authorized to login!");
        } else{
            return ResponseEntity.ok(found_account);
        }
    }

    /**
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody Account account) throws AuthenticationException {
        accountService.login(account.getUsername(), account.getPassword());
        return ResponseEntity.noContent()
                    .header("username", account.getUsername)
                    .build();
    }
    */

    /**
    @PostMapping("/messages")

    @GetMapping("/messages")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/messages/{messageId}")
    */

}
