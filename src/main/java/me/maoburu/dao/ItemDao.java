package me.maoburu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import me.maoburu.pojo.Item;
import me.maoburu.pojo.Video;

@Component
public interface ItemDao {

	List<Item> listItem(@Param("name")String name, 
			@Param("status")String status,
			@Param("charge")String charge,
			@Param("type")String type);

	List<Video> ajaxListRelateVideo(@Param("id")String id, 
			@Param("name")String name,
			@Param("status")String status);

	Item getItemById(String id);

	int updateItem(Item item);

	int addItem(Item item);

	List<Video> listUnrelateVideo(@Param("name")String name);

	int relateVideo(@Param("id")String id, 
			@Param("parentId")String parentId);

	void setItemPosition(@Param("id")String id, 
			@Param("parentId")String parentId, 
			@Param("position")int position,
			@Param("updatePosition")int updatePosition);

	int getMaxPosition(@Param("parentId")String parentId);

	int removeRelateVideo(@Param("id")String id);

	int updateItemCharge(@Param("id")String id, 
			@Param("charge")String charge);

	int updateItemStatus(@Param("id")String id, 
			@Param("status")String status);

	int updatePoster(@Param("id")String id, 
			@Param("poster")String poster);
	
}
