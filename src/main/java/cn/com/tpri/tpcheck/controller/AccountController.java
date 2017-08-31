package cn.com.tpri.tpcheck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.tpri.tpcheck.dao.impl.AccountDAOImpl;
import cn.com.tpri.tpcheck.entity.Account;
import cn.com.tpri.tpcheck.entity.Company;
import cn.com.tpri.tpcheck.service.IAccountService;
import cn.com.tpri.tpcheck.service.ICompanyService;
import cn.com.tpri.tpcheck.support.PageResults;

@Controller
@RequestMapping(value = "/account")
public class AccountController {

	@Autowired
	IAccountService accountService;
	@Autowired
	ICompanyService companyService;

	@RequestMapping(value = "/list_account")
	public @ResponseBody PageResults<Account> listAccount(String page, String company) {
		return accountService.list(Integer.valueOf(page), company);
	}

	@RequestMapping(value = "/active")
	public @ResponseBody int active(String id, String state) {
		Account account = new Account();
		account.setId(Long.valueOf(id));
		int s = Integer.valueOf(state);
		if (s == 1) {
			return accountService.active(account);
		} else {
			return accountService.frozen(account);
		}
	}

	@RequestMapping(value = "/load_account")
	public @ResponseBody Account loadAccount(String id) {
		return accountService.load(Long.valueOf(id));
	}

	@RequestMapping(value = "/edit_account")
	public @ResponseBody int editAccount(String id, String username, String password, String department,
			String position, String company) {
		Account account;
		Long aid = Long.valueOf(id);
		if(aid == -1){
			account = new Account();
			account.setUsername(username);
			account.setPassword(password);
			account.setDepartment(department);
			account.setPosition(position);
			account.setState(1);
			Company com = companyService.load(Long.valueOf(company));
			account.setCompany(com);
			return accountService.add(account);
		}else{
			account = accountService.load(aid);
			account.setUsername(username);
			account.setPassword(password);
			account.setDepartment(department);
			account.setPosition(position);
			Company com = companyService.load(Long.valueOf(company));
			account.setCompany(com);
			return accountService.edit(account);
		}
	}
}
