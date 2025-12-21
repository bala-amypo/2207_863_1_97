package com.example.demo.service.impl;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    
    private final StudentRepository studentRepository;
    
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    
    @Override
    public Student addStudent(Student student) {
        // Check for duplicate email
        Optional<Student> existingByEmail = studentRepository.findByEmail(student.getEmail());
        if (existingByEmail.isPresent()) {
            throw new RuntimeException("Student email exists");
        }
        
        // Check for duplicate roll number
        Optional<Student> existingByRoll = studentRepository.findByRollNumber(student.getRollNumber());
        if (existingByRoll.isPresent()) {
            throw new RuntimeException("Student email exists");
        }
        
        return studentRepository.save(student);
    }
    
    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found"));
    }
}