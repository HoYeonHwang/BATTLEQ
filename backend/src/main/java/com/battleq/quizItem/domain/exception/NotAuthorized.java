package com.battleq.quizItem.domain.exception;

public class NotAuthorized extends Exception{
    public NotAuthorized(){

    }
    public NotAuthorized(String message) {
        super(message);
    }
}
