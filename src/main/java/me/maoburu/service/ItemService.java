package me.maoburu.service;

import me.maoburu.pojo.Item;
import me.maoburu.pojo.Page;

public interface ItemService {
	
	/**
	 * 获得用户列表（分页）
	 * @param status 
	 * @param name 
	 * @param pageNum
	 * @param size
	 * @return
	 */
	Page listItem(String name, String status, String charge, String type, int pageNum, int size);

	/**
	 * ajax获得节目关联视频列表
	 * @param id
	 * @param pageNum
	 * @param size
	 * @return
	 */
	Page ajaxListRelateVideo(String id, String name, String status, int pageNum, int size);
	
	/**
	 * 根据节目id获得节目
	 * @param id 节目id
	 * @return
	 */
	Item getItemById(String id);
	
	/**
	 * 添加或更新节目
	 * @param item
	 * @param operation update更新，为空为添加
	 * @return
	 */
	int addOrUpdateItem(Item item, String operation);
	
	/**
	 * 查询未关联节目视频或关联不存在节目的视频
	 * @param name
	 * @param pageNum
	 * @param size
	 * @return
	 */
	Page listUnrelateVideo(String name, int pageNum, int size);
	
	/**
	 * 视频关联节目
	 * @param id 视频id
	 * @param parentId 节目id
	 * @return
	 */
	int relateVideo(String id, String parentId);
	
	/**
	 * 上移视频
	 * @param id 视频id
	 * @param parentId 节目id
	 * @param position 位置（视频表中以集数作为顺序位置）
	 * @return
	 */
	int upVideo(String id, String parentId, int position);
	
	/**
	 * 下移视频
	 * @param id 视频id
	 * @param parentId 节目id
	 * @param position 位置（视频表中以集数作为顺序位置）
	 * @return
	 */
	int downVideo(String id, String parentId, int position);
	
	/**
	 * 移除节目关联视频
	 * @param id 视频id
	 * @return
	 */
	int removeRelateVideo(String id);

	/**
	 * 修改节目状态
	 * @param id
	 * @param status
	 * @return
	 */
	int ajaxUpdateItemStatus(String id, String status);
	
	/**
	 * 修改节目付费标识
	 * @param id
	 * @param charge
	 * @return
	 */
	int ajaxUpdateItemCharge(String id, String charge);
	
	/**
	 * ajax修改海报地址
	 * @param id
	 * @param poster
	 * @return
	 */
	int ajaxUpdatePoster(String id, String poster);
	
}
