package com.example.demo.service.impl;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.VerificationLog;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.repository.VerificationLogRepository;
import com.example.demo.service.VerificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        Optional<Certificate> certificateOpt = certificateRepository.findByVerificationCode(verificationCode);
        
        VerificationLog log;
        
        if (certificateOpt.isPresent()) {
            log = VerificationLog.builder()
                .certificate(certificateOpt.get())
                .verifiedAt(LocalDateTime.now())
                .status("SUCCESS")
                .ipAddress(clientIp)
                .build();
        } else {
            log = VerificationLog.builder()
                .certificate(null)
                .verifiedAt(LocalDateTime.now())
                .status("FAILED")
                .ipAddress(clientIp)
                .build();
        }
        
        return logRepository.save(log);
    }
    
    @Override
    public List<VerificationLog> getLogsByCertificate(Long certificateId) {
        Certificate certificate = certificateRepository.findById(certificateId)
            .orElseThrow(() -> new ResourceNotFoundException("Certificate not found"));
        
        // Return all logs for this certificate
        return logRepository.findAll().stream()
            .filter(log -> log.getCertificate() != null && log.getCertificate().getId().equals(certificateId))
            .toList();
    }
}