package cn.com.tpri.tpcheck.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "t_device_check_item" )
public class DeviceCheckItem {
	@Id
	@GeneratedValue(generator = "device_check_item_generator")
	@GenericGenerator(name = "device_check_item_generator", strategy = "increment")
	private long id;
	
	private String description;
	private int type;
	
	@JsonIgnore
	@OneToMany( fetch = FetchType.LAZY )
	@JoinColumn( name = "deviceCheckItem_id" )
	private Set<DeviceCheckRecord> DeviceCheckItems;
	
	@ManyToOne
	private DeviceType deviceType;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Set<DeviceCheckRecord> getDeviceCheckItems() {
		return DeviceCheckItems;
	}

	public void setDeviceCheckItems(Set<DeviceCheckRecord> deviceCheckItems) {
		DeviceCheckItems = deviceCheckItems;
	}

	
}
