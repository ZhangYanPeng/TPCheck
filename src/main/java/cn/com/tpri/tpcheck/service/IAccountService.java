package cn.com.tpri.tpcheck.service;

import cn.com.tpri.tpcheck.entity.Account;
import cn.com.tpri.tpcheck.support.PageResults;

public interface IAccountService {
	int add(Account account);
	int frozen(Account account);
	int active(Account account);
	PageResults<Account> list(int page, long department);
	int edit(Account account);
	Account load(long id);
}
