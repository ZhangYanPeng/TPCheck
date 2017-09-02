package cn.com.tpri.tpcheck.service;

import java.util.List;

import cn.com.tpri.tpcheck.entity.BaseType;

public interface IBaseTypeService {
	int add(BaseType baseType);
	int delete(long id);
	int edit(BaseType baseType);
	List<BaseType> list();
	BaseType get(long id);
}
