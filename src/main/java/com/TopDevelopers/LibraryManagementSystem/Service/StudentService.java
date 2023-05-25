package com.TopDevelopers.LibraryManagementSystem.Service;

import com.TopDevelopers.LibraryManagementSystem.DTO.StudentAddRequestDto;
import com.TopDevelopers.LibraryManagementSystem.DTO.StudentEmailUpdateRequestDto;
import com.TopDevelopers.LibraryManagementSystem.DTO.StudentGetByNameResponseDto;
import com.TopDevelopers.LibraryManagementSystem.DTO.StudentResponseToUpdateEmail;
import com.TopDevelopers.LibraryManagementSystem.Entity.LibraryCard;
import com.TopDevelopers.LibraryManagementSystem.Entity.Student;
import com.TopDevelopers.LibraryManagementSystem.Enum.CardStatus;
import com.TopDevelopers.LibraryManagementSystem.Exceptions.EmailAlreadyRegisteredException;
import com.TopDevelopers.LibraryManagementSystem.Exceptions.NoStudentFoundWithThisEmailException;
import com.TopDevelopers.LibraryManagementSystem.Exceptions.StudentNotFoundException;
import com.TopDevelopers.LibraryManagementSystem.Repository.LibraryCardRepository;
import com.TopDevelopers.LibraryManagementSystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    LibraryCardRepository libraryCardRepository;

    // Add Student
    public void addStudent(StudentAddRequestDto studentAddRequestDto) throws EmailAlreadyRegisteredException {
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
        try
        {
            studentRepository.save(student);// will save student and card both
        }
        catch(Exception e)
        {
            throw new EmailAlreadyRegisteredException();
        }
    }

    // Find student by name
    public String findStudentByEmail(String email) throws NoStudentFoundWithThisEmailException {
        try
        {
            return studentRepository.findByEmail(email).getName();
        }
        catch (RuntimeException e)
        {
            throw new NoStudentFoundWithThisEmailException();
        }
    }

    // Update email
    public StudentResponseToUpdateEmail updateEmail(StudentEmailUpdateRequestDto studentEmailUpdateRequestDto) throws StudentNotFoundException {
        Student student;
        try
        {
            student = studentRepository.findById(studentEmailUpdateRequestDto.getId()).get();
        }
        catch (Exception e)
        {
            throw new StudentNotFoundException();
        }
        student.setEmail(studentEmailUpdateRequestDto.getEmail());

        // save
        Student updatedStudent;
        try
        {
            updatedStudent = studentRepository.save(student);
        }
        catch (Exception e)
        {
            throw new StudentNotFoundException();
        }

        // convert updatedStudent to DTO
        StudentResponseToUpdateEmail studentResponseDto = new StudentResponseToUpdateEmail();
        studentResponseDto.setId(updatedStudent.getId());
        studentResponseDto.setName(updatedStudent.getName());
        studentResponseDto.setEmail(updatedStudent.getEmail());
        // return DTO
        return studentResponseDto;
    }

    // Get all Students
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

    // Student get By studentName
    public StudentGetByNameResponseDto getStudentByName(String studentName) throws StudentNotFoundException {
        StudentGetByNameResponseDto studentGetByNameResponseDto = new StudentGetByNameResponseDto();
        Student student;
        try
        {
            student = studentRepository.findByName(studentName);
        }
        catch (Exception e)
        {
            throw new StudentNotFoundException();
        }
        studentGetByNameResponseDto.setName(student.getName());
        studentGetByNameResponseDto.setAge(student.getAge());
        studentGetByNameResponseDto.setDepartment(String.valueOf(student.getDepartment()));
        studentGetByNameResponseDto.setEmail(student.getEmail());

        return studentGetByNameResponseDto;
    }

    // Student get By Library card ID no.
    public Student studentGetByLibraryId(int libraryCardId) throws Exception {
        Student student;
        LibraryCard card;
        try{
            card = libraryCardRepository.findById(libraryCardId).get();
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
        return card.getStudent();
    }

}
