package az.izobretatel7777.hackersuitcase.controller;

import az.izobretatel7777.hackersuitcase.dao.entity.User;
import az.izobretatel7777.hackersuitcase.service.ProfileService;
import az.izobretatel7777.hackersuitcase.service.UpdatePasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UpdatePasswordService updatePasswordService;
    private final ProfileService profileService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("reset_password")
    public String resetPassword(String newPassword, String otp, Model model) {
        String message;
        if (updatePasswordService.resetPassword(newPassword, otp)) {
            message = "Password has been changed successfully!";
        } else {
            message = "Password has not been changed!";
        }
        model.addAttribute("message", message);
        return "register_finished";
    }
    @PostMapping("forget_password")
    public String forgotPassword(String email, Model model) {
        updatePasswordService.sendResetPasswordEmail(email);
        String message = "Check your e-mail for the code to change your password!";
        model.addAttribute("message", message);
        return "reset_password_form";
    }

    @GetMapping("forget_password")
    public String forgotPassword() {
        return "forget_password";
    }

    @GetMapping("profile")
    public String profile(Model model) {
        model.addAttribute("user", profileService.getProfile());
        return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(User user) {
        profileService.updateProfile(user);
        return "profile";
    }

    @GetMapping("logout")
    public String logout() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "logout";
    }
}
