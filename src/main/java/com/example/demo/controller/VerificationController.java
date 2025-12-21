package com.example.demo.controller;

import com.example.demo.entity.VerificationLog;
import com.example.demo.service.VerificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/verify")
@Tag(name = "Certificate Verification", description = "APIs for verifying certificates")
public class VerificationController {
    
    private final VerificationService verificationService;
    
    public VerificationController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }
    
    @PostMapping("/{verificationCode}")
    @Operation(summary = "Verify a certificate by verification code")
    public ResponseEntity<VerificationLog> verifyCertificate(
            @PathVariable String verificationCode,
            HttpServletRequest request) {
        String clientIp = getClientIp(request);
        VerificationLog log = verificationService.verifyCertificate(verificationCode, clientIp);
        return ResponseEntity.ok(log);
    }
    
    @GetMapping("/logs/{certificateId}")
    @Operation(summary = "Get verification logs for a certificate")
    public ResponseEntity<List<VerificationLog>> getLogsByCertificate(@PathVariable Long certificateId) {
        List<VerificationLog> logs = verificationService.getLogsByCertificate(certificateId);
        return ResponseEntity.ok(logs);
    }
    
    private String getClientIp(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
            return xForwardedFor.split(",")[0];
        }
        return request.getRemoteAddr();
    }
}