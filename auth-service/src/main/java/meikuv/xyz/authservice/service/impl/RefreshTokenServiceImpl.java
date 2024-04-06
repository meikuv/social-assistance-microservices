package meikuv.xyz.authservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import meikuv.xyz.authservice.config.jwt.JWTUtils;
import meikuv.xyz.authservice.exception.HttpException;
import meikuv.xyz.authservice.model.RefreshToken;
import meikuv.xyz.authservice.repository.RefreshTokenRepository;
import meikuv.xyz.authservice.service.IRefreshTokenService;
import meikuv.xyz.authservice.service.IUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements IRefreshTokenService {
    @Value("${app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;
    private final JWTUtils jwtUtils;
    private final IUserService userService;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = RefreshToken.builder()
                .user(userService.getUserById(userId))
                .expiryDate(Instant.now().plusMillis(refreshTokenDurationMs))
                .token(UUID.randomUUID().toString())
                .build();
        refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new HttpException(HttpStatus.FORBIDDEN, "Refresh token was expired. Please make a new login request");
        }
        return token;
    }

    @Override
    @Transactional
    public void deleteByUserId(Long userId) {
        refreshTokenRepository.deleteByUser(userService.getUserById(userId));
    }

    @Override
    public String createAccessToken(String refreshToken) {
        if (refreshToken != null && !refreshToken.isEmpty()) {
            return findByToken(refreshToken)
                    .map(this::verifyExpiration)
                    .map(RefreshToken::getUser)
                    .map(userModel -> jwtUtils.generateJwtFromUsername(userModel.getUsername()))
                    .orElseThrow(() -> new HttpException(HttpStatus.FORBIDDEN, "Refresh token is not in database"));
        } else {
            throw new HttpException(HttpStatus.FORBIDDEN, "Refresh Token is empty");
        }
    }
}

