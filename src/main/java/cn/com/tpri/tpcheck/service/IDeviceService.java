package cn.com.tpri.tpcheck.service;

import java.util.List;

import cn.com.tpri.tpcheck.entity.Device;
import cn.com.tpri.tpcheck.support.PageResults;

public interface IDeviceService {
	int importDevice(List<Device> lDevice);
	Device load(long id);
	int add(Device device);
	int delete(long device);
	int edit(Device device);
	PageResults<Device> list(int page, long cid, long btid);
}
