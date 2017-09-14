package cn.com.tpri.tpcheck.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.com.tpri.tpcheck.entity.Picture;

public interface IPictureService {
	int save(Picture picture, MultipartFile pic);
	int delete(long id);
	List<Picture> getSupPic(long id);
	List<Picture> getRecPic(long id);
}
