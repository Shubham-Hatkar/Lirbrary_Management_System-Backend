package com.TopDevelopers.LibraryManagementSystem.Controller;

import com.TopDevelopers.LibraryManagementSystem.DTO.BookRequestDto;
import com.TopDevelopers.LibraryManagementSystem.DTO.BookResponseDto;
import com.TopDevelopers.LibraryManagementSystem.Entity.Book;
import com.TopDevelopers.LibraryManagementSystem.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController
{
    @Autowired
    BookService bookService;

    // add book
    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody BookRequestDto bookRequestDto) {

        try
        {
            BookResponseDto bookResponseDto = bookService.addBook(bookRequestDto);
            return new ResponseEntity(bookResponseDto, HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.ACCEPTED);
        }
    }

    // get booklist by author name
    @GetMapping("/get_books_by_authorname")
    public ResponseEntity getBookListByAuthorName(@RequestParam("name") String authorName)
    {
        List<String> bookList;
        try
        {
            bookList = bookService.getBookListByAuthorName(authorName);
            //return new ResponseEntity(bookList,HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(bookList,HttpStatus.FOUND);
    }
}
