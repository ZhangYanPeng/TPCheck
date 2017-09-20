package cn.com.tpri.tpcheck.entity;

import java.util.Date;
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
@Table( name = "t_device_check_record" )
public class DeviceCheckRecord {
	@Id
	@GeneratedValue(generator = "device_check_record_generator")
	@GenericGenerator(name = "device_check_record_generator", strategy = "increment")
	private long id;
	
	private String record;
	private Date date;
	private String error;
	
	@ManyToOne
	private Account account;
	
	@ManyToOne
	private Device device;
	
	@ManyToOne
	private DeviceCheckItem deviceCheckItem;
	
	@JsonIgnore
	@OneToMany( fetch = FetchType.LAZY )
	@JoinColumn( name = "deviceCheckRecord_id" )
	private Set<Picture> pictures;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public DeviceCheckItem getDeviceCheckItem() {
		return deviceCheckItem;
	}

	public void setDeviceCheckItem(DeviceCheckItem deviceCheckItem) {
		this.deviceCheckItem = deviceCheckItem;
	}

	public Set<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(Set<Picture> pictures) {
		this.pictures = pictures;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
