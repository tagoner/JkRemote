package com.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class Main {
	
	@GetMapping("/")
	public String index(Model model) {
		log.info("- info -");
		log.debug("- debug -");
		log.error("- error -");
		
		return "index";
	}
	
	@GetMapping("/main")
	public String main(Model model) {
		log.info("- info -");
		log.debug("- debug -");
		log.error("- error -");
		
		return "main";
	}
}