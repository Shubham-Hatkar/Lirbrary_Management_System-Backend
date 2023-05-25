package com.TopDevelopers.LibraryManagementSystem.Exceptions;

public class NoStudentFoundWithThisEmailException extends Exception{
    public NoStudentFoundWithThisEmailException()
    {
        super("There is no student with this email id");
    }
}
