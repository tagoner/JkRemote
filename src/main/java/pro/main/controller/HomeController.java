package pro.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/view")
public class HomeController {
	
	@GetMapping("/signIn")
    String signIn(Model model) {
	    log.info("- signIn Controller -");
	    
        return "main/SignIn";
    }
    
    @GetMapping("/signOut")
    String signOut(Model model) {
        log.info("- signOut Controller -");
        
        return "main/SignOut";
    }
	
	@GetMapping("/signUp")
    String getSignUp(Model model) {
	    log.info("- signUp Controller -");
	    
        return "main/SignUp";
    }
}