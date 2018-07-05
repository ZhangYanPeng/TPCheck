package cn.com.tpri.tpcheck.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.com.tpri.tpcheck.entity.Account;
import cn.com.tpri.tpcheck.entity.Blog;
import cn.com.tpri.tpcheck.entity.Company;
import cn.com.tpri.tpcheck.entity.Department;
import cn.com.tpri.tpcheck.entity.Device;
import cn.com.tpri.tpcheck.entity.DeviceCheckItem;
import cn.com.tpri.tpcheck.entity.DeviceCheckRecord;
import cn.com.tpri.tpcheck.entity.Picture;
import cn.com.tpri.tpcheck.entity.Question;
import cn.com.tpri.tpcheck.entity.SuperDevice;
import cn.com.tpri.tpcheck.service.IAccountService;
import cn.com.tpri.tpcheck.service.IAuthorityService;
import cn.com.tpri.tpcheck.service.IBlogService;
import cn.com.tpri.tpcheck.service.ICompanyService;
import cn.com.tpri.tpcheck.service.IDepartmentService;
import cn.com.tpri.tpcheck.service.IDeviceCheckItemService;
import cn.com.tpri.tpcheck.service.IDeviceCheckRecordService;
import cn.com.tpri.tpcheck.service.IDeviceService;
import cn.com.tpri.tpcheck.service.IPictureService;
import cn.com.tpri.tpcheck.service.IQuestionService;
import cn.com.tpri.tpcheck.service.ISuperDeviceService;
import cn.com.tpri.tpcheck.service.impl.DepartmentServiceImpl;
import cn.com.tpri.tpcheck.store.DeviceStore;
import cn.com.tpri.tpcheck.store.QuestionStore;
import cn.com.tpri.tpcheck.store.RecordStore;
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
	@Autowired
	IBlogService blogService;
	@Autowired
	IAuthorityService authorityService;
	@Autowired
	ICompanyService companyService;
	@Autowired
	IDepartmentService departmentService;
	@Autowired
	IQuestionService questionService;
	
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
		return superDeviceService.listByAccount(Long.valueOf(id));
	}
	
	@RequestMapping(value = "/loadAllComs")
	public @ResponseBody List<Company> loadAllComs(String id){
		return companyService.getByAccount(Long.valueOf(id));
	}
	
	@RequestMapping(value = "/loadAllDeps")
	public @ResponseBody List<Department> loadAllDeps(String comid,String accid){
		return departmentService.listByAccount(Long.valueOf(comid),Long.valueOf(accid));
	}
	
	@RequestMapping(value = "/loadAllDevs")
	public @ResponseBody List<SuperDevice> loadAllDevs(String id){
		List<SuperDevice> lsd = superDeviceService.listByDep(Long.valueOf(id));
		return lsd;
	}
	
	@RequestMapping(value = "/loadAllSubDevs")
	public @ResponseBody List<Device> loadAllSubDevs(String id){
		return deviceService.loadBySupDev(Long.valueOf(id));
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
	public @ResponseBody DeviceStore loadDeviceInfo(String aid, String id){
		List<DeviceCheckRecord> ldcr= deviceService.loadDeviceCheckRecords(Long.valueOf(id));
		for( DeviceCheckRecord dcr : ldcr){
			try{
				if(dcr.getImgStr() != null && dcr.getImgStr() != "" ){
					String[] pics = dcr.getImgStr().split(";");
					for (int i=1; i<pics.length;i++){
						Picture pic = pictureService.loadByName(pics[i]);
						if(pic != null){
							pic.setDeviceCheckRecord(dcr);
							pictureService.updateInfo(pic);
						}
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return deviceService.loadDeviceInfos(Long.valueOf(aid),Long.valueOf(id));
	}
	
	@RequestMapping(value = "/loadDevicePic")
	public @ResponseBody List<Picture> loadDevicePic(String id){
		return deviceService.loadDevicePic(Long.valueOf(id));
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
		
		Device dev = deviceService.load(jb.getLong("did"));
		if( authorityService.checkAuthority(jb.getLong("aid"), dev.getSuperDevice().getDepartment().getId()) == 0 )
			return 0;

		SimpleDateFormat sdf= new SimpleDateFormat("yyyy年MM月dd日 H时m分s秒");
		Date date = sdf.parse(jb.getString("date"));
		record.setDate(date);
		record.setAccount(accountService.load(jb.getLong("aid")));
		record.setDevice(dev);

		record.setRecord(jb.getString("content"));
		record.setImgStr(jb.getString("pics"));

		int re = deviceCheckRecordService.add(record);
		record = deviceCheckRecordService.find(jb.getLong("aid"),jb.getLong("did"),date);
		try{
			if(jb.getString("pics") != null && jb.getString("pics") != "" ){
				String[] pics = jb.getString("pics").split(";");
				for (int i=1; i<pics.length;i++){
					Picture pic = pictureService.loadByName(pics[i]);
					if(pic != null){
						pic.setDeviceCheckRecord(record);
						pictureService.updateInfo(pic);
					}
				}
			}
		}catch(Exception e){
			
		}
		return re;
	}
	
	@RequestMapping(value = "/delRecord")
	public @ResponseBody int delRecord(String rid, String aid){
		return deviceCheckRecordService.delete(Long.valueOf(rid),Long.valueOf(aid));
	}
	
	@RequestMapping(value = "/loadRecord")
	public @ResponseBody RecordStore loadRecord(String rid){	
		return deviceCheckRecordService.loadRecrod(Long.valueOf(rid));
	}
	
	@RequestMapping(value = "/modifyPassword")
	public @ResponseBody Account modifyPassword(String id, String pwd){
		Account account = accountService.load(Long.valueOf(id));
		account.setPassword(pwd);
		accountService.edit(account);
		return accountService.load(Long.valueOf(id));
	}
	
	@RequestMapping(value = "/getLastBlog")
	public @ResponseBody List<Blog> getLastBlog(String n){
		return blogService.lastBlog(Integer.valueOf(n));
	}
	
	@RequestMapping(value = "/getBlogPic")
	public @ResponseBody Picture getBlogPic(String id){
		return blogService.getBlogPic(Long.valueOf(id));
	}
	
	@RequestMapping(value = "/uploadQuesPic")
	public @ResponseBody int uploadQuesPic(@RequestParam MultipartFile file, HttpServletRequest request){
		Picture pic = new Picture();
		String originalFilename = file.getOriginalFilename();
		pic.setName(originalFilename);
		String genePath = request.getSession().getServletContext().getRealPath("/upload/record_pic/");
		pic.setSrc(request.getContextPath()+"/upload/record_pic/"+originalFilename);
		pic.setPath(genePath+"/"+originalFilename);
		pictureService.save(pic, file);
		return 0;
	}
	
	@RequestMapping(value = "/uploadQuestion" , method = RequestMethod.POST)
	public @ResponseBody int uploadQuestion(String que) throws JSONException, ParseException{
		JSONObject jb = new JSONObject(que);
		Question question = new Question();
		
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy年MM月dd日 H时m分s秒");
		Date date = sdf.parse(jb.getString("date"));
		question.setDate(date);
		question.setAccount(accountService.load(jb.getLong("aid")));

		question.setRecord(jb.getString("content"));
		question.setImgStr(jb.getString("pics"));

		int re = questionService.add(question);
		question = questionService.find(jb.getLong("aid"),date);
		try{
			if(jb.getString("pics") != null && jb.getString("pics") != "" ){
				String[] pics = jb.getString("pics").split(";");
				for (int i=1; i<pics.length;i++){
					Picture pic = pictureService.loadByName(pics[i]);
					if(pic != null){
						pic.setQuestion(question);;
						pictureService.updateInfo(pic);
					}
				}
			}
		}catch(Exception e){
			
		}
		return re;
	}
	
	@RequestMapping(value = "/loadQues")
	public @ResponseBody List<QuestionStore> loadQues(){
		List<Question> lq = questionService.getQuestions();
		List<QuestionStore> lqs = new ArrayList<QuestionStore>();
		for(Question q : lq){
			lqs.add(new QuestionStore(q));
		}
		return lqs;
	}
	
}
