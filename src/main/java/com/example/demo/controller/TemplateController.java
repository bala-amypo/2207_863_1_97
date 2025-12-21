package com.example.demo.controller;

import com.example.demo.entity.CertificateTemplate;
import com.example.demo.service.TemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/templates")
@Tag(name = "Template Management", description = "APIs for managing certificate templates")
public class TemplateController {
    
    private final TemplateService templateService;
    
    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }
    
    @PostMapping
    @Operation(summary = "Add a new certificate template")
    public ResponseEntity<CertificateTemplate> addTemplate(@RequestBody CertificateTemplate template) {
        CertificateTemplate savedTemplate = templateService.addTemplate(template);
        return ResponseEntity.ok(savedTemplate);
    }
    
    @GetMapping
    @Operation(summary = "Get all certificate templates")
    public ResponseEntity<List<CertificateTemplate>> getAllTemplates() {
        List<CertificateTemplate> templates = templateService.getAllTemplates();
        return ResponseEntity.ok(templates);
    }
}