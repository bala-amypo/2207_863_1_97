package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "certificates")
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
    
    @OneToMany(mappedBy = "certificate", cascade = CascadeType.ALL)
    private List<VerificationLog> verificationLogs;
    
    public Certificate() {}
    
    public Certificate(Student student, CertificateTemplate template, LocalDate issuedDate, String qrCodeUrl, String verificationCode) {
        this.student = student;
        this.template = template;
        this.issuedDate = issuedDate;
        this.qrCodeUrl = qrCodeUrl;
        this.verificationCode = verificationCode;
    }
    
    public static CertificateBuilder builder() {
        return new CertificateBuilder();
    }
    
    // Getters and Setters
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
    
    public List<VerificationLog> getVerificationLogs() { return verificationLogs; }
    public void setVerificationLogs(List<VerificationLog> verificationLogs) { this.verificationLogs = verificationLogs; }
    
    public static class CertificateBuilder {
        private Student student;
        private CertificateTemplate template;
        private LocalDate issuedDate;
        private String qrCodeUrl;
        private String verificationCode;
        
        public CertificateBuilder student(Student student) { this.student = student; return this; }
        public CertificateBuilder template(CertificateTemplate template) { this.template = template; return this; }
        public CertificateBuilder issuedDate(LocalDate issuedDate) { this.issuedDate = issuedDate; return this; }
        public CertificateBuilder qrCodeUrl(String qrCodeUrl) { this.qrCodeUrl = qrCodeUrl; return this; }
        public CertificateBuilder verificationCode(String verificationCode) { this.verificationCode = verificationCode; return this; }
        
        public Certificate build() {
            return new Certificate(student, template, issuedDate, qrCodeUrl, verificationCode);
        }
    }
}