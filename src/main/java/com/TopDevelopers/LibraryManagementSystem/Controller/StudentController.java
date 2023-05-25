package com.TopDevelopers.LibraryManagementSystem.Controller;

import com.TopDevelopers.LibraryManagementSystem.DTO.StudentAddRequestDto;
import com.TopDevelopers.LibraryManagementSystem.DTO.StudentEmailUpdateRequestDto;
import com.TopDevelopers.LibraryManagementSystem.DTO.StudentGetByNameResponseDto;
import com.TopDevelopers.LibraryManagementSystem.DTO.StudentResponseToUpdateEmail;
import com.TopDevelopers.LibraryManagementSystem.Entity.Student;
import com.TopDevelopers.LibraryManagementSystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController<student>
{
    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public String addStudent(@RequestBody StudentAddRequestDto studentAddRequestDto)
    {
        studentService.addStudent(studentAddRequestDto);
        return "Student has been added successfully";
    }

    @GetMapping("/findbyemail")
    public String findStudentByEmail(@RequestParam("email") String email)
    {
        return studentService.findStudentByEmail(email);
    }


    @PutMapping("/update_email")
    public StudentResponseToUpdateEmail updateEmail(@RequestBody StudentEmailUpdateRequestDto studentEmailUpdateRequestDto)
    {
        return studentService.updateEmail(studentEmailUpdateRequestDto);
    }

    @GetMapping("/all_students")
    public List<String> getAllStudent()
    {
        return studentService.getAllStudent();
    }

    @GetMapping("/student_by_name")
    public StudentGetByNameResponseDto getStudentByName(@RequestParam("name") String studentName)
    {
        return studentService.getStudentByName(studentName);
    }
}
