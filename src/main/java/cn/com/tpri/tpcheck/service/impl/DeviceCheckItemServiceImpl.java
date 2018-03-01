package cn.com.tpri.tpcheck.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tpri.tpcheck.dao.impl.DeviceCheckItemDAOImpl;
import cn.com.tpri.tpcheck.entity.DeviceCheckItem;
import cn.com.tpri.tpcheck.entity.DeviceCheckRecord;
import cn.com.tpri.tpcheck.service.IDeviceCheckItemService;

@Service
public class DeviceCheckItemServiceImpl implements IDeviceCheckItemService {
	@Autowired
	DeviceCheckItemDAOImpl deviceCheckItemDAO;
	
	@Override
	@Transactional
	public List<DeviceCheckItem> list(long tid) {
		// TODO Auto-generated method stub
		String hql = "from DeviceCheckItem where deviceType.id = ?";
		Object[] values = {tid};
		return deviceCheckItemDAO.getListByHQL(hql, tid);
	}

	@Override
	@Transactional
	public int add(DeviceCheckItem dci) {
		// TODO Auto-generated method stub
		try {
			deviceCheckItemDAO.save(dci);
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
			deviceCheckItemDAO.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public int edit(DeviceCheckItem dci) {
		// TODO Auto-generated method stub
		try {
			deviceCheckItemDAO.update(dci);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public DeviceCheckItem load(long id) {
		// TODO Auto-generated method stub
		return deviceCheckItemDAO.get(id);
	}

}
