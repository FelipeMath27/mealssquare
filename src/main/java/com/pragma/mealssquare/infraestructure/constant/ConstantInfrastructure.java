package com.pragma.mealssquare.infraestructure.constant;

public class ConstantInfrastructure {
    public static final String ERROR_PROCESS_TOKEN = "Error to process the token";

    //Feign Client
    public static final String START_PROCESS_TO_CREATE_USER_FEIGN = "Start process to create user in Feign Client";
    public static final String START_PROCESS_TO_GET_USER_BY_ID_FEIGN = "Start process to get user by id in Feign Client";
    public static final String START_PROCESS_TO_GET_USER_BY_EMAIL_FEIGN = "Start process to get user by email in Feign Client";
    public static final String ERROR_CREATING_FEIGN_USER = "Error creating Feign user";
    public static final String ERROR_CREATING_USER_FEIGN = "Error creating user in Feign Client";
    public static final String ERROR_GETTING_USER_BY_ID_FEIGN = "Error getting user by id in Feign Client";
    public static final String ERROR_GETTING_FEIGN_USER_BY_ID = "Error getting Feign";
    public static final String ERROR_GETTING_USER_BY_EMAIL_FEIGN = "Error getting user by email in Feign Client";
    public static final String ERROR_GETTING_FEIGN_USER_BY_EMAIL = "Error getting Feign user by email";
    public static final String SENDING_TRACEABILITY_DATA = "Feign client - Sending traceability data to Traceability service";
    public static final String SUCCESSFUL_TRACEABILITY_DATA = "Feign client - Traceability data sent successfully";
    public static final String ERROR_CONNECT_TRACEABILITY_FEIGN = "Error connecting to Traceability microservice via Feign: ";
    public static final String ERROR_TRACEABILITY = "Error creating traceability via Feign: ";

    /** Notification service*/
    public static final String SEND_NOTIFICATION_DATA = "Feign client - Sending notification data to Notification service";
    public static final String SUCCESSFUL_NOTIFICATION_DATA = "Feign client - Notification data sent successfully";
    public static final String ERROR_CONNECT_NOTIFICATION_FEIGN = "Error connecting to Notification microservice via Feign: ";
    public static final String ERROR_NOTIFICATION = "Error sending notification via Feign: ";


    private ConstantInfrastructure() {
    }
}
