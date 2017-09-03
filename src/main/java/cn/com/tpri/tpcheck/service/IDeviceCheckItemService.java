package cn.com.tpri.tpcheck.service;

import java.util.List;

import cn.com.tpri.tpcheck.entity.DeviceCheckItem;


public interface IDeviceCheckItemService {
	List<DeviceCheckItem> list(long tid);
	int add(DeviceCheckItem dci);
	int delete(long id);
	int edit(DeviceCheckItem dci);
	DeviceCheckItem load(long id);
}
