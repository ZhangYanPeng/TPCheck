package cn.com.tpri.tpcheck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.tpri.tpcheck.entity.Account;
import cn.com.tpri.tpcheck.service.IAccountService;

@Controller
@RequestMapping(value="/app")
public class AppController {
	
	@Autowired
	IAccountService accountService;
	
	@RequestMapping(value = "/login")
	public @ResponseBody Account login(String username, String password){
		Account account =  accountService.login(username, password);
		if(account == null){
			account = new Account();
			account.setId(-1);
		}
		return account;
	}
}
