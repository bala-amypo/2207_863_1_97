package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "verification_logs")
public class VerificationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "certificate_id")
    private Certificate certificate;
    
    private LocalDateTime verifiedAt;
    
    private String status;
    
    private String ipAddress;
    
    public VerificationLog() {}
    
    public VerificationLog(Certificate certificate, LocalDateTime verifiedAt, String status, String ipAddress) {
        this.certificate = certificate;
        this.verifiedAt = verifiedAt;
        this.status = status;
        this.ipAddress = ipAddress;
    }
    
    public static VerificationLogBuilder builder() {
        return new VerificationLogBuilder();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Certificate getCertificate() { return certificate; }
    public void setCertificate(Certificate certificate) { this.certificate = certificate; }
    
    public LocalDateTime getVerifiedAt() { return verifiedAt; }
    public void setVerifiedAt(LocalDateTime verifiedAt) { this.verifiedAt = verifiedAt; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
    
    public static class VerificationLogBuilder {
        private Certificate certificate;
        private LocalDateTime verifiedAt;
        private String status;
        private String ipAddress;
        
        public VerificationLogBuilder certificate(Certificate certificate) { this.certificate = certificate; return this; }
        public VerificationLogBuilder verifiedAt(LocalDateTime verifiedAt) { this.verifiedAt = verifiedAt; return this; }
        public VerificationLogBuilder status(String status) { this.status = status; return this; }
        public VerificationLogBuilder ipAddress(String ipAddress) { this.ipAddress = ipAddress; return this; }
        
        public VerificationLog build() {
            return new VerificationLog(certificate, verifiedAt, status, ipAddress);
        }
    }
}