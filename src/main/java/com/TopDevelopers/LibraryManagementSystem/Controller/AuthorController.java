package com.TopDevelopers.LibraryManagementSystem.Controller;

import com.TopDevelopers.LibraryManagementSystem.DTO.AuthorAddRequest;
import com.TopDevelopers.LibraryManagementSystem.Entity.Author;
import com.TopDevelopers.LibraryManagementSystem.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController
{
    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public String addAuthor(@RequestBody AuthorAddRequest authorAddRequest)
    {
        authorService.addAuthor(authorAddRequest);
        return "Author has been added";
    }

    @GetMapping("/get_all_authors")
    public List<Author> getAllAuthors()
    {
        return authorService.getAllAuthors();
    }
}
