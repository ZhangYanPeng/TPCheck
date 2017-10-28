package cn.com.tpri.tpcheck.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tpri.tpcheck.dao.impl.AuthorityDAOImpl;
import cn.com.tpri.tpcheck.entity.Authority;
import cn.com.tpri.tpcheck.service.IAuthorityService;
@Service
public class AuthorityServiceImpl implements IAuthorityService{

	@Autowired
	AuthorityDAOImpl authorityDAO;
	
	@Override
	@Transactional
	public int add(Authority auth) {
		// TODO Auto-generated method stub
		try {
			authorityDAO.save(auth);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	@Override
	@Transactional
	public int delete(Authority auth) {
		// TODO Auto-generated method stub
		try {
			authorityDAO.delete(auth);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	@Override
	@Transactional
	public int edit(Authority auth) {
		// TODO Auto-generated method stub
		try {
			authorityDAO.saveOrUpdate(auth);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	@Override
	@Transactional
	public Authority load(long id) {
		// TODO Auto-generated method stub
		return authorityDAO.get(id);
	}

	@Override
	@Transactional
	public List<Authority> loadByAccount(Long aid) {
		// TODO Auto-generated method stub
		String hql = "from Authority where account.id = ?";
		Object[] values = {aid};
		return authorityDAO.getListByHQL(hql, values);
	}

}
