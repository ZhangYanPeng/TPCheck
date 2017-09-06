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
@Table( name = "t_company" )
public class Company {
	@Id
	@GeneratedValue(generator = "company_generator")
	@GenericGenerator(name = "company_generator", strategy = "increment")
	private long id;
	
	private String name;
	
	private String location;
	private String description;
	private String logo;
	
	private long permission_date;
	
	@JsonIgnore
	@OneToMany( fetch = FetchType.LAZY )
	@JoinColumn( name = "company_id" )
	private Set<Department> departments;
	
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getPermission_date() {
		return permission_date;
	}

	public void setPermission_date(long permission_date) {
		this.permission_date = permission_date;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Set<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}
	
	
}
