package cn.com.tpri.tpcheck.controller;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonSerializer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.tpri.tpcheck.entity.Account;
import cn.com.tpri.tpcheck.entity.SuperDevice;
import cn.com.tpri.tpcheck.service.IAccountService;
import cn.com.tpri.tpcheck.service.ISuperDeviceService;

@Controller
@RequestMapping(value="/app")
public class AppController {
	
	@Autowired
	IAccountService accountService;
	@Autowired
	ISuperDeviceService superDeviceService;
	
	@RequestMapping(value = "/login")
	public @ResponseBody Account login(String username, String password){
		Account account =  accountService.login(username, password);
		if(account == null){
			account = new Account();
			account.setId(-1);
		}
		return account;
	}
	
	@RequestMapping(value = "/loadSupDevices")
	public @ResponseBody List<SuperDevice> loadSupDevices(String id){
		Account account =  accountService.load(Long.valueOf(id));
		return superDeviceService.list(account.getDepartment().getId());
	}
	
	
	@RequestMapping(value = "/loadCheckDevice")
	public @ResponseBody List<SuperDevice> loadCheckDevice(String devices, String type){
		List<SuperDevice> lSupDev= new ArrayList<SuperDevice>();
		System.out.println(devices);
		JSONArray jd = new JSONArray(devices);
		//支吊架
		for(Object d : jd){
			long id = Long.valueOf((String)d);
			SuperDevice sd = superDeviceService.getAllInformation(id,type);
			lSupDev.add(sd);
		}
		return lSupDev;
		
	}
}
