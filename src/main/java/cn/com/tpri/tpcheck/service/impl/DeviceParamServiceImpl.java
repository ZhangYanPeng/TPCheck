package cn.com.tpri.tpcheck.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tpri.tpcheck.dao.impl.DeviceParamDAOImpl;
import cn.com.tpri.tpcheck.entity.DeviceParam;
import cn.com.tpri.tpcheck.service.IDeviceParamService;

@Service
public class DeviceParamServiceImpl implements IDeviceParamService {

	@Autowired
	DeviceParamDAOImpl deviceParamDAO;
	
	@Override
	@Transactional
	public List<DeviceParam> list() {
		// TODO Auto-generated method stub
		return deviceParamDAO.getListByHQL("from DeviceParam", null);
	}

	@Override
	@Transactional
	public int add(DeviceParam dParam) {
		// TODO Auto-generated method stub
		try {
			deviceParamDAO.save(dParam);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public int delete(long id) {
		// TODO Auto-generated method stub
		try {
			deviceParamDAO.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public int edit(DeviceParam dParam) {
		// TODO Auto-generated method stub
		try {
			deviceParamDAO.update(dParam);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public DeviceParam load(long id) {
		// TODO Auto-generated method stub
		return deviceParamDAO.get(id);
	}

}
