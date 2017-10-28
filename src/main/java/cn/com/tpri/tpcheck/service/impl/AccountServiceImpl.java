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
			return -2;
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
	public PageResults<Account> list(int page,long company) {
		// TODO Auto-generated method stub
		if(company>=0){
			String hql = "from Account where company.id = ?";
			String countHql = "select count(*) from Account where company.id = ?";
			Object[] values = {company};
			return accountDAO.findPageByFetchedHql(hql, countHql, page, Constants.PAGE_SIZE, values);
		}
		else{
			String hql = "from Account ";
			String countHql = "select count(*) from Account";
			Object[] values = {};
			return accountDAO.findPageByFetchedHql(hql, countHql, page, Constants.PAGE_SIZE, values);
		}
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

	@Override
	@Transactional
	public Account login(String username, String password) {
		// TODO Auto-generated method stub
		String hqlString = "from Account where username = ? and password = ?";
		Object[] values = {username, password};
		return accountDAO.getByHQL(hqlString, values);
	}

}
