package cn.com.tpri.tpcheck.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tpri.tpcheck.dao.impl.DeviceCheckRecordDAOImpl;
import cn.com.tpri.tpcheck.entity.DeviceCheckRecord;
import cn.com.tpri.tpcheck.service.IDeviceCheckRecordService;
import cn.com.tpri.tpcheck.support.Constants;
import cn.com.tpri.tpcheck.support.PageResults;

@Service
public class DeviceCheckRecordServiceImpl implements IDeviceCheckRecordService{

	@Autowired
	DeviceCheckRecordDAOImpl deviceCheckRecordDAO;
	
	@Override
	@Transactional
	public int add(DeviceCheckRecord dcr) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public PageResults<DeviceCheckRecord> list(String device, int page, long did, long btid) {
		// TODO Auto-generated method stub
		String hql = "from DeviceCheckRecord where device.name like ? and device.superDevice.department.id = ? and device.deviceType.baseType.id = ? order by date desc";
		String countHql = "select count(*) from DeviceCheckRecord where device.name like ? and device.superDevice.department.id = ? and device.deviceType.baseType.id = ?";
		Object[] values = {"%%"+device+"%%",did,btid};
		return deviceCheckRecordDAO.findPageByFetchedHql(hql, countHql, page, Constants.PAGE_SIZE, values);
	}

	@Override
	@Transactional
	public List<DeviceCheckRecord> getall(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
