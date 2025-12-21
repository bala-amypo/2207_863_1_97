package com.example.demo.controller;

import com.example.demo.entity.VerificationLog;
import com.example.demo.service.VerificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/verify")
public class VerificationController {

    private final VerificationService verificationService;

    public VerificationController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @PostMapping("/{verificationCode}")
    public ResponseEntity<VerificationLog> verifyCertificate(@PathVariable String verificationCode,
                                                            HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        VerificationLog log = verificationService.verifyCertificate(verificationCode, clientIp);
        return ResponseEntity.ok(log);
    }

    @GetMapping("/logs/{certificateId}")
    public ResponseEntity<List<VerificationLog>> getVerificationLogs(@PathVariable Long certificateId) {
        List<VerificationLog> logs = verificationService.getLogsByCertificate(certificateId);
        return ResponseEntity.ok(logs);
    }
}