package cn.com.tpri.tpcheck.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.tpri.tpcheck.entity.Company;
import cn.com.tpri.tpcheck.service.ICompanyService;
import cn.com.tpri.tpcheck.support.PageResults;

@Controller
@RequestMapping(value = "/company")
public class CompanyController {
	
	@Autowired
	ICompanyService companyService;

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
}
