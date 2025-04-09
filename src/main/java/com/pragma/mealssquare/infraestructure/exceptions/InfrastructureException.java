package com.pragma.mealssquare.infraestructure.exceptions;

public class InfrastructureException extends RuntimeException{
    public InfrastructureException(String message, Throwable cause){
        super(message, cause);
    }
}
