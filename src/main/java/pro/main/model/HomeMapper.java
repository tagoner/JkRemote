package pro.main.model;

import org.apache.ibatis.annotations.Mapper;

import pro.main.bean.User;

//@Mapper
public interface HomeMapper {
	
	User getUser(User arg);
}