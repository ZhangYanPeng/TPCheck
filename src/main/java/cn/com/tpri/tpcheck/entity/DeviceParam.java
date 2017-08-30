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
@Table( name = "t_device_param" )
public class DeviceParam {
	@Id
	@GeneratedValue(generator = "device_param_generator")
	@GenericGenerator(name = "device_param_generator", strategy = "increment")
	private long id;
	
	private String name;
	private String description;
	
	@JsonIgnore
	@OneToMany( fetch = FetchType.LAZY )
	@JoinColumn( name = "device_param_id" )
	private Set<DeviceCheckItem> deviceCheckItems;

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

	public Set<DeviceCheckItem> getDeviceCheckItems() {
		return deviceCheckItems;
	}

	public void setDeviceCheckItems(Set<DeviceCheckItem> deviceCheckItems) {
		this.deviceCheckItems = deviceCheckItems;
	}
	
	
}
