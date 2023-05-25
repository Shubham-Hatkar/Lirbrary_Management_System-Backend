package com.TopDevelopers.LibraryManagementSystem.Exceptions;

public class EmailAlreadyRegisteredException extends Exception
{
    public EmailAlreadyRegisteredException()
    {
        super("Ohh! This email is already registered with other student");
    }
}
