package com.jiuyuvip.plugins;


import com.jiuyuvip.cache_version.Cache;
import com.jiuyuvip.cache_version.VersionLocker;
import com.jiuyuvip.util.Tools;
import org.apache.commons.lang.StringUtils;
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
        //开始获取判断注解
        String className = StringUtils.substringBeforeLast(ms.getId(), ".");// 当前类
        String currentMethodName = StringUtils.substringAfterLast(ms.getId(), ".");// 当前方法
        Method method = findMethod(className, currentMethodName);// 获取当前Method
        if (method == null || method.getAnnotation(VersionLocker.class) == null) {// 如果当前Method没有注解VersionLocker
            return invocation.proceed();
        }
        SqlCommandType sqlCmdType = ms.getSqlCommandType();
        //判断是不是一个更新操作
        if(sqlCmdType != SqlCommandType.UPDATE) {
            return invocation.proceed();
        }
        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        String originalSql = boundSql.getSql();
        Object originalVersion = metaObject.getValue("delegate.boundSql.parameterObject."+versionColumn);
        originalSql=generateVersionSql(originalSql,(int)originalVersion,versionColumn);
        metaObject.setValue("delegate.boundSql.sql", originalSql);
        return invocation.proceed();
    }


    /**
     * 找到与指定函数名匹配的Method。
     *
     * @param className
     * @param targetMethodName
     * @return
     * @throws Throwable
     */
    private Method findMethod(String className, String targetMethodName) throws Throwable {
        Method[] methods = Class.forName(className).getDeclaredMethods();// 该类所有声明的方法
        if (methods == null) {
            return null;
        }

        for (Method method : methods) {
            if (StringUtils.equals(method.getName(), targetMethodName)) {
                return method;
            }
        }

        return null;
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
