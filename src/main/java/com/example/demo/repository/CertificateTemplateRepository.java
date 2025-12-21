package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.CertificateTemplate;
import java.util.Optional;

@Repository
public interface CertificateTemplateRepository extends JpaRepository<CertificateTemplate, Long> {
    Optional<CertificateTemplate> findByTemplateName(String templateName);
}
