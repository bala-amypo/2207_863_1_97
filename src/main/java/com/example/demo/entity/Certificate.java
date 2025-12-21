package com.example.demo.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "certificates")
@Getter
@Setter
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

    @Column(name = "verification_code", unique = true, nullable = false)
    private String verificationCode;

    @Column(name = "issued_date")
    private LocalDate issuedDate;

    @Column(name = "qr_code_url", columnDefinition = "TEXT")
    private String qrCodeUrl;

    @OneToMany(mappedBy = "certificate", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<VerificationLog> verificationLogs;
}
