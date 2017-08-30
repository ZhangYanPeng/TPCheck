package cn.com.tpri.tpcheck.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "t_device_info" )
public class DeviceInfo {
	@Id
	@GeneratedValue(generator = "device_info_generator")
	@GenericGenerator(name = "device_info_generator", strategy = "increment")
	private long id;
	
	private String value;
	
	@ManyToOne
	private DeviceCheckItem deviceCheckItem;
	
	@ManyToOne
	private Device device;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public DeviceCheckItem getDeviceCheckItem() {
		return deviceCheckItem;
	}

	public void setDeviceCheckItem(DeviceCheckItem deviceCheckItem) {
		this.deviceCheckItem = deviceCheckItem;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}
	
	
}
