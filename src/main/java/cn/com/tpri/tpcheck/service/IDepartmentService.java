package cn.com.tpri.tpcheck.service;

import java.util.List;

import cn.com.tpri.tpcheck.entity.Department;
import cn.com.tpri.tpcheck.support.PageResults;

public interface IDepartmentService {
	public Department load(long id);
	public int add(Department department);
	public int edit(Department department);
	public int delete(Long id);
	public List<Department> list(long cid);
	PageResults<Department> getByPage(int page, long cid);
}
