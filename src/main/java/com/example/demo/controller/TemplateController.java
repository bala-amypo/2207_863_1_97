package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.demo.entity.CertificateTemplate;
import com.example.demo.service.TemplateService;

@RestController
@RequestMapping("/templates")
public class TemplateController {

    private final TemplateService service;

    public TemplateController(TemplateService service) {
        this.service = service;
    }

    // Add a new template
    @PostMapping
    public CertificateTemplate addTemplate(@RequestBody CertificateTemplate template) {
        return service.addTemplate(template);
    }

    // Get all templates
    @GetMapping
    public List<CertificateTemplate> getAllTemplates() {
        return service.getAllTemplates();
    }

    // Get template by id
    @GetMapping("/{id}")
    public CertificateTemplate getTemplateById(@PathVariable Long id) {
        return service.findById(id);
    }
}
