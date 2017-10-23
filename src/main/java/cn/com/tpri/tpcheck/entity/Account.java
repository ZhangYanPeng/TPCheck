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
@Table(name = "t_account")
public class Account {
	@Id
	@GeneratedValue(generator = "account_generator")
	@GenericGenerator(name = "account_generator", strategy = "increment")
	private long id;

	private String username;
	private String password;

	@JsonIgnore
	@OneToMany( fetch = FetchType.LAZY )
	@JoinColumn( name = "account_id" )
	private Set<Authority> authorities;
	
	@JsonIgnore
	@OneToMany( fetch = FetchType.LAZY )
	@JoinColumn( name = "account_id" )
	private Set<DeviceCheckRecord> deviceCheckRecords;
	
	@ManyToOne
	private Department department;

	private int position;
	
	private int state;

	// all authorities 0xFFFFFFFF
	private int authority;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public Set<DeviceCheckRecord> getDeviceCheckRecords() {
		return deviceCheckRecords;
	}

	public void setDeviceCheckRecords(Set<DeviceCheckRecord> deviceCheckRecords) {
		this.deviceCheckRecords = deviceCheckRecords;
	}
	
}
