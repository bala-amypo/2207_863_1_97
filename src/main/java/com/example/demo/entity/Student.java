package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @Column(unique = true)
    private String email;
    
    @Column(unique = true)
    private String rollNumber;
    
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Certificate> certificates;
    
    public Student() {}
    
    public Student(String name, String email, String rollNumber) {
        this.name = name;
        this.email = email;
        this.rollNumber = rollNumber;
    }
    
    public static StudentBuilder builder() {
        return new StudentBuilder();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }
    
    public List<Certificate> getCertificates() { return certificates; }
    public void setCertificates(List<Certificate> certificates) { this.certificates = certificates; }
    
    public static class StudentBuilder {
        private String name;
        private String email;
        private String rollNumber;
        
        public StudentBuilder name(String name) { this.name = name; return this; }
        public StudentBuilder email(String email) { this.email = email; return this; }
        public StudentBuilder rollNumber(String rollNumber) { this.rollNumber = rollNumber; return this; }
        
        public Student build() {
            return new Student(name, email, rollNumber);
        }
    }
}