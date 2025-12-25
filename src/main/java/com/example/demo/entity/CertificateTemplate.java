package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "certificate_templates")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CertificateTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String templateName;
    
    private String backgroundUrl;
    
    private String fontStyle;
    
    private String signatureName;
    
    // Manual getters and setters for compatibility
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
    
    // Static builder method for compatibility
    public static CertificateTemplateBuilder builder() {
        return new CertificateTemplateBuilder();
    }
    
    public static class CertificateTemplateBuilder {
        private Long id;
        private String templateName;
        private String backgroundUrl;
        private String fontStyle;
        private String signatureName;
        
        public CertificateTemplateBuilder id(Long id) { this.id = id; return this; }
        public CertificateTemplateBuilder templateName(String templateName) { this.templateName = templateName; return this; }
        public CertificateTemplateBuilder backgroundUrl(String backgroundUrl) { this.backgroundUrl = backgroundUrl; return this; }
        public CertificateTemplateBuilder fontStyle(String fontStyle) { this.fontStyle = fontStyle; return this; }
        public CertificateTemplateBuilder signatureName(String signatureName) { this.signatureName = signatureName; return this; }
        
        public CertificateTemplate build() {
            CertificateTemplate template = new CertificateTemplate();
            template.setId(id);
            template.setTemplateName(templateName);
            template.setBackgroundUrl(backgroundUrl);
            template.setFontStyle(fontStyle);
            template.setSignatureName(signatureName);
            return template;
        }
    }
}