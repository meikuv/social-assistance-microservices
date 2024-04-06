package meikuv.xyz.emailservice.controller;

import lombok.RequiredArgsConstructor;
import meikuv.xyz.emailservice.dto.VerificationDTO;
import meikuv.xyz.emailservice.service.IEmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/email")
@RequiredArgsConstructor
public class EmailController {
    private final IEmailService emailService;

    @PostMapping("/send-code")
    public ResponseEntity<?> sendVerificationEmail(@RequestBody VerificationDTO verification) {
        emailService.sendVerificationEmail(verification);
        return ResponseEntity.ok().build();
    }
}
