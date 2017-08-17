package com.jiuyuvip.plugins;


import com.jiuyuvip.cache_version.Cache;
import com.jiuyuvip.cache_version.VersionLocker;
import com.jiuyuvip.util.Tools;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;


import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import javax.xml.bind.PropertyException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;


/**
 *
 * 类名称：VersionPlugin.java
 * 类描述：加注解控制锁，true表示控制，false表示不控制
 * @author fuhang
 * 作者单位：
 * 联系方式：
 * 创建时间：2014年7月1日
 * @version 1.0
 */
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class VersionPlugin implements Interceptor {

    private static String dialect = "";	//数据库方言
    private static String versionColumn = "";	//version字段name



    public Object intercept(Invocation invocation) throws Throwable {
         StatementHandler statementHandler = (StatementHandler) processTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        MappedStatement ms = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        SqlCommandType sqlCmdType = ms.getSqlCommandType();
        //判断是不是一个更新操作
        if(sqlCmdType != SqlCommandType.UPDATE) {
            return invocation.proceed();
        }
        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        //判断是不是带锁
        boolean flag = getVersionLocker(ms, boundSql);//true表示有注释，false表示没有注释
        if(!flag) {
            return invocation.proceed();
        }
        String originalSql = boundSql.getSql();
        Object originalVersion = metaObject.getValue("delegate.boundSql.parameterObject."+versionColumn);
        originalSql=generateVersionSql(originalSql,(int)originalVersion,versionColumn);
        metaObject.setValue("delegate.boundSql.sql", originalSql);
        return invocation.proceed();
    }


    /**
     * 根据数据库方言，生成特定的乐观锁sql
     * @param sql
     * @param nowversion
     * @return
     */
    private String generateVersionSql(String sql,int nowversion,String versionname){
        StringBuffer versionSql = new StringBuffer();
        if("mysql".equals(dialect)){
            versionSql.append(sql);
            if(sql.contains("where") || sql.contains("WHERE")){
                versionSql.append(" and  "+versionname+"="+(nowversion-1));
            }
         }else if("oracle".equals(dialect)){
            versionSql.append(sql);
            if(sql.contains("where") || sql.contains("WHERE")){
                versionSql.append(" and  "+versionname+"="+(nowversion-1));
            }

        }
        return versionSql.toString();
    }

    //获取是否含有注解
    private boolean getVersionLocker(MappedStatement ms, BoundSql boundSql) {
        Class<?>[] paramCls = null;
        Object paramObj = boundSql.getParameterObject();

        /******************下面处理参数只能按照下面3个的顺序***********************/
        /******************Process param must order by below ***********************/
        // 1、处理@Param标记的参数
        // 1、Process @Param param
        if(paramObj instanceof MapperMethod.ParamMap<?>) {
            MapperMethod.ParamMap<?> mmp = (MapperMethod.ParamMap<?>) paramObj;
            if(null != mmp && !mmp.isEmpty()) {
                paramCls = new Class<?>[mmp.size() / 2];
                int mmpLen = mmp.size() / 2;
                for(int i=0; i<mmpLen; i++) {
                    Object index = mmp.get("param" + (i + 1));
                    paramCls[i] = index.getClass();
                    if(List.class.isAssignableFrom(paramCls[i])){
                        return true;
                    }
                }
            }

            // 2、处理Map类型参数
            // 2、Process Map param
        } else if (paramObj instanceof Map) {//不支持批量
            @SuppressWarnings("rawtypes")
            Map map = (Map)paramObj;
            if(map.get("list") != null || map.get("array") != null){
                return true;
            }else{
                paramCls = new Class<?>[] {Map.class};
            }
            // 3、处理POJO实体对象类型的参数
            // 3、Process POJO entity param
        } else {
            paramCls = new Class<?>[] {paramObj.getClass()};
        }

//        Cache.MethodSignature vm = new Cache.MethodSignature(ms.getId(), paramCls);
//        VersionLocker versionLocker = versionLockerCache.getVersionLocker(vm);
//        if(null != versionLocker) {
//            return versionLocker;
//        }

        Class<?> mapper = getMapper(ms);
        if(mapper != null) {
            Method m;
            try {
                m = mapper.getDeclaredMethod(getMapperShortId(ms), paramCls);
            } catch (NoSuchMethodException | SecurityException e) {
                throw new RuntimeException("The Map type param error." + e, e);
            }
           if(m.getAnnotation(VersionLocker.class) != null){
                return true;
           }else{
               return false;
           }
         } else {
            throw new RuntimeException("Config info error, maybe you have not config the Mapper interface");
        }
    }


    private Class<?> getMapper(MappedStatement ms){
        String namespace = getMapperNamespace(ms);
        Collection<Class<?>> mappers = ms.getConfiguration().getMapperRegistry().getMappers();
        for(Class<?> clazz : mappers){
            if(clazz.getName().equals(namespace)){
                return clazz;
            }
        }
        return null;
    }

    private String getMapperNamespace(MappedStatement ms){
        String id = ms.getId();
        int pos = id.lastIndexOf(".");
        return id.substring(0, pos);
    }

    private String getMapperShortId(MappedStatement ms){
        String id = ms.getId();
        int pos = id.lastIndexOf(".");
        return id.substring(pos+1);
    }

    private static Object processTarget(Object target) {
        if(Proxy.isProxyClass(target.getClass())) {
            MetaObject mo = SystemMetaObject.forObject(target);
            return processTarget(mo.getValue("h.target"));
        }
        return target;
    }





    public Object plugin(Object arg0) {
        // TODO Auto-generated method stub
        return Plugin.wrap(arg0, this);
    }

    @Override
    public void setProperties(Properties p) {
        dialect = p.getProperty("dialect");
        if (Tools.isEmpty(dialect)) {
            try {
                throw new PropertyException("dialect property is not found!");
            } catch (PropertyException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        versionColumn = p.getProperty("versionColumn");
        if (Tools.isEmpty(versionColumn)) {
            try {
                throw new PropertyException("pageSqlId property is not found!");
            } catch (PropertyException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
