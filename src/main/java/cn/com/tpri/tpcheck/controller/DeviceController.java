package cn.com.tpri.tpcheck.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.com.tpri.tpcheck.entity.Admin;
import cn.com.tpri.tpcheck.service.IDeviceService;
import cn.com.tpri.tpcheck.support.DealExcel;

@Controller
@RequestMapping(value = "/device")
public class DeviceController {
	
	@Autowired
	IDeviceService deviceService;

	@RequestMapping(value = "/upload_devices")
	public @ResponseBody int uploadDevices(@RequestParam MultipartFile devices, HttpServletRequest request) {
		if(devices.isEmpty())
			return -1;
		try {
			String originalFilename = devices.getOriginalFilename();
			String genePath = request.getSession().getServletContext().getRealPath("/upload/devices_file/");
			FileUtils.copyInputStreamToFile(devices.getInputStream(), new File(genePath,originalFilename));
			List<List> info = DealExcel.loadIn(genePath+originalFilename);
			List<String> paramList = info.get(0);
			for(String str : paramList){
				System.out.println(str);
			}
			for(int i=1; i<info.size();i++){
				List<String> d = info.get(i);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -2;
		}
		return 0;
		
	}
}
