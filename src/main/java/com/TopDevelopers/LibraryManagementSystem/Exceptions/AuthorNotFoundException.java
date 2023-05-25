package com.TopDevelopers.LibraryManagementSystem.Exceptions;

public class AuthorNotFoundException extends Exception{

    public AuthorNotFoundException()
    {
        super("You might entered wrong author id / name..we didnt get any author with this id");
    }
}
