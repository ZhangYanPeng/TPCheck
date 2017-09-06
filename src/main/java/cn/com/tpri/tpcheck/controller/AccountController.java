package cn.com.tpri.tpcheck.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.tpri.tpcheck.entity.Account;
import cn.com.tpri.tpcheck.entity.Company;
import cn.com.tpri.tpcheck.entity.Department;
import cn.com.tpri.tpcheck.service.IAccountService;
import cn.com.tpri.tpcheck.service.ICompanyService;
import cn.com.tpri.tpcheck.service.IDepartmentService;
import cn.com.tpri.tpcheck.support.PageResults;

@Controller
@RequestMapping(value = "/account")
public class AccountController {

	@Autowired
	IAccountService accountService;
	@Autowired
	ICompanyService companyService;
	@Autowired
	IDepartmentService departmentService;

	@RequestMapping(value = "/list_company")
	public @ResponseBody PageResults<Company> listCompany(String page){
		return companyService.getByPage(Integer.valueOf(page));
	}
	
	@RequestMapping(value = "/load_company")
	public @ResponseBody Company loadCompany(String id){
		return companyService.load(Long.valueOf(id));
	}
	
	@RequestMapping(value = "/edit_company")
	public @ResponseBody int editCompany(String id, String name, String location, String description, String permission_date) {
		long cid = Long.valueOf(id);
		Company company = new Company();
		company.setName(name);
		company.setLocation(location);
		company.setDescription(description);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟   
		Date date;
		try {
			date = sdf.parse(permission_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return -2;
		}
		company.setPermission_date(date.getTime());
		if( cid == (long)-1 ){
			return companyService.add(company);
		}
		else{
			company.setId(cid);
			return companyService.edit(company);
		}
	}
	
	@RequestMapping(value = "/delete_company")
	public @ResponseBody int deleteCompany(String id){
		return companyService.delete(Long.valueOf(id));
	}
	
	@RequestMapping(value = "/getAllCompany")
	public @ResponseBody List<Company> getAllCompany(){
		return companyService.list();
	}
	
	@RequestMapping(value = "/list_department")
	public @ResponseBody PageResults<Department> listDepartment(String page, String company){
		try{
			return departmentService.getByPage(Integer.valueOf(page),Long.valueOf(company));
		}catch (Exception e) {
			// TODO: handle exception
			return departmentService.getByPage(Integer.valueOf(page),Long.valueOf(-1));
		}
	}
	
	@RequestMapping(value = "/load_department")
	public @ResponseBody Department loadDepartment(String id){
		return departmentService.load(Long.valueOf(id));
	}
	
	@RequestMapping(value = "/edit_department")
	public @ResponseBody int editDepartment(String id, String name, String company) {
		long did = Long.valueOf(id);
		Department department = new Department();
		department.setName(name);
		department.setCompany(companyService.load(Long.valueOf(company)));
		if( did == (long)-1 ){
			return departmentService.add(department);
		}
		else{
			department.setId(did);
			return departmentService.edit(department);
		}
	}
	
	@RequestMapping(value = "/delete_department")
	public @ResponseBody int deleteDepartment(String id){
		return departmentService.delete(Long.valueOf(id));
	}
	
	@RequestMapping(value = "/getAllDepartment")
	public @ResponseBody List<Department> getDepartment(String company){
		return departmentService.list(Long.valueOf(company));
	}

	@RequestMapping(value = "/list_account")
	public @ResponseBody PageResults<Account> listAccount(String page,String department) {
		return accountService.list(Integer.valueOf(page), Long.valueOf(department));
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
			String position, String authority) {
		try{
			Account account;
			Long aid = Long.valueOf(id);
			if(aid == -1){
				account = new Account();
				account.setUsername(username);
				account.setPassword(password);
				account.setPosition(position);
				account.setState(1);
				account.setAuthority(Integer.valueOf(authority));
				Department dep = departmentService.load(Long.valueOf(department));
				account.setDepartment(dep);
				return accountService.add(account);
			}else{
				account = accountService.load(aid);
				account.setUsername(username);
				account.setPassword(password);
				account.setPosition(position);
				account.setAuthority(Integer.valueOf(authority));
				Department dep = departmentService.load(Long.valueOf(department));
				account.setDepartment(dep);
				return accountService.edit(account);
			}
		}catch (Exception e) {
			// TODO: handle exception
			return -2;
		}
	}
}
