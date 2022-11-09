package az.izobretatel7777.hackersuitcase.service.implementations;

import az.izobretatel7777.hackersuitcase.service.EmailingService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;

@Service
@RequiredArgsConstructor
public class EmailingServiceImpl implements EmailingService {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(String from, String to, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setText(content);
        simpleMailMessage.setSubject("HackerSuitcases mail service");
        mailSender.send(simpleMailMessage);
    }
}