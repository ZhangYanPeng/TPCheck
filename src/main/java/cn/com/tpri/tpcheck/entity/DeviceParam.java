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
import org.json.JSONObject;

@Entity
@Table( name = "t_device_param" )
public class DeviceParam {
	@Id
	@GeneratedValue(generator = "device_param_generator")
	@GenericGenerator(name = "device_param_generator", strategy = "increment")
	private long id;
	
	private String name;
	private String description;
	private int authority;
	
	@JsonIgnore
	@ManyToOne
	private DeviceType deviceType;
	
	@JsonIgnore
	@OneToMany( fetch = FetchType.LAZY )
	@JoinColumn( name = "deviceParam_id" )
	private Set<DeviceInfo> deviceInfos;

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

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public Set<DeviceInfo> getDeviceInfos() {
		return deviceInfos;
	}

	public void setDeviceInfos(Set<DeviceInfo> deviceInfos) {
		this.deviceInfos = deviceInfos;
	}

}
