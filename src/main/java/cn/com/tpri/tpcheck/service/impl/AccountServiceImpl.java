package cn.com.tpri.tpcheck.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tpri.tpcheck.dao.impl.AccountDAOImpl;
import cn.com.tpri.tpcheck.entity.Account;
import cn.com.tpri.tpcheck.service.IAccountService;
import cn.com.tpri.tpcheck.support.Constants;
import cn.com.tpri.tpcheck.support.PageResults;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	AccountDAOImpl accountDAO;
	
	@Override
	@Transactional
	public int add(Account account) {
		// TODO Auto-generated method stub
		try {
			accountDAO.save(account);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public int frozen(Account account) {
		// TODO Auto-generated method stub
		try {
			account = accountDAO.get(account.getId());
			account.setState(-1);
			accountDAO.update(account);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public int active(Account account) {
		// TODO Auto-generated method stub
		try {
			account = accountDAO.get(account.getId());
			account.setState(1);
			accountDAO.update(account);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public PageResults<Account> list(int page,String company) {
		// TODO Auto-generated method stub
		String hql = "from Account where company.name like ?";
		String countHql = "select count(*) from Account where company.name like ?";
		company = "%"+company +"%";
		Object[] values = {company};
		return accountDAO.findPageByFetchedHql(hql, countHql, page, Constants.PAGE_SIZE, values);
	}

	@Override
	@Transactional
	public int edit(Account account) {
		// TODO Auto-generated method stub
		try {
			accountDAO.update(account);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public Account load(long id) {
		// TODO Auto-generated method stub
		return accountDAO.get(id);
	}

}
