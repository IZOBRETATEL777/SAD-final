package az.izobretatel7777.hackersuitcase.service.implementations;

import az.izobretatel7777.hackersuitcase.dao.entity.User;
import az.izobretatel7777.hackersuitcase.dao.repo.UserRepository;
import az.izobretatel7777.hackersuitcase.service.UpdatePasswordService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePasswordServiceImpl implements UpdatePasswordService {
    private final EmailingServiceImpl emailingService;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.mail.username}")
    private String fromEmail;


    @Override
    public void sendResetPasswordEmail(String email) {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            return;
        }
        userRepo.save(user);
        String content = "Hello from HackerSuitcase forum! Use this code to change your password:\n" + user.getOtp();
        emailingService.sendEmail(fromEmail, email, content);
    }

    @Override
    public void sendResetPasswordEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        sendResetPasswordEmail(currentPrincipalName);
    }

    @Override
    public boolean resetPassword(String newPassword, String otp) {
        User user = userRepo.findByOtp(otp);
        if (user == null) {
            return false;
        }
        user.setPassword(newPassword);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setOtp(RandomStringUtils.randomNumeric(6));
        userRepo.save(user);
        return true;
    }
}
