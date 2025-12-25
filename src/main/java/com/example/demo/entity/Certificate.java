package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "certificates")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    
    @ManyToOne
    @JoinColumn(name = "template_id")
    private CertificateTemplate template;
    
    private LocalDate issuedDate;
    
    @Column(columnDefinition = "TEXT")
    private String qrCodeUrl;
    
    @Column(unique = true)
    private String verificationCode;
    
    // Manual getters and setters for compatibility
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    
    public CertificateTemplate getTemplate() { return template; }
    public void setTemplate(CertificateTemplate template) { this.template = template; }
    
    public LocalDate getIssuedDate() { return issuedDate; }
    public void setIssuedDate(LocalDate issuedDate) { this.issuedDate = issuedDate; }
    
    public String getQrCodeUrl() { return qrCodeUrl; }
    public void setQrCodeUrl(String qrCodeUrl) { this.qrCodeUrl = qrCodeUrl; }
    
    public String getVerificationCode() { return verificationCode; }
    public void setVerificationCode(String verificationCode) { this.verificationCode = verificationCode; }
    
    // Static builder method for compatibility
    public static CertificateBuilder builder() {
        return new CertificateBuilder();
    }
    
    public static class CertificateBuilder {
        private Long id;
        private Student student;
        private CertificateTemplate template;
        private LocalDate issuedDate;
        private String qrCodeUrl;
        private String verificationCode;
        
        public CertificateBuilder id(Long id) { this.id = id; return this; }
        public CertificateBuilder student(Student student) { this.student = student; return this; }
        public CertificateBuilder template(CertificateTemplate template) { this.template = template; return this; }
        public CertificateBuilder issuedDate(LocalDate issuedDate) { this.issuedDate = issuedDate; return this; }
        public CertificateBuilder qrCodeUrl(String qrCodeUrl) { this.qrCodeUrl = qrCodeUrl; return this; }
        public CertificateBuilder verificationCode(String verificationCode) { this.verificationCode = verificationCode; return this; }
        
        public Certificate build() {
            Certificate certificate = new Certificate();
            certificate.setId(id);
            certificate.setStudent(student);
            certificate.setTemplate(template);
            certificate.setIssuedDate(issuedDate);
            certificate.setQrCodeUrl(qrCodeUrl);
            certificate.setVerificationCode(verificationCode);
            return certificate;
        }
    }
}