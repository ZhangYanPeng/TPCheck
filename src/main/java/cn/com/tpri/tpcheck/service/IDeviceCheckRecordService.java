package cn.com.tpri.tpcheck.service;

import java.util.List;

import cn.com.tpri.tpcheck.entity.Device;
import cn.com.tpri.tpcheck.entity.DeviceCheckRecord;
import cn.com.tpri.tpcheck.support.PageResults;

public interface IDeviceCheckRecordService {
	int add(DeviceCheckRecord dcr);
	PageResults<DeviceCheckRecord> list(String device, int page, long did, long btid);
	List<DeviceCheckRecord> getall(long id);
}
