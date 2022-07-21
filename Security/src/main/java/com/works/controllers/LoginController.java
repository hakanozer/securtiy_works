package com.works.controllers;

import com.works.entities.LoginUser;
import com.works.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.UUID;

@Controller
public class LoginController {

    final UserService uService;
    public LoginController(UserService uService) {
        this.uService = uService;
    }

    String token = "";
    @GetMapping("/")
    public String home( Model model ) {
        token = UUID.randomUUID().toString();
        model.addAttribute("user", new LoginUser());
        model.addAttribute( "token", token );
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @Valid @ModelAttribute("user") LoginUser user,
            BindingResult bindingResult,
            Model model,
            @RequestParam(defaultValue = "") String remember,
            @RequestParam(defaultValue = "") String token
    ) {
        if ( token.equals(this.token) ) {
            if (bindingResult.hasErrors() ) {
                System.out.println( "Errors Valid" );
            }else {
                //model.addAttribute("email", user.getEmail());
                boolean status = uService.userLogin(user, remember);
                if ( status ) {
                    return "redirect:/dashboard";
                }
            }
        }else {
            System.out.println( "Form Token Error" );
        }

        return "login";
    }


    @GetMapping("/logout")
    public String logout() {
        uService.logout();
        return "redirect:/";
    }

}
