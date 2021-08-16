package com.example.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.text.ParseException;

@Controller
public class HomeController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;


    @GetMapping ("/")
    public String getHome(Principal principal , Model model){
        try {

        String name = principal.getName();
            ApplicationUser applicationUser= applicationUserRepository.findByUsername(name);
            model.addAttribute("user",applicationUser);
        }catch(NullPointerException e) {
            System.out.println("NullPointerException thrown!");
            return"home.html";
        }
        return"home.html";
    }

}
