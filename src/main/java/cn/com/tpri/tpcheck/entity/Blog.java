package cn.com.tpri.tpcheck.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_blog")
public class Blog {
	@Id
	@GeneratedValue(generator = "blog_generator")
	@GenericGenerator(name = "blog_generator", strategy = "increment")
	private long id;

	private String title;
	@JsonIgnore
	@OneToMany( fetch = FetchType.LAZY )
	@JoinColumn( name = "blog_id" )
	private Set<Picture> pic;
	private String content;
	private String url;
	private long  date;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Picture> getPic() {
		return pic;
	}

	public void setPic(Set<Picture> pic) {
		this.pic = pic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}
	
}
