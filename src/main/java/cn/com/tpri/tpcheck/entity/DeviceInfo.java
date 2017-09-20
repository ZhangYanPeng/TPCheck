package cn.com.tpri.tpcheck.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.json.JSONObject;

@Entity
@Table( name = "t_device_info" )
public class DeviceInfo {
	@Id
	@GeneratedValue(generator = "device_info_generator")
	@GenericGenerator(name = "device_info_generator", strategy = "increment")
	private long id;
	
	private String value;
	
	@ManyToOne
	private DeviceParam deviceParam;
	
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

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public DeviceParam getDeviceParam() {
		return deviceParam;
	}

	public void setDeviceParam(DeviceParam deviceParam) {
		this.deviceParam = deviceParam;
	}

	public JSONObject toJSON() {
		// TODO Auto-generated method stub
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", id);
		jsonObj.put("value", value);
		jsonObj.put("deviceParam",deviceParam.toJSON());
		return jsonObj;
	}
	
}
