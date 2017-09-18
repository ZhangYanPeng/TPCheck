package cn.com.tpri.tpcheck.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tpri.tpcheck.dao.impl.SuperDeviceDAOImpl;
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

}
