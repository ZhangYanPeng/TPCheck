package cn.com.tpri.tpcheck.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tpri.tpcheck.dao.impl.CompanyDAOImpl;
import cn.com.tpri.tpcheck.entity.Company;
import cn.com.tpri.tpcheck.service.ICompanyService;
import cn.com.tpri.tpcheck.support.Constants;
import cn.com.tpri.tpcheck.support.PageResults;

@Service
public class CompanyServiceImpl implements ICompanyService {

	@Autowired
	CompanyDAOImpl companyDAO;
	
	@Override
	@Transactional
	public PageResults<Company> getByPage(int page) {
		// TODO Auto-generated method stub
		String hql = "from Company";
		String countHql = "select count(*) from Company";
		Object[] values = {};
		return companyDAO.findPageByFetchedHql(hql, countHql, page, Constants.PAGE_SIZE, values);
	}

	@Override
	@Transactional
	public Company load(long id) {
		// TODO Auto-generated method stub
		return companyDAO.get(id);
	}

	@Override
	@Transactional
	public int add(Company company) {
		// TODO Auto-generated method stub
		try {
			companyDAO.save(company);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public int edit(Company company) {
		// TODO Auto-generated method stub
		try {
			companyDAO.update(company);
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
			Company company = companyDAO.get(id);
			if( company.getAccounts().size() >0 ){
				return -1;
			}
			companyDAO.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

}
