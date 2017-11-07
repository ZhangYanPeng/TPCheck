package cn.com.tpri.tpcheck.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tpri.tpcheck.dao.impl.BlogDAOImpl;
import cn.com.tpri.tpcheck.entity.Blog;
import cn.com.tpri.tpcheck.service.IBlogService;
import cn.com.tpri.tpcheck.support.PageResults;
@Service
public class BlogServiceImpl implements IBlogService{
	
	@Autowired
	BlogDAOImpl blogDAO;

	@Override
	@Transactional
	public int add(Blog blog) {
		// TODO Auto-generated method stub
		try {
			blogDAO.save(blog);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	@Override
	@Transactional
	public int edit(Blog blog) {
		// TODO Auto-generated method stub
		try {
			blogDAO.update(blog);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	@Override
	@Transactional
	public int delete(Blog blog) {
		// TODO Auto-generated method stub
		try {
			blogDAO.delete(blog);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	@Override
	@Transactional
	public PageResults<Blog> getByPage(int page) {
		// TODO Auto-generated method stub
		String hql = "from Blog order by date desc";
		String countHql = "select count(*) from Blog order by date desc";
		Object[] values = {};
		return blogDAO.findPageByFetchedHql(hql, countHql, page, 10, values);
	}

	@Override
	@Transactional
	public Blog load(long id) {
		// TODO Auto-generated method stub
		return blogDAO.get(id);
	}

}
