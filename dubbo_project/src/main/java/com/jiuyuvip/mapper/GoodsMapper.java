package com.jiuyuvip.mapper;


import com.jiuyuvip.cache_version.VersionLocker;
import com.jiuyuvip.entity.Goods;

public interface GoodsMapper {

	// 不参与乐观锁控制
 	Goods selectGoodsById(Integer goods_id);

	// 不参与乐观锁控制
 	Integer updateUserNoVersionLocker(Goods goods);


	// 参与乐观锁控制
	@VersionLocker
 	Integer versionGoodsStock(Goods goods);
}
