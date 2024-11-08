package com.example.bookstore_api.exception;

public class NoRelevantRecordFoundException extends Exception{
    public NoRelevantRecordFoundException(String message) {
        super(message);
    }
}
