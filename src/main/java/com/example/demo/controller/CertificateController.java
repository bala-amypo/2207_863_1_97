package com.example.demo.controller;

import com.example.demo.entity.Certificate;
import com.example.demo.service.CertificateService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/certificates")
public class CertificateController {
    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @PostMapping("/generate/{studentId}/{templateId}")
    public Certificate generate(@PathVariable Long studentId, @PathVariable Long templateId) {
        return certificateService.generateCertificate(studentId, templateId);
    }

    @GetMapping("/{certificateId}")
    public Certificate get(@PathVariable Long certificateId) {
        return certificateService.getCertificate(certificateId);
    }

    @GetMapping("/verify/code/{verificationCode}")
    public Certificate getByVerificationCode(@PathVariable String verificationCode) {
        return certificateService.findByVerificationCode(verificationCode);
    }
}