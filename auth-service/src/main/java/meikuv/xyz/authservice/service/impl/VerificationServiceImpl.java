package meikuv.xyz.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import meikuv.xyz.authservice.dto.VerificationDTO;
import meikuv.xyz.authservice.exception.HttpException;
import meikuv.xyz.authservice.model.Verification;
import meikuv.xyz.authservice.repository.VerificationRepository;
import meikuv.xyz.authservice.service.IVerificationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VerificationServiceImpl implements IVerificationService {
    private final VerificationRepository verificationRepository;

    @Override
    public Optional<Verification> getVerificationByCodeAndEmail(String code, String email) {
        return verificationRepository.findByCodeAndEmail(code, email);
    }

    @Override
    @Transactional
    public VerificationDTO createVerification(String code, String email, String username) {
        Verification verification = Verification.builder()
                .code(code).email(email).username(username)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .build();
        verificationRepository.save(verification);

        return verification.toDto();
    }

    @Override
    public Verification checkConfirmDate(Verification verification) {
        if (verification.getConfirmedAt() != null) {
            throw new HttpException(HttpStatus.CONFLICT, "Учетная запись уже подтверждена");
        }

        return verification;
    }

    @Override
    public Verification checkExpirationDate(Verification verification) {
        LocalDateTime expiredAt = verification.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new HttpException(HttpStatus.CONFLICT, "Срок действия проверочного кода истек");
        }

        return verification;
    }

    @Override
    public Verification checkVerification(String code, String email) {
        return getVerificationByCodeAndEmail(code, email)
                .map(this::checkConfirmDate)
                .map(this::checkExpirationDate)
                .orElseThrow(
                        () -> new HttpException(HttpStatus.NOT_FOUND, "Проверочный код не найден")
                );
    }

    @Override
    public void save(Verification verification) {
        verificationRepository.save(verification);
    }

}
