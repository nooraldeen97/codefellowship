package com.example.auth;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
public class ApplicationUserController {


    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    PostRepository postRepository;


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



    @GetMapping("/users/{id}")
    public String getUserInfo(Model m , @PathVariable("id") Integer id , Principal principal){
        String name = principal.getName();
        ApplicationUser applicationUser= applicationUserRepository.findByUsername(name);
        m.addAttribute("user",applicationUser);
        m.addAttribute("userInfo",applicationUserRepository.findById(id).get());

        List<Post> thePost = (List<Post>) postRepository.findAllByApplicationUserId(id);
//        m.addAttribute("postClass",new Post());
        m.addAttribute("addedPost",thePost);
        m.addAttribute("currentName",principal.getName());


        return "profilePage.html";
    }

    @PostMapping("/users/{id}")
    public RedirectView addPosts(Model model,@PathVariable Integer id , @RequestParam(value = "body") String body , @RequestParam(value = "createdAt") String createdAt ){
        ApplicationUser applicationUser = applicationUserRepository.findById(id).get();
        Post post=new Post(body,createdAt,applicationUser);
        model.addAttribute("postClass",new Post());
        postRepository.save(post);

        return new RedirectView("/users/{id}");
    }


    @GetMapping("/logout")
    public RedirectView logoutHandler(){
        return new RedirectView("/");
    }






 @GetMapping("/allUsers")
    public String getAllUsers(Model model ,Principal principal){

            List<ApplicationUser> applicationUsers= (List<ApplicationUser>) applicationUserRepository.findAll();
     System.out.println(applicationUsers);
            model.addAttribute("users",applicationUsers);
        return "usersIndex.html";
 }

 @GetMapping("/follow/{id}")
    public RedirectView addFollowing(@PathVariable (value = "id") Integer id,Principal p){
        ApplicationUser userWhoFollow = applicationUserRepository.findByUsername(p.getName());
        ApplicationUser userWhoReciveFollow= applicationUserRepository.findById(id).get();
        userWhoFollow.getFollowing().add(userWhoReciveFollow);
        userWhoReciveFollow.getFollowers().add(userWhoFollow);
        applicationUserRepository.save(userWhoFollow);
        applicationUserRepository.save(userWhoReciveFollow);
        return new RedirectView("/feed");
 }

    @GetMapping("/feed")
    public String getAllFeed(Principal p, Model model){
        model.addAttribute("usernamePrincipal",p.getName());
        ApplicationUser userWhoFollow=applicationUserRepository.findByUsername(p.getName());
        List<ApplicationUser>following=userWhoFollow.getFollowers();
        model.addAttribute("feeds",following);
        return "feed.html";
    }

//    @GetMapping ("/error")
//    public String errorHandler(){
//        return "error.html";
//    }
}

