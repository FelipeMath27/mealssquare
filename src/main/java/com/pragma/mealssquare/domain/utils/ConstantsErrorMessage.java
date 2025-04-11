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

    public static final String START_FLOW = "Start flow";
    public static final String START_VALIDATE_CREATOR_USER = "Start to validate creator user";
    public static final String START_VALIDATE_OWNER = "Start to validate owner user";
    public static final String START_PROCESS_TO_VALIDATE_CONDITION = "Start process to validate requirements";

    /**Microservices*/
    public static final String CANT_CONNECT_MICROSERVICES = "CAN'T CONNECT TO THE MICROSERVICES";

    /**Price dish*/
    public static final double MIN_PRICE_DISH = 0;
    public static final String PRICE_MUST_BE_GREATER_THAN = "The price must be greater than {}" + MIN_PRICE_DISH;

    /**Restaurant*/
    public static final String RESTAURANT_NOT_FOUND = "This restaurant doesn't exist in the system";

}
