package cn.com.tpri.tpcheck.service;

import cn.com.tpri.tpcheck.entity.Blog;
import cn.com.tpri.tpcheck.support.PageResults;

public interface IBlogService {
	int add(Blog blog);
	int edit(Blog blog);
	int delete(Blog blog);
	Blog load(long id);
	PageResults<Blog> getByPage(int page);
}
