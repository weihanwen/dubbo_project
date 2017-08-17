package com.jiuyuvip.service.impl;

import javax.annotation.Resource;

import com.jiuyuvip.service.UserService;
import org.springframework.stereotype.Service;

import com.jiuyuvip.dao.DaoSupport;
import com.jiuyuvip.mongodb.model.User;
  
/** 
 *
  */  
@Service("userService")  
public class UserServiceImpl implements UserService {
	
	@Resource(name = "daoSupport")
	private DaoSupport dao; 
  

    public User selectUserById(Integer user_id) throws Exception {  
    	return (User)dao.findForObject("UserMapper.selectUserById", user_id);
    } 
 
	
	
 
  
}