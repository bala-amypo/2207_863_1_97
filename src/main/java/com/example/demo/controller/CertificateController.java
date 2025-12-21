package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Certificate;
import com.example.demo.service.CertificateService;
import java.util.List;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @PostMapping("/generate/{studentId}/{templateId}")
    public Certificate generateCertificate(@PathVariable Long studentId,
                                           @PathVariable Long templateId) {
        return certificateService.generateCertificate(studentId, templateId);
    }

    @GetMapping("/{certificateId}")
    public Certificate getCertificate(@PathVariable Long certificateId) {
        return certificateService.getCertificate(certificateId);
    }

    @GetMapping("/verify/code/{verificationCode}")
    public Certificate getCertificateByCode(@PathVariable String verificationCode) {
        return certificateService.findByVerificationCode(verificationCode);
    }

    @GetMapping("/student/{studentId}")
    public List<Certificate> getCertificatesByStudent(@PathVariable Long studentId) {
        return certificateService.findByStudentId(studentId);
    }
}
