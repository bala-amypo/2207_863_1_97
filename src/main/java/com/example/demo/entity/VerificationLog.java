package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "verification_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    
    // Manual getters and setters for compatibility
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
    
    // Static builder method for compatibility
    public static VerificationLogBuilder builder() {
        return new VerificationLogBuilder();
    }
    
    public static class VerificationLogBuilder {
        private Long id;
        private Certificate certificate;
        private LocalDateTime verifiedAt;
        private String status;
        private String ipAddress;
        
        public VerificationLogBuilder id(Long id) { this.id = id; return this; }
        public VerificationLogBuilder certificate(Certificate certificate) { this.certificate = certificate; return this; }
        public VerificationLogBuilder verifiedAt(LocalDateTime verifiedAt) { this.verifiedAt = verifiedAt; return this; }
        public VerificationLogBuilder status(String status) { this.status = status; return this; }
        public VerificationLogBuilder ipAddress(String ipAddress) { this.ipAddress = ipAddress; return this; }
        
        public VerificationLog build() {
            VerificationLog log = new VerificationLog();
            log.setId(id);
            log.setCertificate(certificate);
            log.setVerifiedAt(verifiedAt);
            log.setStatus(status);
            log.setIpAddress(ipAddress);
            return log;
        }
    }
}