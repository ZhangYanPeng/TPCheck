package cn.com.tpri.tpcheck.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tpri.tpcheck.dao.impl.DeviceCheckItemDAOImpl;
import cn.com.tpri.tpcheck.dao.impl.DeviceParamDAOImpl;
import cn.com.tpri.tpcheck.dao.impl.DeviceTypeDAOImpl;
import cn.com.tpri.tpcheck.entity.DeviceCheckItem;
import cn.com.tpri.tpcheck.entity.DeviceParam;
import cn.com.tpri.tpcheck.entity.DeviceType;
import cn.com.tpri.tpcheck.service.IDeviceTypeService;

@Service
public class DeviceTypeServiceImpl implements IDeviceTypeService {

	@Autowired
	DeviceTypeDAOImpl deviceTypeDAO;
	@Autowired
	DeviceParamDAOImpl deviceParamDAO;
	@Autowired
	DeviceCheckItemDAOImpl deviceCheckItemDAO;
	
	@Override
	@Transactional
	public List<DeviceType> list(long btid) {
		// TODO Auto-generated method stub
		String hql = "from DeviceType where baseType.id = ?";
		Object[] values = {btid};
		return deviceTypeDAO.getListByHQL(hql, values);
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
			DeviceType dtype = deviceTypeDAO.get(id);
			if(dtype.getDevices()!=null && dtype.getDevices().size()>0){
				return -1;
			}
			for( DeviceParam dp : dtype.getDeviceParams()){
				deviceParamDAO.delete(dp);
			}
			for( DeviceCheckItem dci : dtype.getDeviceCheckItems()){
				deviceCheckItemDAO.delete(dci);
			}
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
