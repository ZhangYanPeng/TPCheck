package cn.com.tpri.tpcheck.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonSerializer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.tpri.tpcheck.entity.Account;
import cn.com.tpri.tpcheck.entity.DeviceInfo;
import cn.com.tpri.tpcheck.entity.SuperDevice;
import cn.com.tpri.tpcheck.service.IAccountService;
import cn.com.tpri.tpcheck.service.IDeviceService;
import cn.com.tpri.tpcheck.service.ISuperDeviceService;
import cn.com.tpri.tpcheck.store.SuperDeviceStore;

@Controller
@RequestMapping(value="/app")
public class AppController {
	
	@Autowired
	IAccountService accountService;
	@Autowired
	ISuperDeviceService superDeviceService;
	@Autowired
	IDeviceService deviceService;
	
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
	public @ResponseBody List<SuperDeviceStore> loadCheckDevice(String devices, String type){
		List<SuperDeviceStore> lSupDev = new ArrayList<SuperDeviceStore>();
		JSONArray ja = new JSONArray(devices);
		for(Object d : ja){
			long id = Long.valueOf((String)d);
			SuperDeviceStore sd = superDeviceService.getAllInformation(id,type);
			lSupDev.add(sd);
		}
		return lSupDev;
	}
	
	@RequestMapping(value = "/loadDeviceInfo")
	public @ResponseBody List<DeviceInfo> loadDeviceInfo(String id){
		return deviceService.loadInfos(Long.valueOf(id));
	}
}
