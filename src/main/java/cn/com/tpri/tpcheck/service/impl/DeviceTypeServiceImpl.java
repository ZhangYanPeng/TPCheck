package cn.com.tpri.tpcheck.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tpri.tpcheck.dao.impl.DeviceTypeDAOImpl;
import cn.com.tpri.tpcheck.entity.DeviceType;
import cn.com.tpri.tpcheck.service.IDeviceTypeService;

@Service
public class DeviceTypeServiceImpl implements IDeviceTypeService {

	@Autowired
	DeviceTypeDAOImpl deviceTypeDAO;
	
	@Override
	@Transactional
	public List<DeviceType> list() {
		// TODO Auto-generated method stub
		return deviceTypeDAO.getListByHQL("from DeviceType", null);
	}

	@Override
	@Transactional
	public int add(DeviceType dType) {
		// TODO Auto-generated method stub
		try {
			deviceTypeDAO.save(dType);
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
			deviceTypeDAO.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public int edit(DeviceType dType) {
		// TODO Auto-generated method stub
		try {
			deviceTypeDAO.update(dType);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public DeviceType load(long id) {
		// TODO Auto-generated method stub
		return deviceTypeDAO.get(id);
	}

}
