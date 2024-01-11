package com.example.controller;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @RestController
 public class SocialMediaController {
     @Autowired(required = true)
     MessageService messageService;
     
     @Autowired(required = true)
     AccountService accountService;

    // Endpoint Handler for Account Registration
     @PostMapping("/register")
     public ResponseEntity<Account> registerHandler(@RequestBody Account account){
        if (account.getUsername().trim().isEmpty() || account.getPassword().length()<4){
            return ResponseEntity.status(400).body(null);
        }
        Account newAccount =accountService.addAccount(account);
        if (newAccount !=null){
            return ResponseEntity.status(200).body(newAccount);
        }else{
            return ResponseEntity.status(409).body(null);
        }
    }
    
    //  Endpoints Handlers for Logining into and getting userAccount
     @PostMapping("/login")
     ResponseEntity<Account> loginUsingPostRequest(@RequestBody Account account) {
         Account acc = accountService.login(account);
         if (acc != null) {
             return ResponseEntity.ok(acc);
         } else {
             return ResponseEntity.status(401).build();
         }
    }


     @GetMapping("/login")
     ResponseEntity<Account> login(@RequestBody Account account) {
         Account acc = accountService.login(account);
         if (acc != null) {
             return ResponseEntity.ok(acc);
         } else {
             return ResponseEntity.status(401).build();
         }
    }
     
     // Account or Login Credentials in the Requestparams are required.
     @GetMapping("/login-with-args")
     ResponseEntity<Account> login(@RequestParam String username, @RequestParam String password) {
         Account acc = accountService.login(username, password);
         if (acc != null) {
             return ResponseEntity.ok(acc);
         } else {
             return ResponseEntity.status(401).build();
         }
    }
 
      // Endponits Handlers for Posting and Retriving Message(s)
     @PostMapping("/messages")
     public ResponseEntity<Message> postMessageHandler(@RequestBody Message message){
        Message postMessage = messageService.postMessage(message);
        if (postMessage != null){
         return ResponseEntity.status(200).body(postMessage);
        }else{
         return ResponseEntity.status(400).build();
        }
    }
 
     @GetMapping("messages")
     List<Message> getAllMessages() {
         return messageService.getAllMessages();
    }
 
     @GetMapping("/messages/{message_id}")
     Message getMessageByID(@PathVariable("message_id") int messageId) {
         return messageService.findMessageById(messageId);
    }
 
     @GetMapping("/accounts/{account_id}/messages")
     List<Message> getMessageByAccount(@PathVariable("account_id") int accountId) {
         return messageService.findAllMessagesByAUser(accountId);
    }
 
     
    // Endpoint Handler for Partially Updating a given Message by its ID
     @PatchMapping("/messages/{message_id}")
     public ResponseEntity<Integer> patchMessageHandler(@PathVariable int message_id,@RequestBody Message updatedMessage){
        Message message = messageService.patchMessageById(updatedMessage,message_id);
        if (message!=null){
            String[] lines = message.getMessage_text().split("\r|\n");
            return ResponseEntity.status(200).body(lines.length);
        }else{
            return ResponseEntity.status(400).body(0);
        }
    
    }
     
    // Endpoint Handler for Deleting a given Message by its ID
    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable("message_id") Integer messageId) {
        int rowsDeleted = messageService.deleteMessageById(messageId);
        if (rowsDeleted == 0) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.ok(rowsDeleted);
        }
    }
 
}    
    
    

 
   
    
 



 

   



