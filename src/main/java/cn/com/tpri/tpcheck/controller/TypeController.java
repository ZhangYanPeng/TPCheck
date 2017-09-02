package cn.com.tpri.tpcheck.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.tpri.tpcheck.entity.BaseType;
import cn.com.tpri.tpcheck.entity.DeviceParam;
import cn.com.tpri.tpcheck.entity.DeviceType;
import cn.com.tpri.tpcheck.service.IBaseTypeService;
import cn.com.tpri.tpcheck.service.IDeviceParamService;
import cn.com.tpri.tpcheck.service.IDeviceTypeService;

@Controller
@RequestMapping(value = "/type")
public class TypeController {
	
	@Autowired
	IDeviceParamService deviceParamService;
	@Autowired
	IDeviceTypeService deviceTypeService;
	@Autowired
	IBaseTypeService baseTypeService;
	
	
	@RequestMapping(value = "/list_base_type")
	public @ResponseBody List<BaseType> listBaseType(){
		return baseTypeService.list();
	}
	
	@RequestMapping(value = "/save_base_type")
	public @ResponseBody int saveBaseType(String name){
		BaseType baseType = new BaseType();
		baseType.setName(name);
		return baseTypeService.add(baseType);
	}
	
	@RequestMapping(value = "/edit_base_type")
	public @ResponseBody int saveBaseType(String id, String name){
		BaseType baseType = baseTypeService.get(Long.valueOf(id));
		baseType.setName(name);
		return baseTypeService.edit(baseType);
	}
	
	@RequestMapping(value = "/delete_base_type")
	public @ResponseBody int deleteBaseType(String id){
		return baseTypeService.delete(Long.valueOf(id));
	}
	
	@RequestMapping(value = "/list_type")
	public @ResponseBody List<DeviceType> listType( String baseType){
		return deviceTypeService.list(Long.valueOf(baseType));
	}
	
	@RequestMapping(value = "/delete_type")
	public @ResponseBody int deleteType(String id){
		return deviceTypeService.delete(Long.valueOf(id));
	}
	
	@RequestMapping(value = "/load_type")
	public @ResponseBody DeviceType loadType(String id){
		return deviceTypeService.load(Long.valueOf(id));
	}
	
	@RequestMapping(value = "/edit_type")
	public @ResponseBody int saveType(String id, String name, String baseType){
		BaseType bt = baseTypeService.get(Long.valueOf(baseType));
		long tid = Long.valueOf(id);
		if(tid == -1){
			DeviceType deviceType = new DeviceType();
			deviceType.setName(name);
			deviceType.setBaseType(bt);
			return deviceTypeService.add(deviceType);
		}else{
			DeviceType deviceType = deviceTypeService.load(tid);
			deviceType.setName(name);
			deviceType.setBaseType(bt);
			return deviceTypeService.edit(deviceType);
		}
	}
}
