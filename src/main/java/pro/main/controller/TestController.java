package pro.main.controller;

import org.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.var.Role;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TestController {
	
	@PostMapping("/test")
    String getTest(@RequestBody String arg) {
        JSONObject obj = new JSONObject(arg);
        log.info("{}", obj);
        
        for(Role role : Role.values()) {
            log.info("ROLE : {}", role.getCode());
            log.info("ROLE : {}", role.getName());
            
            if(Role.USER.getName().equals(role.getName()))
                log.info("TRUE");
            else
                log.info("FALSE");
        }
        
        Object test = SecurityContextHolder.getContext();
        Object securityKey = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("securityKey : {}", securityKey);
        
        return "TestReturn";
    }
}