package cn.com.tpri.tpcheck.store;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.tpri.tpcheck.entity.Account;
import cn.com.tpri.tpcheck.entity.DeviceCheckRecord;
import cn.com.tpri.tpcheck.entity.Picture;
import cn.com.tpri.tpcheck.service.IPictureService;
import cn.com.tpri.tpcheck.service.impl.PictureServiceImpl;

public class RecordStore {
	private long id;

	private String record;
	private String date;
	private String deviceCheckItem;
	private List<String> pictures;
	private long account;
	private long device;
	
	public RecordStore(DeviceCheckRecord dcr){
		
		this.id = dcr.getId();
		this.record = dcr.getRecord();
		this.date = (dcr.getDate().getYear()+1900)+"年"+dcr.getDate().getMonth()+"月"+dcr.getDate().getDay()+"日"
				+dcr.getDate().getHours()+"时"+dcr.getDate().getMinutes()+"分"+dcr.getDate().getSeconds()+"秒";
		pictures = new ArrayList<String>();
		for(Picture pic : dcr.getPictures()){
			pictures.add(pic.getSrc());
		}
		this.account = dcr.getAccount().getId();
		this.device = dcr.getDevice().getId();
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

	public String getDeviceCheckItem() {
		return deviceCheckItem;
	}

	public void setDeviceCheckItem(String deviceCheckItem) {
		this.deviceCheckItem = deviceCheckItem;
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

	public long getDevice() {
		return device;
	}

	public void setDevice(long device) {
		this.device = device;
	}

	
}
