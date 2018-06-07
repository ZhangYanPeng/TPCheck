package cn.com.tpri.tpcheck.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tpri.tpcheck.dao.impl.AccountDAOImpl;
import cn.com.tpri.tpcheck.dao.impl.AuthorityDAOImpl;
import cn.com.tpri.tpcheck.dao.impl.DepartmentDAOImpl;
import cn.com.tpri.tpcheck.entity.Account;
import cn.com.tpri.tpcheck.entity.Department;
import cn.com.tpri.tpcheck.entity.SuperDevice;
import cn.com.tpri.tpcheck.service.IDepartmentService;
import cn.com.tpri.tpcheck.support.Constants;
import cn.com.tpri.tpcheck.support.PageResults;

@Service
public class DepartmentServiceImpl implements IDepartmentService{

	@Autowired
	DepartmentDAOImpl departmentDAO;
	@Autowired
	AuthorityDAOImpl authorityDAO;
	@Autowired
	AccountDAOImpl accountDAO;
	
	@Override
	@Transactional
	public PageResults<Department> getByPage(int page, long cid) {
		// TODO Auto-generated method stub
		if(cid>=0){
			String hql = "from Department where company.id = ?";
			String countHql = "select count(*) from Department where company.id = ?";
			Object[] values = {cid};
			return departmentDAO.findPageByFetchedHql(hql, countHql, page, Constants.PAGE_SIZE, values);
		}
		else{
			String hql = "from Department";
			String countHql = "select count(*) from Department";
			Object[] values = {};
			return departmentDAO.findPageByFetchedHql(hql, countHql, page, Constants.PAGE_SIZE, values);
		}
	}

	@Override
	@Transactional
	public Department load(long id) {
		// TODO Auto-generated method stub
		return departmentDAO.get(id);
	}

	@Override
	@Transactional
	public int add(Department department) {
		// TODO Auto-generated method stub
		try {
			departmentDAO.save(department);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public int edit(Department department) {
		// TODO Auto-generated method stub
		try {
			departmentDAO.update(department);
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
			Set<SuperDevice> lds = departmentDAO.get(id).getSuperDevices();
			if(lds != null && lds.size()>1 )
				return -1;
			departmentDAO.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public List<Department> list(long cid) {
		// TODO Auto-generated method stub
		String hqlString = "from Department where company.id = ?";
		Object[] values = {cid};
		return departmentDAO.getListByHQL(hqlString, values);
	}

	@Override
	@Transactional
	public List<Department> listByAccount(Long cid, Long aid) {
		// TODO Auto-generated method stub
		String hqlString = "from Department where company.id = ?";
		Object[] values = {cid};
		List<Department> ld = departmentDAO.getListByHQL(hqlString, values);
		Account account = accountDAO.get(aid);
		if(account.getCompany().getId()==0)
			return ld;
		for(int i=0; i<ld.size(); i++){
			Department dep = ld.get(i);
			String hql = "from Authority where account.id=? and department.id=?";
			Object[] vals = {aid, dep.getId()};
			if( authorityDAO.getListByHQL(hql, vals)==null || authorityDAO.getListByHQL(hql, vals).size()==0){
				ld.remove(dep);
				i--;
			}
		}
		return ld;
	}

}
