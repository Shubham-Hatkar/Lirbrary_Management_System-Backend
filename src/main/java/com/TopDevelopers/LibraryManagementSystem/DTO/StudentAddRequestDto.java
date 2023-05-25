package com.TopDevelopers.LibraryManagementSystem.DTO;

import com.TopDevelopers.LibraryManagementSystem.Enum.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentAddRequestDto
{
    private String name;
    private String email;
    private int age;
    private Department department;
}
