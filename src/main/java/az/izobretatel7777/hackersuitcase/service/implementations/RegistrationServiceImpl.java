package az.izobretatel7777.hackersuitcase.service.implementations;

import az.izobretatel7777.hackersuitcase.dao.entity.User;
import az.izobretatel7777.hackersuitcase.dao.repo.UserRepository;
import az.izobretatel7777.hackersuitcase.service.EmailingService;
import az.izobretatel7777.hackersuitcase.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    @Value("${spring.mail.username}")
    private String fromEmail;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EmailingService emailingService;

    public void register(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setOtp(RandomStringUtils.randomNumeric(6));
        userRepository.save(user);
        emailingService.sendEmail(fromEmail, user.getEmail(), "Welcome to HackerSuitcases forum!" +
                " To activate your account, please, use this code: " + user.getOtp());
    }

    public boolean activateUserByOtp(String otp) {
        User user = userRepository.findByOtp(otp);
        // if user with this OTP is not found return false
        if (user == null || user.isActive())
            return false;
        // Otherwise, activate user
        user.setActive(true);
        user.setOtp(RandomStringUtils.randomNumeric(6));
        userRepository.save(user);
        return true;
    }
}
