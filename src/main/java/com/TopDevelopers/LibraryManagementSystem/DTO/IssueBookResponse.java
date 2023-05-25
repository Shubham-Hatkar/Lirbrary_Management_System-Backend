package com.TopDevelopers.LibraryManagementSystem.DTO;

import com.TopDevelopers.LibraryManagementSystem.Enum.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IssueBookResponse
{
    private String transactionId;
    private String bookName;
    private TransactionStatus transactionStatus;
}
