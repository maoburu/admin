package me.maoburu.service;

import me.maoburu.pojo.Page;
import me.maoburu.pojo.Programa;

public interface ProgramaService {
	
	/**
	 * 获得节目列表（根据id，分页）
	 * @param name 
	 * @param pageNum
	 * @param size
	 * @return
	 */
	Page listPrograma(String id,int pageNum, int size);

	/**
	 * 查出栏目关联节目列表
	 * @param id 栏目id
	 * @param pageNum
	 * @param size
	 * @return
	 */
	Page listRelateItem(String id, String name, String status, int pageNum, int size);

	/**
	 * 通过id获得栏目
	 * @param id
	 * @return
	 */
	Programa getProgramaById(String id);
	
	/**
	 * 添加/新增栏目
	 * @param programa
	 * @param operation
	 * @return
	 */
	int addOrUpdatePrograma(Programa programa, String operation);
	
	/**
	 * 查询一级栏目和根栏目
	 * @param pageNum
	 * @param size
	 * @return
	 */
	Page listMainPrograma(int pageNum, int size);

	/**
	 * 查询所有Programa（分页）
	 * @param pageNum
	 * @param size
	 * @return
	 */
	Page list(int pageNum, int size);
	
	/**
	 * 栏目关联栏目
	 * @param id 子栏目id
	 * @param parentId 父栏目id
	 * @return
	 */
	int relatePrograma(String id, String parentId);

	/**
	 * 查询所有节目
	 * @param pageNum
	 * @param size
	 * @return
	 */
	Page listItem(String name, int pageNum, int size);
	
	/**
	 * 栏目关联节目
	 * @param id 节目id
	 * @param parentId 父栏目id
	 * @return
	 */
	int relateItem(String id, String parentId);
	
	
	/**
	 * 上移栏目
	 * @param id
	 * @param parentId
	 * @param position
	 */
	int upPrograma(String id, String parentId, int position);

	/**
	 * 下移栏目
	 * @param id
	 * @param parentId
	 * @param position
	 * @return
	 */
	int downPrograma(String id, String parentId, int position);
	
	/**
	 * 移除关联
	 * @param id
	 * @param parentId
	 * @param markId "0"为栏目与栏目,"1"为栏目与节目
	 * @return
	 */
	int removeRelate(String id, String parentId,int position, String markId);
	
	/**
	 * 上移节目
	 * @param id
	 * @param parentId
	 * @param position
	 * @return
	 */
	int upItem(String id, String parentId, int position);
	
	/**
	 * 下移节目
	 * @param id
	 * @param parentId
	 * @param position
	 * @return
	 */
	int downItem(String id, String parentId, int position);
	
	/**
	 * 通过id获得栏目关联的节目
	 * @return
	 */
	String getItemById(String id, String parentId, String type);
	
	/**
	 * 更新关联节目的三种海报
	 * @param id
	 * @param parentId
	 * @param type 海报类型（3种）
	 * @param poster
	 * @return
	 */
	int updateRelatePoster(String id, String parentId, String type,
			String poster);

}
