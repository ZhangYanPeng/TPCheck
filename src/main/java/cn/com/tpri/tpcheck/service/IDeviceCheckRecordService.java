package cn.com.tpri.tpcheck.service;

import java.util.Date;
import java.util.List;

import cn.com.tpri.tpcheck.entity.Account;
import cn.com.tpri.tpcheck.entity.Device;
import cn.com.tpri.tpcheck.entity.DeviceCheckRecord;
import cn.com.tpri.tpcheck.store.RecordStore;
import cn.com.tpri.tpcheck.support.PageResults;

public interface IDeviceCheckRecordService {
	int add(DeviceCheckRecord dcr);
	PageResults<DeviceCheckRecord> list(String device, int page, long did, long btid);
	List<DeviceCheckRecord> getall(long id);
	DeviceCheckRecord find(long aid, long did, Date date);
	int delete(long rid, long aid);
	DeviceCheckRecord get(Long id);
	RecordStore loadRecrod(Long id);
}
