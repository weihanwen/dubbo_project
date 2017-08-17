package com.jiuyuvip.util;


import com.jiuyuvip.mongodb.DBLoggerDao;
import com.jiuyuvip.service.GoodsService;

public class ServiceHelper {


    public static Object getService(String serviceName){
        return Const.WEB_APP_CONTEXT.getBean(serviceName);
    }

    public static GoodsService getGoodsService(){
        return (GoodsService) getService("goodsService");
    }

    public static DBLoggerDao getDBLoggerDao(){
        return (DBLoggerDao) getService("dBLoggerDao");
    }


}
