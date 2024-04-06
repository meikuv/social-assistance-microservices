package meikuv.xyz.authservice.service;


import meikuv.xyz.authservice.dto.VerificationDTO;

public interface IEmailService {
    void sendCode(String token, VerificationDTO verificationDTO);

    String generateCode();
}
