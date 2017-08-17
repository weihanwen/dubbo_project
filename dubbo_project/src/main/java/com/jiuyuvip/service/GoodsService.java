package com.jiuyuvip.service;

import com.jiuyuvip.cache_version.VersionLocker;
import com.jiuyuvip.dao.DaoSupport;
import com.jiuyuvip.entity.Goods;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/** 
 * GoodsService
  */  
@Service("goodsService")
public class GoodsService {
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;


	@VersionLocker
	public Integer updateUserNoVersionLocker(Goods goods)throws Exception{
     	return (Integer)dao.update("com.jiuyuvip.mapper.GoodsMapper.updateUserNoVersionLocker",goods);
	}
	@Cacheable(value="Goods",key="'selectUserById'+#goods_id")
	public Goods selectGoodsById(Integer goods_id) throws Exception {
		return (Goods)dao.findForObject("com.jiuyuvip.mapper.GoodsMapper.selectGoodsById", goods_id);
	}

 	public Integer versionGoodsStock(Goods goods)throws Exception{
		return (Integer)dao.update("com.jiuyuvip.mapper.GoodsMapper.versionGoodsStock",goods);
	}



}