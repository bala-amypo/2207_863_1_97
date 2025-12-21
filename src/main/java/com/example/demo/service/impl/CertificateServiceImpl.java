package com.example.demo.service.impl;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.CertificateTemplate;
import com.example.demo.entity.Student;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.repository.CertificateTemplateRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.CertificateService;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.Base64;
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
        String qrCodeUrl = generateQRCode(verificationCode);
        
        Certificate certificate = Certificate.builder()
            .student(student)
            .template(template)
            .issuedDate(LocalDate.now())
            .verificationCode(verificationCode)
            .qrCodeUrl(qrCodeUrl)
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
    
    private String generateQRCode(String text) {
        try {
            BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 200, 200);
            g.setColor(Color.BLACK);
            g.drawString("QR: " + text, 10, 100);
            g.dispose();
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            byte[] imageBytes = baos.toByteArray();
            
            return "data:image/png;base64," + Base64.getEncoder().encodeToString(imageBytes);
        } catch (Exception e) {
            return "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNkYPhfDwAChwGA60e6kgAAAABJRU5ErkJggg==";
        }
    }
}