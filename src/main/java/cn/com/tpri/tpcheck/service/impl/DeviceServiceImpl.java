package cn.com.tpri.tpcheck.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tpri.tpcheck.dao.impl.DeviceDAOImpl;
import cn.com.tpri.tpcheck.entity.Device;
import cn.com.tpri.tpcheck.service.IDeviceService;
import cn.com.tpri.tpcheck.support.Constants;
import cn.com.tpri.tpcheck.support.PageResults;

@Service
public class DeviceServiceImpl implements IDeviceService{
	
	@Autowired
	DeviceDAOImpl deviceDAO;

	@Override
	@Transactional
	public int importDevice(List<Device> lDevice) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public Device load(long id) {
		// TODO Auto-generated method stub
		return deviceDAO.get(id);
	}

	@Override
	@Transactional
	public int add(Device device) {
		// TODO Auto-generated method stub
		try {
			deviceDAO.save(device);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public int delete(long device) {
		// TODO Auto-generated method stub
		try {
			deviceDAO.deleteById(device);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public int edit(Device device) {
		// TODO Auto-generated method stub
		try {
			deviceDAO.update(device);;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public PageResults<Device> list(int page, long cid, long btid) {
		// TODO Auto-generated method stub
		String hql = "from Device where company.id = ? and deviceType.baseType.id = ?";
		String countHql = "select count(*) from Device where company.id = ? and deviceType.baseType.id = ?";
		Object[] values = {cid, btid};
		return deviceDAO.findPageByFetchedHql(hql, countHql, page, Constants.PAGE_SIZE, values);
	}

}
