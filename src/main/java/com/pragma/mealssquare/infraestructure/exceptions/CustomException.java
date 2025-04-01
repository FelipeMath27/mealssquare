package com.pragma.mealssquare.infraestructure.exceptions;

public class CustomException extends RuntimeException{
    public CustomException (String message){
        super(message);
    }
}
