package com.example.demo.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
    
    // Manual getters and setters for compatibility
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}