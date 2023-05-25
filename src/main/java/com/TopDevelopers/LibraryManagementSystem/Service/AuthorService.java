package com.TopDevelopers.LibraryManagementSystem.Service;

import com.TopDevelopers.LibraryManagementSystem.DTO.AuthorAddRequest;
import com.TopDevelopers.LibraryManagementSystem.Entity.Author;
import com.TopDevelopers.LibraryManagementSystem.Repository.AuthorRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService
{
    @Autowired
    AuthorRepositoty authorRepositoty;

    public void addAuthor(AuthorAddRequest authorAddRequest)
    {
        Author author = new Author();
        author.setAge(authorAddRequest.getAge());
        author.setName(authorAddRequest.getName());
        author.setEmail(authorAddRequest.getEmail());
        author.setMobNo(authorAddRequest.getMobNo());

        authorRepositoty.save(author);
    }

    public List<String> getAllAuthors()
    {
        List<Author> authors =  authorRepositoty.findAll();
        List<String> authorList = new ArrayList<>();
        for(Author author : authors)
        {
            authorList.add(author.getName());
        }
        return authorList;
    }
}
