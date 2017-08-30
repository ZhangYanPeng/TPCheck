package cn.com.tpri.tpcheck.service;

import cn.com.tpri.tpcheck.entity.Company;
import cn.com.tpri.tpcheck.support.PageResults;

public interface ICompanyService {
	public PageResults<Company> getByPage(int page);
	public Company load(long id);
	public int add(Company company);
	public int edit(Company company);
	public int delete(Long id);

}
