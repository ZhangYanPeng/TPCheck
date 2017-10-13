package cn.com.tpri.tpcheck.store;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ManyToOne;

import cn.com.tpri.tpcheck.entity.Department;
import cn.com.tpri.tpcheck.entity.Device;
import cn.com.tpri.tpcheck.entity.DeviceCheckItem;
import cn.com.tpri.tpcheck.entity.SuperDevice;

public class SuperDeviceStore {

	private long id;
	private String name;
	private String description;

	@ManyToOne
	private Department department;

	private DeviceStore self;
	private List<DeviceStore> devices;

	private String baseType;

	public SuperDeviceStore(SuperDevice sd, String type){
		this.devices = new ArrayList<DeviceStore>();
		this.id = sd.getId();
		this.name = sd.getName();
		this.description = sd.getDescription();
		this.department = sd.getDepartment();
		for(Device d : sd.getDevices()){
			if(d.getSupOrSub() == 0)
				this.self = new DeviceStore(d);
			else if(d.getDeviceType().getBaseType().getId()==Integer.valueOf(type)){
				this.devices.add(new DeviceStore(d));
			}
		}
		this.baseType = type;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public DeviceStore getSelf() {
		return self;
	}

	public void setSelf(DeviceStore self) {
		this.self = self;
	}

	public List<DeviceStore> getDevices() {
		return devices;
	}

	public void setDevices(List<DeviceStore> devices) {
		this.devices = devices;
	}

	public String getBaseType() {
		return baseType;
	}

	public void setBaseType(String baseType) {
		this.baseType = baseType;
	}

}
