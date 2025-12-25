package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @Column(unique = true)
    private String email;
    
    @Column(unique = true)
    private String rollNumber;
    
    // Manual getters and setters for compatibility
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }
    
    // Static builder method for compatibility
    public static StudentBuilder builder() {
        return new StudentBuilder();
    }
    
    public static class StudentBuilder {
        private Long id;
        private String name;
        private String email;
        private String rollNumber;
        
        public StudentBuilder id(Long id) { this.id = id; return this; }
        public StudentBuilder name(String name) { this.name = name; return this; }
        public StudentBuilder email(String email) { this.email = email; return this; }
        public StudentBuilder rollNumber(String rollNumber) { this.rollNumber = rollNumber; return this; }
        
        public Student build() {
            Student student = new Student();
            student.setId(id);
            student.setName(name);
            student.setEmail(email);
            student.setRollNumber(rollNumber);
            return student;
        }
    }
}