package com.TopDevelopers.LibraryManagementSystem.Exceptions;

public class StudentNotFoundException extends Exception
{
    public StudentNotFoundException()
    {
        super("Sorry! Student Not found");
    }
}
