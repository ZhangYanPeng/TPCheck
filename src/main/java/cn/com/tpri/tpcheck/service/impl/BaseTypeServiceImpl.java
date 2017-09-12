package cn.com.tpri.tpcheck.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tpri.tpcheck.dao.impl.BaseTypeDAOImpl;
import cn.com.tpri.tpcheck.dao.impl.DeviceTypeDAOImpl;
import cn.com.tpri.tpcheck.entity.BaseType;
import cn.com.tpri.tpcheck.entity.DeviceType;
import cn.com.tpri.tpcheck.service.IBaseTypeService;

@Service
public class BaseTypeServiceImpl implements IBaseTypeService {

	@Autowired
	BaseTypeDAOImpl baseTypeDAO;
	@Autowired
	DeviceTypeDAOImpl deviceTypeDAO;
	
	@Override
	@Transactional
	public int add(BaseType baseType) {
		// TODO Auto-generated method stub
		try {
			baseTypeDAO.save(baseType);
			DeviceType dt = new DeviceType();
			dt.setName("父类");
			dt.setBaseType(baseType);
			deviceTypeDAO.save(dt);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	@Override
	@Transactional
	public int delete(long id) {
		// TODO Auto-generated method stub
		BaseType btype = baseTypeDAO.get(id);
		if(btype.getDeviceTypes()!=null && btype.getDeviceTypes().size()>0){
			return -1;
		}
		try {
			baseTypeDAO.deleteById(id);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	@Override
	@Transactional
	public int edit(BaseType baseType) {
		// TODO Auto-generated method stub
		try {
			baseTypeDAO.update(baseType);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	@Override
	@Transactional
	public List<BaseType> list() {
		// TODO Auto-generated method stub
		return baseTypeDAO.getListByHQL("from BaseType", null);
	}

	@Override
	@Transactional
	public BaseType get(long id) {
		// TODO Auto-generated method stub
		return baseTypeDAO.get(id);
	}

}
