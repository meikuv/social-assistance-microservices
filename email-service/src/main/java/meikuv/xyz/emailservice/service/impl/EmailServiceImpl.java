package meikuv.xyz.emailservice.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import meikuv.xyz.emailservice.dto.VerificationDTO;
import meikuv.xyz.emailservice.service.IEmailService;
import meikuv.xyz.emailservice.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements IEmailService {
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
    private final FileUtils fileUtils;
    private final JavaMailSender mailSender;

    @Async
    @Override
    public void sendVerificationEmail(VerificationDTO verification) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            String htmlTemplate = fileUtils.readFile("/home/meikuv/IdeaProjects/chat-app/src/main/resources/templates/verification_code.html");
            String htmlContent = htmlTemplate.replace("${username}", verification.getUsername());
            htmlContent = htmlContent.replace("${code}", verification.getCode());

            helper.setText(htmlContent, true);
            helper.setTo(verification.getEmail());
            helper.setSubject("Подтвердите свою электронную почту");
            helper.setFrom("prime@meikuv.xyz");
            mailSender.send(mimeMessage);
        } catch (MessagingException | IOException e) {
            LOGGER.error("failed to send email: ", e);
            throw new IllegalStateException("failed to send email");
        }
    }
}
