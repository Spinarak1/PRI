package com.software.rateit.controllers;

public class CouldNotFindException extends RuntimeException {
    public CouldNotFindException(Long id){
        super("Item with id " + id + " doesn't exist");
    }
    public CouldNotFindException(String stageName){
        super("Artist with name " + stageName + " doesn't exist");
    }
}
