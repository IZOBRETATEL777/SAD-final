package az.izobretatel7777.hackersuitcase.controller;

import az.izobretatel7777.hackersuitcase.service.UpdatePasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UpdatePasswordService updatePasswordService;
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
        return "reset_password_form";
    }

    @GetMapping("forget_password")
    public String forgotPassword() {
        return "forget_password";
    }
}
