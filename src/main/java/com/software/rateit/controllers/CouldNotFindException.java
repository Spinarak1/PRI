package com.software.rateit.controllers;

public class CouldNotFindException extends RuntimeException {
    public CouldNotFindException(Long id){
        super("Item with id " + id + " doesn't exist");
    }
}
