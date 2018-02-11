package cn.com.tpri.tpcheck.service;

import java.util.List;

import cn.com.tpri.tpcheck.entity.Account;
import cn.com.tpri.tpcheck.entity.Authority;

public interface IAuthorityService {
	int add(Authority auth);
	int delete(Authority auth);
	int edit(Authority auth);
	Authority load(long id);
	List<Authority> loadByAccount(Long aid);
	int checkAuthority(long aid, long did);
}
