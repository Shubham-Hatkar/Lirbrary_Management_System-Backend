package com.TopDevelopers.LibraryManagementSystem.Exceptions;

public class StudentIdDoesNotExistException extends Exception{
    public StudentIdDoesNotExistException()
    {
        super("Please enter valid student Id");
    }
}
