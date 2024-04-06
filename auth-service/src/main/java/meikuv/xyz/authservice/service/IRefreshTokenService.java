package meikuv.xyz.authservice.service;

import meikuv.xyz.authservice.model.RefreshToken;

import java.util.Optional;

public interface IRefreshTokenService {
    Optional<RefreshToken> findByToken(String token);

    RefreshToken createRefreshToken(Long userId);

    RefreshToken verifyExpiration(RefreshToken token);

    void deleteByUserId(Long userId);

    String createAccessToken(String refreshToken);
}
