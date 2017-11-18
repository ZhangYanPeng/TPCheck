package cn.com.tpri.tpcheck.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tpri.tpcheck.dao.impl.AccountDAOImpl;
import cn.com.tpri.tpcheck.dao.impl.AuthorityDAOImpl;
import cn.com.tpri.tpcheck.dao.impl.SuperDeviceDAOImpl;
import cn.com.tpri.tpcheck.entity.Account;
import cn.com.tpri.tpcheck.entity.Authority;
import cn.com.tpri.tpcheck.entity.SuperDevice;
import cn.com.tpri.tpcheck.service.ISuperDeviceService;
import cn.com.tpri.tpcheck.store.SuperDeviceStore;

@Service
public class SuperDeviceServiceImpl implements ISuperDeviceService {

	@Autowired
	SuperDeviceDAOImpl superDeviceDAO; 
	@Autowired
	AccountDAOImpl accountDAO;
	@Autowired
	AuthorityDAOImpl authorityDAO;
	
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
	public SuperDeviceStore getAllInformation(long id, String type) {
		// TODO Auto-generated method stub
		SuperDevice sd = superDeviceDAO.get(id);
		return new SuperDeviceStore(sd,type);
	}

	@Override
	@Transactional
	public List<SuperDevice> listByAccount(long id) {
		// TODO Auto-generated method stub
		Account account = accountDAO.get(id);
		String ahql = "from Authority where account.id = ?";
		Object[] aval = {id};
		List<Authority> la = authorityDAO.getListByHQL(ahql, aval);
		List<SuperDevice> lsd = new ArrayList<SuperDevice>();
		for(Authority auth : la){
			String sdhql = "from SuperDevice where department.id = ?";
			Object[] sdval = {auth.getDepartment().getId()};
			lsd.addAll(superDeviceDAO.getListByHQL(sdhql, sdval));
		}
		return lsd;
		
	}

}
