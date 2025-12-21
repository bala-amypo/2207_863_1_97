package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.CertificateTemplate;
import com.example.demo.repository.CertificateTemplateRepository;
import com.example.demo.service.TemplateService;

@Service
public class TemplateServiceImpl implements TemplateService {

    private final CertificateTemplateRepository repository;

    public TemplateServiceImpl(CertificateTemplateRepository repository) {
        this.repository = repository;
    }

    @Override
    public CertificateTemplate addTemplate(CertificateTemplate template) {
        // Check for duplicate template name
        Optional<CertificateTemplate> existing = repository.findByTemplateName(template.getTemplateName());
        if (existing.isPresent()) {
            throw new RuntimeException("Template name exists");
        }

        // Optional: validate backgroundUrl as a proper URL
        if (template.getBackgroundUrl() == null || template.getBackgroundUrl().isBlank()) {
            throw new RuntimeException("Invalid template background URL");
        }

        return repository.save(template);
    }

    @Override
    public List<CertificateTemplate> getAllTemplates() {
        return repository.findAll();
    }

    @Override
    public CertificateTemplate findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Template not found"));
    }
}
