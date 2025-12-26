package com.example.demo.service.impl;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.VerificationLog;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.repository.VerificationLogRepository;
import com.example.demo.service.VerificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VerificationServiceImpl implements VerificationService {
    private final CertificateRepository certificateRepository;
    private final VerificationLogRepository logRepository;

    public VerificationServiceImpl(CertificateRepository certificateRepository,
                                   VerificationLogRepository logRepository) {
        this.certificateRepository = certificateRepository;
        this.logRepository = logRepository;
    }

    @Override
    public VerificationLog verifyCertificate(String verificationCode, String clientIp) {
        Certificate certificate = null;
        String status = "FAILED";
        
        try {
            certificate = certificateRepository.findByVerificationCode(verificationCode).orElse(null);
            if (certificate != null) {
                status = "SUCCESS";
            }
        } catch (Exception e) {
            status = "FAILED";
        }

        VerificationLog log = VerificationLog.builder()
                .certificate(certificate)
                .verifiedAt(LocalDateTime.now())
                .status(status)
                .ipAddress(clientIp)
                .build();

        return logRepository.save(log);
    }

    @Override
    public List<VerificationLog> getLogsByCertificate(Long certificateId) {
        Certificate certificate = certificateRepository.findById(certificateId)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
        
        return logRepository.findAll().stream()
                .filter(log -> log.getCertificate() != null && 
                              log.getCertificate().getId().equals(certificateId))
                .toList();
    }
}