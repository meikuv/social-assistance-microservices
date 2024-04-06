package meikuv.xyz.authservice.controller;

import lombok.RequiredArgsConstructor;
import meikuv.xyz.authservice.config.jwt.JWTUtils;
import meikuv.xyz.authservice.config.user.UserDetailsImpl;
import meikuv.xyz.authservice.dto.VerificationDTO;
import meikuv.xyz.authservice.exception.HttpException;
import meikuv.xyz.authservice.model.RefreshToken;
import meikuv.xyz.authservice.model.User;
import meikuv.xyz.authservice.model.Verification;
import meikuv.xyz.authservice.payload.request.LoginRequest;
import meikuv.xyz.authservice.payload.request.RegisterRequest;
import meikuv.xyz.authservice.payload.request.VerificationRequest;
import meikuv.xyz.authservice.payload.response.AuthResponse;
import meikuv.xyz.authservice.payload.response.MessageResponse;
import meikuv.xyz.authservice.service.IEmailService;
import meikuv.xyz.authservice.service.IRefreshTokenService;
import meikuv.xyz.authservice.service.IUserService;
import meikuv.xyz.authservice.service.IVerificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth/account")
@RequiredArgsConstructor
public class AuthController {
    private final IUserService userService;
    private final IEmailService emailService;
    private final IVerificationService verificationService;
    private final IRefreshTokenService refreshTokenService;

    private final JWTUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        User user = userService.getUserByUsername(request.getUsername());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new HttpException(HttpStatus.UNAUTHORIZED, "Wrong username or password");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwt(userDetails);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        return ResponseEntity.status(HttpStatus.OK).body(
                AuthResponse.builder()
                        .accessToken(jwt)
                        .refreshToken(refreshToken.getToken())
                        .message("Успешно вошли в систему")
                        .build()
        );
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@RequestBody RegisterRequest request) {
        if (userService.existsByUsername(request.getUsername())) {
            throw new HttpException(HttpStatus.CONFLICT, "Имя пользователя уже занято");
        }

        if (userService.existsByEmail(request.getEmail())) {
            throw new HttpException(HttpStatus.CONFLICT, "Электронная почта уже используется");
        }

        String jwt = jwtUtils.generateJwtFromUsername(request.getUsername());
        String code = emailService.generateCode();

        VerificationDTO verificationDTO = verificationService.createVerification(
                code, request.getEmail(), request.getUsername());
        emailService.sendCode(jwt, verificationDTO);


        userService.createUser(request);

        return ResponseEntity.status(HttpStatus.OK).body(
                new MessageResponse("Вы успешно зарегистрированы")
        );
    }

    @PostMapping("/verify")
    public ResponseEntity<MessageResponse> verifyUser(@RequestBody VerificationRequest request) {
        Verification verification = verificationService.checkVerification(
                request.getCode(), request.getEmail()
        );
        verification.setConfirmedAt(LocalDateTime.now());

        verificationService.save(verification);
        userService.enableUser(request.getEmail());

        return ResponseEntity.status(HttpStatus.OK)
                .body(new MessageResponse("Verified successfully"));
    }
}
