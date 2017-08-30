package cn.com.tpri.tpcheck.service;

import cn.com.tpri.tpcheck.entity.Admin;
import cn.com.tpri.tpcheck.support.PageResults;

public interface IAdminService {
	public Admin login(String username, String password);
	public PageResults<Admin> getByPage(int page);
	public Admin load(long id);
	public int add(Admin admin);
	public int edit(Admin admin);
	public int delete(Long id);

}
