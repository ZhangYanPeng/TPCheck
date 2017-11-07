package cn.com.tpri.tpcheck.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.json.JSONObject;

@Entity
@Table(name="t_picture")
public class Picture {
	@Id
	@GeneratedValue(generator = "picture_generator")
	@GenericGenerator(name = "picture_generator", strategy = "increment")
	private long id;
	
	private String name;
	private String path;
	private String src;
	
	@ManyToOne
	private Device device;
	
	@ManyToOne
	private DeviceCheckRecord deviceCheckRecord;
	
	@ManyToOne
	private Blog blog;

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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public DeviceCheckRecord getDeviceCheckRecord() {
		return deviceCheckRecord;
	}

	public void setDeviceCheckRecord(DeviceCheckRecord deviceCheckRecord) {
		this.deviceCheckRecord = deviceCheckRecord;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}
	
	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public JSONObject toJSON() {
		// TODO Auto-generated method stub
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", id);
		jsonObj.put("name", name);
		jsonObj.put("path", path);
		jsonObj.put("src", src);
		return jsonObj;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	
}
