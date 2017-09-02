package cn.com.tpri.tpcheck.entity;



import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "t_base_type" )
public class BaseType {
	@Id
	@GeneratedValue(generator = "base_type_generator")
	@GenericGenerator(name = "base_type_generator", strategy = "increment")
	private long id;
	
	private String name;
	
	@JsonIgnore
	@OneToMany( fetch = FetchType.LAZY )
	@JoinColumn( name = "baseType_id" )
	private Set<DeviceType> deviceTypes;

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

	public Set<DeviceType> getDeviceTypes() {
		return deviceTypes;
	}

	public void setDeviceTypes(Set<DeviceType> deviceTypes) {
		this.deviceTypes = deviceTypes;
	}
	
	
}
