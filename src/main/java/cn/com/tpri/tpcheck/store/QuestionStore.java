package cn.com.tpri.tpcheck.store;

import java.util.ArrayList;
import java.util.List;

import cn.com.tpri.tpcheck.entity.Picture;
import cn.com.tpri.tpcheck.entity.Question;

public class QuestionStore {
	private long id;

	private String record;
	private String date;
	private List<String> pictures;
	private long account;
	
	public QuestionStore(Question q){
		
		this.id = q.getId();
		this.record = q.getRecord();
		this.date = (q.getDate().getYear()+1900)+"年"+q.getDate().getMonth()+"月"+q.getDate().getDay()+"日"
				+q.getDate().getHours()+"时"+q.getDate().getMinutes()+"分"+q.getDate().getSeconds()+"秒";
		pictures = new ArrayList<String>();
		for(Picture pic : q.getPictures()){
			pictures.add(pic.getSrc());
		}
		this.account = q.getAccount().getId();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public List<String> getPictures() {
		return pictures;
	}

	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}

	public long getAccount() {
		return account;
	}

	public void setAccount(long account) {
		this.account = account;
	}
	
}
