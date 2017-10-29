package cn.com.tpri.tpcheck.service;

import java.util.List;

import cn.com.tpri.tpcheck.entity.DeviceParam;

public interface IDeviceParamService {
	List<DeviceParam> list(long tid);
	int add(DeviceParam dParam);
	int delete(long id);
	int edit(DeviceParam dParam);
	DeviceParam load(long id);
	int inQR(long id, int state);
}
