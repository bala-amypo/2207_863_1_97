package com.example.demo.controller;

import com.example.demo.entity.Certificate;
import com.example.demo.service.CertificateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificates")
@Tag(name = "Certificate Management", description = "APIs for managing certificates")
public class CertificateController {
    
    private final CertificateService certificateService;
    
    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }
    
    @PostMapping("/generate/{studentId}/{templateId}")
    @Operation(summary = "Generate a new certificate")
    public ResponseEntity<Certificate> generateCertificate(@PathVariable Long studentId, 
                                                         @PathVariable Long templateId) {
        Certificate certificate = certificateService.generateCertificate(studentId, templateId);
        return ResponseEntity.ok(certificate);
    }
    
    @GetMapping("/{certificateId}")
    @Operation(summary = "Get certificate by ID")
    public ResponseEntity<Certificate> getCertificate(@PathVariable Long certificateId) {
        Certificate certificate = certificateService.getCertificate(certificateId);
        return ResponseEntity.ok(certificate);
    }
    
    @GetMapping("/verify/code/{verificationCode}")
    @Operation(summary = "Get certificate by verification code")
    public ResponseEntity<Certificate> getCertificateByCode(@PathVariable String verificationCode) {
        Certificate certificate = certificateService.findByVerificationCode(verificationCode);
        return ResponseEntity.ok(certificate);
    }
    
    @GetMapping("/student/{studentId}")
    @Operation(summary = "Get all certificates for a student")
    public ResponseEntity<List<Certificate>> getCertificatesByStudent(@PathVariable Long studentId) {
        List<Certificate> certificates = certificateService.findByStudentId(studentId);
        return ResponseEntity.ok(certificates);
    }
}