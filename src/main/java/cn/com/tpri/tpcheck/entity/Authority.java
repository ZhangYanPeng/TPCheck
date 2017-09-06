package cn.com.tpri.tpcheck.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "t_authority")
public class Authority {
	@Id
	@GeneratedValue(generator = "authority_generator")
	@GenericGenerator(name = "authority_generator", strategy = "increment")
	private long id;
	
	@ManyToOne
	private Account account;
	
	@ManyToOne
	private Department department;
	
	@ManyToOne
	private BaseType baseType;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public BaseType getBaseType() {
		return baseType;
	}

	public void setBaseType(BaseType baseType) {
		this.baseType = baseType;
	}
	
	
}
