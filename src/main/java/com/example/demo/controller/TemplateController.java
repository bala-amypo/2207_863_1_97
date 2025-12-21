package com.example.demo.controller;

import com.example.demo.entity.CertificateTemplate;
import com.example.demo.service.TemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/templates")
public class TemplateController {
    
    private final TemplateService templateService;
    
    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }
    
    @PostMapping
    public ResponseEntity<CertificateTemplate> addTemplate(@RequestBody CertificateTemplate template) {
        CertificateTemplate savedTemplate = templateService.addTemplate(template);
        return ResponseEntity.ok(savedTemplate);
    }
    
    @GetMapping
    public ResponseEntity<List<CertificateTemplate>> getAllTemplates() {
        List<CertificateTemplate> templates = templateService.getAllTemplates();
        return ResponseEntity.ok(templates);
    }
}