package cn.com.tpri.tpcheck.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tpri.tpcheck.dao.impl.AdminDAOImpl;
import cn.com.tpri.tpcheck.entity.Admin;
import cn.com.tpri.tpcheck.service.IAdminService;
import cn.com.tpri.tpcheck.support.Constants;
import cn.com.tpri.tpcheck.support.PageResults;

@Service
public class AdminServiceImpl implements IAdminService {
	@Autowired
	AdminDAOImpl adminDAO;

	@Override
	@Transactional
	public Admin login(String username, String password) {
		// TODO Auto-generated method stub
		String hqlString = "from Admin where username = ? and password = ?";
		Object[] values = {username, password};
		return adminDAO.getByHQL(hqlString, values);
	}

	@Override
	@Transactional
	public PageResults<Admin> getByPage(int page) {
		// TODO Auto-generated method stub
		String hql = "from Admin";
		String countHql = "select count(*) from Admin";
		Object[] values = {};
		return adminDAO.findPageByFetchedHql(hql, countHql, page, Constants.PAGE_SIZE, values);
	}

	@Override
	@Transactional
	public Admin load(long id) {
		// TODO Auto-generated method stub
		return adminDAO.get(id);
	}

	@Override
	@Transactional
	public int add(Admin admin) {
		// TODO Auto-generated method stub
		try {
			adminDAO.save(admin);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public int edit(Admin admin) {
		// TODO Auto-generated method stub
		try {
			adminDAO.update(admin);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public int delete(Long id) {
		// TODO Auto-generated method stub
		try {
			adminDAO.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

}
