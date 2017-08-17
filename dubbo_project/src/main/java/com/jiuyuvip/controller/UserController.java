package com.jiuyuvip.controller;

import com.jiuyuvip.entity.Goods;
import com.jiuyuvip.entity.Page;
import com.jiuyuvip.mongodb.UserDao;
import com.jiuyuvip.mongodb.model.User;

import com.jiuyuvip.service.UserFService;
import com.jiuyuvip.util.ServiceHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 *  UserController
 *  
   */  
@Controller
public class UserController  {
    @Resource(name="userFService")
    private UserFService userFService;



    //getIndex.do
    @RequestMapping("/getIndex")
    public ModelAndView getIndex(){
        ModelAndView mav = new ModelAndView();
        try {
         	User user = userFService.selectUserById(10);
            mav.addObject("user", user);
            mav.setViewName("userinfor");
   		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			mav.setViewName("404");
		}
         return mav;    
    }

    //userPage.do
    @RequestMapping("/userPage")
    public ModelAndView userPage(Page page){
        ModelAndView mav = new ModelAndView();
        try {
            List<User> userList=userFService.alllistPageUser(page);
            for (int i= 0; i<userList.size();i++){
                System.out.println(userList.get(i).toString());
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            mav.setViewName("404");
        }
        return mav;
    }

    //updateStock.do---判断库存---id含有效果
    @RequestMapping("/updateStock")
    @ResponseBody
    public synchronized Integer updateStock(Integer goods_id,Integer shopnumber){
        Integer result=0;
        try {
            Goods goods= ServiceHelper.getGoodsService().selectGoodsById(goods_id);
            if(goods != null && ((int)goods.getStock_number())-shopnumber >= 0){
                goods.setStock_number(((int)goods.getStock_number())-shopnumber);
                goods.setVersion(goods.getVersion()+1);
                result=(int)ServiceHelper.getGoodsService().versionGoodsStock(goods);
            }
         } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }

    //testone.do
    @RequestMapping(value = "/testone", method = RequestMethod.POST)
    @ResponseBody
    public String testone(@RequestBody String req) {

        return "";
    }

    //testtwo.do
    @RequestMapping(value = "/testtwo")
    @ResponseBody
    public String testtwo(@RequestBody String req) {

        return "";
    }

    //testthree.do
    @RequestMapping(value = "/testthree")
    @ResponseBody
    public String testthree( String req) {

        return "";
    }

    //updateStock2.do---判断库存注解实现效果
    @RequestMapping("/updateStock2")
    @ResponseBody
    public synchronized Integer updateStock2(Integer goods_id,Integer shopnumber){
        Integer result=0;
        try {
            Goods goods= ServiceHelper.getGoodsService().selectGoodsById(goods_id);
            if(goods != null && ((int)goods.getStock_number())-shopnumber >= 0){
                goods.setStock_number(((int)goods.getStock_number())-shopnumber);
                goods.setVersion(goods.getVersion()+1);
                 result=(int)ServiceHelper.getGoodsService().updateUserNoVersionLocker(goods);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }


    @Resource(name="userDao")
    private UserDao userDao;

    //loggerText.do---记录接口访问时间以及接口名称
    @RequestMapping("/loggerText")
    @ResponseBody
    public Integer loggerText(){
        Integer result=0;
        try {
            User user = new User();
            user.setId("11111");
            user.setUsername("skyLine2");
            user.setPassword("7777777");
            userDao.store(user);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }


    //loggerList.do
    @RequestMapping("/loggerList")
    public ModelAndView loggerList(){
        ModelAndView mav = new ModelAndView();
        try {
            List<LoggerContent> loggerList=userFService.allLoggerList();
            mav.addObject("loggerList",loggerList);
            mav.setViewName("logger");
         } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
         }
        return mav;
    }


    public static void main(String args[]) {

         System.out.println("jieshu");
    }


}