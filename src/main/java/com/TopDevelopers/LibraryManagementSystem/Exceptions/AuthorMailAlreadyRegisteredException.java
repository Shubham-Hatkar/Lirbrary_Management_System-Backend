package com.TopDevelopers.LibraryManagementSystem.Exceptions;

public class AuthorMailAlreadyRegisteredException extends Exception{
    public AuthorMailAlreadyRegisteredException()
    {
        super("This email is already registered with other author");
    }
}
