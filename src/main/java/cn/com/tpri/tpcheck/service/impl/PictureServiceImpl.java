package cn.com.tpri.tpcheck.service.impl;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cn.com.tpri.tpcheck.dao.impl.PictureDAOImpl;
import cn.com.tpri.tpcheck.entity.Picture;
import cn.com.tpri.tpcheck.service.IPictureService;

@Service
public class PictureServiceImpl implements IPictureService{
	@Autowired
	PictureDAOImpl pictureDAO;

	@Override
	@Transactional
	public int delete(long id) {
		// TODO Auto-generated method stub
		File file = new File(pictureDAO.get(id).getPath());
		if (file.exists()) {
			file.delete();
		}
		pictureDAO.deleteById(id);
		return 1;
	}

	@Override
	@Transactional
	public int save(Picture picture, MultipartFile pic) {
		// TODO Auto-generated method stub
		if(pic.isEmpty())
			return -1;
		try {
			FileUtils.copyInputStreamToFile(pic.getInputStream(), new File(picture.getPath()));
			pictureDAO.save(picture);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}
	}

}
