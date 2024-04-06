package meikuv.xyz.authservice.service;

import meikuv.xyz.authservice.dto.VerificationDTO;
import meikuv.xyz.authservice.model.Verification;

import java.util.Optional;

public interface IVerificationService {
    Optional<Verification> getVerificationByCodeAndEmail(String code, String email);

    VerificationDTO createVerification(String code, String email, String username);

    Verification checkConfirmDate(Verification verification);

    Verification checkExpirationDate(Verification verification);

    Verification checkVerification(String code, String email);

    void save(Verification verification);
}
