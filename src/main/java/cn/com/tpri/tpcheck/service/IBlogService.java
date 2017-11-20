package cn.com.tpri.tpcheck.service;

import java.util.List;

import cn.com.tpri.tpcheck.entity.Blog;
import cn.com.tpri.tpcheck.entity.Picture;
import cn.com.tpri.tpcheck.support.PageResults;

public interface IBlogService {
	int add(Blog blog);
	int edit(Blog blog);
	int delete(Blog blog);
	Blog load(long id);
	PageResults<Blog> getByPage(int page);
	List<Blog> lastBlog(Integer n);
	Picture getBlogPic(long id);
}
