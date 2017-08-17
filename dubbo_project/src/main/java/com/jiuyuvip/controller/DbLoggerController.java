package com.jiuyuvip.controller;



import com.jiuyuvip.mongodb.DBLoggerDao;
import com.jiuyuvip.mongodb.model.DBLogger;
import com.jiuyuvip.util.DateFormatUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 *  DbLoggerController
 *  
   */  
@RestController
@RequestMapping("/dblogger")
public class DbLoggerController {

    @Resource(name="dBLoggerDao")
    private DBLoggerDao dBLoggerDao;

    //dblogger/saveLogger ---记录接口访问时间以及接口名称
    @RequestMapping("/saveLogger")
    @ResponseBody
    public String saveLogger(){
         try {
             DBLogger logger = new DBLogger();
             logger.setId("111111");
             logger.setErrorType("1");
             logger.setErrorContent("java.lang.ArithmeticException");
             logger.setCreatetime(DateFormatUtil.Now());
             logger.setRequest_parameter("({'2':'2','2','2'})");
             logger.setResult_parameter("({'2':'2','2','2'})");
             logger.setPath("dblogger/saveLogger");
             dBLoggerDao.save(logger);
             logger = new DBLogger();
             logger.setId("22222");
             logger.setErrorType("2");
             logger.setErrorContent("java.lang.NoSuchMethodError");
             logger.setCreatetime(DateFormatUtil.Now());
             logger.setRequest_parameter("({'2':'2','2','2'})");
             logger.setResult_parameter("({'2':'2','2','2'})");
             logger.setPath("dblogger/saveLogger");
             dBLoggerDao.save(logger);
             logger = new DBLogger();
             logger.setId("3333333");
             logger.setErrorType("3");
             logger.setErrorContent("java.lang.OutOfMemoryError");
             logger.setCreatetime(DateFormatUtil.Now());
             logger.setRequest_parameter("({'2':'2','2','2'})");
             logger.setResult_parameter("({'2':'2','2','2'})");
             logger.setPath("dblogger/saveLogger");
             dBLoggerDao.save(logger);
             logger = new DBLogger();
             logger.setId("44444444");
             logger.setErrorContent("java.lang.UnknownError");
             logger.setErrorType("3");
             logger.setCreatetime(DateFormatUtil.Now());
             logger.setRequest_parameter("({'2':'2','2','2'})");
             logger.setResult_parameter("({'2':'2','2','2'})");
             logger.setPath("dblogger/saveLogger");
             dBLoggerDao.save(logger);
         } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return "0";
    }

    //dblogger/findAll --- 值查询
    @RequestMapping("/findAll")
    @ResponseBody
    public List<DBLogger> findAll(DBLogger dblogger){
        List<DBLogger> ulist=null;
        try {
             ulist=dBLoggerDao.listAllByCondition(dblogger);
         } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ulist;
    }


    //dblogger/findLikeAll?key=id&content=2 ---值模糊查询
    @RequestMapping("/findLikeAll")
    @ResponseBody
    public List<DBLogger> findLikeAll(String key ,String content){
        List<DBLogger> ulist=null;
        try {
            ulist=dBLoggerDao.listAllByLikeCondition(key,content);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ulist;
    }






}