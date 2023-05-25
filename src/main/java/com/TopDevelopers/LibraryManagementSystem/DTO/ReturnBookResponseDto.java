package com.TopDevelopers.LibraryManagementSystem.DTO;

import com.TopDevelopers.LibraryManagementSystem.Enum.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReturnBookResponseDto
{
    private String transactionId;
    private String bookName;
    private TransactionStatus transactionStatus;
}
