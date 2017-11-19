package cn.com.tpri.tpcheck.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tpri.tpcheck.dao.impl.AuthorityDAOImpl;
import cn.com.tpri.tpcheck.dao.impl.DeviceCheckRecordDAOImpl;
import cn.com.tpri.tpcheck.dao.impl.PictureDAOImpl;
import cn.com.tpri.tpcheck.entity.Account;
import cn.com.tpri.tpcheck.entity.Authority;
import cn.com.tpri.tpcheck.entity.DeviceCheckRecord;
import cn.com.tpri.tpcheck.entity.Picture;
import cn.com.tpri.tpcheck.service.IDeviceCheckRecordService;
import cn.com.tpri.tpcheck.store.RecordStore;
import cn.com.tpri.tpcheck.support.Constants;
import cn.com.tpri.tpcheck.support.PageResults;

@Service
public class DeviceCheckRecordServiceImpl implements IDeviceCheckRecordService{

	@Autowired
	DeviceCheckRecordDAOImpl deviceCheckRecordDAO;
	@Autowired
	PictureDAOImpl pictureDAO;
	@Autowired
	AuthorityDAOImpl authorityDAO;
	
	@Override
	@Transactional
	public int add(DeviceCheckRecord dcr) {
		// TODO Auto-generated method stub
		try {
			deviceCheckRecordDAO.save(dcr);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	@Override
	@Transactional
	public PageResults<DeviceCheckRecord> list(String device, int page, long did, long btid) {
		// TODO Auto-generated method stub
		String hql = "from DeviceCheckRecord where device.name like ? and device.superDevice.department.id = ? and device.deviceType.baseType.id = ? order by date desc";
		String countHql = "select count(*) from DeviceCheckRecord where device.name like ? and device.superDevice.department.id = ? and device.deviceType.baseType.id = ?";
		Object[] values = {"%%"+device+"%%",did,btid};
		PageResults<DeviceCheckRecord> pr = deviceCheckRecordDAO.findPageByFetchedHql(hql, countHql, page, Constants.PAGE_SIZE, values);
		for( DeviceCheckRecord dcr : pr.getResults()){
			if(dcr.getPictures() == null || dcr.getPictures().size() == 0 && dcr.getImgStr() != null && !dcr.getImgStr().equals("") ){
				String[] pics = dcr.getImgStr().split(";");
				for (int i=1; i<pics.length;i++){
					String hqlString = "from Picture where name = ?";
					Object[] val = {pics[i]};
					Picture pic = pictureDAO.getByHQL(hqlString, val);
					pic.setDeviceCheckRecord(dcr);
					pictureDAO.update(pic);
				}
			}
		}
		return deviceCheckRecordDAO.findPageByFetchedHql(hql, countHql, page, Constants.PAGE_SIZE, values);
	}

	@Override
	@Transactional
	public List<DeviceCheckRecord> getall(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public DeviceCheckRecord find(long aid, long did, Date date) {
		// TODO Auto-generated method stub
		String hql = "from DeviceCheckRecord where account.id = ? and device.id = ? and date = ?";
		Object[] values = {aid, did, date};
		return deviceCheckRecordDAO.getByHQL(hql, values);
	}

	@Override
	@Transactional
	public int delete(long rid, long aid) {
		// TODO Auto-generated method stub
		String hqlString = "from Authority where account.id = ? and department.id = ?";
		DeviceCheckRecord dcr = deviceCheckRecordDAO.get(rid);
		Object[] values = {aid, dcr.getDevice().getSuperDevice().getDepartment().getId()};
		List<Authority> la = authorityDAO.getListByHQL(hqlString, values);
		if(la == null || la.size()==0){
			return 0;
		}
		for( Picture pic : dcr.getPictures()){
			pictureDAO.delete(pic);
		}
		deviceCheckRecordDAO.delete(dcr);
		return 1;
	}

	@Override
	@Transactional
	public DeviceCheckRecord get(Long id) {
		// TODO Auto-generated method stub
		return deviceCheckRecordDAO.get(id);
	}
	
	@Override
	@Transactional
	public RecordStore loadRecrod(Long id) {
		// TODO Auto-generated method stub
		DeviceCheckRecord dcr =  deviceCheckRecordDAO.get(id);
		return new RecordStore(dcr);
	}

}
