package com.example.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ApplicationUserController {


    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/login")
    public String getSignIn(){
        return "signin.html";
    }

    @GetMapping("/signup")
    public String getUsers(){
        return "signup.html";
    }

    @PostMapping("/signup")
    public RedirectView addUsers(@RequestParam(value = "username") String username,
                                 @RequestParam(value = "password")  String  password,
                                 @RequestParam(value = "firstname") String firstname,
                                 @RequestParam(value = "lastname") String lastname,
                                 @RequestParam(value = "date") String date,
                                 @RequestParam(value = "bio") String bio ) {
        ApplicationUser newUser = new ApplicationUser(username,bCryptPasswordEncoder.encode(password),firstname,lastname,date,bio);
        applicationUserRepository.save(newUser);
        return new RedirectView("/login");
    }
}
