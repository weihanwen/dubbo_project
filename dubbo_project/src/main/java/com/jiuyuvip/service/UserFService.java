package com.jiuyuvip.service;

import javax.annotation.Resource;


import com.jiuyuvip.entity.Page;
import org.springframework.stereotype.Service;

import com.jiuyuvip.dao.DaoSupport;
import com.jiuyuvip.mongodb.model.User;

import java.util.List;

/** 
 * UserServiceʵ
  */  
@Service("userFService")
public class UserFService {
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;


     public User selectUserById(Integer user_id) throws Exception {
    	return (User)dao.findForObject("UserMapper.selectUserById", user_id);
    }


	public List<User> alllistPageUser(Page page) throws Exception {
		return (List<User>)dao.findForList("UserMapper.alllistPageUser", page);
	}



	public void saveLogger(LoggerContent logger)throws Exception{
		 dao.save("LoggerMapper.saveLogger",logger);
	}
 	public List<LoggerContent> allLoggerList() throws Exception {
		return (List<LoggerContent>)dao.findForList("UserMapper.allLoggerList", null);
	}



}