package com.TopDevelopers.LibraryManagementSystem.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentEmailUpdateRequestDto
{
    private int id;
    private String email;
}
