package com.TopDevelopers.LibraryManagementSystem.Service;

import com.TopDevelopers.LibraryManagementSystem.DTO.StudentAddRequestDto;
import com.TopDevelopers.LibraryManagementSystem.DTO.StudentEmailUpdateRequestDto;
import com.TopDevelopers.LibraryManagementSystem.DTO.StudentGetByNameResponseDto;
import com.TopDevelopers.LibraryManagementSystem.DTO.StudentResponseToUpdateEmail;
import com.TopDevelopers.LibraryManagementSystem.Entity.LibraryCard;
import com.TopDevelopers.LibraryManagementSystem.Entity.Student;
import com.TopDevelopers.LibraryManagementSystem.Enum.CardStatus;
import com.TopDevelopers.LibraryManagementSystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public void addStudent(StudentAddRequestDto studentAddRequestDto)
    {
        // create a student object
        Student student = new Student();
        student.setName(studentAddRequestDto.getName());
        student.setAge(studentAddRequestDto.getAge());
        student.setDepartment(studentAddRequestDto.getDepartment());
        student.setEmail(studentAddRequestDto.getEmail());

        // create a card obj
        LibraryCard card = new LibraryCard();
        card.setStatus(CardStatus.ACTIVATED);
        card.setStudent(student); // set the student to card

        student.setCard(card);
        studentRepository.save(student); // will save student and card both
    }

    public String findStudentByEmail(String email)
    {
        return studentRepository.findByEmail(email).getName();
    }

    public StudentResponseToUpdateEmail updateEmail(StudentEmailUpdateRequestDto studentEmailUpdateRequestDto)
    {
        Student student = studentRepository.findById(studentEmailUpdateRequestDto.getId()).get();
        student.setEmail(studentEmailUpdateRequestDto.getEmail());

        // save
        Student updatedStudent = studentRepository.save(student);


        // convert updatedStudent to DTO
        StudentResponseToUpdateEmail studentResponseDto = new StudentResponseToUpdateEmail();
        studentResponseDto.setId(updatedStudent.getId());
        studentResponseDto.setName(updatedStudent.getName());
        studentResponseDto.setEmail(updatedStudent.getEmail());
        // return DTO
        return studentResponseDto;
    }

    public List<String> getAllStudent()
    {
        List<Student> studentList = studentRepository.findAll();
        List<String> ans = new ArrayList<>();
        for(Student student : studentList)
        {
            ans.add("Name : " + student.getName() + ", " + "Department : " + student.getDepartment());
        }
        return ans;
    }

    public StudentGetByNameResponseDto getStudentByName(String studentName)
    {
        StudentGetByNameResponseDto studentGetByNameResponseDto = new StudentGetByNameResponseDto();
        Student student = studentRepository.findByName(studentName);

        studentGetByNameResponseDto.setName(student.getName());
        studentGetByNameResponseDto.setAge(student.getAge());
        studentGetByNameResponseDto.setDepartment(String.valueOf(student.getDepartment()));
        studentGetByNameResponseDto.setEmail(student.getEmail());

        return studentGetByNameResponseDto;
    }
}
