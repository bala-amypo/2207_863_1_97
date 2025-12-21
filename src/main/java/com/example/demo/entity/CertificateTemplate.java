package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "certificate_templates")
public class CertificateTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String templateName;
    
    private String backgroundUrl;
    
    private String fontStyle;
    
    private String signatureName;
    
    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL)
    private List<Certificate> certificates;
    
    public CertificateTemplate() {}
    
    public CertificateTemplate(String templateName, String backgroundUrl, String fontStyle, String signatureName) {
        this.templateName = templateName;
        this.backgroundUrl = backgroundUrl;
        this.fontStyle = fontStyle;
        this.signatureName = signatureName;
    }
    
    public static CertificateTemplateBuilder builder() {
        return new CertificateTemplateBuilder();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTemplateName() { return templateName; }
    public void setTemplateName(String templateName) { this.templateName = templateName; }
    
    public String getBackgroundUrl() { return backgroundUrl; }
    public void setBackgroundUrl(String backgroundUrl) { this.backgroundUrl = backgroundUrl; }
    
    public String getFontStyle() { return fontStyle; }
    public void setFontStyle(String fontStyle) { this.fontStyle = fontStyle; }
    
    public String getSignatureName() { return signatureName; }
    public void setSignatureName(String signatureName) { this.signatureName = signatureName; }
    
    public List<Certificate> getCertificates() { return certificates; }
    public void setCertificates(List<Certificate> certificates) { this.certificates = certificates; }
    
    public static class CertificateTemplateBuilder {
        private String templateName;
        private String backgroundUrl;
        private String fontStyle;
        private String signatureName;
        
        public CertificateTemplateBuilder templateName(String templateName) { this.templateName = templateName; return this; }
        public CertificateTemplateBuilder backgroundUrl(String backgroundUrl) { this.backgroundUrl = backgroundUrl; return this; }
        public CertificateTemplateBuilder fontStyle(String fontStyle) { this.fontStyle = fontStyle; return this; }
        public CertificateTemplateBuilder signatureName(String signatureName) { this.signatureName = signatureName; return this; }
        
        public CertificateTemplate build() {
            return new CertificateTemplate(templateName, backgroundUrl, fontStyle, signatureName);
        }
    }
}