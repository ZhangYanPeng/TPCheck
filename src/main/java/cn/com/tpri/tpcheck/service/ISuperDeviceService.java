package cn.com.tpri.tpcheck.service;

import java.util.List;

import cn.com.tpri.tpcheck.entity.SuperDevice;

public interface ISuperDeviceService {
	List<SuperDevice> list(long did);
}
