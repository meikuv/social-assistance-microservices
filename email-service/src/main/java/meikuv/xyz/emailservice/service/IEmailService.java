package meikuv.xyz.emailservice.service;

import meikuv.xyz.emailservice.dto.VerificationDTO;

public interface IEmailService {
    void sendVerificationEmail(VerificationDTO verification);
}
