package pro.main.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pro.main.service.inh.HomeService;

@Slf4j
@RestController
@RequestMapping("/auth")
public class HomeRestController {
	
	@Resource(name = "homeService")
	private HomeService homeService;
	
	
	/*
	 * 회원가입
	 */
	@PostMapping("/signUp")
    Map<String, Object> getSignUp(@RequestBody String arg) {
	    JSONObject obj = new JSONObject(arg);
	    log.info("Json : {}", obj);
	    
	    Map<String, Object> map = obj.toMap();
	    log.info("Map : {}", map);
	    
	    Map<String, Object> result = new LinkedHashMap<String, Object>();
	    result.put("값", "던지기");
	    
        return result;
    }
}