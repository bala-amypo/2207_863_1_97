package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Certificate;
import com.example.demo.entity.Student;
import java.util.List;
import java.util.Optional;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    Optional<Certificate> findByVerificationCode(String verificationCode);
    List<Certificate> findByStudent(Student student); // added for SRS
}
