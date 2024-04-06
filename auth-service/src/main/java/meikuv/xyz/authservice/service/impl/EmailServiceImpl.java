package meikuv.xyz.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import meikuv.xyz.authservice.dto.VerificationDTO;
import meikuv.xyz.authservice.exception.HttpException;
import meikuv.xyz.authservice.service.IEmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements IEmailService {
    private static final Random random = new Random();

    @Value("${app.emailServiceURI}")
    private String EMAIL_SERVICE_URI;
    private final RestTemplate restTemplate;

    @Override
    public void sendCode(String token, VerificationDTO verificationDTO) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", "Bearer " + token);
            HttpEntity<VerificationDTO> requestHttpEntity = new HttpEntity<>(verificationDTO, httpHeaders);

            ResponseEntity<HttpStatus> response = restTemplate.postForEntity(
                    EMAIL_SERVICE_URI + "/send-code", requestHttpEntity, HttpStatus.class
            );

            if (response.getStatusCode() != HttpStatus.OK) {
                throw new HttpException((HttpStatus) response.getStatusCode(), "Не удалось отправить электронное письмо");
            }
        } catch (HttpStatusCodeException e) {
            throw new HttpException((HttpStatus) e.getStatusCode(), "Не удалось отправить электронное письмо");
        }
    }

    public String generateCode() {
        String digits = "0123456789";
        char[] DIGIT_CHARS = digits.toCharArray();
        char[] codeArray = new char[4];
        for (int i = 0; i < codeArray.length; i++) {
            codeArray[i] = DIGIT_CHARS[random.nextInt(DIGIT_CHARS.length)];
        }

        return new String(codeArray);
    }
}
