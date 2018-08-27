package me.maoburu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import me.maoburu.pojo.Video;

@Component
public interface VideoDao {

	List<Video> listVideo(@Param("name")String name, 
			@Param("status")String status);

	Video getVideoById(String id);

	int updateVideo(Video video);

	int updateVideoStatus(@Param("id")String id, 
			@Param("status")String status);

}
