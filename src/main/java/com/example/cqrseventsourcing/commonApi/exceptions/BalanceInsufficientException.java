package com.example.cqrseventsourcing.commonApi.exceptions;

public class BalanceInsufficientException extends RuntimeException {
    public BalanceInsufficientException(String message) {
        super(message);
    }
}