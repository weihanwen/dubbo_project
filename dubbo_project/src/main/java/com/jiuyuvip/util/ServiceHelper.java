package com.jiuyuvip.util;


import com.jiuyuvip.mongodb.DBLoggerDao;
import com.jiuyuvip.service.GoodsService;
import com.jiuyuvip.service.UserFService;

public class ServiceHelper {


    public static Object getService(String serviceName){
        return Const.WEB_APP_CONTEXT.getBean(serviceName);
    }

    public static UserFService getUserFService(){
        return (UserFService) getService("userFService");
    }

    public static GoodsService getGoodsService(){
        return (GoodsService) getService("goodsService");
    }

    public static DBLoggerDao getDBLoggerDao(){
        return (DBLoggerDao) getService("dBLoggerDao");
    }


}
