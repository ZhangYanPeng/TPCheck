package cn.com.tpri.tpcheck.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "t_device_type" )
public class DeviceType {
	@Id
	@GeneratedValue(generator = "device_type_generator")
	@GenericGenerator(name = "device_type_generator", strategy = "increment")
	private long id;
	
	private int baseType;
	private String type;
	
	@JsonIgnore
	@OneToMany( fetch = FetchType.LAZY )
	@JoinColumn( name = "device_type_id" )
	private Set<Device> devices;
	
	@JsonIgnore
	@ManyToMany( fetch = FetchType.EAGER )
	@JoinColumn( name = "device_type_id" )
	private Set<DeviceCheckItem> deviceCheckItems;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getBaseType() {
		return baseType;
	}

	public void setBaseType(int baseType) {
		this.baseType = baseType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Device> getDevices() {
		return devices;
	}

	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}

	public Set<DeviceCheckItem> getDeviceCheckItems() {
		return deviceCheckItems;
	}

	public void setDeviceCheckItems(Set<DeviceCheckItem> deviceCheckItems) {
		this.deviceCheckItems = deviceCheckItems;
	}
	
	
	
}
