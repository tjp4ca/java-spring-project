package com.example.controller;

import com.example.service.AccountService;
import com.example.service.MessageService;

import com.example.entity.Account;
import com.example.entity.Message;

//import Lab.Model.Sample;
//import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
@RequestMapping("/api")
public class SocialMediaController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MessageService messageService;

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

    @PostMapping(value = "/requestbody")
    public Sample postSample(@RequestBody Sample sample){
        //you will need to change the method's parameters and return the extracted request body.
        return sample;
    }
    */

    @PostMapping(value = "/register")
    public Account postAccount(@RequestBody Account account){
        return account;
    }

    @PostMapping(value = "/login")
    public Account loginAccount(@RequestBody Account account){
        return account;
    }

    /**
    @PostMapping("/messages")

    @GetMapping("/messages")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/messages/{messageId}")
    */

}
