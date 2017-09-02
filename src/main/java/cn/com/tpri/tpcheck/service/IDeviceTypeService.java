package cn.com.tpri.tpcheck.service;

import java.util.List;

import cn.com.tpri.tpcheck.entity.DeviceType;

public interface IDeviceTypeService {
	List<DeviceType> list(long btid);
	int add(DeviceType dType);
	int delete(long id);
	int edit(DeviceType dType);
	DeviceType load(long id);
}
