package com.example.entityConfig;

import java.util.function.Predicate;

import com.example.entity.Message;

public class MessageConfig {
    static final int MAX_MESSAGE_TEXT_LENGTH = 255;

    public static boolean messageIsValid(Message message, Predicate<Integer> verifyingPosted_byBy) {
        return messageIsValid(message) && verifyingPosted_byBy.test(message.getPosted_by());
    }

    public static boolean messageIsValid(Message message) {
        String messageText = message.getMessage_text();
        return messageText != null
            && !messageText.isBlank()
            && messageText.length() < MAX_MESSAGE_TEXT_LENGTH;
    }
}
