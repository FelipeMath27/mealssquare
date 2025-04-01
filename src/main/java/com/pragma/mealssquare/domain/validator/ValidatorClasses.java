package com.pragma.mealssquare.domain.validator;

import com.pragma.mealssquare.infraestructure.exceptions.ConstantsErrorMessage;
import com.pragma.mealssquare.infraestructure.exceptions.CustomException;

public class ValidatorClasses {
    private ValidatorClasses(){}

    public static boolean isNumeric(String value){
        if (value == null){
            throw new CustomException(ConstantsErrorMessage.CANT_BE_NULL);
        }
        return value.matches("^[0-9]+$");
    }

    public static boolean isValidPhone(String value) {
        if(value == null) {
            throw new CustomException(ConstantsErrorMessage.CANT_BE_NULL);
        }
        return value.matches("^\\+?[0-9]{1,13}$");
    }

    public static boolean isValidNameRestaurant(String value){
        if (value == null || value.trim().isEmpty()) {
            throw new  CustomException(ConstantsErrorMessage.CANT_BE_NULL);
        }
        if(value.matches("^[0-9]+$")){
            throw new CustomException(ConstantsErrorMessage.INVALID_RESTAURANT_NAME_FORMAT);
        }
        return true;
    }

    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
