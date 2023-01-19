package pro.main.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pro.main.bean.User;
import pro.main.model.HomeMapper;
import pro.main.service.Inf.HomeService;

@Slf4j
@Service("homeService")
public class HomeServiceImpl implements HomeService {
	
	//@Resource
	HomeMapper homeMapper;

	@Override
	public User login(User arg) {
		log.info("- HomeService -");
		
		return null;
	}
}