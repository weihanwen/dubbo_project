package com.jiuyuvip.mongodb;




import com.jiuyuvip.mongodb.model.DBLogger;

import java.util.List;

/**
 * UserDao
 * Created by tl on 17/2/13.
 */
public interface DBLoggerDao {

    /**
     * 查询所有数据
     *
     * @return
     */
    List<DBLogger> findAll();

    /**
     * 用于分页查询
     *
     * @param skip(第一个坐标为0)
     * @param limit
     * @return
     */
    List<DBLogger> findList(DBLogger logger, int skip, int limit);

    /**
     * 保存用户
     *
     * @param logger
     */
    void save(DBLogger logger);

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
    void save(String id, String path, String errorType, String errorContent, String use_millisecinds, String createtime, String request_parameter, String result_parameter);


    /**
     * 根据id查询
     *
     * @param  id
     * @return
     */
    DBLogger findOne(String id);


    /**
     * 根据参数查询
     *
     * @param  logger
     * @return
     */
    List<DBLogger> listAllByCondition(DBLogger logger);


    /**
     * 根据条件模糊查询
     * @param key 键值key
     * @param content 模糊匹配content
     * @return
     */
     List<DBLogger> listAllByLikeCondition(String key, String content);

    /**
     * 根据地址查询
     *
     * @param path
     * @return
     */
    DBLogger findOneByPath(String path);



    /**
     * 删除
     *
     * @param ids
     */
    void delete(String... ids);
}
