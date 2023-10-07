package com.idb.tasks.exceptions;

public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException() {
        super("No data found with credential");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
