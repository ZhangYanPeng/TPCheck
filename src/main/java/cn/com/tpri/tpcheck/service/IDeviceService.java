package cn.com.tpri.tpcheck.service;

import java.util.List;
import java.util.Map;

import cn.com.tpri.tpcheck.entity.Device;
import cn.com.tpri.tpcheck.entity.DeviceInfo;
import cn.com.tpri.tpcheck.entity.DeviceParam;
import cn.com.tpri.tpcheck.support.PageResults;

public interface IDeviceService {
	int importDevice(List<Device> lDevice);
	Device load(long id);
	int add(Device device);
	int delete(long device);
	int edit(Device device);
	PageResults<Device> list(int page, long did, long btid);
	int loadInSuperDevice(List<List> info, long did, long btid);
	int loadInSubDevice(List<List> info, Long did, Long btid);
	List<DeviceParam> loadParams(long id);
	List<DeviceInfo> loadInfos(long id);
	void update(Map param);
}
