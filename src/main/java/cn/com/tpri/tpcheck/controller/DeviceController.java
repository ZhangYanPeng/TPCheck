package cn.com.tpri.tpcheck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.com.tpri.tpcheck.service.IDeviceService;

@Controller
public class DeviceController {
	
	@Autowired
	IDeviceService deviceService;

}
