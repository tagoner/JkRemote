package pro.main.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import pro.main.bean.User;
import pro.main.service.Inf.HomeService;

@Slf4j
@Controller
public class HomeController {
	
	@Resource(name = "homeService")
	private HomeService homeService;
	
	
	@GetMapping("/")
	String main(Model model) {
		log.info("- HomeController -");
		
		return "main/Home";
	}
	
	@GetMapping("/login")
	User login(@RequestParam User user) {
		log.info("- loginController -");
		
		return homeService.login(user);
	}
}