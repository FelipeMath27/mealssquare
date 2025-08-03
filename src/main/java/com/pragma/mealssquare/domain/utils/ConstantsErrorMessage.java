package com.pragma.mealssquare.domain.utils;

public class ConstantsErrorMessage {
    public static final String CANT_BE_EMPTY_LIST = "This list can't be empty";
    public static final String CANT_BE_NULL = "This parameter can't be null";
    public static final String INVALID_RESTAURANT_NAME_FORMAT = "This restaurant format name is invalid";
    public static final String INVALID_DATA_FORMAT = "The parameters has invalid format";
    public static final String ADMIN_NOT_FOUND = "User admin not found in the system";
    public static final String ROL_NOT_FOUND = "Rol not found in the system";
    public static final String NOT_OWNER_ROL= "Owner rol its necessary, this isn't owner rol";
    public static final String PERMISSION_DENIED = "You do not have permission to create users.";
    public static final String INVALID_ROLE = "Invalid role to create de user";
    public static final String IS_NOT_OWNER_ROLE = "Invalid role to create owner restaurant";


    /**Category*/
    public static final String CATEGORY_NOT_FOUND = "Category not found in the system";


    /**Microservices*/
    public static final String CANT_CONNECT_MICROSERVICES = "CAN'T CONNECT TO THE MICROSERVICES";

    /**Dish*/
    public static final double MIN_PRICE_DISH = 0;
    public static final String PRICE_MUST_BE_GREATER_THAN = "The price must be greater than {}" + MIN_PRICE_DISH;
    public static final String CANT_SAVE_DISH = "Can't persist the new dish in the system";
    public static final String DISH_NOT_FOUND = "This dish don't found in the system";
    public static final String DISH_NAME_CANT_BE_NULL = "Dish name can't be null";
    public static final String DISH_DESCRIPTION_CANT_BE_NULL = "Dish description can't be null";
    public static final String DISH_URL_CANT_BE_NULL = "Dish url can't be null";

    /**Restaurant*/
    public static final String RESTAURANT_NOT_FOUND = "This restaurant doesn't exist in the system";
    public static final String RESTAURANT_NOT_SAVED = "Restaurant not saved in the system";
    public static final String INVALID_FORMAT_NIT = "NIT has an invalid format";
    public static final String INVALID_FORMAT_PHONE = "Phone has an invalid format";


    /**Constants to logs*/
    public static final String START_FLOW = "Start flow";
    public static final String START_VALIDATE_CREATOR_USER = "Start to validate creator user";
    public static final String START_VALIDATE_OWNER = "Start to validate owner user";
    public static final String END_VALIDATE_OWNER = "End to validate owner user";
    public static final String START_PROCESS_TO_VALIDATE_CONDITION = "Start process to validate requirements";
    public static final String END_SUCCESSFUL_FLOW = "End flow successful";
    public static final String START_TO_CREATE_OWNER = "Start process to create a new owner";
    public static final String LISTENER_OK_CONTROLLER = "Controller listen to the HTTP method OK";
    public static final String VALIDATE_EXIST_DISH = "Start flow to validate if the dish exists in the system";


    /**Authentication constant*/

    public static final String START_AUTHENTICATION_FLOW = "Handler get the request to authentication";
    public static final String ERROR_TO_VERIFY_PASSWORD = "Incorrect password, try again";
    public static final String ERROR_TO_GENERATE_TOKEN = "There is a problem to generate a new token";
    public static final String EXPIRED_TOKEN = "Token has expired";
    public static final String ERROR_TO_VALIDATE_TOKEN = "There is a error to validate token JWT";
    public static final String VALID_TOKEN = "This JWT token is valid";
    public static final String CONSTANT_HEADER_AUTHENTICATION = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final int BEARER_SUBSTRING = 7;
    public static final String UNAUTHORIZED_OPERATION = "unauthorized to do this operation";
    public static final String USER_NOT_FOUD = "User not found in the system";

}
