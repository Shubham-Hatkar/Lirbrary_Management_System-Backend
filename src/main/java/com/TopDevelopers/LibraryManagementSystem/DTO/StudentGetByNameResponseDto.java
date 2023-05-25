package com.TopDevelopers.LibraryManagementSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentGetByNameResponseDto
{
    private String name;
    private int age;
    private String email;
    private String department;
}
