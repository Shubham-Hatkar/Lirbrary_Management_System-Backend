package com.TopDevelopers.LibraryManagementSystem.Controller;

import com.TopDevelopers.LibraryManagementSystem.DTO.IssueBookRequest;
import com.TopDevelopers.LibraryManagementSystem.DTO.IssueBookResponse;
import com.TopDevelopers.LibraryManagementSystem.DTO.ReturnBookRequestDto;
import com.TopDevelopers.LibraryManagementSystem.DTO.ReturnBookResponseDto;
import com.TopDevelopers.LibraryManagementSystem.Repository.TransactionRepository;
import com.TopDevelopers.LibraryManagementSystem.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController
{

    @Autowired
    TransactionService transactionService;

    // Issue Book
    @PostMapping("/issue")
    public ResponseEntity issueBook(@RequestBody IssueBookRequest issueBookRequest)
    {
        IssueBookResponse issueBookResponse;
        try
        {
            issueBookResponse = transactionService.issueBook(issueBookRequest);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(issueBookResponse, HttpStatus.ACCEPTED);
    }

    // Return Book
    @PostMapping("/return")
    public ResponseEntity returnBook(@RequestBody ReturnBookRequestDto returnBookRequestDto)
    {
        ReturnBookResponseDto returnBookResponseDto;
        try
        {
            returnBookResponseDto = transactionService.returnBook(returnBookRequestDto);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(returnBookResponseDto,HttpStatus.ACCEPTED);
    }
}
