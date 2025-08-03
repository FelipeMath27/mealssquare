package com.pragma.mealssquare.domain.validator;

import com.pragma.mealssquare.domain.model.TypeRolEnum;
import com.pragma.mealssquare.domain.model.User;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import com.pragma.mealssquare.infraestructure.exceptions.CustomException;

import java.util.Optional;

public class ValidatorClasses {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.com(\\.co)?$";
    private static final String PHONE_REGEX = "^\\+57\\d{10}$";
    private static final String NUMERIC_NIT_REGEX = "^\\d+$";
    private static final String RESTAURANT_NAME_REGEX = "^(?!\\d+$).+";

    private ValidatorClasses(){}

    /** This method sanitized any string*/
    public static Optional<String> sanitize(String input) {
        return Optional.ofNullable(input).map(String::trim).
                filter(s -> !s.isEmpty());
    }

    /**Regex to validate the email format*/
    public static Optional<String> validateEmail(String email){
        return sanitize(email)
                .filter(e -> e.matches(EMAIL_REGEX));
    }

    /**Regex to validate the phone number format*/
    public static Optional<String> validatePhoneNumber(String phoneNumber){
        return sanitize(phoneNumber)
                .filter(e->e.matches(PHONE_REGEX));
    }

    /**Regex to validate the NIT number format*/
    public static Optional<String> validateNitNumber(String nit){
        return sanitize(nit)
                .filter(e ->e.matches(NUMERIC_NIT_REGEX));
    }

    /**Regex to validate the restaurant name format*/
    public static Optional<String> validateRestaurantName(String name){
        return sanitize(name)
                .filter(n -> n.matches(RESTAURANT_NAME_REGEX));
    }


    public static void validateAdminCreator(User user) {
        Optional.ofNullable(user)
                .filter(u -> u.getRol() != null && TypeRolEnum.ADMIN.name().equals(u.getRol().getNameRol()))
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.PERMISSION_DENIED));
    }

    public static void validateOwner(User user) {
        if (user.getRol() == null || !TypeRolEnum.OWNER.name().equals(user.getRol().getNameRol())) throw new CustomException(ConstantsErrorMessage.IS_NOT_OWNER_ROLE);
    }

    public static double validatePriceDish(double price){
        return Optional.of(price)
                .filter(pr -> pr > ConstantsErrorMessage.MIN_PRICE_DISH)
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.PRICE_MUST_BE_GREATER_THAN));
    }

}
