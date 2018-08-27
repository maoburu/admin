package me.maoburu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import me.maoburu.pojo.Item;
import me.maoburu.pojo.Programa;

@Component
public interface ProgramaDao {
	
	List<Programa> listMainPrograma();
	
	List<Programa> listPrograma(String id);

	List<Item> listRelateItem(@Param("id")String id,
			@Param("name")String name, 
			@Param("status")String status);

	Programa getProgramabyId(String id);

	int updatePrograma(Programa programa);

	int addPrograma(Programa programa);

	List<Programa> getProgramaList();

	int updateRelatePrograma(Map map);

	List<Programa> list();

	int checkRelatePrograma(@Param("id")String id, 
			@Param("parentId")String parentId);

	int relatePrograma(@Param("id")String id, 
			@Param("parentId")String parentId, 
			@Param("position")int position);

	int getMaxPosition(@Param("parentId")String parentId, 
			@Param("markId")String markId);

	List<Item> listItem(@Param("name")String name);
	
	int checkRelateItem(@Param("id")String id, 
			@Param("parentId")String parentId);
	
	int relateItem(@Param("id")String id, 
			@Param("parentId")String parentId, 
			@Param("position")int position);

	int setProgramaPosition(@Param("id")String id, 
			@Param("parentId")String parentId, 
			@Param("position")int position,
			@Param("updatePosition")int updatePosition);

	int removeRelate(@Param("id")String id,
			@Param("parentId")String parentId, 
			@Param("markId")String markId);

	int setItemPosition(@Param("id")String id, 
			@Param("parentId")String parentId, 
			@Param("position")int position,
			@Param("updatePosition")int updatePosition);

	Map<String, String> getItemById(@Param("id")String id, 
			@Param("parentId")String parentId);

	int updateRelatePoster(@Param("id")String id, 
			@Param("parentId")String parentId, 
			@Param("type")String type,
			@Param("poster")String poster);

	int updateOtherPosition(@Param("id")String id,
			@Param("parentId")String parentId,
			@Param("position")int position,
			@Param("markId")String markId);
}
