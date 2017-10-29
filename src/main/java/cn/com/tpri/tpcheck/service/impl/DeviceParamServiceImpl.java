package cn.com.tpri.tpcheck.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tpri.tpcheck.dao.impl.DeviceParamDAOImpl;
import cn.com.tpri.tpcheck.entity.DeviceInfo;
import cn.com.tpri.tpcheck.entity.DeviceParam;
import cn.com.tpri.tpcheck.service.IDeviceParamService;

@Service
public class DeviceParamServiceImpl implements IDeviceParamService {

	@Autowired
	DeviceParamDAOImpl deviceParamDAO;
	
	@Override
	@Transactional
	public List<DeviceParam> list(long tid) {
		// TODO Auto-generated method stub
		String hql = "from DeviceParam where deviceType.id = ?";
		Object[] values = {tid};
		return deviceParamDAO.getListByHQL(hql, tid);
	}

	@Override
	@Transactional
	public int add(DeviceParam dParam) {
		// TODO Auto-generated method stub
		try {
			dParam.setInQR(0);
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
			Set<DeviceInfo> di = deviceParamDAO.get(id).getDeviceInfos();
			if( di != null && di.size()>0 ){
				return -1;
			}
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

	@Override
	@Transactional
	public int inQR(long id, int state) {
		// TODO Auto-generated method stub
		try {
			DeviceParam dp = deviceParamDAO.get(id);
			dp.setInQR(state);
			deviceParamDAO.update(dp);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

}
