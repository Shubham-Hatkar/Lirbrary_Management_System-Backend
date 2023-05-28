package com.TopDevelopers.LibraryManagementSystem.Controller;

import com.TopDevelopers.LibraryManagementSystem.DTO.StudentAddRequestDto;
import com.TopDevelopers.LibraryManagementSystem.DTO.StudentEmailUpdateRequestDto;
import com.TopDevelopers.LibraryManagementSystem.DTO.StudentGetByNameResponseDto;
import com.TopDevelopers.LibraryManagementSystem.DTO.StudentResponseToUpdateEmail;
import com.TopDevelopers.LibraryManagementSystem.Entity.LibraryCard;
import com.TopDevelopers.LibraryManagementSystem.Entity.Student;
import com.TopDevelopers.LibraryManagementSystem.Repository.LibraryCardRepository;
import com.TopDevelopers.LibraryManagementSystem.Service.LibraryCardService;
import com.TopDevelopers.LibraryManagementSystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController<student>
{
    @Autowired
    StudentService studentService;

    @Autowired
    LibraryCardRepository libraryCardRepository;


    @PostMapping("/add")
    public String addStudent(@RequestBody StudentAddRequestDto studentAddRequestDto)
    {
        try{
            studentService.addStudent(studentAddRequestDto);
            return "Student has been added successfully";
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
    }

    @GetMapping("/findbyemail")
    public String findStudentByEmail(@RequestParam("email") String email)
    {
        try
        {
            return studentService.findStudentByEmail(email);
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
    }


    @PutMapping("/update_email")
    public ResponseEntity updateEmail(@RequestBody StudentEmailUpdateRequestDto studentEmailUpdateRequestDto)
    {
        try{
            StudentResponseToUpdateEmail studentResponseToUpdateEmail = studentService.updateEmail(studentEmailUpdateRequestDto);
            return new ResponseEntity(studentResponseToUpdateEmail, HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("/all_students")
    public List<String> getAllStudent()
    {
        return studentService.getAllStudent();
    }

    @GetMapping("/student_by_name")
    public ResponseEntity getStudentByName(@RequestParam("name") String studentName)
    {
        try
        {
            StudentGetByNameResponseDto studentGetByNameResponseDto = studentService.getStudentByName(studentName);
            return new ResponseEntity(studentGetByNameResponseDto,HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    // Find Student by library card ID
    @GetMapping("/student_by_libraryid")
    public Student studentGetByLibraryId(@RequestParam("id") int libraryCardId) throws Exception {
        try{
            return studentService.studentGetByLibraryId(libraryCardId);
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

    // Delete Student by id
    @DeleteMapping("/delete_student/{id}")
    public String deleteStudentByStudentId(@PathVariable("id") int studentId) throws Exception {
        try{
            studentService.deleteStudentByStudentId(studentId);
            return "Student deleted successfully";
        }
        catch (Exception e)
        {
            return e.getMessage() ;
        }
    }
}
