package com.example.serhii.ubrainianstest.exceptions;

/**
 * Created by Serhii on 08.02.2018.
 */

public class IncorrectUrlException extends Exception {

    private final String description;

    public IncorrectUrlException(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

}
