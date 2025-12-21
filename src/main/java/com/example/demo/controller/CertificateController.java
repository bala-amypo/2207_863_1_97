package com.example.demo.controller;

import com.example.demo.entity.Certificate;
import com.example.demo.service.CertificateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @PostMapping("/generate/{studentId}/{templateId}")
    public ResponseEntity<Certificate> generateCertificate(@PathVariable Long studentId, 
                                                          @PathVariable Long templateId) {
        Certificate certificate = certificateService.generateCertificate(studentId, templateId);
        return ResponseEntity.ok(certificate);
    }

    @GetMapping("/{certificateId}")
    public ResponseEntity<Certificate> getCertificate(@PathVariable Long certificateId) {
        Certificate certificate = certificateService.getCertificate(certificateId);
        return ResponseEntity.ok(certificate);
    }

    @GetMapping("/verify/code/{verificationCode}")
    public ResponseEntity<Certificate> getCertificateByCode(@PathVariable String verificationCode) {
        Certificate certificate = certificateService.findByVerificationCode(verificationCode);
        return ResponseEntity.ok(certificate);
    }
}