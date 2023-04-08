package boot.test;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.core.Application;
import com.util.JwtProvider;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@ContextConfiguration(classes = Application.class)
@SpringBootTest
class Calulator {
	
    @Resource(name="Jwt")
    private JwtProvider test;
    
    
	@Test
	void test() {
		Map<String,String> param = new LinkedHashMap<>();
		param.put("name", "tagon");
		param.put("age", "29");
		
		try {
    		String token = test.createToken(param);
    		log.info("en_token : {}", token);
    		
    		Claims claims = test.getClaims(token);
    		log.info("de_token : {}", claims);
		}
		catch(Exception e) {
		    log.error("", e);
		}
	}
}