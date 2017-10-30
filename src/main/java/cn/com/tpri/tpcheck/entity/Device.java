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
@Table(name = "t_device")
public class Device {
	@Id
	@GeneratedValue(generator = "device_generator")
	@GenericGenerator(name = "device_generator", strategy = "increment")
	private long id;

	private String name;
	private String description;
	private int supOrSub;
	private int pic_num;

	@ManyToOne
	private SuperDevice superDevice;

	@ManyToOne
	private DeviceType deviceType;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "device_id")
	private Set<DeviceInfo> deviceInfos;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "device_id")
	private Set<Picture> pictures;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "device_id")
	private Set<DeviceCheckRecord> deviceCheckRecords;
	
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

	public Set<DeviceCheckRecord> getDeviceCheckRecords() {
		return deviceCheckRecords;
	}

	public void setDeviceCheckRecords(Set<DeviceCheckRecord> deviceCheckRecords) {
		this.deviceCheckRecords = deviceCheckRecords;
	}

	public SuperDevice getSuperDevice() {
		return superDevice;
	}

	public void setSuperDevice(SuperDevice superDevice) {
		this.superDevice = superDevice;
	}

	public int getSupOrSub() {
		return supOrSub;
	}

	public void setSupOrSub(int supOrSub) {
		this.supOrSub = supOrSub;
	}

	public Set<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(Set<Picture> pictures) {
		this.pictures = pictures;
	}

	public int getPic_num() {
		return pic_num;
	}

	public void setPic_num(int pic_num) {
		this.pic_num = pic_num;
	}
	
	
}
