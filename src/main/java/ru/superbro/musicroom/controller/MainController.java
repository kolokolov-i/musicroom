package ru.superbro.musicroom.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(Authentication authentication){
        if(authentication != null && authentication.isAuthenticated()){
            return "redirect:/home";
        }
        else {
            return "redirect:/login";
        }
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

}
