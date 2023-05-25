package com.TopDevelopers.LibraryManagementSystem.Service;

import com.TopDevelopers.LibraryManagementSystem.DTO.BookRequestDto;
import com.TopDevelopers.LibraryManagementSystem.DTO.BookResponseDto;
import com.TopDevelopers.LibraryManagementSystem.Entity.Author;
import com.TopDevelopers.LibraryManagementSystem.Entity.Book;
import com.TopDevelopers.LibraryManagementSystem.Exceptions.AuthorNotFoundException;
import com.TopDevelopers.LibraryManagementSystem.Repository.AuthorRepositoty;
import com.TopDevelopers.LibraryManagementSystem.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    AuthorRepositoty authorRepositoty;


    // add book (to author)
    public BookResponseDto addBook(BookRequestDto bookRequestDto) throws AuthorNotFoundException {
        // get the author obj
        Author author;
        try
        {
            author = authorRepositoty.findById(bookRequestDto.getAuthorId()).get();
        }
        catch (Exception e)
        {
            throw new AuthorNotFoundException();
        }
        Book book = new Book();
        book.setTitle(bookRequestDto.getTitle());
        book.setGenre(bookRequestDto.getGenre());
        book.setPrice(bookRequestDto.getPrice());
        book.setIssued(false);
        book.setAuthor(author);

        // author updated
        author.getBooks().add(book);
        authorRepositoty.save(author);

        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setPrice(book.getPrice());
        bookResponseDto.setTitle(book.getTitle());
        return bookResponseDto;
    }

    // Get booklist by author Name
    public List<String> getBookListByAuthorName(String authorName) throws AuthorNotFoundException {
        Author author = authorRepositoty.findByName(authorName);
        if(author == null)
        {
            throw new AuthorNotFoundException();
        }
        List<Book> bookList = author.getBooks();
        List<String> books = new ArrayList<>();
        for(Book book : bookList)
        {
            books.add(book.getTitle());
        }
        return books;
    }
}
