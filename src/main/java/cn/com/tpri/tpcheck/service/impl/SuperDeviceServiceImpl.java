package cn.com.tpri.tpcheck.service.impl;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tpri.tpcheck.dao.impl.SuperDeviceDAOImpl;
import cn.com.tpri.tpcheck.entity.Device;
import cn.com.tpri.tpcheck.entity.DeviceInfo;
import cn.com.tpri.tpcheck.entity.DeviceParam;
import cn.com.tpri.tpcheck.entity.DeviceType;
import cn.com.tpri.tpcheck.entity.SuperDevice;
import cn.com.tpri.tpcheck.service.ISuperDeviceService;

@Service
public class SuperDeviceServiceImpl implements ISuperDeviceService {

	@Autowired
	SuperDeviceDAOImpl superDeviceDAO; 
	
	@Override
	@Transactional
	public List<SuperDevice> list(long did) {
		// TODO Auto-generated method stub
		String hqlString = "from SuperDevice where department.id = ?";
		Object[] values = {did};
		return superDeviceDAO.getListByHQL(hqlString, values);
	}

	@Override
	@Transactional
	public SuperDevice getAllInformation(long id, String type) {
		// TODO Auto-generated method stub
		SuperDevice sd = superDeviceDAO.get(id);
		Set<Device> ds = new HashSet<Device>();
		for(Device d : sd.getDevices()) {
			d.setDeviceCheckRecords(null);
			if( d.getSupOrSub()==1 && !d.getDeviceType().getName().equals(type) )
				continue;
			DeviceType dt = d.getDeviceType();
			for( DeviceParam dp : dt.getDeviceParams()) {
				if(dp.getPos()>0) {
					DeviceInfo di = new DeviceInfo();
					di.set
				}
			}
		}
		return sd;
	}

}
