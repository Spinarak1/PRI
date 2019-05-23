package com.software.rateit.controllers;

import java.util.Date;

public class CouldNotFindException extends RuntimeException {
    public CouldNotFindException(Long id){
        super("Item with id " + id + " doesn't exist");
    }
    public CouldNotFindException(String stageName){
        super("Object with name " + stageName + " doesn't exist");
    }
    public CouldNotFindException(Date released){
        super("Couldn't find CD with date released "+ released);
    }
}
