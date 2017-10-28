package cn.com.tpri.tpcheck.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "t_admin",
uniqueConstraints = {@UniqueConstraint(columnNames={"username"})})
public class Admin {
	@Id
	@GeneratedValue(generator = "admingenerator")
	@GenericGenerator(name = "admingenerator", strategy = "increment")
	private long id;
	
	private String username;
	private String password;
	
	//all authorities 0xFFFFFFFF
	int authority;

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

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}
	
	
	
}
