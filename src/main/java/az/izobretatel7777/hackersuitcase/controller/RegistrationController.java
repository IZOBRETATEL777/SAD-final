package az.izobretatel7777.hackersuitcase.controller;

import az.izobretatel7777.hackersuitcase.dao.entity.User;
import az.izobretatel7777.hackersuitcase.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

	private final RegistrationService registrationService;
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());

		return "signup_form";
	}

	@PostMapping("/otp")
	public String sendOtp(String otp, Model model) {
		String message;
		if (registrationService.activateUserByOtp(otp))
			message = "You have successfully registered!";
		else
			message = "Invalid OTP. Please try again!";
		model.addAttribute("message", message);
		return "register_finished";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
		registrationService.register(user);
		return "otp_form";
	}
}
