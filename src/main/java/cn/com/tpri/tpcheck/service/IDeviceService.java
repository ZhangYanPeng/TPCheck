package cn.com.tpri.tpcheck.service;

import java.util.List;
import java.util.Map;

import cn.com.tpri.tpcheck.entity.Device;
import cn.com.tpri.tpcheck.entity.DeviceCheckItem;
import cn.com.tpri.tpcheck.entity.DeviceCheckRecord;
import cn.com.tpri.tpcheck.entity.DeviceInfo;
import cn.com.tpri.tpcheck.entity.DeviceParam;
import cn.com.tpri.tpcheck.entity.Picture;
import cn.com.tpri.tpcheck.store.DeviceStore;
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
	DeviceStore loadDeviceInfos(long aid, long id);
	List<DeviceInfo> loadInfos(long id);
	List<DeviceParam> loadParams(long id);
	void update(Map param);
	List<DeviceCheckItem> loadCheckItems(long id);
	List<Picture> loadDevicePic(long id);
	List<Device> loadBySupDev(long id);
	List<DeviceCheckRecord> loadDeviceCheckRecords(Long id);
}
