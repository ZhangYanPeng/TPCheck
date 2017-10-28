package cn.com.tpri.tpcheck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.tpri.tpcheck.entity.Admin;
import cn.com.tpri.tpcheck.service.IAdminService;
import cn.com.tpri.tpcheck.support.PageResults;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	@Autowired
	IAdminService adminService;
	
	@RequestMapping(value = "/login")
	public @ResponseBody Admin login(String username, String password){
		Admin admin = adminService.login(username, password);
		if(admin == null){
			admin = new Admin();
			admin.setId((long)-1);
		}
		return admin;
	}
	
	@RequestMapping(value = "/list_admin")
	public @ResponseBody PageResults<Admin> listAdmin(String page){
		return adminService.getByPage(Integer.valueOf(page));
	}
	
	@RequestMapping(value = "/load_admin")
	public @ResponseBody Admin loadAdmin(String id){
		Admin admin =  adminService.load(Long.valueOf(id));
		return admin;
	}
	
	@RequestMapping(value = "/edit_admin")
	public @ResponseBody int editAdmin(String id, String username, String password){
		try {
			long aid = Long.valueOf(id);
			Admin admin = new Admin();
			admin.setUsername(username);
			admin.setPassword(password);
			if( aid == (long)-1 ){
				return adminService.add(admin);
			}
			else{
				admin.setId(aid);
				return adminService.edit(admin);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return -2;
		}
	}
	
	@RequestMapping(value = "/delete_admin")
	public @ResponseBody int deleteAdmin(String id){
		return adminService.delete(Long.valueOf(id));
	}

}
