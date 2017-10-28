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
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_account",
	uniqueConstraints = {@UniqueConstraint(columnNames={"username"})})
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
	private Company company;

	private int position;
	
	private int state;

	public long getId() {
		return id;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
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
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	public Set<DeviceCheckRecord> getDeviceCheckRecords() {
		return deviceCheckRecords;
	}

	public void setDeviceCheckRecords(Set<DeviceCheckRecord> deviceCheckRecords) {
		this.deviceCheckRecords = deviceCheckRecords;
	}
	
}
