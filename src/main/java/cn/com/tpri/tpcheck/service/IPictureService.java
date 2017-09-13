package cn.com.tpri.tpcheck.service;

import org.springframework.web.multipart.MultipartFile;

import cn.com.tpri.tpcheck.entity.Picture;

public interface IPictureService {
	int save(Picture picture, MultipartFile pic);
	int delete(long id);
}
