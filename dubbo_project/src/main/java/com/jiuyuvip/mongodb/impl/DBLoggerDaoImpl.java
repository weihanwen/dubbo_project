package com.jiuyuvip.mongodb.impl;


import com.jiuyuvip.mongodb.DBLoggerDao;
import com.jiuyuvip.mongodb.model.DBLogger;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Pattern;

/**
 * UserDaoImpl
 * Created by tl on 17/2/13.
 */
@Service("dBLoggerDao")
public class DBLoggerDaoImpl implements DBLoggerDao {

    //MongoTemplate是数据库和代码之间的接口，对数据库的操作都在它里面
    @Resource(name="mongoTemplate")
     private MongoTemplate mongoTemplate;

    /**
     * 保存用户
     *
     * @param logger
     */
    @Override
    public void save(DBLogger logger) {
        this.mongoTemplate.insert(logger);
    }


    /**
     * 保存用户
     * @param id   唯一标识ID
     * @param path 路径
     * @param errorType 错误类型
     * @param errorContent 错误内容
     * @param use_millisecinds 间隔时间
     * @param createtime 创建时间
     * @param request_parameter 请求参数
     * @param result_parameter  返回参数
     */
    @Override
    public void saveDetail(String id, String path, String errorType, String errorContent, String use_millisecinds, String createtime, String request_parameter, String result_parameter) {
        DBLogger logger=new DBLogger();
        logger.setId(id);
        logger.setErrorType(errorType);
        logger.setPath(path);
        logger.setResult_parameter(result_parameter);
        logger.setRequest_parameter(request_parameter);
        logger.setCreatetime(createtime);
        logger.setUse_millisecinds(use_millisecinds);
        logger.setErrorContent(errorContent);
        this.mongoTemplate.insert(logger);
    }


    /**
     * 根据条件查询
     *
     * @param  logger
     * @return
     */
    @Override
    public List<DBLogger> listAllByCondition(DBLogger logger) {
        Query   query = getQuery(logger);
        List<DBLogger> listlogger=this.mongoTemplate.find(query, DBLogger.class);
        return listlogger;
    }

    /**
     * 根据条件模糊查询
     * @param key 键值key
     * @param content 模糊匹配content
     * @return
     */
    @Override
    public List<DBLogger> listAllByLikeCondition(String key,String content) {

//        //完全匹配
//        Pattern pattern = Pattern.compile("^王$", Pattern.CASE_INSENSITIVE);
//        //右匹配
//        Pattern pattern = Pattern.compile("^.*王$", Pattern.CASE_INSENSITIVE);
//        //左匹配
//        Pattern pattern = Pattern.compile("^王.*$", Pattern.CASE_INSENSITIVE);
        //模糊匹配
        Pattern pattern = Pattern.compile("^.*"+content+".*$", Pattern.CASE_INSENSITIVE);
        Query query = Query.query(Criteria.where(key).regex(pattern));
        List<DBLogger> listlogger = mongoTemplate.find(query, DBLogger.class);
        return listlogger;
    }




    /**
     * 查询所有
     * @return
     */
    @Override
    public List<DBLogger> findAll() {
         return this.mongoTemplate.find(new Query(), DBLogger.class);
    }


    /**
     * 用于分页查询
     *
     * @param skip(第一个坐标为0)
     * @param limit
     * @return
     */
    @Override
    public List<DBLogger> findList(DBLogger logger,int skip, int limit) {
        // TODO Auto-generated method stub
        Query query = getQuery(logger);
        query.skip(skip);
        query.limit(limit);
        return mongoTemplate.find(query, DBLogger.class);
    }


    /**
     * 条件查询需要的参数
     * <br>------------------------------<br>
     * @param criteriaUser
     * @return
     */
    private Query getQuery(DBLogger criteriaUser) {
        if (criteriaUser == null) {
            criteriaUser = new DBLogger();
        }
        Query query = new Query();
        if (criteriaUser.getId() != null) {
            Criteria criteria = Criteria.where("id").is(criteriaUser.getId());
            query.addCriteria(criteria);
        }
        if (criteriaUser.getErrorType() != null) {
            Criteria criteria = Criteria.where("errorType").is(criteriaUser.getErrorType());
            query.addCriteria(criteria);
        }
        if (criteriaUser.getPath() != null) {
            Criteria criteria = Criteria.where("path").is(criteriaUser.getPath());
            query.addCriteria(criteria);
        }
        if (criteriaUser.getErrorContent() != null) {
            Criteria criteria = Criteria.where("errorContent").is(criteriaUser.getErrorContent());
            query.addCriteria(criteria);
        }
        if (criteriaUser.getUse_millisecinds() != null) {
            Criteria criteria = Criteria.where("use_millisecinds").is(criteriaUser.getUse_millisecinds());
            query.addCriteria(criteria);
        }
        if (criteriaUser.getRequest_parameter() != null) {
            Criteria criteria = Criteria.where("request_parameter").is(criteriaUser.getRequest_parameter());
            query.addCriteria(criteria);
        }
        if (criteriaUser.getResult_parameter() != null) {
            Criteria criteria = Criteria.where("result_parameter").is(criteriaUser.getResult_parameter());
            query.addCriteria(criteria);
        }
        return query;
    }




    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public DBLogger findOne(String id) {
        Query query = new Query();
        Criteria criteria = Criteria.where("_id").is(id);
        query.addCriteria(criteria);
        return this.mongoTemplate.findOne(query, DBLogger.class);
    }



    /**
     * Criteria.where("path").is(path)
     * 前一个是数据库的字段,后一个是java类的字段
     *
     * @param path
     * @return
     */
    @Override
    public DBLogger findOneByPath(String path) {
        Query query = new Query();
        Criteria criteria = Criteria.where("path").is(path);
        query.addCriteria(criteria);
        return this.mongoTemplate.findOne(query, DBLogger.class);
    }


    /**
     * 删除
     *
     * @param ids
     */
    @Override
    public void delete(String... ids) {
        if (ids == null || ids.length == 0) {
            return;
        }
        for (String id : ids) {
            Query query = new Query(Criteria.where("_id").is(id));
            this.mongoTemplate.remove(query, DBLogger.class);
        }
    }


}
