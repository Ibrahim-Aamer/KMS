package com.example.kms;

public class QuantityIsZero extends Exception{
    public QuantityIsZero(String errorMessage) {
        super(errorMessage);
    }
}
