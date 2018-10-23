package cn.com.tpri.tpcheck.service;

import java.util.List;

import org.json.JSONObject;

import cn.com.tpri.tpcheck.entity.SuperDevice;
import cn.com.tpri.tpcheck.store.SuperDeviceStore;

public interface ISuperDeviceService {
	List<SuperDevice> list(long did);
	SuperDeviceStore getAllInformation(long id,String type);
	List<SuperDevice> listByAccount(long id);
	List<SuperDevice> listByDep(long id);
}
