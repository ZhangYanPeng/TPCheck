package cn.com.tpri.tpcheck.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.com.tpri.tpcheck.entity.Picture;

public interface IPictureService {
	int save(Picture picture, MultipartFile pic);
	int delete(long id);
	List<Picture> getDevPic(long id);
	List<Picture> getRecPic(long id);
	Picture loadByName(String name);
	void updateInfo(Picture pic);
	List<Picture> loadBlogPic(long id);
}
