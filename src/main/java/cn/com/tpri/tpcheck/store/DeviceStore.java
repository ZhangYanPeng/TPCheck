package cn.com.tpri.tpcheck.store;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;

import cn.com.tpri.tpcheck.entity.Device;
import cn.com.tpri.tpcheck.entity.DeviceCheckItem;
import cn.com.tpri.tpcheck.entity.DeviceInfo;
import cn.com.tpri.tpcheck.entity.DeviceType;

public class DeviceStore {
	
	private long id;

	private String name;
	private String description;
	private int supOrSub;

	private DeviceType deviceType;
	private List<DeviceCheckItem> deviceCheckItems;
	private List<DeviceInfo> deviceInfos;

	public DeviceStore(Device d) {
		// TODO Auto-generated constructor stub
		this.id = d.getId();
		this.name = d.getName();
		this.description = d.getDescription();
		this.supOrSub = d.getSupOrSub();
		this.deviceType = d.getDeviceType();
		deviceCheckItems = new ArrayList<DeviceCheckItem>();
		for(DeviceCheckItem dci :  d.getDeviceType().getDeviceCheckItems()){
			deviceCheckItems.add(dci);
		}
		deviceInfos = new ArrayList<DeviceInfo>();
		for(DeviceInfo di :  d.getDeviceInfos()){
			deviceInfos.add(di);
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSupOrSub() {
		return supOrSub;
	}

	public void setSupOrSub(int supOrSub) {
		this.supOrSub = supOrSub;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public List<DeviceCheckItem> getDeviceCheckItems() {
		return deviceCheckItems;
	}

	public void setDeviceCheckItems(List<DeviceCheckItem> deviceCheckItems) {
		this.deviceCheckItems = deviceCheckItems;
	}

	public List<DeviceInfo> getDeviceInfos() {
		return deviceInfos;
	}

	public void setDeviceInfos(List<DeviceInfo> deviceInfos) {
		this.deviceInfos = deviceInfos;
	}

	
}
