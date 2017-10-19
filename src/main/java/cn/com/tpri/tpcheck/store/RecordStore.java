package cn.com.tpri.tpcheck.store;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.com.tpri.tpcheck.entity.DeviceCheckRecord;
import cn.com.tpri.tpcheck.entity.Picture;

public class RecordStore {
	private long id;

	private String record;
	private String date;
	private String deviceCheckItem;
	private List<String> pictures;
	
	public RecordStore(DeviceCheckRecord dcr){
		this.record = dcr.getRecord();
		this.date = dcr.getDate().toGMTString();
		if( dcr.getDeviceCheckItem() != null )
			this.deviceCheckItem = dcr.getDeviceCheckItem().getDescription();
		else
			this.deviceCheckItem = "";
		pictures = new ArrayList<String>();
		for(Picture pic : dcr.getPictures()){
			pictures.add(pic.getSrc());
		}
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

}
