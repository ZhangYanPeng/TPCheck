package cn.com.tpri.tpcheck.service.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cn.com.tpri.tpcheck.dao.impl.DeviceDAOImpl;
import cn.com.tpri.tpcheck.dao.impl.PictureDAOImpl;
import cn.com.tpri.tpcheck.entity.Device;
import cn.com.tpri.tpcheck.entity.Picture;
import cn.com.tpri.tpcheck.service.IPictureService;

@Service
public class PictureServiceImpl implements IPictureService{
	@Autowired
	PictureDAOImpl pictureDAO;
	@Autowired
	DeviceDAOImpl deviceDAO;

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

	@Override
	@Transactional
	public List<Picture> getSupPic(long id) {
		// TODO Auto-generated method stub
		Device dev = deviceDAO.get(id);
		String hqlString = "from Picture where superDevice.id = ?";
		Object[] values = {dev.getSuperDevice().getId()};
		return pictureDAO.getListByHQL(hqlString, values);
	}

	@Override
	@Transactional
	public List<Picture> getRecPic(long id) {
		// TODO Auto-generated method stub
		String hqlString = "from Picture where deviceCheckRecord.id = ?";
		Object[] values = {id};
		return pictureDAO.getListByHQL(hqlString, values);
	}

	@Override
	@Transactional
	public Picture loadByName(String name) {
		// TODO Auto-generated method stub
		String hqlString = "from Picture where name = ?";
		Object[] values = {name};
		return pictureDAO.getByHQL(hqlString, values);
	}

	@Override
	@Transactional
	public void updateInfo(Picture pic) {
		// TODO Auto-generated method stub
		pictureDAO.saveOrUpdate(pic);
	}

}
