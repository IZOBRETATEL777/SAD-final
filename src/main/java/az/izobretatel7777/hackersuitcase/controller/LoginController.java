package az.izobretatel7777.hackersuitcase.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
