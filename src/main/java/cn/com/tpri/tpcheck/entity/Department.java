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
@Table( name = "t_department" )
public class Department {
	@Id
	@GeneratedValue(generator = "department_generator")
	@GenericGenerator(name = "department_generator", strategy = "increment")
	private long id;
	
	private String name;
	
	@ManyToOne
	private Company company;
	
	@JsonIgnore
	@OneToMany( fetch = FetchType.LAZY )
	@JoinColumn( name = "department_id" )
	private Set<Account> accounts;
	
	@JsonIgnore
	@OneToMany( fetch = FetchType.LAZY )
	@JoinColumn( name = "department_id" )
	private Set<Authority> authorities;
	
	@JsonIgnore
	@OneToMany( fetch = FetchType.LAZY )
	@JoinColumn( name = "department_id" )
	private Set<SuperDevice> superDevices;

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

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public Set<SuperDevice> getSuperDevices() {
		return superDevices;
	}

	public void setSuperDevices(Set<SuperDevice> superDevices) {
		this.superDevices = superDevices;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
	
	
}
