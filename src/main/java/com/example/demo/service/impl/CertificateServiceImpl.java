package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.entity.Certificate;
import com.example.demo.entity.Student;
import com.example.demo.entity.CertificateTemplate;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.CertificateTemplateRepository;
import com.example.demo.service.CertificateService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;
    private final StudentRepository studentRepository;
    private final CertificateTemplateRepository templateRepository;

    public CertificateServiceImpl(CertificateRepository certificateRepository,
                                  StudentRepository studentRepository,
                                  CertificateTemplateRepository templateRepository) {
        this.certificateRepository = certificateRepository;
        this.studentRepository = studentRepository;
        this.templateRepository = templateRepository;
    }

    @Override
    public Certificate generateCertificate(Long studentId, Long templateId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        CertificateTemplate template = templateRepository.findById(templateId)
                .orElseThrow(() -> new RuntimeException("Template not found"));

        String verificationCode = "VC-" + UUID.randomUUID().toString();
        String qrCodeUrl = generateQrCodeBase64(verificationCode);

        Certificate certificate = Certificate.builder()
                .student(student)
                .template(template)
                .verificationCode(verificationCode)
                .qrCodeUrl(qrCodeUrl)
                .issuedDate(LocalDate.now())
                .build();

        return certificateRepository.save(certificate);
    }

    @Override
    public Certificate getCertificate(Long certificateId) {
        return certificateRepository.findById(certificateId)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
    }

    @Override
    public Certificate findByVerificationCode(String code) {
        return certificateRepository.findByVerificationCode(code)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
    }

    @Override
    public List<Certificate> findByStudentId(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return certificateRepository.findByStudent(student);
    }

    // TODO: implement real QR code generation
    private String generateQrCodeBase64(String text) {
        // Placeholder: in real code, generate QR and encode to Base64
        return "data:image/png;base64,FAKE_BASE64_QR_" + text;
    }
}
