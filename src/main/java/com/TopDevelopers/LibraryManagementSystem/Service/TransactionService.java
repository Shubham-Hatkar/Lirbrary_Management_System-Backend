package com.TopDevelopers.LibraryManagementSystem.Service;

import com.TopDevelopers.LibraryManagementSystem.DTO.IssueBookRequest;
import com.TopDevelopers.LibraryManagementSystem.DTO.IssueBookResponse;
import com.TopDevelopers.LibraryManagementSystem.DTO.ReturnBookRequestDto;
import com.TopDevelopers.LibraryManagementSystem.DTO.ReturnBookResponseDto;
import com.TopDevelopers.LibraryManagementSystem.Entity.Book;
import com.TopDevelopers.LibraryManagementSystem.Entity.LibraryCard;
import com.TopDevelopers.LibraryManagementSystem.Entity.Transaction;
import com.TopDevelopers.LibraryManagementSystem.Enum.CardStatus;
import com.TopDevelopers.LibraryManagementSystem.Enum.TransactionStatus;
import com.TopDevelopers.LibraryManagementSystem.Repository.BookRepository;
import com.TopDevelopers.LibraryManagementSystem.Repository.LibraryCardRepository;
import com.TopDevelopers.LibraryManagementSystem.Repository.TransactionRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService
{
    @Autowired
    LibraryCardRepository libraryCardRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public IssueBookResponse issueBook(IssueBookRequest issueBookRequest) throws Exception {

        Transaction transaction = new Transaction();
        transaction.setTransactionNo(String.valueOf(UUID.randomUUID()));
        transaction.setIssueOperation(true);


        LibraryCard card;
        try
        {
            card = libraryCardRepository.findById(issueBookRequest.getCardId()).get();
        }
        catch (Exception e)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Invalid card ID");
            transactionRepository.save(transaction);
            throw new Exception("Invalid card ID");
        }

        Book book;
        try
        {
            book = bookRepository.findById(issueBookRequest.getBookId()).get();
        }
        catch(Exception e)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Invalid book ID");
            transactionRepository.save(transaction);
            throw new Exception("Invalid book ID");
        }

        // both card and book are valid
        transaction.setBook(book);
        transaction.setCard(card);

        if(card.getStatus() != CardStatus.ACTIVATED)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("your card is not activated");
            transactionRepository.save(transaction);
            throw new Exception("your card is not activated");
        }
        if(book.isIssued() == true)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Sorry! this book is already issued");
            transactionRepository.save(transaction);
            throw new Exception("Sorry! this book is already issued");
        }

        // I can issue book
        book.setIssued(true);
        book.setCard(card);
        book.getTransactions().add(transaction);
        card.getTransactionList().add(transaction);
        card.getBooksIssued().add(book);

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setMessage("Book is issued successfully.");

        libraryCardRepository.save(card); // this will save book and transaction also

        // responseDto
        IssueBookResponse issueBookResponse = new IssueBookResponse();
        issueBookResponse.setTransactionId(transaction.getTransactionNo());
        issueBookResponse.setBookName(book.getTitle());
        issueBookResponse.setTransactionStatus(TransactionStatus.SUCCESS);

        return issueBookResponse;
    }

    public ReturnBookResponseDto returnBook(ReturnBookRequestDto returnBookRequestDto) throws Exception
    {
        Transaction transaction = new Transaction();
        transaction.setTransactionNo(String.valueOf(UUID.randomUUID()));
        transaction.setIssueOperation(false); // this is for returning book

        LibraryCard card;
        try
        {
            card = libraryCardRepository.findById(returnBookRequestDto.getCardId()).get();
        }
        catch (Exception e)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Invalid card ID");
            transactionRepository.save(transaction);
            throw new Exception("Invalid card ID");
        }

        Book book;
        try
        {
            book = bookRepository.findById(returnBookRequestDto.getBookId()).get();
        }
        catch(Exception e)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Invalid book ID");
            transactionRepository.save(transaction);
            throw new Exception("Invalid book ID");
        }

        if(card.getStatus() != CardStatus.ACTIVATED)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("your card is not activated");
            transactionRepository.save(transaction);
            throw new Exception("your card is not activated");
        }
        if(book.isIssued() == false)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Ohh! this book is already returned");
            transactionRepository.save(transaction);
            throw new Exception("Ohh! this book is already returned");
        }

        // i can return a book
        book.setIssued(false); // returning book
        book.setCard(card);
        book.getTransactions().add(transaction);
        card.getTransactionList().add(transaction);
        card.getBooksIssued().remove(book);

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setMessage("Book has been returned successfully");

        libraryCardRepository.save(card);

        ReturnBookResponseDto returnBookResponseDto = new ReturnBookResponseDto();
        returnBookResponseDto.setBookName(book.getTitle());
        returnBookResponseDto.setTransactionId(transaction.getTransactionNo());
        returnBookResponseDto.setTransactionStatus(TransactionStatus.SUCCESS);
        return returnBookResponseDto;
    }
}
