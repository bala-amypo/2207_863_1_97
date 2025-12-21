package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.service.VerificationService;
import com.example.demo.entity.VerificationLog;
import com.example.demo.entity.Certificate;
import com.example.demo.repository.VerificationLogRepository;
import com.example.demo.repository.CertificateRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VerificationServiceImpl implements VerificationService {

    private final VerificationLogRepository logRepository;
    private final CertificateRepository certificateRepository;

    public VerificationServiceImpl(VerificationLogRepository logRepository,
                                   CertificateRepository certificateRepository) {
        this.logRepository = logRepository;
        this.certificateRepository = certificateRepository;
    }

    @Override
    public VerificationLog verifyCertificate(String verificationCode, String clientIp) {
        Certificate certificate = certificateRepository.findByVerificationCode(verificationCode)
                .orElse(null);

        VerificationLog log = VerificationLog.builder()
                .certificate(certificate)
                .verifiedAt(LocalDateTime.now())
                .ipAddress(clientIp)
                .status(certificate != null ? "SUCCESS" : "FAILED")
                .build();

        // Save the log regardless of success or failure
        logRepository.save(log);

        if (certificate == null) {
            // Optional: throw an exception for higher layers if needed
            throw new RuntimeException("Certificate not found");
        }

        return log;
    }

    @Override
    public List<VerificationLog> getLogsByCertificate(Long certificateId) {
        Certificate certificate = certificateRepository.findById(certificateId)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));

        return logRepository.findByCertificate(certificate);
    }
}
