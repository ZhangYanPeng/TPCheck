package cn.com.tpri.tpcheck.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.tpri.tpcheck.entity.DeviceParam;
import cn.com.tpri.tpcheck.entity.DeviceType;
import cn.com.tpri.tpcheck.service.IDeviceParamService;
import cn.com.tpri.tpcheck.service.IDeviceTypeService;

@Controller
@RequestMapping(value = "/device")
public class DeviceController {
	
	@Autowired
	IDeviceParamService deviceParamService;
	@Autowired
	IDeviceTypeService deviceTypeService;
	
	
	@RequestMapping(value = "/list_dtype")
	public @ResponseBody List<DeviceType> listDtype(){
		return deviceTypeService.list();
	}
}
