package cn.com.tpri.tpcheck.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.com.tpri.tpcheck.entity.Blog;
import cn.com.tpri.tpcheck.entity.Picture;
import cn.com.tpri.tpcheck.service.IBlogService;
import cn.com.tpri.tpcheck.service.IPictureService;
import cn.com.tpri.tpcheck.support.PageResults;

@Controller
@RequestMapping(value="/blog")
public class BlogController {

	@Autowired
	IBlogService blogService;
	@Autowired
	IPictureService pictureService;
	
	@RequestMapping(value = "/list_blog")
	public @ResponseBody PageResults<Blog>  listBlog(int page) {
		return blogService.getByPage(page);
	}
	
	@RequestMapping(value = "/edit_blog")
	public @ResponseBody int  editBlog(String id, String title, String content, String url) {
		Blog blog = blogService.load(Long.valueOf(id));
		blog.setTitle(title);
		blog.setContent(content);
		blog.setUrl(url);
		return blogService.edit(blog);
	}
	
	@RequestMapping(value = "/add_blog")
	public @ResponseBody int  addBlog(String title, String content) {
		Blog blog = new Blog();
		blog.setTitle(title);
		blog.setContent(content);
		blog.setDate(System.currentTimeMillis());
		return blogService.add(blog);
	}
	
	@RequestMapping(value = "/delete_blog")
	public @ResponseBody int  listBlog(String id) {
		Blog blog = blogService.load(Long.valueOf(id));
		return blogService.delete(blog);
	}
	
	@RequestMapping(value = "/load_blog")
	public @ResponseBody Blog  loadBlog(String id) {
		return blogService.load(Long.valueOf(id));
	}
	
	@RequestMapping(value = "/load_blog_pic")
	public @ResponseBody List<Picture>  loadBlogPic(String id) {
		return pictureService.loadBlogPic(Long.valueOf(id));
	}
	
	@RequestMapping(value = "/upload_pic")
	public @ResponseBody int uploadPic(@RequestParam String id, @RequestParam MultipartFile picture, HttpServletRequest request){
		Picture pic = new Picture();
		Blog b = blogService.load(Long.valueOf(id));
		pic.setBlog(b);
		String originalFilename = picture.getOriginalFilename();
		String genePath = request.getSession().getServletContext().getRealPath("/upload/blog/");
		pic.setSrc(request.getContextPath()+"/upload/blog/"+originalFilename);
		pic.setPath(genePath+"/"+originalFilename);
		pictureService.save(pic, picture);
		return 0;
	}
	
	@RequestMapping(value = "/remove_pic")
	public @ResponseBody int removePic(String id){
		return pictureService.delete(Long.valueOf(id));
	}
}
