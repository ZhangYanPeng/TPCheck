package cn.com.tpri.tpcheck.controller;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.com.tpri.tpcheck.entity.Admin;
import cn.com.tpri.tpcheck.entity.Device;
import cn.com.tpri.tpcheck.entity.DeviceCheckRecord;
import cn.com.tpri.tpcheck.entity.DeviceInfo;
import cn.com.tpri.tpcheck.entity.DeviceParam;
import cn.com.tpri.tpcheck.entity.Picture;
import cn.com.tpri.tpcheck.service.IDeviceCheckRecordService;
import cn.com.tpri.tpcheck.service.IDeviceService;
import cn.com.tpri.tpcheck.service.IPictureService;
import cn.com.tpri.tpcheck.support.DealExcel;
import cn.com.tpri.tpcheck.support.PageResults;

@Controller
@RequestMapping(value = "/device")
public class DeviceController {
	
	@Autowired
	IDeviceService deviceService;
	@Autowired
	IPictureService pictureService;
	@Autowired
	IDeviceCheckRecordService deviceCheckRecordService;

	@RequestMapping(value = "/upload_devices")
	public @ResponseBody int uploadDevices(@RequestParam String type, @RequestParam String btid, @RequestParam String did, @RequestParam MultipartFile devices, HttpServletRequest request) {
		if(devices.isEmpty())
			return -1;
		try {
			String originalFilename = devices.getOriginalFilename();
			String genePath = request.getSession().getServletContext().getRealPath("/upload/devices_file/");
			FileUtils.copyInputStreamToFile(devices.getInputStream(), new File(genePath,originalFilename));
			List<List> info = DealExcel.loadIn(genePath+"/"+originalFilename);
			if(type.equals("0")){
				return deviceService.loadInSuperDevice(info, Long.valueOf(did), Long.valueOf(btid));
			}
			else{
				return deviceService.loadInSubDevice(info, Long.valueOf(did), Long.valueOf(btid));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}
	}
	
	@RequestMapping(value = "/list_device")
	public @ResponseBody PageResults<Device> listDevice(String page, String did, String btid, String device){
		return deviceService.list(Integer.valueOf(page), Long.valueOf(did), Long.valueOf(btid),device);
	}
	
	@RequestMapping(value = "/delete_device")
	public @ResponseBody int deleteDevice(String id){
		return deviceService.delete(Long.valueOf(id));
	}
	
	@RequestMapping(value = "/load_params")
	public @ResponseBody List<DeviceParam> loadParams(String id){
		return deviceService.loadParams(Long.valueOf(id));
	}
	
	@RequestMapping(value = "/load_infos")
	public @ResponseBody List<DeviceInfo> loadInfos(String id){
		return deviceService.loadInfos(Long.valueOf(id));
	}
	
	@RequestMapping(value = "/edit_device")
	public @ResponseBody int editDevice(HttpServletRequest request){
		Enumeration em = request.getParameterNames();
		Map param = new HashMap();
		while (em.hasMoreElements()) {
			String name = (String) em.nextElement();
			String value = request.getParameter(name);
			param.put(name, value);
		}
		deviceService.update(param);
		return 0;
	}
	
	@RequestMapping(value = "/upload_pic")
	public @ResponseBody int uploadPic(@RequestParam String id, @RequestParam String picname, @RequestParam MultipartFile picture, HttpServletRequest request){
		Picture pic = new Picture();
		Device d = deviceService.load(Long.valueOf(id));
		pic.setDevice(d);
		pic.setName(picname);
		String originalFilename = System.currentTimeMillis()+"-"+picture.getOriginalFilename();
		String genePath = request.getSession().getServletContext().getRealPath("/upload/device/");
		pic.setSrc(request.getContextPath()+"/upload/device/"+originalFilename);
		pic.setPath(genePath+"/"+originalFilename);
		pictureService.save(pic, picture);
		return 0;
	}
	
	@RequestMapping(value = "/load_dev_pic")
	public @ResponseBody List<Picture> loadDevPic(String id){
		return pictureService.getDevPic(Long.valueOf(id));
	}
	
	@RequestMapping(value = "/remove_pic")
	public @ResponseBody int removePic(String id){
		return pictureService.delete(Long.valueOf(id));
	}
	
	@RequestMapping(value = "/list_check_record")
	public @ResponseBody PageResults<DeviceCheckRecord> listCheckRecord(String page, String did, String btid, String device){
		return deviceCheckRecordService.list(device, Integer.valueOf(page), Long.valueOf(did), Long.valueOf(btid));
	}
	
	@RequestMapping(value = "/load_rec_pic")
	public @ResponseBody List<Picture> loadRecPic(String id){
		return pictureService.getRecPic(Long.valueOf(id));
	}
}
