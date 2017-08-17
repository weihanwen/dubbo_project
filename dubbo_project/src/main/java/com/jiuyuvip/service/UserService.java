package com.jiuyuvip.service;

import com.jiuyuvip.mongodb.model.User;
  
/** 
 *
  */  
public interface UserService {  
 
    public User selectUserById(Integer user_id) throws Exception; 
  
}