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
import cn.com.tpri.tpcheck.entity.DeviceInfo;
import cn.com.tpri.tpcheck.entity.DeviceParam;
import cn.com.tpri.tpcheck.entity.Picture;
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

	@RequestMapping(value = "/upload_devices")
	public @ResponseBody int uploadDevices(@RequestParam String type, @RequestParam String btid, @RequestParam String did, @RequestParam MultipartFile devices, HttpServletRequest request) {
		if(devices.isEmpty())
			return -1;
		try {
			String originalFilename = devices.getOriginalFilename();
			String genePath = request.getSession().getServletContext().getRealPath("/upload/devices_file/");
			FileUtils.copyInputStreamToFile(devices.getInputStream(), new File(genePath,originalFilename));
			List<List> info = DealExcel.loadIn(genePath+originalFilename);
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
	public @ResponseBody PageResults<Device> listDevice(String page, String did, String btid){
		return deviceService.list(Integer.valueOf(page), Long.valueOf(did), Long.valueOf(btid));
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
		pic.setSuperDevice(d.getSuperDevice());
		pic.setName(picname);
		String originalFilename = picture.getOriginalFilename();
		String genePath = request.getSession().getServletContext().getRealPath("/upload/devices_file/");
		pic.setSrc("/upload/supdevice/"+originalFilename);
		pic.setPath(genePath+originalFilename);
		pictureService.save(pic, picture);
		return 0;
	}
}
