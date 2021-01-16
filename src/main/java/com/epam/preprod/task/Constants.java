package com.epam.preprod.task;

public class Constants {

    private Constants() {
    }

    // General config
    public static final int MESSAGE_WAITING_TIME = 100;
    public static final String KAFKA_PORT = "localhost:9092";
    public static final String TOPIC_NAME = "fibonacci";

    // Consumer config
    public static final int MAX_NO_MESSAGE_NUMBER = 10;
    public static final int MAX_POLL_RECORDS = 1;
    public static final String AUTO_COMMIT_CONFIG = "false";
    public static final String GROUP_ID_CONFIG = "consumerGroup1";

    // Other constants
    public static final int ARG_FIRST_N_FIBONACCI_NUMBERS = 0;
    public static final int ARG_FIRST_M_FIBONACCI_NUMBERS = 1;
    public static final int MAX_QUANTITY_NUMBERS = 90;
    public static final int MIN_QUANTITY_COMMAND_LINE_ARGS = 2;
    public static final int MIN_QUANTITY_NUMBERS = 1;
    public static final String CONSUMER_OUTPUT_FORMAT = "Consumer -> number: %d, value: %d";
    public static final String CONSUMER_OUTPUT_SUM = "Consumer -> Sun m Fibonacci numbers: %d";
    public static final String PRODUCER_OUTPUT_FORMAT = "Producer -> number: %d, value: %d";

    // Exception messages
    public static final String EXCEPTION_N_LESS_M = "Number %d is less than %d";
    public static final String EXCEPTION_OUT_OF_RANGE = "Out of range (%d-%d): %d";
    public static final String EXCEPTION_WRONG_COMMAND_LINE_ARGS = "Wrong command line arguments";


}
