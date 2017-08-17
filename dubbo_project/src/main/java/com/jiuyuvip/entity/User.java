package com.jiuyuvip.entity;



import java.io.Serializable;


/** 
* User
 */
public class User implements Serializable {
  private static final long serialVersionUID = 1L;
  private Integer USER_ID;
  private String USER_NAME;
  private String USER_PASSWORD;
  private String USER_EMAIL;

public Integer getUSER_ID() {
	return USER_ID;
}
@Override
public String toString() {
	return "User [USER_ID=" + USER_ID + ", USER_NAME=" + USER_NAME
			+ ", USER_PASSWORD=" + USER_PASSWORD + ", USER_EMAIL=" + USER_EMAIL
			+ "]";
}
public void setUSER_ID(Integer uSER_ID) {
	USER_ID = uSER_ID;
}
public String getUSER_NAME() {
	return USER_NAME;
}
public void setUSER_NAME(String uSER_NAME) {
	USER_NAME = uSER_NAME;
}
public String getUSER_PASSWORD() {
	return USER_PASSWORD;
}
public void setUSER_PASSWORD(String uSER_PASSWORD) {
	USER_PASSWORD = uSER_PASSWORD;
}
public String getUSER_EMAIL() {
	return USER_EMAIL;
}
public void setUSER_EMAIL(String uSER_EMAIL) {
	USER_EMAIL = uSER_EMAIL;
}


}  