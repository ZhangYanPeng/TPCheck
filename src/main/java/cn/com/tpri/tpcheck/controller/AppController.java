package cn.com.tpri.tpcheck.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonSerializer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.com.tpri.tpcheck.entity.Account;
import cn.com.tpri.tpcheck.entity.Device;
import cn.com.tpri.tpcheck.entity.DeviceCheckItem;
import cn.com.tpri.tpcheck.entity.DeviceCheckRecord;
import cn.com.tpri.tpcheck.entity.DeviceInfo;
import cn.com.tpri.tpcheck.entity.Picture;
import cn.com.tpri.tpcheck.entity.Record;
import cn.com.tpri.tpcheck.entity.SuperDevice;
import cn.com.tpri.tpcheck.service.IAccountService;
import cn.com.tpri.tpcheck.service.IDeviceCheckItemService;
import cn.com.tpri.tpcheck.service.IDeviceCheckRecordService;
import cn.com.tpri.tpcheck.service.IDeviceService;
import cn.com.tpri.tpcheck.service.IPictureService;
import cn.com.tpri.tpcheck.service.ISuperDeviceService;
import cn.com.tpri.tpcheck.service.impl.DeviceCheckItemServiceImpl;
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
	@Autowired
	IPictureService pictureService;
	@Autowired
	IDeviceCheckItemService deviceCheckItemService;
	@Autowired
	IDeviceCheckRecordService deviceCheckRecordService;
	
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
	
	@RequestMapping(value = "/loadCheckItem")
	public @ResponseBody List<DeviceCheckItem> loadCheckItem(String id){
		return deviceService.loadCheckItems(Long.valueOf(id));
	}
	
	@RequestMapping(value = "/uploadRecPic")
	public @ResponseBody int uploadRecPic(@RequestParam MultipartFile file, HttpServletRequest request){
		Picture pic = new Picture();
		String originalFilename = file.getOriginalFilename();
		pic.setName(originalFilename);
		String genePath = request.getSession().getServletContext().getRealPath("/upload/record_pic/");
		pic.setSrc(request.getContextPath()+"/upload/record_pic/"+originalFilename);
		pic.setPath(genePath+"/"+originalFilename);
		pictureService.save(pic, file);
		return 0;
	}
	
	@RequestMapping(value = "/uploadRecord" , method = RequestMethod.POST)
	public @ResponseBody int uploadRecord(String rec) throws JSONException, ParseException{
		JSONObject jb = new JSONObject(rec);
		DeviceCheckRecord record = new DeviceCheckRecord();
		
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd H:m:s");
		Date date = sdf.parse(jb.getString("date"));
		record.setDate(date);
		record.setAccount(accountService.load(jb.getLong("aid")));
		record.setDevice(deviceService.load(jb.getLong("did")));
		try{
			if(jb.getString("ciid") != null && jb.getString("ciid") != "" && jb.getLong("ciid") >= 0)
				record.setDeviceCheckItem(deviceCheckItemService.load(jb.getLong("ciid")));
		}catch(Exception e){
			
		}
			
		record.setRecord(jb.getString("content"));
		
		int re = deviceCheckRecordService.add(record);
		record = deviceCheckRecordService.find(jb.getLong("aid"),jb.getLong("did"),date);
		try{
			if(jb.getString("pics") != null && jb.getString("pics") != "" ){
				String[] pics = jb.getString("pics").split(";");
				for (int i=1; i<pics.length;i++){
					Picture pic = pictureService.loadByName(pics[i]);
					pic.setDeviceCheckRecord(record);
					pictureService.updateInfo(pic);
				}
			}
		}catch(Exception e){
			
		}
		return re;
	}
}
