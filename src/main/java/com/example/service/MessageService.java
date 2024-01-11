package com.example.service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired(required = true)
    MessageRepository messageRepository;

    @Autowired(required = true)
    AccountRepository accountRepository;
    public Message postMessage(Message message){
        if (message.getMessage_text().trim().equals("") || message.getMessage_text().length()>254){
            return null;
        }
        List<Account> account = accountRepository.findAll();
        for (Account checkedAcount : account){
            if (checkedAcount.getAccount_id().equals(message.getPosted_by())){
                return messageRepository.save(message);
            }
        }
        
            return null;
    }

    public List<Message> getAllMessages() {
        return messageRepository.getAllMessages();
    }

    public Message findMessageById(int id) {
        return messageRepository.getMessageByID(id);
    }
    
    public int deleteMessageById(Integer messageId) {
        Optional<Message> message = messageRepository.findById(messageId);
        if (message.isPresent()) {
            messageRepository.deleteById(messageId);
            return 1;
        } else {
            return 0;
        }
    }
    
    public Message patchMessageById(Message message, int message_id){
        if (message.getMessage_text().trim()=="" || message.getMessage_text().length()>254){
            return null;
        }
        Optional<Message> optionalMessage = messageRepository.findById(message_id);
        if (optionalMessage.isPresent()){
            Message updatedMessage = optionalMessage.get();
            updatedMessage.setMessage_text(message.getMessage_text());
            return messageRepository.save(updatedMessage);
        }else{
            return null;
        }
    }

    public List<Message> findAllMessagesByAUser(int account_id) {
        return messageRepository.getMessageByAccount(account_id);
    }

}



