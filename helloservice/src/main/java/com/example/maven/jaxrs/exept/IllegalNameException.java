package com.example.maven.jaxrs.exept;

public class IllegalNameException extends Exception {

    private static final long serialVersionUID = -6647544772732631047L;
    public static IllegalNameException DEFAULT_INSTANCE = new IllegalNameException("personID cannot be 0");
    
    public IllegalNameException(String message) {
        super(message);
    }
}
