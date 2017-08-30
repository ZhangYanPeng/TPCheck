package cn.com.tpri.tpcheck.entity;

import java.util.List;

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
@Table( name = "t_device" )
public class Device {
	@Id
	@GeneratedValue(generator = "device_generator")
	@GenericGenerator(name = "device_generator", strategy = "increment")
	private long id;
	
	private String name;
	private String description;
	
	@JsonIgnore
	@ManyToOne
	private District district;
	
	@ManyToOne
	private DeviceType deviceType;
	
	@OneToMany( fetch = FetchType.LAZY )
	@JoinColumn( name = "device_id" )
	private List<DeviceInfo> deviceInfos;
	
	@OneToMany( fetch = FetchType.LAZY )
	@JoinColumn( name = "device_id" )
	private List<DeviceCheckRecord> deviceCheckRecords;

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

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public List<DeviceInfo> getDeviceInfos() {
		return deviceInfos;
	}

	public void setDeviceInfos(List<DeviceInfo> deviceInfos) {
		this.deviceInfos = deviceInfos;
	}

	public List<DeviceCheckRecord> getDeviceCheckRecords() {
		return deviceCheckRecords;
	}

	public void setDeviceCheckRecords(List<DeviceCheckRecord> deviceCheckRecords) {
		this.deviceCheckRecords = deviceCheckRecords;
	}
	
	
	
	
}
