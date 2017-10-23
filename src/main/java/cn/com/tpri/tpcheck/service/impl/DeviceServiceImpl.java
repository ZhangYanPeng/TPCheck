package cn.com.tpri.tpcheck.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tpri.tpcheck.dao.impl.BaseTypeDAOImpl;
import cn.com.tpri.tpcheck.dao.impl.DepartmentDAOImpl;
import cn.com.tpri.tpcheck.dao.impl.DeviceCheckItemDAOImpl;
import cn.com.tpri.tpcheck.dao.impl.DeviceDAOImpl;
import cn.com.tpri.tpcheck.dao.impl.DeviceInfoDAOImpl;
import cn.com.tpri.tpcheck.dao.impl.DeviceParamDAOImpl;
import cn.com.tpri.tpcheck.dao.impl.DeviceTypeDAOImpl;
import cn.com.tpri.tpcheck.dao.impl.SuperDeviceDAOImpl;
import cn.com.tpri.tpcheck.entity.Device;
import cn.com.tpri.tpcheck.entity.DeviceCheckItem;
import cn.com.tpri.tpcheck.entity.DeviceInfo;
import cn.com.tpri.tpcheck.entity.DeviceParam;
import cn.com.tpri.tpcheck.entity.DeviceType;
import cn.com.tpri.tpcheck.entity.SuperDevice;
import cn.com.tpri.tpcheck.service.IDeviceService;
import cn.com.tpri.tpcheck.store.DeviceStore;
import cn.com.tpri.tpcheck.support.Constants;
import cn.com.tpri.tpcheck.support.PageResults;

@Service
public class DeviceServiceImpl implements IDeviceService{
	
	@Autowired
	DeviceDAOImpl deviceDAO;
	@Autowired
	DepartmentDAOImpl departmentDAO;
	@Autowired
	DeviceParamDAOImpl deviceParamDAO;
	@Autowired
	DeviceTypeDAOImpl deviceTypeDAO;
	@Autowired
	DeviceInfoDAOImpl deviceInfoDAO;
	@Autowired
	SuperDeviceDAOImpl superDeviceDAO;
	@Autowired
	BaseTypeDAOImpl baseTypeDAO;
	@Autowired
	DeviceCheckItemDAOImpl deviceCheckItemDAO;


	@Override
	@Transactional
	public int importDevice(List<Device> lDevice) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public Device load(long id) {
		// TODO Auto-generated method stub
		return deviceDAO.get(id);
	}

	@Override
	@Transactional
	public int add(Device device) {
		// TODO Auto-generated method stub
		try {
			deviceDAO.save(device);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public int delete(long device) {
		// TODO Auto-generated method stub
		try {
			Device d = deviceDAO.get(device);
			if(d.getSupOrSub()==0){
				if( d.getSuperDevice().getDevices().size()>1 )
					return -1;
				if( d.getDeviceCheckRecords() != null && d.getDeviceCheckRecords().size() >0)
					return -1;
				else{
					long sdid = d.getSuperDevice().getId();
					for(DeviceInfo di : d.getDeviceInfos()){
						deviceInfoDAO.delete(di);
					}
					deviceDAO.deleteById(device);
					superDeviceDAO.deleteById(sdid);
				}
			}else{
				if( d.getDeviceCheckRecords() != null && d.getDeviceCheckRecords().size() >0)
					return -1;
				else{
					for(DeviceInfo di : d.getDeviceInfos()){
						deviceInfoDAO.delete(di);
					}
					deviceDAO.deleteById(device);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public int edit(Device device) {
		// TODO Auto-generated method stub
		try {
			deviceDAO.update(device);;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public PageResults<Device> list(int page, long did, long btid) {
		// TODO Auto-generated method stub
		String hql = "from Device where superDevice.department.id = ? and deviceType.baseType.id = ?";
		String countHql = "select count(*) from Device where superDevice.department.id = ? and deviceType.baseType.id = ?";
		Object[] values = {did, btid};
		return deviceDAO.findPageByFetchedHql(hql, countHql, page, Constants.PAGE_SIZE, values);
	}

	@Override
	@Transactional
	public int loadInSuperDevice(List<List> info, long did, long btid) {
		// TODO Auto-generated method stub
		int sum = 0;
		List<String> pList = info.get(0);
		List<String> pDesList = info.get(1);
		List<DeviceParam> paramList = new ArrayList<DeviceParam>();
		Object[] vals = {btid, "父类"};
		DeviceType dt = deviceTypeDAO.getByHQL("from DeviceType where baseType.id = ? and name = ?",vals);
		for(int i=1; i<pList.size(); i++){
			String hql = "from DeviceParam where deviceType.id = ? and name = ?";
			Object[] values = { dt.getId(), pList.get(i), pDesList.get(i)};
			DeviceParam dp = deviceParamDAO.getByHQL(hql, values);
			if(dp == null){
				dp = new DeviceParam();
				dp.setLevel(Integer.valueOf(pDesList.get(i).split("@")[1]));
				dp.setDescription(pDesList.get(i).split("@")[0]);
				dp.setName(pList.get(i));
				dp.setDeviceType(dt);
				deviceParamDAO.save(dp);
			}
			paramList.add(dp);
		}
		for(int i=2; i<info.size();i++){
			List<String> dinfo = info.get(i);
			Object[] vs = {dinfo.get(0), did};
			SuperDevice sd = superDeviceDAO.getByHQL("from SuperDevice where name = ? and department.id = ?", vs);
			if(sd != null){
				continue;
			}
			sd = new SuperDevice();
			sd.setName(dinfo.get(0));
			sd.setDepartment(departmentDAO.load(did));
			superDeviceDAO.saveOrUpdate(sd);
			Device d = new Device();
			d.setName(dinfo.get(0));
			d.setDeviceType(dt);
			d.setSupOrSub(0);
			d.setSuperDevice(sd);
			deviceDAO.save(d);
			for( int j=1; j < dinfo.size(); j++){
				DeviceParam dp = paramList.get(j-1);
				DeviceInfo di = new DeviceInfo();
				di.setDevice(d);
				di.setDeviceParam(dp);
				di.setValue(dinfo.get(j));
				deviceInfoDAO.save(di);
			}
			sum++;
		}
		return sum;
	}

	@Override
	@Transactional
	public int loadInSubDevice(List<List> info, Long did, Long btid) {
		// TODO Auto-generated method stub
		int sum=0;
		for(int j=2; j<info.size();j++){
			List<String> dinfo = info.get(j);
			List<String> pList = info.get(0);
			List<String> pDesList = info.get(1);
			List<DeviceParam> paramList = new ArrayList<DeviceParam>();
			Object[] vals = {btid, dinfo.get(2)};
			if(dinfo.get(0).equals("") || dinfo.get(1).equals(""))
				continue;
			DeviceType dt = deviceTypeDAO.getByHQL("from DeviceType where baseType.id = ? and name = ?",vals);
			if(dt == null){
				dt = new DeviceType();
				dt.setName(dinfo.get(2));
				dt.setBaseType(baseTypeDAO.load(btid));
				deviceTypeDAO.save(dt);
			}
			
			Object[] vas = {dinfo.get(1) , dinfo.get(0), did};
			Device d = deviceDAO.getByHQL("from Device where name = ? and superDevice.name = ? and superDevice.department.id = ?", vas);
			if(d != null){
				continue;
			}
			
			d = new Device();
			d.setName(dinfo.get(1));
			d.setDeviceType(dt);
			d.setSupOrSub(1);
			Object[] vs ={dinfo.get(0), did};
			SuperDevice sd = superDeviceDAO.getByHQL("from SuperDevice where name = ? and department.id = ?", vs);
			d.setSuperDevice(sd);
			deviceDAO.save(d);
			for(int i=3; i<pList.size(); i++){
				String hql = "from DeviceParam where deviceType.id = ? and name = ? and description = ?";
				Object[] values = { dt.getId(), pList.get(i), pDesList.get(i)};
				DeviceParam dp = deviceParamDAO.getByHQL(hql, values);
				if(dp == null){
					dp = new DeviceParam();
					dp.setLevel(Integer.valueOf(pDesList.get(i).split("@")[1]));
					dp.setDescription(pDesList.get(i).split("@")[0]);
					dp.setName(pList.get(i));
					dp.setDeviceType(dt);
					deviceParamDAO.save(dp);
				}
				DeviceInfo di = new DeviceInfo();
				di.setDevice(d);
				di.setDeviceParam(dp);
				di.setValue(dinfo.get(i));
				deviceInfoDAO.save(di);
			}
			sum++;
		}
		
		return sum;
	}

	@Override
	@Transactional
	public List<DeviceParam> loadParams(long id) {
		// TODO Auto-generated method stub
		Device d = deviceDAO.get(id);
		String hql = "from DeviceParam where deviceType.id = ?";
		Object[] values = {d.getDeviceType().getId()};
		return deviceParamDAO.getListByHQL(hql, values);
	}

	@Override
	@Transactional
	public DeviceStore loadDeviceInfos(long id) {
		// TODO Auto-generated method stub
		Device d = deviceDAO.get(id);
		return new DeviceStore(d);
	}
	
	@Override
	@Transactional
	public List<DeviceInfo> loadInfos(long id) {
		// TODO Auto-generated method stub
		String hql = "from DeviceInfo where device.id = ?";
		Object[] values = {id};
		return deviceInfoDAO.getListByHQL(hql, values);
	}

	@Override
	@Transactional
	public void update(Map param) {
		// TODO Auto-generated method stub
		long did = Long.valueOf((String) param.get("id"));
		Device d = deviceDAO.get(did);
		Set<DeviceParam> params = d.getDeviceType().getDeviceParams();
		for(DeviceParam dp : params) {
			String value = (String) param.get(""+dp.getId());
			String hql = "from DeviceInfo where device.id =? and deviceParam.id = ?";
			Object[] vas = {did, dp.getId()};
			DeviceInfo di = deviceInfoDAO.getByHQL(hql, vas);
			if(di == null) {
				if(value != null && value !="") {
					di = new DeviceInfo();
					di.setDevice(d);
					di.setDeviceParam(dp);
					di.setValue(value);
					deviceInfoDAO.save(di);
				}
			}else {
				di.setValue(value);
				deviceInfoDAO.update(di);
			}
		}
	}

	@Override
	@Transactional
	public List<DeviceCheckItem> loadCheckItems(long id) {
		// TODO Auto-generated method stub
		String hql = "from DeviceCheckItem where deviceType.id = ?";
		Object[] values = {deviceDAO.get(id).getDeviceType().getId()};
		List<DeviceCheckItem> dciList = deviceCheckItemDAO.getListByHQL(hql, values);
		return dciList;
	}

}
